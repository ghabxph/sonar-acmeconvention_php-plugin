package com.acmecorp.convention.php.rules;

import com.acmecorp.convention.php.exception.NewIssueException;
import com.acmecorp.convention.php.helpers.PhpRegexModifier;
import com.acmecorp.convention.php.helpers.PhpStringLiteral;
import com.acmecorp.convention.php.rules.common.ForbidFunctionRule;
import com.google.common.collect.ImmutableSet;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.php.api.tree.Tree;
import org.sonar.plugins.php.api.tree.declaration.NamespaceNameTree;
import org.sonar.plugins.php.api.tree.expression.ExpressionTree;
import org.sonar.plugins.php.api.tree.expression.FunctionCallTree;
import org.sonar.plugins.php.api.tree.expression.LiteralTree;
import org.sonar.plugins.php.api.tree.expression.VariableIdentifierTree;

import java.util.Set;

/**
 * (Rule) Forbids the use of PHP Code Execution Functions
 * Disallowed Functions:
 *   - eval
 *   - create_functions
 *
 * To Support:
 *   - Disallow preg_replace with e (exec)
 *
 * @author ghabxph (me@ghabxph.info)
 */
@Rule(
    priority = Priority.MAJOR,
    key = "ForbidPhpCodeExecution",
    name = "Forbids the use of PHP Code Execution Functions",
    tags = {"convention"}
)
public class ForbidPhpCodeExecution extends ForbidFunctionRule {

    /**
     * List of command execution functions that are forbidden to be used by convention
     *
     * @return ImmutableSet
     */
    @Override
    protected Set<String> forbiddenFunctions() {
        return ImmutableSet.of("eval", "create_function");
    }

    /**
     * Error message to be returned if blocked function is called
     *
     * @return String
     */
    @Override
    protected String errorMessage() {
        return "${KEYWORD} is a forbidden php code execution function. Please consider not relying on this function.";
    }

    /**
     * Forbids certain functions listed returned by forbiddenFunctions() method
     *
     * @param tree FunctionCallTree
     */
    @Override
    public void visitFunctionCall(FunctionCallTree tree) {

        checkEvalInPregReplace(tree);

        super.visitFunctionCall(tree);
    }

    /**
     * Checks eval modifier in preg_replace
     *
     * @param tree  FunctionCallTree
     */
    private void checkEvalInPregReplace(FunctionCallTree tree) {
        ExpressionTree callee = tree.callee();
        String keyword = (callee.is(Tree.Kind.NAMESPACE_NAME)) ? ((NamespaceNameTree) callee).qualifiedName() : "";

        if (!keyword.equals("preg_replace")) {
            return;
        }

        try {
            checkIfPregParam1Variable(tree);
            checkIfPregEModifierIsSet(tree);
        } catch (NewIssueException e) {
            context().newIssue(this, tree, e.getMessage());
        }
    }

    /**
     * Checks if preg_replace's param 1 is variable
     *   - If it is, then things are risky if user can put value in the regex. They may introduce unintended
     *     action for the program, thus, a potential vulnerability
     *
     * @param tree  FunctionCallTree
     * @throws NewIssueException  Throws exception if preg_replace's first parameter is variable
     */
    private void checkIfPregParam1Variable(FunctionCallTree tree) throws NewIssueException {

        if (tree.arguments().get(0) instanceof VariableIdentifierTree) {

            VariableIdentifierTree paramPattern = (VariableIdentifierTree) tree.arguments().get(0);

            String pregReplace = "preg_replace(" + paramPattern.token().text() + ", ...)";

            throw new NewIssueException(pregReplace + " is risky. "     +
                    "Please consider refactoring this code. This is false positive if " +
                    "user is not able to manipulate this variable. preg_replace with "  +
                    "e modifier is prohibited.");
        }
    }

    /**
     * Checks if preg_replace's eval modifier is set
     *
     * @param tree checkIfPregEModifierIsSet
     * @throws NewIssueException  Throws exception if eval modifier is set
     */
    private void checkIfPregEModifierIsSet(FunctionCallTree tree) throws NewIssueException {

        String pattern = PhpStringLiteral.set(((LiteralTree) ((FunctionCallTree) tree.callee().getParent()).arguments().get(0)).value()).value();

        if (PhpRegexModifier.hasModifier(pattern, 'e')) {
            throw new NewIssueException("preg_replace with e is forbidden. Please consider not relying on this function.");
        }
    }
}

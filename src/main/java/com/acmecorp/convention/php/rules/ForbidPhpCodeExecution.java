package com.acmecorp.convention.php.rules;

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

import java.util.Set;

/**
 * (Rule) Forbids the use of PHP Code Execution Functions
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

    private void checkEvalInPregReplace(FunctionCallTree tree) {
        ExpressionTree callee = tree.callee();
        String keyword = (callee.is(Tree.Kind.NAMESPACE_NAME)) ? ((NamespaceNameTree) callee).qualifiedName() : "";

        if (!keyword.equals("preg_replace")) {
            return;
        }

        String pattern = PhpStringLiteral.set(((LiteralTree) ((FunctionCallTree) tree.callee().getParent()).arguments().get(0)).value()).value();

        if (PhpRegexModifier.hasModifier(pattern, 'e')) {
            context().newIssue(this, callee, "preg_replace with e is forbidden. Please consider not relying on this function.");
        }
    }
}

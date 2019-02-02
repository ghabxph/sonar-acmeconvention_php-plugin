package com.acmecorp.convention.php.rules.common;

import com.google.common.collect.ImmutableSet;
import org.sonar.plugins.php.api.tree.Tree;
import org.sonar.plugins.php.api.tree.declaration.NamespaceNameTree;
import org.sonar.plugins.php.api.tree.expression.ExpressionTree;
import org.sonar.plugins.php.api.tree.expression.FunctionCallTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

import java.util.regex.Pattern;

/**
 * (Common Rule) Common Rule that blocks certain usage of PHP Function
 *   - Note: It only blocks PHP function. Not method
 *
 * @author ghabxph (ghabxph.official@gmail.com)
 */
public abstract class ForbidFunctionRule extends PHPVisitorCheck {

    /**
     * List of functions that are forbidden to be used
     *
     * @return ImmutableSet
     */
    protected abstract ImmutableSet forbiddenFunctions();

    /**
     * Error message to be returned if blocked function is called
     *
     * @return String
     */
    protected abstract String errorMessage();


    /**
     * Final error message with replaced values, from errorMessage() abstract method
     *
     * @param keyword  Name of keyword that is being matched from the list of forbidden functions
     * @return String
     */
    private String finalErrorMessage(String keyword) {
        return errorMessage().replaceAll(Pattern.compile("\\$\\{KEYWORD}", Pattern.CASE_INSENSITIVE).pattern(), keyword);
    }

    /**
     * Forbids certain functions listed returned by forbiddenFunctions() method
     *
     * @param tree FunctionCallTree
     */
    @Override
    public void visitFunctionCall(FunctionCallTree tree) {

        ExpressionTree callee = tree.callee();
        String keyword = (callee.is(Tree.Kind.NAMESPACE_NAME)) ? ((NamespaceNameTree) callee).qualifiedName() : "";

        if (!keyword.equals("") && forbiddenFunctions().contains(keyword)) {
            context().newIssue(this, callee, finalErrorMessage(keyword));
            System.out.println(callee.getKind() + " " + keyword + ": " + finalErrorMessage(keyword));
        }
        super.visitFunctionCall(tree);
    }
}

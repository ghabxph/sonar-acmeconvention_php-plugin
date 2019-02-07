package com.acmecorp.convention.php.rules;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.php.api.tree.declaration.NamespaceNameTree;
import org.sonar.plugins.php.api.tree.expression.FunctionCallTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

/**
 * (Rule) Avoid usage of sizeof
 *     - Use count() instead
 *
 * @author ghabxph (me@ghabxph.info)
 */
@Rule(
    priority = Priority.MINOR,
    key = "AvoidFunctionSizeof",
    name = "Use count() instead of sizeof()",
    tags = {"convention"}
)
public class AvoidFunctionSizeof extends PHPVisitorCheck {

    /**
     * Visits function call
     *   - Looks for sizeof() call, and notifies user to use sizeof() instead
     *
     * @param tree  FunctionCallTree
     */
    @Override
    public void visitFunctionCall(FunctionCallTree tree) {

        if (((NamespaceNameTree)tree.callee()).name().text().equals("sizeof")) {
            context().newIssue(this, tree, "Use count() instead of sizeof()");
        }
        super.visitFunctionCall(tree);
    }
}

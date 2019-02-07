package com.acmecorp.convention.php.rules;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.php.api.tree.declaration.NamespaceNameTree;
import org.sonar.plugins.php.api.tree.expression.FunctionCallTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

/**
 * (Rule) Prevents usage of delete function
 *   - Use unset instead.
 *
 * @author ghabxph (me@ghabxph.info)
 */
@Rule(
    priority = Priority.MINOR,
    key = "AvoidFunctionDelete",
    name = "Use unset() instead of delete()",
    tags = {"convention"}
)
public class AvoidFunctionDelete extends PHPVisitorCheck {

    /**
     * Visits function call
     *   - Look for delete() and notifies user to use unset() instead
     *
     * @param tree  FunctionCallTree
     */
    @Override
    public void visitFunctionCall(FunctionCallTree tree) {

        if (((NamespaceNameTree)tree.callee()).name().text().equals("delete")) {
            context().newIssue(this, tree, "Use unset() instead of delete()");
        }
        super.visitFunctionCall(tree);
    }
}

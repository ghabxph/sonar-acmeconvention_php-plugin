package com.acmecorp.convention.php.rules;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.php.api.tree.statement.GotoStatementTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

/**
 * (Rule) Forbids the use of GOTO
 *
 * @author ghabxph (me@ghabxph.info)
 */
@Rule(
    priority = Priority.MAJOR,
    key = "ForbidGoto",
    name = "Forbids the use of GOTO",
    tags = {"convention"}
)
public class ForbidGoto extends PHPVisitorCheck {

    /**
     * Error Message
     */
    private static final String ERR_MSG = "Goto statement is not allowed by our convention. Please consider refactoring your code.";

    /**
     * Forbids goto statement
     *
     * @param tree GotoStatementTree
     */
    @Override
    public void visitGotoStatement(GotoStatementTree tree) {

        System.out.println(tree.getKind() + ": " + ERR_MSG);

        // Displays an error message, when a goto statement has been detected
        context().newIssue(this, tree, ERR_MSG);

        // super method must be called in order to visit function call node's children
        super.visitGotoStatement(tree);
    }
}

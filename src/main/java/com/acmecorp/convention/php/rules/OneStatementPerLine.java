package com.acmecorp.convention.php.rules;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.php.api.tree.ScriptTree;
import org.sonar.plugins.php.api.tree.statement.ExpressionStatementTree;
import org.sonar.plugins.php.api.tree.statement.StatementTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

/**
 * (Rule) There should only be one statement per line
 *
 * @author ghabxph (ghabxph.official@gmail.com)
 */
@Rule(
    priority = Priority.MAJOR,
    key = "OneStatementPerLine",
    name = "There should only be one statement per line",
    tags = {"convention", "bad-practice"}
)
public class OneStatementPerLine extends PHPVisitorCheck {

    private int lineOfCurrentStatement = 0;

    /**
     * Visits Script
     *   - Scans all statement one by one, and see if there are more than one statement within a line.
     *
     * @param tree  ScriptTree
     */
    @Override
    public void visitScript(ScriptTree tree) {

        for (StatementTree statement : tree.statements()) {
            if (statement instanceof ExpressionStatementTree) {
                lookForMoreThanOneStatementWithinLine((ExpressionStatementTree)statement);
            }
        }
        super.visitScript(tree);
    }

    /**
     * Looks for more than one statement within line
     *
     * @param statement  Current statement to be scanned
     */
    private void lookForMoreThanOneStatementWithinLine(ExpressionStatementTree statement) {

        int lineOfNextStatement = (statement.eosToken()).line();
        if (lineOfCurrentStatement == lineOfNextStatement) {
            context().newIssue(this, statement, "There should only be one statement per line.");
        }
        lineOfCurrentStatement = lineOfNextStatement;
    }
}

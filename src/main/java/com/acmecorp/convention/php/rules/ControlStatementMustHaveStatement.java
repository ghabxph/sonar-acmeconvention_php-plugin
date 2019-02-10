package com.acmecorp.convention.php.rules;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.php.api.tree.Tree;
import org.sonar.plugins.php.api.tree.statement.*;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

/**
 * (Rule) Control statement cannot be empty
 *
 * @author ghabxph (me@ghabxph.info)
 */
@Rule(
    priority = Priority.MAJOR,
    key = "ControlStatementMustHaveStatement",
    name = "Control statement cannot be empty",
    tags = {"convention", "code-smell"}
)
public class ControlStatementMustHaveStatement extends PHPVisitorCheck {

    /**
     * Visits for statement.
     *   - Captures for statement, and checks if it is empty or not.
     *
     * @param tree  ForStatementTree
     */
    @Override
    public void visitForStatement(ForStatementTree tree) {

        checkForStatements(tree.statements().get(0), tree, "For statement");
        super.visitForStatement(tree);
    }

    /**
     * Visits foreach statement.
     *   - Captures foreach statement, and checks if it is empty or not.
     *
     * @param tree  ForEachStatementTree
     */
    @Override
    public void visitForEachStatement(ForEachStatementTree tree) {

        checkForStatements(tree.statements().get(0), tree, "Foreach statement");
        super.visitForEachStatement(tree);
    }

    /**
     * Visits while statement.
     *   - Captures while statement and checks if it is empty or not.
     *
     * @param tree WhileStatementTree
     */
    @Override
    public void visitWhileStatement(WhileStatementTree tree) {

        checkForStatements(tree.statements().get(0), tree, "While statement");
        super.visitWhileStatement(tree);
    }

    /**
     * Visits do-while statement.
     *   - Captures do-while statement and checks if it is empty or not.
     *
     * @param tree DoWhileStatementTree
     */
    @Override
    public void visitDoWhileStatement(DoWhileStatementTree tree) {

        checkForStatements(tree.statement(), tree, "Do-While statement");
        super.visitDoWhileStatement(tree);
    }

    /**
     * Visits elseif clause.
     *   - Captures elseif statement and checks if it is empty or not.
     *
     * @param tree ElseifClauseTree
     */
    @Override
    public void visitElseifClause(ElseifClauseTree tree) {

        checkForStatements(tree.statements().get(0), tree, "Elseif clause");
        super.visitElseifClause(tree);
    }

    /**
     * Visits else clause.
     *   - Captures else statement and checks if it is empty or not.
     *
     * @param tree ElseClauseTree
     */
    @Override
    public void visitElseClause(ElseClauseTree tree) {

        checkForStatements(tree.statements().get(0), tree, "Else clause");
        super.visitElseClause(tree);

    }

    /**
     * Visits if statement.
     *   - Captures if statement and checks if it is empty or not.
     *
     * @param tree IfStatementTree
     */
    @Override
    public void visitIfStatement(IfStatementTree tree) {

        checkForStatements(tree.statements().get(0), tree, "If statement");
        super.visitIfStatement(tree);
    }

    /**
     * Check control structure if it has statement or not
     */
    private void checkForStatements(StatementTree statement, Tree tree, String statementType) {

        if (!(statement instanceof BlockTree)) {
            return;
        }

        if (((BlockTree)statement).statements().size() == 0) {
            context().newIssue(this, tree, statementType + " should not be empty.");
        }
    }
}

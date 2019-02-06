package com.acmecorp.convention.php.rules;

import org.sonar.php.tree.impl.statement.BlockTreeImpl;
import org.sonar.plugins.php.api.tree.declaration.MethodDeclarationTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

/**
 * (Rule) Method should have less than 100 lines of code
 *
 * @author ghabxph (ghabxph.official@gmail.com)
 */
public class MethodLess100Lines extends PHPVisitorCheck {

    /**
     * Visits method declaration
     *
     * @param tree MethodDeclarationTree
     */
    public void visitMethodDeclaration(MethodDeclarationTree tree) {

        BlockTreeImpl block = (BlockTreeImpl)tree.body();

        if (block.statements().size() >= 100) {
            context().newIssue(this, tree, "Method should have less than 100 statements");
        }

        super.visitMethodDeclaration(tree);
    }
}


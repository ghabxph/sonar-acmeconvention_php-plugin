package com.acmecorp.convention.php.rules.helpers;

import org.sonar.plugins.php.api.tree.CompilationUnitTree;
import org.sonar.plugins.php.api.tree.declaration.ClassDeclarationTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;
import org.sonar.plugins.php.api.visitors.PhpFile;

/**
 * (Class) Responsible for treating class like an issue
 *   - Intended to be used by other rules.
 *   - It's basically a trick...
 *
 * @author ghabxph (ghabxph.official@gmail.com)
 */
public class ClassCounter extends PHPVisitorCheck {

    /**
     * Visits class declarations
     *   - Basically counts number of class.
     *
     * @param tree
     */
    @Override
    public void visitClassDeclaration(ClassDeclarationTree tree) {

        context().newIssue(this, tree, "");
        super.visitClassDeclaration(tree);
    }

    /**
     * Returns the total number of class within a file.
     *
     * @param file  File to be scanned
     * @param tree  Tree to be used to analyze the file
     * @return      Returns the number of class within a file
     */
    public static int getClassCountInFile(PhpFile file, CompilationUnitTree tree) {

        return (new ClassCounter()).analyze(file, tree).size();
    }
}

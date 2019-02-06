package com.acmecorp.convention.php.rules;

import org.sonar.plugins.php.api.tree.ScriptTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

/**
 * (Rule) Rule that checks whether each php statement ends with CRLF
 *
 * @author ghabxph (ghabxph.official@gmail.com)
 */
public class NoCrlf extends PHPVisitorCheck {

    /**
     * Visits the PHP Script
     *
     * @param tree ScriptTree
     */
    @Override
    public void visitScript(ScriptTree tree) {

        super.visitScript(tree);
    }
}

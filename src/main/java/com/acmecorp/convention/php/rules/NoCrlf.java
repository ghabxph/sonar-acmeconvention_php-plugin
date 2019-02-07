package com.acmecorp.convention.php.rules;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.php.api.tree.ScriptTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

/**
 * (Rule) Rule that checks whether each php statement ends with CRLF
 *
 * @author ghabxph (ghabxph.official@gmail.com)
 */
@Rule(
    priority = Priority.MAJOR,
    key = "NoCrlf",
    name = "File should use unix line delimiter.",
    tags = {"convention"}
)
public class NoCrlf extends PHPVisitorCheck {

    /**
     * Visits the PHP Script
     *
     * @param tree ScriptTree
     */
    @Override
    public void visitScript(ScriptTree tree) {

        String file = context().getPhpFile().contents();

        if (file.contains("\r\n")) {
            context().newIssue(this, tree, "File should use unix line delimiter (LF).");
        }

        super.visitScript(tree);
    }
}

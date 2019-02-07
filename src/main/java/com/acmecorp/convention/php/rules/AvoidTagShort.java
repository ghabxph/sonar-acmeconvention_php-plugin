package com.acmecorp.convention.php.rules;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.php.api.tree.Tree;
import org.sonar.plugins.php.api.tree.lexical.SyntaxToken;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

/**
 * (Rule) Prohibits short open tag usage.
 *
 * @author ghabxph (me@ghabxph.info)
 */
@Rule(
    priority = Priority.MAJOR,
    key = "AvoidTagShort",
    name = "Avoid usage of short open tag",
    tags = {"convention"}
)
public class AvoidTagShort extends PHPVisitorCheck {

    /**
     * Visits token
     *   - Basically looks for <? or <?= token
     *   - If found, it notifies user that something is wrong with his/her code.
     *
     * @param token  SyntaxToken
     */
    @Override
    public void visitToken(SyntaxToken token) {

        if (token.text().equals("<?") || token.text().contains("<?=") && token.is(Tree.Kind.SCRIPT)) {
            context().newIssue(this, token, "Do not use short open tag.");
        }
        super.visitToken(token);
    }
}

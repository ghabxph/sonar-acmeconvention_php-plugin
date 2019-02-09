package com.acmecorp.convention.php.rules;

import com.acmecorp.convention.php.helpers.PhpStringLiteral;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.php.api.tree.expression.LiteralTree;
import org.sonar.plugins.php.api.tree.lexical.SyntaxToken;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

/**
 * (Rule) Use single quote (instead of double quote)
 *
 * @author ghabxph (me@ghabxph.info)
 */
@Rule(
    priority = Priority.MAJOR,
    key = "AvoidDoubleQuote",
    name = "Avoid usage of double quote for string literals.",
    tags = {"convention"}
)
public class AvoidDoubleQuote extends PHPVisitorCheck {

    /**
     * Visits token
     *
     * @param token  SyntaxToken
     */
    public void visitToken(SyntaxToken token) {

        if (!(token.getParent() instanceof LiteralTree) && !(token.getParent().getKind().name().equals("REGULAR_STRING_LITERAL"))) {
            super.visitToken(token);
            return;
        }

        if (PhpStringLiteral.set(token.text()).getQuote() == '"') {
            context().newIssue(this, token, "Avoid usage of double quote for string literals.");
        }

        super.visitToken(token);
    }
}

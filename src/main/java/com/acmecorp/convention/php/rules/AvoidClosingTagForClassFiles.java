package com.acmecorp.convention.php.rules;

import com.acmecorp.convention.php.rules.helpers.ClassCounter;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.php.api.tree.lexical.SyntaxToken;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

/**
 * (Rule) Prohibits closing tag.
 *   - In this rule, we only prohibit closing tag if file has class
 *   - This prevents false positive scans on general PHP file that are intended to be a Dynamic HTML
 *   - Dynamic HTML means, HTML that contains dynamic value that comes straight from the server
 *     - We can make dynamic html through javascript, but some developer, due to time restriction, directly
 *       get dynamic data from the server through PHP. In short, we anticipate simpler practical implementation
 *       by developer, and we don't want to warn them with useless error that won't help them create quality code.
 *
 *   - This rule basically wants to prohibit closing tag on non-html files. That's all.
 *
 * @author ghabxph (me@ghabxph.info)
 */
@Rule(
    priority = Priority.MAJOR,
    key = "AvoidClosingTagForClassFiles",
    name = "Avoid closing tag (for class files)",
    tags = {"convention"}
)
public class AvoidClosingTagForClassFiles extends PHPVisitorCheck {

    /**
     * Visits token
     *   - Returns error if there is ?> and has one single class.
     *
     * @param token SyntaxToken
     */
    @Override
    public void visitToken(SyntaxToken token) {

        if (token.getKind().name().equals("INLINE_HTML_TOKEN") && token.text().contains("?>") && hasClass()) {
            context().newIssue(this, token, "Usage of closing tag is prohibited. Please remove closing tag.");
        }
        super.visitToken(token);
    }

    /**
     * Do file have only one class?
     *
     * @return  Returns true if we have only one class within a file.
     */
    private boolean hasClass() {
        return ClassCounter.getClassCountInFile(context().getPhpFile(), context().tree()) >= 1;
    }
}

package com.acmecorp.convention.php.rules;

import com.acmecorp.convention.php.rules.helpers.ClassCounter;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.php.api.tree.ScriptTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

/**
 * (Rule) PHP File should have no more than 1 class
 *
 * @author ghabxph (me@ghabxph.info)
 */
@Rule(
    priority = Priority.MAJOR,
    key = "OneClassPerFile",
    name = "PHP File should have no more than 1 class.",
    tags = {"convention", "bad-practice"}
)
public class OneClassPerFile extends PHPVisitorCheck {

    /**
     * Visits Script
     *   - And check if class has more than one class.
     *
     * @param tree  ScriptTree
     */
    @Override
    public void visitScript(ScriptTree tree) {

        if (hasMoreThanOneClass()) {
            context().newIssue(this, tree, "PHP File can only have one class");
        }
        super.visitScript(tree);
    }

    /**
     * Do file have only one class?
     *
     * @return  Returns true if we have only one class within a file.
     */
    private boolean hasMoreThanOneClass() {
        return ClassCounter.getClassCountInFile(context().getPhpFile(), context().tree()) > 1;
    }
}

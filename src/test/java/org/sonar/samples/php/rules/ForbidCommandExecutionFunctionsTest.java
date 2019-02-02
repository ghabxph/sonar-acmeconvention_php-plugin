package org.sonar.samples.php.rules;

import com.acmecorp.convention.php.rules.ForbidCommandExecution;
import org.junit.Test;
import org.sonar.plugins.php.api.tests.PHPCheckTest;
import org.sonar.plugins.php.api.tests.PhpTestFile;

import java.io.File;


/**
 * (Unit Test) Tests Forbid Command Execution Functions
 *
 * @author ghabxph (ghabxph.official@gmail.com)
 */
public class ForbidCommandExecutionFunctionsTest {

    @Test
    public void test() {

        // Class to test
        ForbidCommandExecution classToTest = new ForbidCommandExecution();

        // Sample PHP File
        PhpTestFile fileSample = new PhpTestFile(new File("src/test/resources/checks/sample.php"));

        // Generic Test
        PHPCheckTest.check(classToTest, fileSample);
    }
}

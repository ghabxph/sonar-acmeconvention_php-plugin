package com.acmecorp.convention.php.rules;

import org.junit.Test;
import org.sonar.plugins.php.api.tests.PHPCheckTest;
import org.sonar.plugins.php.api.tests.PhpTestFile;

import java.io.File;

/**
 * (Unit Test) Tests Forbid Goto
 *
 * @author ghabxph (ghabxph.official@gmail.com)
 */
public class ForbidGotoTest {

    @Test
    public void test() throws Exception {

        // Class to test
        ForbidGoto classToTest = new ForbidGoto();

        // Sample PHP File
        PhpTestFile fileSample = new PhpTestFile(new File("src/test/resources/checks/sample.php"));

        // Generic Test
        PHPCheckTest.check(classToTest, fileSample);

    }
}

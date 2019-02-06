package com.acmecorp.convention.php.rules;

import org.junit.Test;
import org.sonar.plugins.php.api.tests.PHPCheckTest;
import org.sonar.plugins.php.api.tests.PhpTestFile;

import java.io.File;

/**
 * (Test) Tests rule that checks whether method has less than 100 lines of code
 *
 * @author ghabxph (ghabxph.official@gmail.com)
 */
public class MethodLess100LinesTest {

    @Test
    public void test() {

        // Class to test
        MethodLess100Lines classToTest = new MethodLess100Lines();

        // Sample PHP File
        PhpTestFile fileSample = new PhpTestFile(new File("src/test/resources/checks/MethodLess100Lines.php"));

        // Generic Test
        PHPCheckTest.check(classToTest, fileSample);
    }
}

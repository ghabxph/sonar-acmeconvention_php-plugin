package com.acmecorp.convention.php.helper;

import org.junit.Test;
import org.sonar.plugins.php.api.tests.PHPCheckTest;
import org.sonar.plugins.php.api.tests.PhpTestFile;
import org.sonar.plugins.php.api.visitors.PHPCheck;

import java.io.File;

/**
 * (Class) Class that performs generic test.
 *   - Designed to be a parent class
 *
 * @author ghabxph (ghabxph.official@gmail.com)
 */
public abstract class GenericTest {

    /**
     * Returns the file sample file
     *
     * @param file  File name
     * @return  Returns the file sample file
     */
    protected String fileSampleFile(String file) {
        return "src/test/resources/checks/" + file;
    }

    /**
     * Class to be tested
     *
     * @return PHPCheck  Returns the class to be tested
     */
    protected abstract PHPCheck classToTest();

    /**
     * Path to file sample
     *
     * @return String  Returns the string path
     */
    protected abstract String fileSample();

    /**
     * Performs the test
     */
    @Test
    public void test() {

        // Sample PHP File
        PhpTestFile fileSample = new PhpTestFile(new File(fileSample()));

        // Generic Test
        PHPCheckTest.check(classToTest(), fileSample);
    }
}

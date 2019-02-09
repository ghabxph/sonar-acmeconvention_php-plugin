package com.acmecorp.convention.php.rules;

import com.acmecorp.convention.php.helper.GenericTest;
import org.sonar.plugins.php.api.visitors.PHPCheck;

import static org.junit.Assert.*;

public class AvoidTagShortTest extends GenericTest {

    /**
     * Class to be tested
     *
     * @return PHPCheck  Returns the class to be tested
     */
    @Override
    protected PHPCheck classToTest() {
        return new AvoidTagShort();
    }

    /**
     * Path to file sample
     *
     * @return String  Returns the string path
     */
    @Override
    protected String fileSample() {
        return fileSampleFile("AvoidTagShort.php");
    }
}

package com.acmecorp.convention.php.rules;

import com.acmecorp.convention.php.helper.GenericTest;
import org.sonar.plugins.php.api.visitors.PHPCheck;

public class AvoidDoubleQuoteTest extends GenericTest {

    /**
     * Class to be tested
     *
     * @return PHPCheck  Returns the class to be tested
     */
    @Override
    protected PHPCheck classToTest() {
        return new AvoidDoubleQuote();
    }

    /**
     * Path to file sample
     *
     * @return String  Returns the string path
     */
    @Override
    protected String fileSample() {
        return "src/test/resources/checks/AvoidDoubleQuote.php";
    }
}
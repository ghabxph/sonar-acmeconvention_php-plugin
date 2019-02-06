package com.acmecorp.convention.php.rules;

import com.acmecorp.convention.php.helper.GenericTest;
import org.sonar.plugins.php.api.visitors.PHPCheck;

/**
 * (Test) Tests rule that checks whether method has less than 100 lines of code
 *
 * @author ghabxph (ghabxph.official@gmail.com)
 */
public class MethodLess100LinesTest extends GenericTest {

    /**
     * Class to be tested
     *
     * @return PHPCheck  Returns the class to be tested
     */
    @Override
    protected PHPCheck classToTest() {
        return new MethodLess100Lines();
    }

    /**
     * Path to file sample
     *
     * @return String  Returns the string path
     */
    @Override
    protected String fileSample() {
        return "src/test/resources/checks/MethodLess100Lines.php";
    }
}

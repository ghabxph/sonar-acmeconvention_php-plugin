package com.acmecorp.convention.php.rules;

import com.acmecorp.convention.php.helper.GenericTest;
import org.sonar.plugins.php.api.visitors.PHPCheck;

/**
 * (Unit Test) Tests Forbid Command Execution Functions
 *
 * @author ghabxph (ghabxph.official@gmail.com)
 */
public class ForbidCommandExecutionTest extends GenericTest {

    /**
     * Class to be tested
     *
     * @return PHPCheck  Returns the class to be tested
     */
    @Override
    protected PHPCheck classToTest() {
        return new ForbidCommandExecution();
    }

    /**
     * Path to file sample
     *
     * @return String  Returns the string path
     */
    @Override
    protected String fileSample() {
        return "src/test/resources/checks/ForbidCommandExecution.php";
    }
}

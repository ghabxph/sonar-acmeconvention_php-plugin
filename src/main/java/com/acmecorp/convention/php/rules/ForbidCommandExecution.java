package com.acmecorp.convention.php.rules;

import com.acmecorp.convention.php.rules.common.ForbidFunctionRule;
import com.google.common.collect.ImmutableSet;
import org.sonar.check.Priority;
import org.sonar.check.Rule;

/**
 * (Class) Entry point of Acme Php Rules Plugin
 *
 * @author ghabxph (me@ghabxph.info)
 */
@Rule(
    priority = Priority.MAJOR,
    key = "ForbidCommandExecution",
    name = "Forbid Goto Statement",
    tags = {"convention"}
)
public class ForbidCommandExecution extends ForbidFunctionRule {

    /**
     * List of command execution functions that are forbidden to be used by convention
     *
     * @return ImmutableSet
     */
    @Override
    protected ImmutableSet forbiddenFunctions() {
        return ImmutableSet.of("exec", "passthru", "system", "shell_exec", "popen", "proc_open", "pcntl_exec");
    }

    /**
     * Error message to be returned if blocked function is called
     *
     * @return String
     */
    @Override
    protected String errorMessage() {
        return "${KEYWORD} is a forbidden command execution function. Please consider not relying on this function.";
    }
}

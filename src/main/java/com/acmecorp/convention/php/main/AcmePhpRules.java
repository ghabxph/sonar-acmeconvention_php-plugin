/*
 * SonarQube PHP Custom Rules Example
 * Copyright (C) 2016-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.acmecorp.convention.php.main;


import com.acmecorp.convention.php.rules.*;
import com.google.common.collect.ImmutableList;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionAnnotationLoader;
import org.sonar.plugins.php.api.visitors.PHPCustomRuleRepository;

/**
 * Extension point to define a PHP rule repository.
 *
 * @author ghabxph (me@ghabxph.info)
 */
public class AcmePhpRules implements RulesDefinition, PHPCustomRuleRepository {

    /**
     * Name of constant
     */
    private static final String TITLE = "ACME Inc. Coding Convention Violation";

    /**
     * Provide the repository key
     */
    @Override
    public String repositoryKey() {
        return "custom";
    }

    /**
     * Provide the list of checks class that implements rules
     * to be part of the rule repository
     */
    @Override
    public ImmutableList<Class> checkClasses() {
        return ImmutableList.of(

            // Forbid command execution functions such as:
            // exec, passthru, system, shell_exec, popen, proc_open, pcntl_exec
            ForbidCommandExecution.class,

            // Forbids goto statement
            ForbidGoto.class,

            // Forbid PHP Code execution functions such as:
            // eval, preg_replace (with e flag), create_function
            ForbidPhpCodeExecution.class,

            // Checks whether class is less than 100 lines
            MethodLess100Lines.class,

            // Checks for CRLF
            NoCrlf.class
      );
    }

    @Override
    public void define(Context context) {
        NewRepository repository = context.createRepository(repositoryKey(), "php").setName(TITLE);

        // Load rule meta data from annotations
        RulesDefinitionAnnotationLoader annotationLoader = new RulesDefinitionAnnotationLoader();
        checkClasses().forEach(ruleClass -> annotationLoader.load(repository, ruleClass));

        // Optionally override html description from annotation with content from html files
        repository.rules().forEach(rule -> rule.setHtmlDescription(loadResource("/com/acmecorp/convention/php/rules/" + rule.key() + ".html")));

        // Optionally define remediation costs
        defineRemediationCosts(repository);

        repository.done();
    }

    private String loadResource(String path) {
        URL resource = getClass().getResource(path);
        if (resource == null) {
            throw new IllegalStateException("Resource not found: " + path);
        }
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        try (InputStream in = resource.openStream()) {
            byte[] buffer = new byte[1024];
            for (int len = in.read(buffer); len != -1; len = in.read(buffer)) {
                result.write(buffer, 0, len);
            }
            return new String(result.toByteArray(), StandardCharsets.UTF_8).replace("{{title}}", TITLE);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read resource: " + path, e);
        }
    }

    /**
     * Optionally define remediation costs
     *
     * @param repository  NewRepository
     */
    private void defineRemediationCosts(NewRepository repository)
    {
//        Map<String, String> remediationCosts = new HashMap<>();
//        remediationCosts.put("ForbidCommandExecution", "5min");
//        remediationCosts.put("ForbidGoto", "5min");
//        remediationCosts.put("ForbidPhpCodeExecution", "5min");
//        repository.rules().forEach(rule -> rule.setDebtRemediationFunction(
//                rule.debtRemediationFunctions().constantPerIssue(remediationCosts.get(rule.key()))));
    }
}

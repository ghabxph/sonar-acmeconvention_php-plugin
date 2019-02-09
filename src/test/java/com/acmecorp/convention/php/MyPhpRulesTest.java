package com.acmecorp.convention.php;

import com.acmecorp.convention.php.main.AcmePhpRules;
import org.junit.Test;
import org.sonar.api.server.rule.RulesDefinition;

import static org.junit.Assert.assertEquals;

public class MyPhpRulesTest {

    @Test
    public void rules() {
        AcmePhpRules rulesDefinition = new AcmePhpRules();
        RulesDefinition.Context context = new RulesDefinition.Context();
        rulesDefinition.define(context);
        RulesDefinition.Repository repository = context.repository("custom");
        assertEquals(9, repository.rules().size());
    }
}

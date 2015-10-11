package com.zuppelli;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"classpath:cukes/feature/com/zuppelli/Inicial.feature"}, plugin = {"pretty", "html:target/cucumber"}, glue = "com.zuppelli.steps", strict = true)
public class RunITests
{
    @BeforeClass
    public static void startup() {
        System.out.println("tests");
    }
}

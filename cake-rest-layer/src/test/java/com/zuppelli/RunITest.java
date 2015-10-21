package com.zuppelli;

import com.zuppelli.helper.CucumberContext;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "classpath:cukes/feature/com/zuppelli/Compra.feature",
                "classpath:cukes/feature/com/zuppelli/VerificaPrecio.feature"
        },
        plugin = {"pretty", "html:target/cucumber"},
        glue = "com.zuppelli.pasos", strict = true)
public class RunITest
{
    @BeforeClass
    public static void startup() {
        System.out.println("tests");
    }
    @After
    public void tearDown() {
        CucumberContext.getInstance().reset();
    }
}

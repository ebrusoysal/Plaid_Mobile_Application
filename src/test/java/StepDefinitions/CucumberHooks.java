package StepDefinitions;

import Base.Base;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.IOException;

public class CucumberHooks extends Base {
    @Before
    public void setUp() throws IOException {
        launchApp();
        startRecording();
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        stopRecording(scenario.getName() + "_" + scenario.getLine());
        getScreenShot(scenario);
    }
}

package fooddelivery.common;

import fooddelivery.CustomerApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { CustomerApplication.class })
public class CucumberSpingConfiguration {}

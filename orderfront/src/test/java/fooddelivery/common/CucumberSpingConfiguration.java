package fooddelivery.common;

import fooddelivery.OrderfrontApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { OrderfrontApplication.class })
public class CucumberSpingConfiguration {}

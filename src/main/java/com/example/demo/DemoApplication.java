package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info =@Info(
		   title = "Accounts Microservice REST API documentation",
		   description = "Demo app",
		   version = "v1",
		   contact = @Contact(
		     name = "Karunakaran",
		     email = "karuna@gmail.com"
		   ),
		   license = @License(
			 name = "Apache 2.0",
			 url = "https://www.google.com"
		   )

		)
)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

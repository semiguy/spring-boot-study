package com.cbcho.boot.ch7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyBootAppCh7Application {

	public static void main(String[] args) {
		//SpringApplication.run(MyBootAppCh7Application.class, args);
		SpringApplication.run(MyBootAppCh7Application.class, new String[] {"100"});
	}

}

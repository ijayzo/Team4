package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*
* 2/10
*.class is using the main class as a root class... this is apllication context
*passing in basic class that is going to startup the application context
*application context = eagerly instantiates beans... makes beans ... opens up new ability to support new modules for bean
*bean factory(?) = making beans
*spring bean = java class/object that is being managed by spring
*lazy initialization vs eager initialization.
*impacted at runtime. initializes on demand.
*IOC give control to other object
*spring boot gives control to app.context to create and inject beans where rerquesteded
*spring boot = injection container. creates application context. what does it want
*	is spring trying to inject and create for me
*
* Spring Bena Life Cycle : Constructor and Setter Methods
 */

@SpringBootApplication
public class EmailSenderApplication {

	public static void main(String[] args){
		SpringApplication.run(EmailSenderApplication.class, args);
	}


}

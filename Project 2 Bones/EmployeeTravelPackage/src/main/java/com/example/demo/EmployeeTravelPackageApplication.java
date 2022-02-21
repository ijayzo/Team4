package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


//Simulate some business 30%, Documentation 5% - (javadocs), User Interaction 10%, Unit Testing (40% coverage) 40%
//Presentation 5%, Data Persistence 5%, Logging 5%

//Goal: Build 2 separate web APIs using spring boot and deploy and orchestrate the applications using Docker and Docker Compose.

//API 1
//Request reimbursement API
	//As a Employee I can:
//Submit a reimbursement request
//View all of my reimbursements
	// As a Manager I can:
//View all reimbursements
//Approve/Deny/Reassign reimbursements

//API 2
//Email API
//Receive request to send emails to specified users.

//Containerize and deploy the parts necessary to run the entire system

//JUnit
//Spring Test
//Docker

/**
 * Using jSon
 * @RestController = spring bean that communicates in a data format (convention, jSon or xml, we use jSon).
 *     Create methods that handle certain requests and returns data (maybe) as jSon
 *  Will set up as restful as possible (Restful representational state transfer)
 *  @RequestMapping = tells Spring web application context how to map controllers to certain types of requests
 *        like project 0 with paths
 *  After @RequestMapping we make @GetMapping
 *  Shown in first way, different kinds of @GetMapping 's
 *  When using multiple GetMapping isntances, change name @GetMapping("{name}")
 *    Looking at second @GetMapping("{id}") in first way, we hard coded everything except id, change parameters...
 *  Create DTO for @PostMapping called CreatePostDTO... for this DTO, no real action, make constructors and getters and setters
 *  @PostMapping in way 1 mocks workflow, created it, saved in db, and return new Response entity (.created)
 * 		 here we added the @Value("${server.port}") and the int port;
 *  @RequestBody inside @PostMapping in Way 1 = send in that CreatePostDto
 *    got to insomnia. new folder "name", new request post with json body.
 *    "posterName": "poster.name", enter "receiverName": "receiver.name", "content": "Tag you're it"
 * 	   go to headers, should have created a url with some-id at end
 *  @PutMapping or @PatchMapping does UPDATE
 * @PostMapping ADD NEW
 * @DeleteMapping
 * @Column for column mapping, can comment out @Column b/c hibernate will know theres a column there if private String column_name; is there.
 * 		@Transient on column will tell Hibernate to ignore the column
 *Optional used for null checks. (DataJPA, not hibernate)is
 */


@SpringBootApplication
public class EmployeeTravelPackageApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeTravelPackageApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.build();
	}
}









package com.onlineAcademy.onlineAcademyClient.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.onlineAcademy.service.RESTservice.model.Category;

public class InvocationClass {

	public static void main(String[] args) {
		
		InvocationClass invocationClass = new InvocationClass();
		Invocation invocation = invocationClass.createRequestForCourseByCategory(Category.ARTS);
		Response response = invocation.invoke();
		System.out.println("Status code: " + response.getStatus());

	}
	
	public Invocation createRequestForCourseByCategory(Category category) {
        
		Client client = ClientBuilder.newClient();
		
		return client.target("http://localhost:8080/RESTservice/webapi/")
		                         .path("courses")
		                         .queryParam("category", category)
		                         .request(MediaType.APPLICATION_JSON)
		                         .buildGet();
	}

}

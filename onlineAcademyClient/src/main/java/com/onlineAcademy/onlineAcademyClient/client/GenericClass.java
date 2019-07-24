package com.onlineAcademy.onlineAcademyClient.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.onlineAcademy.service.RESTservice.model.Category;
import com.onlineAcademy.service.RESTservice.model.Course;

public class GenericClass {

	public static void main(String[] args) {
		
        Client client = ClientBuilder.newClient();
		
		List<Course> courses = client.target("http://localhost:8080/RESTservice/webapi/")
		                         .path("courses")
		                         .queryParam("category", Category.ARTS)
		                         .request(MediaType.APPLICATION_JSON)
		                         .get(new GenericType<List<Course>>() {} );
		
		System.out.println("First course from categoty ARTS -> " + courses.get(0).getName());

	}

}

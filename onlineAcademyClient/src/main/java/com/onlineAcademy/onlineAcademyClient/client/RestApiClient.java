package com.onlineAcademy.onlineAcademyClient.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.onlineAcademy.service.RESTservice.model.Course;

public class RestApiClient {

	public static void main(String[] args) {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget baseTarget = client.target("http://localhost:8080/RESTservice/webapi/");
		WebTarget allCoursesTarget = baseTarget.path("courses");
		WebTarget courseTarget = allCoursesTarget.path("{courseId}");
		
		Course course = courseTarget.resolveTemplate("courseId", 1)
				.request(MediaType.APPLICATION_JSON)
				.get(Course.class);
		
		System.out.println("Course name: " + course.getName());
		
	}
	
}

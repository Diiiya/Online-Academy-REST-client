package com.onlineAcademy.onlineAcademyClient.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.onlineAcademy.service.RESTservice.model.Category;
import com.onlineAcademy.service.RESTservice.model.Course;
import com.onlineAcademy.service.RESTservice.model.Level;

public class RestApiClient {

	public static void main(String[] args) {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget baseTarget = client.target("http://localhost:8080/RESTservice/webapi/");
		WebTarget allCoursesTarget = baseTarget.path("courses");
		WebTarget courseTarget = allCoursesTarget.path("{courseId}");
		
		// GET
		Course course = courseTarget.resolveTemplate("courseId", 1)
									.request(MediaType.APPLICATION_JSON)
									.get(Course.class);
		
		System.out.println("Course name: " + course.getName() + " for course with id: " + course.getId());
		
		// POST
		Course newCourse = new Course(10L, "Adobe XD", Level.BEGINNER, Category.IT, null);
		Response postResponse = allCoursesTarget.request()
			                                 	.post(Entity.json(newCourse));
		if(postResponse.getStatus() != 201) {
			System.out.println("ERROR!");
		}
		Course createdCourse = postResponse.readEntity(Course.class);
		
		System.out.println("Newly created course name: " + createdCourse.getName());
		
		// PUT
		Course courseToUpdate = new Course(10L, "Adobe XD", Level.BEGINNER, Category.IT, null);
		Response putResponce = courseTarget.resolveTemplate("courseId", 1)
											.request(MediaType.APPLICATION_JSON)
											.post(Entity.json(courseToUpdate));
		Course updatedCourse = putResponce.readEntity(Course.class);
		
		System.out.println("Newly updated course name: " + updatedCourse.getName() + " for course with id: " + course.getId());
	}
	
}

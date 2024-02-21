package com.java_elastic.controllers;

import java.util.List;
import java.util.logging.Logger;

import com.java_elastic.elastic.ElasticSearchService;
import com.java_elastic.elastic.bean.Person;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/elastic")
@RequestScoped
public class ElasticSearchController {
	final static Logger LOGGER = Logger.getLogger(ElasticSearchController.class.getName());

	@Inject
	private ElasticSearchService elasticService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDefaultMessage() {
		elasticService.getDocument();
		return Response.status(Response.Status.OK).entity("Success").build();
	}
	
	@GET
	@Path("/search/{name}")
	public Response searchByName(@PathParam("name") String name) {
		LOGGER.info("Received search name ::"+name);
		List<Person> person = elasticService.searchPerson(name);
		if(person==null) {
			LOGGER.info("No person found with name ::"+name);
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.status(Response.Status.OK).entity(person).build();
	}
	
	
	@POST
	@Path("/{name}/{id}")
	public Response createPerson(@PathParam("name") String name,@PathParam("id") int id) {
		LOGGER.info("Received create person with name ::"+name);
		Person person = elasticService.createPerson(name,id);
		return Response.status(Response.Status.CREATED).entity(person).build();
	}

	
	
}

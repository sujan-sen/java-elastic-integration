package com.java_elastic.elastic;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.java_elastic.elastic.bean.Person;

import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ElasticSearchService {
	
	final static Logger LOGGER = Logger.getLogger(ElasticSearchService.class.getName());
	
	@Inject
	private ElasticClientFactory elasticClientFactory;
	
	
	public void getDocument() {
		Person person = new Person("Mark Doe", 20, new Date(1471466076564L));
		try {
			IndexResponse response = elasticClientFactory.elasticsearchClient().index(i -> i
			  .index("person")
			  .id(person.getFullName())
			  .document(person));
			LOGGER.info("Index Version created in Elastic "+response.version());
		} catch (ElasticsearchException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Person> searchPerson(String searchText) {
		SearchResponse<Person> searchResponse;
		try {
			searchResponse = elasticClientFactory.elasticsearchClient().search(
					s -> s.index("person").query(q -> q.match(t -> t.field("fullName").query(searchText))),
					Person.class);
			List<Hit<Person>> hits = searchResponse.hits().hits();
			if (searchResponse!=null && searchResponse.hits()!=null 
					&& searchResponse.hits().hits()!=null 
					&& searchResponse.hits().hits().size() != 0) {
				return hits.stream().map(aHit -> {
					return aHit.source();
				}).collect(Collectors.toList());
			} else {
				return null;
			}
		} catch (ElasticsearchException | IOException e) {
			LOGGER.severe("Error while search with text " + searchText);
			LOGGER.severe(e.getMessage());
		} catch (Exception e) {
			LOGGER.severe("General error " + searchText);
			LOGGER.severe(e.getMessage());
		}
		return null;

	}
	
	public Person createPerson(String name,int id) {
		Person person = new Person(name, id, new Date(1471466076564L));
		try {
			IndexResponse response = elasticClientFactory.elasticsearchClient().index(i -> i
			  .index("person")
			  .id(person.getFullName())
			  .document(person));
			LOGGER.info("Index Version created in Elastic "+response.version());
		} catch (ElasticsearchException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return person;
	}

}

package com.java_elastic.elastic;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ElasticClientFactory {

	private static ElasticsearchClient elasticsearchClient;

	public ElasticsearchClient elasticsearchClient() {
		if (elasticsearchClient == null) {
			RestClient restClient = RestClient.builder(HttpHost.create("http://localhost:9200")).build();
			ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
			elasticsearchClient = new ElasticsearchClient(transport);
		}
		return elasticsearchClient;
	}
}

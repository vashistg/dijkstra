package com.dijkstra.config;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableNeo4jRepositories("com.dijkstra.repository")
public class MyNeo4jConfiguration extends Neo4jConfiguration {

	final String grapheneUrl;

	public MyNeo4jConfiguration() {
		grapheneUrl = "http://neo4j:abcd1234@localhost:7474";
	}


	@Bean
	public org.neo4j.ogm.config.Configuration getConfiguration() {
		org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
		config.driverConfiguration().setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver").setURI(grapheneUrl);
		return config;
	}

	@Override
	@Bean
	public SessionFactory getSessionFactory() {
		return new SessionFactory(getConfiguration(), "com.dijkstra.neo4j.nodes");
	}

	@Override
	@Bean
	@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public Session getSession() throws Exception {
		return super.getSession();
	}
	
	@Bean(name = { "neo4jTransaction" })
	@Autowired
	public Neo4jTransactionManager transactionManager(Session s) {
		Neo4jTransactionManager txManager = new Neo4jTransactionManager(s);
		return txManager;
	}
}

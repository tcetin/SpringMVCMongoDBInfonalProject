package com.springinsert.spapp.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.springinsert.spapp.model.*;


@Repository
public class PersonService {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public static final String COLLECTION_NAME = "person";
	
	/**
	 * addPerson method make insert operation with posted person model from PersonController Class. 
	 * @param person
	 */
	public void addPerson(Person person) {
		if (!mongoTemplate.collectionExists(Person.class)) {
			mongoTemplate.createCollection(Person.class);
		}		
		person.setId(UUID.randomUUID().toString());
		mongoTemplate.insert(person, COLLECTION_NAME);
	}
	
	/**
	 * listPerson lists documents which inserted to "person" collection
	 * @return
	 */
	public List<Person> listPerson() {
		return mongoTemplate.findAll(Person.class, COLLECTION_NAME); 
	} 
	
	/**
	 * getPerson method returns a document to PersonController  using with posted id  from PersonController Class.
	 * @param id
	 * @return
	 */
	public Person getPerson(String id) {
		return mongoTemplate.findOne( Query.query(Criteria.where("_id").is(id)),
							        Person.class,
							        COLLECTION_NAME) ;
	} 

	/**
	 * deletePerson method deletes a document from collection  using with posted id  from PersonController Class.
	 * @param person
	 */
	public void deletePerson(Person person) {
		mongoTemplate.remove(person, COLLECTION_NAME);
	}
	
	/**
	 * updatePerson method updates a document from collection  using with posted person model  from PersonController Class.
	 * @param person
	 */
	public void updatePerson(Person person) {
		//mongoTemplate.insert(person, COLLECTION_NAME);
		mongoTemplate.save(person, COLLECTION_NAME);
	}
}

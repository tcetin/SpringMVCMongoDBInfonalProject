package com.springinsert.spapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "person")
public class Person {
	
	@Id
	private String 	id;
	private String 	name;
	private String	surname;
	private String 	phoneNumber;
	
	/**
	 * This method is default constructer method
	 */
	public Person(){}
	
	/**
	 * This method is constructer method with given parameters.
	 * 
	 * @param id
	 * @param name
	 * @param surname
	 * @param phoneNumber
	 * 
	 */
	public Person(String id, String name, String surname, String phoneNumber) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
	}

	/**
	 * This method is id  getter.
	 * @return
	 */
	
	public String getId() {
		return this.id;
	}
	
	/**
	 * This Method is id setter.
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * This Method is name getter.
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This Method is name setter.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * This method is phone number getter
	 * @return
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * This method is phone number setter.
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * This method is last name getter.
	 * @return
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * This method is last name setter.
	 * @param surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
}
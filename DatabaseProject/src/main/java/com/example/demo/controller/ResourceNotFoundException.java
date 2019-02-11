package com.example.demo.controller;

public class ResourceNotFoundException extends RuntimeException{
	
	//private int resourceId;
	
	public ResourceNotFoundException(String msg)
	{
		super(msg);
	}
	

}

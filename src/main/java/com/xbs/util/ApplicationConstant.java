package com.xbs.util;

public class ApplicationConstant {

	private ApplicationConstant() {}

	// organization constants
	public static final String ORGANIZATION_ID_NULL = "Organization Id should not be null";
	public static final String ORGANIZATION_NAME_BLANK = "Organization name should not be left blank";
	public static final String ORGANIZATION_ADDRESS_BLANK = "Address should not be left blank";
	public static final String ORGANIZATION_CREATE_SUCCESS = "Organization created successfully";
	public static final String ORGANIZATION_CREATE_FAIL = "Failed to create an Organization";
	public static final String ORGANIZATION_ALREADY_EXISTS = "Organization already exists";
	public static final String ORGANIZATION_NOT_FOUND = "Organization not found";
	public static final String INVALID_ORGANIZATION_ID = "Invalid Organization Id";

	// employee constants
	public static final String EMPLOYEE_NAME_BLANK = "Employee Name should not be left blank";
	public static final String EMPLOYEE_AGE_NULL = "Age should not be null";
	public static final String EMPLOYEE_PROFESSION_BLANK = "Profession should not be left blank";
	public static final String EMPLOYEE_NOT_FOUND = "Employee not found";
	public static final String EMPLOYEE_NOT_FOUND_FOR_ORGANIZATION = "Employee not found for the Organization";
	public static final String EMPLOYEE_CREATE_SUCCESS = "Employee created successfully";
	public static final String EMPLOYEE_CREATE_FAIL = "Failed to create an Employee";
	public static final String EMPLOYEE_UPDATE_SUCCESS = "Employee updated successfully";
	public static final String EMPLOYEE_UPDATE_FAIL = "Failed to update an Employee";
	public static final String EMPLOYEE_FETCH_SUCCESS = "Employee fetched successfully";
	public static final String EMPLOYEE_FETCH_FAIL = "Failed to fetch Employee details";
	public static final String EMPLOYEE_DETAILS_ALREADY_EXISTS = "Employee already exists with same details";
	public static final String EMPLOYEE_DELETE_SUCCESS = "Employee deleted successfully";
	public static final String EMPLOYEE_DELETE_FAIL = "Failed to delete an Employee";
	public static final String INVALID_AGE = "Age should be between 20 and 70";
	

}

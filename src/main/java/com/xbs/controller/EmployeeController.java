package com.xbs.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xbs.dto.request.EmployeeRequestDto;
import com.xbs.dto.response.EmployeeResponseDto;
import com.xbs.dto.response.ApplicationResponse;
import com.xbs.entity.Employee;
import com.xbs.service.EmployeeServiceImpl;
import com.xbs.util.ApplicationConstant;

@RestController
@RequestMapping("/v1/api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl employeeService;

	/**
	 * Create an Employee
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping
	public ApplicationResponse<?> createEmployee(@Valid @RequestBody EmployeeRequestDto request) {
		EmployeeResponseDto response = employeeService.createEmployee(request);
		return new ApplicationResponse<>(response, HttpStatus.OK.value(), ApplicationConstant.EMPLOYEE_CREATE_SUCCESS);
	}

	/**
	 * Get Employee by Organization Id and Employee Id
	 * 
	 * @param organizationId
	 * @param id
	 * @return
	 */
	@GetMapping(value = { "/{organizationId}", "/{organizationId}/{id}" })
	public ApplicationResponse<?> getEmployeeByOrganizationIdAndEmployeeId(@PathVariable int organizationId,
			@PathVariable(required = false) Integer id) {
		List<EmployeeResponseDto> response = employeeService.getEmployeeByOrganizationIdAndEmpId(organizationId, id);
		return new ApplicationResponse<>(response, HttpStatus.OK.value(), ApplicationConstant.EMPLOYEE_FETCH_SUCCESS);
	}

	/**
	 * Get Employees List by Employee Ids List
	 * 
	 * @param ids
	 * @return
	 */
	@GetMapping
	public ApplicationResponse<?> getEmployeesByEmployeeIds(@RequestParam("ids") List<Integer> ids) {
		List<EmployeeResponseDto> response = employeeService.getEmployeesByEmployeeIds(ids);
		return new ApplicationResponse<>(response, HttpStatus.OK.value(), ApplicationConstant.EMPLOYEE_FETCH_SUCCESS);
	}

	/**
	 * Update an Employee
	 * 
	 * @param request
	 * @return
	 */
	@PutMapping
	public ApplicationResponse<?> updateEmployee(@Valid @RequestBody EmployeeRequestDto request) {
		EmployeeResponseDto response = employeeService.updateEmployee(request);
		return new ApplicationResponse<>(response, HttpStatus.OK.value(), ApplicationConstant.EMPLOYEE_UPDATE_SUCCESS);
	}

	/**
	 * Mark Employee as deleted within an Organization by Organization Id and Employee Id
	 * 
	 * @param organizationId
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{organizationId}/{id}")
	public ApplicationResponse<?> deleteEmployeeByOrganizationIdAndId(@PathVariable int organizationId,
			@PathVariable int id) {
		employeeService.deleteEmployeeByOrganizationIdAndId(organizationId, id);
		return new ApplicationResponse<>(HttpStatus.OK.value(), ApplicationConstant.EMPLOYEE_DELETE_SUCCESS);
	}

	/**
	 * Mark Employees as deleted within an Organization by Employee Ids list
	 * 
	 * @param ids
	 * @return
	 */
	@DeleteMapping
	public ApplicationResponse<?> deleteEmployeeByIds(@RequestParam("ids") List<Integer> ids) {
		employeeService.deleteEmployeeByIds(ids);
		return new ApplicationResponse<>(HttpStatus.OK.value(), ApplicationConstant.EMPLOYEE_DELETE_SUCCESS);
	}

}

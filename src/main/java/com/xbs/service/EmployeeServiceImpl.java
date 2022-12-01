package com.xbs.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.xbs.dto.request.EmployeeRequestDto;
import com.xbs.dto.response.EmployeeResponseDto;
import com.xbs.entity.Employee;
import com.xbs.entity.Organization;
import com.xbs.exception.ApplicationException;
import com.xbs.repository.EmployeeRepository;
import com.xbs.repository.OrganizationRepository;
import com.xbs.util.ApplicationConstant;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private OrganizationRepository organizationRepository;

	/**
	 * Create an Employee
	 * 
	 * @param request
	 * @return
	 */
	@Transactional
	public EmployeeResponseDto createEmployee(EmployeeRequestDto request) {
		String employeeName = request.getEmployeeName();
		Integer age = request.getAge();
		String profession = request.getProfession();
		Integer organizationId = request.getOrganizationId();
		if (ObjectUtils.isEmpty(organizationId))
			throw new ApplicationException(HttpStatus.BAD_REQUEST, ApplicationConstant.INVALID_ORGANIZATION_ID);
		if (!organizationRepository.existsByIdAndDeleted(organizationId, false))
			throw new ApplicationException(HttpStatus.NOT_FOUND, ApplicationConstant.ORGANIZATION_NOT_FOUND);
		if (ObjectUtils.isEmpty(age))
			throw new ApplicationException(HttpStatus.BAD_REQUEST, ApplicationConstant.INVALID_AGE);
		try {
			Employee employee = new Employee();
			employee.setEmployeeName(employeeName);
			employee.setAge(age);
			employee.setProfession(profession);
			Optional<Organization> organization = organizationRepository.findById(organizationId);
			if (organization.isPresent())
				employee.setOrganization(organization.get());
			employeeRepository.save(employee);
			return new EmployeeResponseDto(employee);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new ApplicationException(ApplicationConstant.EMPLOYEE_CREATE_FAIL);
		}
	}

	/**
	 * Get Employee by Organization Id and Employee Id
	 * 
	 * @param organizationId
	 * @param id
	 * @return
	 */
	public List<EmployeeResponseDto> getEmployeeByOrganizationIdAndEmpId(int organizationId, Integer id) {
		if (!ObjectUtils.isEmpty(id)) {
			if(!organizationRepository.existsByIdAndDeleted(organizationId, false))
				throw new ApplicationException(HttpStatus.NOT_FOUND, ApplicationConstant.ORGANIZATION_NOT_FOUND);
			if (!employeeRepository.existsByIdAndDeleted(id, false))
				throw new ApplicationException(HttpStatus.NOT_FOUND, ApplicationConstant.EMPLOYEE_NOT_FOUND);
			if (!employeeRepository.existsByIdAndOrganizationId(id, organizationId))
				throw new ApplicationException(HttpStatus.NOT_FOUND, ApplicationConstant.EMPLOYEE_NOT_FOUND_FOR_ORGANIZATION);
		} else {
			if(!organizationRepository.existsByIdAndDeleted(organizationId, false))
				throw new ApplicationException(HttpStatus.NOT_FOUND, ApplicationConstant.ORGANIZATION_NOT_FOUND);
			if (!employeeRepository.existsByOrganizationId(organizationId))
				throw new ApplicationException(HttpStatus.NOT_FOUND, ApplicationConstant.ORGANIZATION_NOT_FOUND);
		}
		try {
			if (ObjectUtils.isEmpty(id))
				return employeeRepository.getReferenceByOrganizationId(organizationId);
			return employeeRepository.getReferenceByOrganizationIdAndId(organizationId, id);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new ApplicationException(ApplicationConstant.EMPLOYEE_FETCH_FAIL);
		}
	}

	/**
	 * Get Employees List by Employee Ids List
	 * 
	 * @param ids
	 * @return
	 */
	public List<EmployeeResponseDto> getEmployeesByEmployeeIds(List<Integer> ids) {
		if (!employeeRepository.existsByIdInAndDeleted(ids, false))
			throw new ApplicationException(HttpStatus.NOT_FOUND, ApplicationConstant.EMPLOYEE_NOT_FOUND);
		try {
			return !CollectionUtils.isEmpty(ids) ? employeeRepository.getReferenceByIdIn(ids) : null;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new ApplicationException(ApplicationConstant.EMPLOYEE_FETCH_FAIL);
		}
	}

	/**
	 * Update an Employee
	 * 
	 * @param request
	 * @return
	 */
	@Transactional
	public EmployeeResponseDto updateEmployee(EmployeeRequestDto request) {
		Integer id = request.getId();
		String employeeName = request.getEmployeeName();
		Integer organizationId = request.getOrganizationId();
		Integer age = request.getAge();
		String profession = request.getProfession();
		if (!employeeRepository.existsByIdAndDeleted(id, false))
			throw new ApplicationException(HttpStatus.NOT_FOUND, ApplicationConstant.EMPLOYEE_NOT_FOUND);
		if (!organizationRepository.existsByIdAndDeleted(organizationId, false))
			throw new ApplicationException(HttpStatus.NOT_FOUND, ApplicationConstant.ORGANIZATION_NOT_FOUND);
		if (ObjectUtils.isEmpty(age))
			throw new ApplicationException(HttpStatus.BAD_REQUEST, ApplicationConstant.INVALID_AGE);
		if (employeeRepository.existsByIdAndEmployeeNameAndAgeAndProfessionAndOrganizationId(id, employeeName, age, profession, organizationId))
			throw new ApplicationException(HttpStatus.CONFLICT, ApplicationConstant.EMPLOYEE_DETAILS_ALREADY_EXISTS);
		try {
			Employee employee = employeeRepository.getReferenceById(id);
			if (!employee.getEmployeeName().equals(employeeName))
				employee.setEmployeeName(employeeName);
			if (employee.getAge() != age)
				employee.setAge(age);
			if (!employee.getProfession().equals(profession))
				employee.setProfession(profession);
			Optional<Organization> organization = organizationRepository.findById(organizationId);
			if (organization.isPresent())
				employee.setOrganization(organization.get());
			employeeRepository.save(employee);
			return new EmployeeResponseDto(employee);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new ApplicationException(ApplicationConstant.EMPLOYEE_UPDATE_FAIL);
		}
	}

	/**
	 * Mark Employee as deleted within an Organization by Organization Id and Employee Id
	 * 
	 * @param organizationId
	 * @param id
	 * @return
	 */
	public void deleteEmployeeByOrganizationIdAndId(int organizationId, int id) {
		if (!employeeRepository.existsByIdAndDeleted(id, false))
			throw new ApplicationException(HttpStatus.NOT_FOUND, ApplicationConstant.EMPLOYEE_NOT_FOUND);
		if (!organizationRepository.existsById(organizationId))
			throw new ApplicationException(HttpStatus.NOT_FOUND, ApplicationConstant.ORGANIZATION_NOT_FOUND);
		if (!employeeRepository.existsByIdAndOrganizationId(id, organizationId))
			throw new ApplicationException(HttpStatus.NOT_FOUND, ApplicationConstant.EMPLOYEE_NOT_FOUND_FOR_ORGANIZATION);
		try {
			Employee employee = employeeRepository.getByOrganizationIdAndId(organizationId, id);
			employee.setDeleted(true);
			employeeRepository.save(employee);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new ApplicationException(ApplicationConstant.EMPLOYEE_DELETE_FAIL);
		}
	}

	/**
	 * Mark Employees as deleted within an Organization by Employee Ids list
	 * 
	 * @param ids
	 * @return
	 */
	@Transactional
	public void deleteEmployeeByIds(List<Integer> ids) {
		if (!employeeRepository.existsByIdInAndDeleted(ids, false))
			throw new ApplicationException(HttpStatus.NOT_FOUND, ApplicationConstant.EMPLOYEE_NOT_FOUND);
		try {
			List<Employee> employees = employeeRepository.getByIdIn(ids);
			employees.forEach(emp -> {
				emp.setDeleted(true);
				employeeRepository.save(emp);
			});
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new ApplicationException(ApplicationConstant.EMPLOYEE_DELETE_FAIL);
		}
	}
}

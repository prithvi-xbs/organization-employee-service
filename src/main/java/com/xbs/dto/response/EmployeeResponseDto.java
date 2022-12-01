package com.xbs.dto.response;

import java.util.Optional;

import com.xbs.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDto {

	private Integer id;

	private String employeeName;

	private Integer age;

	private String profession;

	private Optional<Integer> organizationId;

	private Optional<String> organizationName;

	public Optional<Integer> getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Optional<Integer> organizationId) {
		this.organizationId = organizationId;
	}

	public Optional<String> getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(Optional<String> organizationName) {
		this.organizationName = organizationName;
	}

	public EmployeeResponseDto(Employee employee) {
		this.id = employee.getId();
		this.employeeName = employee.getEmployeeName();
		this.age = employee.getAge();
		this.profession = employee.getProfession();
		this.organizationId = Optional.of(employee.getOrganization().getId());
		this.organizationName = Optional.of(employee.getOrganization().getOrganizationName());
	}

}

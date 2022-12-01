package com.xbs.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.xbs.util.ApplicationConstant;

import lombok.Data;

@Data
public class EmployeeRequestDto {

	// only to be used for update
	private Integer id;

	@NotBlank(message = ApplicationConstant.EMPLOYEE_NAME_BLANK)
	private String employeeName;

	@NotNull(message = ApplicationConstant.EMPLOYEE_AGE_NULL)
	@Range(min = 20, max = 70, message = ApplicationConstant.INVALID_AGE)
	private Integer age;

	@NotBlank(message = ApplicationConstant.EMPLOYEE_PROFESSION_BLANK)
	private String profession;

	@NotNull(message = ApplicationConstant.ORGANIZATION_ID_NULL)
	private Integer organizationId;

}

package com.xbs.dto.request;

import javax.validation.constraints.NotBlank;

import com.xbs.util.ApplicationConstant;

import lombok.Data;

@Data
public class OrganizationRequestDto {

	// to be used only for Update
	private Integer id;

	@NotBlank(message = ApplicationConstant.ORGANIZATION_NAME_BLANK)
	private String organizationName;

	@NotBlank(message = ApplicationConstant.ORGANIZATION_ADDRESS_BLANK)
	private String address;

}

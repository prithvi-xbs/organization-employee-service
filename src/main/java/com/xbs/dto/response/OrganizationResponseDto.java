package com.xbs.dto.response;

import com.xbs.entity.Organization;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrganizationResponseDto {

	private Integer id;

	private String organizationName;

	private String address;

	public OrganizationResponseDto(Organization organization) {
		this.id = organization.getId();
		this.organizationName = organization.getOrganizationName();
		this.address = organization.getAddress();
	}
}

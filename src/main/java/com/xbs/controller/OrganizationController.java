package com.xbs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xbs.dto.request.OrganizationRequestDto;
import com.xbs.dto.response.ApplicationResponse;
import com.xbs.entity.Organization;
import com.xbs.service.OrganizationServiceImpl;
import com.xbs.util.ApplicationConstant;

@RestController
@RequestMapping("/v1/api/organization")
public class OrganizationController {

	@Autowired
	private OrganizationServiceImpl organizationService;

	/**
	 * Create an Organization
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping
	public ApplicationResponse<?> createOrganization(@Valid @RequestBody OrganizationRequestDto request) {
		Organization response = organizationService.createOrganization(request);
		return new ApplicationResponse<>(response, HttpStatus.OK.value(),
				ApplicationConstant.ORGANIZATION_CREATE_SUCCESS);
	}

}

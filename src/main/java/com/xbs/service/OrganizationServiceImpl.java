package com.xbs.service;

import javax.transaction.Transactional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.xbs.dto.request.OrganizationRequestDto;
import com.xbs.entity.Organization;
import com.xbs.exception.ApplicationException;
import com.xbs.repository.OrganizationRepository;
import com.xbs.util.ApplicationConstant;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrganizationServiceImpl {

	@Autowired
	private OrganizationRepository organizationRepository;

	/**
	 * Create an Organization
	 * 
	 * @param request
	 * @return
	 */
	@Transactional
	public Organization createOrganization(OrganizationRequestDto request) {
		String organizationName = request.getOrganizationName();
		String address = request.getAddress();
		if (organizationRepository.existsByOrganizationNameAndDeleted(organizationName, false))
			throw new ApplicationException(HttpStatus.CONFLICT, ApplicationConstant.ORGANIZATION_ALREADY_EXISTS);
		try {
			Organization organization = new Organization();
			organization.setOrganizationName(organizationName);
			organization.setAddress(address);
			organizationRepository.save(organization);
			return organization;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new ApplicationException(ApplicationConstant.ORGANIZATION_CREATE_FAIL);
		}
	}

}

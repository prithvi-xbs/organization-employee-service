package com.xbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xbs.entity.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {

	boolean existsByOrganizationNameAndDeleted(String organizationName, boolean deleted);

	boolean existsByIdAndDeleted(int organizationId, boolean deleted);

}

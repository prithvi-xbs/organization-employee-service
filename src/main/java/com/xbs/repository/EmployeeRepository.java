package com.xbs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xbs.dto.response.EmployeeResponseDto;
import com.xbs.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	boolean existsByIdAndOrganizationId(int id, int organizationId);

	List<EmployeeResponseDto> getReferenceByOrganizationIdAndId(int organizationId, int id);

	Employee getByOrganizationIdAndId(int organizationId, int id);

	boolean existsByOrganizationId(int organizationId);

	List<EmployeeResponseDto> getReferenceByOrganizationId(int organizationId);

	boolean existsByIdInAndDeleted(List<Integer> ids, boolean deleted);

	List<EmployeeResponseDto> getReferenceByIdIn(List<Integer> ids);

	boolean existsByIdAndEmployeeNameAndAgeAndProfessionAndOrganizationId(int id, String employeeName, int age,
			String profession, int organizationId);

	boolean existsByIdAndDeleted(Integer id, boolean deleted);

	List<Employee> getByIdIn(List<Integer> ids);

}

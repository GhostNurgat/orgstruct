package ru.brusnika.orgstruct.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import ru.brusnika.orgstruct.dto.CompanyResponse;
import ru.brusnika.orgstruct.dto.DepartmentResponse;
import ru.brusnika.orgstruct.dto.GroupResponse;
import ru.brusnika.orgstruct.dto.LocationResponse;
import ru.brusnika.orgstruct.dto.PostResponse;
import ru.brusnika.orgstruct.dto.SubdivisionResponse;
import ru.brusnika.orgstruct.dto.TypeWorkResponse;
import ru.brusnika.orgstruct.dto.WorkerResponse;

import ru.brusnika.orgstruct.repository.*;
import ru.brusnika.orgstruct.model.*;

@Service
@RequiredArgsConstructor
public class OrgStructServiceImpl implements OrgStructService {
    private final CompanyRepository companyRepository;
    private final LocationRepository locationRepository;
    private final SubdivisionRepository subdivisionRepository;
    private final DepartmentRepository departmentRepository;
    private final GroupRepository groupRepository;
    private final WorkerRepository workerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CompanyResponse> getCompanies() {
        return companyRepository.findAll()
            .stream()
            .map(this::buildCompanyResponse)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyResponse getCompany(String entityLegal) {
        return companyRepository.findById(entityLegal)
            .map(this::buildCompanyResponse)
            .orElseThrow(() -> new EntityNotFoundException("Company " + entityLegal + " is not found"));
    }

    private CompanyResponse buildCompanyResponse(Company company) {
        return new CompanyResponse()
            .setEntityLegal(company.getEntityLegal())
            .setCompanyName(company.getCompanyName());
    }

    @Override
    @Transactional(readOnly = true)
    public List<LocationResponse> getLocations() {
        return locationRepository.findAll()
            .stream()
            .map(this::buildLocationResponse)
            .collect(Collectors.toList());
    }

    private LocationResponse buildLocationResponse(Location location) {
        return new LocationResponse()
            .setId((long) location.getId())
            .setName(location.getName())
            .setCompanyResponse(new CompanyResponse()
                .setEntityLegal(location.getCompany().getEntityLegal())
                .setCompanyName(location.getCompany().getCompanyName()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubdivisionResponse> getSubdivisions() {
        return subdivisionRepository.findAll()
            .stream()
            .map(this::buildSubdivisionResponse)
            .toList();
    }

    private SubdivisionResponse buildSubdivisionResponse(Subdivision sub) {
        return new SubdivisionResponse()
            .setSubId((long) sub.getSubId())
            .setSubName(sub.getSubName())
            .setLocationResponse(new LocationResponse()
                .setId((long) sub.getLocation().getId())
                .setName(sub.getLocation().getName()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentResponse> getDepartments() {
        return departmentRepository.findAll()
            .stream()
            .map(this::builDepartmentResponse)
            .toList();
    }

    private DepartmentResponse builDepartmentResponse(Department department) {
        return new DepartmentResponse()
            .setDepartmentId((long) department.getDepartmentId())
            .setDepartmentName(department.getDepartmentName())
            .setSubdivisionResponse(new SubdivisionResponse()
                .setSubId((long) department.getSubdivision().getSubId())
                .setSubName(department.getSubdivision().getSubName()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupResponse> getGroups() {
        return groupRepository.findAll()
            .stream()
            .map(this::buildGroupResponse)
            .toList();
    }

    private GroupResponse buildGroupResponse(Group group) {
        return new GroupResponse()
            .setGroupId((long) group.getGroupId())
            .setGroupName(group.getGroupName())
            .setDepartmentResponse(new DepartmentResponse()
                .setDepartmentId((long) group.getDepartment().getDepartmentId())
                .setDepartmentName(group.getDepartment().getDepartmentName()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkerResponse> getWorkers() {
        return workerRepository.findAll()
            .stream()
            .map(this::buildWorkerResponse)
            .toList();
    }
    
    private WorkerResponse buildWorkerResponse(Worker worker) {
        return new WorkerResponse()
            .setId(worker.getId())
            .setGroupResponse(new GroupResponse()
                .setGroupId((long) worker.getGroup().getGroupId())
                .setGroupName(worker.getGroup().getGroupName()))
            .setFIO(worker.getFIO())
            .setPostResponse(new PostResponse()
                .setPostId((long) worker.getPost().getPostId())
                .setPostName(worker.getPost().getPostName()))
            .setTypeWorkResponse(new TypeWorkResponse()
                .setTypeId((long) worker.getType().getTypeId())
                .setTypeName(worker.getType().getTypeName()));
    }
}

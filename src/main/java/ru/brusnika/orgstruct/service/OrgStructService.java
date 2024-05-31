package ru.brusnika.orgstruct.service;

import java.util.List;

import ru.brusnika.orgstruct.dto.*;

public interface OrgStructService {
    List<CompanyResponse> getCompanies();

    CompanyResponse getCompany(String entityLegal);

    List<LocationResponse> getLocations();

    List<SubdivisionResponse> getSubdivisions();

    List<DepartmentResponse> getDepartments();

    List<GroupResponse> getGroups();

    List<WorkerResponse> getWorkers();
}

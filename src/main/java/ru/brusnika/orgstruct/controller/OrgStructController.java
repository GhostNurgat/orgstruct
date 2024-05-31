package ru.brusnika.orgstruct.controller;

import java.util.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.brusnika.orgstruct.service.OrgStructService;
import ru.brusnika.orgstruct.dto.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/orgStructures")
@RequiredArgsConstructor
public class OrgStructController {
    private final OrgStructService orgStructService;

    @GetMapping(value = "/getCompanies", produces = "application/json")
    public List<CompanyResponse> getCompanies() {
        return orgStructService.getCompanies();
    }

    @GetMapping(value = "/getCompany/{entityLegal}", produces = "application/json")
    public CompanyResponse getCompany(@RequestParam String entityLegal) {
        return orgStructService.getCompany(entityLegal);
    }

    @GetMapping(value = "/getLocations", produces = "application/json")
    public List<LocationResponse> getLocations() {
        return orgStructService.getLocations();
    }

    @GetMapping(value = "/getSubdivisions", produces = "application/json")
    public List<SubdivisionResponse> getSubdivisions() {
        return orgStructService.getSubdivisions();
    }

    @GetMapping(value = "/getDepartments", produces = "application/json")
    public List<DepartmentResponse> getDepartments() {
        return orgStructService.getDepartments();
    }

    @GetMapping(value = "/getGroups", produces = "application/json")
    public List<GroupResponse> getGroups() {
        return orgStructService.getGroups();
    }

    @GetMapping(value = "/getWorkers", produces = "application/json")
    public List<WorkerResponse> getWorkers() {
        return orgStructService.getWorkers();
    }
}

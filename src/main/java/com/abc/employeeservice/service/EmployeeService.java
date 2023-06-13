package com.abc.employeeservice.service;

import com.abc.employeeservice.Execptionss.NoResourseFoundException;
import com.abc.employeeservice.model.Employee;
import com.abc.employeeservice.model.EmployeeResponse;
import com.abc.employeeservice.model.EmployeeStandarsResponse;
import com.abc.employeeservice.repository.EmployeeRepository;
import com.abc.employeeservice.utills.ApplicationConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeResponse saveEmployee(Employee empObj){
        var ret=  employeeRepository.save(empObj);
        var stResponse= EmployeeStandarsResponse.builder().status("succes").reason("succes").build();
        employeeRepository.save(empObj);
        return new EmployeeResponse(ret,stResponse);
    }
    public EmployeeResponse fetchEmployee(int employeeID)throws NoResourseFoundException {

        var response=employeeRepository.findById(employeeID);
        var stResponse= EmployeeStandarsResponse.builder().status("Failed").build();
        var stuGetResponse=EmployeeResponse.builder().employeeStandarsResponse(stResponse).build();
        if(response.isPresent()){
            stuGetResponse.setStudent(response.get());
            stuGetResponse.getEmployeeStandarsResponse().setStatus("succes");
            return stuGetResponse;
        }
        throw new NoResourseFoundException("No Employee found in data base for given id");
    }

    public EmployeeResponse studentUpadateById(Employee stu, int employeeId) throws NoResourseFoundException {
        var response=employeeRepository.findById(employeeId);
        var stResponse= EmployeeStandarsResponse.builder().status(ApplicationConstants.Success).build();
        var stuGetResponse=EmployeeResponse.builder().employeeStandarsResponse(stResponse).build();

        if(response.isPresent()){
            response.get().setEmployeeId(stu.getEmployeeId());
            if(StringUtils.isNotBlank(stu.getFirstname())) {
                response.get().setFirstname(stu.getFirstname());
            }
            if(StringUtils.isNotBlank(stu.getLastname())) {
                response.get().setLastname(stu.getLastname());
            }
            if(StringUtils.isNotBlank(stu.getReportingmanager())) {
                response.get().setReportingmanager(stu.getReportingmanager());
            }
            if(stu.getSalary()!=0) {
                response.get().setSalary(stu.getSalary());
            }
            if(stu.getExperience()!=0) {
                response.get().setExperience(stu.getExperience());
            }
            var toSaveUpdateThing=employeeRepository.save(response.get());
            stuGetResponse.setStudent(toSaveUpdateThing);
            stuGetResponse.getEmployeeStandarsResponse().setStatus("succes");
            return stuGetResponse;
        }
        throw new RuntimeException("no employee found in this id");

    }

    public EmployeeResponse studentDeletById(int employeeId) {
        var stResponse= EmployeeStandarsResponse.builder().status("Failed").build();
        var stuGetResponse=EmployeeResponse.builder().employeeStandarsResponse(stResponse).build();
        if(employeeRepository.findById(employeeId).isPresent()) {
            employeeRepository.deleteById(employeeId);
            stuGetResponse.getEmployeeStandarsResponse().setStatus(ApplicationConstants.Success);
            return stuGetResponse;
        }

        stuGetResponse.getEmployeeStandarsResponse().setReason("Failed becuse employeeId= "+employeeId+" is not present");
        return stuGetResponse;
    }


}

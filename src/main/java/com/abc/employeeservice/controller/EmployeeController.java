package com.abc.employeeservice.controller;
import com.abc.employeeservice.Execptionss.NoResourseFoundException;
import com.abc.employeeservice.model.Employee;
import com.abc.employeeservice.model.EmployeeResponse;
import com.abc.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    EmployeeService employeeResponse;
    @PostMapping(value = "/employee", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeResponse> saveEmployeeToDb(@RequestBody Employee empObj){
        return new ResponseEntity<>(employeeResponse.saveEmployee(empObj), HttpStatus.ACCEPTED);

    }

    @GetMapping(value = "/Student/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeResponse>fetchById(@PathVariable int employeeId) throws NoResourseFoundException {
        return new ResponseEntity<>(employeeResponse.fetchEmployee(employeeId),HttpStatus.OK);
    }

}

package com.abc.employeeservice.model;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class EmployeeResponse {
        private Employee student;
        private EmployeeStandarsResponse employeeStandarsResponse;


}





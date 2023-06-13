package com.abc.employeeservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@AllArgsConstructor
@Builder
@Data
public class EmployeeStandarsResponse {

        public String status;
        private String reason;


}

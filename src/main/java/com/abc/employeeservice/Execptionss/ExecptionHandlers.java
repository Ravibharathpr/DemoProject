package com.abc.employeeservice.Execptionss;

import com.abc.employeeservice.model.EmployeeResponse;
import com.abc.employeeservice.model.EmployeeStandarsResponse;
import lombok.extern.slf4j.Slf4j;
import org.h2.message.DbException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;

@RestControllerAdvice
@Slf4j
public class ExecptionHandlers {
    @ExceptionHandler(NoResourseFoundException.class)
    public ResponseEntity<EmployeeResponse>handelNoresourseFoundException(Exception ex, WebRequest request){
        log.error("execption in service {} {}",request.getDescription(true));
        var stResponse= EmployeeStandarsResponse.builder().status("Errored").reason(ex.getMessage()).build();
        var stuGetResponse=EmployeeResponse.builder().employeeStandarsResponse(stResponse).build();
        return new ResponseEntity<>(stuGetResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @ExceptionHandler({DbException.class, DataIntegrityViolationException.class,SQLException.class})
    public ResponseEntity<EmployeeResponse>handlerSqlException(Exception ex,WebRequest request){
        log.error("execption in service {} {}",request.getParameterMap());
        var stResponse= EmployeeStandarsResponse.builder().status("Errored").reason("request processing failed contact admin").build();
        var stuGetResponse=EmployeeResponse.builder().employeeStandarsResponse(stResponse).build();
        return new ResponseEntity<>(stuGetResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<EmployeeResponse>defultHandler(Exception ex,WebRequest request){
        log.error("execption in service {} {}",request.getParameterMap());
        var stResponse= EmployeeStandarsResponse.builder().status("Errored").reason("request processing failed contact admin").build();
        var stuGetResponse=EmployeeResponse.builder().employeeStandarsResponse(stResponse).build();
        return new ResponseEntity<>(stuGetResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}

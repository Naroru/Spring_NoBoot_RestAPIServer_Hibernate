package springMVC_RestAPI.service;


import springMVC_RestAPI.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    void save(Employee emp);

    Employee getByID(int id);

    void delete(int id);
}

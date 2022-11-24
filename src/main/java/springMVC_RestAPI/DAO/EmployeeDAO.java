package springMVC_RestAPI.DAO;

import springMVC_RestAPI.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    public List<Employee> getAllEmployees();

    void save(Employee emp);

    Employee getByID(int id);

    void delete(int id);
}

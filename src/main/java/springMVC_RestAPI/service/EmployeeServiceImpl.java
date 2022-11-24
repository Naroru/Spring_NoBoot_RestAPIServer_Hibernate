package springMVC_RestAPI.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springMVC_RestAPI.DAO.EmployeeDAO;
import springMVC_RestAPI.entity.Employee;

import javax.transaction.Transactional;
import java.util.List;

//аннотация Service говорит, что это @Component и используется для сервисов
@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeDAO employeeDAO;

    // аннотация @Transactional автоматически открывает и закрывает транзакцию
    @Transactional
    @Override
    public List<Employee> getAllEmployees() {

        return  employeeDAO.getAllEmployees();
    }

    @Override
    @Transactional
    public void save(Employee emp) {
        employeeDAO.save(emp);
    }

    @Override
    @Transactional
    public Employee getByID(int id) {
        return  employeeDAO.getByID(id);
    }

    @Override
    @Transactional
    public void delete(int id) {
        employeeDAO.delete(id);
    }
}

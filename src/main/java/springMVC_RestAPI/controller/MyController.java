package springMVC_RestAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springMVC_RestAPI.entity.Employee;
import springMVC_RestAPI.exceptionHandling.InformationAboutException;
import springMVC_RestAPI.exceptionHandling.NoSuchEmployeeException;
import springMVC_RestAPI.service.EmployeeService;

import java.util.List;

@RestController // Это вместо аннотации @Controller. Используем @RestController потому что будем делать приложение RestAPI
@RequestMapping("/api") //это по словам Заура - бест практис, чтобы во всех запросах (URL) был префикс api
public class MyController {

    @Autowired
    EmployeeService employeeService;

    // GetMapping используем вместо @RequestMapping(принимает все типы http методов). А @GetMapping
    //работает только с GET методами
    @GetMapping("/employees")
    public List<Employee> showAllEmployees(Model model) {

        //преобразование из List<Employee> в json будет происходить благодаря зависимости jackson
        List<Employee> employees = employeeService.getAllEmployees();
        return employees;

    }

    //PostMapping работает только с POST методами. Используется вместо @RequestMapping(принимает все типы http методов).
    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee emp)
    {
        //@RequestBody берет тело из http запроса ( в нашем случае это json). Потом он автоматом с помощью зависимости
        //jackson будет преобразовано в Employee. Сохраняем его, ему присваиватся id, и возвращаем его
        employeeService.save(emp);
        return emp;
    }

    //работает только с PUT http методами
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee emp)
    {
        employeeService.save(emp);
        return emp;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {

        Employee employee = employeeService.getByID(id);
        if (employee == null)
            //ловля исключений будет обрабатываться моим классом GlobalExceptionHandler
            throw new NoSuchEmployeeException("There is no employee with id " + id);
        else {
            employeeService.delete(id);
            return "Employee with id "+id+" was deleted";
        }
    }


    //в URL мы можем указать переменную в {}. Она называется path variable.
    // Далее мы можем получить её значение в параметре метода с помощью аннотации @PathVariable
    //т.е. path variable введена в URL и мы её получаем через анноатацию
    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {

        Employee employee = employeeService.getByID(id);
        if (employee == null)
            //ловля исключений будет обрабатываться моим классом GlobalExceptionHandler
            throw new NoSuchEmployeeException("There is no employee with id " + id);
        else
            return employee;

    }


}

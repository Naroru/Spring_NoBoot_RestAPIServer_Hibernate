package springMVC_RestAPI.DAO;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springMVC_RestAPI.entity.Employee;



import java.util.List;

//Аннотация @Repository показывает, что класс будет @Component + некоторые доп. фичи для персистанс API, для DAO
@Repository
public class EmployeesDAOImpl implements EmployeeDAO{

    //создание бина прописано в конфигурационной файле, поэтому @Autowired сработает
    @Autowired
    SessionFactory sessionFactory;


    @Override
    public List<Employee> getAllEmployees() {

        Session session = sessionFactory.getCurrentSession();

        Query<Employee> query = session.createQuery("from Employee ", Employee.class);

        List<Employee> allEmployees = query.getResultList();

        return allEmployees;

    }

    @Override
    public void save(Employee emp) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(emp);
    }

    @Override
    public Employee getByID(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Employee.class,id);
    }

    @Override
    public void delete(int id) {

        sessionFactory
                .getCurrentSession()
                .createQuery("delete from Employee where id = :Employeeid")
                .setParameter("Employeeid",id)
                .executeUpdate();

    }
}

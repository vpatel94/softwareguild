package jdbctcomplexexample.dao;

import jdbctcomplexexample.entity.Employee;
import java.util.List;

/**
 *
 * @author kylerudy
 */
public interface EmployeeDao {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);
    Employee addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployeeById(int id);
}

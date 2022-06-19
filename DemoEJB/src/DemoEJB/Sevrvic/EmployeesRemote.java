package DemoEJB.Sevrvic;

import java.util.List;

import javax.ejb.Remote;
import DemoEJB.Model.Employee;

@Remote
public interface EmployeesRemote {
	public void insertEmployee(Employee employee);
	public void deleteEmployee(int id);
	public void updateEmployee(Employee employee);
	public Employee findEmployee(int id);
	public List<Employee> findAllEmployee();
}

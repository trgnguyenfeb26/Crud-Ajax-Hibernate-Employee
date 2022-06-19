package DemoEJB.Sevrvic;

import java.util.List;

import javax.ejb.Local;

import DemoEJB.Model.Employee;
@Local
public interface EmployeesLocal {
	public void insertEmployee(Employee employee);
	public void deleteEmployee(int id) ;
	public void updateEmployee(Employee employee);
	public Employee findEmployee(int id);
	public List<Employee> findAllEmployee();
}

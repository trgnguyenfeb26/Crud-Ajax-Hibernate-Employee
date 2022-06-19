package DemoEJB.Sevrvic;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.hibernate.Session;
import org.hibernate.Transaction;

import DemoEJB.Model.Employee;
import DemoEJB.Utl.HibernateUtil;

/**
 * Session Bean implementation class Employee
 */
@Stateless
@LocalBean
public class Employees implements EmployeesRemote, EmployeesLocal {
	

    /**
     * Default constructor. 
     */
    public Employees() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void insertEmployee(Employee employee) {
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
		
	}
	@Override
	public void deleteEmployee(int id) {
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            if (employee != null) session.delete(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

	}
	@Override
	public void updateEmployee(Employee employee) {
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        
	}
	@Override
	public Employee findEmployee(int id) {
		// TODO Auto-generated method stub
		 Employee employee = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            employee = session.get(Employee.class, id);;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	     return employee;
	}
	@Override
	 @SuppressWarnings("unchecked")
	public List<Employee> findAllEmployee() {
		// TODO Auto-generated method stub
		List < Employee > listEmployee = null;
	     try (Session session = HibernateUtil.getSessionFactory().openSession()) { 
	        listEmployee = session.createQuery("from Employee").getResultList();
	     } catch (Exception e) {
	           e.printStackTrace();
	     }
	     return listEmployee;     
	}
	
	

}

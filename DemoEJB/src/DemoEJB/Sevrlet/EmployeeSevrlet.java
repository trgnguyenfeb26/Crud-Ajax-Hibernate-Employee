package DemoEJB.Sevrlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import DemoEJB.Model.Employee;
import DemoEJB.Sevrvic.EmployeesLocal;



/**
 * Servlet implementation class Employee
 */
@WebServlet("/")
public class EmployeeSevrlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	EmployeesLocal employee; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeSevrlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action = request.getServletPath();
		switch (action) {
			case "/new":
				NewEmployee(request, response);
				break;
			case "/insert":
				insertEmployee(request, response);
				break;
			case "/delete":
				deleteEmployee(request, response);
				break;
			case "/edit":
				editEmployee(request, response);
				break;
			case "/update":
				updateEmployee(request, response);
				break;
			case "/employee":
				listJsonEmployee(request, response);
				break;
			default:
				listEmployee(request, response);
				break;
			}
	}

	private void listJsonEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		List<Employee> listEmployee = employee.findAllEmployee();
		request.setAttribute("listEmployee", listEmployee);
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(listEmployee, new TypeToken<List<Employee>>(){}.getType());
		 JsonArray jsonArray = element.getAsJsonArray();
		 response.setContentType("application/json");
		 response.getWriter().println(jsonArray);
	}

	private void listEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Employee> listEmployee = employee.findAllEmployee();
		request.setAttribute("listEmployee", listEmployee);
//		Gson gson = new Gson();
//		JsonElement element = gson.toJsonTree(listEmployee, new TypeToken<List<Employee>>(){}.getType());
//		 JsonArray jsonArray = element.getAsJsonArray();
//		 response.setContentType("application/json");
//		 response.getWriter().println(jsonArray);
//		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.postForObject(
//				  "http://localhost:8080/DemoEJB/employees",
//				  listEmployee,
//				  ResponseEntity.class);
		response.getWriter().write(listEmployee.toString());
		RequestDispatcher dispatcher = request.getRequestDispatcher("ListEmployees.jsp");
		dispatcher.forward(request, response);
	}

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		Employee editEmployee = new Employee(id, name, email,phone, address);
		employee.updateEmployee(editEmployee);
		response.sendRedirect("list-employee");
	}

	private void editEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		Employee EditEmployee = employee.findEmployee(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ListEmployees.jsp");
		request.setAttribute("EditEmployee", EditEmployee);
		dispatcher.forward(request, response);
	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		employee.deleteEmployee(id);
		response.sendRedirect("list-employee");
		
	}

	private void insertEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		Employee newEmployee = new Employee(name, email,phone, address);
		employee.insertEmployee(newEmployee);
		response.sendRedirect("list-employee");
	}

	private void NewEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("ListEmployees.jsp");
		dispatcher.forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

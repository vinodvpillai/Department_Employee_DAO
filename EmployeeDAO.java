/**
 * EmployeeDAO
 * Created on March 9, 2017
 * @author Vinod Pillai <vinodthebest@gmail.com>
 * @version 1.0
 * 
 */
package com.vinod.deptemp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.vinod.deptemp.db.DatabaseOperation;
import com.vinod.deptemp.main.MainProg;
import com.vinod.deptemp.model.Department;
import com.vinod.deptemp.model.Employee;

public class EmployeeDAO {

	public static boolean insertEmployee() {
		Employee emp = new Employee();
		emp.setData();

		// Display All Department
		ArrayList<Department> deptlist = DepartmentDAO.getAllDepartment();

		if (deptlist.size() > 0) {
			for (Department dept : deptlist) {
				dept.display();
			}

			// Get Department ID
			System.out.println("Enter the Department ID:");
			Scanner scanner = new Scanner(System.in);
			int dept_id = scanner.nextInt();

			// Validate Department ID - Exists
			Department dept = DepartmentDAO.getDepartmentById(dept_id);
			if (dept != null) {
				// Database Insert Statement
				String sql = "insert into employee values(" + emp.getId() + ",'" + emp.getName() + "',"
						+ emp.getSalary() + "," + emp.getAge() + "," + dept_id + ")";

				return DatabaseOperation.updateDB(sql);
			}
		}

		return false;

	}

	public static boolean updateEmployee() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Employee ID:");
		int id = scanner.nextInt();

		Employee emp = getEmployeeById(id);

		if (emp != null) {
			emp.updateData();

			// Display All Department
			// Display All Department
			ArrayList<Department> deptlist = DepartmentDAO.getAllDepartment();

			if (deptlist.size() > 0) {
				for (Department dept : deptlist) {
					dept.display();
				}

				// Get Department ID
				System.out.println("Enter the Department ID:");
				int dept_id = scanner.nextInt();

				// Validate Department ID - Exists
				Department dept = DepartmentDAO.getDepartmentById(dept_id);
				if (dept != null) {
					// Database Insert Statement
					String sql = "update employee set name='" + emp.getName() + "',age=" + emp.getAge() + ",salary="
							+ emp.getSalary() + ",fk_dept=" + dept_id + " where id=" + id;

					return DatabaseOperation.updateDB(sql);
				}
			}
		}
		return false;
	}

	public static boolean deleteEmployee() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Employee ID:");
		int id = scanner.nextInt();

		Employee emp = getEmployeeById(id);

		if (emp != null) {

			String sql = "delete from employee where id=" + id;

			return DatabaseOperation.updateDB(sql);
		}

		return false;
	}

	public static Employee getEmployeeById(int id) {
		Employee emp = null;
		Scanner scanner = new Scanner(System.in);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(DatabaseOperation.DB_URL, DatabaseOperation.DB_USER,
					DatabaseOperation.DB_PASSWORD);
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("select * from employee where id=" + id);
			while (rs.next()) {
				emp = new Employee();
				emp.setId(Integer.parseInt(rs.getString("id")));
				emp.setAge(Integer.parseInt(rs.getString("age")));
				emp.setName(rs.getString("name"));
				emp.setSalary(Integer.parseInt(rs.getString("salary")));
				emp.setDept(DepartmentDAO.getDepartmentById(Integer.parseInt(rs.getString("fk_dept"))));
			}
			rs.close();
			stat.close();
			con.close();
			return emp;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public static ArrayList<Employee> getAllEmployee() {
		ArrayList<Employee> employeelist = new ArrayList<>();
		Employee emp = null;
		Scanner scanner = new Scanner(System.in);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(DatabaseOperation.DB_URL, DatabaseOperation.DB_USER,
					DatabaseOperation.DB_PASSWORD);
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("select * from employee");
			while (rs.next()) {
				emp = new Employee();
				emp.setId(Integer.parseInt(rs.getString("id")));
				emp.setAge(Integer.parseInt(rs.getString("age")));
				emp.setName(rs.getString("name"));
				emp.setSalary(Integer.parseInt(rs.getString("salary")));
				emp.setDept(DepartmentDAO.getDepartmentById(Integer.parseInt(rs.getString("fk_dept"))));

				employeelist.add(emp);
			}
			rs.close();
			stat.close();
			con.close();
			return employeelist;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public static ArrayList<Employee> getAllEmployeeByDeptarment(int id) {
		ArrayList<Employee> employeelist = new ArrayList<>();
		Employee emp = null;
		Scanner scanner = new Scanner(System.in);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(DatabaseOperation.DB_URL, DatabaseOperation.DB_USER,
					DatabaseOperation.DB_PASSWORD);
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("select * from employee where fk_dept=" + id);
			while (rs.next()) {
				emp = new Employee();
				emp.setId(Integer.parseInt(rs.getString("id")));
				emp.setAge(Integer.parseInt(rs.getString("age")));
				emp.setName(rs.getString("name"));
				emp.setSalary(Integer.parseInt(rs.getString("salary")));
				emp.setDept(DepartmentDAO.getDepartmentById(Integer.parseInt(rs.getString("fk_dept"))));

				employeelist.add(emp);
			}
			rs.close();
			stat.close();
			con.close();
			return employeelist;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}

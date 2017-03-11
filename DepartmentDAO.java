/**
 * DepartmentDAO
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
import com.vinod.deptemp.model.Department;
import com.vinod.deptemp.model.Employee;

public class DepartmentDAO {

	public static boolean insertDepartment() {
		Department dept = new Department();
		dept.setData();
		// Database Insert Statement
		String sql = "insert into department values(" + dept.getId() + ",'" + dept.getName() + "','"
				+ dept.getDescription() + "')";

		return DatabaseOperation.updateDB(sql);
	}

	public static boolean updateDepartment() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Department ID:");
		int id = scanner.nextInt();

		Department dept = getDepartmentById(id);

		if (dept != null) {
			dept.updateData();
			// Database Update Statement
			String sql = "update department set name='" + dept.getName() + "',description='" + dept.getDescription()
					+ "' where id=" + id;

			return DatabaseOperation.updateDB(sql);
		}

		return false;
	}

	public static boolean deleteDepartment() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Department ID:");
		int id = scanner.nextInt();

		Department dept = getDepartmentById(id);

		if (dept != null) {

			ArrayList<Employee> employeelist = EmployeeDAO.getAllEmployeeByDeptarment(id);
			if (employeelist.size() > 0) {
				return false;
			} else {
				// Database Update Statement
				String sql = "delete from department where id=" + id;

				return DatabaseOperation.updateDB(sql);
			}
		}

		return false;
	}

	public static Department getDepartmentById(int id) {
		Department dept = null;
		Scanner scanner = new Scanner(System.in);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(DatabaseOperation.DB_URL, DatabaseOperation.DB_USER,
					DatabaseOperation.DB_PASSWORD);
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("select * from department where id=" + id);
			while (rs.next()) {
				dept = new Department();
				dept.setId(Integer.parseInt(rs.getString("id")));
				dept.setName(rs.getString("name"));
				dept.setDescription(rs.getString("description"));
			}
			rs.close();
			stat.close();
			con.close();
			return dept;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public static ArrayList<Department> getAllDepartment() {

		ArrayList<Department> list = new ArrayList<>();
		Department dept = null;
		Scanner scanner = new Scanner(System.in);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(DatabaseOperation.DB_URL, DatabaseOperation.DB_USER,
					DatabaseOperation.DB_PASSWORD);
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("select * from department");
			while (rs.next()) {
				dept = new Department();
				dept.setId(Integer.parseInt(rs.getString("id")));
				dept.setName(rs.getString("name"));
				dept.setDescription(rs.getString("description"));

				list.add(dept);
				// Pending Employee also of that Department.
			}
			rs.close();
			stat.close();
			con.close();
			return list;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}

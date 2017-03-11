/**
 * MainProg
 * Created on March 9, 2017
 * @author Vinod Pillai <vinodthebest@gmail.com>
 * @version 1.0
 * 
 */
package com.vinod.deptemp.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.vinod.deptemp.dao.DepartmentDAO;
import com.vinod.deptemp.dao.EmployeeDAO;
import com.vinod.deptemp.model.Department;
import com.vinod.deptemp.model.Employee;

public class MainProg {

	public static void main(String[] args) {

		int operation;

		int employeeoperation;
		int departmentoperation;

		int empback = 0;
		int deptback = 0;
		int id = 0;

		Scanner scanner = new Scanner(System.in);

		while (true) {

			System.out.println("1. Manage Department");
			System.out.println("2. Manage Employee");

			System.out.println("0. Exit");

			System.out.println("Select the Operation:");
			operation = scanner.nextInt();

			switch (operation) {
			case 1:
				deptback = 0;
				departmentoperation = 0;
				while (true) {
					System.out.println("1. Add Department");
					System.out.println("2. Update Department");
					System.out.println("3. Remove Department - ID");
					System.out.println("4. Display all Department");
					System.out.println("0. Back to Main Menu");

					System.out.println("Select the Operation:");
					departmentoperation = scanner.nextInt();

					switch (departmentoperation) {
					case 1:
						if (!(DepartmentDAO.insertDepartment())) {
							System.out.println("Sorry Error occured please try agian later");
						}

						break;
					case 2:
						// Update
						if (!(DepartmentDAO.updateDepartment())) {
							System.out.println("Sorry Error occured please try agian later");
						}
						break;
					case 3:
						// Remove
						if (!(DepartmentDAO.deleteDepartment())) {
							System.out.println("Sorry Error occured please try agian later");
						}

						break;
					case 4:
						ArrayList<Department> deptlist = DepartmentDAO.getAllDepartment();

						for (Department dept : deptlist) {
							dept.display();
						}
						break;

					case 0:
						deptback = 1;
						break;
					default:
						break;
					}

					if (deptback == 1) {
						break;

					}
				}
				break;

			case 2:
				employeeoperation = 0;
				empback = 0;
				while (true) {
					System.out.println("1. Add Employee");
					System.out.println("2. Update Employee");
					System.out.println("3. Remove Employee - ID");
					System.out.println("4. Display all Employee");
					System.out.println("5. Display all Employee - Department ID");
					System.out.println("0. Back to Main Menu");

					System.out.println("Select the Operation:");
					employeeoperation = scanner.nextInt();

					switch (employeeoperation) {
					case 1:
						// Insert
						if (!(EmployeeDAO.insertEmployee())) {
							System.out.println("Sorry Error occured please try agian later");
						}
						break;
					case 2:
						// Update
						if (!(EmployeeDAO.updateEmployee())) {
							System.out.println("Sorry Error occured please try agian later");
						}
						break;
					case 3:
						// Delete
						if (!(EmployeeDAO.deleteEmployee())) {
							System.out.println("Sorry Error occured please try agian later");
						}
						break;
					case 4:
						// Display all Employee

						ArrayList<Employee> emplist = EmployeeDAO.getAllEmployee();

						for (Employee emp : emplist) {
							emp.display();
						}
						break;
					case 5:
						// Display all Employee - By Department ID
						System.out.println("Enter the Department ID:");
						id = scanner.nextInt();
						emplist = EmployeeDAO.getAllEmployeeByDeptarment(id);

						for (Employee emp : emplist) {
							emp.display();
						}

						break;
					case 0:
						empback = 1;
						break;
					default:
						break;
					}

					if (empback == 1) {
						break;

					}
				}
				break;

			case 0:
				System.exit(0);
			}

		}
	}
}

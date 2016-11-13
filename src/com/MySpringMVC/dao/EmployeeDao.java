package com.MySpringMVC.dao;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.MySpringMVC.entities.Department;
import com.MySpringMVC.entities.Employee;


@Repository
public class EmployeeDao {

	private static Map<Integer, Employee> employees = null;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	static{
		employees = new HashMap<Integer, Employee>();

		try {
			employees.put(1001, new Employee(1001, "E-AA", "aa@163.com", 1, new Department(101, "D-AA"),new SimpleDateFormat("yyyy-MM-dd").parse("1994-03-21")));
			employees.put(1002, new Employee(1002, "E-BB", "bb@163.com", 1, new Department(102, "D-BB"),new SimpleDateFormat("yyyy-MM-dd").parse("1994-03-21")));
			employees.put(1003, new Employee(1003, "E-CC", "cc@163.com", 0, new Department(103, "D-CC"),new SimpleDateFormat("yyyy-MM-dd").parse("1994-03-21")));
			employees.put(1004, new Employee(1004, "E-DD", "dd@163.com", 0, new Department(104, "D-DD"),new SimpleDateFormat("yyyy-MM-dd").parse("1994-03-21")));
			employees.put(1005, new Employee(1005, "E-EE", "ee@163.com", 1, new Department(105, "D-EE"),new SimpleDateFormat("yyyy-MM-dd").parse("1994-03-21")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static Integer initId = 1006;
	
	public void save(Employee employee){
		if(employee.getId() == null){
			employee.setId(initId++);
		}
		
		employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
		employees.put(employee.getId(), employee);
	}
	
	public Collection<Employee> getAll(){
		return employees.values();
	}
	
	public Employee get(Integer id){
		return employees.get(id);
	}
	
	public void delete(Integer id){
		employees.remove(id);
	}
}

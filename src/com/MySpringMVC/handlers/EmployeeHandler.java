package com.MySpringMVC.handlers;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.MySpringMVC.dao.DepartmentDao;
import com.MySpringMVC.dao.EmployeeDao;
import com.MySpringMVC.entities.Employee;

@Controller
public class EmployeeHandler {

	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private DepartmentDao departmenDao;

	@ModelAttribute
	public void getEmployee(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
		if (id != null)
			map.put("employee", employeeDao.get(id));
	}
	

	@RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id) {
		employeeDao.delete(id);
		return "redirect:/emps";
	}

	@RequestMapping(value = "/emp", method = RequestMethod.PUT)
	public String update(Employee employee) {
		employeeDao.save(employee);
		return "redirect:/emps";
	}

	@RequestMapping(value = "/emp", method = RequestMethod.POST)
	public String save(@Valid Employee employee, BindingResult result, Map<String, Object> map) {
		if (result.getErrorCount() > 0) {
			System.out.println("出错了！");
			for (FieldError error : result.getFieldErrors()) {
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			map.put("departments", departmenDao.getDepartments());
			return "input";

		}
		employeeDao.save(employee);
		return "redirect:/emps";
	}

	@RequestMapping(value = "/emp", method = RequestMethod.GET)
	public String input(Map<String, Object> map) {
		map.put("departments", departmenDao.getDepartments());
		/*
		 * 在input.jsp中使用了SpringMVC的form标签，SpringMVC认为 表单是一定要进行回显的，即便是“第一次来”，
		 * 所以要new一个 Employee对象放入request域 。
		 */
		map.put("employee", new Employee());
		return "input";
	}

	@RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
	public String input(@PathVariable("id") Integer id, Map<String, Object> map) {
		map.put("departments", departmenDao.getDepartments());
		/*
		 * 在input.jsp中使用了SpringMVC的form标签，SpringMVC认为
		 * 表单是一定要进行回显的，即便是“第一次来”，所以要获取一个 Employee对象放入request域 。
		 */
		map.put("employee", employeeDao.get(id));
		return "input";
	}

	@RequestMapping("/emps")
	public String list(Map<String, Object> map) {
		map.put("employees", employeeDao.getAll());
		return "list";
	}
}

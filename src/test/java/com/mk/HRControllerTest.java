package com.mk;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mk.controller.HRController;
import com.mk.entity.Employee;
import com.mk.entity.EmployeeGrade;
import com.mk.service.EmployeeService;


@RunWith(SpringRunner.class)
@WebMvcTest(value = HRController.class)
@SpringBootTest
public class HRControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;
	
	@Test
	public void getAllEmployesTest() throws Exception {
		
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("Mriganka Koley");
		employee.setExperience(5);
		EmployeeGrade employeeGrade = new EmployeeGrade();
		employeeGrade.setId(1);
		employeeGrade.setGrade("A");
		employeeGrade.setYear(2019);
		employee.setEmployeeGrade(List.of(employeeGrade)); 
		
		List<Employee> employees = List.of(employee);
		
		Mockito.when(employeeService.findAll()).thenReturn(employees);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/employees").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expected = "{\r\n"
				+ "    \"id\": 1,\r\n"
				+ "    \"name\": \"Mriganka Koley\",\r\n"
				+ "    \"experience\": 5,\r\n"
				+ "    \"employeeGrade\": [\r\n"
				+ "        {\r\n"
				+ "            \"id\": 1,\r\n"
				+ "            \"grade\": \"A\",\r\n"
				+ "            \"year\": 2019\r\n"
				+ "        }\r\n"
				+ "    ]\r\n"
				+ "}";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		
	}

}

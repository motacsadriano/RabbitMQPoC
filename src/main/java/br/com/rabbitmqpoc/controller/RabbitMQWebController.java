package br.com.rabbitmqpoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rabbitmqpoc.model.Employee;
import br.com.rabbitmqpoc.service.RabbitMQSender;

@RestController
@RequestMapping(value = "/v1/rabbitmqpoc/")
public class RabbitMQWebController {

	@Autowired
	RabbitMQSender rabbitMQSender;
	
	@GetMapping(value = "/producer")
	public String producer(@RequestParam("empName") String empName, @RequestParam("empId") String empId) {
	
		Employee employee = new Employee();
		employee.setEmployeeId(empId);
		employee.setEmployeeName(empName);
	
		rabbitMQSender.send(employee);

		return "Message sent to the RabbitMQ JavaInUse Successfully";
	}
}

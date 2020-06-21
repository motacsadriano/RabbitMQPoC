package br.com.rabbitmqpoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rabbitmqpoc.model.Employee;
import br.com.rabbitmqpoc.service.RabbitMQConsumer;
import br.com.rabbitmqpoc.service.RabbitMQSender;

@RestController
@RequestMapping(value = "/v1/rabbitmqpoc/")
public class RabbitMQWebController {

	@Autowired
	RabbitMQSender rabbitMQSender;
	
	@Autowired
	RabbitMQConsumer rabbitMQConsumer;
	
	@PostMapping(value = "/producer")
	public ResponseEntity<?> producer(@RequestBody Employee employee) {
		rabbitMQSender.send(employee);
		return ResponseEntity.ok().body("Message "+employee.toString()+" sent to the RabbitMQ Successfully");
	}
	
	@GetMapping(value = "/consumer")
	public ResponseEntity<?> consumer(){
		Employee employee = rabbitMQConsumer.getMessage();
		return ResponseEntity.ok().body("Recieved Message From RabbitMQ: "+employee.toString());
	}
}

package br.com.rabbitmqpoc.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.rabbitmqpoc.model.Employee;

@Service
@Component
public class RabbitMQConsumer {

	private Employee emp = new Employee();;
	
	@RabbitListener(queues = "${poc.rabbitmq.queue}")
	public void recievedMessage(Employee employee) {
		System.out.println("Recieved Message From RabbitMQ: " + employee);
		this.emp = employee;
	}
	
	public Employee getMessage() {
		return this.emp;
	}
}

package br.com.rabbitmqpoc.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.rabbitmqpoc.model.Employee;

@Service
public class RabbitMQSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Value("${poc.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${poc.rabbitmq.routingkey}")
	private String routingkey;	
	
	public void send(Employee employee) {
		rabbitTemplate.convertAndSend(exchange, routingkey, employee);
		System.out.println("Send msg = " + employee);
	}
}

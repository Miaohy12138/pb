package net.miaohy.pb.common.utils;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqUtils{

	@Autowired
	private RabbitTemplate rabbitTemplate;

	/**
	 *
	 * @param object
	 */
	public void convertAndSend(Object object){
		rabbitTemplate.convertAndSend(object);
	}

	/**
	 *
	 * @param exchange
	 * @param routingKey
	 * @param object
	 */
	public void convertAndSend(String exchange, String routingKey, final Object object){
		rabbitTemplate.convertAndSend(exchange, routingKey, object);
	}
}

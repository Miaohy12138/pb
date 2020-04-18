package net.miaohy.pb.common.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
	//exchange
	public final static String EXCHANGE = "open_cp_exchange";
	//内容推送队列
	public final static String THIRDQUEUENAME = "open_third_contentPush";
	//声明队列
	@Bean
	public Queue thirdQueue() {
		return new Queue(RabbitMqConfig.THIRDQUEUENAME);
	}
	//exchange
	@Bean
	TopicExchange exchange(){
		return new TopicExchange(EXCHANGE);
	}
	//bind exchange 和 queue  这里使用open.third.*
	@Bean
	Binding bindingExchangeMessage(){
		return BindingBuilder.bind(thirdQueue()).to(exchange()).with("open.push.*");
	}
}


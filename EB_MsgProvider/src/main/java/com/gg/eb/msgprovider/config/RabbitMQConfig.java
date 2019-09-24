package com.gg.eb.msgprovider.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    //定义队列
    public static String qname="nrsmsvc";
    //定义交换器
    public static String ename="smsvc";
    //创建队列对象
    @Bean
    public Queue creatQueue(){
        return  new Queue(qname);
    }
    public Exchange createExchange(){
        return ExchangeBuilder.fanoutExchange(ename).build();
    }
    public Binding createBind(Queue queue,Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }
}

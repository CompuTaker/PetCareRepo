package com.test.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.test.constants.Constants;

@Configuration
public class RedisConfig {
	
//	@Bean(name = "reactiveRedisConnectionFactory")
//	ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
//		JedisConnectionFactory factory = new ReactiveRedisConnectionFactory();
//		factory.setHostName(Constants.REDIS_PUBLIC_IP_ADDRESS);
//		factory.setPort(Constants.REDIS_PUBLIC_PORT);
//		factory.setUsePool(true);
//		System.out.println("@Test - jedisConnectionFactory => " + factory.toString());
//		return factory;
//	}
	
	@Bean(name = "jedisConnectionFactory")
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(Constants.REDIS_PUBLIC_IP_ADDRESS);
		factory.setPort(Constants.REDIS_PUBLIC_PORT);
		factory.setUsePool(true);
		System.out.println("@Test - jedisConnectionFactory => " + factory.toString());
		return factory;
	}
	
	@Bean(name = "redisTemplate")
	RedisTemplate<Object, Object> redisTemplate() {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setKeySerializer( new StringRedisSerializer() );
        redisTemplate.setHashValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
        redisTemplate.setValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
        System.out.println("@Test - redisTemplate => " + redisTemplate.toString());
		return redisTemplate;
	}

	@Bean(name = "stringRedisTemplate")
	StringRedisTemplate stringRedisTemplate() {
		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
		stringRedisTemplate.setConnectionFactory(jedisConnectionFactory());
		return stringRedisTemplate;
	}
	
	@Bean
	ChannelTopic defaultTopic() {
	    return new ChannelTopic("messageQueue");
	}
	
//	@Bean
//	RedisMessageListenerContainer redisContainer() {
//	    RedisMessageListenerContainer container = new RedisMessageListenerContainer(); 
//	    container.setConnectionFactory(jedisConnectionFactory()); 
//	    container.addMessageListener(messageListener(), topic()); 
//	    return container;
//	}
	
	@Bean
	MyMessageListenerContainer redisContainer() {
		MyMessageListenerContainer container = new MyMessageListenerContainer();
		container.setConnectionFactory(jedisConnectionFactory()); 
	    container.addMessageListener(myMessageListener(), defaultTopic()); 
	    return container;
	}
	
	@Bean
	MyMessageListenerAdapter myMessageListener() {
	    return new MyMessageListenerAdapter(new RedisMessageSubscriber(-1));
	}
	
	@Bean
	MyMessagePublisher redisPublisher() {
	    return new RedisMessagePublisher(stringRedisTemplate(), defaultTopic());
	}
	
}

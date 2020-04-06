package com.test.chat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;

@Configuration
public class RedisConfig {
	
	@Bean(name = "jedisConnectionFactory")
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName("52.197.77.223");
		factory.setPort(6379);
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

}

package geektime.spring.springbucks.config;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
public class RedisConfig {
	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory factory) {
	  RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
	  Set<String> cacheNames = new HashSet<>();
	  cacheNames.add("coffee_cache");
	  ConcurrentHashMap<String, RedisCacheConfiguration> configMap = new ConcurrentHashMap<>();
	  configMap.put("coffee_cache", config.entryTtl(Duration.ofSeconds(5L)));
	  RedisCacheManager cacheManager = RedisCacheManager.builder(factory).initialCacheNames(cacheNames).withInitialCacheConfigurations(configMap).build();
	  return cacheManager;
	}
}

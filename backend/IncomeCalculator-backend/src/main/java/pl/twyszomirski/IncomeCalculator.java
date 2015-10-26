package pl.twyszomirski;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

/**
 * Created by Tomasz
 * Application starting point
 */
@SpringBootApplication
@EnableCaching
public class IncomeCalculator {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(IncomeCalculator.class, args);
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("exchangeRates");
    }
}

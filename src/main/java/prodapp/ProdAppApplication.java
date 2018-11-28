package prodapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@EnableScheduling
public class ProdAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdAppApplication.class, args);
	}
	
	 @Bean
	    CommandLineRunner init(StorageService storageService) {
	        return (args) -> {
	            storageService.deleteAll();
	            storageService.init();
	        };
	    }
	
}

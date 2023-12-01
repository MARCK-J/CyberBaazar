package ucb.edu.bo.SIS213.Cyberbaazar.CyberBaazar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CyberBaazarApplication {

	public static void main(String[] args) {
		SpringApplication.run(CyberBaazarApplication.class, args);
	}
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}

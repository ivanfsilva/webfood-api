package br.com.ivanfsilva.webfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.ivanfsilva.webfood.infrastrutucture.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class WebfoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfoodApiApplication.class, args);
	}

}

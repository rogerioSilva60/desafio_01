package br.com.desafio.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.desafio.papel.entity.Papeis;
import br.com.desafio.papel.repository.PapelRepository;

@Configuration
@Profile("production")
public class ProductionConfig {

	private final PapelRepository repository;

	public ProductionConfig(PapelRepository repository) {
		this.repository = repository;
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			try {
				if(!repository.existsByNome("ADM")) {
					Papeis papel = new Papeis();
					papel.setNome("ADM");
					repository.save(papel);
				}
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		};
	}
}

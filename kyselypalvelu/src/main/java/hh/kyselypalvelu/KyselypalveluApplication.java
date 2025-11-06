package hh.kyselypalvelu;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Arrays;
import java.util.List;

import hh.kyselypalvelu.domain.*;

@SpringBootApplication
public class KyselypalveluApplication {

	public static void main(String[] args) {
		SpringApplication.run(KyselypalveluApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(KyselyRepository kyselyRepository, KysymysRepository kysymysRepository) {
		return (args) -> {
			// Testidata voidaan lisätä tähän tarvittaessa

			Kysely kysely1 = new Kysely("Asiakastyytyväisyyskysely", "Kysely asiakkaiden tyytyväisyydestä palveluihimme", null);
			Kysely kysely2 = new Kysely("Työtyytyväisyyskysely", "Kysely työntekijöiden tyytyväisyydestä työympäristöön", null);
			Kysely kysely3 = new Kysely("Tuotepalaute", "Kysely tuotteidemme laadusta ja toimivuudesta", null);
			kyselyRepository.save(kysely1);
			kyselyRepository.save(kysely2);
			kyselyRepository.save(kysely3);
		};
	}
}

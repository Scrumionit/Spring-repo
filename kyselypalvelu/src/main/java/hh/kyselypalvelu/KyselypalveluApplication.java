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

			Kysely kysely1 = new Kysely("Asiakastyytyväisyyskysely", "Kysely asiakkaiden tyytyväisyydestä palveluihimme", null, "2023-01-01", "2023-01-31");
			Kysely kysely2 = new Kysely("Työtyytyväisyyskysely", "Kysely työntekijöiden tyytyväisyydestä työympäristöön", null, "2023-02-01", "2023-02-28");
			Kysely kysely3 = new Kysely("Tuotepalaute", "Kysely tuotteidemme laadusta ja toimivuudesta", null, "2023-03-01", "2023-03-31");
			kyselyRepository.save(kysely1);
			kyselyRepository.save(kysely2);
			kyselyRepository.save(kysely3);
		};
	}
}

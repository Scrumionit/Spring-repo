package hh.kyselypalvelu;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;

import hh.kyselypalvelu.domain.*;

@SpringBootApplication
public class KyselypalveluApplication {

	public static void main(String[] args) {
		SpringApplication.run(KyselypalveluApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(KyselyRepository kyselyRepository, KysymysRepository kysymysRepository,
			KysymysTyyppiRepository kysymysTyyppiRepository) {
		return (args) -> {
			// Testidata voidaan lisätä tähän tarvittaessa
			/*
			 * TESTIDATA:
			 * Kysely kysely1 = new Kysely("Asiakastyytyväisyyskysely",
			 * "Kysely asiakkaiden tyytyväisyydestä palveluihimme", null, "2023-01-01",
			 * "2023-01-31");
			 * Kysely kysely2 = new Kysely("Työtyytyväisyyskysely",
			 * "Kysely työntekijöiden tyytyväisyydestä työympäristöön", null, "2023-02-01",
			 * "2023-02-28");
			 * Kysely kysely3 = new Kysely("Tuotepalaute",
			 * "Kysely tuotteidemme laadusta ja toimivuudesta", null, "2023-03-01",
			 * "2023-03-31");
			 * kyselyRepository.save(kysely1);
			 * kyselyRepository.save(kysely2);
			 * kyselyRepository.save(kysely3);
			 * 
			 * Kysymys kysymys1 = new Kysymys("Monivalinta",
			 * "Kuinka tyytyväinen olet palveluumme?", Arrays.asList("Erittäin tyytyväinen",
			 * "Tyytyväinen", "Neutraali", "Tyytymätön", "Erittäin tyytymätön"));
			 * kysymys1.setKysely(kysely1);
			 * kysymysRepository.save(kysymys1);
			 * 
			 * Kysymys kysymys3 = new Kysymys("Monivalinta",
			 * "Kuinka usein käytät tuotteitamme?", Arrays.asList("Päivittäin",
			 * "Viikoittain", "Kuukausittain", "Harvemmin"));
			 * kysymys3.setKysely(kysely1);
			 * kysymysRepository.save(kysymys3);
			 * 
			 * Kysymys kysymys2 = new Kysymys("Avoin",
			 * "Miten voisimme parantaa palveluamme?", null);
			 * kysymys2.setKysely(kysely2);
			 * kysymysRepository.save(kysymys2);
			 * 
			 */
			KysymysTyyppi avoin = new KysymysTyyppi("Avoin");
			KysymysTyyppi monivalinta = new KysymysTyyppi("Monivalinta");
			kysymysTyyppiRepository.saveAll(List.of(avoin, monivalinta));

			Kysely kysely1 = new Kysely("Hyvinvointikysely","Kysely opiskelijoiden henkisestä ja fyysisestä hyvinvoinnista", null, "2025-11-13", "2025-12-31");
			kyselyRepository.save(kysely1);

			Kysymys kysymys1 = new Kysymys(avoin, "Kuinka hyvin nukuit?");
			kysymys1.setKysely(kysely1);
			kysymysRepository.save(kysymys1);

			Kysymys kysymys2 = new Kysymys(avoin, "Mitä harrastat vapaa-ajallasi ja kuinka usein?");
			kysymys2.setKysely(kysely1);
			kysymysRepository.save(kysymys2);

			Kysymys kysymys3 = new Kysymys(avoin, "Onko viikottainen työmäärä opinnoissasi sopiva? Jos ei ole, kerro miksi ja anna ehdotuksia, kuinka tilannetta voisi parantaa.");
			kysymys3.setKysely(kysely1);
			kysymysRepository.save(kysymys3);

			Kysymys kysymys4 = new Kysymys(avoin, "Miten koet sosiaalisen elämäsi opiskelijana? Onko sinulla tarpeeksi aikaa ystäville ja perheelle?");
			kysymys4.setKysely(kysely1);
			kysymysRepository.save(kysymys4);

			Kysymys kysymys5 = new Kysymys(avoin, "Miten arvioisit mielialaasi viimeisen kuukauden aikana? Onko sinulla ollut stressiä, ahdistusta tai muita mielialan vaihteluita?");
			kysymys5.setKysely(kysely1);
			kysymysRepository.save(kysymys5);

			Kysymys kysymys6 = new Kysymys(avoin, "Onko sinulla riittävästi tukea opinnoissasi, kuten opettajilta, opiskelutovereilta tai muilta tukipalveluilta?");
			kysymys6.setKysely(kysely1);
			kysymysRepository.save(kysymys6);

		};
	}
}

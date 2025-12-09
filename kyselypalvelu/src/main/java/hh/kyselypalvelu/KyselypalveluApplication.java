package hh.kyselypalvelu;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
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
		
			KysymysTyyppi avoin = new KysymysTyyppi("Avoin");
			KysymysTyyppi monivalinta = new KysymysTyyppi("Monivalinta");
			kysymysTyyppiRepository.saveAll(List.of(avoin, monivalinta));

			Kysely kysely1 = new Kysely("Hyvinvointikysely","Kysely opiskelijoiden henkisestä ja fyysisestä hyvinvoinnista", null, "2025-11-13", "2025-12-31");
			kyselyRepository.save(kysely1);

			Kysymys kysymys1 = new Kysymys(avoin, "Kuinka hyvin nukuit?");
			kysymys1.setKysely(kysely1);
			kysymysRepository.save(kysymys1);

			Kysymys kysymys2 = new Kysymys(avoin, "Mitä harrastat vapaa-ajallasi?");
			kysymys2.setKysely(kysely1);
			kysymysRepository.save(kysymys2);

			Vaihtoehto vaihtoehto1 = new Vaihtoehto("En koskaan");
			Vaihtoehto vaihtoehto2 = new Vaihtoehto("1-2 kertaa");
			Vaihtoehto vaihtoehto3 = new Vaihtoehto("3-4 kertaa");
			Vaihtoehto vaihtoehto4 = new Vaihtoehto("5 tai enemmän kertaa");

			Kysymys kysymys3 = new Kysymys(monivalinta, "Kuinka usein viikossa harrastat?");

			kysymys3.setVaihtoehdot(List.of(vaihtoehto1, vaihtoehto2, vaihtoehto3, vaihtoehto4));
			vaihtoehto1.setKysymys(kysymys3);
			vaihtoehto2.setKysymys(kysymys3);
			vaihtoehto3.setKysymys(kysymys3);
			vaihtoehto4.setKysymys(kysymys3);
		
			kysymys3.setKysely(kysely1);
			kysymysRepository.save(kysymys3);

			Vaihtoehto vaihtoehto5 = new Vaihtoehto("Kyllä");
			Vaihtoehto vaihtoehto6 = new Vaihtoehto("Ei");
			Vaihtoehto vaihtoehto7 = new Vaihtoehto("En osaa sanoa");

			Kysymys kysymys4 = new Kysymys(monivalinta, "Onko viikottainen työmäärä opinnoissasi sopiva?");

			kysymys4.setVaihtoehdot(List.of(vaihtoehto5, vaihtoehto6, vaihtoehto7));
			vaihtoehto5.setKysymys(kysymys4);
			vaihtoehto6.setKysymys(kysymys4);
			vaihtoehto7.setKysymys(kysymys4);
			kysymys4.setKysely(kysely1);
			kysymysRepository.save(kysymys4);

			Kysymys kysymys5 = new Kysymys(avoin, "Jos vastasit ei ole, kerro miksi ja anna ehdotuksia, kuinka tilannetta voisi parantaa.");
			kysymys5.setKysely(kysely1);
			kysymysRepository.save(kysymys5);

			Kysymys kysymys6 = new Kysymys(avoin, "Miten koet sosiaalisen elämäsi opiskelijana?");
			kysymys6.setKysely(kysely1);
			kysymysRepository.save(kysymys6);

			Kysymys kysymys7 = new Kysymys(avoin, "Onko sinulla tarpeeksi aikaa ystäville ja perheelle?");
			kysymys7.setKysely(kysely1);
			kysymysRepository.save(kysymys7);

			Kysymys kysymys8 = new Kysymys(avoin, "Miten arvioisit mielialaasi viimeisen kuukauden aikana? Onko sinulla ollut stressiä, ahdistusta tai muita mielialan vaihteluita?");
			kysymys8.setKysely(kysely1);
			kysymysRepository.save(kysymys8);

			Vaihtoehto vaihtoehto8 = new Vaihtoehto("On");
			Vaihtoehto vaihtoehto9 = new Vaihtoehto("On tarpeeksi");
			Vaihtoehto vaihtoehto10 = new Vaihtoehto("Ei ole riittävästi");
			Vaihtoehto vaihtoehto11 = new Vaihtoehto("Ei ole lainkaan");
			Vaihtoehto vaihtoehto12 = new Vaihtoehto("En osaa sanoa");


			Kysymys kysymys9 = new Kysymys(monivalinta, "Onko sinulla riittävästi tukea opinnoissasi, kuten opettajilta, opiskelutovereilta tai muilta tukipalveluilta?");

			kysymys9.setVaihtoehdot(List.of(vaihtoehto8, vaihtoehto9, vaihtoehto10, vaihtoehto11, vaihtoehto12));
			vaihtoehto8.setKysymys(kysymys9);
			vaihtoehto9.setKysymys(kysymys9);
			vaihtoehto10.setKysymys(kysymys9);
			vaihtoehto11.setKysymys(kysymys9);
			vaihtoehto12.setKysymys(kysymys9);
			kysymys9.setKysely(kysely1);
			kysymysRepository.save(kysymys9);


			Kysymys kysymys10 = new Kysymys(avoin, "Onko sinulla muita kommentteja tai ehdotuksia hyvinvointisi parantamiseksi opiskelijana?");
			kysymys10.setKysely(kysely1);
			kysymysRepository.save(kysymys10);


		};
	}
}

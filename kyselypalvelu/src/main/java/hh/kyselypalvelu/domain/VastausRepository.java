package hh.kyselypalvelu.domain;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VastausRepository extends CrudRepository<Vastaus, Long> {
	@Query("select v from Vastaus v where v.kysymys.kysymys_id = :kysymysId")
	List<Vastaus> findByKysymysId(@Param("kysymysId") Long kysymysId);

	@Query("select v from Vastaus v where v.kysymys.kysely.kysely_id = :kyselyId")
	List<Vastaus> findByKyselyId(@Param("kyselyId") Long kyselyId);
}

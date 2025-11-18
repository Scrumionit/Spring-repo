package hh.kyselypalvelu.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KysymysTyyppiRepository extends CrudRepository<KysymysTyyppi, Long> {

}

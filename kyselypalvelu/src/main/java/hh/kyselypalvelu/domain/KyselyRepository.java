package hh.kyselypalvelu.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KyselyRepository extends CrudRepository<Kysely, Long> {

}
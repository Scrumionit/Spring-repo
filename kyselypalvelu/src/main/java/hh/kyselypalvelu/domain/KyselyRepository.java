package hh.kyselypalvelu.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KyselyRepository extends CrudRepository<Kysely, Long> {
    List<Kysely> findByKyselyId(Long kysely_id);
}

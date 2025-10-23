package hh.kyselypalvelu.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KysymysRepository extends CrudRepository<Kysymys, Long> {
    List<Kysymys> findByKysymysId(Long kysymys_id);
}

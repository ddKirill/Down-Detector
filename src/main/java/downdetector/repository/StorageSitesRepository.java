package downdetector.repository;

import downdetector.entity.HistorySites;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StorageSitesRepository extends CrudRepository<HistorySites, UUID> {

}

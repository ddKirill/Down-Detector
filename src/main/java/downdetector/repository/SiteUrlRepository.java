package downdetector.repository;

import downdetector.entity.SiteUrl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface SiteUrlRepository extends CrudRepository<SiteUrl, UUID> {



}

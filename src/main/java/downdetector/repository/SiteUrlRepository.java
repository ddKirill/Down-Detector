package downdetector.repository;

import downdetector.entity.SiteUrl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface SiteUrlRepository extends CrudRepository<SiteUrl, Integer> {



}

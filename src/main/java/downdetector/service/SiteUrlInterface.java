package downdetector.service;

import downdetector.entity.SiteUrl;
import java.util.Optional;


public interface SiteUrlInterface {

    Optional<SiteUrl> findById(Integer id);
}

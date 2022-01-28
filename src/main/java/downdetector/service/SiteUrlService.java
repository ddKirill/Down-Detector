package downdetector.service;

import downdetector.entity.SiteUrl;
import downdetector.repository.SiteUrlRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SiteUrlService implements SiteUrlInterface {

    private final SiteUrlRepository siteUrlRepository;

    public SiteUrlService(SiteUrlRepository siteUrlRepository) {
        this.siteUrlRepository = siteUrlRepository;
    }

    @Override
    public Optional<SiteUrl> findById(Integer id) {
        return siteUrlRepository.findById(id);
    }


}

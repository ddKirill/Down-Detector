package downdetector.service;

import downdetector.controller.CheckResultDTO;
import downdetector.domain.CheckResult;
import downdetector.entity.SiteUrl;
import downdetector.entity.HistorySites;
import downdetector.repository.SiteUrlRepository;
import downdetector.repository.StorageSitesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetSitesUrl {

    private DownDetector downDetector;
    private SiteUrlRepository siteUrlRepository;
    private StorageSitesRepository storageSitesRepository;

    @Autowired
    public GetSitesUrl(DownDetector downDetector, SiteUrlRepository siteUrlRepository,
                       StorageSitesRepository storageSitesRepository) {
        this.downDetector = downDetector;
        this.siteUrlRepository = siteUrlRepository;
        this.storageSitesRepository = storageSitesRepository;
    }

    public List<CheckResult> getUrlAndStatus() {

        Iterable<SiteUrl> siteUrls = siteUrlRepository.findAll();
        List<CheckResult> checkResults = new ArrayList<>();
        List<HistorySites> historySitesList = new ArrayList<>();

        for (SiteUrl siteCheckResult : siteUrls  ) {
            URI url = URI.create(siteCheckResult.getUrl());


            boolean result = downDetector.checkUrl(url);

            CheckResult checkResult = new CheckResult(siteCheckResult.getUrl(),result);
            HistorySites historySites = new HistorySites(null, url.toString(), result, LocalDateTime.now());

            checkResults.add(checkResult);
            historySitesList.add(historySites);


        }
        storageSitesRepository.saveAll(historySitesList);

        return checkResults;
    }

}

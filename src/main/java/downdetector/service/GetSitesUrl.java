package downdetector.service;

import downdetector.entity.SiteUrl;
import downdetector.repository.SiteUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetSitesUrl {

    private DownDetector downDetector;
    private SiteUrlRepository siteUrlRepository;

    @Autowired
    public GetSitesUrl(DownDetector downDetector, SiteUrlRepository siteUrlRepository) {
        this.downDetector = downDetector;
        this.siteUrlRepository = siteUrlRepository;
    }

    public List<CheckResult> getUrlAndStatus() throws URISyntaxException {

        Iterable<SiteUrl> siteUrls = siteUrlRepository.findAll();
        List<CheckResult> checkResults = new ArrayList<>();

        for (SiteUrl siteCheckResult : siteUrls  ) {
            String result = siteCheckResult.getUrl();
            URI url = new URI(result);
            boolean resultCheck = downDetector.checkUrl(url);
            String status;
            if (resultCheck == true){
                status = "OK";
            } else {
                status = "Fail";
            }

            CheckResult checkResult = new CheckResult(result,status);
            checkResults.add(checkResult);
        }

        return checkResults;
    }

}

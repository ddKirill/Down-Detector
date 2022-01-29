package downdetector.controller;

import downdetector.entity.SiteUrl;
import downdetector.entity.SiteUrlAdd;
import downdetector.repository.SiteUrlRepository;
import downdetector.service.CheckResult;
import downdetector.service.DownDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class DownDetectorController {

    private final SiteUrlRepository siteUrlRepository;
    private final DownDetector downDetector;

    @Autowired
    public DownDetectorController(SiteUrlRepository siteUrlRepository, DownDetector downDetector) {
        this.siteUrlRepository = siteUrlRepository;
        this.downDetector = downDetector;
    }


    @GetMapping("/all")
    public String getUrl(Model model) throws URISyntaxException {
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

                CheckResult checkResult =new CheckResult(result,status);
                checkResults.add(checkResult);
            }
        model.addAttribute("checkResults", checkResults);

        return "all";
    }

    @GetMapping("/add")
    public String add(){
        return "add";
    }

    @PostMapping("/add")
    public String addSite(@ModelAttribute SiteUrlAdd siteUrlAdd){
         SiteUrl siteUrl = new SiteUrl(null, siteUrlAdd.getUrl());
         siteUrlRepository.save(siteUrl);

        return "redirect:/all";
    }

}

package downdetector.controller;

import downdetector.entity.SiteUrl;
import downdetector.entity.SiteUrlAdd;
import downdetector.repository.SiteUrlRepository;
import downdetector.domain.CheckResult;
import downdetector.service.GetSitesUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.ArrayList;
import java.util.List;


@Controller
public class DownDetectorController {

    private final SiteUrlRepository siteUrlRepository;
    private final GetSitesUrl getSitesUrl;


    @Autowired
    public DownDetectorController(SiteUrlRepository siteUrlRepository, GetSitesUrl getSitesUrl) {
        this.siteUrlRepository = siteUrlRepository;
        this.getSitesUrl = getSitesUrl;
    }


    @GetMapping("/all")
    public String getUrl(Model model)  {
        List<CheckResult> checkResults = getSitesUrl.getUrlAndStatus();
        List<CheckResultDTO> checkResultDTOS = new ArrayList<>();

        for (CheckResult checkResult : checkResults) {
            String status;
            if ( checkResult.getStatus()){
                status = "OK";
            } else {
                status = "Fail";
            }

            CheckResultDTO checkResultDTO = new CheckResultDTO(checkResult.getUrl(), status);
            checkResultDTOS.add(checkResultDTO);
        }

        model.addAttribute("checkResult", checkResultDTOS);
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

    @GetMapping("/delete")
    public String deleteAllSites(){
        siteUrlRepository.deleteAll();
        return "redirect:all";
    }


}

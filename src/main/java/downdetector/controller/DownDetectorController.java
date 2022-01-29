package downdetector.controller;

import downdetector.entity.SiteUrl;
import downdetector.repository.SiteUrlRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@Controller
public class DownDetectorController {

    private final SiteUrlRepository siteUrlRepository;
    private SiteUrl siteUrl;

    public DownDetectorController(SiteUrlRepository siteUrlRepository, SiteUrl siteUrl) {
        this.siteUrlRepository = siteUrlRepository;
        this.siteUrl = siteUrl;
    }


    @GetMapping("/all")
    public String getUrl(Map<String, Object> model) {
        Iterable<SiteUrl> siteUrls = siteUrlRepository.findAll();
        model.put("siteUrls", siteUrls);
        return "all";
    }

    @GetMapping("/add")
    public String add(){
        return "add";
    }

    @PostMapping("/add")
    public String addSite(@RequestParam String url, Map<String, Object> model){
        SiteUrl siteUrl = new SiteUrl(url);
        siteUrlRepository.save(siteUrl);
        Iterable<SiteUrl> siteUrls = siteUrlRepository.findAll();
        model.put("siteUrls", siteUrls);
        return "redirect:/all";
    }

}

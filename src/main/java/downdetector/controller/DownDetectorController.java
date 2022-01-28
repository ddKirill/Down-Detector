package downdetector.controller;

import downdetector.repository.SiteUrlRepository;
import downdetector.service.DownDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class DownDetectorController {




    @GetMapping()
    public String getIndex(){
        return "all.html";
    }

    @GetMapping("/all")
    public String getUrl(Model model) {
        model.addAttribute("checkResults", result);
        return "all.html";
    }

//    @GetMapping("/add")
//    public String add(){
//        return "add.html";
//    }

//    @PostMapping("/add")
//    public String addSite(@ModelAttribute Site site){
//        result.add(new SiteStatusResult(site.getSite(),"Fail"));
//        return "redirect:/all";
//    }

}

package downdetector.controller;

import downdetector.CheckResult;
import downdetector.Site;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DownDetectorController {

    @GetMapping()
    public String getIndex(){
        return "hello.html";
    }

    private List<CheckResult> result = new ArrayList();
    {
        result.add(new CheckResult("yandex", "fail"));
        result.add(new CheckResult("google", "fail"));
    }

    @GetMapping("/all")
    public String getUrl(Model model) {
        model.addAttribute("checkResults", result);
        return "hello.html";
    }


    @GetMapping("/add")
    public String add(){
        return "add.html";
    }

    @PostMapping("/add")
    public String addSite(@ModelAttribute Site site){
        result.add(new CheckResult(site.getSite(),"Fail"));
        return "redirect:/all";
    }

}
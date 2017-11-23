package kochbuch.project.kochbuch.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by kgrif on 18.11.2017.
 */

@Controller
public class IndexController
{
    @GetMapping("/")
    public String index()
    {
        return "index";
    }
}

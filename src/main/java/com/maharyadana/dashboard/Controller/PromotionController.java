package com.maharyadana.dashboard.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PromotionController {
    
    @GetMapping("/promotion")
    public String promotion() {
        return "promotion";
    }
    
}

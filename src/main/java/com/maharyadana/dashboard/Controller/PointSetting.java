package com.maharyadana.dashboard.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PointSetting {
    
    @GetMapping("/pointsetting")
    public String pointSetting() {
        return "pointsetting";
    }
    
}

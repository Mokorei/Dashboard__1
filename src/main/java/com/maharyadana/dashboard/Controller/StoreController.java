package com.maharyadana.dashboard.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class StoreController {

    @GetMapping("/store")
    public String store() {
        return "store";
    }
    
}

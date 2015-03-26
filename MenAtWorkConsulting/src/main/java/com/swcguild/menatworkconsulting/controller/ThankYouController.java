package com.swcguild.menatworkconsulting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Michael J. Gulley
 */

@Controller
public class ThankYouController {

    //endpoint mapping for tab Contact on navbar
    @RequestMapping(value = "/thankyou")
    public String displayThankYouPage() {
        return "thankyou";
    }

}

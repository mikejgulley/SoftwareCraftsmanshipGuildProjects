//package com.swcguild.menatworkconsulting.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
///**
// *
// * @author Michael J. Gulley
// */
//@Controller
//public class LoginController {
//
//    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
//    public String displayLoginPage() {
//        return "login";
//    }
//    
//    // TODO
//    // make function to submit contact form
//}


package com.swcguild.menatworkconsulting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    // #1 - respond to all GET requests for /login
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String showLoginForm() {
        return "login";
    }
}
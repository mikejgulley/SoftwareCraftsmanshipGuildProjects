package com.swcguild.menatworkconsulting.controller;

import com.swcguild.menatworkconsulting.dao.ContactDao;
import com.swcguild.menatworkconsulting.model.Contact;
import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.validation.Valid;
import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Michael J. Gulley
 */
//CB made up a lot of code; check for blunders here
@Controller
public class ContactController {

    private ReCaptcha reCaptcha;
    private ContactDao contactDao;

    //constructor injection
    @Inject
    public ContactController(ContactDao contactDao, ReCaptcha reCaptcha) {
        this.contactDao = contactDao;
        this.reCaptcha = reCaptcha;
    }

    //endpoint mapping for tab Contact on navbar
    @RequestMapping(value = "/contact")
    public String displayContactPage() {
        return "contact";
    }

    @RequestMapping(value = "/captchaError")
    public String displayCaptchaErrorPage() {
        return "captchaError";
    }

    //sendMessage called from contact.jsp
    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
    public String createContact(@Valid @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("message") String message,
            @RequestParam("recaptcha_challenge_field") String challengeField,
            @RequestParam("recaptcha_response_field") String responseField,
            ServletRequest servletRequest) {

        String remoteAddress = servletRequest.getRemoteAddr();

        ReCaptchaResponse reCaptchaResponse = this.reCaptcha.checkAnswer(remoteAddress, challengeField, responseField);

        if (reCaptchaResponse.isValid()) {
            Contact contact = new Contact();
            contact.setFirstName(firstName);
            contact.setLastName(lastName);
            contact.setEmail(email);
            contact.setMessage(message);

            contactDao.createContact(contact);
            return "redirect:thankyou";
        } else {
            return "redirect:captchaError";
        }
    }
}

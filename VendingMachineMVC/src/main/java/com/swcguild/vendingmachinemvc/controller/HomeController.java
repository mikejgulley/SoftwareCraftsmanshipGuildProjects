package com.swcguild.vendingmachinemvc.controller;

import com.swcguild.vendingmachinemvc.businesslogic.BusinessLogic;
import static com.swcguild.vendingmachinemvc.businesslogic.BusinessLogicImpl.currentBalance;
import com.swcguild.vendingmachinemvc.dao.VendingMachineDao;
import com.swcguild.vendingmachinemvc.dto.Item;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Michael J. Gulley
 */
@Controller
public class HomeController {
    DecimalFormat df = new DecimalFormat("#0.00");
    VendingMachineDao dao;
    BusinessLogic bLogic;
    
    @Inject
    public HomeController(VendingMachineDao dao, BusinessLogic bLogic) {
        this.dao = dao;
        this.bLogic = bLogic;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayHomePage() {
        return "index";
    }
    
    @RequestMapping(value = "/balance", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody public String getBalance() {
        return df.format(currentBalance);
    }
    
    @RequestMapping(value = "/balance", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody public double addFunds(@RequestBody String moneyIn) {
//        String moneyInFormatted = df.format(moneyIn);
        return bLogic.addToBalance(Double.parseDouble(moneyIn));
    }
    
    @RequestMapping(value = "/resetBalance", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody public double updateBalance(@RequestBody String moneyIn) {
        return bLogic.updateBalance(Double.parseDouble(moneyIn));
    }
    
    @RequestMapping(value = "/buy", method = RequestMethod.PUT)
    @ResponseBody public double buyItem(@RequestBody String itemId) {
        Item itemToBuy = dao.getItemById(itemId);
        return bLogic.calcTransaction(itemToBuy.getId());
    }
    
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ResponseBody public List<Item> getItems() {
//        return dao.getAllItems();
        List<Item> items = new ArrayList<>(dao.getAllItems());
        Collections.sort(items);
        return items;
    }
    
//    @RequestMapping(value = "/makeChange", method = RequestMethod.POST)
//    public String makeChange(HttpServletRequest req, Model model) {
//        double changeDue = currentBalance / 100;
//        int numQuarters, numDimes, numNickels, numPennies;
//
//        numQuarters = (int) currentBalance / 25;
//        currentBalance %= 25;
//        numDimes = (int) currentBalance / 10;
//        currentBalance %= 10;
//        numNickels = (int) currentBalance / 5;
//        currentBalance %= 5;
//        numPennies = (int) currentBalance / 1;
//        currentBalance %= 1;
//        
//        String changeDueString = "Change:";
//        String quartersDue = "Quarters: " + numQuarters;
//        String dimesDue = "Dimes: " + numDimes;
//        String nickelsDue = "Nickels: " + numNickels;
//        String penniesDue = "Pennies: " + numPennies;
//        
//        model.addAttribute("changeMessage", changeDueString);
//        model.addAttribute("quartersMessage", quartersDue);
//        model.addAttribute("dimesMessage", dimesDue);
//        model.addAttribute("nickelsMessage", nickelsDue);
//        model.addAttribute("penniesMessage", penniesDue);
//        
//        return "changeResult";
//    }
}

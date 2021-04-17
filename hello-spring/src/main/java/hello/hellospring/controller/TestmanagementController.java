package hello.hellospring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/areazero")
public class TestmanagementController {
    private static final Logger log = LoggerFactory.getLogger(TestmanagementController.class);

    @RequestMapping(value = "/test" , method = GET)
    public String getTestHTML(Model model) {
        return "_managementOfIrresponsibility";
    }

}

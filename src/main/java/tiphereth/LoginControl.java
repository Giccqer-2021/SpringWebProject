package tiphereth;

import hod.ICombineAdministratorDataCommands;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginControl {
    public static ClassPathXmlApplicationContext context;
    public static ICombineAdministratorDataCommands administratorDataCommands;

    static {
        context = new ClassPathXmlApplicationContext("classpath:data_controller.xml", "classpath:command_together.xml");
        administratorDataCommands = (ICombineAdministratorDataCommands) context.getBean("administratorService");
    }

    @RequestMapping("/welcome.html")
    public String getWelcome() {
        return "welcome";
    }

    @RequestMapping("/login.html")
    public String defaultLoginPage() {
        return "login";
    }

    @RequestMapping({"/", "/**/*.html"})
    public String defaultPage(HttpSession session) {
        if (session.getAttribute("login_confirm") != null) {
            return "redirect:/employee.html";
        } else {
            return "redirect:/login.html";
        }
    }

    @RequestMapping(value = "/login.action", method = RequestMethod.GET)
    @ResponseBody
    public String login(@RequestParam("user_name") String name, @RequestParam("user_password") String password, HttpSession session) {
        String passwordData = administratorDataCommands.getPassword(name);
        if (password.equals(passwordData)) {
            session.setAttribute("login_confirm", "true");
//            return "redirect:/employee.html";
            return "true";
        }
//        return "redirect:/login.html";
        return "false";
    }
}

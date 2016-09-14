package testform;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {
 
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ModelAndView showForm() {
    	ModelAndView modelAndView = new ModelAndView("employee");
    	modelAndView.addObject("employee", new Employee());
    	modelAndView.addObject("countries", Country.values());
        return modelAndView;
    }
 
    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public String submit(@Validated @ModelAttribute("employee")Employee employee, 
      BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        model.addAttribute("name", employee.getName());
        model.addAttribute("contactNumber", employee.getContactNumber());
        model.addAttribute("id", employee.getId());
        model.addAttribute("country", employee.getCountry());

        return "employeeView";
    }
}
package controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import model.Task;
import model.TaskStatus;
import model.User;
import model.UserRole;
import service.StatusChangeService;
import service.TaskService;
import service.UserService;

@Controller
public class MainController {

	@Autowired
	TaskService taskService;
	@Autowired
	UserService userService;
	@Autowired
	StatusChangeService statusChangeService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView homepage() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		if(isUserRoleAdmin()){
			ModelAndView model = new ModelAndView();
			model.addObject("title", "Spring Security + Hibernate Example");
			model.addObject("message", "This is admin page");
			model.setViewName("admin");
			return model;
		}
		else if(isUserRole()){
			return buildTaskPageModel();
		} else{
			ModelAndView model = new ModelAndView();
			model.setViewName("403");
			return model;
		}
	}
	
	@RequestMapping(value = "/taskdetails/{id}", method = RequestMethod.GET)
	public ModelAndView taskdetail(@PathVariable(value="id") String id) {
			ModelAndView model = new ModelAndView();
			
			Task task = taskService.findTaskById(Integer.parseInt(id));
			model.addObject("task", task);
			model.setViewName("taskdetails");
			return model;
	}

	private ModelAndView buildTaskPageModel() {
		ModelAndView model = new ModelAndView();
		
		User user = getCurrentUser();
		List<Task> tasks = taskService.findTasksForUser(user);
		
		model.addObject("taskform", new UserTasksForm(tasks));
		model.addObject("statuslist", TaskStatus.values());
		model.setViewName("user_tasks");
		return model;
	}

	private User getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		User user = userService.findByUserName(userDetail.getUsername());
		return user;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}

	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());
		}

		model.setViewName("403");
		return model;

	}

	@RequestMapping(value = "/updateTasksStatus", method = RequestMethod.POST)
	public ModelAndView updateTasksStatus(@ModelAttribute("taskform") UserTasksForm taskForm) {
		List<Task> tasks = taskForm.getTasks();
		
		if(null != tasks && tasks.size() > 0) {
			for (Task task : tasks) {
				taskService.save(task);
			}
		}
		
		return new ModelAndView("user_tasks", "contactForm", new UserTasksForm(tasks));
	}
	private boolean isUserRoleAdmin(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		Collection<? extends GrantedAuthority> authorities = userDetail.getAuthorities();
		for(GrantedAuthority authority : authorities){
			if(authority.getAuthority().equals(UserRole.ROLE_ADMIN.toString())){
				return true;
			}
		}
		return false;
	}
	
	private boolean isUserRole(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		Collection<? extends GrantedAuthority> authorities = userDetail.getAuthorities();
		for(GrantedAuthority authority : authorities){
			if(authority.getAuthority().equals(UserRole.ROLE_USER.toString())){
				return true;
			}
		}
		return false;
	}
}
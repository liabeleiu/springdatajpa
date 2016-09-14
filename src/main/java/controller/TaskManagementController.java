package controller;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import model.User;
import service.StatusChangeService;
import service.TaskService;
import service.UserService;

@Controller
public class TaskManagementController {

	@Autowired
	TaskService taskService;
	@Autowired
	UserService userService;
	@Autowired
	StatusChangeService statusChangeService;
	
	@Autowired	
	EntityManagerFactory entityManagerFactory;
	
	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
	public void helloWorld() {
//		List<Task> allTasks = taskService.findAllTasks();
//		
//		System.out.println("-----------------------------------");
//		for(Task task : allTasks){
//			System.out.println(task.getDescription());
//			System.out.println(task.getName());
//			System.out.println(task.getStatus().toString());
//			System.out.println(task.getStatusChanges().size());
//			System.out.println(task.getUser().getName());
//			System.out.println(task.getStatusChanges().size());
//		}
//		
//		System.out.println(taskService.findTaskByNameContaining("Io").get(0).getName());
//		System.out.println(taskService.findTaskById(1).getDescription());
		
//		Task t = taskService.findTaskById(1);
//		taskService.deleteTask(t);
//		User u = new User();
//		u.setName("Added");
//		u.setPassword("testtets");
//		u.setRole(UserRole.EMPLOYEE);
//		u.setSurname("Bel");
//		u.setUserName("lia");
//		userService.save(u);
		List<User> u = userService.findByNameSurnameAndUserName("Ad", "", "");
		for(User us : u){
			System.out.println(us.getName());
		}
		
		u = userService.findByNameSurnameAndUserName("", "B", "");
		for(User us : u){
			System.out.println(us.getName());
		}
		
		u = userService.findByNameSurnameAndUserName("Add", "", "");
		for(User us : u){
			System.out.println(us.getName());
		}
//		userService.delete(u);
//		System.out.println(u.getName());
		//List<Task> t = taskService.findTasksForUser(u);
		
//		System.out.println(t.getName());
//		StatusChange change = new StatusChange();
//		change.setChangeDate(new Date());
//		change.setChangeInfo("Test");
//		change.setNewStatus(TaskStatus.IN_WORK);
//		change.setTask(t);
//		change.setUser(u);
//		statusChangeService.save(change);
		
		//System.out.println(t.size());
	}
	   @RequestMapping(value = "/student", method = RequestMethod.GET)
	   public ModelAndView student() {
	      return new ModelAndView("student", "command", new Object());
	   }
	   
}

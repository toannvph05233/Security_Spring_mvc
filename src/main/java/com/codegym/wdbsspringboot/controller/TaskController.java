package com.codegym.wdbsspringboot.controller;

import com.codegym.wdbsspringboot.model.AppUser;
import com.codegym.wdbsspringboot.model.Task;
import com.codegym.wdbsspringboot.service.taskservice.ITaskService;
import com.codegym.wdbsspringboot.service.userservice.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private ITaskService taskService;

    @Autowired
    private IAppUserService userService;

    @ModelAttribute("user")
    public AppUser user() {
        return userService.getCurrentUser();
    }

    @GetMapping()
    public ModelAndView listTask(){
        ModelAndView modelAndView = new ModelAndView("/task/list");
        List<Task> tasks = (List<Task>) taskService.findAllByUser(userService.getCurrentUser());
        modelAndView.addObject("tasks", tasks);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("/task/create");
        modelAndView.addObject("task", new Task());
        modelAndView.addObject("user", userService.getCurrentUser());
        return modelAndView;
    }

    @PostMapping("/create")
    public RedirectView creatTask(@ModelAttribute Task task){
        taskService.save(task);
        return new RedirectView("");
    }
}

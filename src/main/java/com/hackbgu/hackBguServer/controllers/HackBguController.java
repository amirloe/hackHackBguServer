package com.hackbgu.hackBguServer.controllers;

import com.hackbgu.hackBguServer.daos.CourseDAO;
import com.hackbgu.hackBguServer.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@CrossOrigin
@Controller
public class HackBguController {

    @Autowired
    private CourseDAO courseDAO;

    @GetMapping("/info")
    @ResponseBody
    public String getInfo(){
        System.out.println("Test");
        return "hello world";
    }

    @GetMapping("/test")
    @ResponseBody
    public Course addCourse(@RequestParam(value = "name")String name){
        Course c = new Course();
        c.setCourseName(name);
        courseDAO.save(c);
        return c;
    }

}


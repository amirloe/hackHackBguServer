package com.hackbgu.hackBguServer.controllers;

import com.hackbgu.hackBguServer.daos.CourseDAO;
import com.hackbgu.hackBguServer.daos.StudyGroupDAO;
import com.hackbgu.hackBguServer.entities.Course;
import com.hackbgu.hackBguServer.entities.StudyGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CrossOrigin
@Controller
public class HackBguController {

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private StudyGroupDAO studyGroupDAO;

    @GetMapping("/info")
    @ResponseBody
    public String getInfo(){
        System.out.println("Test");
        return "hello world";
    }

    @GetMapping("/groups")
    @ResponseBody
    public List<StudyGroup> getGroupsByCourseName(@RequestParam(value = "courseName")String courseName){
        return studyGroupDAO.findByCourseName(courseName);
    }

}


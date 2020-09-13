package com.hackbgu.hackBguServer.controllers;

import com.hackbgu.hackBguServer.daos.CourseDAO;
import com.hackbgu.hackBguServer.daos.StudentDAO;
import com.hackbgu.hackBguServer.daos.StudyGroupDAO;
import com.hackbgu.hackBguServer.entities.Course;
import com.hackbgu.hackBguServer.entities.Student;
import com.hackbgu.hackBguServer.entities.StudyGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@Controller
public class HackBguController {

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private StudyGroupDAO studyGroupDAO;

    @Autowired
    private StudentDAO studentDAO;


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

    @GetMapping("/create_group")
    @ResponseBody
    public StudyGroup CreateGroup(@RequestParam(value = "groupName")String groupName,
                                  @RequestParam(value = "startTime")Date startTime,
                                  @RequestParam(value = "groupSize")Integer groupSize,
                                  @RequestParam(value = "students")List students,
                                  @RequestParam(value = "description")Optional<String> description,
                                  @RequestParam(value = "zoomUrl")Optional<String> zoomUrl){
        if (students == null){
            students = new ArrayList<Student>();
        }
        //ToDo: add the owener of the group

        StudyGroup studyGroup = new StudyGroup(groupName,startTime,groupSize,students,description.orElse(""),zoomUrl.orElse(""));


        studyGroupDAO.save(studyGroup);
        return studyGroup;
    }

    //todo:
    //function to create a new group(with parameters)
    //bring from the server details about an existing group
    //add student, in order to add student list to study group
    //







}


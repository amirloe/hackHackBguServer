package com.hackbgu.hackBguServer.controllers;

import com.hackbgu.hackBguServer.daos.CourseDAO;
import com.hackbgu.hackBguServer.daos.StudyGroupDAO;
import com.hackbgu.hackBguServer.entities.StudyGroup;
import org.springframework.beans.factory.annotation.Autowired;
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

    //@Autowired
    //private StudentDAO studentDAO;


    @GetMapping("/info")
    @ResponseBody
    public String getInfo(){
        System.out.println("Test");

        return "hello world";
    }

    @GetMapping("/groups")
    @ResponseBody
    public List<StudyGroup> getGroupsByGroupName(@RequestParam(value = "groupName")String groupName){
        return studyGroupDAO.findByGroupName(groupName);
    }

    @GetMapping("/create_group")
    @ResponseBody
    public StudyGroup CreateGroup(@RequestParam(value = "groupCreator")String groupCreator,
                                  @RequestParam(value = "groupName")String groupName,
                                  @RequestParam(value = "courseName")String courseName,
                                  @RequestParam(value = "maxNumberOfStudents")Integer maxNumberOfStudents,
                                  @RequestParam(value = "description")Optional<String> description,
                                  @RequestParam(value = "zoomUrl")Optional<String> zoomUrl){

        StudyGroup studyGroup = new StudyGroup(groupCreator,groupName,courseName,maxNumberOfStudents,description.orElse(""),zoomUrl.orElse(""));

        studyGroupDAO.save(studyGroup);
        return studyGroup;
    }

    @GetMapping("/add_student")
    @ResponseBody
    public String AddStudentToGroup(@RequestParam(value = "groupName")String groupName,
                                        @RequestParam(value = "studentName")String studentName){
        List<StudyGroup> studyGroupList =  studyGroupDAO.findByGroupName(groupName);
        if (studyGroupList.size() == 0)
            return "failed to add student";

        StudyGroup sg = studyGroupList.get(0);
        if (sg.getGroupSize() >= sg.getMaxNumberOfStudents()) {
            return "failed to add student";
        }
        studyGroupList.get(0).addStudent(studentName);

        studyGroupDAO.save(studyGroupList.get(0));
        return "success to add student";

    }

    @GetMapping("/remove_student")
    @ResponseBody
    public StudyGroup removeStudentFromGroup(@RequestParam(value = "groupName")String groupName,
                                        @RequestParam(value = "studentName")String studentName){
        List<StudyGroup> studyGroupList =  studyGroupDAO.findByGroupName(groupName);
        if (studyGroupList.size() == 0)
            return null;

        studyGroupList.get(0).removeStudent(studentName);

        studyGroupDAO.save(studyGroupList.get(0));
        return studyGroupList.get(0);

    }

    @GetMapping("/remove_group")
    @ResponseBody
    public String removeStudyGroup(@RequestParam(value = "groupName")String groupName,
                                        @RequestParam(value = "groupCreator")String groupCreator)
        {
        List<StudyGroup> studyGroupList =  studyGroupDAO.findByGroupName(groupName);
        if (studyGroupList.size() == 0)
            return null;


        StudyGroup sg = studyGroupList.get(0);
        if(!sg.getGroupCreator().equals(groupCreator)){
            return "failed to remove group";
        }

        studyGroupDAO.delete(studyGroupList.get(0));
        return "Success to remove Group";

    }

    @GetMapping("/set_description")
    @ResponseBody
    public StudyGroup setGroupDescription(@RequestParam(value = "groupName")String groupName,
                                        @RequestParam(value = "description")String description){
        List<StudyGroup> studyGroupList =  studyGroupDAO.findByGroupName(groupName);
        if (studyGroupList.size() == 0)
            return null;

        studyGroupList.get(0).setDescription(description);

        studyGroupDAO.save(studyGroupList.get(0));
        return studyGroupList.get(0);

    }

    @GetMapping("/set_max_number_of_students")
    @ResponseBody
    public String setMaxNumberOfStudent(@RequestParam(value = "groupName")String groupName,
                                          @RequestParam(value = "max_number_of_student")int maxNumberOfStudents){
        List<StudyGroup> studyGroupList =  studyGroupDAO.findByGroupName(groupName);
        if (studyGroupList.size() == 0)
            return null;

        StudyGroup sg = studyGroupList.get(0);
        if(sg.getMaxNumberOfStudents() < maxNumberOfStudents || sg.getGroupSize() <= maxNumberOfStudents){
            sg.setMaxNumberOfStudents(maxNumberOfStudents);
        }else{
            return "failed to set max number of students";
        }

        studyGroupDAO.save(studyGroupList.get(0));
        return "success to set max number of students";

    }


    @GetMapping("/set_start_time")
    @ResponseBody
    public String setStartTime(@RequestParam(value = "groupName")String groupName,
                               @RequestParam(value = "groupCreator")String groupCreator,
                               @RequestParam(value = "start_time")Date start_time){

        List<StudyGroup> studyGroupList =  studyGroupDAO.findByGroupName(groupName);
        if (studyGroupList.size() == 0)
            return null;

        StudyGroup sg = studyGroupList.get(0);
        if(sg.getGroupCreator().equals(groupCreator) && System.currentTimeMillis() < start_time.getTime()){
            sg.setStartTime(start_time);
        }else{
            return "failed to set start time";
        }

        studyGroupDAO.save(studyGroupList.get(0));
        return "success to set start time";

    }

    //if(this.maxNumberOfStudents < maxNumberOfStudents || this.groupSize <= maxNumberOfStudents){
    //todo:
    //bring from the server details about an existing group
    //add student, in order to add student list to study group
    //







}


package com.example.demo.controllers;

import com.example.demo.entities.Course;
import com.example.demo.entities.StudyGroup;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class HelloController {

    private List<Course> courses = new ArrayList<>();
    private List<StudyGroup> studyGroups = new ArrayList<>();

    public List<StudyGroup> findGroupsByGroupName(String groupName){
        return studyGroups.stream().filter(g -> g.getGroupName().equals(groupName)).collect(Collectors.toList());
    }

    @RequestMapping("/info")
    @ResponseBody
    public String index() {
        return "Welcome to ZooMate site!";
    }

    @GetMapping("/groups")
    @ResponseBody
    public List<StudyGroup> getGroupsByGroupName(@RequestParam(value = "groupName")String groupName){
        return findGroupsByGroupName(groupName);
    }

    @GetMapping("/groups_all")
    @ResponseBody
    public List<StudyGroup> getAllGroups(){
        return studyGroups;
    }

    @GetMapping("/groups_by_courseName")
    @ResponseBody
    public List<StudyGroup> getGroupsByCourseName(@RequestParam(value = "courseName")String courseName){
        return studyGroups.stream().filter(g -> g.getCourseName().equals(courseName)).collect(Collectors.toList());
    }

    @GetMapping("/groups_by_date")
    public List<StudyGroup> getGroupsByStartTime(@RequestParam(value = "startTime") Date startTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return studyGroups.stream().filter(g -> sdf.format(g.getStartTime()).equals(sdf.format(startTime))).collect(Collectors.toList());
    }

    @GetMapping("/create_group")
    @ResponseBody
    public String CreateGroup(@RequestParam(value = "groupCreator")String groupCreator,
                              @RequestParam(value = "groupName")String groupName,
                              @RequestParam(value = "courseName")String courseName,
                              @RequestParam(value = "maxNumberOfStudents")Integer maxNumberOfStudents,
                              @RequestParam(value = "description") Optional<String> description,
                              @RequestParam(value = "zoomUrl")Optional<String> zoomUrl){

        List<StudyGroup> studyGroupList =  findGroupsByGroupName(groupName);
        if (studyGroupList == null || studyGroupList.size() > 0)
            return "failed to create group: group already exist";

        studyGroups.add(new StudyGroup(groupName,groupCreator,courseName,maxNumberOfStudents,description.orElse(""),zoomUrl.orElse("")));
        return "success to create group";
    }

    @GetMapping("/add_student")
    @ResponseBody
    public String AddStudentToGroup(@RequestParam(value = "groupName")String groupName,
                                    @RequestParam(value = "studentName")String studentName){
        List<StudyGroup> studyGroupList =  findGroupsByGroupName(groupName);
        if (studyGroupList == null || studyGroupList.size() == 0)
            return "failed to add student";

        StudyGroup sg = studyGroupList.get(0);
        if (sg.getGroupSize() >= sg.getMaxNumberOfStudents()) {
            return "failed to add student";
        }

        studyGroups.stream()
                .filter(g -> g.getGroupName().equals(groupName))
                .findFirst()
                .ifPresent(g -> g.addStudent(studentName));
        return "success to add student";

    }

    @GetMapping("/remove_student")
    @ResponseBody
    public StudyGroup removeStudentFromGroup(@RequestParam(value = "groupName")String groupName,
                                             @RequestParam(value = "studentName")String studentName){
        List<StudyGroup> studyGroupList =  findGroupsByGroupName(groupName);
        if (studyGroupList == null || studyGroupList.size() == 0)
            return null;

        studyGroupList.stream()
                .filter(g -> g.getGroupName().equals(groupName))
                .filter(g -> g.getStudents().contains(studentName))
                .findFirst()
                .ifPresent(g -> g.removeStudent(studentName));

        return studyGroupList.get(0);
    }

    @GetMapping("/remove_group")
    @ResponseBody
    public String removeStudyGroup(@RequestParam(value = "groupName")String groupName,
                                   @RequestParam(value = "groupCreator")String groupCreator)
    {
        List<StudyGroup> studyGroupList =  findGroupsByGroupName(groupName);
        if (studyGroupList == null || studyGroupList.size() == 0)
            return null;

        StudyGroup sg = studyGroupList.get(0);
        if(!sg.getGroupCreator().equals(groupCreator)){
            return "failed to remove group: group not exist";
        }

        studyGroups.stream()
                .filter(g -> g.getGroupName().equals(groupName))
                .filter(g -> g.getGroupCreator().equals(groupCreator))
                .findFirst()
                .map(p -> {
                    studyGroups.remove(p);
                    return p;
                });
        return "Success to remove Group";
    }

    @GetMapping("/set_description")
    @ResponseBody
    public StudyGroup setGroupDescription(@RequestParam(value = "groupName")String groupName,
                                          @RequestParam(value = "description")String description){
        List<StudyGroup> studyGroupList =  findGroupsByGroupName(groupName);
        if (studyGroupList == null || studyGroupList.size() == 0)
            return null;

        studyGroups.stream()
                .filter(g -> g.getGroupName().equals(groupName))
                .findFirst()
                .ifPresent(g -> g.setDescription(description));
        return studyGroupList.get(0);
    }

    @GetMapping("/set_max_number_of_students")
    @ResponseBody
    public String setMaxNumberOfStudent(@RequestParam(value = "groupName")String groupName,
                                        @RequestParam(value = "max_number_of_student")int maxNumberOfStudents){
        List<StudyGroup> studyGroupList =  findGroupsByGroupName(groupName);
        if (studyGroupList == null || studyGroupList.size() == 0)
            return null;

        if(studyGroupList.get(0).getMaxNumberOfStudents() < maxNumberOfStudents || studyGroupList.get(0).getGroupSize() <= maxNumberOfStudents){
            studyGroups.stream()
                    .filter(g -> g.getGroupName().equals(groupName))
                    .findFirst()
                    .ifPresent(g -> g.setMaxNumberOfStudents(maxNumberOfStudents));
            //studyGroupList.get(0).setMaxNumberOfStudents(maxNumberOfStudents);
        }else{
            return "failed to set max number of students";
        }

        return "success to set max number of students";
    }

    @GetMapping("/set_start_time")
    @ResponseBody
    public String setStartTime(@RequestParam(value = "groupName")String groupName,
                               @RequestParam(value = "groupCreator")String groupCreator,
                               @RequestParam(value = "start_time")Date start_time){

        List<StudyGroup> studyGroupList =  findGroupsByGroupName(groupName);
        if (studyGroupList == null || studyGroupList.size() == 0)
            return null;

        if(studyGroupList.get(0).getGroupCreator().equals(groupCreator) && System.currentTimeMillis() < start_time.getTime()){
            studyGroups.stream()
                    .filter(g -> g.getGroupName().equals(groupName))
                    .findFirst()
                    .ifPresent(g -> g.setStartTime(start_time));
        }else{
            return "failed to set start time";
        }

        return "success to set start time";

    }
}


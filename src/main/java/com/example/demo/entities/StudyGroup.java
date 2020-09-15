package com.example.demo.entities;

import java.util.*;

public class StudyGroup {

    public StudyGroup() {
    }

    private int id;

    private int maxNumberOfStudents;

    private int groupSize;

    private String groupName;

    private String courseName;

    private String groupCreator;

    private String description; //"assiggnment 3" or "study for test" for example

    private Date startTime;

    private String zoomUrl;

    private List<String> students = new ArrayList<>();

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupCreator() {
        return groupCreator;
    }

    public void setGroupCreator(String groupOwener) {
        this.groupCreator = groupOwener;
    }

    public StudyGroup(String groupName,String groupCreator,String courseName, Integer maxNumberOfStudents, String description, String zoomUrl){
        this.groupCreator = groupCreator;
        this.groupName = groupName;
        this.courseName = courseName;
        this.startTime = new Date();
        this.groupSize = 0;
        this.description = description;
        this.maxNumberOfStudents = maxNumberOfStudents;
        this.zoomUrl = zoomUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addStudent(String student){
        students.add(student);
        groupSize++;
    }

    public void removeStudent(String student){
        students.removeIf(s -> s.equals(student));
        groupSize--;
    }

    public int getMaxNumberOfStudents() {
        return maxNumberOfStudents;
    }

    public int getGroupSize() {
        return groupSize;
    }

    //@OneToMany//(targetEntity=Student.class, mappedBy="name", fetch=FetchType.EAGER)
    //@Transient
    public List<String> getStudents() {
        return students;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }

    public void setMaxNumberOfStudents(int maxNumberOfStudents) {
        this.maxNumberOfStudents = maxNumberOfStudents;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date dateTime) {
        this.startTime = dateTime;
    }

    public String getZoomUrl() {
        return zoomUrl;
    }

    public void setZoomUrl(String zoomUrl) {
        this.zoomUrl = zoomUrl;
    }

}//StudyGroup

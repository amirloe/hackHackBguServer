package com.hackbgu.hackBguServer.entities;

import javax.persistence.*;
import java.util.*;


@Entity
public class StudyGroup {

    public StudyGroup() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //todo: fix the bug that makes it not work
    private int id;

    private int maxNumberOfStudents;

    private int groupSize;

    @Column(unique=true)
    private String groupName;

    @Column(unique=true)
    private String courseName;

    @Column(unique=true)
    private String groupCreator;

    private String description; //"assiggnment 3" or "study for test" for example

    private Date startTime;

    private String zoomUrl;

    private String students = "";

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

    public StudyGroup(String groupCreator, String groupName, String courseName, Integer maxNumberOfStudents, String description, String zoomUrl){
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
        students = students + ", " + student;
        //students.add(student);
        groupSize++;

    }

    public void removeStudent(String student){
        List<String> studs = new LinkedList<String>(Arrays.asList(students.split(", ")));//Arrays.asList(students.split(", "));
        studs.removeIf(s -> s.equals(student));
        groupSize--;
        this.students = String.join(", ", studs);
    }

    public int getMaxNumberOfStudents() {
        return maxNumberOfStudents;
    }

    public int getGroupSize() {
        return groupSize;
    }

    //@OneToMany//(targetEntity=Student.class, mappedBy="name", fetch=FetchType.EAGER)
    //@Transient
    public String getStudents() {
        return students;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    //@Transient
    public void setStudents(String students) {
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

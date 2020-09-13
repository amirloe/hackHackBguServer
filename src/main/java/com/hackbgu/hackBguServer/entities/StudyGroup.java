package com.hackbgu.hackBguServer.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;



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
    private String courseName;

    private String description; //"assiggnment 3" or "study for test" for example

    private Date startTime;

    @OneToMany
    @Type(type = "com.hackbgu.hackBguServer.entities.Student")
    private List<Student> students;

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public StudyGroup(String groupName, Date startTime, Integer groupSize, List<Student> students, String description, String zoomUrl){
        this.courseName = groupName;
        this.startTime = startTime;
        this.groupSize = groupSize;
        this.students = students;
        this.description = description;
        this.zoomUrl = zoomUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addStudent(Student student){
        if (groupSize < maxNumberOfStudents){
            students.add(student);
            groupSize++;
        }
        else{
            System.out.println("max number of students in this group is " + groupSize);
        }
    }

    public int getMaxNumberOfStudents() {
        return maxNumberOfStudents;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public List<Student> getStudents() {
        return students;
    }

    private String zoomUrl;

    public void setId(int id) {
        this.id = id;
    }

    @Id
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

package com.hackbgu.hackBguServer.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class StudyGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String courseName;

    private Date dateTime;

    private String zoomUrl;

    public void setId(String id) {
        this.id = id;
    }

    @Id
    public String getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getZoomUrl() {
        return zoomUrl;
    }

    public void setZoomUrl(String zoomUrl) {
        this.zoomUrl = zoomUrl;
    }
}

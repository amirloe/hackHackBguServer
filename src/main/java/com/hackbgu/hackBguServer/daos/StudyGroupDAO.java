package com.hackbgu.hackBguServer.daos;

import com.hackbgu.hackBguServer.entities.StudyGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface StudyGroupDAO extends CrudRepository<StudyGroup,Integer> {
    //StudyGroup findByGroupName(String groupName);
    List<StudyGroup> findByGroupName(String groupName);
    List<StudyGroup> findByCourseName(String courseName);
    List<StudyGroup> findByStartTime(Date startTime);
}

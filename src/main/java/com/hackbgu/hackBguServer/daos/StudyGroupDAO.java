package com.hackbgu.hackBguServer.daos;

import com.hackbgu.hackBguServer.entities.StudyGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudyGroupDAO extends CrudRepository<StudyGroup,Integer> {
    List<StudyGroup> findByCourseName(String courseName);
}

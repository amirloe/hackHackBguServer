package com.hackbgu.hackBguServer.daos;

import com.hackbgu.hackBguServer.entities.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseDAO extends CrudRepository<Course,Integer> {
}

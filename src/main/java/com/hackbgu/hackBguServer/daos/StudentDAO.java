package com.hackbgu.hackBguServer.daos;

import com.hackbgu.hackBguServer.entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentDAO extends CrudRepository<Student,Integer> {
}

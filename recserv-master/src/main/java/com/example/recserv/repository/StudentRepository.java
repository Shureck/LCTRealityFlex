package com.example.recserv.repository;

import com.example.recserv.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;



public interface StudentRepository extends JpaRepository<Student, Integer> {
   Student findALLById(Integer id);
}

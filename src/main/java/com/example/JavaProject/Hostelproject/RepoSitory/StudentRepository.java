package com.example.JavaProject.Hostelproject.RepoSitory;

import com.example.JavaProject.Hostelproject.Entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity,Long> {
}

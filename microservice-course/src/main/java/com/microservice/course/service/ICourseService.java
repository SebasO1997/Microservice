package com.microservice.course.service;

import com.microservice.course.entities.Course;
import com.microservice.course.http.response.StudentByCourseResponse;

import java.util.List;

public interface ICourseService{

    List<Course> findAll();

    Course findById(Long id);

    void save (Course course);

    // respuesta personalizada, desde cursos se envia una peticion a estudiantes y la respuesta se personaliza para el cliente\
    StudentByCourseResponse findByStudentByIdCourse(Long idCourse);

}

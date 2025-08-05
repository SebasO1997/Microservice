package com.microservice.course.service;

import com.microservice.course.client.IStudentClient;
import com.microservice.course.entities.Course;
import com.microservice.course.http.response.StudentByCourseResponse;
import com.microservice.course.persistence.dto.StudentDTO;
import com.microservice.course.persistence.repository.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private IStudentClient studentClient;


    @Override
    public List<Course> findAll() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Override
    public StudentByCourseResponse findByStudentByIdCourse(Long idCourse) {

        // Consultar el curso
        Course course = courseRepository.findById(idCourse).orElseThrow(() -> new NoSuchElementException("No se encontró el curso"));

        // Obtener los estudiantes, con esto se conecta con el microservicio
        List<StudentDTO> studentDTOList = studentClient.findAllStudentsByCourse(idCourse);
        return StudentByCourseResponse.builder()
                .courseName(course.getName())
                .teacher(course.getTeacher())
                .studentDTOList(studentDTOList)
                .build();
    }
}

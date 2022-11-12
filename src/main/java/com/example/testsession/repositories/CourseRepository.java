package com.example.testsession.repositories;

import com.example.testsession.dto.CourseResponse;
import com.example.testsession.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Modifying
    @Transactional
    @Query("update Course set " +
            "courseName = :courseName, " +
            "courseDescription = :description, " +
            "courseImage = :image where id = :id")
    void update(@Param("id") Long id,
                @Param("courseName") String courseName,
                @Param("description") String description,
                @Param("image") String image);


    @Query("select new com.example.testsession.dto.CourseResponse(" +
            "c.id," +
            "c.courseName," +
            "c.courseDescription," +
            "c.courseImage) from Course c group by c order by c.id desc ")
    List<CourseResponse> getAllCourses();

    @Query("select new com.example.testsession.dto.CourseResponse(" +
            "c.id," +
            "c.courseName," +
            "c.courseDescription," +
            "c.courseImage) from Course c where c.id = ?1")
    CourseResponse getCourseById(Long id);
}
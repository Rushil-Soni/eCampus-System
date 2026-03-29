package com.ecampus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecampus.model.StudentRegistrationCourses;

@Repository
public interface StudentRegistrationCoursesRepository extends JpaRepository<StudentRegistrationCourses, Long> {

    List<StudentRegistrationCourses> findBySrcsrgidIn(List<Long> srgIds);

    Optional<StudentRegistrationCourses> findBySrcid(Long srcid);

    @Query(value = "SELECT COALESCE(MAX(src.srcid), 0) FROM ec2.studentregistrationcourses src", nativeQuery = true)
    Long findMaxSrcId();
}

package com.ecampus.repository;

import com.ecampus.model.CourseTypeConversion;
import com.ecampus.model.CourseTypeConversionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseTypeConversionRepository extends JpaRepository<CourseTypeConversion, CourseTypeConversionId> {
    
}

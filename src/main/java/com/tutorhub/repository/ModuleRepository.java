package com.tutorhub.repository;

import com.tutorhub.model.course.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, Long> {}

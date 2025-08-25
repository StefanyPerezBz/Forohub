package com.forohub.forohub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forohub.forohub.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
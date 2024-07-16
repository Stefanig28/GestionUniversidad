package com.example.GestionUniversidad.Repository;

import com.example.GestionUniversidad.Model.Curso;
import com.example.GestionUniversidad.Model.Estudiante;
import com.example.GestionUniversidad.Model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    Optional<Matricula> findByCursoAndEstudiante(Curso curso, Estudiante estudiante);
}

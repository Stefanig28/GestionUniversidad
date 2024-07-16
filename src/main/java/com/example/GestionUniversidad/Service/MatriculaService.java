package com.example.GestionUniversidad.Service;

import com.example.GestionUniversidad.Model.Curso;
import com.example.GestionUniversidad.Model.Estudiante;
import com.example.GestionUniversidad.Model.Matricula;
import com.example.GestionUniversidad.Repository.CursoRepository;
import com.example.GestionUniversidad.Repository.EstudianteRepository;
import com.example.GestionUniversidad.Repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {

    private MatriculaRepository matriculaRepository;
    private CursoRepository cursoRepository;
    private EstudianteRepository estudianteRepository;

    @Autowired
    public MatriculaService(CursoRepository cursoRepository, EstudianteRepository estudianteRepository, MatriculaRepository matriculaRepository) {
        this.cursoRepository = cursoRepository;
        this.estudianteRepository = estudianteRepository;
        this.matriculaRepository = matriculaRepository;
    }

    public void matricularEstudiante(Long curso_id, Long estudiante_id) {
        Curso curso = cursoRepository.findById(curso_id).orElseThrow(() -> new RuntimeException("Curso " + curso_id + " no encontrado"));
        Estudiante estudiante = estudianteRepository.findById(estudiante_id).orElseThrow(() -> new RuntimeException("Estudiante " + estudiante_id + " no encontrado"));

        if (curso.getEstudiantes().contains(estudiante)) {
            throw new RuntimeException("El estudiante " + estudiante_id + " ya está matriculado");
        }
        if (curso.cursoLleno()) {
            throw new RuntimeException("No se puede matricular el estudiante " + estudiante_id + " el curso está lleno");
        }

        Matricula matricula = new Matricula(curso, estudiante, LocalDate.now());

        matriculaRepository.save(matricula);
        curso.agregarMatricula(matricula);

        estudianteRepository.save(estudiante);
        curso.getEstudiantes().add(estudiante);

        cursoRepository.save(curso);
        estudiante.getCursos().add(curso);

        // agregar la lógica de no poder matricular al mismo estudiante
    }


    public List<Matricula> obtenerMatricula() {
        return this.matriculaRepository.findAll();
    }

    public void actualizarMatricula(long id, Matricula matricula) {
        Optional<Matricula> optionalMatricula = matriculaRepository.findById(id);
        if (optionalMatricula.isPresent()) {
            Matricula matriculaExistente = optionalMatricula.get();
            matriculaExistente.setFechaMatricula(matricula.getFechaMatricula());
            matriculaExistente.setFechaDesmatricula(matricula.getFechaDesmatricula());

            matriculaRepository.save(matriculaExistente);
        }
    }

    public void desmatricularEstudiante(Long curso_id, Long estudiante_id) {
        Curso curso = cursoRepository.findById(curso_id).orElseThrow(() -> new RuntimeException("Curso " + curso_id + " no encontrado"));
        Estudiante estudiante = estudianteRepository.findById(estudiante_id).orElseThrow(() -> new RuntimeException("Estudiante " + estudiante_id + " no encontrado"));

        Matricula matricula = matriculaRepository.findByCursoAndEstudiante(curso, estudiante).orElseThrow(() -> new RuntimeException("El curso o el estudiante no se encuetran registrados"));

        matricula.setFechaDesmatricula(LocalDate.now());

        matriculaRepository.save(matricula);
        curso.eliminarMatricula(matricula);

        estudianteRepository.save(estudiante);
        curso.getEstudiantes().remove(estudiante);

        cursoRepository.save(curso);
        estudiante.getCursos().remove(curso);
    }

    public void eliminarMatricula(Long curso_id){
        Matricula matricula = matriculaRepository.findById(curso_id).orElseThrow(() -> new RuntimeException("No se encontro la matricula"));
        matriculaRepository.delete(matricula);
    }

}

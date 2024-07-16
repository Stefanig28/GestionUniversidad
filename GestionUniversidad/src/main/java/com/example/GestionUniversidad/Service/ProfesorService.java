package com.example.GestionUniversidad.Service;

import com.example.GestionUniversidad.Model.Estudiante;
import com.example.GestionUniversidad.Model.Profesor;
import com.example.GestionUniversidad.Repository.CursoRepository;
import com.example.GestionUniversidad.Repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService {

    private ProfesorRepository profesorRepository;
    private CursoRepository cursoRepository;

    @Autowired
    public ProfesorService(CursoRepository cursoRepository, ProfesorRepository profesorRepository) {
        this.cursoRepository = cursoRepository;
        this.profesorRepository = profesorRepository;
    }

    public void crearProfesor(Profesor profesor){
        this.profesorRepository.save(profesor);
    }

    public List<Profesor> obtenerProfesor(){
        return this.profesorRepository.findAll();
    }

    public void actualizarProfesor(Long id, Profesor profesor){
        Optional<Profesor> optionalProfesor = profesorRepository.findById(id);
        if (optionalProfesor.isPresent()){
            Profesor profesorExistente = optionalProfesor.get();
            profesorExistente.setNombre(profesor.getNombre());
            profesorExistente.setCursos(profesor.getCursos());

            profesorRepository.save(profesorExistente);
        }
    }

    public void eliminarProfesor(Long profesorId){
        profesorRepository.findById(profesorId); // TODO: añadir excepcion
        if (!cursoRepository.existsByProfesorId(profesorId)){
            profesorRepository.deleteById(profesorId);
        } // añadir excepcion
    }

}

package com.example.GestionUniversidad.Service;

import com.example.GestionUniversidad.Model.Curso;
import com.example.GestionUniversidad.Repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private CursoRepository cursoRepository;

    @Autowired
    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public void crearCurso(Curso curso){
        this.cursoRepository.save(curso);
    }

    public List<Curso> obtenerCurso(){
        return this.cursoRepository.findAll();
    }

    public void actualizarCurso(Long id, Curso curso){
        Optional<Curso> optionalCurso = cursoRepository.findById(id);
        if (optionalCurso.isPresent()){
            Curso cursoExistente = optionalCurso.get();
            cursoExistente.setNombre(curso.getNombre());
            cursoExistente.setDescripcion(curso.getDescripcion());

            cursoRepository.save(cursoExistente);
        }
    }

    public void eliminarCurso(Long id){
        Optional<Curso> optionalCurso = cursoRepository.findById(id);
        if (optionalCurso.isPresent()){
            cursoRepository.deleteById(id);
        }
    }

}

package com.example.GestionUniversidad.Service;

import com.example.GestionUniversidad.Model.Estudiante;
import com.example.GestionUniversidad.Repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    private EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public void crearEstudiante(Estudiante estudiante){
        if(Period.between(estudiante.getFechaNacimiento(), LocalDate.now()).getYears() >= 18){
            this.estudianteRepository.save(estudiante);
        }
    }

    public List<Estudiante> obtenerEstudiantes(){
        return this.estudianteRepository.findAll();
    }

    public void actualizarEstudiante(Long id, Estudiante estudiante){
        Optional<Estudiante> optionalEstudiante = estudianteRepository.findById(id);
        if (optionalEstudiante.isPresent()) {
            Estudiante estudianteExistente = optionalEstudiante.get();
            estudianteExistente.setNombre(estudiante.getNombre());
            estudianteExistente.setCursos(estudiante.getCursos());

            estudianteRepository.save(estudianteExistente);
        }
    }

    public void eliminarEstudiante(Long id, Estudiante estudiante){
        Optional<Estudiante> optionalEstudiante = estudianteRepository.findById(id);
        if (optionalEstudiante.isPresent()) {
            estudianteRepository.deleteById(id);
        }
    }

}

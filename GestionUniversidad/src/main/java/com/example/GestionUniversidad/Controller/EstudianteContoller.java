package com.example.GestionUniversidad.Controller;

import com.example.GestionUniversidad.Model.Estudiante;
import com.example.GestionUniversidad.Service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/estudiantes")
public class EstudianteContoller {

    private EstudianteService estudianteService;

    public EstudianteContoller(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @PostMapping
    public ResponseEntity<String> crearEstudiante(@RequestBody Estudiante estudiante){
        this.estudianteService.crearEstudiante(estudiante);
        return ResponseEntity.ok("Se creo el estudiante correctamente");
    }

    @GetMapping
    public List<Estudiante> obtenerEstudiantes(){
        return this.estudianteService.obtenerEstudiantes();
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarEstudiante(@PathVariable Long id, @RequestBody Estudiante estudiante){
        this.estudianteService.actualizarEstudiante(id, estudiante);
        return ResponseEntity.ok("Se actualizo el estudiante correctamente");
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarEstudiante(@PathVariable Long id, Estudiante estudiante){
        this.estudianteService.eliminarEstudiante(id, estudiante);
        return ResponseEntity.ok("Se eliminó el estudiante correctamente");
    }

}

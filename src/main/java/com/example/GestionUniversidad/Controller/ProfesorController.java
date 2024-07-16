package com.example.GestionUniversidad.Controller;

import com.example.GestionUniversidad.Model.Estudiante;
import com.example.GestionUniversidad.Model.Profesor;
import com.example.GestionUniversidad.Service.ProfesorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/profesores")
public class ProfesorController {

    private ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @PostMapping
    public ResponseEntity<String> crearProfesor(@RequestBody Profesor profesor) {
        this.profesorService.crearProfesor(profesor);
        return ResponseEntity.ok("Se creo el profesor correctamente");
    }

    @GetMapping
    public List<Profesor> obtenerProfesor(){
        return this.profesorService.obtenerProfesor();
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarProfesor(@PathVariable Long id, @RequestBody Profesor profesor){
        this.profesorService.actualizarProfesor(id, profesor);
        return ResponseEntity.ok("Se actualizo el profesor correctamente");
    }

    @DeleteMapping("/eliminar/{profesor_id}")
    public ResponseEntity<String> eliminarProfesor(@PathVariable Long profesorId){
        this.profesorService.eliminarProfesor(profesorId);
        return ResponseEntity.ok("Se eliminó el profesor correctamente");
    }

}

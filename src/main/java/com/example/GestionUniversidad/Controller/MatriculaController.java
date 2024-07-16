package com.example.GestionUniversidad.Controller;

import com.example.GestionUniversidad.Model.Matricula;
import com.example.GestionUniversidad.Service.MatriculaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/matriculas")
public class MatriculaController {

    private MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @PostMapping("/curso/{curso_id}/matricular/estudiante/{estudiante_id}")
    public ResponseEntity<String> matricularEstudiante(@PathVariable Long curso_id, @PathVariable Long estudiante_id){
        this.matriculaService.matricularEstudiante(curso_id, estudiante_id);
        return ResponseEntity.ok("Se matriculo correctamente al estudiante");
    }

    @GetMapping
    public List<Matricula> obtenerMatricula(){
        return this.matriculaService.obtenerMatricula();
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarMatricula(@PathVariable Long id, @RequestBody Matricula matricula){
        this.matriculaService.actualizarMatricula(id, matricula);
        return ResponseEntity.ok("Se actualizo la matricula correctamente");
    }

    @PostMapping("/curso/{curso_id}/desmatricular/estudiante/{estudiante_id}")
    public ResponseEntity<String> desmatricularEstudiante(@PathVariable Long curso_id, @PathVariable Long estudiante_id){
        this.matriculaService.desmatricularEstudiante(curso_id, estudiante_id);
        return ResponseEntity.ok("Se desmatriculo correctamente al estudiante");
    }

    @DeleteMapping("/eliminar/{curso_id}")
    public ResponseEntity<String> eliminarMatricula(@PathVariable Long curso_id){
        this.matriculaService.eliminarMatricula(curso_id);
        return ResponseEntity.ok("Se eliminó la matricula correctamente");
    }

}

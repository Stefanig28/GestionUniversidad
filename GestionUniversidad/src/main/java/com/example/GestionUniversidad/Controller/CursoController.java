package com.example.GestionUniversidad.Controller;

import com.example.GestionUniversidad.Model.Curso;
import com.example.GestionUniversidad.Service.CursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cursos")
public class CursoController {

    private CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    public ResponseEntity<String> crearCurso(@RequestBody Curso curso){
        this.cursoService.crearCurso(curso);
        return ResponseEntity.ok("Se creo el curso correctamente");
    }

    @GetMapping
    public List<Curso> obtenerCurso(){
        return this.cursoService.obtenerCurso();
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarCurso(@PathVariable Long id, @RequestBody Curso curso){
        this.cursoService.actualizarCurso(id, curso);
        return ResponseEntity.ok("Se actualizo el curso correctamente");
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarCurso(@PathVariable Long id){
        this.cursoService.eliminarCurso(id);
        return ResponseEntity.ok("Se elimino el curso correctamente");
    }

}

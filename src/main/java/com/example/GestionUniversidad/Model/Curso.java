package com.example.GestionUniversidad.Model;

import jakarta.persistence.*;
import org.aspectj.weaver.ISourceContext;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curso_id")
    private Long id

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "materia")
    private String materia;

    @Column(name = "max_estudiantes")
    private int maxEstudiantes;


    @ManyToOne
    @JoinColumn(name = "curso_profesor_id")
    private Profesor profesor;

    @ManyToMany
    @JoinTable(name = "estudiantes", joinColumns = @JoinColumn(name = "curso_id"), inverseJoinColumns = @JoinColumn(name = "estudiante_id"))
    private List<Estudiante> estudiantes;

    @OneToMany(mappedBy = "curso")
    private Set<Matricula> matricula;

    public Curso() {
    }

    public Curso(String descripcion, Long id, String materia, String nombre) {
        this.descripcion = descripcion;
        this.id = id;
        this.materia = materia;
        this.nombre = nombre;
    }

    public Curso(String descripcion, List<Estudiante> estudiantes, Long id, String materia, int maxEstudiantes, String nombre, Profesor profesor) {
        this.descripcion = descripcion;
        this.estudiantes = estudiantes;
        this.id = id;
        this.materia = materia;
        this.maxEstudiantes = maxEstudiantes;
        this.nombre = nombre;
        this.profesor = profesor;
    }

    public boolean cursoLleno(){
        return matricula.size() >= maxEstudiantes;
    }

    public void agregarMatricula(Matricula matricula){
        this.matricula.add(matricula);
        matricula.setCurso(this);
    }

    public void eliminarMatricula(Matricula matricula){
        this.matricula.remove(matricula);
        matricula.setCurso(null);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Long getId() {
        return id;
    }

    public String getMateria() {
        return materia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public int getMaxEstudiantes() {
        return maxEstudiantes;
    }

    public void setMaxEstudiantes(int maxEstudiantes) {
        this.maxEstudiantes = maxEstudiantes;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public Set<Matricula> getMatricula() {
        return matricula;
    }

    public void setMatricula(Set<Matricula> matricula) {
        this.matricula = matricula;
    }

    public Profesor getProfesor() {
        return profesor;
    }
}

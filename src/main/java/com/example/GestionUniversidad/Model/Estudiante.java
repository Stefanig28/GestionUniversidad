package com.example.GestionUniversidad.Model;

import jakarta.persistence.*;
import lombok.extern.apachecommons.CommonsLog;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "estudiante")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estudiante_id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @ManyToMany(mappedBy = "estudiantes")
    @Column(name = "cursos")
    private List<Curso> cursos;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    @Column(name = "matricula")
    private List<Matricula> matricula;

    public Estudiante(String apellido, LocalDate fechaNacimiento, Long id, String nombre) {
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.id = id;
        this.nombre = nombre;
    }

    public Estudiante() {
    }

    public String getApellido() {
        return apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Estudiante(String apellido, List<Curso> cursos, LocalDate fechaNacimiento, Long id, List<Matricula> matricula, String nombre) {
        this.apellido = apellido;
        this.cursos = cursos;
        this.fechaNacimiento = fechaNacimiento;
        this.id = id;
        this.matricula = matricula;
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMatricula(List<Matricula> matricula) {
        this.matricula = matricula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public List<Matricula> getMatricula() {
        return matricula;
    }
}

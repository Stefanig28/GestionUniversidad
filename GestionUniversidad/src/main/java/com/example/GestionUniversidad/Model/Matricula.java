package com.example.GestionUniversidad.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "matricula")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matricula_id")
    private Long id;

    @Column(name = "fecha_matricula")
    private LocalDate fechaMatricula;

    @Column(name = "fecha_desmatricula")
    private LocalDate fechaDesmatricula;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Matricula() {
    }

    public Matricula(Curso curso, Estudiante estudiante, LocalDate fechaMatricula) {
        this.curso = curso;
        this.estudiante = estudiante;
        this.fechaMatricula = fechaMatricula;
    }

    public Matricula(Curso curso, Estudiante estudiante, LocalDate fechaDesmatricula, LocalDate fechaMatricula, Long id) {
        this.curso = curso;
        this.estudiante = estudiante;
        this.fechaDesmatricula = fechaDesmatricula;
        this.fechaMatricula = fechaMatricula;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getFechaMatricula() {
        return fechaMatricula;
    }

    public LocalDate getFechaDesmatricula() {
        return fechaDesmatricula;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public void setFechaDesmatricula(LocalDate fechaDesmatricula) {
        this.fechaDesmatricula = fechaDesmatricula;
    }

    public void setFechaMatricula(LocalDate fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public Curso getCurso() {
        return curso;
    }
}

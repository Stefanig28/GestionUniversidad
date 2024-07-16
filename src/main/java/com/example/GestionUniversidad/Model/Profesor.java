package com.example.GestionUniversidad.Model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profesor")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profesor_id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "fecha_inicio_trabajo")
    private LocalDate FechaInicioTrabajo;

    @ElementCollection
    @CollectionTable(name = "profesor_titulos_academicos", joinColumns = @JoinColumn(name = "profesor_id"))
    @Column(name = "titulo_academico")
    private List<String> titulosAcademicos;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL)
    @Column(name = "cursos")
    private List<Curso> cursos = new ArrayList<>();

    public Profesor() {
    }

    public Profesor(String apellido, LocalDate fechaInicioTrabajo, Long id, String nombre) {
        this.apellido = apellido;
        FechaInicioTrabajo = fechaInicioTrabajo;
        this.id = id;
        this.nombre = nombre;
    }

    public Profesor(String apellido, List<Curso> cursos, LocalDate fechaInicioTrabajo, Long id, String nombre, List<String> titulosAcademicos) {
        this.apellido = apellido;
        this.cursos = cursos;
        FechaInicioTrabajo = fechaInicioTrabajo;
        this.id = id;
        this.nombre = nombre;
        this.titulosAcademicos = titulosAcademicos;
    }

    public String getApellido() {
        return apellido;
    }

    public LocalDate getFechaInicioTrabajo() {
        return FechaInicioTrabajo;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public List<String> getTitulosAcademicos() {
        return titulosAcademicos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public void setFechaInicioTrabajo(LocalDate fechaInicioTrabajo) {
        FechaInicioTrabajo = fechaInicioTrabajo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTitulosAcademicos(List<String> titulosAcademicos) {
        this.titulosAcademicos = titulosAcademicos;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

}

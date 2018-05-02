/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo1.beans;

import com.demo1.dao.LibroDAO;
import com.demo1.models.Libro;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;



/**
 *
 * @author raul
 */
@ManagedBean(name = "libroBean")
@ViewScoped
public class LibroBean implements Serializable {
    
    private int idLibro;
    private String nombres;
    private String autor;
    private String editorial;
    private String publicacion;
    private Libro mlibro;
    private List<Libro> listaLibroFiltrados;
    private List<Libro> listalibros;
    
    
    public LibroBean(){
        listarLibros();
    }
    
    
    
    public void addLibro(){
        Libro libro = new Libro(idLibro, nombres, autor, editorial, publicacion);
        LibroDAO libroDAO = new LibroDAO();
        libroDAO.addLibro(libro);
    }
    
    public void returnLibroById(){
        LibroDAO libroDAO = new LibroDAO();
        Libro libro = libroDAO.findLibroByID(idLibro);
        
        if(libro != null){
            setNombres(libro.getNombres());
            setPublicacion(libro.getPublicacion());
            setEditorial(libro.getEditorial());
            setAutor(libro.getAutor());
        }else{
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Libro no encontrado"));
        }
    }
    
    public void updateLibro(){
        Libro newLibro = new Libro(idLibro, nombres, autor, editorial, publicacion);
        LibroDAO libroDAO = new LibroDAO();
        libroDAO.updateLibro(getIdLibro(), newLibro);
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage("Cliente actualizado correctamente"));   
    }
    
    public List<Libro> listarLibros(){
        LibroDAO libroDAO = new LibroDAO();
        listalibros = libroDAO.listaLibroTodos();
        return listalibros;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(String publicacion) {
        this.publicacion = publicacion;
    }

    public Libro getMlibro() {
        return mlibro;
    }

    public void setMlibro(Libro mlibro) {
        this.mlibro = mlibro;
    }

    public List<Libro> getListaLibroFiltrados() {
        return listaLibroFiltrados;
    }

    public void setListaLibroFiltrados(List<Libro> listaLibroFiltrados) {
        this.listaLibroFiltrados = listaLibroFiltrados;
    }

    public List<Libro> getListalibros() {
        return listalibros;
    }

    public void setListalibros(List<Libro> listalibros) {
        this.listalibros = listalibros;
    }
    
    
    
    
}

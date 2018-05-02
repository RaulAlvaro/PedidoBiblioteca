/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo1.beans;

import com.demo1.dao.AlumnoDAO;
import com.demo1.models.Alumno;
import com.demo1.utils.NewHibernateUtil;
import java.sql.Connection;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author raul
 */
@ManagedBean(name = "alumnoBean")
@SessionScoped
public class AlumnoBean {

    private int codigo;
     private String nombres;
     private String apellidos;
     private String escuela;
     private String dni;
     private String correo;
    
     
    public AlumnoBean() {
    
    }
    
    public void addAlumno(){
        Alumno alumno = new Alumno(codigo, nombres, apellidos, escuela, dni, correo);
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        alumnoDAO.addAlumno(alumno);
        limpiarCampos();
    }

    public void returnAlumnoById(){
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        Alumno alumno = alumnoDAO.findAlumnoByID(getCodigo());
        if(alumno != null){
            System.out.println(alumno.getCodigo());
            System.out.println(alumno.getNombres());
            setCodigo(alumno.getCodigo());
            setNombres(alumno.getNombres());
            setApellidos(alumno.getApellidos());
            setEscuela(alumno.getEscuela());
            setCorreo(alumno.getCorreo());
            setDni(alumno.getDni());
        }else{
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Alumno no encontrado"));
        }
    }
    public String probarConexion() throws NamingException, SQLException{
        String ret = null;
        DataSource ds = (DataSource)new InitialContext().lookup("jndi_biblio_conn");
        try(Connection conn = ds.getConnection()){
            ret = "default.xhtml";
        }
        
        return ret;
    }
    
    public void updateAlumno(){
        Alumno newAlumno = new Alumno(getCodigo(), getNombres(), getApellidos(), getEscuela()
                , getDni(), getCorreo());
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        alumnoDAO.updateAlumno(getCodigo(), newAlumno);
        
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage("Alumno actualizado correctamente"));   
    }
    
    public void deleteAlumno(){
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        alumnoDAO.deleteAlumno(codigo);
        limpiarCampos();
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage("Alumno eliminado"));
    }
    
    public void limpiarCampos(){
        setCodigo(0);
        setNombres("");
        setApellidos("");
        setCorreo("");
        setDni("");
        setEscuela("");
    }
    
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

   
}

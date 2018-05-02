/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo1.transaccion;



import com.demo1.beans.UsuarioBean;
import com.demo1.dao.AlumnoDAO;
import com.demo1.dao.LibroDAO;
import com.demo1.dao.LibroxAlumnoDAO;
import com.demo1.dao.UsuarioDAO;
import com.demo1.models.Alumno;
import com.demo1.models.Libro;
import com.demo1.models.LibroporAlumno;
import com.demo1.models.Usuario;
import com.demo1.utils.UsuarioSesion;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author raul
 */
@ManagedBean(name = "pedidoLibroBean")
@SessionScoped
public class PedidoLibroBean implements Serializable {
    
    private int idLibroPorAlumno;
    private int idAlumno;
    private int idLibro;
    private int idUsuario;
    private Alumno alumno;
    private Libro libro;
    private Usuario usuario;
    private Integer contPedidoLibro;
    
    
    public PedidoLibroBean() {
    }
    
    
    public String addPedidoLibro(){
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        LibroDAO libroDAO = new LibroDAO();
        //UsuarioBean usuarioBean = new UsuarioBean();
        LibroxAlumnoDAO libroxAlumnoDAO = new LibroxAlumnoDAO();
        //idUsuario = usuarioBean.getIdUsuario();
        
        alumno = alumnoDAO.findAlumnoByID(idAlumno);
        //usuario = usuarioDAO.findUsuarioByID(idUsuario);
        usuario = UsuarioSesion.UsuarioSesion;
        libro = libroDAO.findLibroByID(idLibro);
        contPedidoLibro = libroxAlumnoDAO.ContadorDeLibroPorAlumno();
        if(contPedidoLibro!= 0){
            idLibroPorAlumno = contPedidoLibro+1;
        }
        else{
            idLibroPorAlumno=1;
        }
        
        LibroporAlumno libroporAlumno = new LibroporAlumno(idLibroPorAlumno, alumno, libro, usuario);
        libroxAlumnoDAO.addLibroxAlumno(libroporAlumno);
        FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Pedido de Libro Registrado"));
        return "pedidoLibro";
    }
    
    public void returnPedidoLibro(){
        LibroxAlumnoDAO libroxAlumnoDAO = new LibroxAlumnoDAO();
        LibroporAlumno libroporAlumno = libroxAlumnoDAO.findLibroxAlumno(idLibroPorAlumno);
        if(libroporAlumno != null){
            setAlumno(libroporAlumno.getAlumno());
            setLibro(libroporAlumno.getLibro());
            setUsuario(libroporAlumno.getUsuario());
        }
        FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Pedido de Libro no encontrado"));
    }
    
    
    public void updatePedidoLibro(){
        LibroporAlumno newlibroporAlumno = new LibroporAlumno(idLibroPorAlumno, alumno, libro, usuario);
        LibroxAlumnoDAO libroxAlumnoDAO = new LibroxAlumnoDAO();
        libroxAlumnoDAO.updateLibroxAlumno(getIdLibroPorAlumno(), newlibroporAlumno);
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage("Pedido de Libro actualizado correctamente"));   
    }
    
    
    public void deletePedidoLibro(){
        LibroxAlumnoDAO libroxAlumnoDAO = new LibroxAlumnoDAO();
        libroxAlumnoDAO.deleteLibroxAlumno(idLibroPorAlumno);
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage("Pedido de Libro eliminado"));
    }

    public Integer getContPedidoLibro() {
        return contPedidoLibro;
    }

    public void setContPedidoLibro(Integer contPedidoLibro) {
        this.contPedidoLibro = contPedidoLibro;
    }
    
    public int getIdLibroPorAlumno() {
        return idLibroPorAlumno;
    }

    public void setIdLibroPorAlumno(int idLibroPorAlumno) {
        this.idLibroPorAlumno = idLibroPorAlumno;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}

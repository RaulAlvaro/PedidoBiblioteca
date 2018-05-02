/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo1.beans;

import com.demo1.dao.UsuarioDAO;
import com.demo1.models.Usuario;
import com.demo1.utils.UsuarioSesion;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author raul
 */
@ManagedBean(name = "usuarioBean")
@SessionScoped
public class UsuarioBean {

    private int idUsuario;
    private String usuario;
    private String password;
    private String nombres;
    private String apellidos;
    public static int idSesion;
    
    public UsuarioBean() {
    }
    
    public void addUsuario(){
        Usuario user =  new Usuario(idUsuario, usuario, password, nombres, apellidos);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.addUsuario(user);
    }
    
    public String ingresoSistema(){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario user = usuarioDAO.findUsuarioByNameAndPassword(usuario,password);
        UsuarioSesion.UsuarioSesion = user;
        if(user != null){
            setIdUsuario(user.getIdUsuario());
            setNombres(user.getNombres());
            setApellidos(user.getApellidos());
            setUsuario(user.getUsuario());
            setPassword(user.getPassword());
            return "pedidoLibro";
        }else{
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Usuario no encontrado"));
            return "index";
        }
    }
    
    public void updateUsuario(){
        Usuario newusuario = new Usuario(getIdUsuario(), getUsuario(), getPassword(), getNombres(), getApellidos());
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.updateUsuario(idUsuario, newusuario);
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage("Usuario actualizado correctamente"));   
    }
    
    
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    
        
}

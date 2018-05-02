/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo1.dao;

import com.demo1.models.Usuario;
import com.demo1.utils.NewHibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author raul
 */
public class UsuarioDAO {
    
    public void addUsuario(Usuario usuario){
        Transaction tx = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try{
            tx = session.beginTransaction();
            session.save(usuario);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally{
            session.flush();
            session.close();
        } 
    }
    
    
    public void deleteUsuario(int idUsuario){
        Transaction trns = null;
         Session session = NewHibernateUtil.getSessionFactory().openSession();
         try{
             trns = session.beginTransaction();
             Usuario usuario = (Usuario) session.load(Usuario.class, new Integer (idUsuario));
             session.delete(usuario);
             session.getTransaction().commit();
         }catch(RuntimeException e){
             trns.rollback();
         } finally{
             session.flush();
             session.close();
         }
    }
    
    public void updateUsuario(int idUsuario, Usuario newusuario){
        Transaction trns = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try{
            trns = session.beginTransaction();
            Usuario oldUsuario = (Usuario) session.load(Usuario.class, idUsuario);
            oldUsuario.setUsuario(newusuario.getUsuario());
            oldUsuario.setPassword(newusuario.getPassword());
            oldUsuario.setNombres(newusuario.getNombres());
            oldUsuario.setApellidos(newusuario.getApellidos());
            session.update(oldUsuario);
            trns.commit();
        } catch(RuntimeException e){
            trns.rollback();
        } finally{
            session.flush();
            session.close();
        }
    
    }
    
    public Usuario findUsuarioByNameAndPassword(String user, String password){
        Usuario usuario = null;
        Transaction trns = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try{
            trns = session.beginTransaction();
            String queryString = "from Usuario where usuario = :idToFind1 and password = :idToFind2";
            Query query = session.createQuery(queryString);
            query.setString("idToFind1", user);
            query.setString("idToFind2", password);
            usuario = (Usuario) query.uniqueResult();
        }catch(RuntimeException e){
            e.printStackTrace();
        }finally{
            session.flush();
            session.close();
        }
        return usuario;
    }
    
    public Usuario findUsuarioByID(int idUsuario){
        Usuario usuario = null;
        Transaction trns = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try{
            trns = session.beginTransaction();
            String queryString = "from Usuario where idUsuario = :idToFind";
            Query query = session.createQuery(queryString);
            query.setInteger("idToFind", idUsuario);
            usuario = (Usuario) query.uniqueResult();
        }catch(RuntimeException e){
            e.printStackTrace();
        }finally{
            session.flush();
            session.close();
        }
        return usuario;
    }
    
    
}

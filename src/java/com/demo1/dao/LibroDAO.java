/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo1.dao;

import com.demo1.models.Libro;
import com.demo1.utils.NewHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author raul
 */
public class LibroDAO {
    public void addLibro(Libro libro){
        Transaction tx = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try{
            tx = session.beginTransaction();
            session.save(libro);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally{
            session.flush();
            session.close();
        }
    }
    
    public void deleteLibro(int idLibro){
        Transaction trns = null;
         Session session = NewHibernateUtil.getSessionFactory().openSession();
         try{
             trns = session.beginTransaction();
             Libro libro = (Libro) session.load(Libro.class, new Integer (idLibro));
             session.delete(libro);
             session.getTransaction().commit();
         }catch(RuntimeException e){
             trns.rollback();
         } finally{
             session.flush();
             session.close();
         }
    }
    
    
    public void updateLibro(int idLibro, Libro newlibro){
        Transaction trns = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try{
            trns = session.beginTransaction();
            Libro oldLibro = (Libro) session.load(Libro.class, idLibro);
            oldLibro.setNombres(newlibro.getNombres());
            oldLibro.setAutor(newlibro.getAutor());
            oldLibro.setEditorial(newlibro.getEditorial());
            oldLibro.setPublicacion(newlibro.getPublicacion());
            session.update(oldLibro);
            trns.commit();
        } catch(RuntimeException e){
            trns.rollback();
        } finally{
            session.flush();
            session.close();
        }
    
    
    }
    
    
    public Libro findLibroByID(int idLibro){
        Libro libro = null;
        Transaction trns = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try{
            trns = session.beginTransaction();
            String queryString = "from Libro where idLibro = :idToFind";
            Query query = session.createQuery(queryString);
            query.setInteger("idToFind", idLibro);
            libro = (Libro) query.uniqueResult();
        }catch(RuntimeException e){
            e.printStackTrace();
        }finally{
            session.flush();
            session.close();
        }
        return libro;
    }
    
    public List<Libro> listaLibroTodos(){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        String hql = " from Libro";        
        List<Libro> listaLibro= new ArrayList<>();
        try{
            Query query = session.createQuery(hql);
            listaLibro= (List<Libro>) query.list();
            
        }catch(RuntimeException e){
            e.printStackTrace();
        }finally{
            session.flush();
            session.close();
        }
        return listaLibro;
    }
    
    
    
}

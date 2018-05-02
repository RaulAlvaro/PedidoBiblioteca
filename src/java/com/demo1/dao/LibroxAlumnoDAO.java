/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo1.dao;

import com.demo1.models.LibroporAlumno;
import com.demo1.utils.NewHibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author raul
 */
public class LibroxAlumnoDAO {
    public void addLibroxAlumno(LibroporAlumno libroporAlumno){
        Transaction tx = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.save(libroporAlumno);
            session.getTransaction().commit();
        } catch (Exception e) {
             e.printStackTrace();
            tx.rollback();
        }finally{
            session.flush();
            session.close();
        }
    }
    
    public void deleteLibroxAlumno(int idLibroxAlumno){
        Transaction trns = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try{
             trns = session.beginTransaction();
             LibroporAlumno libroporAlumno = (LibroporAlumno) session.load(LibroporAlumno.class, new Integer (idLibroxAlumno));
             session.delete(libroporAlumno);
             session.getTransaction().commit();
         }catch(RuntimeException e){
             trns.rollback();
         } finally{
             session.flush();
             session.close();
         }
    }
    
    
    public void updateLibroxAlumno(int idLibroxAlumno, LibroporAlumno newLibroporAlumno){
        Transaction trns = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try{
            trns = session.beginTransaction();
            LibroporAlumno libroporAlumno = (LibroporAlumno) session.load(LibroporAlumno.class, idLibroxAlumno);
            //Alumno oldAlumno = (Alumno) session.load(Alumno.class, idAlumno);
            libroporAlumno.setAlumno(newLibroporAlumno.getAlumno());
            libroporAlumno.setLibro(newLibroporAlumno.getLibro());
            session.update(libroporAlumno);
            trns.commit();
        } catch(RuntimeException e){
            trns.rollback();
        } finally{
            session.flush();
            session.close();
        }
    }
    
    public LibroporAlumno findLibroxAlumno(int idLibroxAlumno){
        LibroporAlumno libroporAlumno = null;
        Transaction trns = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try{
            trns = session.beginTransaction();
            String queryString = "from LibroporAlumno where codigo = :idToFind";
            Query query = session.createQuery(queryString);
            query.setInteger("idToFind", idLibroxAlumno);
            libroporAlumno = (LibroporAlumno) query.uniqueResult();
        }catch(RuntimeException e){
            e.printStackTrace();
        }finally{
            session.flush();
            session.close();
        }
        return libroporAlumno;    
    }
    
    public Integer ContadorDeLibroPorAlumno(){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        String hql = "select count(*) from LibroporAlumno";
        Query query = session.createQuery(hql);
        Long FilasTab=(Long) query.uniqueResult();
        Integer cont=FilasTab.intValue();
        return cont;
    }
    
}

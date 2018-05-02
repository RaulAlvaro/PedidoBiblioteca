/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo1.dao;

import com.demo1.models.Alumno;
import com.demo1.utils.NewHibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author raul
 */
public class AlumnoDAO {
    public void addAlumno(Alumno alumno){
        Transaction tx = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try{
            tx = session.beginTransaction();
            session.save(alumno);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally{
            session.flush();
            session.close();
        } 
   }
    
    public void deleteAlumno(int idAlumno){
         Transaction trns = null;
         Session session = NewHibernateUtil.getSessionFactory().openSession();
         try{
             trns = session.beginTransaction();
             Alumno alumno = (Alumno) session.load(Alumno.class, new Integer (idAlumno));
             session.delete(alumno);
             session.getTransaction().commit();
         }catch(RuntimeException e){
             trns.rollback();
         } finally{
             session.flush();
             session.close();
         }
    }
    
    public void updateAlumno(int idAlumno, Alumno newAlumno){
        Transaction trns = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try{
            trns = session.beginTransaction();
            Alumno oldAlumno = (Alumno) session.load(Alumno.class, idAlumno);
            
            System.out.println(oldAlumno.getCodigo());
            System.out.println(oldAlumno.getNombres());
            System.out.println(oldAlumno.getApellidos());
            
            
            oldAlumno.setCodigo(newAlumno.getCodigo());
            oldAlumno.setNombres(newAlumno.getNombres());
            oldAlumno.setApellidos(newAlumno.getApellidos());
            oldAlumno.setEscuela(newAlumno.getEscuela());
            oldAlumno.setDni(newAlumno.getDni());
            oldAlumno.setCorreo(newAlumno.getCorreo());
            session.update(oldAlumno);
            trns.commit();
        } catch(RuntimeException e){
            trns.rollback();
        } finally{
            session.flush();
            session.close();
        }
    }
    
    public Alumno findAlumnoByID(int idAlumno){
        Alumno alumno = null;
        Transaction trns = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try{
            trns = session.beginTransaction();
            String queryString = "from Alumno where codigo = :idToFind";
            Query query = session.createQuery(queryString);
            query.setInteger("idToFind", idAlumno);
            alumno = (Alumno) query.uniqueResult();
        }catch(RuntimeException e){
            e.printStackTrace();
        }finally{
            session.flush();
            session.close();
        }
        return alumno;
    }
}

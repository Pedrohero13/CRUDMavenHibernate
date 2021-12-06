/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Modelo;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author pedro
 */
public class DAOEmpleado implements DaoGeneral<Empleado> {

    @Override
    public boolean guardar(Empleado obj) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        session.beginTransaction();
        session.save(obj);
        
        
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean modificar(Empleado obj) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
     
        session.update(obj);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean Eliminar(String clave) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        Empleado emp = (Empleado)session.get(Empleado.class, clave);
        
        session.delete(emp);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public List<Empleado> ConsultarTodo() {
        List<Empleado> listEmp;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        session.beginTransaction();
        listEmp = (List) session.createQuery(
                "FROM Empleado").list();
        return listEmp;
       
    }

    @Override
    public Empleado buscarBYID(String clave) {
        Empleado p;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        p = (Empleado)session.get(Empleado.class, clave);
        
        return p;
    }

}

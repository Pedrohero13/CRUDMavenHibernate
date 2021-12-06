/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author pedro
 */
public class DAODepartamento implements DaoGeneral<Departamento> {

    

    @Override
    public boolean guardar(Departamento obj) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        session.beginTransaction();
        session.save(obj);
        
        
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean modificar(Departamento obj) {
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
    public List<Departamento> ConsultarTodo() {
        List<Departamento> listEmp;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        session.beginTransaction();
        listEmp = (List) session.createQuery(
                "FROM Empleado").list();
        return listEmp;
    }

    @Override
    public Departamento buscarBYID(String clave) {
        Departamento p;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        p = (Departamento)session.get(Departamento.class, clave);
        
        return p;
    }
    
}

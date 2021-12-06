/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Modelo;

/**
 *
 * @author pedro
 */
public class FactoryDAO {
     public enum DAOType{EMPLEADO,DEPARTAMENTO};
     public static DaoGeneral create(DAOType type){
         DaoGeneral dao=null;
         switch(type){
             case EMPLEADO :
                 dao = new DAOEmpleado();
                 break;
        case DEPARTAMENTO :
                 dao = new DAODepartamento();
                 break;
         }
        
     return dao;
}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Controlador;

import org.Vista.FormularioGUI;



/**
 *
 * @author pedro
 */
public class FactoryGUI {
    public enum TypeGUI{EMPLEADO,DEPARTAMENTO}
    public static FormularioGUI create(TypeGUI type){
        FormularioGUI gui =null;
        
        switch (type){
            case EMPLEADO:
                gui = new EmpleadoGUI();
                gui.setVisible(true);
                break;
            case DEPARTAMENTO:
                gui = new DepartamentoGUI();
                gui.setVisible(true);
                break;
        }
        return gui;
                
    }
}

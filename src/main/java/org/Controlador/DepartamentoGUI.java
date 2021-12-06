/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Controlador;


import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.Modelo.DaoGeneral;
import org.Modelo.Departamento;
import org.Modelo.FactoryDAO;
import org.Vista.FormularioGUI;

/**
 *
 * @author pedro
 */
public class DepartamentoGUI extends FormularioGUI{

    public DepartamentoGUI() {
        this.setTitle("Departamentos");
        this.txtDireccion.setVisible(false);
        this.txtTelefono.setVisible(false);
        this.lb3.setVisible(false);
        this.lb4.setVisible(false);
        this.jLabel1.setVisible(false);
        this.jComboBox1.setVisible(false);
        this.txtNombreDepa.setVisible(false);
        this.consultar();
    }

    //sirve para gardar el departamento.
    @Override
    public void guardar() {
        DaoGeneral<Departamento> dao = FactoryDAO.create(FactoryDAO.DAOType.DEPARTAMENTO);
        Departamento depa = new Departamento();
        depa.setClave(txtClave.getText());
        depa.setNombre(txtNombre.getText());
        
        boolean re =dao.guardar(depa);
       if(re)
           JOptionPane.showMessageDialog(this, "Guardado");
       else
           JOptionPane.showMessageDialog(this, "ERROR");
       this.consultar();
    }

    @Override
    public void modificar() {
        String clave = (String)tablaGUI.getValueAt(tablaGUI.getSelectedRow(), tablaGUI.getSelectedColumn());
        
        Departamento e = new Departamento();
        e.setClave(txtClave.getText());
        e.setNombre(txtNombre.getText());
      
        
        DaoGeneral <Departamento> dao= FactoryDAO.create(FactoryDAO.DAOType.DEPARTAMENTO);
        boolean re = dao.modificar(e);
        if(re)
           JOptionPane.showMessageDialog(this, "Modificado");
       else
           JOptionPane.showMessageDialog(this, "ERROR");
        
        this.consultar();
    }

    @Override
    public void eliminar() {
         String clave = (String)tablaGUI.getValueAt(tablaGUI.getSelectedRow(), 0);
        
        DaoGeneral<Departamento> dao = FactoryDAO.create(FactoryDAO.DAOType.DEPARTAMENTO);
        dao.Eliminar(clave);
        this.consultar();
    }

    @Override
    public void consultar() {
        Vector<String> columnas = new Vector<>();
        columnas.add("clave");
        columnas.add("nombre");
        
       

        Vector datos = new Vector();
        DaoGeneral<Departamento> dao = FactoryDAO.create(FactoryDAO.DAOType.DEPARTAMENTO);
        List<Departamento> lstdepa = dao.ConsultarTodo();
        for (Departamento pojodepa : lstdepa) {
            Vector row = new Vector();
            row.add(pojodepa.getClave());
            row.add(pojodepa.getNombre());
           
                     
            datos.add(row);
        }

        DefaultTableModel model = new DefaultTableModel(datos, columnas);
        tablaGUI.setModel(model);  
    }

    @Override
    public void buscarPorClave() {
        DaoGeneral<Departamento> daodepa = FactoryDAO.create(FactoryDAO.DAOType.DEPARTAMENTO);
        Departamento depa = daodepa.buscarBYID(txtClave.getText());
        txtNombre.setText(depa.getNombre());
       
    }
    
}

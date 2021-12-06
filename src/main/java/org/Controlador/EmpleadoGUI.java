/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Controlador;


import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.Modelo.DaoGeneral;
import org.Modelo.Departamento;
import org.Modelo.Empleado;
import org.Modelo.FactoryDAO;
import org.Vista.FormularioGUI;

/**
 *
 * @author pedro
 */
public class EmpleadoGUI extends FormularioGUI {

    public EmpleadoGUI() {
        this.setTitle("EMPLEADOS");
        this.llenarCombo();
        this.consultar();

    }

    // FUNCIONES DE BOTONES
    @Override
    public void guardar() {
        DaoGeneral<Empleado> dao = FactoryDAO.create(FactoryDAO.DAOType.EMPLEADO);
        Empleado e = new Empleado();
        e.setClave(txtClave.getText());
        e.setNombre(txtNombre.getText());
        e.setDireccion(txtDireccion.getText());
        e.setTelefono(txtTelefono.getText());
        e.setClaveDepartamento(new Departamento(jComboBox1.getSelectedItem().toString()));
        boolean re = dao.guardar(e);
        if (re) {
            JOptionPane.showMessageDialog(this, "Guardado");
        } else {
            JOptionPane.showMessageDialog(this, "ERROR");
        }
        this.consultar();
    }

    @Override
    public void modificar() {

        

        Empleado e = new Empleado();
        e.setClave(txtClave.getText());
        e.setNombre(txtNombre.getText());
        e.setDireccion(txtDireccion.getText());
        e.setTelefono(txtTelefono.getText());
        e.setClaveDepartamento(new Departamento(jComboBox1.getSelectedItem().toString()));

        DaoGeneral<Empleado> dao = FactoryDAO.create(FactoryDAO.DAOType.EMPLEADO);
        dao.modificar(e);
        this.consultar();
    }

    @Override
    public void eliminar() {
        String clave = (String) tablaGUI.getValueAt(tablaGUI.getSelectedRow(), 0);

        DaoGeneral<Empleado> dao = FactoryDAO.create(FactoryDAO.DAOType.EMPLEADO);
        dao.Eliminar(clave);
        this.consultar();
    }

    @Override
    public void consultar() {
        Vector<String> columnas = new Vector<>();
        columnas.add("clave");
        columnas.add("nombre");
        columnas.add("direccion");
        columnas.add("telefono");
        columnas.add("clave_depa");

        Vector datos = new Vector();
        DaoGeneral<Empleado> dao = FactoryDAO.create(FactoryDAO.DAOType.EMPLEADO);
        List<Empleado> lstEmpleados = dao.ConsultarTodo();
        for (Empleado pojoempleado : lstEmpleados) {
            Vector row = new Vector();
            row.add(pojoempleado.getClave());
            row.add(pojoempleado.getNombre());
            row.add(pojoempleado.getDireccion());
            row.add(pojoempleado.getTelefono());
            row.add(pojoempleado.getClaveDepartamento().getNombre());
            datos.add(row);
        }

        DefaultTableModel model = new DefaultTableModel(datos, columnas);
        tablaGUI.setModel(model);
    }

    

    @Override
    public void buscarPorClave() {
         DaoGeneral<Empleado> dao = FactoryDAO.create(FactoryDAO.DAOType.EMPLEADO);
        Empleado emp = dao.buscarBYID(txtClave.getText());
        
        txtNombre.setText(emp.getNombre());
        txtDireccion.setText(emp.getDireccion());
        txtTelefono.setText(emp.getTelefono());
        
        DaoGeneral<Departamento> daodepa = FactoryDAO.create(FactoryDAO.DAOType.DEPARTAMENTO);
        Departamento depa = daodepa.buscarBYID(emp.getClaveDepartamento().getClave());
        txtNombreDepa.setText(depa.getNombre());
    }
    
    public void llenarCombo() {
        DaoGeneral<Departamento> dao = FactoryDAO.create(FactoryDAO.DAOType.DEPARTAMENTO);
        List<Departamento> listDepa = dao.ConsultarTodo();
        for (Departamento depa : listDepa) {
            jComboBox1.addItem(depa.getClave());

        }
    }
     
}

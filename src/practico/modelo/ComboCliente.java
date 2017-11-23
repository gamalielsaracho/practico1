/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package practico.modelo;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ComboCliente {
     private int id_cliente;
     private String nombre;
     private String apellido;  
     

    public ComboCliente( int id_cliente, String nombre, String apellido) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
      
    }

    public ComboCliente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setId_cliente(String valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}

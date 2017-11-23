/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import practico.conexion.Conexion;
import practico.modelo.Cliente;

/**
 *
 * @author Eli
 */
public class ClienteDAO {
    private Connection connection;
    
    public ClienteDAO() {
        connection = Conexion.getConnection();
    }
    
    public String verificarCliente(Cliente cliente) {
        
        try {
            String m;
            PreparedStatement ps = connection.prepareStatement("select * from clientes where id_cliente = ?");
            ps.setInt(1, cliente.getId_cliente());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) // encontrado
            {
                m=actualizarCliente(cliente);
            } else {
                
                m=agregarCliente(cliente);
            }
            return m;
        } catch (Exception ex) {
            System.out.println("Error in check() -->" + ex.getMessage());
            return "ERROR VERIFICAR";
        } 
    }
    
    public String agregarCliente(Cliente cliente) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into clientes(nombre, apellido, sexo, edad) values (?, ?, ?, ?)");

            preparedStatement.setString(1,cliente.getNombre());
            preparedStatement.setString(2,cliente.getApellido());
            preparedStatement.setString(3, cliente.getSexo());
            preparedStatement.setInt(4,cliente.getEdad());

            preparedStatement.executeUpdate();
            return "AGREGADO";
            
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
            return "NO AGREGADO";
        }
    }
    
    public void eliminarCliente(String idCliente){
        try {
            PreparedStatement ps = connection.prepareStatement("delete from clientes where id_cliente = ?");
            
            ps.setString(1, idCliente);
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String actualizarCliente(Cliente cliente){
        try {
            PreparedStatement ps = connection.prepareStatement("update clientes set nombre=?, apellido=?, sexo=?, edad=? WHERE id_cliente=? ");
            
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getSexo());
            ps.setInt(4, cliente.getEdad());
            ps.setInt(5, cliente.getId_cliente()); //esto esta de prueba por ahora
            
            ps.executeUpdate();
            return "ACTUALIZADO";
            
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
            return "NO ACTUALIZADO";
        }
    }
    
    public List<Cliente> listarClientes(){
        List<Cliente> users = new ArrayList<Cliente>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from clientes");
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setSexo(rs.getString("sexo"));;
                cliente.setEdad(rs.getInt("edad"));
                
                users.add(cliente);
            }
            
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return users;
    }
    
    public Cliente buscarCliente(String id_cliente){
        Cliente cliente = new Cliente();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from clientes where id_cliente = ?");
            ps.setString(1, id_cliente);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setEdad(rs.getInt("edad"));

            }
            
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        
        return cliente;
    }
    
}

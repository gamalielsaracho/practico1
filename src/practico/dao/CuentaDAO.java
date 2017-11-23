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
import practico.conexion.Conexion;
import practico.modelo.Cliente;
import practico.modelo.Cuenta;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */

public class CuentaDAO {
    private Connection connection;
    
    public CuentaDAO() {
        connection = Conexion.getConnection();
    }
    
    public String verificarCuenta(Cuenta cuenta) {
        
        try {
            String m;
            PreparedStatement ps = connection.prepareStatement("select * from cuentas where id_cuenta = ? AND id_banco = ? AND id_cliente = ?");
            ps.setInt(1, cuenta.getId_cuenta());
            ps.setInt(2, cuenta.getId_banco());
            ps.setInt(3, cuenta.getId_cliente());
            //ps.setInt(1, cliente.getId_cliente());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) // encontrado
            {
                m=actualizarCuenta(cuenta);
            } else {
                
                m=agregarCuenta(cuenta);
            }
            return m;
        } catch (SQLException ex) {
            System.out.println("Error in check() -->" + ex.getMessage());
            return "ERROR VERIFICAR";
        } 
    }
    
    public String agregarCuenta(Cuenta cuenta) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into cuentas(id_cuenta, id_banco, id_cliente, fechaCreacion, saldo, sobreHabilitado) values (null, ?, ?, now(), 0, ?)");

            preparedStatement.setInt(1, cuenta.getId_banco());
            preparedStatement.setInt(2, cuenta.getId_cliente());
            // preparedStatement.setDate(3, cuenta.getFechaUmovimiento());
            // preparedStatement.setInt(3, cuenta.getSaldo());
            preparedStatement.setInt(3, cuenta.getSobreHabilitado());

            preparedStatement.executeUpdate();
            return "AGREGADO";
            
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
            return "NO AGREGADO";
        }
    }
    
    
    public void eliminarCuenta(String idBanco , String idCliente ){
        try {
            PreparedStatement ps = connection.prepareStatement("delete from cuentas where id_banco = ? AND id_cliente = ?");
            
            ps.setString(1, idBanco);
            ps.setString(2, idCliente);
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public String actualizarCuenta(Cuenta cuenta){
        try {
            PreparedStatement ps = connection.prepareStatement("update cuentas set  sobreHabilitado = ? WHERE id_cuenta = ?");
                    
           
            ps.setInt(1, cuenta.getSobreHabilitado());
            ps.setInt(2, cuenta.getId_cuenta());
           
            
            ps.executeUpdate();
            return "ACTUALIZADO";
            
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
            return "NO ACTUALIZADO";
        }
    }
    
    public List<Cuenta> listarCuentas(){
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from cuentas cu, clientes cli, bancos b "
                    + "where cu.id_cliente = cli.id_cliente AND "
                    + "cu.id_banco = b.id_banco");
            while (rs.next()) {
                
                
                
                Cuenta cuenta = new Cuenta();
               
               
                // System.out.println("HOLA ---> "+ rs.getInt("cli.nombre"));
                
                
                cuenta.setId_cuenta(rs.getInt("cu.id_cuenta"));
                cuenta.setId_banco(rs.getInt("cu.id_banco"));
                cuenta.setNombreBanco(rs.getString("b.nombre"));
                
                cuenta.setId_cliente(rs.getInt("cu.id_cliente"));
                cuenta.setNombreCliente(rs.getString("cli.nombre"));
                cuenta.setApellidoCliente(rs.getString("cli.apellido"));
                
                cuenta.setFechaCreacion(rs.getDate("fechaCreacion"));
                cuenta.setFechaUmovimiento(rs.getDate("fechaUmovimiento"));;
                cuenta.setSaldo(rs.getInt("saldo"));
                cuenta.setSobreHabilitado(rs.getInt("sobreHabilitado"));

                
                cuentas.add(cuenta);
               
            }
            
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        return cuentas;
    }
}

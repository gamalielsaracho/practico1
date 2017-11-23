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
import practico.modelo.Banco;

/**
 *
 * @author Eli
 */
public class BancoDAO {
    private Connection connection;
    
    public BancoDAO() {
        connection = Conexion.getConnection();
    }
    
    public String verificarBanco(Banco banco) {
        
        try {
            String m;
            PreparedStatement ps = connection.prepareStatement("select * from bancos where id_banco= ?");
            ps.setInt(1, banco.getId_banco());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) // encontrado
            {
                m=actualizarBanco(banco);
            } else {
                
                m=agregarBanco(banco);
            }
            return m;
        } catch (Exception ex) {
            System.out.println("Error in check() -->" + ex.getMessage());
            return "ERROR VERIFICAR";
        } 
    }
    
    public String agregarBanco(Banco banco) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into bancos(nombre) values (?)");

            preparedStatement.setString(1,banco.getNombre());

            preparedStatement.executeUpdate();
            return "AGREGADO";
            
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
            return "NO AGREGADO";
        }
    }
    
    public void eliminarBanco(String idBanco){
        try {
            PreparedStatement ps = connection.prepareStatement("delete from bancos where id_banco= ?");
            
            ps.setString(1, idBanco);
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String actualizarBanco(Banco banco){
        try {
            PreparedStatement ps = connection.prepareStatement("update bancos set nombre=? WHERE id_banco=? ");
            
            ps.setString(1, banco.getNombre());
            ps.setInt(2, banco.getId_banco()); //esto esta de prueba por ahora
            
            ps.executeUpdate();
            return "ACTUALIZADO";
            
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
            return "NO ACTUALIZADO";
        }
    }
    
    public List<Banco> listarBancos(){
        List<Banco> users = new ArrayList<Banco>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from bancos");
            while (rs.next()) {
                Banco banco = new Banco();
                banco.setId_banco(rs.getInt("id_banco"));
                banco.setNombre(rs.getString("nombre"));
                
                users.add(banco);
            }
            
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return users;
    }
    
    public Banco buscarBanco(String id_banco){
        Banco banco = new Banco();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from banco where id_banco= ?");
            ps.setString(1, id_banco);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                banco.setId_banco(rs.getInt("id_banco"));
                banco.setNombre(rs.getString("nombre"));
            }
            
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        
        return banco;
    }
}

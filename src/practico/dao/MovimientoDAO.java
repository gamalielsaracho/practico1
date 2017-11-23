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
import practico.modelo.Movimiento;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class MovimientoDAO {
    private Connection connection;
    
    public MovimientoDAO() {
        connection = Conexion.getConnection();
    }
    
    public String verificarMovimiento(Movimiento movimiento) {
        
        try {
            String m;
            PreparedStatement ps = connection.prepareStatement("select * from movimientos "
                    + "WHERE id_movimiento = ?");
            
            ps.setInt(1, movimiento.getId_movimiento());
            
            // ps.setInt(2, movimiento.getId_banco());
            // ps.setInt(3, movimiento.getId_cliente());
            
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) // encontrado
            {
                m=actualizarMovimiento(movimiento);
            } else {
                
                m=agregarMovimiento(movimiento);
            }
            return m;
        } catch (SQLException ex) {
            System.out.println("Error in check() -->" + ex.getMessage());
            return "ERROR VERIFICAR";
        } 
    }
    
    public String agregarMovimiento(Movimiento movimiento) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into movimientos("
                    + "id_movimiento, fechaMovimiento, monto, tipo, id_cuenta, id_banco, id_cliente) "
                    + "values (null, now(), ?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, movimiento.getMonto());
            preparedStatement.setString(2, movimiento.getTipo());
            
            preparedStatement.setInt(3, movimiento.getId_cuenta());
            preparedStatement.setInt(4, movimiento.getId_banco());
            preparedStatement.setInt(5, movimiento.getId_cliente());

            preparedStatement.executeUpdate();
            return "AGREGADO";
            
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
            return "NO AGREGADO";
        }
    }
    
    // ELIMINAR...............................................
    
    
    public String actualizarMovimiento(Movimiento movimiento){
        try {
            PreparedStatement ps = connection.prepareStatement("update movimientos SET  "
                    + "monto = ?, tipo = ?,  fechaMovimiento = now() "
                    + "WHERE id_movimiento = ?");
                    
            ps.setInt(1, movimiento.getMonto());
            ps.setString(2, movimiento.getTipo());
            
            ps.setInt(3, movimiento.getId_movimiento());
            
            // ps.setInt(4, movimiento.getId_banco());
            // ps.setInt(5, movimiento.getId_cliente());
            
            ps.executeUpdate();
            return "ACTUALIZADO";
            
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
            return "NO ACTUALIZADO";
        }
    }
    
    
    public List<Movimiento> listarMovimientosPorIdCuenta(int idCuenta){
        List<Movimiento> movimientos = new ArrayList<Movimiento>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from movimientos "
                    + "where id_cuenta = "+idCuenta);
            
            while (rs.next()) {
                
                Movimiento movimiento = new Movimiento();
                
                // System.out.println("HOLA ---> "+ rs.getInt("cli.nombre"));
                
               movimiento.setId_movimiento(rs.getInt("id_movimiento"));
               movimiento.setMonto(rs.getInt("monto"));
               movimiento.setTipo(rs.getString("tipo"));
               movimiento.setFechaMovimiento(rs.getDate("fechaMovimiento"));
                
                movimientos.add(movimiento);
               
            }
            
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        
        return movimientos;
    }
    
    
}

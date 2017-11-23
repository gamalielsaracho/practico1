/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package practico.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import practico.conexion.Conexion;
import practico.modelo.ComboCliente;


/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ComboClienteDAO {
    private Connection connection;
    
    public ComboClienteDAO() {
        connection = Conexion.getConnection();
    }
    
    
}

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
public class ComboBanco {
    private int id_banco;
    private String nombre; 

     public ComboBanco(int id_banco, String nombre) {
           this.id_banco = id_banco;
           this.nombre = nombre;
    }
    
    public int getId_banco() {
        return id_banco;
    }

    public void setId_banco(int id_banco) {
        this.id_banco = id_banco;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}

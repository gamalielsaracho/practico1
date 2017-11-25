/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package practico.modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Cuenta {
    Integer id_cuenta;
    Integer id_banco;
    Integer id_cliente;
    Date fechaCreacion;
    Date fechaUmovimiento;
    Integer saldo;
    Integer sobreHabilitado;

    String nombreCliente;
    String apellidoCliente;
    String sexo;
    int edad;
    
    String nombreBanco;
    
    List<Movimiento> movimientos = new ArrayList<>();

    public Integer getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(Integer id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public Integer getId_banco() {
        return id_banco;
    }

    public void setId_banco(Integer id_banco) {
        this.id_banco = id_banco;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaUmovimiento() {
        return fechaUmovimiento;
    }

    public void setFechaUmovimiento(Date fechaUmovimiento) {
        this.fechaUmovimiento = fechaUmovimiento;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public Integer getSobreHabilitado() {
        return sobreHabilitado;
    }

    public void setSobreHabilitado(Integer sobreHabilitado) {
        this.sobreHabilitado = sobreHabilitado;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }
}

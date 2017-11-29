/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package practico1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import practico.conexion.Conexion;
import practico.dao.BancoDAO;
import practico.dao.ClienteDAO;
import practico.dao.CuentaDAO;
import practico.dao.MovimientoDAO;
import practico.modelo.Banco;
import practico.modelo.Cliente;
import practico.modelo.ComboBanco;
import practico.modelo.ComboCliente;
import practico.modelo.Cuenta;
import practico.modelo.Movimiento;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class FormularioCuenta extends javax.swing.JFrame {
     private BancoDAO daoBanco;
     private ClienteDAO daoCliente;
    private CuentaDAO dao;
    private MovimientoDAO daoMovimiento;
    
    FormularioMovimiento  oMovimientosByIdCuentaModal = new FormularioMovimiento();
    DetalleCuenta  oDetalleCuenta = new DetalleCuenta();
    
    /**
     * Creates new form FormularioCuenta
     */
    // private Connection connection;
    public  Vector<ComboCliente>  ListaDeObjetosParaComboBoxCliente() {     
          Connection connection;
          connection = Conexion.getConnection();
           
                try 
                    {     
                          ResultSet resultado;
                          Statement sentencia;                          
                          sentencia=connection.createStatement();
                          resultado=sentencia.executeQuery("SELECT * FROM clientes");
                                            
                         Vector<ComboCliente> data=new Vector<ComboCliente>();                         
                         
    
                           if(resultado.next())
                             {   
                             do 
                               { 
                                   
                                 ComboCliente oListaTemporal = new ComboCliente(resultado.getInt(1), resultado.getString(2),  resultado.getString(3));
                        	//System.out.println("oListaTemporal "+resultado.getInt(1));     
                                
                                 data.addElement(oListaTemporal);
                                 
                               }
                            while(resultado.next()); 
                            return data;
                           
                            }
                          else
                        	{ 
                            return null;
                          }            
                    }  
               catch(Exception ex)
                {
                   System.out.print(ex);
                    return null;
                }   
            }
     
    
     public  Vector<ComboBanco>  ListaDeObjetosParaComboBoxBanco() {     
          Connection connection;
          connection = Conexion.getConnection();
           
                try 
                    {     
                          ResultSet resultado;
                          Statement sentencia;                          
                          sentencia=connection.createStatement();
                          resultado=sentencia.executeQuery("SELECT * FROM bancos");
                                            
                         Vector<ComboBanco> data=new Vector<ComboBanco>();                         
                         
    
                           if(resultado.next())
                             {   
                             do 
                               { 
                                   
                                 ComboBanco oListaTemporal = new ComboBanco(resultado.getInt(1), resultado.getString(2));
                        	//System.out.println("oListaTemporal "+resultado.getInt(1));     
                                
                                 data.addElement(oListaTemporal);
                                 
                               }
                            while(resultado.next()); 
                            return data;
                           
                            }
                          else
                        	{ 
                            return null;
                          }            
                    }  
               catch(Exception ex)
                {
                   System.out.print(ex);
                    return null;
                }   
            }
     
    public FormularioCuenta() {
        initComponents();
        
        // Ocultar el input que tiene el id del movimiento Para editar únicamente si hace falta.
        this.movimientoIdInput.setVisible(false);
        
        
        this.idCuentaInput.setVisible(false);
        this.idClienteInput.setVisible(false);
        this.idBancoInput.setVisible(false);
       
        daoMovimiento = new MovimientoDAO();
        dao = new CuentaDAO();
        daoBanco = new BancoDAO();
        daoCliente = new ClienteDAO();
         ListarBancosEnTabla();
         ListarClientesEnTabla();
         ListarCuentasEnTabla();
        // connection = Conexion.getConnection();
        
        
         // CLIENTE.
         clientesCombo.removeAllItems();
         Vector<ComboCliente> oLista = ListaDeObjetosParaComboBoxCliente();
           
           
         for(int i=0;i<oLista.size();i=i+1)
            { 
              // int id_cliente, String nombre, String apellido, String sexo, int edad
              ComboCliente oItem = new ComboCliente(oLista.get(i).getId_cliente(), oLista.get(i).getNombre(), oLista.get(i).getApellido());

               // System.out.println("oLista --> "+ oLista.get(i).getId_cliente());
              System.out.println(oItem);
              clientesCombo.addItem(oItem.getId_cliente()+" "+oItem.getApellido()+" "+oItem.getNombre());
              
              // clientesCombo.addItem(); 
           }
         
         
         // BANCO.
         bancosCombo.removeAllItems();
         Vector<ComboBanco> oListaBancos = ListaDeObjetosParaComboBoxBanco();
         
         for(int i=0;i<oListaBancos.size();i=i+1)
            { 
              // int id_cliente, String nombre, String apellido, String sexo, int edad
              ComboBanco oItemB = new ComboBanco(oListaBancos.get(i).getId_banco(), oListaBancos.get(i).getNombre());

               // System.out.println("oListaBancos --> "+ oListaBancos.get(i).getId_banco());
              System.out.println(oItemB);
              bancosCombo.addItem(oItemB.getId_banco()+"  "+oItemB.getNombre());
              // clientesCombo.addItem(); 

           }
    }

    
     
    
    
     public  void  ListarBancosEnTabla() {
        DefaultTableModel modelo = (DefaultTableModel)  tablaBanco.getModel(); 
        modelo.setRowCount(0);
        
        List<Banco> users = new ArrayList<Banco>();
        users = daoBanco.listarBancos();
        
        for (int i = 0; i < users.size(); i = i+1) {   
            Banco b = new Banco();
            b = users.get(i);
            
            modelo.addRow( new Object[]{ b.getId_banco(), b.getNombre() } );
            
       }
     }
    
     public  void  ListarClientesEnTabla() {
         DefaultTableModel modelo = (DefaultTableModel)  tablaCliente.getModel(); 
        modelo.setRowCount(0);
        
        List<Cliente> users = new ArrayList<Cliente>();
        users = daoCliente.listarClientes();
        
        for (int i = 0; i < users.size(); i = i+1)
        {   
            Cliente c= new Cliente();
            c=users.get(i);
            
            modelo.addRow( new Object[]{ c.getId_cliente(), c.getNombre(), c.getApellido() } );
            
       }
     }
    
     
     public  void  ListarCuentasEnTabla() {
        DefaultTableModel modelo = (DefaultTableModel)  tablaCuenta.getModel(); 
        modelo.setRowCount(0);
        
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas = dao.listarCuentas();
        
        for (int i = 0; i < cuentas.size(); i = i+1)
        {   
            Cuenta cu= new Cuenta();
            cu=cuentas.get(i);
            
            modelo.addRow( new Object[]{
                    cu.getId_cuenta(), cu.getNombreCliente()+" "+cu.getApellidoCliente(), 
                    cu.getNombreBanco(), cu.getFechaCreacion(), 
                    cu.getFechaUmovimiento(), cu.getSaldo(), cu.getSobreHabilitado() });
       }
     }
     
     
     public  void  listarMovimientosPorIdCuentaEnTabla(int idCuenta) {
         DefaultTableModel modelo = (DefaultTableModel)  tablaMovimiento.getModel(); 
        modelo.setRowCount(0);
        
        List<Movimiento> movimientos = new ArrayList<Movimiento>();
        movimientos = daoMovimiento.listarMovimientosPorIdCuenta(idCuenta);
        
        for (int i = 0; i < movimientos.size(); i = i+1)
        {   
            Movimiento mov= new Movimiento();
            mov=movimientos.get(i);
            
            modelo.addRow( new Object[]{
                    mov.getId_movimiento(), mov.getMonto(),
                    mov.getTipo(), mov.getFechaMovimiento()});
       }
     }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        clientesCombo = new javax.swing.JComboBox();
        bancosCombo = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        agregar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        idClienteInput = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCuenta = new javax.swing.JTable();
        sobreHabilitadoInput = new javax.swing.JTextField();
        idBancoInput = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCliente = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaBanco = new javax.swing.JTable();
        reporte = new javax.swing.JButton();
        idCuentaInput = new javax.swing.JTextField();
        actualizarCuentas = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaMovimiento = new javax.swing.JTable();
        montoInput = new javax.swing.JTextField();
        tiposCombo = new javax.swing.JComboBox();
        agregarMovimientoBtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        movimientoIdInput = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        clientesCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientesComboActionPerformed(evt);
            }
        });

        jLabel1.setText("Clientes");

        jLabel2.setText("Bancos");

        agregar.setText("Agregar");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        jLabel3.setText("sobre Habilitado");

        idClienteInput.setEnabled(false);

        tablaCuenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "código cuenta", "Nombre", "Banco", "Fecha Creacion", "Fecha U.movimiento", "Saldo", "Sobre habilitado"
            }
        ));
        tablaCuenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCuentaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaCuenta);

        idBancoInput.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Cuentas");

        jLabel5.setText("Clientes");

        jLabel6.setText("Bancos");

        tablaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "código", "Nombre", "Apellido"
            }
        ));
        jScrollPane2.setViewportView(tablaCliente);

        tablaBanco.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "código", "Nombre"
            }
        ));
        jScrollPane3.setViewportView(tablaBanco);

        reporte.setText("Reporte");
        reporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporteActionPerformed(evt);
            }
        });

        idCuentaInput.setEnabled(false);

        actualizarCuentas.setText("Mostrar movimientos");
        actualizarCuentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarCuentasActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Movimientos");

        tablaMovimiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código movimiento", "Monto", "Tipo", "Fecha"
            }
        ));
        jScrollPane4.setViewportView(tablaMovimiento);

        tiposCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tipos", "Debe", "Haber" }));
        tiposCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tiposComboActionPerformed(evt);
            }
        });

        agregarMovimientoBtn.setText("Agregar");
        agregarMovimientoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarMovimientoBtnActionPerformed(evt);
            }
        });

        jLabel8.setText("Tipo");

        jLabel9.setText("Monto");

        movimientoIdInput.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addComponent(jLabel5)
                                .addGap(300, 300, 300)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(sobreHabilitadoInput, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(agregar)
                                        .addGap(75, 75, 75))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(221, 221, 221))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(idCuentaInput, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(idClienteInput, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(idBancoInput, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(155, 155, 155)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(clientesCombo, javax.swing.GroupLayout.Alignment.LEADING, 0, 130, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(bancosCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(342, 342, 342)
                                .addComponent(jLabel4)
                                .addGap(261, 261, 261)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(montoInput, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9))
                                        .addGap(27, 27, 27)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(tiposCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(agregarMovimientoBtn))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(340, 340, 340)
                                        .addComponent(movimientoIdInput, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(314, 314, 314)
                                        .addComponent(jLabel7))))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(actualizarCuentas)
                        .addGap(18, 18, 18)
                        .addComponent(reporte))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idClienteInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idBancoInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idCuentaInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(clientesCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bancosCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(sobreHabilitadoInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(agregar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(reporte)
                            .addComponent(actualizarCuentas))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(montoInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tiposCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(agregarMovimientoBtn)
                            .addComponent(movimientoIdInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void clientesComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientesComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clientesComboActionPerformed

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        // TODO add your handling code here:
       
       /* ComboCliente oCliente =  (ComboCliente) clientesCombo.getSelectedItem();
       ComboBanco oBanco = (ComboBanco) bancosCombo.getSelectedItem();
         
       int idCliente = oCliente.getId_cliente();
       int idBanco = oBanco.getId_banco();    
       
       System.out.println("el id es ---> "+oCliente.getId_cliente()); */
       
        int filaCliente=tablaCliente.getSelectedRow();
        int filaBanco=tablaBanco.getSelectedRow();
        
        if(filaCliente == -1) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar un cliente.");
        } else {
            if(filaBanco == -1) {
                JOptionPane.showMessageDialog(null, "Debes seleccionar un banco.");
            } else {
                String clienteId = tablaCliente.getValueAt(filaCliente, 0).toString();
                String bancoId = tablaBanco.getValueAt(filaBanco, 0).toString();
        
                Cuenta c = new Cuenta();
      
                // ESTO ES PARA EDITAR SOLAMENTE SI HACE FALTA.

                if(!idClienteInput.getText().isEmpty() && !idBancoInput.getText().isEmpty()) { // Si no está limpio el input.
                    c.setId_cuenta(Integer.valueOf(idCuentaInput.getText()));

                    c.setId_cliente(Integer.valueOf(idClienteInput.getText()));
                    c.setId_banco(Integer.valueOf(idBancoInput.getText()));
                } else {

                    c.setId_cuenta(2921);
                    c.setId_cliente(Integer.valueOf(clienteId));
                    c.setId_banco(Integer.valueOf(bancoId));
                }
        
                if(sobreHabilitadoInput.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debes Introducir Monto.");
                } else {
                     c.setSobreHabilitado(Integer.valueOf(sobreHabilitadoInput.getText()));  

                    // System.out.println("HOLA ---> "+ c);

                    dao.verificarCuenta(c);

                    
                      /* idBancoInput.setText("");
                      idClienteInput.setText(""); */
                      
                      sobreHabilitadoInput.setText("");

                      ListarCuentasEnTabla();
                }
            }
        }
    }//GEN-LAST:event_agregarActionPerformed

    private void reporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporteActionPerformed
        // TODO add your handling code here:
        
          Connection connection;
         connection = Conexion.getConnection();

               
           
        
        try {

            JasperReport report = (JasperReport) JRLoader.loadObject("D:\\Usuario\\Desktop\\listaGrupoCuentas.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, connection);

            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.pack();
            frame.setVisible(true);

        } catch (JRException e) {
            e.printStackTrace();
        }

      
     
    }//GEN-LAST:event_reporteActionPerformed

    private void tablaCuentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCuentaMouseClicked
        // TODO add your handling code here:
        
        /* oMovimientosByIdCuentaModal.show();
        
        //oDetalleCuenta.setVisible(true);
        
        this.hide();
        // this.setVisible(false);
        
        // oDetalleCuenta.show();
        
         //oMovimientosByIdCuentaModal.show();
        
        
         int fila=tablaCuenta.getSelectedRow();
         String cuentaId = tablaCuenta.getValueAt(fila, 0).toString();
         
         String sobreHabilitado = tablaCuenta.getValueAt(fila, 6).toString();
         
         // String clienteId = tablaCuenta.getValueAt(fila, 1).toString();
         // String bancoId = tablaCuenta.getValueAt(fila, 2).toString();
      
        Cuenta c = new Cuenta();
        
        // para mostrar los id en los inputs del modal de movimientos.
        oMovimientosByIdCuentaModal.cuentaIdInput.setText(cuentaId);
        oMovimientosByIdCuentaModal.sobreHabilitadoInput.setText(sobreHabilitado);
        
        // oMovimientosByIdCuentaModal.clienteIdInput.setText(clienteId);
        // oMovimientosByIdCuentaModal.bancoIdInput.setText(bancoId);
      
        listarMovimientosPorIdCuentaEnTabla(Integer.valueOf(cuentaId)); */
        
        
        
       /*  if(!idClienteInput.getText().isEmpty() && !idBancoInput.getText().isEmpty()) {
            c.setId_cliente(Integer.valueOf(idClienteInput.getText()));
            c.setId_banco(Integer.valueOf(idBancoInput.getText()));
       } else {
            c.setId_cliente(Integer.valueOf(clienteId));
            c.setId_banco(Integer.valueOf(bancoId));
        }
        
        c.setSobreHabilitado(Integer.valueOf(sobreHabilitadoInput.getText())); */
        
    }//GEN-LAST:event_tablaCuentaMouseClicked

    private void actualizarCuentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarCuentasActionPerformed
        // TODO add your handling code here:
        
        int fila=tablaCuenta.getSelectedRow();
        String cuentaId = tablaCuenta.getValueAt(fila, 0).toString();
         
         String sobreHabilitado = tablaCuenta.getValueAt(fila, 6).toString();
         
         
      
        Cuenta c = new Cuenta();
        
        // para mostrar los id en los inputs del modal de movimientos.
        //oMovimientosByIdCuentaModal.cuentaIdInput.setText(cuentaId);
        // oMovimientosByIdCuentaModal.sobreHabilitadoInput.setText(sobreHabilitado);
        
        // oMovimientosByIdCuentaModal.clienteIdInput.setText(clienteId);
        // oMovimientosByIdCuentaModal.bancoIdInput.setText(bancoId);
      
        listarMovimientosPorIdCuentaEnTabla(Integer.valueOf(cuentaId));
    }//GEN-LAST:event_actualizarCuentasActionPerformed

    private void tiposComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tiposComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tiposComboActionPerformed

    private void agregarMovimientoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarMovimientoBtnActionPerformed
        // TODO add your handling code here:

        int fila = tablaCuenta.getSelectedRow();
        
        if(fila == -1) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una cuenta.");
        } else {
            int cuentaId = Integer.valueOf(tablaCuenta.getValueAt(fila, 0).toString());
            
            // Obtenemos el tipo para agregar los datos.
            String tipo = tiposCombo.getSelectedItem().toString();
                
            if((tipo == "Tipos") || (montoInput.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "El Monto o el tipo están incompletos, favor completar.");
            } else {
                // Obtenemos el monto para agregar los datos.
                int monto = Integer.valueOf(montoInput.getText());
                
                 Movimiento oMovi = new Movimiento();
            
                // Obtenemos el id de la cuenta que está en el Input.
                //int cuentaId = Integer.valueOf(cuentaIdInput.getText());

                if(!movimientoIdInput.getText().isEmpty()) { // Si No está vacio.

                    oMovi.setId_movimiento(Integer.valueOf(movimientoIdInput.getText()));
                }
                

                // Cargamos los datos del formulario al modelo.
                oMovi.setId_cuenta(cuentaId);
                oMovi.setTipo(tipo);
                oMovi.setMonto(monto);

                // Obtenemos el monto permitido de la cuenta desde sobreHabilitadoInput.
        
                int sobreHabilitado = Integer.valueOf(tablaCuenta.getValueAt(fila, 6).toString());

                if(tipo == "Debe") {
                    if(monto > sobreHabilitado) {
                        JOptionPane.showMessageDialog(null, "Monto No permitido.");
                    } else {
                        daoMovimiento.verificarMovimiento(oMovi);

                        listarMovimientosPorIdCuentaEnTabla(cuentaId);
                    }
                } else {
                    daoMovimiento.verificarMovimiento(oMovi);

                    listarMovimientosPorIdCuentaEnTabla(cuentaId);
                }

                // LIMPIANDO EL FORMULARIO.

                // movimientoIdInput.setText("");
                montoInput.setText("");
                tiposCombo.setSelectedItem("Debe");

                ListarCuentasEnTabla();
            }
        }

    }//GEN-LAST:event_agregarMovimientoBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormularioCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormularioCuenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizarCuentas;
    private javax.swing.JButton agregar;
    private javax.swing.JButton agregarMovimientoBtn;
    private javax.swing.JComboBox bancosCombo;
    private javax.swing.JComboBox clientesCombo;
    private javax.swing.JTextField idBancoInput;
    private javax.swing.JTextField idClienteInput;
    private javax.swing.JTextField idCuentaInput;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    public javax.swing.JTextField montoInput;
    private javax.swing.JTextField movimientoIdInput;
    private javax.swing.JButton reporte;
    private javax.swing.JTextField sobreHabilitadoInput;
    private javax.swing.JTable tablaBanco;
    private javax.swing.JTable tablaCliente;
    public javax.swing.JTable tablaCuenta;
    public javax.swing.JTable tablaMovimiento;
    private javax.swing.JComboBox tiposCombo;
    // End of variables declaration//GEN-END:variables
}

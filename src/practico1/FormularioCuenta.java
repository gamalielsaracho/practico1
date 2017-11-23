/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package practico1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
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
                    cu.getId_cuenta() , cu.getId_cliente(), cu.getId_banco(), cu.getNombreCliente()+" "+cu.getApellidoCliente(), 
                    cu.getNombreBanco(), cu.getFechaCreacion(), 
                    cu.getFechaUmovimiento(), cu.getSaldo(), cu.getSobreHabilitado() });
       }
     }
     
     
     public  void  listarMovimientosPorIdCuentaEnTabla(int idCuenta) {
         DefaultTableModel modelo = (DefaultTableModel)  oMovimientosByIdCuentaModal.tablaMovimiento.getModel(); 
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
        limpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCuenta = new javax.swing.JTable();
        editar = new javax.swing.JButton();
        sobreHabilitadoInput = new javax.swing.JTextField();
        idBancoInput = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCliente = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaBanco = new javax.swing.JTable();
        mostrarMovimientos = new javax.swing.JButton();
        idCuentaInput = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        limpiar.setText("Limpiar");
        limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarActionPerformed(evt);
            }
        });

        tablaCuenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "código cuenta", "código cliente", "código banco", "Nombre", "Banco", "Fecha Creacion", "Fecha U.movimiento", "Saldo", "Sobre habilitado"
            }
        ));
        tablaCuenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCuentaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaCuenta);

        editar.setText("Editar");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });

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

        mostrarMovimientos.setText("Mostrar movimientos");
        mostrarMovimientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarMovimientosActionPerformed(evt);
            }
        });

        idCuentaInput.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                            .addComponent(bancosCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(135, 135, 135))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addComponent(jLabel5)
                                .addGap(300, 300, 300)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(286, 286, 286)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mostrarMovimientos))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(sobreHabilitadoInput, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(agregar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(limpiar))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(editar)))
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1018, Short.MAX_VALUE)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sobreHabilitadoInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(agregar)
                        .addComponent(limpiar)))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editar)
                    .addComponent(jLabel4)
                    .addComponent(mostrarMovimientos))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
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
        
         String clienteId = tablaCliente.getValueAt(filaCliente, 0).toString();
         String bancoId = tablaBanco.getValueAt(filaBanco, 0).toString();
        
        Cuenta c = new Cuenta();
      
        if(!idClienteInput.getText().isEmpty() && !idBancoInput.getText().isEmpty()) {
            c.setId_cuenta(Integer.valueOf(idCuentaInput.getText()));
            
            c.setId_cliente(Integer.valueOf(idClienteInput.getText()));
            c.setId_banco(Integer.valueOf(idBancoInput.getText()));
       } else {
         
            c.setId_cuenta(2921);
            c.setId_cliente(Integer.valueOf(clienteId));
            c.setId_banco(Integer.valueOf(bancoId));
        }
        
        c.setSobreHabilitado(Integer.valueOf(sobreHabilitadoInput.getText()));  
      
         // System.out.println("HOLA ---> "+ c);
        
         dao.verificarCuenta(c);
         
        /*  
          idBancoInput.setText("");
            idClienteInput.setText("");
            sobreHabilitadoInput.setText("");
         */
         
       
    
        
        ListarCuentasEnTabla();
    }//GEN-LAST:event_agregarActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        // TODO add your handling code here:
      
         int fila=tablaCuenta.getSelectedRow();
         //int filaBanco=tablaBanco.getSelectedRow();
         
         String cuentaId = tablaCuenta.getValueAt(fila, 0).toString();
         String bancoId = tablaCuenta.getValueAt(fila, 1).toString();
         String clienteId = tablaCuenta.getValueAt(fila, 2).toString();
         
         String setSobreHabilitado = tablaCuenta.getValueAt(fila, 8).toString();
       
        idCuentaInput.setText(cuentaId);
        idBancoInput.setText(bancoId);
        idClienteInput.setText(clienteId);
        sobreHabilitadoInput.setText(setSobreHabilitado);
       
       
          
        /* String clienteNombre = tablaCuenta.getValueAt(fila, 1).toString();
        String clienteApellido = tablaCuenta.getValueAt(fila, 2).toString(); */
        
    }//GEN-LAST:event_editarActionPerformed

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        // TODO add your handling code here:
        idCuentaInput.setText("");
        idBancoInput.setText("");
        idClienteInput.setText("");
        sobreHabilitadoInput.setText("");
    }//GEN-LAST:event_limpiarActionPerformed

    private void mostrarMovimientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarMovimientosActionPerformed
        // TODO add your handling code here:
        
      
      
         //Movimiento  oMovimiento = new Movimiento();
        //oMovimiento.show();
    }//GEN-LAST:event_mostrarMovimientosActionPerformed

    private void tablaCuentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCuentaMouseClicked
        // TODO add your handling code here:
        
        oMovimientosByIdCuentaModal.show();
        
         int fila=tablaCuenta.getSelectedRow();
         String cuentaId = tablaCuenta.getValueAt(fila, 0).toString();
         
         String clienteId = tablaCuenta.getValueAt(fila, 1).toString();
         String bancoId = tablaCuenta.getValueAt(fila, 2).toString();
      
        Cuenta c = new Cuenta();
        
        // para mostrar los id en los inputs.
        oMovimientosByIdCuentaModal.cuentaIdInput.setText(cuentaId);
        oMovimientosByIdCuentaModal.clienteIdInput.setText(clienteId);
        oMovimientosByIdCuentaModal.bancoIdInput.setText(bancoId);
      
        listarMovimientosPorIdCuentaEnTabla(Integer.valueOf(cuentaId));
        
       /*  if(!idClienteInput.getText().isEmpty() && !idBancoInput.getText().isEmpty()) {
            c.setId_cliente(Integer.valueOf(idClienteInput.getText()));
            c.setId_banco(Integer.valueOf(idBancoInput.getText()));
       } else {
            c.setId_cliente(Integer.valueOf(clienteId));
            c.setId_banco(Integer.valueOf(bancoId));
        }
        
        c.setSobreHabilitado(Integer.valueOf(sobreHabilitadoInput.getText())); */
        
    }//GEN-LAST:event_tablaCuentaMouseClicked

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
    private javax.swing.JButton agregar;
    private javax.swing.JComboBox bancosCombo;
    private javax.swing.JComboBox clientesCombo;
    private javax.swing.JButton editar;
    private javax.swing.JTextField idBancoInput;
    private javax.swing.JTextField idClienteInput;
    private javax.swing.JTextField idCuentaInput;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton limpiar;
    private javax.swing.JButton mostrarMovimientos;
    private javax.swing.JTextField sobreHabilitadoInput;
    private javax.swing.JTable tablaBanco;
    private javax.swing.JTable tablaCliente;
    public javax.swing.JTable tablaCuenta;
    // End of variables declaration//GEN-END:variables
}

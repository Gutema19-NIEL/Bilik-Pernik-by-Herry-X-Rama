/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Admin_Page;

import Bilik_Pernik_Connection.BK_Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author asuna
 */
public class Admin_DB_Page extends javax.swing.JFrame {
    public static String nama_pengguna;
    /**
     * Creates new form Admin_DB_Page
     */
    public Admin_DB_Page() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        pengguna_data();
        total_pengguna();
        Kategori_Data();
        list_subctg();
        list_subctg_del();
        total_subctg();
        Subkategori_Data();
        list_subctg_up();
    }
    
    private void search_bar(){
        
    }
    public Admin_DB_Page(String Username) {
        initComponents();
        nama_pengguna=Username;
        ImageIcon admin_card;
        
        if(nama_pengguna.equals("Rama Swara") || nama_pengguna.equals("RamaSwara@bilikpernik.com")){
                admin_card = new ImageIcon("src/Picture/Effect/Admin Card R.png");/*Format pemanggilan file ImageIcon
                yang ingin dipanggil*/
                Admin_Foto.setIcon(admin_card);/*Pallete pada java (berupa JLabel)
                yang digunakan untuk melakukan set pada ImageIcon*/
        }else if(nama_pengguna.equals("Herry Liukae") || nama_pengguna.equals("HerryLiukae@bilikpernik.com")){
                admin_card = new ImageIcon("src/Picture/Effect/Admin Card H.png");/*Format pemanggilan file ImageIcon
                yang ingin dipanggil*/
                Admin_Foto.setIcon(admin_card);/*Pallete pada java (berupa JLabel)
                yang digunakan untuk melakukan set pada ImageIcon*/
        }
    }
    
    public void pengguna_data(){
        DefaultTableModel tabel = new DefaultTableModel();
        
        tabel.addColumn("ID Pengguna");
        tabel.addColumn("Nama Pengguna");
        tabel.addColumn("Email");
        tabel.addColumn("Kata Sandi");
        tabel.addColumn("Nomor Telepon");
        tabel.addColumn("Alamat");
        tabel.addColumn("Foto Profile");
        
        PreparedStatement cmd;
        ResultSet rs;
        
         try {
            String sql = "select*from tb_pengguna";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
            while(rs.next()){
                tabel.addRow(new Object[]{
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                });
            }
            tb_pengguna.setModel(tabel);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
    }
    
    public void total_pengguna(){
        PreparedStatement cmd;
        ResultSet rs;
        int count_user;
        String count_user1;
        try {
            
            String sql = "select count(id_pengguna) from tb_pengguna";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
            if(rs.next()){
            count_user = rs.getInt(1);
            count_user1 = Integer.toString(count_user);
            Field_sum_User.setText(count_user1);
            }
              
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
    }
    // Toko Data
    public void toko_data(){
        DefaultTableModel tabel = new DefaultTableModel();
        
        tabel.addColumn("ID Penjual");
        tabel.addColumn("Nama Penjual");
        tabel.addColumn("Email Penjual");
        tabel.addColumn("Kata Sandi");
        tabel.addColumn("Nama Toko");
        tabel.addColumn("No Telepon Penjual");
        tabel.addColumn("Alamat Penjual");
        tabel.addColumn("Foto Profil Penjual");
        
        PreparedStatement cmd;
        ResultSet rs;
        
         try {
            String sql = "select*from tb_seller";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
            while(rs.next()){
                tabel.addRow(new Object[]{
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7)
                });
            }
            tb_toko.setModel(tabel);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
    }
    
    public void total_toko(){
        PreparedStatement cmd;
        ResultSet rs;
        int count_user;
        String count_user1;
        try {
            
            String sql = "select count(id_seller) from tb_seller";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
            if(rs.next()){
            count_user = rs.getInt(1);
            count_user1 = Integer.toString(count_user);
            Field_sum_seller.setText(count_user1);
            }
              
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
    }
    
    // Item Data
    
     public void item_data(){
        DefaultTableModel tabel = new DefaultTableModel();
        
        tabel.addColumn("ID Item");
        tabel.addColumn("Nama Toko");
        tabel.addColumn("Nama Produk");
        tabel.addColumn("Kategori");
        tabel.addColumn("Subkategori");
        tabel.addColumn("Stok");
        tabel.addColumn("Massa (gr)");
        tabel.addColumn("Panjang (cm)");
        tabel.addColumn("Lebar (cm)");
        tabel.addColumn("Tinggi (cm)");
        tabel.addColumn("Harga (Rp)");
        tabel.addColumn("Foto Produk");
        
        
        PreparedStatement cmd;
        ResultSet rs;
        
         try {
            String sql = "call tb_item()";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
            while(rs.next()){
                tabel.addRow(new Object[]{
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10),
                    rs.getString(11),
                    rs.getString(12),
                });
            }
            tb_item.setModel(tabel);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
    }
    
    public void Prc_item(String s_item){
        DefaultTableModel tabel = new DefaultTableModel();
        
        tabel.addColumn("ID Item");
        tabel.addColumn("Nama Toko");
        tabel.addColumn("Nama Produk");
        tabel.addColumn("Kategori");
        tabel.addColumn("Subkategori");
        tabel.addColumn("Stok");
        tabel.addColumn("Massa (gr)");
        tabel.addColumn("Panjang (cm)");
        tabel.addColumn("Lebar (cm)");
        tabel.addColumn("Tinggi (cm)");
        tabel.addColumn("Harga (Rp)");
        tabel.addColumn("Foto Produk");
        
        
        PreparedStatement cmd;
        ResultSet rs;
        
         try {
            String sql = "call cari_item('"+s_item+"')";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
            while(rs.next()){
                tabel.addRow(new Object[]{
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10),
                    rs.getString(11),
                    rs.getString(12),
                });
            }
            tb_item.setModel(tabel);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
    }
    
    public void total_item(){
        PreparedStatement cmd;
        ResultSet rs;
        int count_user;
        String count_user1;
        try {
            
            String sql = "call total_item()";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
            if(rs.next()){
            count_user = rs.getInt(1);
            count_user1 = Integer.toString(count_user);
            Field_sum_item.setText(count_user1);
            }
              
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
    }
    
    // Kategori Data
    
    public void Kategori_Data(){
        DefaultTableModel tabel = new DefaultTableModel();
        
        tabel.addColumn("ID Kategori");
        tabel.addColumn("Kategori");
        
        PreparedStatement cmd;
        ResultSet rs;
        
         try {
            String sql = "select*from tb_kategori";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
            while(rs.next()){
                tabel.addRow(new Object[]{
                    rs.getInt(1),
                    rs.getString(2)
                });
            }
            tb_ctg.setModel(tabel);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
    }
    
    private void kategori_del(){
        ctg_combo_1.removeAllItems();
        ctg_combo_1.addItem("Tentukkan kategori yang ingin dihapus");
        
        Statement cmd;
        ResultSet rs;
        String sql = "select*from tb_kategori";
        
        try {  
            cmd = BK_Connection.getBilik_Pernik_Connect().createStatement();
            rs = cmd.executeQuery(sql);  
            while(rs.next()){
               ctg_combo_1.addItem(rs.getString(2));
            }
                
        } catch (Exception e) {
                System.out.println("Error\n"+e);
        }
    
    }
    
    private void kategori_up(){
        ctg_combo.removeAllItems();
        ctg_combo.addItem("Tentukkan kategori yang ingin diperbaharui");
        
        Statement cmd;
        ResultSet rs;
        String sql = "select*from tb_kategori";
        
        try {  
            cmd = BK_Connection.getBilik_Pernik_Connect().createStatement();
            rs = cmd.executeQuery(sql);  
            while(rs.next()){
               ctg_combo.addItem(rs.getString(2));
            }
                
        } catch (Exception e) {
                System.out.println("Error\n"+e);
        }
    
    }
    
     public void total_ctg(){
        PreparedStatement cmd;
        ResultSet rs;
        int count_user;
        String count_user1;
        try {
            
            String sql = "select count(id_kategori) from tb_kategori";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
            if(rs.next()){
            count_user = rs.getInt(1);
            count_user1 = Integer.toString(count_user);
            Field_sum_ctg.setText(count_user1);
            }
              
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
     }
     
     // Subkateogri Data
     private void list_subctg(){
        subctg_combo1.removeAllItems();
        subctg_combo1.addItem("Tentukan kategori yang ingin ditambahkan");
        
        Statement cmd;
        ResultSet rs;
        String sql = "select*from tb_kategori";
        
        try {  
            cmd = BK_Connection.getBilik_Pernik_Connect().createStatement();
            rs = cmd.executeQuery(sql);  
            while(rs.next()){
               subctg_combo1.addItem(rs.getString(2));
            }
                
        } catch (Exception e) {
                System.out.println("Error\n"+e);
        }
     }
     
      private void list_subctg_del(){
        subctg_combo2.removeAllItems();
        subctg_combo2.addItem("Tentukan kategori yang ingin dihapus");
        
        Statement cmd;
        ResultSet rs;
        String sql = "select*from tb_subkategori";
        
        try {  
            cmd = BK_Connection.getBilik_Pernik_Connect().createStatement();
            rs = cmd.executeQuery(sql);  
            while(rs.next()){
               subctg_combo2.addItem(rs.getString(3));
            }
                
        } catch (Exception e) {
                System.out.println("Error\n"+e);
        }
     }
     
     public void total_subctg(){
        PreparedStatement cmd;
        ResultSet rs;
        int count_user;
        String count_user1;
        try {
            
            String sql = "select count(id_subkategori) from tb_subkategori";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
            if(rs.next()){
            count_user = rs.getInt(1);
            count_user1 = Integer.toString(count_user);
            Field_sum_subctg.setText(count_user1);
            }
              
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
     }
     
     public void Subkategori_Data(){
        DefaultTableModel tabel = new DefaultTableModel();
        
        tabel.addColumn("Kategori");
        tabel.addColumn("Subkategori");
        
        PreparedStatement cmd;
        ResultSet rs;
        
         try {
            String sql = "select tb_kategori.kategori, tb_subkategori.subkategori from tb_subkategori"
                    + " inner join tb_kategori on tb_subkategori.id_kategori = tb_kategori.id_kategori";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
            while(rs.next()){
                tabel.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2)
                });
            }
            tb_subctg.setModel(tabel);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
    }
    
   private void list_subctg_up(){
        subctg_combo.removeAllItems();
        subctg_combo.addItem("Tentukan kategori yang ingin diperbarui");
        
        Statement cmd;
        ResultSet rs;
        String sql = "select*from tb_subkategori";
        
        try {  
            cmd = BK_Connection.getBilik_Pernik_Connect().createStatement();
            rs = cmd.executeQuery(sql);  
            while(rs.next()){
               subctg_combo.addItem(rs.getString(3));
            }
                
        } catch (Exception e) {
                System.out.println("Error\n"+e);
        }
     }

   // Mitra Payment
    
    public void Payment_Data(){
        DefaultTableModel tabel = new DefaultTableModel();
        
        tabel.addColumn("ID Mitra Pembayaran");
        tabel.addColumn("Mitra Pembayaran");
        
        PreparedStatement cmd;
        ResultSet rs;
        
         try {
            String sql = "select*from tb_mitra_bayar";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
            while(rs.next()){
                tabel.addRow(new Object[]{
                    rs.getInt(1),
                    rs.getString(2)
                });
            }
            tb_payment.setModel(tabel);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
    }
    
    private void payment_del(){
        payment_combo_1.removeAllItems();
        payment_combo_1.addItem("Tentukkan Mitra Pembayaran yang ingin dihapus");
        
        Statement cmd;
        ResultSet rs;
        String sql = "select*from tb_mitra_bayar";
        
        try {  
            cmd = BK_Connection.getBilik_Pernik_Connect().createStatement();
            rs = cmd.executeQuery(sql);  
            while(rs.next()){
               payment_combo_1.addItem(rs.getString(2));
            }
                
        } catch (Exception e) {
                System.out.println("Error\n"+e);
        }
    
    }
    
    private void payment_up(){
        payment_combo.removeAllItems();
        payment_combo.addItem("Tentukkan mitra pembayaran yang ingin diperbaharui");
        
        Statement cmd;
        ResultSet rs;
        String sql = "select*from tb_mitra_bayar";
        
        try {  
            cmd = BK_Connection.getBilik_Pernik_Connect().createStatement();
            rs = cmd.executeQuery(sql);  
            while(rs.next()){
               payment_combo.addItem(rs.getString(2));
            }
                
        } catch (Exception e) {
                System.out.println("Error\n"+e);
        }
    
    }
    
     public void total_payment(){
        PreparedStatement cmd;
        ResultSet rs;
        int count_user;
        String count_user1;
        try {
            
            String sql = "select count(id_mitra_bayar) from tb_mitra_bayar";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
            if(rs.next()){
            count_user = rs.getInt(1);
            count_user1 = Integer.toString(count_user);
            Field_sum_payment.setText(count_user1);
            }
              
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
     }
     
     // Jaskir
    
    public void Jaskir_Data(){
        DefaultTableModel tabel = new DefaultTableModel();
        
        tabel.addColumn("ID Mitra Pengiriman");
        tabel.addColumn("Mitra Pengirima");
        
        PreparedStatement cmd;
        ResultSet rs;
        
         try {
            String sql = "select*from tb_mitra_kirim";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
            while(rs.next()){
                tabel.addRow(new Object[]{
                    rs.getInt(1),
                    rs.getString(2)
                });
            }
            tb_jaskir.setModel(tabel);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
    }
    
    private void jaskir_del(){
        jaskir_combo_1.removeAllItems();
        jaskir_combo_1.addItem("Tentukkan Mitra Pengiriman yang ingin dihapus");
        
        Statement cmd;
        ResultSet rs;
        String sql = "select*from tb_mitra_kirim";
        
        try {  
            cmd = BK_Connection.getBilik_Pernik_Connect().createStatement();
            rs = cmd.executeQuery(sql);  
            while(rs.next()){
               jaskir_combo_1.addItem(rs.getString(2));
            }
                
        } catch (Exception e) {
                System.out.println("Error\n"+e);
        }
    
    }
    
    private void jaskir_up(){
        jaskir_combo.removeAllItems();
        jaskir_combo.addItem("Tentukkan mitra pengiriman yang ingin diperbaharui");
        
        Statement cmd;
        ResultSet rs;
        String sql = "select*from tb_mitra_kirim";
        
        try {  
            cmd = BK_Connection.getBilik_Pernik_Connect().createStatement();
            rs = cmd.executeQuery(sql);  
            while(rs.next()){
               jaskir_combo.addItem(rs.getString(2));
            }
                
        } catch (Exception e) {
                System.out.println("Error\n"+e);
        }
    
    }
    
     public void total_jaskir(){
        PreparedStatement cmd;
        ResultSet rs;
        int count_user;
        String count_user1;
        try {
            
            String sql = "select count(id_mitra_kirim) from tb_mitra_kirim";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
            if(rs.next()){
            count_user = rs.getInt(1);
            count_user1 = Integer.toString(count_user);
            Field_sum_jaskir.setText(count_user1);
            }
              
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
     }
     
     
      // Transaksi
    
     public void trc_data(){
        DefaultTableModel tabel = new DefaultTableModel();
        
        tabel.addColumn("ID Transaksi");
        tabel.addColumn("Nama Pembeli");
        tabel.addColumn("Nama Alamat");
        tabel.addColumn("Nama Toko");
        tabel.addColumn("Mitra Bayar");
        tabel.addColumn("Mitra Kirim");
        tabel.addColumn("Total");
        tabel.addColumn("Tanggal");
        tabel.addColumn("Kuantitas");
        tabel.addColumn("Nama Produk");
        tabel.addColumn("Harga (Rp)");
        
        
        PreparedStatement cmd;
        ResultSet rs;
        
         try {
            String sql = "call transaksi_admin()";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
            while(rs.next()){
                tabel.addRow(new Object[]{
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10),
                    rs.getString(11)
                });
            }
            tb_transaksi.setModel(tabel);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
    }
    
    public void Prc_trans(String s_trans){
         DefaultTableModel tabel = new DefaultTableModel();
        
        tabel.addColumn("ID Transaksi");
        tabel.addColumn("Nama Pembeli");
        tabel.addColumn("Alamat");
        tabel.addColumn("Nama Toko");
        tabel.addColumn("Mitra Bayar");
        tabel.addColumn("Mitra Kirim");
        tabel.addColumn("Total");
        tabel.addColumn("Tanggal");
        tabel.addColumn("Kuantitas");
        tabel.addColumn("Nama Produk");
        tabel.addColumn("Harga (Rp)");
        
        
        PreparedStatement cmd;
        ResultSet rs;
        
         try {
            String sql = "call transaksi_admin_cari('"+s_trans+"')";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
            while(rs.next()){
                tabel.addRow(new Object[]{
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10),
                    rs.getString(11)
                });
            }
            tb_transaksi.setModel(tabel);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
          
    }
    
    public void total_trc(){
        PreparedStatement cmd;
        ResultSet rs;
        int count_user;
        String count_user1;
        try {
            
            String sql = "call total_trc()";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
            if(rs.next()){
            count_user = rs.getInt(1);
            count_user1 = Integer.toString(count_user);
            Field_sum_rpt.setText(count_user1);
            }
              
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
    }
    
     
     
     //Pencarian
     
     private void cari_user(String s_user){ 
         
        DefaultTableModel tabel = new DefaultTableModel();
        
        tabel.addColumn("ID Pengguna");
        tabel.addColumn("Nama Pengguna");
        tabel.addColumn("Email");
        tabel.addColumn("Kata Sandi");
        tabel.addColumn("Alamat");
        tabel.addColumn("Foto Profile");
        
        PreparedStatement cmd;
        ResultSet rs;
       
        try {
            
            String sql = "select*from tb_pengguna where id_pengguna LIKE CONCAT('"+s_user+"','%') "
                    + "or nama LIKE CONCAT('"+s_user+"','%') or email LIKE CONCAT('"+s_user+"','%')"
                    + "or katasandi LIKE CONCAT('"+s_user+"','%') or telp LIKE CONCAT('"+s_user+"','%') or alamat LIKE CONCAT('"+s_user+"','%')";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
           while(rs.next()){
                tabel.addRow(new Object[]{
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                });
            }
            tb_pengguna.setModel(tabel);
              
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
     }
     private void cari_seller(String s_seller){
        DefaultTableModel tabel = new DefaultTableModel();
        
        tabel.addColumn("ID Penjual");
        tabel.addColumn("Nama Penjual");
        tabel.addColumn("Email Penjual");
        tabel.addColumn("Kata Sandi");
        tabel.addColumn("Nama Toko");
        tabel.addColumn("No Telepon Penjual");
        tabel.addColumn("Alamat Penjual");
        tabel.addColumn("Foto Profil Penjual");
        
        PreparedStatement cmd;
        ResultSet rs;
        
         try {
            String sql = "select*from tb_seller where id_seller LIKE CONCAT('"+s_seller+"','%')"
                    + "or username_seller LIKE CONCAT('"+s_seller+"','%') or email_seller LIKE CONCAT('"+s_seller+"','%') "
                    + "or password_seller LIKE CONCAT('"+s_seller+"','%') or nama_seller LIKE CONCAT('"+s_seller+"','%') "
                    + "or no_telp_seller LIKE CONCAT('"+s_seller+"','%') or alamat_seller LIKE CONCAT('"+s_seller+"','%')";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
            while(rs.next()){
                tabel.addRow(new Object[]{
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7)
                });
            }
            tb_toko.setModel(tabel);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
     }
     
     private void cari_kategori(String s_ctg){
        DefaultTableModel tabel = new DefaultTableModel();
        
        tabel.addColumn("ID Kategori");
        tabel.addColumn("Kategori");
        
        PreparedStatement cmd;
        ResultSet rs;
        
         try {
            String sql = "select*from tb_kategori where id_kategori LIKE CONCAT('"+s_ctg+"','%') or kategori LIKE CONCAT('"+s_ctg+"','%')'";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
            while(rs.next()){
                tabel.addRow(new Object[]{
                    rs.getInt(1),
                    rs.getString(2)
                });
            }
            tb_ctg.setModel(tabel);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
    }
    
    private void cari_subkategori(String s_sctg){
        DefaultTableModel tabel = new DefaultTableModel();
        
        tabel.addColumn("Kategori");
        tabel.addColumn("Subkategori");
        
        PreparedStatement cmd;
        ResultSet rs;
        
         try {
            String sql = "select tb_kategori.kategori, tb_subkategori.subkategori from tb_subkategori inner join tb_kategori on tb_subkategori.id_kategori"
                    + "= tb_kategori.id_kategori where tb_kategori.kategori LIKE CONCAT('"+s_sctg+"','%') "
                    + "or tb_subkategori.subkategori LIKE CONCAT('"+s_sctg+"','%')";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
            while(rs.next()){
                tabel.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2)
                });
            }
            tb_subctg.setModel(tabel);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
    }
    
    private void cari_payment(String s_payment){
        DefaultTableModel tabel = new DefaultTableModel();
        
        tabel.addColumn("ID Mitra Pembayaran");
        tabel.addColumn("Mitra Pembayaran");
        
        PreparedStatement cmd;
        ResultSet rs;
        
         try {
            String sql = "select*from tb_mitra_bayar where id_mitra_bayar LIKE CONCAT('"+s_payment+"','%') or mitra_bayar LIKE CONCAT('"+s_payment+"','%')";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
            while(rs.next()){
                tabel.addRow(new Object[]{
                    rs.getInt(1),
                    rs.getString(2)
                });
            }
            tb_payment.setModel(tabel);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
    }
    
    private void cari_jaskir(String s_jaskir){
        DefaultTableModel tabel = new DefaultTableModel();
        
        tabel.addColumn("ID Mitra Pengiriman");
        tabel.addColumn("Mitra Pengiriman");
        
        PreparedStatement cmd;
        ResultSet rs;
        
         try {
            String sql = "select*from tb_mitra_kirim where id_mitra_kirim LIKE CONCAT('"+s_jaskir+"','%') or mitra_kirim LIKE CONCAT('"+s_jaskir+"','%')";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
            while(rs.next()){
                tabel.addRow(new Object[]{
                    rs.getInt(1),
                    rs.getString(2)
                });
            }
            tb_jaskir.setModel(tabel);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
    }
    
    private void alter_ctg(){
        PreparedStatement cmd;
        ResultSet rs;
        /*Try dan Catch digunakan untuk menangani error pada java, eksekusi yang menampilkan
        error dan membuat program tetap berjalan tanpa dihentikan secara langsung*/
        try{ /*Statement yang digunakan untuk menghubungkan database pada mysql ke Netbeans*/
            String sql = " ALTER TABLE tb_kategori AUTO_INCREMENT = 0;"; /*Syntax pada mysql yang digunakan untuk melakukan peng-update-an data
            password melalui username atau email yang dimasukkan pada form sebelumnya*/
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            cmd.executeUpdate(); /*Untuk melakukan ekseskusi pada syntax sql*/
            
        }catch(SQLException e) /*Menangkap kesalahan atau bug yang terjadi dalam block try*/
        {
            JOptionPane.showMessageDialog(null, e); /*Kondisi apabila terjadi kesalahan pada saat melakukan input data*/
        }
    
    }
    
    private void alter_s_ctg(){
        PreparedStatement cmd;
        ResultSet rs;
        /*Try dan Catch digunakan untuk menangani error pada java, eksekusi yang menampilkan
        error dan membuat program tetap berjalan tanpa dihentikan secara langsung*/
        try{ /*Statement yang digunakan untuk menghubungkan database pada mysql ke Netbeans*/
            String sql = " ALTER TABLE tb_subkategori AUTO_INCREMENT = 0;"; /*Syntax pada mysql yang digunakan untuk melakukan peng-update-an data
            password melalui username atau email yang dimasukkan pada form sebelumnya*/
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            cmd.executeUpdate(); /*Untuk melakukan ekseskusi pada syntax sql*/
           
        }catch(SQLException e) /*Menangkap kesalahan atau bug yang terjadi dalam block try*/
        {
            JOptionPane.showMessageDialog(null, e); /*Kondisi apabila terjadi kesalahan pada saat melakukan input data*/
        }
    
    }
    
     private void alter_pay(){
        PreparedStatement cmd;
        ResultSet rs;
        /*Try dan Catch digunakan untuk menangani error pada java, eksekusi yang menampilkan
        error dan membuat program tetap berjalan tanpa dihentikan secara langsung*/
        try{ /*Statement yang digunakan untuk menghubungkan database pada mysql ke Netbeans*/
            String sql = " ALTER TABLE tb_mitra_bayar AUTO_INCREMENT = 0;"; /*Syntax pada mysql yang digunakan untuk melakukan peng-update-an data
            password melalui username atau email yang dimasukkan pada form sebelumnya*/
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            cmd.executeUpdate(); /*Untuk melakukan ekseskusi pada syntax sql*/
            
        }catch(SQLException e) /*Menangkap kesalahan atau bug yang terjadi dalam block try*/
        {
            JOptionPane.showMessageDialog(null, e); /*Kondisi apabila terjadi kesalahan pada saat melakukan input data*/
        }
    
    }
    
     private void alter_jaskir(){
        PreparedStatement cmd;
        ResultSet rs;
        /*Try dan Catch digunakan untuk menangani error pada java, eksekusi yang menampilkan
        error dan membuat program tetap berjalan tanpa dihentikan secara langsung*/
        try{ /*Statement yang digunakan untuk menghubungkan database pada mysql ke Netbeans*/
            String sql = " ALTER TABLE tb_mitra_kirim AUTO_INCREMENT = 0;"; /*Syntax pada mysql yang digunakan untuk melakukan peng-update-an data
            password melalui username atau email yang dimasukkan pada form sebelumnya*/
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            cmd.executeUpdate(); /*Untuk melakukan ekseskusi pada syntax sql*/
            
        }catch(SQLException e) /*Menangkap kesalahan atau bug yang terjadi dalam block try*/
        {
            JOptionPane.showMessageDialog(null, e); /*Kondisi apabila terjadi kesalahan pada saat melakukan input data*/
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

        Search_Bar = new javax.swing.JPanel();
        LogOut_Btn = new javax.swing.JLabel();
        Logo_Picture = new javax.swing.JLabel();
        Laporan_Btn = new javax.swing.JLabel();
        Telusuri_Data_Label = new javax.swing.JLabel();
        Home_Btn_eft2 = new javax.swing.JLabel();
        Home_Btn = new javax.swing.JLabel();
        Cari_Data_TextField = new javax.swing.JTextField();
        Tentang_Btn = new javax.swing.JLabel();
        Data_Btn = new javax.swing.JLabel();
        Search_Btn = new javax.swing.JLabel();
        Search_Bar_Label = new javax.swing.JLabel();
        Admin_Main_Panel = new javax.swing.JPanel();
        Home_Page = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        More_Btn = new javax.swing.JLabel();
        Item_List_Btn = new javax.swing.JLabel();
        Toko_List_Btn = new javax.swing.JLabel();
        User_List_Btn = new javax.swing.JLabel();
        Admin_Foto = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Data_Page = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        Daftar_Jaskir_Btn = new javax.swing.JLabel();
        Daftar_Payment_Btn = new javax.swing.JLabel();
        Daftar_Barang_Btn = new javax.swing.JLabel();
        Daftar_Toko_Btn = new javax.swing.JLabel();
        Subktg_Btn = new javax.swing.JLabel();
        Daftar_Kategori_Btn = new javax.swing.JLabel();
        Daftar_Pengguna_Btn = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        List_User_Page = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        Back_Btn = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_pengguna = new javax.swing.JTable();
        Field_sum_User = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        Print_Btn = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        List_Store_Page = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        Back_Btn1 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        Field_sum_seller = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        Print = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_toko = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        List_Item_Page = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        Back_Btn2 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        Field_sum_item = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_item = new javax.swing.JTable();
        Print_a = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        List_Category_Page = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        Delete_btn = new javax.swing.JLabel();
        Simpan_Ctg = new javax.swing.JLabel();
        Simpan_Upctg = new javax.swing.JLabel();
        Back_Btn3 = new javax.swing.JLabel();
        Upctg_Text = new javax.swing.JLabel();
        Upctg_TextField = new javax.swing.JTextField();
        Upnewctg_Text = new javax.swing.JLabel();
        Upnewctg_TextField = new javax.swing.JTextField();
        ctg_combo = new javax.swing.JComboBox<>();
        Field_sum_ctg = new javax.swing.JTextField();
        ctg_combo_1 = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_ctg = new javax.swing.JTable();
        jLabel31 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        List_Subcategory_Page = new javax.swing.JScrollPane();
        jPanel9 = new javax.swing.JPanel();
        Delete_subbtn = new javax.swing.JLabel();
        Upnewsubctg1_TextField = new javax.swing.JTextField();
        subctg_combo1 = new javax.swing.JComboBox<>();
        Simpan_Upsubctg = new javax.swing.JLabel();
        Upsubctg_TextField = new javax.swing.JTextField();
        Upnewsubctg1_Text = new javax.swing.JLabel();
        Field_sum_subctg = new javax.swing.JTextField();
        subctg_combo2 = new javax.swing.JComboBox<>();
        Simpan_subctg = new javax.swing.JLabel();
        subctg_combo = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        tb_subctg = new javax.swing.JTable();
        Upsubctg_Text = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        Back_Btn4 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        List_Payment_Page = new javax.swing.JScrollPane();
        jPanel10 = new javax.swing.JPanel();
        Uppay_TextField = new javax.swing.JTextField();
        Field_sum_payment = new javax.swing.JTextField();
        Uppayment_Text = new javax.swing.JLabel();
        payment_combo_1 = new javax.swing.JComboBox<>();
        Simpan_Payment = new javax.swing.JLabel();
        Deletepay_btn = new javax.swing.JLabel();
        payment_combo = new javax.swing.JComboBox<>();
        Upnewpay_TextField = new javax.swing.JTextField();
        Upnewctg_Text1 = new javax.swing.JLabel();
        Simpan_Uppay = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tb_payment = new javax.swing.JTable();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        Back_Btn5 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        List_Expedition_Page = new javax.swing.JScrollPane();
        jPanel11 = new javax.swing.JPanel();
        Back_Btn6 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tb_jaskir = new javax.swing.JTable();
        Upjaskir_Text = new javax.swing.JLabel();
        Upjaskir_TextField = new javax.swing.JTextField();
        Simpan_Jaskir = new javax.swing.JLabel();
        jaskir_combo_1 = new javax.swing.JComboBox<>();
        Delete_Jaskir_btn = new javax.swing.JLabel();
        jaskir_combo = new javax.swing.JComboBox<>();
        Upnewjaskir_Text = new javax.swing.JLabel();
        Upnewjaskir_TextField = new javax.swing.JTextField();
        Simpan_UpJaskir = new javax.swing.JLabel();
        Field_sum_jaskir = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        Laporan_Page = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        Field_sum_rpt = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        Print_rpt_Btn = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tb_transaksi = new javax.swing.JTable();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        About_Us_Page = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Search_Bar.setPreferredSize(new java.awt.Dimension(1920, 221));
        Search_Bar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LogOut_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/LogOut Btn.png"))); // NOI18N
        LogOut_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogOut_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LogOut_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LogOut_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                LogOut_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                LogOut_BtnMouseReleased(evt);
            }
        });
        Search_Bar.add(LogOut_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1657, 41, -1, -1));

        Logo_Picture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/LoginPage/Logo.png"))); // NOI18N
        Logo_Picture.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Logo_Picture.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Logo_PictureMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Logo_PictureMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Logo_PictureMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Logo_PictureMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Logo_PictureMouseReleased(evt);
            }
        });
        Search_Bar.add(Logo_Picture, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, -1, -1));

        Laporan_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Laporan.png"))); // NOI18N
        Laporan_Btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Laporan_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Laporan_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Laporan_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Laporan_BtnMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Laporan_BtnMouseReleased(evt);
            }
        });
        Search_Bar.add(Laporan_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(952, 130, -1, -1));

        Telusuri_Data_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Telusuri Data.png"))); // NOI18N
        Search_Bar.add(Telusuri_Data_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, -1, -1));

        Home_Btn_eft2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Rumah effect 2.png"))); // NOI18N
        Search_Bar.add(Home_Btn_eft2, new org.netbeans.lib.awtextra.AbsoluteConstraints(668, 128, -1, -1));

        Home_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Rumah.png"))); // NOI18N
        Home_Btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Home_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Home_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Home_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Home_BtnMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Home_BtnMouseReleased(evt);
            }
        });
        Search_Bar.add(Home_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(668, 128, -1, -1));

        Cari_Data_TextField.setBackground(new java.awt.Color(235, 239, 246));
        Cari_Data_TextField.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        Cari_Data_TextField.setForeground(new java.awt.Color(102, 102, 102));
        Cari_Data_TextField.setBorder(null);
        Cari_Data_TextField.setCaretColor(new java.awt.Color(102, 102, 102));
        Cari_Data_TextField.setOpaque(false);
        Cari_Data_TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Cari_Data_TextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Cari_Data_TextFieldFocusLost(evt);
            }
        });
        Search_Bar.add(Cari_Data_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 65, 500, 30));

        Tentang_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Tentang.png"))); // NOI18N
        Tentang_Btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Tentang_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tentang_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Tentang_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Tentang_BtnMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Tentang_BtnMouseReleased(evt);
            }
        });
        Search_Bar.add(Tentang_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1095, 128, -1, -1));

        Data_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Data.png"))); // NOI18N
        Data_Btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Data_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Data_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Data_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Data_BtnMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Data_BtnMouseReleased(evt);
            }
        });
        Search_Bar.add(Data_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 128, -1, -1));

        Search_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Search_Btn.png"))); // NOI18N
        Search_Btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Search_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Search_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Search_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Search_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Search_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Search_BtnMouseReleased(evt);
            }
        });
        Search_Bar.add(Search_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1207, 44, -1, -1));

        Search_Bar_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Group 86.png"))); // NOI18N
        Search_Bar.add(Search_Bar_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1940, -1));

        getContentPane().add(Search_Bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Admin_Main_Panel.setLayout(new java.awt.CardLayout());

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        More_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Admin Page/More Btn.png"))); // NOI18N
        More_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                More_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                More_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                More_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                More_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                More_BtnMouseReleased(evt);
            }
        });
        jPanel1.add(More_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 1830, -1, -1));

        Item_List_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Admin Page/Daftar Barang Btn.png"))); // NOI18N
        Item_List_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Item_List_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Item_List_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Item_List_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Item_List_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Item_List_BtnMouseReleased(evt);
            }
        });
        jPanel1.add(Item_List_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 1640, -1, -1));

        Toko_List_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Admin Page/Daftar Toko.png"))); // NOI18N
        Toko_List_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Toko_List_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Toko_List_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Toko_List_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Toko_List_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Toko_List_BtnMouseReleased(evt);
            }
        });
        jPanel1.add(Toko_List_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 1160, -1, -1));

        User_List_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Admin Page/Daftar Pengguna Btn.png"))); // NOI18N
        User_List_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                User_List_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                User_List_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                User_List_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                User_List_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                User_List_BtnMouseReleased(evt);
            }
        });
        jPanel1.add(User_List_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 1160, -1, -1));

        Admin_Foto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Admin Card H.png"))); // NOI18N
        jPanel1.add(Admin_Foto, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 1190, -1, 1020));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Home Page.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Home_Page.setViewportView(jPanel1);

        Admin_Main_Panel.add(Home_Page, "card2");

        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Daftar_Jaskir_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Admin Page/Mitra Kirim Btn.png"))); // NOI18N
        Daftar_Jaskir_Btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Daftar_Jaskir_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Daftar_Jaskir_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Daftar_Jaskir_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Daftar_Jaskir_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Daftar_Jaskir_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Daftar_Jaskir_BtnMouseReleased(evt);
            }
        });
        jPanel2.add(Daftar_Jaskir_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 1730, -1, -1));

        Daftar_Payment_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Admin Page/Mitra Pembayaran Btn.png"))); // NOI18N
        Daftar_Payment_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Daftar_Payment_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Daftar_Payment_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Daftar_Payment_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Daftar_Payment_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Daftar_Payment_BtnMouseReleased(evt);
            }
        });
        jPanel2.add(Daftar_Payment_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 2170, -1, -1));

        Daftar_Barang_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Admin Page/Daftar Barang A1 Btn.png"))); // NOI18N
        Daftar_Barang_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Daftar_Barang_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Daftar_Barang_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Daftar_Barang_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Daftar_Barang_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Daftar_Barang_BtnMouseReleased(evt);
            }
        });
        jPanel2.add(Daftar_Barang_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 1290, -1, -1));

        Daftar_Toko_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Admin Page/Daftar Toko A1 Btn.png"))); // NOI18N
        Daftar_Toko_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Daftar_Toko_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Daftar_Toko_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Daftar_Toko_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Daftar_Toko_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Daftar_Toko_BtnMouseReleased(evt);
            }
        });
        jPanel2.add(Daftar_Toko_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 1290, -1, -1));

        Subktg_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Admin Page/Sub Kategori A1 Btn.png"))); // NOI18N
        Subktg_Btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Subktg_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Subktg_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Subktg_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Subktg_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Subktg_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Subktg_BtnMouseReleased(evt);
            }
        });
        jPanel2.add(Subktg_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 1730, -1, -1));

        Daftar_Kategori_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Admin Page/Kategori A1 Btn.png"))); // NOI18N
        Daftar_Kategori_Btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Daftar_Kategori_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Daftar_Kategori_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Daftar_Kategori_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Daftar_Kategori_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Daftar_Kategori_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Daftar_Kategori_BtnMouseReleased(evt);
            }
        });
        jPanel2.add(Daftar_Kategori_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 1730, -1, -1));

        Daftar_Pengguna_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Admin Page/Daftar Pengguna Btn.png"))); // NOI18N
        Daftar_Pengguna_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Daftar_Pengguna_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Daftar_Pengguna_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Daftar_Pengguna_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Daftar_Pengguna_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Daftar_Pengguna_BtnMouseReleased(evt);
            }
        });
        jPanel2.add(Daftar_Pengguna_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 1290, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Data_Page.png"))); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(1920, 3454));
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 3960));

        Data_Page.setViewportView(jPanel2);

        Admin_Main_Panel.add(Data_Page, "card3");

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Data _ Daftar Pengguna.png"))); // NOI18N
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 57, -1, -1));

        Back_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Back_Btn.png"))); // NOI18N
        Back_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Back_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Back_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Back_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Back_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Back_BtnMouseReleased(evt);
            }
        });
        jPanel5.add(Back_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 30, -1, -1));

        jScrollPane1.setBackground(new java.awt.Color(130, 80, 74));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(831, 795));

        tb_pengguna.setAutoCreateRowSorter(true);
        tb_pengguna.setBackground(new java.awt.Color(130, 80, 74));
        tb_pengguna.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        tb_pengguna.setForeground(new java.awt.Color(253, 253, 252));
        tb_pengguna.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tb_pengguna.setGridColor(new java.awt.Color(130, 80, 74));
        tb_pengguna.setOpaque(false);
        tb_pengguna.setRowHeight(50);
        tb_pengguna.setSelectionBackground(new java.awt.Color(220, 139, 27));
        jScrollPane1.setViewportView(tb_pengguna);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(944, 1115, -1, -1));

        Field_sum_User.setEditable(false);
        Field_sum_User.setBackground(new java.awt.Color(130, 80, 74));
        Field_sum_User.setFont(new java.awt.Font("Serif", 1, 48)); // NOI18N
        Field_sum_User.setForeground(new java.awt.Color(253, 253, 252));
        Field_sum_User.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Field_sum_User.setBorder(null);
        jPanel5.add(Field_sum_User, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 1224, 280, 190));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Hiasan_Pengguna_1.png"))); // NOI18N
        jPanel5.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 1520, -1, -1));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Hiasan_Pengguna.png"))); // NOI18N
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 1020, -1, -1));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Rectangle 513.png"))); // NOI18N
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(1274, 1020, -1, -1));

        Print_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Print Btn.png"))); // NOI18N
        Print_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Print_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Print_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Print_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Print_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Print_BtnMouseReleased(evt);
            }
        });
        jPanel5.add(Print_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 1020, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Daftar Pengguna Page.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        List_User_Page.setViewportView(jPanel5);

        Admin_Main_Panel.add(List_User_Page, "card6");

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Back_Btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Back_Btn.png"))); // NOI18N
        Back_Btn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Back_Btn1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Back_Btn1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Back_Btn1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Back_Btn1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Back_Btn1MouseReleased(evt);
            }
        });
        jPanel6.add(Back_Btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 30, -1, -1));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Data _ Daftar Toko.png"))); // NOI18N
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 57, -1, -1));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Hiasan_Pengguna_1.png"))); // NOI18N
        jPanel6.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 1520, -1, -1));

        Field_sum_seller.setEditable(false);
        Field_sum_seller.setBackground(new java.awt.Color(130, 80, 74));
        Field_sum_seller.setFont(new java.awt.Font("Serif", 1, 48)); // NOI18N
        Field_sum_seller.setForeground(new java.awt.Color(253, 253, 252));
        Field_sum_seller.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel6.add(Field_sum_seller, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 1254, 280, 160));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/HIasan Toko.png"))); // NOI18N
        jPanel6.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 1020, -1, -1));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Rectangle 513.png"))); // NOI18N
        jPanel6.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(1274, 1020, -1, -1));

        Print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Print Btn.png"))); // NOI18N
        Print.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PrintMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PrintMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PrintMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PrintMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                PrintMouseReleased(evt);
            }
        });
        jPanel6.add(Print, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 1020, -1, -1));

        jScrollPane2.setBackground(new java.awt.Color(130, 80, 74));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(831, 795));

        tb_toko.setAutoCreateRowSorter(true);
        tb_toko.setBackground(new java.awt.Color(130, 80, 74));
        tb_toko.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        tb_toko.setForeground(new java.awt.Color(253, 253, 252));
        tb_toko.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tb_toko.setGridColor(new java.awt.Color(130, 80, 74));
        tb_toko.setOpaque(false);
        tb_toko.setRowHeight(50);
        tb_toko.setSelectionBackground(new java.awt.Color(220, 139, 27));
        jScrollPane2.setViewportView(tb_toko);

        jPanel6.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(944, 1115, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Daftar Toko Page.png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        List_Store_Page.setViewportView(jPanel6);

        Admin_Main_Panel.add(List_Store_Page, "card7");

        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Back_Btn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Back_Btn.png"))); // NOI18N
        Back_Btn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Back_Btn2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Back_Btn2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Back_Btn2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Back_Btn2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Back_Btn2MouseReleased(evt);
            }
        });
        jPanel8.add(Back_Btn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 30, -1, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Data _ Daftar Barang.png"))); // NOI18N
        jPanel8.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 57, -1, -1));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Hiasan_Pengguna_1.png"))); // NOI18N
        jPanel8.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 1520, -1, -1));

        Field_sum_item.setEditable(false);
        Field_sum_item.setBackground(new java.awt.Color(130, 80, 74));
        Field_sum_item.setFont(new java.awt.Font("Serif", 1, 48)); // NOI18N
        Field_sum_item.setForeground(new java.awt.Color(253, 253, 252));
        Field_sum_item.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Field_sum_item.setBorder(null);
        jPanel8.add(Field_sum_item, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 1254, 280, 160));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Hiasan Barang.png"))); // NOI18N
        jPanel8.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 1020, -1, -1));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Rectangle 513.png"))); // NOI18N
        jPanel8.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(1274, 1020, -1, -1));

        jScrollPane3.setBackground(new java.awt.Color(130, 80, 74));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(831, 795));

        tb_item.setAutoCreateRowSorter(true);
        tb_item.setBackground(new java.awt.Color(130, 80, 74));
        tb_item.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        tb_item.setForeground(new java.awt.Color(253, 253, 252));
        tb_item.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tb_item.setGridColor(new java.awt.Color(130, 80, 74));
        tb_item.setOpaque(false);
        tb_item.setRowHeight(50);
        tb_item.setSelectionBackground(new java.awt.Color(220, 139, 27));
        jScrollPane3.setViewportView(tb_item);

        jPanel8.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(944, 1115, -1, -1));

        Print_a.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Print Btn.png"))); // NOI18N
        Print_a.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Print_aMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Print_aMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Print_aMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Print_aMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Print_aMouseReleased(evt);
            }
        });
        jPanel8.add(Print_a, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 1020, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Daftar Barang Page.png"))); // NOI18N
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel8.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        List_Item_Page.setViewportView(jPanel8);

        Admin_Main_Panel.add(List_Item_Page, "card9");

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Delete_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Hapus Data Ctg.png"))); // NOI18N
        Delete_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Delete_btnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Delete_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Delete_btnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Delete_btnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Delete_btnMouseReleased(evt);
            }
        });
        jPanel7.add(Delete_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 1810, -1, -1));

        Simpan_Ctg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Simpan Data Ctg.png"))); // NOI18N
        Simpan_Ctg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Simpan_CtgMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Simpan_CtgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Simpan_CtgMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Simpan_CtgMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Simpan_CtgMouseReleased(evt);
            }
        });
        jPanel7.add(Simpan_Ctg, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 1810, -1, -1));

        Simpan_Upctg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Simpan A1 Data Ctg.png"))); // NOI18N
        Simpan_Upctg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Simpan_UpctgMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Simpan_UpctgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Simpan_UpctgMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Simpan_UpctgMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Simpan_UpctgMouseReleased(evt);
            }
        });
        jPanel7.add(Simpan_Upctg, new org.netbeans.lib.awtextra.AbsoluteConstraints(1600, 1830, -1, -1));

        Back_Btn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Back_Btn.png"))); // NOI18N
        Back_Btn3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Back_Btn3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Back_Btn3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Back_Btn3MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Back_Btn3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Back_Btn3MouseReleased(evt);
            }
        });
        jPanel7.add(Back_Btn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 30, -1, -1));

        Upctg_Text.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Tambahkan Kategori Baru.png"))); // NOI18N
        jPanel7.add(Upctg_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 1710, -1, -1));

        Upctg_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Upctg_TextField.setForeground(new java.awt.Color(255, 255, 255));
        Upctg_TextField.setBorder(null);
        Upctg_TextField.setOpaque(false);
        Upctg_TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Upctg_TextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Upctg_TextFieldFocusLost(evt);
            }
        });
        Upctg_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Upctg_TextFieldKeyPressed(evt);
            }
        });
        jPanel7.add(Upctg_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 1705, 270, 30));

        Upnewctg_Text.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Masukkan Kategori Baru.png"))); // NOI18N
        jPanel7.add(Upnewctg_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(1450, 1762, -1, -1));

        Upnewctg_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Upnewctg_TextField.setForeground(new java.awt.Color(255, 255, 255));
        Upnewctg_TextField.setBorder(null);
        Upnewctg_TextField.setOpaque(false);
        Upnewctg_TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Upnewctg_TextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Upnewctg_TextFieldFocusLost(evt);
            }
        });
        Upnewctg_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Upnewctg_TextFieldKeyPressed(evt);
            }
        });
        jPanel7.add(Upnewctg_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1450, 1755, 230, 30));

        ctg_combo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ctg_comboMouseClicked(evt);
            }
        });
        ctg_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ctg_comboActionPerformed(evt);
            }
        });
        jPanel7.add(ctg_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1416, 1696, 270, 40));

        Field_sum_ctg.setEditable(false);
        Field_sum_ctg.setBackground(new java.awt.Color(130, 80, 74));
        Field_sum_ctg.setFont(new java.awt.Font("Serif", 1, 48)); // NOI18N
        Field_sum_ctg.setForeground(new java.awt.Color(253, 253, 252));
        Field_sum_ctg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Field_sum_ctg.setBorder(null);
        jPanel7.add(Field_sum_ctg, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 1254, 280, 160));

        ctg_combo_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ctg_combo_1MouseClicked(evt);
            }
        });
        jPanel7.add(ctg_combo_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 1718, 320, 50));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Admin Page/Hiasan admin.png"))); // NOI18N
        jPanel7.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 1020, -1, -1));

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Hiasan Kategori.png"))); // NOI18N
        jPanel7.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 1020, -1, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Data _ Daftar Kategori.png"))); // NOI18N
        jPanel7.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 57, -1, -1));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/IDU Kategori.png"))); // NOI18N
        jPanel7.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 1520, -1, -1));

        jScrollPane4.setBackground(new java.awt.Color(130, 80, 74));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(831, 795));

        tb_ctg.setAutoCreateRowSorter(true);
        tb_ctg.setBackground(new java.awt.Color(130, 80, 74));
        tb_ctg.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        tb_ctg.setForeground(new java.awt.Color(253, 253, 252));
        tb_ctg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tb_ctg.setGridColor(new java.awt.Color(130, 80, 74));
        tb_ctg.setOpaque(false);
        tb_ctg.setRowHeight(50);
        tb_ctg.setSelectionBackground(new java.awt.Color(220, 139, 27));
        jScrollPane4.setViewportView(tb_ctg);

        jPanel7.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(944, 1115, -1, 300));

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Daftar Kategori Page.png"))); // NOI18N
        jLabel31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel31MouseClicked(evt);
            }
        });
        jPanel7.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Daftar Kategori Page.png"))); // NOI18N
        jPanel7.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 3230));

        List_Category_Page.setViewportView(jPanel7);

        Admin_Main_Panel.add(List_Category_Page, "card8");

        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Delete_subbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Hapus Data Ctg.png"))); // NOI18N
        Delete_subbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Delete_subbtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Delete_subbtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Delete_subbtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Delete_subbtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Delete_subbtnMouseReleased(evt);
            }
        });
        jPanel9.add(Delete_subbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 1810, -1, -1));

        Upnewsubctg1_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Upnewsubctg1_TextField.setForeground(new java.awt.Color(255, 255, 255));
        Upnewsubctg1_TextField.setBorder(null);
        Upnewsubctg1_TextField.setOpaque(false);
        Upnewsubctg1_TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Upnewsubctg1_TextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Upnewsubctg1_TextFieldFocusLost(evt);
            }
        });
        Upnewsubctg1_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Upnewsubctg1_TextFieldKeyPressed(evt);
            }
        });
        jPanel9.add(Upnewsubctg1_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1450, 1755, 230, 30));

        subctg_combo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                subctg_combo1MouseClicked(evt);
            }
        });
        subctg_combo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subctg_combo1ActionPerformed(evt);
            }
        });
        jPanel9.add(subctg_combo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 1666, 320, 50));

        Simpan_Upsubctg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Simpan A1 Data Ctg.png"))); // NOI18N
        Simpan_Upsubctg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Simpan_UpsubctgMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Simpan_UpsubctgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Simpan_UpsubctgMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Simpan_UpsubctgMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Simpan_UpsubctgMouseReleased(evt);
            }
        });
        jPanel9.add(Simpan_Upsubctg, new org.netbeans.lib.awtextra.AbsoluteConstraints(1600, 1830, -1, -1));

        Upsubctg_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Upsubctg_TextField.setForeground(new java.awt.Color(255, 255, 255));
        Upsubctg_TextField.setBorder(null);
        Upsubctg_TextField.setOpaque(false);
        Upsubctg_TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Upsubctg_TextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Upsubctg_TextFieldFocusLost(evt);
            }
        });
        Upsubctg_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Upsubctg_TextFieldKeyPressed(evt);
            }
        });
        jPanel9.add(Upsubctg_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 1740, 270, 30));

        Upnewsubctg1_Text.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Masukkan Subkategori Baru.png"))); // NOI18N
        jPanel9.add(Upnewsubctg1_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(1450, 1762, 210, -1));

        Field_sum_subctg.setEditable(false);
        Field_sum_subctg.setBackground(new java.awt.Color(130, 80, 74));
        Field_sum_subctg.setFont(new java.awt.Font("Serif", 1, 48)); // NOI18N
        Field_sum_subctg.setForeground(new java.awt.Color(253, 253, 252));
        Field_sum_subctg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Field_sum_subctg.setBorder(null);
        jPanel9.add(Field_sum_subctg, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 1254, 280, 160));

        subctg_combo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                subctg_combo2MouseClicked(evt);
            }
        });
        subctg_combo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subctg_combo2ActionPerformed(evt);
            }
        });
        jPanel9.add(subctg_combo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 1704, 320, 50));

        Simpan_subctg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Simpan Data Ctg.png"))); // NOI18N
        Simpan_subctg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Simpan_subctgMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Simpan_subctgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Simpan_subctgMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Simpan_subctgMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Simpan_subctgMouseReleased(evt);
            }
        });
        jPanel9.add(Simpan_subctg, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 1810, -1, -1));

        subctg_combo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                subctg_comboMouseClicked(evt);
            }
        });
        subctg_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subctg_comboActionPerformed(evt);
            }
        });
        jPanel9.add(subctg_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1416, 1696, 270, 40));

        jScrollPane5.setBackground(new java.awt.Color(130, 80, 74));
        jScrollPane5.setPreferredSize(new java.awt.Dimension(831, 795));

        tb_subctg.setAutoCreateRowSorter(true);
        tb_subctg.setBackground(new java.awt.Color(130, 80, 74));
        tb_subctg.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        tb_subctg.setForeground(new java.awt.Color(253, 253, 252));
        tb_subctg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tb_subctg.setGridColor(new java.awt.Color(130, 80, 74));
        tb_subctg.setOpaque(false);
        tb_subctg.setRowHeight(50);
        tb_subctg.setSelectionBackground(new java.awt.Color(220, 139, 27));
        jScrollPane5.setViewportView(tb_subctg);

        jPanel9.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(944, 1115, -1, 300));

        Upsubctg_Text.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Tambahkan Subategori Baru.png"))); // NOI18N
        jPanel9.add(Upsubctg_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 1745, -1, -1));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Hiasan Subkategori.png"))); // NOI18N
        jPanel9.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 1020, -1, -1));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Admin Page/Hiasan admin.png"))); // NOI18N
        jPanel9.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 1020, -1, -1));

        Back_Btn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Back_Btn.png"))); // NOI18N
        Back_Btn4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Back_Btn4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Back_Btn4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Back_Btn4MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Back_Btn4MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Back_Btn4MouseReleased(evt);
            }
        });
        jPanel9.add(Back_Btn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 30, -1, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Data _ Daftar Sub Kategori.png"))); // NOI18N
        jPanel9.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 57, -1, -1));

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/IDU Subkategori.png"))); // NOI18N
        jPanel9.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 1520, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Daftar Subkategori Page.png"))); // NOI18N
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        jPanel9.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        List_Subcategory_Page.setViewportView(jPanel9);

        Admin_Main_Panel.add(List_Subcategory_Page, "card10");

        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Uppay_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Uppay_TextField.setForeground(new java.awt.Color(255, 255, 255));
        Uppay_TextField.setBorder(null);
        Uppay_TextField.setOpaque(false);
        Uppay_TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Uppay_TextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Uppay_TextFieldFocusLost(evt);
            }
        });
        Uppay_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Uppay_TextFieldKeyPressed(evt);
            }
        });
        jPanel10.add(Uppay_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(295, 1705, 270, 30));

        Field_sum_payment.setEditable(false);
        Field_sum_payment.setBackground(new java.awt.Color(130, 80, 74));
        Field_sum_payment.setFont(new java.awt.Font("Serif", 1, 48)); // NOI18N
        Field_sum_payment.setForeground(new java.awt.Color(253, 253, 252));
        Field_sum_payment.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Field_sum_payment.setBorder(null);
        jPanel10.add(Field_sum_payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 1254, 280, 160));

        Uppayment_Text.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Tambahkan Mitra Pembayaran Baru.png"))); // NOI18N
        jPanel10.add(Uppayment_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 1710, -1, -1));

        payment_combo_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                payment_combo_1MouseClicked(evt);
            }
        });
        jPanel10.add(payment_combo_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 1718, 320, 50));

        Simpan_Payment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Simpan Data Ctg.png"))); // NOI18N
        Simpan_Payment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Simpan_PaymentMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Simpan_PaymentMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Simpan_PaymentMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Simpan_PaymentMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Simpan_PaymentMouseReleased(evt);
            }
        });
        jPanel10.add(Simpan_Payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 1810, -1, -1));

        Deletepay_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Hapus Data Ctg.png"))); // NOI18N
        Deletepay_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Deletepay_btnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Deletepay_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Deletepay_btnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Deletepay_btnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Deletepay_btnMouseReleased(evt);
            }
        });
        jPanel10.add(Deletepay_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 1810, -1, -1));

        payment_combo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                payment_comboMouseClicked(evt);
            }
        });
        payment_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payment_comboActionPerformed(evt);
            }
        });
        jPanel10.add(payment_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1416, 1696, 270, 40));

        Upnewpay_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Upnewpay_TextField.setForeground(new java.awt.Color(255, 255, 255));
        Upnewpay_TextField.setBorder(null);
        Upnewpay_TextField.setOpaque(false);
        Upnewpay_TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Upnewpay_TextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Upnewpay_TextFieldFocusLost(evt);
            }
        });
        Upnewpay_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Upnewpay_TextFieldKeyPressed(evt);
            }
        });
        jPanel10.add(Upnewpay_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1450, 1755, 230, 30));

        Upnewctg_Text1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Masukkan Mitra Pembayaran Baru.png"))); // NOI18N
        jPanel10.add(Upnewctg_Text1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1450, 1762, -1, -1));

        Simpan_Uppay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Simpan A1 Data Ctg.png"))); // NOI18N
        Simpan_Uppay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Simpan_UppayMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Simpan_UppayMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Simpan_UppayMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Simpan_UppayMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Simpan_UppayMouseReleased(evt);
            }
        });
        jPanel10.add(Simpan_Uppay, new org.netbeans.lib.awtextra.AbsoluteConstraints(1600, 1830, -1, -1));

        jScrollPane6.setBackground(new java.awt.Color(130, 80, 74));
        jScrollPane6.setPreferredSize(new java.awt.Dimension(831, 795));

        tb_payment.setAutoCreateRowSorter(true);
        tb_payment.setBackground(new java.awt.Color(130, 80, 74));
        tb_payment.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        tb_payment.setForeground(new java.awt.Color(253, 253, 252));
        tb_payment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tb_payment.setGridColor(new java.awt.Color(130, 80, 74));
        tb_payment.setOpaque(false);
        tb_payment.setRowHeight(50);
        tb_payment.setSelectionBackground(new java.awt.Color(220, 139, 27));
        jScrollPane6.setViewportView(tb_payment);

        jPanel10.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(944, 1115, -1, 300));

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Admin Page/Hiasan admin.png"))); // NOI18N
        jPanel10.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 1020, -1, -1));

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Hiasan Payment.png"))); // NOI18N
        jPanel10.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 1020, -1, -1));

        Back_Btn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Back_Btn.png"))); // NOI18N
        Back_Btn5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Back_Btn5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Back_Btn5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Back_Btn5MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Back_Btn5MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Back_Btn5MouseReleased(evt);
            }
        });
        jPanel10.add(Back_Btn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 30, -1, -1));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Data _ Daftar Mitra Pembayaran.png"))); // NOI18N
        jPanel10.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 57, -1, -1));

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/IDU Mitra Pembayaran.png"))); // NOI18N
        jPanel10.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 1520, -1, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Daftar Mitra Pembayaran.png"))); // NOI18N
        jLabel13.setToolTipText("");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        jPanel10.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        List_Payment_Page.setViewportView(jPanel10);

        Admin_Main_Panel.add(List_Payment_Page, "card11");

        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Back_Btn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Back_Btn.png"))); // NOI18N
        Back_Btn6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Back_Btn6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Back_Btn6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Back_Btn6MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Back_Btn6MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Back_Btn6MouseReleased(evt);
            }
        });
        jPanel11.add(Back_Btn6, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 30, -1, -1));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Data _ Jasa Kirim.png"))); // NOI18N
        jPanel11.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 57, -1, -1));

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Admin Page/Hiasan admin.png"))); // NOI18N
        jPanel11.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 1020, -1, -1));

        jScrollPane7.setBackground(new java.awt.Color(130, 80, 74));
        jScrollPane7.setPreferredSize(new java.awt.Dimension(831, 795));

        tb_jaskir.setAutoCreateRowSorter(true);
        tb_jaskir.setBackground(new java.awt.Color(130, 80, 74));
        tb_jaskir.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        tb_jaskir.setForeground(new java.awt.Color(253, 253, 252));
        tb_jaskir.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tb_jaskir.setGridColor(new java.awt.Color(130, 80, 74));
        tb_jaskir.setOpaque(false);
        tb_jaskir.setRowHeight(50);
        tb_jaskir.setSelectionBackground(new java.awt.Color(220, 139, 27));
        jScrollPane7.setViewportView(tb_jaskir);

        jPanel11.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(944, 1115, -1, 300));

        Upjaskir_Text.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Tambahkan Mitra Pengiriman Baru.png"))); // NOI18N
        jPanel11.add(Upjaskir_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 1710, -1, -1));

        Upjaskir_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Upjaskir_TextField.setForeground(new java.awt.Color(255, 255, 255));
        Upjaskir_TextField.setBorder(null);
        Upjaskir_TextField.setOpaque(false);
        Upjaskir_TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Upjaskir_TextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Upjaskir_TextFieldFocusLost(evt);
            }
        });
        Upjaskir_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Upjaskir_TextFieldKeyPressed(evt);
            }
        });
        jPanel11.add(Upjaskir_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(295, 1705, 270, 30));

        Simpan_Jaskir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Simpan Data Ctg.png"))); // NOI18N
        Simpan_Jaskir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Simpan_JaskirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Simpan_JaskirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Simpan_JaskirMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Simpan_JaskirMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Simpan_JaskirMouseReleased(evt);
            }
        });
        jPanel11.add(Simpan_Jaskir, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 1810, -1, -1));

        jaskir_combo_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jaskir_combo_1MouseClicked(evt);
            }
        });
        jPanel11.add(jaskir_combo_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 1718, 320, 50));

        Delete_Jaskir_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Hapus Data Ctg.png"))); // NOI18N
        Delete_Jaskir_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Delete_Jaskir_btnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Delete_Jaskir_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Delete_Jaskir_btnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Delete_Jaskir_btnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Delete_Jaskir_btnMouseReleased(evt);
            }
        });
        jPanel11.add(Delete_Jaskir_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 1810, -1, -1));

        jaskir_combo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jaskir_comboMouseClicked(evt);
            }
        });
        jaskir_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jaskir_comboActionPerformed(evt);
            }
        });
        jPanel11.add(jaskir_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1416, 1696, 270, 40));

        Upnewjaskir_Text.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Masukkan Mitra Pengiriman Baru.png"))); // NOI18N
        jPanel11.add(Upnewjaskir_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(1450, 1762, -1, -1));

        Upnewjaskir_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Upnewjaskir_TextField.setForeground(new java.awt.Color(255, 255, 255));
        Upnewjaskir_TextField.setBorder(null);
        Upnewjaskir_TextField.setOpaque(false);
        Upnewjaskir_TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Upnewjaskir_TextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Upnewjaskir_TextFieldFocusLost(evt);
            }
        });
        Upnewjaskir_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Upnewjaskir_TextFieldKeyPressed(evt);
            }
        });
        jPanel11.add(Upnewjaskir_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1450, 1755, 230, 30));

        Simpan_UpJaskir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Simpan A1 Data Ctg.png"))); // NOI18N
        Simpan_UpJaskir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Simpan_UpJaskirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Simpan_UpJaskirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Simpan_UpJaskirMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Simpan_UpJaskirMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Simpan_UpJaskirMouseReleased(evt);
            }
        });
        jPanel11.add(Simpan_UpJaskir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1600, 1830, -1, -1));

        Field_sum_jaskir.setEditable(false);
        Field_sum_jaskir.setBackground(new java.awt.Color(130, 80, 74));
        Field_sum_jaskir.setFont(new java.awt.Font("Serif", 1, 48)); // NOI18N
        Field_sum_jaskir.setForeground(new java.awt.Color(253, 253, 252));
        Field_sum_jaskir.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Field_sum_jaskir.setBorder(null);
        jPanel11.add(Field_sum_jaskir, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 1254, 280, 160));

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/IDU Mitra Kirim.png"))); // NOI18N
        jPanel11.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 1520, -1, -1));

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Hiasan Jaskir.png"))); // NOI18N
        jPanel11.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 1020, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Daftar_Jasa_Pengiriman_Page.png"))); // NOI18N
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        jPanel11.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        List_Expedition_Page.setViewportView(jPanel11);

        Admin_Main_Panel.add(List_Expedition_Page, "card12");

        Laporan_Page.setPreferredSize(new java.awt.Dimension(1920, 859));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Field_sum_rpt.setEditable(false);
        Field_sum_rpt.setBackground(new java.awt.Color(130, 80, 74));
        Field_sum_rpt.setFont(new java.awt.Font("Serif", 1, 48)); // NOI18N
        Field_sum_rpt.setForeground(new java.awt.Color(253, 253, 252));
        Field_sum_rpt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Field_sum_rpt.setBorder(null);
        jPanel3.add(Field_sum_rpt, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 1224, 280, 190));

        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Hiasan Transaksi.png"))); // NOI18N
        jPanel3.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 1020, -1, -1));

        Print_rpt_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Print Btn.png"))); // NOI18N
        Print_rpt_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Print_rpt_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Print_rpt_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Print_rpt_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Print_rpt_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Print_rpt_BtnMouseReleased(evt);
            }
        });
        jPanel3.add(Print_rpt_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 1020, -1, -1));

        jScrollPane8.setBackground(new java.awt.Color(130, 80, 74));
        jScrollPane8.setPreferredSize(new java.awt.Dimension(831, 795));

        tb_transaksi.setAutoCreateRowSorter(true);
        tb_transaksi.setBackground(new java.awt.Color(130, 80, 74));
        tb_transaksi.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        tb_transaksi.setForeground(new java.awt.Color(253, 253, 252));
        tb_transaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tb_transaksi.setGridColor(new java.awt.Color(130, 80, 74));
        tb_transaksi.setOpaque(false);
        tb_transaksi.setRowHeight(50);
        tb_transaksi.setSelectionBackground(new java.awt.Color(220, 139, 27));
        jScrollPane8.setViewportView(tb_transaksi);

        jPanel3.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(944, 1115, -1, -1));

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Hiasan_Pengguna_1.png"))); // NOI18N
        jPanel3.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 1520, -1, -1));

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Rectangle 513.png"))); // NOI18N
        jPanel3.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(1274, 1020, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Admin Page/Data_Page.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 3150));

        Laporan_Page.setViewportView(jPanel3);

        Admin_Main_Panel.add(Laporan_Page, "card4");

        About_Us_Page.setPreferredSize(new java.awt.Dimension(1920, 859));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/About Us Page/About Us Page.png"))); // NOI18N
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        About_Us_Page.setViewportView(jPanel4);

        Admin_Main_Panel.add(About_Us_Page, "card5");

        getContentPane().add(Admin_Main_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 1920, 859));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void User_List_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_User_List_BtnMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_User_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_User_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Pengguna Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        User_List_Btn.setIcon(Btn_User_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
          //Menghapus Panel
        Admin_Main_Panel.removeAll();
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();

        //Menambah panel
        Admin_Main_Panel.add(List_User_Page);
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();
        
        total_pengguna();
        pengguna_data();
        
         Home_Btn_eft2.setVisible(false);
        ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Data effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Data_Btn.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
         ImageIcon Btn_Home;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Home = new ImageIcon("src/Picture/Effect/Rumah.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Home_Btn.setIcon(Btn_Home);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        ImageIcon Btn_Tentang;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Tentang = new ImageIcon("src/Picture/Effect/Tentang.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Tentang_Btn.setIcon(Btn_Tentang);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        ImageIcon Btn_Laporan;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Laporan = new ImageIcon("src/Picture/Effect/Laporan.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Laporan_Btn.setIcon(Btn_Laporan);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        search_bar();
    }//GEN-LAST:event_User_List_BtnMouseClicked

    private void User_List_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_User_List_BtnMouseEntered
        // TODO add your handling code here:
       ImageIcon Btn_User_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_User_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Pengguna Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        User_List_Btn.setIcon(Btn_User_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

    }//GEN-LAST:event_User_List_BtnMouseEntered

    private void User_List_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_User_List_BtnMouseExited
       ImageIcon Btn_User_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_User_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Pengguna Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        User_List_Btn.setIcon(Btn_User_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

    }//GEN-LAST:event_User_List_BtnMouseExited

    private void User_List_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_User_List_BtnMousePressed
       ImageIcon Btn_User_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_User_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Pengguna Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        User_List_Btn.setIcon(Btn_User_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

    }//GEN-LAST:event_User_List_BtnMousePressed

    private void User_List_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_User_List_BtnMouseReleased
       ImageIcon Btn_User_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_User_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Pengguna Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        User_List_Btn.setIcon(Btn_User_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

    }//GEN-LAST:event_User_List_BtnMouseReleased

    private void Toko_List_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Toko_List_BtnMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Item_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Item_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Toko Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Toko_List_Btn.setIcon(Btn_Item_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
         ImageIcon Btn_Home;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Home = new ImageIcon("src/Picture/Effect/Rumah.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Home_Btn.setIcon(Btn_Home);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        ImageIcon Btn_Tentang;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Tentang = new ImageIcon("src/Picture/Effect/Tentang.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Tentang_Btn.setIcon(Btn_Tentang);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        ImageIcon Btn_Laporan;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Laporan = new ImageIcon("src/Picture/Effect/Laporan.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Laporan_Btn.setIcon(Btn_Laporan);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
           //Menghapus Panel
        Admin_Main_Panel.removeAll();
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();

        //Menambah panel
        Admin_Main_Panel.add(List_Store_Page);
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();
        
         Home_Btn_eft2.setVisible(false);
        ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Data effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Data_Btn.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        toko_data();
        total_toko();
        search_bar();
    }//GEN-LAST:event_Toko_List_BtnMouseClicked

    private void Toko_List_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Toko_List_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Item_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Item_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Toko Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Toko_List_Btn.setIcon(Btn_Item_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
    }//GEN-LAST:event_Toko_List_BtnMouseEntered

    private void Toko_List_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Toko_List_BtnMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Item_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Item_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Toko.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Toko_List_Btn.setIcon(Btn_Item_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
    }//GEN-LAST:event_Toko_List_BtnMouseExited

    private void Toko_List_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Toko_List_BtnMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Item_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Item_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Toko Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Toko_List_Btn.setIcon(Btn_Item_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
    }//GEN-LAST:event_Toko_List_BtnMousePressed

    private void Toko_List_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Toko_List_BtnMouseReleased
        ImageIcon Btn_Item_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Item_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Toko.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Toko_List_Btn.setIcon(Btn_Item_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
    }//GEN-LAST:event_Toko_List_BtnMouseReleased

    private void Item_List_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Item_List_BtnMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Item_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Item_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Barang Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Item_List_Btn.setIcon(Btn_Item_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
         ImageIcon Btn_Home;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Home = new ImageIcon("src/Picture/Effect/Rumah.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Home_Btn.setIcon(Btn_Home);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        ImageIcon Btn_Tentang;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Tentang = new ImageIcon("src/Picture/Effect/Tentang.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Tentang_Btn.setIcon(Btn_Tentang);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        ImageIcon Btn_Laporan;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Laporan = new ImageIcon("src/Picture/Effect/Laporan.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Laporan_Btn.setIcon(Btn_Laporan);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
           //Menghapus Panel
        Admin_Main_Panel.removeAll();
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();

        //Menambah panel
        Admin_Main_Panel.add(List_Item_Page);
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();
        
         Home_Btn_eft2.setVisible(false);
        ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Data effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Data_Btn.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        item_data();
        total_item();
        search_bar();
    }//GEN-LAST:event_Item_List_BtnMouseClicked

    private void Item_List_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Item_List_BtnMouseEntered
       ImageIcon Btn_Item_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Item_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Barang Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Item_List_Btn.setIcon(Btn_Item_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Item_List_BtnMouseEntered

    private void Item_List_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Item_List_BtnMouseExited
        // TODO add your handling code here:
       ImageIcon Btn_Item_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Item_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Barang Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Item_List_Btn.setIcon(Btn_Item_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Item_List_BtnMouseExited

    private void Item_List_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Item_List_BtnMousePressed
        // TODO add your handling code here:
       ImageIcon Btn_Item_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Item_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Barang Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Item_List_Btn.setIcon(Btn_Item_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Item_List_BtnMousePressed

    private void Item_List_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Item_List_BtnMouseReleased
        // TODO add your handling code here:
       ImageIcon Btn_Item_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Item_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Barang Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Item_List_Btn.setIcon(Btn_Item_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Item_List_BtnMouseReleased

    private void More_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_More_BtnMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_More;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_More = new ImageIcon("src/Picture/Effect/Effect Admin Page/More Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        More_Btn.setIcon(Btn_More);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
         ImageIcon Btn_Home;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Home = new ImageIcon("src/Picture/Effect/Rumah.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Home_Btn.setIcon(Btn_Home);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        ImageIcon Btn_Tentang;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Tentang = new ImageIcon("src/Picture/Effect/Tentang.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Tentang_Btn.setIcon(Btn_Tentang);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        ImageIcon Btn_Laporan;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Laporan = new ImageIcon("src/Picture/Effect/Laporan.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Laporan_Btn.setIcon(Btn_Laporan);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
           //Menghapus Panel
        Admin_Main_Panel.removeAll();
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();

        //Menambah panel
        Admin_Main_Panel.add(Data_Page);
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();
        
         Home_Btn_eft2.setVisible(false);
        ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Data effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Data_Btn.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_More_BtnMouseClicked

    private void More_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_More_BtnMouseEntered
         // TODO add your handling code here:
        ImageIcon Btn_More;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_More = new ImageIcon("src/Picture/Effect/Effect Admin Page/More Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        More_Btn.setIcon(Btn_More);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_More_BtnMouseEntered

    private void More_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_More_BtnMouseExited
        // TODO add your handling code here:
         ImageIcon Btn_More;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_More = new ImageIcon("src/Picture/Effect/Effect Admin Page/More Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        More_Btn.setIcon(Btn_More);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_More_BtnMouseExited

    private void More_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_More_BtnMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_More;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_More = new ImageIcon("src/Picture/Effect/Effect Admin Page/More Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        More_Btn.setIcon(Btn_More);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_More_BtnMousePressed

    private void More_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_More_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_More;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_More = new ImageIcon("src/Picture/Effect/Effect Admin Page/More Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        More_Btn.setIcon(Btn_More);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_More_BtnMouseReleased

    private void Daftar_Pengguna_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Pengguna_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_User_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_User_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Pengguna Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Pengguna_Btn.setIcon(Btn_User_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Daftar_Pengguna_BtnMouseReleased

    private void Daftar_Pengguna_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Pengguna_BtnMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_User_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_User_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Pengguna Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Pengguna_Btn.setIcon(Btn_User_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Daftar_Pengguna_BtnMousePressed

    private void Daftar_Pengguna_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Pengguna_BtnMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_User_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_User_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Pengguna Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Pengguna_Btn.setIcon(Btn_User_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Daftar_Pengguna_BtnMouseExited

    private void Daftar_Pengguna_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Pengguna_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_User_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_User_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Pengguna Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Pengguna_Btn.setIcon(Btn_User_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Daftar_Pengguna_BtnMouseEntered

    private void Daftar_Pengguna_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Pengguna_BtnMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_User_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_User_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Pengguna Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Pengguna_Btn.setIcon(Btn_User_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
           //Menghapus Panel
        Admin_Main_Panel.removeAll();
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();

        //Menambah panel
        Admin_Main_Panel.add(List_User_Page);
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();
        
        pengguna_data();
        total_pengguna();
        
         Home_Btn_eft2.setVisible(false);
        ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Data effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Data_Btn.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        search_bar();
    }//GEN-LAST:event_Daftar_Pengguna_BtnMouseClicked

    private void Daftar_Kategori_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Kategori_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Ctg_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Ctg_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Kategori A1 Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Kategori_Btn.setIcon(Btn_Ctg_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Daftar_Kategori_BtnMouseReleased

    private void Daftar_Kategori_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Kategori_BtnMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Ctg_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Ctg_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Kategori A1 Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Kategori_Btn.setIcon(Btn_Ctg_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Daftar_Kategori_BtnMousePressed

    private void Daftar_Kategori_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Kategori_BtnMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Ctg_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Ctg_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Kategori A1 Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Kategori_Btn.setIcon(Btn_Ctg_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Daftar_Kategori_BtnMouseExited

    private void Daftar_Kategori_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Kategori_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Ctg_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Ctg_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Kategori A1 Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Kategori_Btn.setIcon(Btn_Ctg_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Daftar_Kategori_BtnMouseEntered

    private void Daftar_Kategori_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Kategori_BtnMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Ctg_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Ctg_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Kategori A1 Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Kategori_Btn.setIcon(Btn_Ctg_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
           //Menghapus Panel
        Admin_Main_Panel.removeAll();
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();

        //Menambah panel
        Admin_Main_Panel.add(List_Category_Page);
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();
        
         Home_Btn_eft2.setVisible(false);
        ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Data effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Data_Btn.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        Kategori_Data();
        kategori_del();
        kategori_up();
        total_ctg();
        search_bar();
    }//GEN-LAST:event_Daftar_Kategori_BtnMouseClicked

    private void Subktg_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Subktg_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Sctg_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Sctg_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Sub Kategori A1 Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Subktg_Btn.setIcon(Btn_Sctg_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Subktg_BtnMouseReleased

    private void Subktg_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Subktg_BtnMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Sctg_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Sctg_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Sub Kategori A1 Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Subktg_Btn.setIcon(Btn_Sctg_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Subktg_BtnMousePressed

    private void Subktg_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Subktg_BtnMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Sctg_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Sctg_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Sub Kategori A1 Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Subktg_Btn.setIcon(Btn_Sctg_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Subktg_BtnMouseExited

    private void Subktg_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Subktg_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Sctg_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Sctg_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Sub Kategori A1 Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Subktg_Btn.setIcon(Btn_Sctg_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Subktg_BtnMouseEntered

    private void Subktg_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Subktg_BtnMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Sctg_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Sctg_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Sub Kategori A1 Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Subktg_Btn.setIcon(Btn_Sctg_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        search_bar();
        
           //Menghapus Panel
        Admin_Main_Panel.removeAll();
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();

        //Menambah panel
        Admin_Main_Panel.add(List_Subcategory_Page);
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();
        
         Home_Btn_eft2.setVisible(false);
        ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Data effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Data_Btn.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        list_subctg();
        list_subctg_del();
        total_subctg();
        Subkategori_Data();
        list_subctg_up();
    }//GEN-LAST:event_Subktg_BtnMouseClicked

    private void Daftar_Toko_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Toko_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Toko_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Toko_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Toko A1 Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Toko_Btn.setIcon(Btn_Toko_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Daftar_Toko_BtnMouseReleased

    private void Daftar_Toko_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Toko_BtnMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Toko_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Toko_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Toko A1 Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Toko_Btn.setIcon(Btn_Toko_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Daftar_Toko_BtnMousePressed

    private void Daftar_Toko_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Toko_BtnMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Toko_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Toko_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Toko A1 Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Toko_Btn.setIcon(Btn_Toko_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Daftar_Toko_BtnMouseExited

    private void Daftar_Toko_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Toko_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Toko_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Toko_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Toko A1 Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Toko_Btn.setIcon(Btn_Toko_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Daftar_Toko_BtnMouseEntered

    private void Daftar_Toko_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Toko_BtnMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Toko_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Toko_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Toko A1 Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Toko_Btn.setIcon(Btn_Toko_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
           //Menghapus Panel
        Admin_Main_Panel.removeAll();
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();

        //Menambah panel
        Admin_Main_Panel.add(List_Store_Page);
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();
        
         Home_Btn_eft2.setVisible(false);
        ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Data effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Data_Btn.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        toko_data();
        total_toko();
        search_bar();
    }//GEN-LAST:event_Daftar_Toko_BtnMouseClicked

    private void Daftar_Barang_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Barang_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Item_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Item_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Barang A1 Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Barang_Btn.setIcon(Btn_Item_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Daftar_Barang_BtnMouseReleased

    private void Daftar_Barang_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Barang_BtnMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Item_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Item_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Barang A1 Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Barang_Btn.setIcon(Btn_Item_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Daftar_Barang_BtnMousePressed

    private void Daftar_Barang_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Barang_BtnMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Item_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Item_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Barang A1 Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Barang_Btn.setIcon(Btn_Item_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Daftar_Barang_BtnMouseExited

    private void Daftar_Barang_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Barang_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Item_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Item_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Barang A1 Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Barang_Btn.setIcon(Btn_Item_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Daftar_Barang_BtnMouseEntered

    private void Daftar_Barang_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Barang_BtnMouseClicked
        // TODO add your handling code here:
        
        search_bar();
        item_data();
        total_item();
        ImageIcon Btn_Item_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Item_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Daftar Barang A1 Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Barang_Btn.setIcon(Btn_Item_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
           //Menghapus Panel
        Admin_Main_Panel.removeAll();
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();

        //Menambah panel
        Admin_Main_Panel.add(List_Item_Page);
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();
        
         Home_Btn_eft2.setVisible(false);
        ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Data effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Data_Btn.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Daftar_Barang_BtnMouseClicked

    private void Daftar_Payment_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Payment_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Payment_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Payment_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Mitra Pembayaran Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Payment_Btn.setIcon(Btn_Payment_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Daftar_Payment_BtnMouseReleased

    private void Daftar_Payment_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Payment_BtnMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Payment_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Payment_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Mitra Pembayaran Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Payment_Btn.setIcon(Btn_Payment_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Daftar_Payment_BtnMousePressed

    private void Daftar_Payment_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Payment_BtnMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Payment_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Payment_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Mitra Pembayaran Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Payment_Btn.setIcon(Btn_Payment_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Daftar_Payment_BtnMouseExited

    private void Daftar_Payment_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Payment_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Payment_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Payment_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Mitra Pembayaran Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Payment_Btn.setIcon(Btn_Payment_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Daftar_Payment_BtnMouseEntered

    private void Daftar_Payment_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Payment_BtnMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Payment_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Payment_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Mitra Pembayaran Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Payment_Btn.setIcon(Btn_Payment_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        search_bar();
        
           //Menghapus Panel
        Admin_Main_Panel.removeAll();
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();

        //Menambah panel
        Admin_Main_Panel.add(List_Payment_Page);
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();
        
         Home_Btn_eft2.setVisible(false);
        ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Data effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Data_Btn.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        total_payment();
        payment_up();
        payment_del();
        Payment_Data();
    }//GEN-LAST:event_Daftar_Payment_BtnMouseClicked

    private void Daftar_Jaskir_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Jaskir_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Jaskir_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Jaskir_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Mitra Kirim Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Jaskir_Btn.setIcon(Btn_Jaskir_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Daftar_Jaskir_BtnMouseReleased

    private void Daftar_Jaskir_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Jaskir_BtnMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Jaskir_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Jaskir_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Mitra Kirim Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Jaskir_Btn.setIcon(Btn_Jaskir_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Daftar_Jaskir_BtnMousePressed

    private void Daftar_Jaskir_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Jaskir_BtnMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Jaskir_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Jaskir_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Mitra Kirim Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Jaskir_Btn.setIcon(Btn_Jaskir_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Daftar_Jaskir_BtnMouseExited

    private void Daftar_Jaskir_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Jaskir_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Jaskir_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Jaskir_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Mitra Kirim Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Jaskir_Btn.setIcon(Btn_Jaskir_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Daftar_Jaskir_BtnMouseEntered

    private void Daftar_Jaskir_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Daftar_Jaskir_BtnMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Jaskir_List;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Jaskir_List = new ImageIcon("src/Picture/Effect/Effect Admin Page/Mitra Kirim Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Daftar_Jaskir_Btn.setIcon(Btn_Jaskir_List);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        search_bar();
        
           //Menghapus Panel
        Admin_Main_Panel.removeAll();
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();

        //Menambah panel
        Admin_Main_Panel.add(List_Expedition_Page);
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();
        
         Home_Btn_eft2.setVisible(false);
        ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Data effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Data_Btn.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        Jaskir_Data();
        jaskir_del();
        jaskir_up();
        total_jaskir();
    }//GEN-LAST:event_Daftar_Jaskir_BtnMouseClicked

    private void Cari_Data_TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Cari_Data_TextFieldFocusLost
        // TODO add your handling code here:
        if(Cari_Data_TextField.getText().length() > 0){ // Ketika jumlah karakter lebih dari nol
            Telusuri_Data_Label.setVisible(false);// Jlabel dibuat menghilang
        }else{
            Telusuri_Data_Label.setVisible(true);// Jlabel dibuat muncul kembali
        }
    }//GEN-LAST:event_Cari_Data_TextFieldFocusLost

    private void Cari_Data_TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Cari_Data_TextFieldFocusGained
        // TODO add your handling code here:
        Telusuri_Data_Label.setVisible(false);//JLabel dibuat menghilang
    }//GEN-LAST:event_Cari_Data_TextFieldFocusGained

    private void Home_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Home_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Home;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Home = new ImageIcon("src/Picture/Effect/Rumah.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Home_Btn.setIcon(Btn_Home);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        if(Home_Page.isShowing()){
            ImageIcon Btn_Home1;/*Deklarasi variabel yang digunakan
            untuk memanggil ImageIcon*/
            Btn_Home1 = new ImageIcon("src/Picture/Effect/Rumah effect 2.png");/*Format pemanggilan file ImageIcon
            yang ingin dipanggil*/
            Home_Btn.setIcon(Btn_Home1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
        }
    }//GEN-LAST:event_Home_BtnMouseReleased

    private void Home_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Home_BtnMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Home;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Home = new ImageIcon("src/Picture/Effect/Rumah.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Home_Btn.setIcon(Btn_Home);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        if(Home_Page.isShowing()){
            ImageIcon Btn_Home1;/*Deklarasi variabel yang digunakan
            untuk memanggil ImageIcon*/
            Btn_Home1 = new ImageIcon("src/Picture/Effect/Rumah effect 2.png");/*Format pemanggilan file ImageIcon
            yang ingin dipanggil*/
            Home_Btn.setIcon(Btn_Home1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
        }
    }//GEN-LAST:event_Home_BtnMouseExited

    private void Home_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Home_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Home;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Home = new ImageIcon("src/Picture/Effect/Rumah effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Home_Btn.setIcon(Btn_Home);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Home_BtnMouseEntered

    private void Home_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Home_BtnMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Home;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Home = new ImageIcon("src/Picture/Effect/Rumah effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Home_Btn.setIcon(Btn_Home);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        search_bar();
        ImageIcon Btn_Data;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Data = new ImageIcon("src/Picture/Effect/Data.png");/*Format pemanggilan file ImageIcon
        // yang ingin dipanggil*/
        Data_Btn.setIcon(Btn_Data);/*Pallete pada java (berupa JLabel)
        //yang digunakan untuk melakukan set pada ImageIcon*/

        ImageIcon Btn_Laporan;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Laporan = new ImageIcon("src/Picture/Effect/Laporan.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Laporan_Btn.setIcon(Btn_Laporan);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
         
        ImageIcon Btn_Tentang;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Tentang = new ImageIcon("src/Picture/Effect/Tentang.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Tentang_Btn.setIcon(Btn_Tentang);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        //Menghapus Panel
        Admin_Main_Panel.removeAll();
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();

        //Menambah panel
        Admin_Main_Panel.add(Home_Page);
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();

    }//GEN-LAST:event_Home_BtnMouseClicked

    private void Data_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Data_BtnMouseReleased

        ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Data.png");/*Format pemanggilan file ImageIcon
        // yang ingin dipanggil*/
        Data_Btn.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel)
        //yang digunakan untuk melakukan set pada ImageIcon*/

        if(Data_Page.isShowing()){
            ImageIcon Btn_Belanja1;/*Deklarasi variabel yang digunakan
            untuk memanggil ImageIcon*/
            Btn_Belanja1 = new ImageIcon("src/Picture/Effect/Data effect 2.png");/*Format pemanggilan file ImageIcon
            yang ingin dipanggil*/
            Data_Btn.setIcon(Btn_Belanja1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/

        }
    }//GEN-LAST:event_Data_BtnMouseReleased

    private void Data_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Data_BtnMouseExited
        //TODO add your handling code here:
        ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Data.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Data_Btn.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        if(Data_Page.isShowing()){
            ImageIcon Btn_Belanja1;/*Deklarasi variabel yang digunakan
            untuk memanggil ImageIcon*/
            Btn_Belanja1 = new ImageIcon("src/Picture/Effect/Data effect 2.png");/*Format pemanggilan file ImageIcon
            yang ingin dipanggil*/
            Data_Btn.setIcon(Btn_Belanja1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/

        }
    }//GEN-LAST:event_Data_BtnMouseExited

    private void Data_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Data_BtnMouseEntered
        // TODO add your handling code here:

        ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Data effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Data_Btn.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Data_BtnMouseEntered

    private void Data_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Data_BtnMouseClicked
        // TODO add your handling code here:
        Home_Btn_eft2.setVisible(false);
        ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Data effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Data_Btn.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        ImageIcon Btn_Home;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Home = new ImageIcon("src/Picture/Effect/Rumah.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Home_Btn.setIcon(Btn_Home);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        ImageIcon Btn_Tentang;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Tentang = new ImageIcon("src/Picture/Effect/Tentang.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Tentang_Btn.setIcon(Btn_Tentang);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        ImageIcon Btn_Laporan;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Laporan = new ImageIcon("src/Picture/Effect/Laporan.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Laporan_Btn.setIcon(Btn_Laporan);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        //Menghapus Panel
        Admin_Main_Panel.removeAll();
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();

        //Menambah panel
        Admin_Main_Panel.add(Data_Page);
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();
        search_bar();
    }//GEN-LAST:event_Data_BtnMouseClicked

    private void Tentang_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tentang_BtnMouseReleased
        ImageIcon Btn_Tentang;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Tentang = new ImageIcon("src/Picture/Effect/Tentang.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Tentang_Btn.setIcon(Btn_Tentang);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        if(About_Us_Page.isShowing()){
            ImageIcon Btn_Tentang1;/*Deklarasi variabel yang digunakan
            untuk memanggil ImageIcon*/
            Btn_Tentang1 = new ImageIcon("src/Picture/Effect/Tentang effect 2.png");/*Format pemanggilan file ImageIcon
            yang ingin dipanggil*/
            Tentang_Btn.setIcon(Btn_Tentang1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
        }
    }//GEN-LAST:event_Tentang_BtnMouseReleased

    private void Tentang_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tentang_BtnMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Tentang;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Tentang = new ImageIcon("src/Picture/Effect/Tentang.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Tentang_Btn.setIcon(Btn_Tentang);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        if(About_Us_Page.isShowing()){
            ImageIcon Btn_Tentang1;/*Deklarasi variabel yang digunakan
            untuk memanggil ImageIcon*/
            Btn_Tentang1 = new ImageIcon("src/Picture/Effect/Tentang effect 2.png");/*Format pemanggilan file ImageIcon
            yang ingin dipanggil*/
            Tentang_Btn.setIcon(Btn_Tentang1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
        }
    }//GEN-LAST:event_Tentang_BtnMouseExited

    private void Tentang_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tentang_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Tentang;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Tentang = new ImageIcon("src/Picture/Effect/Tentang effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Tentang_Btn.setIcon(Btn_Tentang);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Tentang_BtnMouseEntered

    private void Tentang_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tentang_BtnMouseClicked
        // TODO add your handling code here:
       
        
        Home_Btn_eft2.setVisible(false);
        ImageIcon Btn_Tentang;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Tentang = new ImageIcon("src/Picture/Effect/Tentang effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Tentang_Btn.setIcon(Btn_Tentang);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        ImageIcon Btn_Home;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Home = new ImageIcon("src/Picture/Effect/Rumah.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Home_Btn.setIcon(Btn_Home);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Data.png");/*Format pemanggilan file ImageIcon
        // yang ingin dipanggil*/
        Data_Btn.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel)
        //yang digunakan untuk melakukan set pada ImageIcon*/

        ImageIcon Btn_Laporan;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Laporan = new ImageIcon("src/Picture/Effect/Laporan.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Laporan_Btn.setIcon(Btn_Laporan);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        //Menghapus Panel
        Admin_Main_Panel.removeAll();
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();

        //Menambah panel
        Admin_Main_Panel.add(About_Us_Page);
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();
        
        search_bar();

    }//GEN-LAST:event_Tentang_BtnMouseClicked

    private void Search_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Search_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Search;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Search = new ImageIcon("src/Picture/Effect/Search_Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Search_Btn.setIcon(Btn_Search);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Search_BtnMouseReleased

    private void Search_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Search_BtnMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Search;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Search = new ImageIcon("src/Picture/Effect/Search_Btn effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Search_Btn.setIcon(Btn_Search);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Search_BtnMousePressed

    private void Search_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Search_BtnMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Search;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Search = new ImageIcon("src/Picture/Effect/Search_Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Search_Btn.setIcon(Btn_Search);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Search_BtnMouseExited

    private void Search_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Search_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Search;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Search = new ImageIcon("src/Picture/Effect/Search_Btn effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Search_Btn.setIcon(Btn_Search);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Search_BtnMouseEntered

    private void Search_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Search_BtnMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Search;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Search = new ImageIcon("src/Picture/Effect/Search_Btn effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Search_Btn.setIcon(Btn_Search);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        String cari = Cari_Data_TextField.getText();
        
        if(List_User_Page.isShowing()){
          cari_user(cari);
        }else if(List_Item_Page.isShowing()){
            Prc_item(cari);
        }else if(List_Category_Page.isShowing()){
          cari_kategori(cari);
        }else if(List_Subcategory_Page.isShowing()){
           cari_subkategori(cari);
        }else if(List_Payment_Page.isShowing()){
           cari_payment(cari);
        }else if(List_Expedition_Page.isShowing()){
           cari_jaskir(cari);
        }else if(List_Store_Page.isShowing()){
          cari_seller(cari);
        }else if(Laporan_Page.isShowing()){
           Prc_trans(cari);
        }
    }//GEN-LAST:event_Search_BtnMouseClicked

    private void Back_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_BtnMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        //Menghapus Panel
        Admin_Main_Panel.removeAll();
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();

        //Menambah panel
        Admin_Main_Panel.add(Data_Page);
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();
    }//GEN-LAST:event_Back_BtnMouseClicked

    private void Back_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_BtnMouseEntered

    private void Back_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_BtnMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_BtnMouseExited

    private void Back_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_BtnMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_BtnMousePressed

    private void Back_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_BtnMouseReleased

    private void Back_Btn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn1MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        //Menghapus Panel
        Admin_Main_Panel.removeAll();
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();

        //Menambah panel
        Admin_Main_Panel.add(Data_Page);
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();
    }//GEN-LAST:event_Back_Btn1MouseClicked

    private void Back_Btn1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn1MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_Btn1MouseEntered

    private void Back_Btn1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn1MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_Btn1MouseExited

    private void Back_Btn1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn1MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_Btn1MousePressed

    private void Back_Btn1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn1MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_Btn1MouseReleased

    private void Back_Btn2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn2MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        //Menghapus Panel
        Admin_Main_Panel.removeAll();
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();

        //Menambah panel
        Admin_Main_Panel.add(Data_Page);
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();
    }//GEN-LAST:event_Back_Btn2MouseClicked

    private void Back_Btn2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn2MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_Btn2MouseEntered

    private void Back_Btn2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn2MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_Btn2MouseExited

    private void Back_Btn2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn2MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_Btn2MousePressed

    private void Back_Btn2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn2MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_Btn2MouseReleased

    private void Back_Btn3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn3MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        //Menghapus Panel
        Admin_Main_Panel.removeAll();
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();

        //Menambah panel
        Admin_Main_Panel.add(Data_Page);
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();
    }//GEN-LAST:event_Back_Btn3MouseClicked

    private void Back_Btn3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn3MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_Btn3MouseEntered

    private void Back_Btn3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn3MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_Btn3MouseExited

    private void Back_Btn3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn3MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_Btn3MousePressed

    private void Back_Btn3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn3MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_Btn3MouseReleased

    private void Back_Btn4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn4MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

          //Menghapus Panel
        Admin_Main_Panel.removeAll();
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();

        //Menambah panel
        Admin_Main_Panel.add(Data_Page);
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();
    }//GEN-LAST:event_Back_Btn4MouseClicked

    private void Back_Btn4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn4MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_Btn4MouseEntered

    private void Back_Btn4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn4MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_Btn4MouseExited

    private void Back_Btn4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn4MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_Btn4MousePressed

    private void Back_Btn4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn4MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_Btn4MouseReleased

    private void Back_Btn5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn5MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

         //Menghapus Panel
        Admin_Main_Panel.removeAll();
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();

        //Menambah panel
        Admin_Main_Panel.add(Data_Page);
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();
    }//GEN-LAST:event_Back_Btn5MouseClicked

    private void Back_Btn5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn5MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_Btn5MouseEntered

    private void Back_Btn5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn5MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_Btn5MouseExited

    private void Back_Btn5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn5MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_Btn5MousePressed

    private void Back_Btn5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn5MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_Btn5MouseReleased

    private void Back_Btn6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn6MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

          //Menghapus Panel
        Admin_Main_Panel.removeAll();
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();

        //Menambah panel
        Admin_Main_Panel.add(Data_Page);
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();
    }//GEN-LAST:event_Back_Btn6MouseClicked

    private void Back_Btn6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn6MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_Btn6MouseEntered

    private void Back_Btn6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn6MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_Btn6MouseExited

    private void Back_Btn6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn6MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_Btn6MousePressed

    private void Back_Btn6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn6MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_Btn6MouseReleased

    private void LogOut_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogOut_BtnMouseClicked
        // TODO add your handling code here:
        
                ImageIcon Btn_LogOut;/*Deklarasi variabel yang digunakan
                untuk memanggil ImageIcon*/
                Btn_LogOut = new ImageIcon("src/Picture/Effect/LogOut Btn Effect 2.png");/*Format pemanggilan file ImageIcon
                yang ingin dipanggil*/
                LogOut_Btn.setIcon(Btn_LogOut);/*Pallete pada java (berupa JLabel)
                yang digunakan untuk melakukan set pada ImageIcon*/
                
                Admin_Page a = new Admin_Page(); /*Deklarasi 
               variabel pada form yang ingin dituju dan variabel tersebut 
                menujukkan nilai yang sama dengan form yang ingin dituju*/ 
                a.setVisible(true); /* variabel tersebut akan menampilkan form 
                yang dituju*/
                this.dispose();// Untuk menutup form ini
    }//GEN-LAST:event_LogOut_BtnMouseClicked

    private void LogOut_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogOut_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_LogOut;/*Deklarasi variabel yang digunakan
                untuk memanggil ImageIcon*/
                Btn_LogOut = new ImageIcon("src/Picture/Effect/LogOut Btn Effect 1.png");/*Format pemanggilan file ImageIcon
                yang ingin dipanggil*/
                LogOut_Btn.setIcon(Btn_LogOut);/*Pallete pada java (berupa JLabel)
                yang digunakan untuk melakukan set pada ImageIcon*/
                
    }//GEN-LAST:event_LogOut_BtnMouseEntered

    private void LogOut_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogOut_BtnMouseExited
        // TODO add your handling code here:
         ImageIcon Btn_LogOut;/*Deklarasi variabel yang digunakan
                untuk memanggil ImageIcon*/
                Btn_LogOut = new ImageIcon("src/Picture/Effect/LogOut Btn.png");/*Format pemanggilan file ImageIcon
                yang ingin dipanggil*/
                LogOut_Btn.setIcon(Btn_LogOut);/*Pallete pada java (berupa JLabel)
                yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_LogOut_BtnMouseExited

    private void LogOut_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogOut_BtnMousePressed
        // TODO add your handling code here:
         ImageIcon Btn_LogOut;/*Deklarasi variabel yang digunakan
                untuk memanggil ImageIcon*/
                Btn_LogOut = new ImageIcon("src/Picture/Effect/LogOut Btn Effect 3.png");/*Format pemanggilan file ImageIcon
                yang ingin dipanggil*/
                LogOut_Btn.setIcon(Btn_LogOut);/*Pallete pada java (berupa JLabel)
                yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_LogOut_BtnMousePressed

    private void LogOut_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogOut_BtnMouseReleased
        // TODO add your handling code here:
                ImageIcon Btn_LogOut;/*Deklarasi variabel yang digunakan
                untuk memanggil ImageIcon*/
                Btn_LogOut = new ImageIcon("src/Picture/Effect/LogOut Btn.png");/*Format pemanggilan file ImageIcon
                yang ingin dipanggil*/
                LogOut_Btn.setIcon(Btn_LogOut);/*Pallete pada java (berupa JLabel)
                yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_LogOut_BtnMouseReleased

    private void Print_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Print_BtnMouseClicked
        // TODO add your handling code here:
                ImageIcon Btn_Print;/*Deklarasi variabel yang digunakan
                untuk memanggil ImageIcon*/
                Btn_Print = new ImageIcon("src/Picture/Effect/Print Btn Effect 2.png");/*Format pemanggilan file ImageIcon
                yang ingin dipanggil*/
                Print_Btn.setIcon(Btn_Print);/*Pallete pada java (berupa JLabel)
                yang digunakan untuk melakukan set pada ImageIcon*/
                
                Connection cnt;
                
               try {
                    
                      cnt = BK_Connection.getBilik_Pernik_Connect();
                      HashMap hash = new HashMap();
                      String Data_List = ("D:\\UNUD2020\\KULIAH SEMESTER II\\PEMROGRAMAN BERORIENTASI OBJEK (E)\\Bilik-Pernik-by-Herry-X-Rama\\Bilik-Pernik-by-Herry-X-Rama\\Bilik Pernik\\src\\Data_Print\\list_report_pengguna.jrxml");
                      JasperReport rpt = JasperCompileManager.compileReport(Data_List);
                      JasperPrint print = JasperFillManager.fillReport(rpt,hash,cnt);
                      JasperViewer.viewReport(print,false);
                     
                  
               } catch (JRException e) {
                   javax.swing.JOptionPane.showMessageDialog(null, 
                   "Data tidak data tercetak"+"\n"+e.getMessage(),"Cetak Data",javax.swing.JOptionPane.ERROR_MESSAGE);
               }
 
                
                
                
    }//GEN-LAST:event_Print_BtnMouseClicked

    private void Print_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Print_BtnMouseEntered
        // TODO add your handling code here:
         ImageIcon Btn_Print;/*Deklarasi variabel yang digunakan
                untuk memanggil ImageIcon*/
                Btn_Print = new ImageIcon("src/Picture/Effect/Print Btn Effect 1.png");/*Format pemanggilan file ImageIcon
                yang ingin dipanggil*/
                Print_Btn.setIcon(Btn_Print);/*Pallete pada java (berupa JLabel)
                yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Print_BtnMouseEntered

    private void Print_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Print_BtnMouseExited
        // TODO add your handling code here:
         ImageIcon Btn_Print;/*Deklarasi variabel yang digunakan
                untuk memanggil ImageIcon*/
                Btn_Print = new ImageIcon("src/Picture/Effect/Print Btn.png");/*Format pemanggilan file ImageIcon
                yang ingin dipanggil*/
                Print_Btn.setIcon(Btn_Print);/*Pallete pada java (berupa JLabel)
                yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Print_BtnMouseExited

    private void Print_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Print_BtnMousePressed
        // TODO add your handling code here:
                ImageIcon Btn_Print;/*Deklarasi variabel yang digunakan
                untuk memanggil ImageIcon*/
                Btn_Print = new ImageIcon("src/Picture/Effect/Print Btn Effect 3.png");/*Format pemanggilan file ImageIcon
                yang ingin dipanggil*/
                Print_Btn.setIcon(Btn_Print);/*Pallete pada java (berupa JLabel)
                yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Print_BtnMousePressed

    private void Print_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Print_BtnMouseReleased
        // TODO add your handling code here:
                ImageIcon Btn_Print;/*Deklarasi variabel yang digunakan
                untuk memanggil ImageIcon*/
                Btn_Print = new ImageIcon("src/Picture/Effect/Print Btn.png");/*Format pemanggilan file ImageIcon
                yang ingin dipanggil*/
                Print_Btn.setIcon(Btn_Print);/*Pallete pada java (berupa JLabel)
                yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Print_BtnMouseReleased

    private void Laporan_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Laporan_BtnMouseClicked
        // TODO add your handling code here:
        Home_Btn_eft2.setVisible(false);
                ImageIcon Btn_Laporan;/*Deklarasi variabel yang digunakan
                untuk memanggil ImageIcon*/
                Btn_Laporan = new ImageIcon("src/Picture/Effect/Laporan Effect 2.png");/*Format pemanggilan file ImageIcon
                yang ingin dipanggil*/
                Laporan_Btn.setIcon(Btn_Laporan);/*Pallete pada java (berupa JLabel)
                yang digunakan untuk melakukan set pada ImageIcon*/
        ImageIcon Btn_Data;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Data = new ImageIcon("src/Picture/Effect/Data.png");/*Format pemanggilan file ImageIcon
        // yang ingin dipanggil*/
        Data_Btn.setIcon(Btn_Data);/*Pallete pada java (berupa JLabel)
        //yang digunakan untuk melakukan set pada ImageIcon*/
        
                ImageIcon Btn_Home;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Home = new ImageIcon("src/Picture/Effect/Rumah.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Home_Btn.setIcon(Btn_Home);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        ImageIcon Btn_Tentang;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Tentang = new ImageIcon("src/Picture/Effect/Tentang.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Tentang_Btn.setIcon(Btn_Tentang);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        
                
         //Menghapus Panel
        Admin_Main_Panel.removeAll();
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();

        //Menambah panel
        Admin_Main_Panel.add(Laporan_Page);
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();
        
        total_trc();
        trc_data();
                
    }//GEN-LAST:event_Laporan_BtnMouseClicked

    private void Laporan_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Laporan_BtnMouseEntered
        ImageIcon Btn_Laporan;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Laporan = new ImageIcon("src/Picture/Effect/Laporan effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Laporan_Btn.setIcon(Btn_Laporan);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Laporan_BtnMouseEntered

    private void Laporan_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Laporan_BtnMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Laporan;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Laporan = new ImageIcon("src/Picture/Effect/Laporan.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Laporan_Btn.setIcon(Btn_Laporan);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        if(Laporan_Page.isShowing()){
        
        Btn_Laporan = new ImageIcon("src/Picture/Effect/Laporan Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Laporan_Btn.setIcon(Btn_Laporan);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        }
    }//GEN-LAST:event_Laporan_BtnMouseExited

    private void Laporan_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Laporan_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Laporan;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Laporan = new ImageIcon("src/Picture/Effect/Laporan.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Laporan_Btn.setIcon(Btn_Laporan);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        if(Laporan_Page.isShowing()){
        
        Btn_Laporan = new ImageIcon("src/Picture/Effect/Laporan Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Laporan_Btn.setIcon(Btn_Laporan);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        }
    }//GEN-LAST:event_Laporan_BtnMouseReleased

    private void PrintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PrintMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Print;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Print = new ImageIcon("src/Picture/Effect/Print Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Print.setIcon(Btn_Print);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        Connection cnt;

        try {

            cnt = BK_Connection.getBilik_Pernik_Connect();
            HashMap hash = new HashMap();
            String Data_List = ("D:\\UNUD2020\\KULIAH SEMESTER II\\PEMROGRAMAN BERORIENTASI OBJEK (E)\\Bilik-Pernik-by-Herry-X-Rama\\Bilik-Pernik-by-Herry-X-Rama\\Bilik Pernik\\src\\Data_Print\\list_report_seller.jrxml");
            JasperReport rpt = JasperCompileManager.compileReport(Data_List);
            JasperPrint print = JasperFillManager.fillReport(rpt,hash,cnt);
            JasperViewer.viewReport(print,false);

        } catch (JRException e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                "Data tidak data tercetak"+"\n"+e.getMessage(),"Cetak Data",javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_PrintMouseClicked

    private void PrintMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PrintMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Print;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Print = new ImageIcon("src/Picture/Effect/Print Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Print.setIcon(Btn_Print);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_PrintMouseEntered

    private void PrintMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PrintMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Print;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Print = new ImageIcon("src/Picture/Effect/Print Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Print.setIcon(Btn_Print);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_PrintMouseExited

    private void PrintMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PrintMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Print;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Print = new ImageIcon("src/Picture/Effect/Print Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Print.setIcon(Btn_Print);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_PrintMousePressed

    private void PrintMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PrintMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Print;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Print = new ImageIcon("src/Picture/Effect/Print Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Print.setIcon(Btn_Print);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_PrintMouseReleased

    private void Print_aMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Print_aMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Print;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Print = new ImageIcon("src/Picture/Effect/Print Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Print_a.setIcon(Btn_Print);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        Connection cnt;

        try {

            cnt = BK_Connection.getBilik_Pernik_Connect();
            HashMap hash = new HashMap();
            String Data_List = ("D:\\UNUD2020\\KULIAH SEMESTER II\\PEMROGRAMAN BERORIENTASI OBJEK (E)\\Bilik-Pernik-by-Herry-X-Rama\\Bilik-Pernik-by-Herry-X-Rama\\Bilik Pernik\\src\\Data_Print\\list_report_item.jrxml");
            JasperReport rpt = JasperCompileManager.compileReport(Data_List);
            JasperPrint print = JasperFillManager.fillReport(rpt,hash,cnt);
            JasperViewer.viewReport(print,false);

        } catch (JRException e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                "Data tidak data tercetak"+"\n"+e.getMessage(),"Cetak Data",javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_Print_aMouseClicked

    private void Print_aMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Print_aMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Print;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Print = new ImageIcon("src/Picture/Effect/Print Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Print_a.setIcon(Btn_Print);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Print_aMouseEntered

    private void Print_aMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Print_aMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Print;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Print = new ImageIcon("src/Picture/Effect/Print Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Print_a.setIcon(Btn_Print);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Print_aMouseExited

    private void Print_aMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Print_aMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Print;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Print = new ImageIcon("src/Picture/Effect/Print Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Print_a.setIcon(Btn_Print);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Print_aMousePressed

    private void Print_aMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Print_aMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Print;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Print = new ImageIcon("src/Picture/Effect/Print Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Print_a.setIcon(Btn_Print);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Print_aMouseReleased

    private void Upctg_TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Upctg_TextFieldFocusGained
        Upctg_Text.setVisible(false);
    }//GEN-LAST:event_Upctg_TextFieldFocusGained

    private void Upctg_TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Upctg_TextFieldFocusLost
        if(Upctg_TextField.getText().length() == 0){
            Upctg_Text.setVisible(true);
        }else if(Upctg_TextField.getText().length() > 0){
            Upctg_Text.setVisible(false);
        }
    }//GEN-LAST:event_Upctg_TextFieldFocusLost

    private void Upctg_TextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Upctg_TextFieldKeyPressed
        
    }//GEN-LAST:event_Upctg_TextFieldKeyPressed

    private void Upnewctg_TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Upnewctg_TextFieldFocusGained
        Upnewctg_Text.setVisible(false);
    }//GEN-LAST:event_Upnewctg_TextFieldFocusGained

    private void Upnewctg_TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Upnewctg_TextFieldFocusLost
        if(Upnewctg_TextField.getText().length() == 0){
            Upnewctg_Text.setVisible(true);
        }else if(Upnewctg_TextField.getText().length() > 0){
            Upnewctg_Text.setVisible(false);
        }
    }//GEN-LAST:event_Upnewctg_TextFieldFocusLost

    private void Upnewctg_TextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Upnewctg_TextFieldKeyPressed
        
    }//GEN-LAST:event_Upnewctg_TextFieldKeyPressed

    private void Simpan_CtgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_CtgMouseClicked
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Ctg Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Ctg.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/

        String Nama = Upctg_TextField.getText(); /*Variabel untuk mengambil data nama kategori*/
        
        PreparedStatement cmd;
        ResultSet rs;
        /*Try dan Catch digunakan untuk menangani error pada java, eksekusi yang menampilkan
        error dan membuat program tetap berjalan tanpa dihentikan secara langsung*/
        try{ /*Statement yang digunakan untuk menghubungkan database pada mysql ke Netbeans*/
            String sql = "insert into tb_kategori(kategori) values(?)"; /*Syntax pada mysql yang digunakan untuk melakukan peng-update-an data
            password melalui username atau email yang dimasukkan pada form sebelumnya*/
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            cmd.setString(1, Upctg_TextField.getText()); /*Pemasangan dan pemasukkan data pada database server*/
            

            if(Upctg_TextField.getText().length() > 0){ /*Kondisi bahwa karakter dari password yang telah dimasukkan pada JTextField harus sesuai dengan apa
                password yang dimasukkan kembali pada JTextField berikutnya*/
                cmd.executeUpdate(); /*Untuk melakukan ekseskusi pada syntax sql*/
                Upctg_TextField.setText("");
                Upctg_Text.setVisible(true);
               
           
            }else{ /*Jika kondisi diatas tidak sesuai, maka menuju ke else*/
                Upctg_TextField.setText("");
            }
        }catch(SQLException e) /*Menangkap kesalahan atau bug yang terjadi dalam block try*/
        {
            JOptionPane.showMessageDialog(null, e); /*Kondisi apabila terjadi kesalahan pada saat melakukan input data*/
        }
        
        kategori_del();
        kategori_up();
        Kategori_Data();
        total_ctg();
    }//GEN-LAST:event_Simpan_CtgMouseClicked

    private void Simpan_CtgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_CtgMouseEntered
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Ctg Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Ctg.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_CtgMouseEntered

    private void Simpan_CtgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_CtgMouseExited
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Ctg.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Ctg.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_CtgMouseExited

    private void Simpan_CtgMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_CtgMousePressed
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Ctg Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Ctg.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_CtgMousePressed

    private void Simpan_CtgMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_CtgMouseReleased
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Ctg.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Ctg.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_CtgMouseReleased

    private void Simpan_UpctgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_UpctgMouseClicked
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan A1 Data Ctg Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Upctg.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
        
        String Nama = Upnewctg_TextField.getText(); /*Variabel untuk mengambil data nama kategori*/
        String Nama1 = ctg_combo.getSelectedItem().toString();
        
        PreparedStatement cmd;
        ResultSet rs;
        /*Try dan Catch digunakan untuk menangani error pada java, eksekusi yang menampilkan
        error dan membuat program tetap berjalan tanpa dihentikan secara langsung*/
        try{ /*Statement yang digunakan untuk menghubungkan database pada mysql ke Netbeans*/
            String sql = "update tb_kategori set kategori=? where kategori=?"; /*Syntax pada mysql yang digunakan untuk melakukan peng-update-an data
            password melalui username atau email yang dimasukkan pada form sebelumnya*/
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            cmd.setString(1, Upnewctg_TextField.getText()); /*Pemasangan dan pemasukkan data pada database server*/
            cmd.setString(2, ctg_combo.getSelectedItem().toString());

            if(Upnewctg_TextField.getText().length() > 0){ /*Kondisi bahwa karakter dari password yang telah dimasukkan pada JTextField harus sesuai dengan apa
                password yang dimasukkan kembali pada JTextField berikutnya*/
                cmd.executeUpdate(); /*Untuk melakukan ekseskusi pada syntax sql*/
                Upnewctg_TextField.setText("");
                Upnewctg_Text.setVisible(false);
                ctg_combo.setSelectedItem("Pilih kategori yang ingin diperbaharui");
               
           
            }else{ /*Jika kondisi diatas tidak sesuai, maka menuju ke else*/
                Upnewctg_TextField.setText("");
                Upnewctg_Text.setVisible(false);
                ctg_combo.setSelectedItem("Pilih kategori yang ingin diperbaharui");
            }
        }catch(SQLException e) /*Menangkap kesalahan atau bug yang terjadi dalam block try*/
        {
            JOptionPane.showMessageDialog(null, e); /*Kondisi apabila terjadi kesalahan pada saat melakukan input data*/
        }
        kategori_del();
        kategori_up();
        Kategori_Data();
        total_ctg();
    }//GEN-LAST:event_Simpan_UpctgMouseClicked

    private void Simpan_UpctgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_UpctgMouseEntered
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan A1 Data Ctg Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Upctg.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_UpctgMouseEntered

    private void Simpan_UpctgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_UpctgMouseExited
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan A1 Data Ctg.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Upctg.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_UpctgMouseExited

    private void Simpan_UpctgMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_UpctgMousePressed
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan A1 Data Ctg Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Upctg.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_UpctgMousePressed

    private void Simpan_UpctgMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_UpctgMouseReleased
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan A1 Data Ctg.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Upctg.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_UpctgMouseReleased

    private void Delete_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Delete_btnMouseClicked
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Hapus Data Ctg Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Delete_btn.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
        
        String Nama = ctg_combo_1.getSelectedItem().toString(); /*Variabel untuk mengambil data nama kategori*/
        
        PreparedStatement cmd;
        ResultSet rs;
        /*Try dan Catch digunakan untuk menangani error pada java, eksekusi yang menampilkan
        error dan membuat program tetap berjalan tanpa dihentikan secara langsung*/
        try{ /*Statement yang digunakan untuk menghubungkan database pada mysql ke Netbeans*/
            String sql = " delete from tb_kategori where kategori=?"; /*Syntax pada mysql yang digunakan untuk melakukan peng-update-an data
            password melalui username atau email yang dimasukkan pada form sebelumnya*/
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            cmd.setString(1, ctg_combo_1.getSelectedItem().toString()); /*Pemasangan dan pemasukkan data pada database server*/
          

            if(ctg_combo_1.getSelectedItem().toString().length() > 0){ /*Kondisi bahwa karakter dari password yang telah dimasukkan pada JTextField harus sesuai dengan apa
                password yang dimasukkan kembali pada JTextField berikutnya*/
                cmd.executeUpdate(); /*Untuk melakukan ekseskusi pada syntax sql*/
                ctg_combo_1.setSelectedItem("Pilih kategori yang ingin dihapus");
               
                
            }else{ /*Jika kondisi diatas tidak sesuai, maka menuju ke else*/
                ctg_combo_1.setSelectedItem("Pilih kategori yang ingin dihapus");
            }
            
        }catch(SQLException e) /*Menangkap kesalahan atau bug yang terjadi dalam block try*/
        {
            JOptionPane.showMessageDialog(null, e); /*Kondisi apabila terjadi kesalahan pada saat melakukan input data*/
        }
        alter_ctg();
        kategori_del();
        kategori_up();
        Kategori_Data();
        total_ctg();
    }//GEN-LAST:event_Delete_btnMouseClicked

    private void Delete_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Delete_btnMouseEntered
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Hapus Data Ctg Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Delete_btn.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Delete_btnMouseEntered

    private void Delete_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Delete_btnMouseExited
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Hapus Data Ctg.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Delete_btn.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Delete_btnMouseExited

    private void Delete_btnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Delete_btnMousePressed
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Hapus Data Ctg Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Delete_btn.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Delete_btnMousePressed

    private void Delete_btnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Delete_btnMouseReleased
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Hapus Data Ctg.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Delete_btn.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Delete_btnMouseReleased

    private void ctg_combo_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ctg_combo_1MouseClicked
        // TODO add your handling code here:
        Kategori_Data();
    }//GEN-LAST:event_ctg_combo_1MouseClicked

    private void ctg_comboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ctg_comboMouseClicked
        // TODO add your handling code here:
        Kategori_Data();
    }//GEN-LAST:event_ctg_comboMouseClicked

    private void ctg_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ctg_comboActionPerformed
        // TODO add your handling code here:
        Kategori_Data();
    }//GEN-LAST:event_ctg_comboActionPerformed

    private void jLabel31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseClicked
        // TODO add your handling code here:
        Kategori_Data();
    }//GEN-LAST:event_jLabel31MouseClicked

    private void subctg_combo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subctg_combo1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_subctg_combo1MouseClicked

    private void subctg_combo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subctg_combo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subctg_combo1ActionPerformed

    private void Upsubctg_TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Upsubctg_TextFieldFocusGained
        Upsubctg_Text.setVisible(false);
    }//GEN-LAST:event_Upsubctg_TextFieldFocusGained

    private void Upsubctg_TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Upsubctg_TextFieldFocusLost
        if(Upsubctg_TextField.getText().length() == 0){
            Upsubctg_Text.setVisible(true);
        }else if(Upsubctg_TextField.getText().length() > 0){
            Upsubctg_Text.setVisible(false);
        }
    }//GEN-LAST:event_Upsubctg_TextFieldFocusLost

    private void Upsubctg_TextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Upsubctg_TextFieldKeyPressed

    }//GEN-LAST:event_Upsubctg_TextFieldKeyPressed

    private void Simpan_subctgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_subctgMouseClicked
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Ctg Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_subctg.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/

        String Nama = Upsubctg_TextField.getText(); /*Variabel untuk mengambil data nama kategori*/
        int Nama1 = subctg_combo1.getSelectedIndex();
        PreparedStatement cmd;
        ResultSet rs;
        /*Try dan Catch digunakan untuk menangani error pada java, eksekusi yang menampilkan
        error dan membuat program tetap berjalan tanpa dihentikan secara langsung*/
        try{ /*Statement yang digunakan untuk menghubungkan database pada mysql ke Netbeans*/
            String sql = "insert into tb_subkategori (id_kategori,subkategori) values(?,?)"; /*Syntax pada mysql yang digunakan untuk melakukan peng-update-an data
            password melalui username atau email yang dimasukkan pada form sebelumnya*/
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            cmd.setInt(1, subctg_combo1.getSelectedIndex()); /*Pemasangan dan pemasukkan data pada database server*/
            cmd.setString(2, Upsubctg_TextField.getText());
            
            if(Upsubctg_TextField.getText().length() > 0){ /*Kondisi bahwa karakter dari password yang telah dimasukkan pada JTextField harus sesuai dengan apa
                password yang dimasukkan kembali pada JTextField berikutnya*/
                cmd.executeUpdate(); /*Untuk melakukan ekseskusi pada syntax sql*/
                Upsubctg_TextField.setText("");
                Upsubctg_Text.setVisible(true);
                subctg_combo1.setSelectedItem("Pilihlah kategori produk");

            }else{ /*Jika kondisi diatas tidak sesuai, maka menuju ke else*/
                Upsubctg_TextField.setText("");
                Upsubctg_Text.setVisible(true);
                subctg_combo1.setSelectedItem("Pilihlah kategori produk");
            }
        }catch(SQLException e) /*Menangkap kesalahan atau bug yang terjadi dalam block try*/
        {
            JOptionPane.showMessageDialog(null, e); /*Kondisi apabila terjadi kesalahan pada saat melakukan input data*/
        }

        list_subctg();
        list_subctg_del();
        total_subctg();
        Subkategori_Data();
        list_subctg_up();
        
    }//GEN-LAST:event_Simpan_subctgMouseClicked

    private void Simpan_subctgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_subctgMouseEntered
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Ctg Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_subctg.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_subctgMouseEntered

    private void Simpan_subctgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_subctgMouseExited
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Ctg.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_subctg.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_subctgMouseExited

    private void Simpan_subctgMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_subctgMousePressed
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Ctg Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_subctg.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_subctgMousePressed

    private void Simpan_subctgMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_subctgMouseReleased
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Ctg.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_subctg.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_subctgMouseReleased

    private void subctg_combo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subctg_combo2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_subctg_combo2MouseClicked

    private void subctg_combo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subctg_combo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subctg_combo2ActionPerformed

    private void Delete_subbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Delete_subbtnMouseClicked
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Hapus Data Ctg Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Delete_subbtn.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/

        String Nama = subctg_combo2.getSelectedItem().toString(); /*Variabel untuk mengambil data nama kategori*/

        PreparedStatement cmd;
        ResultSet rs;
        /*Try dan Catch digunakan untuk menangani error pada java, eksekusi yang menampilkan
        error dan membuat program tetap berjalan tanpa dihentikan secara langsung*/
        try{ /*Statement yang digunakan untuk menghubungkan database pada mysql ke Netbeans*/
            String sql = " delete from tb_subkategori where subkategori=?"; /*Syntax pada mysql yang digunakan untuk melakukan peng-update-an data
            password melalui username atau email yang dimasukkan pada form sebelumnya*/
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            cmd.setString(1, subctg_combo2.getSelectedItem().toString()); /*Pemasangan dan pemasukkan data pada database server*/

            if(subctg_combo2.getSelectedItem().toString().length() > 0){ /*Kondisi bahwa karakter dari password yang telah dimasukkan pada JTextField harus sesuai dengan apa
                password yang dimasukkan kembali pada JTextField berikutnya*/
                cmd.executeUpdate(); /*Untuk melakukan ekseskusi pada syntax sql*/
                subctg_combo2.setSelectedItem("Pilih subkategori yang ingin dihapus");

            }else{ /*Jika kondisi diatas tidak sesuai, maka menuju ke else*/
                subctg_combo2.setSelectedItem("Pilih subkategori yang ingin dihapus");
            }
        }catch(SQLException e) /*Menangkap kesalahan atau bug yang terjadi dalam block try*/
        {
            JOptionPane.showMessageDialog(null, e); /*Kondisi apabila terjadi kesalahan pada saat melakukan input data*/
        }
        alter_s_ctg();
        list_subctg();
        list_subctg_del();
        total_subctg();
        Subkategori_Data();
        list_subctg_up();
    }//GEN-LAST:event_Delete_subbtnMouseClicked

    private void Delete_subbtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Delete_subbtnMouseEntered
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Hapus Data Ctg Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Delete_subbtn.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Delete_subbtnMouseEntered

    private void Delete_subbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Delete_subbtnMouseExited
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Hapus Data Ctg.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Delete_subbtn.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Delete_subbtnMouseExited

    private void Delete_subbtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Delete_subbtnMousePressed
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Hapus Data Ctg Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Delete_subbtn.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Delete_subbtnMousePressed

    private void Delete_subbtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Delete_subbtnMouseReleased
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Hapus Data Ctg.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Delete_subbtn.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Delete_subbtnMouseReleased

    private void subctg_comboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subctg_comboMouseClicked
        // TODO add your handling code here:
        Kategori_Data();
    }//GEN-LAST:event_subctg_comboMouseClicked

    private void subctg_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subctg_comboActionPerformed
        // TODO add your handling code here:
        Kategori_Data();
    }//GEN-LAST:event_subctg_comboActionPerformed

    private void Upnewsubctg1_TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Upnewsubctg1_TextFieldFocusGained
        Upnewsubctg1_Text.setVisible(false);
    }//GEN-LAST:event_Upnewsubctg1_TextFieldFocusGained

    private void Upnewsubctg1_TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Upnewsubctg1_TextFieldFocusLost
        if(Upnewsubctg1_TextField.getText().length() == 0){
            Upnewsubctg1_Text.setVisible(true);
        }else if(Upnewsubctg1_TextField.getText().length() > 0){
           Upnewsubctg1_Text.setVisible(false);
        }
    }//GEN-LAST:event_Upnewsubctg1_TextFieldFocusLost

    private void Upnewsubctg1_TextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Upnewsubctg1_TextFieldKeyPressed

    }//GEN-LAST:event_Upnewsubctg1_TextFieldKeyPressed

    private void Simpan_UpsubctgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_UpsubctgMouseClicked
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan A1 Data Ctg Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Upsubctg.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/

        String Nama = Upnewsubctg1_TextField.getText(); /*Variabel untuk mengambil data nama kategori*/
        String Nama1 = subctg_combo.getSelectedItem().toString();

        PreparedStatement cmd;
        ResultSet rs;
        /*Try dan Catch digunakan untuk menangani error pada java, eksekusi yang menampilkan
        error dan membuat program tetap berjalan tanpa dihentikan secara langsung*/
        try{ /*Statement yang digunakan untuk menghubungkan database pada mysql ke Netbeans*/
            String sql = "update tb_subkategori set subkategori=? where subkategori=?"; /*Syntax pada mysql yang digunakan untuk melakukan peng-update-an data
            password melalui username atau email yang dimasukkan pada form sebelumnya*/
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            cmd.setString(1, Upnewsubctg1_TextField.getText()); /*Pemasangan dan pemasukkan data pada database server*/
            cmd.setString(2, subctg_combo.getSelectedItem().toString());

            if(Upnewsubctg1_TextField.getText().length() > 0){ /*Kondisi bahwa karakter dari password yang telah dimasukkan pada JTextField harus sesuai dengan apa
                password yang dimasukkan kembali pada JTextField berikutnya*/
                cmd.executeUpdate(); /*Untuk melakukan ekseskusi pada syntax sql*/
                Upnewsubctg1_TextField.setText("");
                Upnewctg_Text.setVisible(false);
                subctg_combo.setSelectedItem("Pilih kategori yang ingin diperbaharui");

            }else{ /*Jika kondisi diatas tidak sesuai, maka menuju ke else*/
                Upnewsubctg1_TextField.setText("");
                Upnewctg_Text.setVisible(false);
                subctg_combo.setSelectedItem("Pilih kategori yang ingin diperbaharui");
            }
        }catch(SQLException e) /*Menangkap kesalahan atau bug yang terjadi dalam block try*/
        {
            JOptionPane.showMessageDialog(null, e); /*Kondisi apabila terjadi kesalahan pada saat melakukan input data*/
        }
        list_subctg();
        list_subctg_del();
        total_subctg();
        Subkategori_Data();
        list_subctg_up();
    }//GEN-LAST:event_Simpan_UpsubctgMouseClicked

    private void Simpan_UpsubctgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_UpsubctgMouseEntered
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan A1 Data Ctg Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Upsubctg.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_UpsubctgMouseEntered

    private void Simpan_UpsubctgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_UpsubctgMouseExited
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan A1 Data Ctg.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Upsubctg.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_UpsubctgMouseExited

    private void Simpan_UpsubctgMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_UpsubctgMousePressed
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan A1 Data Ctg Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Upsubctg.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_UpsubctgMousePressed

    private void Simpan_UpsubctgMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_UpsubctgMouseReleased
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan A1 Data Ctg.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Upsubctg.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_UpsubctgMouseReleased

    private void Uppay_TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Uppay_TextFieldFocusGained
        Uppayment_Text.setVisible(false);
    }//GEN-LAST:event_Uppay_TextFieldFocusGained

    private void Uppay_TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Uppay_TextFieldFocusLost
        if(Uppay_TextField.getText().length() == 0){
            Uppayment_Text.setVisible(true);
        }else if(Uppay_TextField.getText().length() > 0){
            Uppayment_Text.setVisible(false);
        }
    }//GEN-LAST:event_Uppay_TextFieldFocusLost

    private void Uppay_TextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Uppay_TextFieldKeyPressed

    }//GEN-LAST:event_Uppay_TextFieldKeyPressed

    private void payment_combo_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_payment_combo_1MouseClicked
        // TODO add your handling code here:
        Kategori_Data();
    }//GEN-LAST:event_payment_combo_1MouseClicked

    private void Simpan_PaymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_PaymentMouseClicked
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Ctg Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Payment.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/

        String Nama = Uppay_TextField.getText(); /*Variabel untuk mengambil data nama kategori*/

        PreparedStatement cmd;
        ResultSet rs;
        /*Try dan Catch digunakan untuk menangani error pada java, eksekusi yang menampilkan
        error dan membuat program tetap berjalan tanpa dihentikan secara langsung*/
        try{ /*Statement yang digunakan untuk menghubungkan database pada mysql ke Netbeans*/
            String sql = "insert into tb_mitra_bayar(mitra_bayar) values(?)"; /*Syntax pada mysql yang digunakan untuk melakukan peng-update-an data
            password melalui username atau email yang dimasukkan pada form sebelumnya*/
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            cmd.setString(1, Uppay_TextField.getText()); /*Pemasangan dan pemasukkan data pada database server*/

            if(Uppay_TextField.getText().length() > 0){ /*Kondisi bahwa karakter dari password yang telah dimasukkan pada JTextField harus sesuai dengan apa
                password yang dimasukkan kembali pada JTextField berikutnya*/
                cmd.executeUpdate(); /*Untuk melakukan ekseskusi pada syntax sql*/
                Uppay_TextField.setText("");
                Uppayment_Text.setVisible(true);

            }else{ /*Jika kondisi diatas tidak sesuai, maka menuju ke else*/
                Uppay_TextField.setText("");
            }
        }catch(SQLException e) /*Menangkap kesalahan atau bug yang terjadi dalam block try*/
        {
            JOptionPane.showMessageDialog(null, e); /*Kondisi apabila terjadi kesalahan pada saat melakukan input data*/
        }
        total_payment();
        payment_up();
        payment_del();
        Payment_Data();
    }//GEN-LAST:event_Simpan_PaymentMouseClicked

    private void Simpan_PaymentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_PaymentMouseEntered
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Ctg Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Payment.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_PaymentMouseEntered

    private void Simpan_PaymentMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_PaymentMouseExited
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Ctg.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Payment.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_PaymentMouseExited

    private void Simpan_PaymentMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_PaymentMousePressed
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Ctg Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Payment.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_PaymentMousePressed

    private void Simpan_PaymentMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_PaymentMouseReleased
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Ctg.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Payment.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_PaymentMouseReleased

    private void Deletepay_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Deletepay_btnMouseClicked
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Hapus Data Ctg Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Deletepay_btn.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/

        String Nama = payment_combo_1.getSelectedItem().toString(); /*Variabel untuk mengambil data nama kategori*/

        PreparedStatement cmd;
        ResultSet rs;
        /*Try dan Catch digunakan untuk menangani error pada java, eksekusi yang menampilkan
        error dan membuat program tetap berjalan tanpa dihentikan secara langsung*/
        try{ /*Statement yang digunakan untuk menghubungkan database pada mysql ke Netbeans*/
            String sql = " delete from tb_mitra_bayar where mitra_bayar=?"; /*Syntax pada mysql yang digunakan untuk melakukan peng-update-an data
            password melalui username atau email yang dimasukkan pada form sebelumnya*/
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            cmd.setString(1, payment_combo_1.getSelectedItem().toString()); /*Pemasangan dan pemasukkan data pada database server*/

            if(payment_combo_1.getSelectedItem().toString().length() > 0){ /*Kondisi bahwa karakter dari password yang telah dimasukkan pada JTextField harus sesuai dengan apa
                password yang dimasukkan kembali pada JTextField berikutnya*/
                cmd.executeUpdate(); /*Untuk melakukan ekseskusi pada syntax sql*/
                payment_combo_1.setSelectedItem("Pilih mitra pembayaran yang ingin dihapus");

            }else{ /*Jika kondisi diatas tidak sesuai, maka menuju ke else*/
                payment_combo_1.setSelectedItem("Pilih mitra pembayaran yang ingin dihapus");
            }
        }catch(SQLException e) /*Menangkap kesalahan atau bug yang terjadi dalam block try*/
        {
            JOptionPane.showMessageDialog(null, e); /*Kondisi apabila terjadi kesalahan pada saat melakukan input data*/
        }
        alter_pay();
        total_payment();
        payment_up();
        payment_del();
        Payment_Data();
    }//GEN-LAST:event_Deletepay_btnMouseClicked

    private void Deletepay_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Deletepay_btnMouseEntered
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Hapus Data Ctg Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Deletepay_btn.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Deletepay_btnMouseEntered

    private void Deletepay_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Deletepay_btnMouseExited
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Hapus Data Ctg.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Deletepay_btn.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Deletepay_btnMouseExited

    private void Deletepay_btnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Deletepay_btnMousePressed
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Hapus Data Ctg Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Deletepay_btn.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Deletepay_btnMousePressed

    private void Deletepay_btnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Deletepay_btnMouseReleased
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Hapus Data Ctg.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Deletepay_btn.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Deletepay_btnMouseReleased

    private void payment_comboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_payment_comboMouseClicked
        // TODO add your handling code here:
        Kategori_Data();
    }//GEN-LAST:event_payment_comboMouseClicked

    private void payment_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payment_comboActionPerformed
        // TODO add your handling code here:
        Kategori_Data();
    }//GEN-LAST:event_payment_comboActionPerformed

    private void Upnewpay_TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Upnewpay_TextFieldFocusGained
        Upnewctg_Text1.setVisible(false);
    }//GEN-LAST:event_Upnewpay_TextFieldFocusGained

    private void Upnewpay_TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Upnewpay_TextFieldFocusLost
        if(Upnewpay_TextField.getText().length() == 0){
            Upnewctg_Text1.setVisible(true);
        }else if(Upnewpay_TextField.getText().length() > 0){
            Upnewctg_Text1.setVisible(false);
        }
    }//GEN-LAST:event_Upnewpay_TextFieldFocusLost

    private void Upnewpay_TextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Upnewpay_TextFieldKeyPressed

    }//GEN-LAST:event_Upnewpay_TextFieldKeyPressed

    private void Simpan_UppayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_UppayMouseClicked
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan A1 Data Ctg Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Uppay.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/

        String Nama = Upnewpay_TextField.getText(); /*Variabel untuk mengambil data nama kategori*/
        String Nama1 = payment_combo.getSelectedItem().toString();

        PreparedStatement cmd;
        ResultSet rs;
        /*Try dan Catch digunakan untuk menangani error pada java, eksekusi yang menampilkan
        error dan membuat program tetap berjalan tanpa dihentikan secara langsung*/
        try{ /*Statement yang digunakan untuk menghubungkan database pada mysql ke Netbeans*/
            String sql = "update tb_mitra_bayar set mitra_bayar=? where mitra_bayar=?"; /*Syntax pada mysql yang digunakan untuk melakukan peng-update-an data
            password melalui username atau email yang dimasukkan pada form sebelumnya*/
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            cmd.setString(1, Upnewpay_TextField.getText()); /*Pemasangan dan pemasukkan data pada database server*/
            cmd.setString(2, payment_combo.getSelectedItem().toString());

            if(Upnewpay_TextField.getText().length() > 0){ /*Kondisi bahwa karakter dari password yang telah dimasukkan pada JTextField harus sesuai dengan apa
                password yang dimasukkan kembali pada JTextField berikutnya*/
                cmd.executeUpdate(); /*Untuk melakukan ekseskusi pada syntax sql*/
                Upnewpay_TextField.setText("");
                Upnewctg_Text.setVisible(false);
                payment_combo.setSelectedItem("Pilih mitra pembayaran yang ingin diperbarui");

            }else{ /*Jika kondisi diatas tidak sesuai, maka menuju ke else*/
                Upnewpay_TextField.setText("");
                Upnewctg_Text.setVisible(false);
                payment_combo.setSelectedItem("Pilih mitra pembayaran yang ingin diperbarui");
            }
        }catch(SQLException e) /*Menangkap kesalahan atau bug yang terjadi dalam block try*/
        {
            JOptionPane.showMessageDialog(null, e); /*Kondisi apabila terjadi kesalahan pada saat melakukan input data*/
        }
        
        total_payment();
        payment_up();
        payment_del();
        Payment_Data();

    }//GEN-LAST:event_Simpan_UppayMouseClicked

    private void Simpan_UppayMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_UppayMouseEntered
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan A1 Data Ctg Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Uppay.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_UppayMouseEntered

    private void Simpan_UppayMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_UppayMouseExited
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan A1 Data Ctg.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Uppay.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_UppayMouseExited

    private void Simpan_UppayMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_UppayMousePressed
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan A1 Data Ctg Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Uppay.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_UppayMousePressed

    private void Simpan_UppayMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_UppayMouseReleased
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan A1 Data Ctg.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Uppay.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_UppayMouseReleased

    private void Upjaskir_TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Upjaskir_TextFieldFocusGained
        Upjaskir_Text.setVisible(false);
    }//GEN-LAST:event_Upjaskir_TextFieldFocusGained

    private void Upjaskir_TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Upjaskir_TextFieldFocusLost
        if(Upjaskir_TextField.getText().length() == 0){
            Upjaskir_Text.setVisible(true);
        }else if(Upjaskir_TextField.getText().length() > 0){
            Upjaskir_Text.setVisible(false);
        }
    }//GEN-LAST:event_Upjaskir_TextFieldFocusLost

    private void Upjaskir_TextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Upjaskir_TextFieldKeyPressed

    }//GEN-LAST:event_Upjaskir_TextFieldKeyPressed

    private void Simpan_JaskirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_JaskirMouseClicked
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Ctg Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Jaskir.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/

        String Nama = Upjaskir_TextField.getText(); /*Variabel untuk mengambil data nama kategori*/

        PreparedStatement cmd;
        ResultSet rs;
        /*Try dan Catch digunakan untuk menangani error pada java, eksekusi yang menampilkan
        error dan membuat program tetap berjalan tanpa dihentikan secara langsung*/
        try{ /*Statement yang digunakan untuk menghubungkan database pada mysql ke Netbeans*/
            String sql = "insert into tb_mitra_kirim(mitra_kirim) values(?)"; /*Syntax pada mysql yang digunakan untuk melakukan peng-update-an data
            password melalui username atau email yang dimasukkan pada form sebelumnya*/
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            cmd.setString(1, Upjaskir_TextField.getText()); /*Pemasangan dan pemasukkan data pada database server*/

            if(Upjaskir_TextField.getText().length() > 0){ /*Kondisi bahwa karakter dari password yang telah dimasukkan pada JTextField harus sesuai dengan apa
                password yang dimasukkan kembali pada JTextField berikutnya*/
                cmd.executeUpdate(); /*Untuk melakukan ekseskusi pada syntax sql*/
                Upjaskir_TextField.setText("");
                Upjaskir_Text.setVisible(true);

            }else{ /*Jika kondisi diatas tidak sesuai, maka menuju ke else*/
                Upjaskir_TextField.setText("");
            }
        }catch(SQLException e) /*Menangkap kesalahan atau bug yang terjadi dalam block try*/
        {
            JOptionPane.showMessageDialog(null, e); /*Kondisi apabila terjadi kesalahan pada saat melakukan input data*/
        }

        Jaskir_Data();
        jaskir_del();
        jaskir_up();
        total_jaskir();
    }//GEN-LAST:event_Simpan_JaskirMouseClicked

    private void Simpan_JaskirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_JaskirMouseEntered
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Ctg Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Jaskir.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_JaskirMouseEntered

    private void Simpan_JaskirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_JaskirMouseExited
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Ctg.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Jaskir.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_JaskirMouseExited

    private void Simpan_JaskirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_JaskirMousePressed
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Ctg Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Jaskir.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_JaskirMousePressed

    private void Simpan_JaskirMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_JaskirMouseReleased
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Ctg.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Jaskir.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_JaskirMouseReleased

    private void jaskir_combo_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jaskir_combo_1MouseClicked
        // TODO add your handling code here:
        Kategori_Data();
    }//GEN-LAST:event_jaskir_combo_1MouseClicked

    private void Delete_Jaskir_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Delete_Jaskir_btnMouseClicked
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Hapus Data Ctg Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Delete_Jaskir_btn.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/

        String Nama = jaskir_combo_1.getSelectedItem().toString(); /*Variabel untuk mengambil data nama kategori*/

        PreparedStatement cmd;
        ResultSet rs;
        /*Try dan Catch digunakan untuk menangani error pada java, eksekusi yang menampilkan
        error dan membuat program tetap berjalan tanpa dihentikan secara langsung*/
        try{ /*Statement yang digunakan untuk menghubungkan database pada mysql ke Netbeans*/
            String sql = " delete from tb_mitra_kirim where mitra_kirim=?"; /*Syntax pada mysql yang digunakan untuk melakukan peng-update-an data
            password melalui username atau email yang dimasukkan pada form sebelumnya*/
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            cmd.setString(1, jaskir_combo_1.getSelectedItem().toString()); /*Pemasangan dan pemasukkan data pada database server*/

            if(jaskir_combo_1.getSelectedItem().toString().length() > 0){ /*Kondisi bahwa karakter dari password yang telah dimasukkan pada JTextField harus sesuai dengan apa
                password yang dimasukkan kembali pada JTextField berikutnya*/
                cmd.executeUpdate(); /*Untuk melakukan ekseskusi pada syntax sql*/
                jaskir_combo_1.setSelectedItem("Pilih mitra kirim yang ingin dihapus");

            }else{ /*Jika kondisi diatas tidak sesuai, maka menuju ke else*/
                jaskir_combo_1.setSelectedItem("Pilih mitra kirim yang ingin dihapus");
            }
        }catch(SQLException e) /*Menangkap kesalahan atau bug yang terjadi dalam block try*/
        {
            JOptionPane.showMessageDialog(null, e); /*Kondisi apabila terjadi kesalahan pada saat melakukan input data*/
        }
        alter_jaskir();
        Jaskir_Data();
        jaskir_del();
        jaskir_up();
        total_jaskir();
    }//GEN-LAST:event_Delete_Jaskir_btnMouseClicked

    private void Delete_Jaskir_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Delete_Jaskir_btnMouseEntered
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Hapus Data Ctg Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Delete_Jaskir_btn.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Delete_Jaskir_btnMouseEntered

    private void Delete_Jaskir_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Delete_Jaskir_btnMouseExited
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Hapus Data Ctg.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Delete_Jaskir_btn.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Delete_Jaskir_btnMouseExited

    private void Delete_Jaskir_btnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Delete_Jaskir_btnMousePressed
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Hapus Data Ctg Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Delete_Jaskir_btn.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Delete_Jaskir_btnMousePressed

    private void Delete_Jaskir_btnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Delete_Jaskir_btnMouseReleased
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Hapus Data Ctg.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Delete_Jaskir_btn.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Delete_Jaskir_btnMouseReleased

    private void jaskir_comboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jaskir_comboMouseClicked
        // TODO add your handling code here:
        Kategori_Data();
    }//GEN-LAST:event_jaskir_comboMouseClicked

    private void jaskir_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jaskir_comboActionPerformed
        // TODO add your handling code here:
        Kategori_Data();
    }//GEN-LAST:event_jaskir_comboActionPerformed

    private void Upnewjaskir_TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Upnewjaskir_TextFieldFocusGained
        Upnewjaskir_Text.setVisible(false);
    }//GEN-LAST:event_Upnewjaskir_TextFieldFocusGained

    private void Upnewjaskir_TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Upnewjaskir_TextFieldFocusLost
        if(Upnewjaskir_TextField.getText().length() == 0){
            Upnewjaskir_Text.setVisible(true);
        }else if(Upnewjaskir_TextField.getText().length() > 0){
            Upnewjaskir_Text.setVisible(false);
        }
    }//GEN-LAST:event_Upnewjaskir_TextFieldFocusLost

    private void Upnewjaskir_TextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Upnewjaskir_TextFieldKeyPressed

    }//GEN-LAST:event_Upnewjaskir_TextFieldKeyPressed

    private void Simpan_UpJaskirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_UpJaskirMouseClicked
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan A1 Data Ctg Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_UpJaskir.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/

        String Nama = Upnewjaskir_TextField.getText(); /*Variabel untuk mengambil data nama kategori*/
        String Nama1 = jaskir_combo.getSelectedItem().toString();

        PreparedStatement cmd;
        ResultSet rs;
        /*Try dan Catch digunakan untuk menangani error pada java, eksekusi yang menampilkan
        error dan membuat program tetap berjalan tanpa dihentikan secara langsung*/
        try{ /*Statement yang digunakan untuk menghubungkan database pada mysql ke Netbeans*/
            String sql = "update tb_mitra_kirim set mitra_kirim =? where mitra_kirim=?"; /*Syntax pada mysql yang digunakan untuk melakukan peng-update-an data
            password melalui username atau email yang dimasukkan pada form sebelumnya*/
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            cmd.setString(1, Upnewjaskir_TextField.getText()); /*Pemasangan dan pemasukkan data pada database server*/
            cmd.setString(2, jaskir_combo.getSelectedItem().toString());
           

            if(Upnewjaskir_TextField.getText().length() > 0){ /*Kondisi bahwa karakter dari password yang telah dimasukkan pada JTextField harus sesuai dengan apa
                password yang dimasukkan kembali pada JTextField berikutnya*/
                cmd.executeUpdate(); /*Untuk melakukan ekseskusi pada syntax sql*/
                Upnewjaskir_TextField.setText("");
                Upnewjaskir_Text.setVisible(false);
                jaskir_combo.setSelectedItem("Pilih mitra pengiriman yang ingin diperbaharui");

            }else{ /*Jika kondisi diatas tidak sesuai, maka menuju ke else*/
                Upnewjaskir_TextField.setText("");
                Upnewjaskir_Text.setVisible(false);
                jaskir_combo.setSelectedItem("Pilih mitra pengiriman yang ingin diperbaharui");
            }
        }catch(SQLException e) /*Menangkap kesalahan atau bug yang terjadi dalam block try*/
        {
            JOptionPane.showMessageDialog(null, e); /*Kondisi apabila terjadi kesalahan pada saat melakukan input data*/
        }
        Jaskir_Data();
        jaskir_del();
        jaskir_up();
        total_jaskir();
    }//GEN-LAST:event_Simpan_UpJaskirMouseClicked

    private void Simpan_UpJaskirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_UpJaskirMouseEntered
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan A1 Data Ctg Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_UpJaskir.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_UpJaskirMouseEntered

    private void Simpan_UpJaskirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_UpJaskirMouseExited
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan A1 Data Ctg.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_UpJaskir.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_UpJaskirMouseExited

    private void Simpan_UpJaskirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_UpJaskirMousePressed
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan A1 Data Ctg Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_UpJaskir.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_UpJaskirMousePressed

    private void Simpan_UpJaskirMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_UpJaskirMouseReleased
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan A1 Data Ctg.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_UpJaskir.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_UpJaskirMouseReleased

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        pengguna_data();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        toko_data();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
        Subkategori_Data();
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
        Payment_Data();
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
        Jaskir_Data();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void Logo_PictureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Logo_PictureMouseClicked
        ImageIcon Btn_Home;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Home = new ImageIcon("src/Picture/Effect/Rumah effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Home_Btn.setIcon(Btn_Home);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        ImageIcon Btn_Data;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Data = new ImageIcon("src/Picture/Effect/Data.png");/*Format pemanggilan file ImageIcon
        // yang ingin dipanggil*/
        Data_Btn.setIcon(Btn_Data);/*Pallete pada java (berupa JLabel)
        //yang digunakan untuk melakukan set pada ImageIcon*/

        ImageIcon Btn_Laporan;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        ImageIcon Btn_Tentang;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Tentang = new ImageIcon("src/Picture/Effect/Tentang.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Tentang_Btn.setIcon(Btn_Tentang);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        //Menghapus Panel
        Admin_Main_Panel.removeAll();
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();

        //Menambah panel
        Admin_Main_Panel.add(Home_Page);
        Admin_Main_Panel.repaint();
        Admin_Main_Panel.revalidate();
    }//GEN-LAST:event_Logo_PictureMouseClicked

    private void Logo_PictureMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Logo_PictureMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_Logo_PictureMouseEntered

    private void Logo_PictureMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Logo_PictureMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_Logo_PictureMouseExited

    private void Logo_PictureMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Logo_PictureMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Logo_PictureMousePressed

    private void Logo_PictureMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Logo_PictureMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_Logo_PictureMouseReleased

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        item_data();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void Print_rpt_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Print_rpt_BtnMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Print;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Print = new ImageIcon("src/Picture/Effect/Print Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Print_rpt_Btn.setIcon(Btn_Print);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        Connection cnt;

        try {

            cnt = BK_Connection.getBilik_Pernik_Connect();
            HashMap hash = new HashMap();
            String Data_List = ("D:\\UNUD2020\\KULIAH SEMESTER II\\PEMROGRAMAN BERORIENTASI OBJEK (E)\\Bilik-Pernik-by-Herry-X-Rama\\Bilik-Pernik-by-Herry-X-Rama\\Bilik Pernik\\src\\Data_Print\\List_rpt_BP.jrxml");
            JasperReport rpt = JasperCompileManager.compileReport(Data_List);
            JasperPrint print = JasperFillManager.fillReport(rpt,hash,cnt);
            JasperViewer.viewReport(print,false);

        } catch (JRException e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                "Data tidak data tercetak"+"\n"+e.getMessage(),"Cetak Data",javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_Print_rpt_BtnMouseClicked

    private void Print_rpt_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Print_rpt_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Print;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Print = new ImageIcon("src/Picture/Effect/Print Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Print_rpt_Btn.setIcon(Btn_Print);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Print_rpt_BtnMouseEntered

    private void Print_rpt_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Print_rpt_BtnMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Print;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Print = new ImageIcon("src/Picture/Effect/Print Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Print_rpt_Btn.setIcon(Btn_Print);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Print_rpt_BtnMouseExited

    private void Print_rpt_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Print_rpt_BtnMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Print;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Print = new ImageIcon("src/Picture/Effect/Print Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Print_rpt_Btn.setIcon(Btn_Print);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Print_rpt_BtnMousePressed

    private void Print_rpt_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Print_rpt_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Print;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Print = new ImageIcon("src/Picture/Effect/Print Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Print_rpt_Btn.setIcon(Btn_Print);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Print_rpt_BtnMouseReleased

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Admin_DB_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_DB_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_DB_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_DB_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_DB_Page().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane About_Us_Page;
    private javax.swing.JLabel Admin_Foto;
    private javax.swing.JPanel Admin_Main_Panel;
    private javax.swing.JLabel Back_Btn;
    private javax.swing.JLabel Back_Btn1;
    private javax.swing.JLabel Back_Btn2;
    private javax.swing.JLabel Back_Btn3;
    private javax.swing.JLabel Back_Btn4;
    private javax.swing.JLabel Back_Btn5;
    private javax.swing.JLabel Back_Btn6;
    private javax.swing.JTextField Cari_Data_TextField;
    private javax.swing.JLabel Daftar_Barang_Btn;
    private javax.swing.JLabel Daftar_Jaskir_Btn;
    private javax.swing.JLabel Daftar_Kategori_Btn;
    private javax.swing.JLabel Daftar_Payment_Btn;
    private javax.swing.JLabel Daftar_Pengguna_Btn;
    private javax.swing.JLabel Daftar_Toko_Btn;
    private javax.swing.JLabel Data_Btn;
    private javax.swing.JScrollPane Data_Page;
    private javax.swing.JLabel Delete_Jaskir_btn;
    private javax.swing.JLabel Delete_btn;
    private javax.swing.JLabel Delete_subbtn;
    private javax.swing.JLabel Deletepay_btn;
    private javax.swing.JTextField Field_sum_User;
    private javax.swing.JTextField Field_sum_ctg;
    private javax.swing.JTextField Field_sum_item;
    private javax.swing.JTextField Field_sum_jaskir;
    private javax.swing.JTextField Field_sum_payment;
    private javax.swing.JTextField Field_sum_rpt;
    private javax.swing.JTextField Field_sum_seller;
    private javax.swing.JTextField Field_sum_subctg;
    private javax.swing.JLabel Home_Btn;
    private javax.swing.JLabel Home_Btn_eft2;
    private javax.swing.JScrollPane Home_Page;
    private javax.swing.JLabel Item_List_Btn;
    private javax.swing.JLabel Laporan_Btn;
    private javax.swing.JScrollPane Laporan_Page;
    private javax.swing.JScrollPane List_Category_Page;
    private javax.swing.JScrollPane List_Expedition_Page;
    private javax.swing.JScrollPane List_Item_Page;
    private javax.swing.JScrollPane List_Payment_Page;
    private javax.swing.JScrollPane List_Store_Page;
    private javax.swing.JScrollPane List_Subcategory_Page;
    private javax.swing.JScrollPane List_User_Page;
    private javax.swing.JLabel LogOut_Btn;
    private javax.swing.JLabel Logo_Picture;
    private javax.swing.JLabel More_Btn;
    private javax.swing.JLabel Print;
    private javax.swing.JLabel Print_Btn;
    private javax.swing.JLabel Print_a;
    private javax.swing.JLabel Print_rpt_Btn;
    private javax.swing.JPanel Search_Bar;
    private javax.swing.JLabel Search_Bar_Label;
    private javax.swing.JLabel Search_Btn;
    private javax.swing.JLabel Simpan_Ctg;
    private javax.swing.JLabel Simpan_Jaskir;
    private javax.swing.JLabel Simpan_Payment;
    private javax.swing.JLabel Simpan_UpJaskir;
    private javax.swing.JLabel Simpan_Upctg;
    private javax.swing.JLabel Simpan_Uppay;
    private javax.swing.JLabel Simpan_Upsubctg;
    private javax.swing.JLabel Simpan_subctg;
    private javax.swing.JLabel Subktg_Btn;
    private javax.swing.JLabel Telusuri_Data_Label;
    private javax.swing.JLabel Tentang_Btn;
    private javax.swing.JLabel Toko_List_Btn;
    private javax.swing.JLabel Upctg_Text;
    private javax.swing.JTextField Upctg_TextField;
    private javax.swing.JLabel Upjaskir_Text;
    private javax.swing.JTextField Upjaskir_TextField;
    private javax.swing.JLabel Upnewctg_Text;
    private javax.swing.JLabel Upnewctg_Text1;
    private javax.swing.JTextField Upnewctg_TextField;
    private javax.swing.JLabel Upnewjaskir_Text;
    private javax.swing.JTextField Upnewjaskir_TextField;
    private javax.swing.JTextField Upnewpay_TextField;
    private javax.swing.JLabel Upnewsubctg1_Text;
    private javax.swing.JTextField Upnewsubctg1_TextField;
    private javax.swing.JTextField Uppay_TextField;
    private javax.swing.JLabel Uppayment_Text;
    private javax.swing.JLabel Upsubctg_Text;
    private javax.swing.JTextField Upsubctg_TextField;
    private javax.swing.JLabel User_List_Btn;
    private javax.swing.JComboBox<String> ctg_combo;
    private javax.swing.JComboBox<String> ctg_combo_1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JComboBox<String> jaskir_combo;
    private javax.swing.JComboBox<String> jaskir_combo_1;
    private javax.swing.JComboBox<String> payment_combo;
    private javax.swing.JComboBox<String> payment_combo_1;
    private javax.swing.JComboBox<String> subctg_combo;
    private javax.swing.JComboBox<String> subctg_combo1;
    private javax.swing.JComboBox<String> subctg_combo2;
    private javax.swing.JTable tb_ctg;
    private javax.swing.JTable tb_item;
    private javax.swing.JTable tb_jaskir;
    private javax.swing.JTable tb_payment;
    private javax.swing.JTable tb_pengguna;
    private javax.swing.JTable tb_subctg;
    private javax.swing.JTable tb_toko;
    private javax.swing.JTable tb_transaksi;
    // End of variables declaration//GEN-END:variables
}

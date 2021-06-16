/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_User_Page;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import AppPackage.AnimationClass;
import Bilik_Pernik_Connection.BK_Connection;
import Pencarian.ProsesSearching;
import View_Opening_Page.Opening_Page_Pt_2;
import java.awt.Image;
import com.mysql.jdbc.Connection;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.mail.*;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
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
public class Home_Page extends javax.swing.JFrame {
    public String Namaa;
    public String Surell;
    Image images;
    byte[] photo=null;
    String filename=null;
    
    /**
     * Creates new form Home_Page
     */
    public Home_Page() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        NamaPenggunaProfile_TextField.setBackground(new java.awt.Color(0,0,0,1));
        NamaPenggunaHome_TextField.setBackground(new java.awt.Color(0,0,0,1));
        EmailHome_TextField.setBackground(new java.awt.Color(0,0,0,1));
        TelpHome_TextField.setBackground(new java.awt.Color(0,0,0,1));
        AlamatHome_TextField.setBackground(new java.awt.Color(0,0,0,1));
        slideshow();
        slideshow1();
        Email_TextField.setBackground(new java.awt.Color(0,0,0,1));
        KataSandi_TextField.setBackground(new java.awt.Color(0,0,0,1));
        
        jasbar();
        jaskir();
        jLabel34.setVisible(false);
        jScrollPane4.setVisible(false);
        tb_cart.setVisible(false);
        jLabel35.setVisible(false);
        jScrollPane5.setVisible(false);
        tb_wishlist.setVisible(false);
    }
    
   Home_Page(String Name, String Surel) {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Namaa = Name;
        Surell = Surel;
        NamaPenggunaProfile_TextField.setText(Namaa);
        NamaPenggunaHome_TextField.setText(Namaa);
        EmailHome_TextField.setText(Surell);
        NamaPenggunaHome_Text.setVisible(false);
        EmailHome_Text.setVisible(false);
        
        jasbar();
        jaskir();
        jLabel34.setVisible(false);
        jScrollPane4.setVisible(false);
        tb_cart.setVisible(false);
        jLabel35.setVisible(false);
        jScrollPane5.setVisible(false);
        tb_wishlist.setVisible(false);
    }
    
    AnimationClass AC = new AnimationClass();

    Home_Page(String Coba) {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        String Pass = Coba;
        PreparedStatement cmd;
        ResultSet rs;
        String query = "select * from tb_pengguna where katasandi = '"+Pass+"'";
        try {
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(query);
            rs =  cmd.executeQuery();
            if(rs.next()){
                
                Pengguna_ID.setText(Integer.toString(rs.getInt("id_pengguna")));
                NamaPenggunaHome_TextField1.setText(rs.getString("nama"));
                NamaPenggunaProfile_TextField.setText(rs.getString("nama"));
                EmailHome_TextField1.setText(rs.getString("email"));
                TelpHome_TextField1.setText(rs.getString("telp"));
                AlamatHome_TextField1.setText(rs.getString("alamat"));
                byte [] img=rs.getBytes("image");
                BufferedImage file=ImageIO.read(new ByteArrayInputStream(img));
                ImageIcon icon=new ImageIcon(resize(Image_Label.getWidth(), Image_Label.getHeight(), file));
                Image_Label.setIcon(icon);
                
                NamaPenggunaHome_TextField.setText(rs.getString("nama"));
                EmailHome_TextField.setText(rs.getString("email"));
                TelpHome_TextField.setText(rs.getString("telp"));
                AlamatHome_TextField.setText(rs.getString("alamat"));
                
                NamaPenggunaHome_Text1.setVisible(false);
                EmailHome_Text1.setVisible(false);
                TelpHome_Text1.setVisible(false);
                AlamatHome_Text1.setVisible(false);
                NamaPenggunaHome_Text.setVisible(false);
                EmailHome_Text.setVisible(false);
                TelpHome_Text.setVisible(false);
                AlamatHome_Text.setVisible(false);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e); /*Kondisi apabila terjadi kesalahan pada saat melakukan input data*/
        }
        
        jasbar();
        jaskir();
        jLabel34.setVisible(false);
        jScrollPane4.setVisible(false);
        tb_cart.setVisible(false);
        jLabel35.setVisible(false);
        jScrollPane5.setVisible(false);
        tb_wishlist.setVisible(false);
    }

    private void jasbar(){
        JasBar_CB.removeAllItems();
        JasBar_CB.addItem("Pilih Metode Pembayaran");
        
        Statement cmd;
        ResultSet rs;
        String sql = "select*from tb_mitra_bayar";
        
        try {  
            cmd = BK_Connection.getBilik_Pernik_Connect().createStatement();
            rs = cmd.executeQuery(sql);  
            while(rs.next()){
               JasBar_CB.addItem(rs.getString(2));
            }
                
        } catch (Exception e) {
                System.out.println("Error\n"+e);
        }
    }
    
    private void jaskir(){
        JasKir_CB.removeAllItems();
        JasKir_CB.addItem("Pilih Metode Pengiriman");
        
        Statement cmd;
        ResultSet rs;
        String sql = "select*from tb_mitra_kirim";
        
        try {  
            cmd = BK_Connection.getBilik_Pernik_Connect().createStatement();
            rs = cmd.executeQuery(sql);  
            while(rs.next()){
               JasKir_CB.addItem(rs.getString(2));
            }
                
        } catch (Exception e) {
                System.out.println("Error\n"+e);
        }
    }
   
    public void slideshow(){
       new Thread(new Runnable() {
       @Override
       public void run(){
           int tb=0;
           try{
               while(true){
                   switch(tb){
                       case 0:
                         Thread.sleep(1000);
                         AC.jLabelXLeft(0, -1920, 12 ,10, jLabel2);
                         AC.jLabelXLeft(1920, 0, 12 ,10, jLabel18);
                         AC.jLabelXLeft(3840, 1920, 12 ,10, jLabel16);
                         tb++;
                        case 1:
                         Thread.sleep(1000);
                         AC.jLabelXLeft(-1920, -3840, 12 ,10, jLabel2);
                         AC.jLabelXLeft(0, -1920, 12 ,10, jLabel18);
                         AC.jLabelXLeft(1920, 0, 12 ,10, jLabel16);
                         tb++;
                        
                        case 2:
                         Thread.sleep(1000);
                         AC.jLabelXRight(-3840, -1920, 12 ,10, jLabel2);
                         AC.jLabelXRight(-1920, 0, 12 ,10, jLabel18);
                         AC.jLabelXRight(0, 1920, 12 ,10, jLabel16);
                         tb++;
                        case 3:
                         Thread.sleep(1000);
                         AC.jLabelXRight(-1920, 0, 12 ,10, jLabel2);
                         AC.jLabelXRight(0, 1920, 12 ,10, jLabel18);
                         AC.jLabelXRight(1920, 3840, 12 ,10, jLabel16);
                         tb=0;
                   }
               }
           }catch(Exception ex){
               
           }
       }
           
       }).start();
    }
   
    public void slideshow1(){
       new Thread(new Runnable() {
       @Override
       public void run(){
           int tb=0;
           try{
               while(true){
                   switch(tb){
                       case 0:
                         Thread.sleep(1000);
                         AC.jLabelXLeft(0, -1920, 12 ,10, jLabel10);
                         AC.jLabelXLeft(1920, 0, 12 ,10, jLabel21);
                         AC.jLabelXLeft(3840, 1920, 12 ,10, jLabel22);
                         tb++;
                        case 1:
                         Thread.sleep(1000);
                         AC.jLabelXLeft(-1920, -3840, 12 ,10, jLabel10);
                         AC.jLabelXLeft(0, -1920, 12 ,10, jLabel21);
                         AC.jLabelXLeft(1920, 0, 12 ,10, jLabel22);
                         tb++;
                        
                        case 2:
                         Thread.sleep(1000);
                         AC.jLabelXRight(-3840, -1920, 12 ,10, jLabel10);
                         AC.jLabelXRight(-1920, 0, 12 ,10, jLabel21);
                         AC.jLabelXRight(0, 1920, 12 ,10, jLabel22);
                         tb++;
                        case 3:
                         Thread.sleep(1000);
                         AC.jLabelXRight(-1920, 0, 12 ,10, jLabel10);
                         AC.jLabelXRight(0, 1920, 12 ,10, jLabel21);
                         AC.jLabelXRight(1920, 3840, 12 ,10, jLabel22);
                         tb=0;
                   }
               }
           }catch(Exception ex){
               
           }
       }
           
       }).start();
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Search_ScrollPanel = new javax.swing.JScrollPane();
        Pencarian_Tabel = new javax.swing.JTable();
        Logo_Picture = new javax.swing.JLabel();
        Search_Btn = new javax.swing.JLabel();
        Tentang_Btn = new javax.swing.JLabel();
        Kontak_Btn = new javax.swing.JLabel();
        Belanja_Btn = new javax.swing.JLabel();
        Home_Btn_eft2 = new javax.swing.JLabel();
        Home_Btn = new javax.swing.JLabel();
        Wishlist_Btn = new javax.swing.JLabel();
        Chart_Btn = new javax.swing.JLabel();
        Telusuri_Produk_Label = new javax.swing.JLabel();
        User_Btn = new javax.swing.JLabel();
        Cari_Item_TextField = new javax.swing.JTextField();
        Search_Bar_Label = new javax.swing.JLabel();
        Main_Panel = new javax.swing.JPanel();
        Home_Panel = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        Instagram_Klik1 = new javax.swing.JLabel();
        Twitter_Klik1 = new javax.swing.JLabel();
        Facebook_Klik1 = new javax.swing.JLabel();
        MataTutup_Icon = new javax.swing.JLabel();
        KataSandi_TextField = new javax.swing.JPasswordField();
        Kirim_Pesan_Btn = new javax.swing.JLabel();
        Belanja_Btn1 = new javax.swing.JLabel();
        Email_Label = new javax.swing.JLabel();
        KataSandi_Label = new javax.swing.JLabel();
        Masukkan_Pesan_Label = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Message_TextField = new javax.swing.JTextArea();
        Selengkapnya_Btn = new javax.swing.JLabel();
        Email_TextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        Belanja_Panel = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        Toko_C = new javax.swing.JTextField();
        Jalan_C = new javax.swing.JTextField();
        TokoC_Panel = new javax.swing.JDesktopPane();
        Image_C1 = new javax.swing.JLabel();
        Toko_A = new javax.swing.JTextField();
        Jalan_A = new javax.swing.JTextField();
        TokoA_Panel = new javax.swing.JDesktopPane();
        Image_A1 = new javax.swing.JLabel();
        Toko_B = new javax.swing.JTextField();
        Jalan_B = new javax.swing.JTextField();
        TokoB_Panel = new javax.swing.JDesktopPane();
        Image_B1 = new javax.swing.JLabel();
        Next_Btn = new javax.swing.JLabel();
        Prev_Btn = new javax.swing.JLabel();
        Bullet3 = new javax.swing.JLabel();
        Bullet2 = new javax.swing.JLabel();
        Bullet1 = new javax.swing.JLabel();
        More_Btn1 = new javax.swing.JLabel();
        More_Btn = new javax.swing.JLabel();
        TC_Panel = new javax.swing.JDesktopPane();
        Image_TC = new javax.swing.JLabel();
        Produk_TC = new javax.swing.JTextField();
        Harga_TC = new javax.swing.JTextField();
        Produk3_Btn = new javax.swing.JLabel();
        TB_Panel = new javax.swing.JDesktopPane();
        Image_TB = new javax.swing.JLabel();
        Produk_TB = new javax.swing.JTextField();
        Harga_TB = new javax.swing.JTextField();
        Produk2_Btn = new javax.swing.JLabel();
        TA_Panel = new javax.swing.JDesktopPane();
        Image_TA = new javax.swing.JLabel();
        Produk_TA = new javax.swing.JTextField();
        Harga_TA = new javax.swing.JTextField();
        Produk1_Btn = new javax.swing.JLabel();
        Parsel = new javax.swing.JLabel();
        Merchandise = new javax.swing.JLabel();
        Dekorasi = new javax.swing.JLabel();
        Kado = new javax.swing.JLabel();
        Souvernir = new javax.swing.JLabel();
        TokoC = new javax.swing.JLabel();
        Produk_D = new javax.swing.JTextField();
        Harga_D = new javax.swing.JTextField();
        ProdukD_Panel = new javax.swing.JDesktopPane();
        Image_D = new javax.swing.JLabel();
        Produk_B = new javax.swing.JTextField();
        Harga_B = new javax.swing.JTextField();
        ProdukB_Panel = new javax.swing.JDesktopPane();
        Image_B = new javax.swing.JLabel();
        Produk_A = new javax.swing.JTextField();
        Harga_A = new javax.swing.JTextField();
        ProdukA_Panel = new javax.swing.JDesktopPane();
        Image_A = new javax.swing.JLabel();
        Produk_C = new javax.swing.JTextField();
        Harga_C = new javax.swing.JTextField();
        ProdukC_Panel = new javax.swing.JDesktopPane();
        Image_C = new javax.swing.JLabel();
        TokoB = new javax.swing.JLabel();
        TokoA = new javax.swing.JLabel();
        ProdukD = new javax.swing.JLabel();
        ProdukC = new javax.swing.JLabel();
        ProdukB = new javax.swing.JLabel();
        ProdukA = new javax.swing.JLabel();
        Opening_Shop_Panel = new javax.swing.JPanel();
        Opening1_Panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        Opening2_Panel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        Opening3_Panel = new javax.swing.JPanel();
        Buy_Now_Btn = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Our_Item_Panel = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        Next_Btn1 = new javax.swing.JLabel();
        Prev_Btn1 = new javax.swing.JLabel();
        Back_Btn = new javax.swing.JLabel();
        Bullet_Item3 = new javax.swing.JLabel();
        Bullet_Item1 = new javax.swing.JLabel();
        Bullet_Item2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        ProdukF2 = new javax.swing.JLabel();
        ProdukE2 = new javax.swing.JLabel();
        ProdukD2 = new javax.swing.JLabel();
        ProdukC2 = new javax.swing.JLabel();
        ProdukB2 = new javax.swing.JLabel();
        ProdukA2 = new javax.swing.JLabel();
        Opening_Item_Panel = new javax.swing.JPanel();
        Opening1_Panel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        Opening2_Panel1 = new javax.swing.JPanel();
        Buy_Now_Btn3 = new javax.swing.JLabel();
        Buy_Now_Btn2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Opening3_Panel1 = new javax.swing.JPanel();
        Buy_Now_Btn1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Our_Channels_Panel = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        TokoH2 = new javax.swing.JLabel();
        TokoG2 = new javax.swing.JLabel();
        TokoE2 = new javax.swing.JLabel();
        TokoD2 = new javax.swing.JLabel();
        TokoC2 = new javax.swing.JLabel();
        TokoB2 = new javax.swing.JLabel();
        TokoA2 = new javax.swing.JLabel();
        TokoF2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        Back_Btn1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        HasilPencarianProduk_Panel = new javax.swing.JScrollPane();
        jPanel10 = new javax.swing.JPanel();
        jDesktopPane = new javax.swing.JDesktopPane();
        Produk_Label = new javax.swing.JLabel();
        Banyak_Text = new javax.swing.JLabel();
        NamaProduk_TextField = new javax.swing.JTextField();
        Total_TextField = new javax.swing.JTextField();
        Banyak_TextField = new javax.swing.JTextField();
        Harga_TextField = new javax.swing.JTextField();
        Tinggi_TextField = new javax.swing.JTextField();
        Lebar_TextField = new javax.swing.JTextField();
        Massa_TextField = new javax.swing.JTextField();
        Panjang_TextField = new javax.swing.JTextField();
        Toko_TextField = new javax.swing.JTextField();
        Beli_Tombol = new javax.swing.JLabel();
        Chart_Btn1 = new javax.swing.JLabel();
        Wishlist_Btn1 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        Produk_ID = new javax.swing.JLabel();
        Transaksi_Panel = new javax.swing.JScrollPane();
        jPanel11 = new javax.swing.JPanel();
        Next_Tombol = new javax.swing.JLabel();
        TotalHargaTrans_TextField = new javax.swing.JTextField();
        BanyakBeliTrans_TextField = new javax.swing.JTextField();
        NamaProdukTrans_TextField = new javax.swing.JTextField();
        HargaProdukTrans_TextField = new javax.swing.JTextField();
        JasBar_CB = new javax.swing.JComboBox<>();
        JasKir_CB = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        Transaksi_TGL = new javax.swing.JTextField();
        Transaksi_ID = new javax.swing.JTextField();
        Seller_ID = new javax.swing.JTextField();
        Transaksi2_Panel = new javax.swing.JScrollPane();
        jPanel12 = new javax.swing.JPanel();
        Next2_Tombol = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        Transaksi3_Panel = new javax.swing.JScrollPane();
        jPanel13 = new javax.swing.JPanel();
        Cetak_Tombol = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        Kontak_Panel = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        Instagram_Klik2 = new javax.swing.JLabel();
        Twitter_Klik2 = new javax.swing.JLabel();
        Facebook_Klik2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        About_Us_Panel = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        Wishlist_Panel = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        Social_Media4 = new javax.swing.JLabel();
        Social_Media5 = new javax.swing.JLabel();
        Social_Media6 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        Ayo_Belanja2_Btn1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tb_wishlist = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        Keranjang_Panel = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        Ayo_Belanja2_Btn = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_cart = new javax.swing.JTable();
        Social_Media3 = new javax.swing.JLabel();
        Social_Media2 = new javax.swing.JLabel();
        Social_Media1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        UserPagePanel = new javax.swing.JScrollPane();
        jPanel9 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        Image_Label = new javax.swing.JLabel();
        NamaPenggunaHome_Text1 = new javax.swing.JLabel();
        NamaPenggunaHome_TextField1 = new javax.swing.JTextField();
        EmailHome_Text1 = new javax.swing.JLabel();
        EmailHome_TextField1 = new javax.swing.JTextField();
        TelpHome_Text1 = new javax.swing.JLabel();
        TelpHome_TextField1 = new javax.swing.JTextField();
        AlamatHome_Text1 = new javax.swing.JLabel();
        AlamatHome_TextField1 = new javax.swing.JTextField();
        NamaPenggunaHome_Text = new javax.swing.JLabel();
        EmailHome_Text = new javax.swing.JLabel();
        TelpHome_Text = new javax.swing.JLabel();
        TelpHome_TextField = new javax.swing.JTextField();
        AlamatHome_Text = new javax.swing.JLabel();
        AlamatHome_TextField = new javax.swing.JTextField();
        Simpan_Tombol = new javax.swing.JLabel();
        Instagram_Klik = new javax.swing.JLabel();
        Twitter_Klik = new javax.swing.JLabel();
        Facebook_Klik = new javax.swing.JLabel();
        GantiFoto_Tombol = new javax.swing.JLabel();
        User_Label = new javax.swing.JLabel();
        Pengguna_ID = new javax.swing.JTextField();
        Filename_Text = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bilik Pernik");
        setBackground(new java.awt.Color(49, 58, 148));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Search_ScrollPanel.setBorder(null);

        Pencarian_Tabel.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        Pencarian_Tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null}
            },
            new String [] {
                ""
            }
        ));
        Pencarian_Tabel.setGridColor(new java.awt.Color(255, 255, 255));
        Pencarian_Tabel.setOpaque(false);
        Pencarian_Tabel.setRowHeight(36);
        Pencarian_Tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Pencarian_TabelMouseClicked(evt);
            }
        });
        Search_ScrollPanel.setViewportView(Pencarian_Tabel);

        getContentPane().add(Search_ScrollPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 110, 559, 110));

        Logo_Picture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/LoginPage/Logo.png"))); // NOI18N
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
        getContentPane().add(Logo_Picture, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, -1, -1));

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
        getContentPane().add(Search_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1207, 44, -1, -1));

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
        getContentPane().add(Tentang_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 128, -1, -1));

        Kontak_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Kontak.png"))); // NOI18N
        Kontak_Btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Kontak_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Kontak_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Kontak_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Kontak_BtnMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Kontak_BtnMouseReleased(evt);
            }
        });
        getContentPane().add(Kontak_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 128, -1, -1));

        Belanja_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Belanja.png"))); // NOI18N
        Belanja_Btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Belanja_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Belanja_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Belanja_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Belanja_BtnMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Belanja_BtnMouseReleased(evt);
            }
        });
        getContentPane().add(Belanja_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 128, -1, -1));

        Home_Btn_eft2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Rumah effect 2.png"))); // NOI18N
        getContentPane().add(Home_Btn_eft2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 128, -1, -1));

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
        getContentPane().add(Home_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 128, -1, -1));

        Wishlist_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/ic_baseline-favorite-border.png"))); // NOI18N
        Wishlist_Btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Wishlist_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Wishlist_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Wishlist_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Wishlist_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Wishlist_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Wishlist_BtnMouseReleased(evt);
            }
        });
        getContentPane().add(Wishlist_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 50, -1, -1));

        Chart_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/clarity_shopping-cart-line.png"))); // NOI18N
        Chart_Btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Chart_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Chart_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Chart_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Chart_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Chart_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Chart_BtnMouseReleased(evt);
            }
        });
        getContentPane().add(Chart_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 50, -1, -1));

        Telusuri_Produk_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Telusuri produk.png"))); // NOI18N
        getContentPane().add(Telusuri_Produk_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, -1, -1));

        User_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/carbon_user-avatar-filled-alt.png"))); // NOI18N
        User_Btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        User_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                User_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                User_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                User_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                User_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                User_BtnMouseReleased(evt);
            }
        });
        getContentPane().add(User_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1660, 40, -1, -1));

        Cari_Item_TextField.setBackground(new java.awt.Color(235, 239, 246));
        Cari_Item_TextField.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        Cari_Item_TextField.setForeground(new java.awt.Color(102, 102, 102));
        Cari_Item_TextField.setBorder(null);
        Cari_Item_TextField.setCaretColor(new java.awt.Color(102, 102, 102));
        Cari_Item_TextField.setOpaque(false);
        Cari_Item_TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Cari_Item_TextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Cari_Item_TextFieldFocusLost(evt);
            }
        });
        Cari_Item_TextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Cari_Item_TextFieldMouseClicked(evt);
            }
        });
        Cari_Item_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Cari_Item_TextFieldKeyReleased(evt);
            }
        });
        getContentPane().add(Cari_Item_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 66, 500, 30));

        Search_Bar_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Group 86.png"))); // NOI18N
        Search_Bar_Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Search_Bar_LabelMouseClicked(evt);
            }
        });
        getContentPane().add(Search_Bar_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1940, -1));

        Main_Panel.setLayout(new java.awt.CardLayout());

        Home_Panel.setBackground(new java.awt.Color(102, 102, 255));
        Home_Panel.setBorder(null);
        Home_Panel.setAutoscrolls(true);
        Home_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Home_Panel.setEnabled(false);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Instagram_Klik1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Instagram Btn.png"))); // NOI18N
        Instagram_Klik1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Instagram_Klik1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Instagram_Klik1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Instagram_Klik1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Instagram_Klik1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Instagram_Klik1MouseReleased(evt);
            }
        });
        jPanel2.add(Instagram_Klik1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1830, 210, -1, -1));

        Twitter_Klik1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Twitter Btn.png"))); // NOI18N
        Twitter_Klik1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Twitter_Klik1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Twitter_Klik1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Twitter_Klik1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Twitter_Klik1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Twitter_Klik1MouseReleased(evt);
            }
        });
        jPanel2.add(Twitter_Klik1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1830, 340, -1, -1));

        Facebook_Klik1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Facebook Btn.png"))); // NOI18N
        Facebook_Klik1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Facebook_Klik1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Facebook_Klik1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Facebook_Klik1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Facebook_Klik1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Facebook_Klik1MouseReleased(evt);
            }
        });
        jPanel2.add(Facebook_Klik1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1830, 480, -1, -1));

        MataTutup_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/LoginPage/Mata Tutup.png"))); // NOI18N
        MataTutup_Icon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MataTutup_IconMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                MataTutup_IconMousePressed(evt);
            }
        });
        jPanel2.add(MataTutup_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 3450, -1, -1));

        KataSandi_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        KataSandi_TextField.setForeground(new java.awt.Color(255, 255, 255));
        KataSandi_TextField.setBorder(null);
        KataSandi_TextField.setEchoChar('\u25cf');
        KataSandi_TextField.setOpaque(false);
        KataSandi_TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                KataSandi_TextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                KataSandi_TextFieldFocusLost(evt);
            }
        });
        KataSandi_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KataSandi_TextFieldKeyPressed(evt);
            }
        });
        jPanel2.add(KataSandi_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 3450, 360, 30));

        Kirim_Pesan_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Kirim Pesan.png"))); // NOI18N
        Kirim_Pesan_Btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Kirim_Pesan_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Kirim_Pesan_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Kirim_Pesan_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Kirim_Pesan_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Kirim_Pesan_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Kirim_Pesan_BtnMouseReleased(evt);
            }
        });
        jPanel2.add(Kirim_Pesan_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 3760, -1, -1));

        Belanja_Btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Masuk.png"))); // NOI18N
        Belanja_Btn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Belanja_Btn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Belanja_Btn1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Belanja_Btn1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Belanja_Btn1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Belanja_Btn1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Belanja_Btn1MouseReleased(evt);
            }
        });
        jPanel2.add(Belanja_Btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 620, -1, -1));

        Email_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Email.png"))); // NOI18N
        jPanel2.add(Email_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 3345, -1, -1));

        KataSandi_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Kata Sandi.png"))); // NOI18N
        jPanel2.add(KataSandi_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 3455, -1, -1));

        Masukkan_Pesan_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Masukan pesan.png"))); // NOI18N
        jPanel2.add(Masukkan_Pesan_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 3550, -1, -1));

        jScrollPane1.setBackground(new java.awt.Color(220, 139, 27));
        jScrollPane1.setBorder(null);

        Message_TextField.setBackground(new java.awt.Color(202, 129, 36));
        Message_TextField.setColumns(20);
        Message_TextField.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        Message_TextField.setForeground(new java.awt.Color(255, 255, 255));
        Message_TextField.setRows(10);
        Message_TextField.setTabSize(1);
        Message_TextField.setBorder(null);
        Message_TextField.setCaretColor(new java.awt.Color(255, 255, 255));
        Message_TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Message_TextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Message_TextFieldFocusLost(evt);
            }
        });
        Message_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Message_TextFieldKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(Message_TextField);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(256, 3528, 440, 170));

        Selengkapnya_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/selengkapnya_Btn.png"))); // NOI18N
        Selengkapnya_Btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Selengkapnya_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Selengkapnya_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Selengkapnya_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Selengkapnya_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Selengkapnya_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Selengkapnya_BtnMouseReleased(evt);
            }
        });
        jPanel2.add(Selengkapnya_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 2660, -1, -1));

        Email_TextField.setBackground(new java.awt.Color(220, 139, 27));
        Email_TextField.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        Email_TextField.setForeground(new java.awt.Color(255, 255, 255));
        Email_TextField.setBorder(null);
        Email_TextField.setCaretColor(new java.awt.Color(255, 255, 255));
        Email_TextField.setOpaque(false);
        Email_TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Email_TextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Email_TextFieldFocusLost(evt);
            }
        });
        Email_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Email_TextFieldKeyPressed(evt);
            }
        });
        jPanel2.add(Email_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 3340, 400, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Home Page.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Home_Panel.setViewportView(jPanel2);

        Main_Panel.add(Home_Panel, "card2");

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Toko_C.setEditable(false);
        Toko_C.setFont(new java.awt.Font("Serif", 0, 30)); // NOI18N
        Toko_C.setForeground(new java.awt.Color(255, 255, 255));
        Toko_C.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Toko_C.setBorder(null);
        Toko_C.setOpaque(false);
        jPanel1.add(Toko_C, new org.netbeans.lib.awtextra.AbsoluteConstraints(1505, 2750, 400, 50));

        Jalan_C.setEditable(false);
        Jalan_C.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        Jalan_C.setForeground(new java.awt.Color(255, 255, 255));
        Jalan_C.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Jalan_C.setBorder(null);
        Jalan_C.setOpaque(false);
        jPanel1.add(Jalan_C, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 2810, 410, 50));

        TokoC_Panel.setMaximumSize(new java.awt.Dimension(481, 688));
        TokoC_Panel.setMinimumSize(new java.awt.Dimension(481, 688));
        TokoC_Panel.setOpaque(false);

        TokoC_Panel.setLayer(Image_C1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout TokoC_PanelLayout = new javax.swing.GroupLayout(TokoC_Panel);
        TokoC_Panel.setLayout(TokoC_PanelLayout);
        TokoC_PanelLayout.setHorizontalGroup(
            TokoC_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Image_C1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
        );
        TokoC_PanelLayout.setVerticalGroup(
            TokoC_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TokoC_PanelLayout.createSequentialGroup()
                .addComponent(Image_C1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 312, Short.MAX_VALUE))
        );

        jPanel1.add(TokoC_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1494, 2345, 426, 376));

        Toko_A.setEditable(false);
        Toko_A.setFont(new java.awt.Font("Serif", 0, 30)); // NOI18N
        Toko_A.setForeground(new java.awt.Color(255, 255, 255));
        Toko_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Toko_A.setBorder(null);
        Toko_A.setOpaque(false);
        jPanel1.add(Toko_A, new org.netbeans.lib.awtextra.AbsoluteConstraints(657, 2750, 400, 50));

        Jalan_A.setEditable(false);
        Jalan_A.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        Jalan_A.setForeground(new java.awt.Color(255, 255, 255));
        Jalan_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Jalan_A.setBorder(null);
        Jalan_A.setOpaque(false);
        jPanel1.add(Jalan_A, new org.netbeans.lib.awtextra.AbsoluteConstraints(657, 2810, 410, 50));

        TokoA_Panel.setMaximumSize(new java.awt.Dimension(481, 688));
        TokoA_Panel.setMinimumSize(new java.awt.Dimension(481, 688));
        TokoA_Panel.setOpaque(false);

        TokoA_Panel.setLayer(Image_A1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout TokoA_PanelLayout = new javax.swing.GroupLayout(TokoA_Panel);
        TokoA_Panel.setLayout(TokoA_PanelLayout);
        TokoA_PanelLayout.setHorizontalGroup(
            TokoA_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Image_A1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
        );
        TokoA_PanelLayout.setVerticalGroup(
            TokoA_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TokoA_PanelLayout.createSequentialGroup()
                .addComponent(Image_A1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 312, Short.MAX_VALUE))
        );

        jPanel1.add(TokoA_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(642, 2345, 426, 376));

        Toko_B.setEditable(false);
        Toko_B.setFont(new java.awt.Font("Serif", 0, 30)); // NOI18N
        Toko_B.setForeground(new java.awt.Color(255, 255, 255));
        Toko_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Toko_B.setBorder(null);
        Toko_B.setOpaque(false);
        jPanel1.add(Toko_B, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 2750, 400, 50));

        Jalan_B.setEditable(false);
        Jalan_B.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        Jalan_B.setForeground(new java.awt.Color(255, 255, 255));
        Jalan_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Jalan_B.setBorder(null);
        Jalan_B.setOpaque(false);
        jPanel1.add(Jalan_B, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 2810, 410, 50));

        TokoB_Panel.setMaximumSize(new java.awt.Dimension(481, 688));
        TokoB_Panel.setMinimumSize(new java.awt.Dimension(481, 688));
        TokoB_Panel.setOpaque(false);

        TokoB_Panel.setLayer(Image_B1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout TokoB_PanelLayout = new javax.swing.GroupLayout(TokoB_Panel);
        TokoB_Panel.setLayout(TokoB_PanelLayout);
        TokoB_PanelLayout.setHorizontalGroup(
            TokoB_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Image_B1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
        );
        TokoB_PanelLayout.setVerticalGroup(
            TokoB_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TokoB_PanelLayout.createSequentialGroup()
                .addComponent(Image_B1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 312, Short.MAX_VALUE))
        );

        jPanel1.add(TokoB_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1068, 2345, 426, 376));

        Next_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Next Btn.png"))); // NOI18N
        Next_Btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Next_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Next_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Next_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Next_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Next_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Next_BtnMouseReleased(evt);
            }
        });
        jPanel1.add(Next_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1665, 380, -1, -1));

        Prev_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Prev Btn.png"))); // NOI18N
        Prev_Btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Prev_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Prev_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Prev_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Prev_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Prev_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Prev_BtnMouseReleased(evt);
            }
        });
        jPanel1.add(Prev_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 380, -1, -1));

        Bullet3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Bullet.png"))); // NOI18N
        Bullet3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Bullet3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Bullet3MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Bullet3MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Bullet3MouseReleased(evt);
            }
        });
        jPanel1.add(Bullet3, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 760, -1, -1));

        Bullet2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Bullet.png"))); // NOI18N
        Bullet2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Bullet2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Bullet2MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Bullet2MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Bullet2MouseReleased(evt);
            }
        });
        jPanel1.add(Bullet2, new org.netbeans.lib.awtextra.AbsoluteConstraints(947, 760, -1, -1));

        Bullet1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Bullet Effect.png"))); // NOI18N
        Bullet1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Bullet1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Bullet1MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Bullet1MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Bullet1MouseReleased(evt);
            }
        });
        jPanel1.add(Bullet1, new org.netbeans.lib.awtextra.AbsoluteConstraints(912, 760, -1, -1));

        More_Btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Masuk.png"))); // NOI18N
        More_Btn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        More_Btn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                More_Btn1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                More_Btn1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                More_Btn1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                More_Btn1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                More_Btn1MouseReleased(evt);
            }
        });
        jPanel1.add(More_Btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 2730, -1, -1));

        More_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Masuk.png"))); // NOI18N
        More_Btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        jPanel1.add(More_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 1360, -1, -1));

        TC_Panel.setMaximumSize(new java.awt.Dimension(481, 688));
        TC_Panel.setMinimumSize(new java.awt.Dimension(481, 688));
        TC_Panel.setOpaque(false);

        Image_TC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Image_TCMouseClicked(evt);
            }
        });

        TC_Panel.setLayer(Image_TC, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout TC_PanelLayout = new javax.swing.GroupLayout(TC_Panel);
        TC_Panel.setLayout(TC_PanelLayout);
        TC_PanelLayout.setHorizontalGroup(
            TC_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TC_PanelLayout.createSequentialGroup()
                .addComponent(Image_TC, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 161, Short.MAX_VALUE))
        );
        TC_PanelLayout.setVerticalGroup(
            TC_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TC_PanelLayout.createSequentialGroup()
                .addComponent(Image_TC, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 338, Short.MAX_VALUE))
        );

        jPanel1.add(TC_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1540, 1000, 320, 350));

        Produk_TC.setEditable(false);
        Produk_TC.setFont(new java.awt.Font("Serif", 0, 30)); // NOI18N
        Produk_TC.setForeground(new java.awt.Color(255, 255, 255));
        Produk_TC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Produk_TC.setBorder(null);
        Produk_TC.setOpaque(false);
        jPanel1.add(Produk_TC, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 1370, 400, 50));

        Harga_TC.setEditable(false);
        Harga_TC.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        Harga_TC.setForeground(new java.awt.Color(255, 255, 255));
        Harga_TC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Harga_TC.setBorder(null);
        Harga_TC.setOpaque(false);
        jPanel1.add(Harga_TC, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 1430, 410, 50));

        Produk3_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Produk 3.png"))); // NOI18N
        Produk3_Btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Produk3_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Produk3_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Produk3_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Produk3_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Produk3_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Produk3_BtnMouseReleased(evt);
            }
        });
        jPanel1.add(Produk3_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1493, 977, -1, -1));

        TB_Panel.setMaximumSize(new java.awt.Dimension(481, 688));
        TB_Panel.setMinimumSize(new java.awt.Dimension(481, 688));
        TB_Panel.setOpaque(false);

        Image_TB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Image_TBMouseClicked(evt);
            }
        });

        TB_Panel.setLayer(Image_TB, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout TB_PanelLayout = new javax.swing.GroupLayout(TB_Panel);
        TB_Panel.setLayout(TB_PanelLayout);
        TB_PanelLayout.setHorizontalGroup(
            TB_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TB_PanelLayout.createSequentialGroup()
                .addComponent(Image_TB, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 161, Short.MAX_VALUE))
        );
        TB_PanelLayout.setVerticalGroup(
            TB_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TB_PanelLayout.createSequentialGroup()
                .addComponent(Image_TB, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 338, Short.MAX_VALUE))
        );

        jPanel1.add(TB_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 1000, 320, 350));

        Produk_TB.setEditable(false);
        Produk_TB.setFont(new java.awt.Font("Serif", 0, 30)); // NOI18N
        Produk_TB.setForeground(new java.awt.Color(255, 255, 255));
        Produk_TB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Produk_TB.setBorder(null);
        Produk_TB.setOpaque(false);
        jPanel1.add(Produk_TB, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 1370, 400, 50));

        Harga_TB.setEditable(false);
        Harga_TB.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        Harga_TB.setForeground(new java.awt.Color(255, 255, 255));
        Harga_TB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Harga_TB.setBorder(null);
        Harga_TB.setOpaque(false);
        jPanel1.add(Harga_TB, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 1430, 410, 50));

        Produk2_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Produk 2.png"))); // NOI18N
        Produk2_Btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Produk2_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Produk2_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Produk2_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Produk2_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Produk2_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Produk2_BtnMouseReleased(evt);
            }
        });
        jPanel1.add(Produk2_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1066, 977, -1, -1));

        TA_Panel.setMaximumSize(new java.awt.Dimension(481, 688));
        TA_Panel.setMinimumSize(new java.awt.Dimension(481, 688));
        TA_Panel.setOpaque(false);
        TA_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TA_PanelMouseClicked(evt);
            }
        });

        TA_Panel.setLayer(Image_TA, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout TA_PanelLayout = new javax.swing.GroupLayout(TA_Panel);
        TA_Panel.setLayout(TA_PanelLayout);
        TA_PanelLayout.setHorizontalGroup(
            TA_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TA_PanelLayout.createSequentialGroup()
                .addComponent(Image_TA, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 161, Short.MAX_VALUE))
        );
        TA_PanelLayout.setVerticalGroup(
            TA_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TA_PanelLayout.createSequentialGroup()
                .addComponent(Image_TA, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 338, Short.MAX_VALUE))
        );

        jPanel1.add(TA_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 1000, 320, 350));

        Produk_TA.setEditable(false);
        Produk_TA.setFont(new java.awt.Font("Serif", 0, 30)); // NOI18N
        Produk_TA.setForeground(new java.awt.Color(255, 255, 255));
        Produk_TA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Produk_TA.setBorder(null);
        Produk_TA.setOpaque(false);
        jPanel1.add(Produk_TA, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 1370, 400, 50));

        Harga_TA.setEditable(false);
        Harga_TA.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        Harga_TA.setForeground(new java.awt.Color(255, 255, 255));
        Harga_TA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Harga_TA.setBorder(null);
        Harga_TA.setOpaque(false);
        jPanel1.add(Harga_TA, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 1430, 410, 50));

        Produk1_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Produk 1.png"))); // NOI18N
        Produk1_Btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Produk1_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Produk1_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Produk1_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Produk1_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Produk1_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Produk1_BtnMouseReleased(evt);
            }
        });
        jPanel1.add(Produk1_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(641, 977, -1, -1));

        Parsel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Parsel.png"))); // NOI18N
        Parsel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Parsel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ParselMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ParselMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ParselMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ParselMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ParselMouseReleased(evt);
            }
        });
        jPanel1.add(Parsel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1493, 1521, -1, -1));

        Merchandise.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Merchandise.png"))); // NOI18N
        Merchandise.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Merchandise.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MerchandiseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MerchandiseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MerchandiseMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                MerchandiseMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                MerchandiseMouseReleased(evt);
            }
        });
        jPanel1.add(Merchandise, new org.netbeans.lib.awtextra.AbsoluteConstraints(1493, 1933, -1, -1));

        Dekorasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Dekorasi.png"))); // NOI18N
        Dekorasi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Dekorasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DekorasiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DekorasiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DekorasiMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                DekorasiMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                DekorasiMouseReleased(evt);
            }
        });
        jPanel1.add(Dekorasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1069, 1933, -1, -1));

        Kado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Kado.png"))); // NOI18N
        Kado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Kado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KadoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                KadoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                KadoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                KadoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                KadoMouseReleased(evt);
            }
        });
        jPanel1.add(Kado, new org.netbeans.lib.awtextra.AbsoluteConstraints(642, 1933, -1, -1));

        Souvernir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Souvenir.png"))); // NOI18N
        Souvernir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Souvernir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SouvernirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SouvernirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SouvernirMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SouvernirMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                SouvernirMouseReleased(evt);
            }
        });
        jPanel1.add(Souvernir, new org.netbeans.lib.awtextra.AbsoluteConstraints(642, 1521, -1, -1));

        TokoC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Toko C.png"))); // NOI18N
        TokoC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TokoC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TokoCMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TokoCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TokoCMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TokoCMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TokoCMouseReleased(evt);
            }
        });
        jPanel1.add(TokoC, new org.netbeans.lib.awtextra.AbsoluteConstraints(1494, 2345, -1, -1));

        Produk_D.setEditable(false);
        Produk_D.setFont(new java.awt.Font("Serif", 0, 30)); // NOI18N
        Produk_D.setForeground(new java.awt.Color(255, 255, 255));
        Produk_D.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Produk_D.setBorder(null);
        Produk_D.setOpaque(false);
        jPanel1.add(Produk_D, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 3350, 340, 50));

        Harga_D.setEditable(false);
        Harga_D.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        Harga_D.setForeground(new java.awt.Color(255, 255, 255));
        Harga_D.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Harga_D.setBorder(null);
        Harga_D.setOpaque(false);
        jPanel1.add(Harga_D, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 3410, 340, 50));

        ProdukD_Panel.setMaximumSize(new java.awt.Dimension(481, 688));
        ProdukD_Panel.setMinimumSize(new java.awt.Dimension(481, 688));
        ProdukD_Panel.setOpaque(false);

        Image_D.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Image_DMouseClicked(evt);
            }
        });

        ProdukD_Panel.setLayer(Image_D, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout ProdukD_PanelLayout = new javax.swing.GroupLayout(ProdukD_Panel);
        ProdukD_Panel.setLayout(ProdukD_PanelLayout);
        ProdukD_PanelLayout.setHorizontalGroup(
            ProdukD_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Image_D, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
        );
        ProdukD_PanelLayout.setVerticalGroup(
            ProdukD_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProdukD_PanelLayout.createSequentialGroup()
                .addComponent(Image_D, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 438, Short.MAX_VALUE))
        );

        jPanel1.add(ProdukD_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1350, 3080, 270, 250));

        Produk_B.setEditable(false);
        Produk_B.setFont(new java.awt.Font("Serif", 0, 30)); // NOI18N
        Produk_B.setForeground(new java.awt.Color(255, 255, 255));
        Produk_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Produk_B.setBorder(null);
        Produk_B.setOpaque(false);
        jPanel1.add(Produk_B, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 3350, 340, 50));

        Harga_B.setEditable(false);
        Harga_B.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        Harga_B.setForeground(new java.awt.Color(255, 255, 255));
        Harga_B.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Harga_B.setBorder(null);
        Harga_B.setOpaque(false);
        jPanel1.add(Harga_B, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 3410, 340, 50));

        ProdukB_Panel.setMaximumSize(new java.awt.Dimension(481, 688));
        ProdukB_Panel.setMinimumSize(new java.awt.Dimension(481, 688));
        ProdukB_Panel.setOpaque(false);

        Image_B.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Image_BMouseClicked(evt);
            }
        });

        ProdukB_Panel.setLayer(Image_B, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout ProdukB_PanelLayout = new javax.swing.GroupLayout(ProdukB_Panel);
        ProdukB_Panel.setLayout(ProdukB_PanelLayout);
        ProdukB_PanelLayout.setHorizontalGroup(
            ProdukB_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Image_B, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
        );
        ProdukB_PanelLayout.setVerticalGroup(
            ProdukB_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProdukB_PanelLayout.createSequentialGroup()
                .addComponent(Image_B, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 438, Short.MAX_VALUE))
        );

        jPanel1.add(ProdukB_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 3080, 270, 250));

        Produk_A.setEditable(false);
        Produk_A.setFont(new java.awt.Font("Serif", 0, 30)); // NOI18N
        Produk_A.setForeground(new java.awt.Color(255, 255, 255));
        Produk_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Produk_A.setBorder(null);
        Produk_A.setOpaque(false);
        jPanel1.add(Produk_A, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 3350, 340, 50));

        Harga_A.setEditable(false);
        Harga_A.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        Harga_A.setForeground(new java.awt.Color(255, 255, 255));
        Harga_A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Harga_A.setBorder(null);
        Harga_A.setOpaque(false);
        jPanel1.add(Harga_A, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 3410, 340, 50));

        ProdukA_Panel.setMaximumSize(new java.awt.Dimension(481, 688));
        ProdukA_Panel.setMinimumSize(new java.awt.Dimension(481, 688));
        ProdukA_Panel.setOpaque(false);

        Image_A.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Image_AMouseClicked(evt);
            }
        });

        ProdukA_Panel.setLayer(Image_A, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout ProdukA_PanelLayout = new javax.swing.GroupLayout(ProdukA_Panel);
        ProdukA_Panel.setLayout(ProdukA_PanelLayout);
        ProdukA_PanelLayout.setHorizontalGroup(
            ProdukA_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Image_A, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
        );
        ProdukA_PanelLayout.setVerticalGroup(
            ProdukA_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProdukA_PanelLayout.createSequentialGroup()
                .addComponent(Image_A, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 438, Short.MAX_VALUE))
        );

        jPanel1.add(ProdukA_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 3080, 270, 250));

        Produk_C.setEditable(false);
        Produk_C.setFont(new java.awt.Font("Serif", 0, 30)); // NOI18N
        Produk_C.setForeground(new java.awt.Color(255, 255, 255));
        Produk_C.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Produk_C.setBorder(null);
        Produk_C.setOpaque(false);
        jPanel1.add(Produk_C, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 3350, 340, 50));

        Harga_C.setEditable(false);
        Harga_C.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        Harga_C.setForeground(new java.awt.Color(255, 255, 255));
        Harga_C.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Harga_C.setBorder(null);
        Harga_C.setOpaque(false);
        jPanel1.add(Harga_C, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 3410, 340, 50));

        ProdukC_Panel.setMaximumSize(new java.awt.Dimension(481, 688));
        ProdukC_Panel.setMinimumSize(new java.awt.Dimension(481, 688));
        ProdukC_Panel.setOpaque(false);

        Image_C.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Image_CMouseClicked(evt);
            }
        });

        ProdukC_Panel.setLayer(Image_C, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout ProdukC_PanelLayout = new javax.swing.GroupLayout(ProdukC_Panel);
        ProdukC_Panel.setLayout(ProdukC_PanelLayout);
        ProdukC_PanelLayout.setHorizontalGroup(
            ProdukC_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Image_C, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
        );
        ProdukC_PanelLayout.setVerticalGroup(
            ProdukC_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProdukC_PanelLayout.createSequentialGroup()
                .addComponent(Image_C, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 438, Short.MAX_VALUE))
        );

        jPanel1.add(ProdukC_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(935, 3080, 270, 250));

        TokoB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Toko B.png"))); // NOI18N
        TokoB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TokoB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TokoBMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TokoBMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TokoBMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TokoBMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TokoBMouseReleased(evt);
            }
        });
        jPanel1.add(TokoB, new org.netbeans.lib.awtextra.AbsoluteConstraints(1068, 2345, -1, -1));

        TokoA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Toko A.png"))); // NOI18N
        TokoA.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TokoA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TokoAMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TokoAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TokoAMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TokoAMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TokoAMouseReleased(evt);
            }
        });
        jPanel1.add(TokoA, new org.netbeans.lib.awtextra.AbsoluteConstraints(642, 2345, -1, -1));

        ProdukD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Produk D.png"))); // NOI18N
        ProdukD.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ProdukD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProdukDMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ProdukDMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ProdukDMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ProdukDMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ProdukDMouseReleased(evt);
            }
        });
        jPanel1.add(ProdukD, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 3010, -1, -1));

        ProdukC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Produk C.png"))); // NOI18N
        ProdukC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ProdukC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProdukCMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ProdukCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ProdukCMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ProdukCMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ProdukCMouseReleased(evt);
            }
        });
        jPanel1.add(ProdukC, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 3010, -1, -1));

        ProdukB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Produk B.png"))); // NOI18N
        ProdukB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ProdukB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProdukBMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ProdukBMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ProdukBMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ProdukBMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ProdukBMouseReleased(evt);
            }
        });
        jPanel1.add(ProdukB, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 3010, -1, -1));

        ProdukA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Produk A.png"))); // NOI18N
        ProdukA.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ProdukA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProdukAMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ProdukAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ProdukAMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ProdukAMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ProdukAMouseReleased(evt);
            }
        });
        jPanel1.add(ProdukA, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 3010, -1, -1));

        Opening_Shop_Panel.setLayout(new java.awt.CardLayout());

        Opening1_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Shop Page/Opening Shop Page.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Opening1_Panel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1980, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Shop Page/Opening Shop Page 2.png"))); // NOI18N
        Opening1_Panel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Shop Page/Opening Shop Page 3.png"))); // NOI18N
        Opening1_Panel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Opening_Shop_Panel.add(Opening1_Panel, "card2");

        Opening2_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Shop Page/Opening Shop Page 2.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        Opening2_Panel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Opening_Shop_Panel.add(Opening2_Panel, "card3");

        Opening3_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Buy_Now_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Ayo Belanja.png"))); // NOI18N
        Buy_Now_Btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Buy_Now_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Buy_Now_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Buy_Now_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Buy_Now_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Buy_Now_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Buy_Now_BtnMouseReleased(evt);
            }
        });
        Opening3_Panel.add(Buy_Now_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 510, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Shop Page/Opening Shop Page 3.png"))); // NOI18N
        jLabel4.setText("jLabel4");
        Opening3_Panel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Opening_Shop_Panel.add(Opening3_Panel, "card4");

        jPanel1.add(Opening_Shop_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 853));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Shop Page/Shop_Page.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Belanja_Panel.setViewportView(jPanel1);

        Main_Panel.add(Belanja_Panel, "card3");

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Next_Btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Next Btn.png"))); // NOI18N
        Next_Btn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Next_Btn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Next_Btn1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Next_Btn1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Next_Btn1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Next_Btn1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Next_Btn1MouseReleased(evt);
            }
        });
        jPanel5.add(Next_Btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1665, 380, -1, -1));

        Prev_Btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Prev Btn.png"))); // NOI18N
        Prev_Btn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Prev_Btn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Prev_Btn1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Prev_Btn1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Prev_Btn1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Prev_Btn1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Prev_Btn1MouseReleased(evt);
            }
        });
        jPanel5.add(Prev_Btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 380, -1, -1));

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

        Bullet_Item3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Bullet.png"))); // NOI18N
        Bullet_Item3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Bullet_Item3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Bullet_Item3MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Bullet_Item3MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Bullet_Item3MouseReleased(evt);
            }
        });
        jPanel5.add(Bullet_Item3, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 760, -1, -1));

        Bullet_Item1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Bullet Effect.png"))); // NOI18N
        Bullet_Item1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Bullet_Item1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Bullet_Item1MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Bullet_Item1MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Bullet_Item1MouseReleased(evt);
            }
        });
        jPanel5.add(Bullet_Item1, new org.netbeans.lib.awtextra.AbsoluteConstraints(912, 760, -1, -1));

        Bullet_Item2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Bullet.png"))); // NOI18N
        Bullet_Item2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Bullet_Item2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Bullet_Item2MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Bullet_Item2MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Bullet_Item2MouseReleased(evt);
            }
        });
        jPanel5.add(Bullet_Item2, new org.netbeans.lib.awtextra.AbsoluteConstraints(947, 760, -1, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Shop Page/Belanja _ Produk Kami.png"))); // NOI18N
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 57, -1, -1));

        ProdukF2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Produk F2.png"))); // NOI18N
        ProdukF2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProdukF2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ProdukF2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ProdukF2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ProdukF2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ProdukF2MouseReleased(evt);
            }
        });
        jPanel5.add(ProdukF2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 1863, -1, -1));

        ProdukE2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Produk E2.png"))); // NOI18N
        ProdukE2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProdukE2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ProdukE2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ProdukE2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ProdukE2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ProdukE2MouseReleased(evt);
            }
        });
        jPanel5.add(ProdukE2, new org.netbeans.lib.awtextra.AbsoluteConstraints(972, 1350, -1, -1));

        ProdukD2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Produk D2.png"))); // NOI18N
        ProdukD2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProdukD2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ProdukD2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ProdukD2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ProdukD2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ProdukD2MouseReleased(evt);
            }
        });
        jPanel5.add(ProdukD2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1378, 2310, -1, -1));

        ProdukC2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Produk C2.png"))); // NOI18N
        ProdukC2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProdukC2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ProdukC2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ProdukC2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ProdukC2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ProdukC2MouseReleased(evt);
            }
        });
        jPanel5.add(ProdukC2, new org.netbeans.lib.awtextra.AbsoluteConstraints(972, 2310, -1, -1));

        ProdukB2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Produk B2.png"))); // NOI18N
        ProdukB2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProdukB2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ProdukB2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ProdukB2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ProdukB2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ProdukB2MouseReleased(evt);
            }
        });
        jPanel5.add(ProdukB2, new org.netbeans.lib.awtextra.AbsoluteConstraints(568, 1350, -1, -1));

        ProdukA2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Produk A2.png"))); // NOI18N
        ProdukA2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProdukA2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ProdukA2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ProdukA2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ProdukA2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ProdukA2MouseReleased(evt);
            }
        });
        jPanel5.add(ProdukA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 1350, -1, -1));

        Opening_Item_Panel.setLayout(new java.awt.CardLayout());

        Opening1_Panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Shop Page/Opening Shop2 Page 1.png"))); // NOI18N
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Opening1_Panel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1980, -1));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Shop Page/Opening Shop2 Page 3.png"))); // NOI18N
        Opening1_Panel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Shop Page/Opening Shop Page 3.png"))); // NOI18N
        Opening1_Panel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Opening_Item_Panel.add(Opening1_Panel1, "card2");

        Opening2_Panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Buy_Now_Btn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Ayo Belanja.png"))); // NOI18N
        Buy_Now_Btn3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Buy_Now_Btn3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Buy_Now_Btn3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Buy_Now_Btn3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Buy_Now_Btn3MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Buy_Now_Btn3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Buy_Now_Btn3MouseReleased(evt);
            }
        });
        Opening2_Panel1.add(Buy_Now_Btn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 653, -1, -1));

        Buy_Now_Btn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Ayo Belanja.png"))); // NOI18N
        Buy_Now_Btn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Buy_Now_Btn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Buy_Now_Btn2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Buy_Now_Btn2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Buy_Now_Btn2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Buy_Now_Btn2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Buy_Now_Btn2MouseReleased(evt);
            }
        });
        Opening2_Panel1.add(Buy_Now_Btn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(517, 653, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Shop Page/Opening Shop2 Page 3.png"))); // NOI18N
        jLabel11.setText("jLabel3");
        Opening2_Panel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Opening_Item_Panel.add(Opening2_Panel1, "card3");

        Opening3_Panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Buy_Now_Btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Ayo Belanja.png"))); // NOI18N
        Buy_Now_Btn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Buy_Now_Btn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Buy_Now_Btn1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Buy_Now_Btn1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Buy_Now_Btn1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Buy_Now_Btn1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Buy_Now_Btn1MouseReleased(evt);
            }
        });
        Opening3_Panel1.add(Buy_Now_Btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 510, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Shop Page/Opening Shop Page 3.png"))); // NOI18N
        jLabel12.setText("jLabel4");
        Opening3_Panel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Opening_Item_Panel.add(Opening3_Panel1, "card4");

        jPanel5.add(Opening_Item_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 853));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Shop Page/Shop Page Pt2.png"))); // NOI18N
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Our_Item_Panel.setViewportView(jPanel5);

        Main_Panel.add(Our_Item_Panel, "card5");

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TokoH2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Toko H2.png"))); // NOI18N
        TokoH2.setToolTipText("");
        TokoH2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TokoH2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TokoH2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TokoH2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TokoH2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TokoH2MouseReleased(evt);
            }
        });
        jPanel6.add(TokoH2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1383, 2144, -1, -1));

        TokoG2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Toko G2.png"))); // NOI18N
        TokoG2.setToolTipText("");
        TokoG2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TokoG2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TokoG2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TokoG2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TokoG2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TokoG2MouseReleased(evt);
            }
        });
        jPanel6.add(TokoG2, new org.netbeans.lib.awtextra.AbsoluteConstraints(974, 2144, -1, -1));

        TokoE2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Toko E2.png"))); // NOI18N
        TokoE2.setToolTipText("");
        TokoE2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TokoE2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TokoE2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TokoE2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TokoE2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TokoE2MouseReleased(evt);
            }
        });
        jPanel6.add(TokoE2, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 2144, -1, -1));

        TokoD2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Toko D2.png"))); // NOI18N
        TokoD2.setToolTipText("");
        TokoD2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TokoD2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TokoD2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TokoD2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TokoD2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TokoD2MouseReleased(evt);
            }
        });
        jPanel6.add(TokoD2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1383, 1560, -1, -1));

        TokoC2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Toko C2.png"))); // NOI18N
        TokoC2.setToolTipText("");
        TokoC2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TokoC2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TokoC2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TokoC2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TokoC2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TokoC2MouseReleased(evt);
            }
        });
        jPanel6.add(TokoC2, new org.netbeans.lib.awtextra.AbsoluteConstraints(974, 1560, -1, -1));

        TokoB2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Toko B2.png"))); // NOI18N
        TokoB2.setToolTipText("");
        TokoB2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TokoB2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TokoB2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TokoB2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TokoB2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TokoB2MouseReleased(evt);
            }
        });
        jPanel6.add(TokoB2, new org.netbeans.lib.awtextra.AbsoluteConstraints(574, 1560, -1, -1));

        TokoA2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Toko A2.png"))); // NOI18N
        TokoA2.setToolTipText("");
        TokoA2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TokoA2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TokoA2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TokoA2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TokoA2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TokoA2MouseReleased(evt);
            }
        });
        jPanel6.add(TokoA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 1560, -1, -1));

        TokoF2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Toko F2.png"))); // NOI18N
        TokoF2.setToolTipText("");
        TokoF2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TokoF2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TokoF2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TokoF2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TokoF2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TokoF2MouseReleased(evt);
            }
        });
        jPanel6.add(TokoF2, new org.netbeans.lib.awtextra.AbsoluteConstraints(574, 2144, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Shop Page/Belanja _ Mitra Kami.png"))); // NOI18N
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 57, -1, -1));

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

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Shop Page/Mitra Kami Page.png"))); // NOI18N
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Our_Channels_Panel.setViewportView(jPanel6);

        Main_Panel.add(Our_Channels_Panel, "card7");

        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDesktopPane.setMaximumSize(new java.awt.Dimension(481, 688));
        jDesktopPane.setMinimumSize(new java.awt.Dimension(481, 688));
        jDesktopPane.setOpaque(false);

        jDesktopPane.setLayer(Produk_Label, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPaneLayout = new javax.swing.GroupLayout(jDesktopPane);
        jDesktopPane.setLayout(jDesktopPaneLayout);
        jDesktopPaneLayout.setHorizontalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Produk_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE)
        );
        jDesktopPaneLayout.setVerticalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Produk_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 1056, Short.MAX_VALUE)
        );

        jPanel10.add(jDesktopPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 390, 852, 1056));

        Banyak_Text.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/TransaksiPage/jumlah beli.png"))); // NOI18N
        jPanel10.add(Banyak_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(1198, 1140, -1, -1));

        NamaProduk_TextField.setEditable(false);
        NamaProduk_TextField.setBackground(new java.awt.Color(253, 253, 252));
        NamaProduk_TextField.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        NamaProduk_TextField.setForeground(new java.awt.Color(255, 255, 255));
        NamaProduk_TextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NamaProduk_TextField.setBorder(null);
        NamaProduk_TextField.setOpaque(false);
        jPanel10.add(NamaProduk_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 470, 620, 100));

        Total_TextField.setEditable(false);
        Total_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Total_TextField.setForeground(new java.awt.Color(255, 255, 255));
        Total_TextField.setBorder(null);
        Total_TextField.setOpaque(false);
        jPanel10.add(Total_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1215, 1213, 400, 30));

        Banyak_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Banyak_TextField.setForeground(new java.awt.Color(255, 255, 255));
        Banyak_TextField.setBorder(null);
        Banyak_TextField.setOpaque(false);
        Banyak_TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Banyak_TextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Banyak_TextFieldFocusLost(evt);
            }
        });
        Banyak_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Banyak_TextFieldKeyPressed(evt);
            }
        });
        jPanel10.add(Banyak_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 1140, 420, 30));

        Harga_TextField.setEditable(false);
        Harga_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Harga_TextField.setForeground(new java.awt.Color(255, 255, 255));
        Harga_TextField.setBorder(null);
        Harga_TextField.setOpaque(false);
        jPanel10.add(Harga_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1215, 1055, 400, 30));

        Tinggi_TextField.setEditable(false);
        Tinggi_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Tinggi_TextField.setForeground(new java.awt.Color(255, 255, 255));
        Tinggi_TextField.setBorder(null);
        Tinggi_TextField.setOpaque(false);
        jPanel10.add(Tinggi_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 975, 320, 30));

        Lebar_TextField.setEditable(false);
        Lebar_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Lebar_TextField.setForeground(new java.awt.Color(255, 255, 255));
        Lebar_TextField.setBorder(null);
        Lebar_TextField.setOpaque(false);
        jPanel10.add(Lebar_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 905, 330, 30));

        Massa_TextField.setEditable(false);
        Massa_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Massa_TextField.setForeground(new java.awt.Color(255, 255, 255));
        Massa_TextField.setBorder(null);
        Massa_TextField.setOpaque(false);
        jPanel10.add(Massa_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 755, 330, 30));

        Panjang_TextField.setEditable(false);
        Panjang_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Panjang_TextField.setForeground(new java.awt.Color(255, 255, 255));
        Panjang_TextField.setBorder(null);
        Panjang_TextField.setOpaque(false);
        jPanel10.add(Panjang_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 835, 310, 30));

        Toko_TextField.setEditable(false);
        Toko_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Toko_TextField.setForeground(new java.awt.Color(255, 255, 255));
        Toko_TextField.setBorder(null);
        Toko_TextField.setOpaque(false);
        jPanel10.add(Toko_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 685, 420, 30));

        Beli_Tombol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/TransaksiPage/Ayo Belanja 1.png"))); // NOI18N
        Beli_Tombol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Beli_TombolMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Beli_TombolMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Beli_TombolMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Beli_TombolMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Beli_TombolMouseReleased(evt);
            }
        });
        jPanel10.add(Beli_Tombol, new org.netbeans.lib.awtextra.AbsoluteConstraints(1400, 1320, -1, -1));

        Chart_Btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/clarity_shopping-cart-line.png"))); // NOI18N
        Chart_Btn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Chart_Btn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Chart_Btn1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Chart_Btn1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Chart_Btn1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Chart_Btn1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Chart_Btn1MouseReleased(evt);
            }
        });
        jPanel10.add(Chart_Btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 1338, -1, -1));

        Wishlist_Btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/ic_baseline-favorite-border.png"))); // NOI18N
        Wishlist_Btn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Wishlist_Btn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Wishlist_Btn1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Wishlist_Btn1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Wishlist_Btn1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Wishlist_Btn1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Wishlist_Btn1MouseReleased(evt);
            }
        });
        jPanel10.add(Wishlist_Btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 1335, -1, -1));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/TransaksiPage/Pilih Produk Latar.png"))); // NOI18N
        jPanel10.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, -1, -1));
        jPanel10.add(Produk_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 470, -1, -1));

        HasilPencarianProduk_Panel.setViewportView(jPanel10);

        Main_Panel.add(HasilPencarianProduk_Panel, "card11");

        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Next_Tombol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/TransaksiPage/Berkutnya Beli Btn.png"))); // NOI18N
        Next_Tombol.setToolTipText("");
        Next_Tombol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Next_TombolMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Next_TombolMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Next_TombolMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Next_TombolMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Next_TombolMouseReleased(evt);
            }
        });
        jPanel11.add(Next_Tombol, new org.netbeans.lib.awtextra.AbsoluteConstraints(1420, 920, -1, -1));

        TotalHargaTrans_TextField.setEditable(false);
        TotalHargaTrans_TextField.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        TotalHargaTrans_TextField.setForeground(new java.awt.Color(255, 255, 255));
        TotalHargaTrans_TextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        TotalHargaTrans_TextField.setBorder(null);
        TotalHargaTrans_TextField.setOpaque(false);
        jPanel11.add(TotalHargaTrans_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1620, 827, 120, 40));

        BanyakBeliTrans_TextField.setEditable(false);
        BanyakBeliTrans_TextField.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        BanyakBeliTrans_TextField.setForeground(new java.awt.Color(255, 255, 255));
        BanyakBeliTrans_TextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        BanyakBeliTrans_TextField.setBorder(null);
        BanyakBeliTrans_TextField.setOpaque(false);
        jPanel11.add(BanyakBeliTrans_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1600, 760, 140, 40));

        NamaProdukTrans_TextField.setEditable(false);
        NamaProdukTrans_TextField.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        NamaProdukTrans_TextField.setForeground(new java.awt.Color(255, 255, 255));
        NamaProdukTrans_TextField.setBorder(null);
        NamaProdukTrans_TextField.setOpaque(false);
        jPanel11.add(NamaProdukTrans_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1316, 690, 240, 40));

        HargaProdukTrans_TextField.setEditable(false);
        HargaProdukTrans_TextField.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        HargaProdukTrans_TextField.setForeground(new java.awt.Color(255, 255, 255));
        HargaProdukTrans_TextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        HargaProdukTrans_TextField.setBorder(null);
        HargaProdukTrans_TextField.setOpaque(false);
        jPanel11.add(HargaProdukTrans_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1620, 680, 120, 40));

        JasBar_CB.setBorder(null);
        JasBar_CB.setOpaque(false);
        jPanel11.add(JasBar_CB, new org.netbeans.lib.awtextra.AbsoluteConstraints(1312, 372, 430, 70));

        JasKir_CB.setBorder(null);
        JasKir_CB.setOpaque(false);
        jPanel11.add(JasKir_CB, new org.netbeans.lib.awtextra.AbsoluteConstraints(1311, 507, 430, 70));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/TransaksiPage/Transaksi Pembayaran 1.png"))); // NOI18N
        jPanel11.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Transaksi_TGL.setEditable(false);
        Transaksi_TGL.setBorder(null);
        Transaksi_TGL.setOpaque(false);
        jPanel11.add(Transaksi_TGL, new org.netbeans.lib.awtextra.AbsoluteConstraints(1375, 290, -1, -1));

        Transaksi_ID.setEditable(false);
        Transaksi_ID.setBorder(null);
        Transaksi_ID.setOpaque(false);
        jPanel11.add(Transaksi_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(1350, 290, -1, -1));

        Seller_ID.setEditable(false);
        Seller_ID.setBorder(null);
        Seller_ID.setOpaque(false);
        jPanel11.add(Seller_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 290, -1, -1));

        Transaksi_Panel.setViewportView(jPanel11);

        Main_Panel.add(Transaksi_Panel, "card12");

        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Next2_Tombol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/TransaksiPage/Berkutnya Cetak Btn.png"))); // NOI18N
        Next2_Tombol.setToolTipText("");
        Next2_Tombol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Next2_TombolMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Next2_TombolMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Next2_TombolMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Next2_TombolMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Next2_TombolMouseReleased(evt);
            }
        });
        jPanel12.add(Next2_Tombol, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 690, -1, -1));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/TransaksiPage/Transaksi Pembayaran 2.png"))); // NOI18N
        jPanel12.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Transaksi2_Panel.setViewportView(jPanel12);

        Main_Panel.add(Transaksi2_Panel, "card13");

        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Cetak_Tombol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/TransaksiPage/Cetak Btn.png"))); // NOI18N
        Cetak_Tombol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Cetak_TombolMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Cetak_TombolMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Cetak_TombolMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Cetak_TombolMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Cetak_TombolMouseReleased(evt);
            }
        });
        jPanel13.add(Cetak_Tombol, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 680, -1, -1));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/TransaksiPage/Transaksi Pembayaran 3.png"))); // NOI18N
        jLabel26.setText("jLabel26");
        jPanel13.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -10, -1, -1));

        Transaksi3_Panel.setViewportView(jPanel13);

        Main_Panel.add(Transaksi3_Panel, "card14");

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Instagram_Klik2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Instagram Btn.png"))); // NOI18N
        Instagram_Klik2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Instagram_Klik2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Instagram_Klik2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Instagram_Klik2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Instagram_Klik2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Instagram_Klik2MouseReleased(evt);
            }
        });
        jPanel3.add(Instagram_Klik2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));

        Twitter_Klik2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Twitter Btn.png"))); // NOI18N
        Twitter_Klik2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Twitter_Klik2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Twitter_Klik2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Twitter_Klik2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Twitter_Klik2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Twitter_Klik2MouseReleased(evt);
            }
        });
        jPanel3.add(Twitter_Klik2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, -1, -1));

        Facebook_Klik2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Facebook Btn.png"))); // NOI18N
        Facebook_Klik2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Facebook_Klik2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Facebook_Klik2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Facebook_Klik2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Facebook_Klik2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Facebook_Klik2MouseReleased(evt);
            }
        });
        jPanel3.add(Facebook_Klik2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 540, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Contact Page/Contact Page.png"))); // NOI18N
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Kontak_Panel.setViewportView(jPanel3);

        Main_Panel.add(Kontak_Panel, "card4");

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/About Us Page/About Us Page.png"))); // NOI18N
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        About_Us_Panel.setViewportView(jPanel4);

        Main_Panel.add(About_Us_Panel, "card5");

        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Empty Page/Data Empty1.png"))); // NOI18N
        jPanel8.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 180, -1, -1));

        Social_Media4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Facebook Btn.png"))); // NOI18N
        Social_Media4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Social_Media4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Social_Media4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Social_Media4MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Social_Media4MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Social_Media4MouseReleased(evt);
            }
        });
        jPanel8.add(Social_Media4, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 550, -1, -1));

        Social_Media5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Twitter Btn.png"))); // NOI18N
        Social_Media5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Social_Media5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Social_Media5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Social_Media5MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Social_Media5MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Social_Media5MouseReleased(evt);
            }
        });
        jPanel8.add(Social_Media5, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 400, -1, -1));

        Social_Media6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Group.png"))); // NOI18N
        Social_Media6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Social_Media6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Social_Media6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Social_Media6MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Social_Media6MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Social_Media6MouseReleased(evt);
            }
        });
        jPanel8.add(Social_Media6, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 260, -1, -1));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Admin Page/Hiasan admin.png"))); // NOI18N
        jPanel8.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 190, -1, -1));

        Ayo_Belanja2_Btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Ayo Belanja 2.png"))); // NOI18N
        Ayo_Belanja2_Btn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Ayo_Belanja2_Btn1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Ayo_Belanja2_Btn1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Ayo_Belanja2_Btn1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Ayo_Belanja2_Btn1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Ayo_Belanja2_Btn1MouseReleased(evt);
            }
        });
        jPanel8.add(Ayo_Belanja2_Btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(584, 540, -1, -1));

        jScrollPane5.setBackground(new java.awt.Color(130, 80, 74));
        jScrollPane5.setPreferredSize(new java.awt.Dimension(831, 795));

        tb_wishlist.setAutoCreateRowSorter(true);
        tb_wishlist.setBackground(new java.awt.Color(130, 80, 74));
        tb_wishlist.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        tb_wishlist.setForeground(new java.awt.Color(253, 253, 252));
        tb_wishlist.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_wishlist.setGridColor(new java.awt.Color(130, 80, 74));
        tb_wishlist.setOpaque(false);
        tb_wishlist.setRowHeight(50);
        tb_wishlist.setSelectionBackground(new java.awt.Color(220, 139, 27));
        tb_wishlist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_wishlistMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tb_wishlist);

        jPanel8.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 290, -1, 300));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Belanja Yang Disukai.png"))); // NOI18N
        jLabel19.setToolTipText("");
        jPanel8.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 72, -1, -1));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Empty Page/Polos Page.png"))); // NOI18N
        jPanel8.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, -1, -1));

        Wishlist_Panel.setViewportView(jPanel8);

        Main_Panel.add(Wishlist_Panel, "card9");

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Empty Page/Data Empty.png"))); // NOI18N
        jPanel7.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 180, -1, -1));

        Ayo_Belanja2_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Shop Page/Ayo Belanja 2.png"))); // NOI18N
        Ayo_Belanja2_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Ayo_Belanja2_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Ayo_Belanja2_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Ayo_Belanja2_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Ayo_Belanja2_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Ayo_Belanja2_BtnMouseReleased(evt);
            }
        });
        jPanel7.add(Ayo_Belanja2_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(584, 540, -1, -1));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Admin Page/Hiasan admin.png"))); // NOI18N
        jPanel7.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 190, -1, -1));

        jScrollPane4.setBackground(new java.awt.Color(130, 80, 74));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(831, 795));

        tb_cart.setAutoCreateRowSorter(true);
        tb_cart.setBackground(new java.awt.Color(130, 80, 74));
        tb_cart.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        tb_cart.setForeground(new java.awt.Color(253, 253, 252));
        tb_cart.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_cart.setGridColor(new java.awt.Color(130, 80, 74));
        tb_cart.setOpaque(false);
        tb_cart.setRowHeight(50);
        tb_cart.setSelectionBackground(new java.awt.Color(220, 139, 27));
        tb_cart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_cartMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tb_cart);

        jPanel7.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 290, -1, 300));

        Social_Media3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Facebook Btn.png"))); // NOI18N
        Social_Media3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Social_Media3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Social_Media3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Social_Media3MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Social_Media3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Social_Media3MouseReleased(evt);
            }
        });
        jPanel7.add(Social_Media3, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 550, -1, -1));

        Social_Media2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Twitter Btn.png"))); // NOI18N
        Social_Media2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Social_Media2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Social_Media2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Social_Media2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Social_Media2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Social_Media2MouseReleased(evt);
            }
        });
        jPanel7.add(Social_Media2, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 400, -1, -1));

        Social_Media1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Group.png"))); // NOI18N
        Social_Media1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Social_Media1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Social_Media1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Social_Media1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Social_Media1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Social_Media1MouseReleased(evt);
            }
        });
        jPanel7.add(Social_Media1, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 260, -1, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Keranjang.png"))); // NOI18N
        jLabel17.setToolTipText("");
        jPanel7.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 72, -1, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Empty Page/Polos Page.png"))); // NOI18N
        jPanel7.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, -1, -1));

        Keranjang_Panel.setViewportView(jPanel7);

        Main_Panel.add(Keranjang_Panel, "card8");

        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDesktopPane1.setMaximumSize(new java.awt.Dimension(481, 688));
        jDesktopPane1.setMinimumSize(new java.awt.Dimension(481, 688));
        jDesktopPane1.setOpaque(false);

        jDesktopPane1.setLayer(Image_Label, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Image_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Image_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel9.add(jDesktopPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1065, 842, 481, 688));

        NamaPenggunaHome_Text1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Nama Lengkap.png"))); // NOI18N
        jPanel9.add(NamaPenggunaHome_Text1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 1045, -1, -1));

        NamaPenggunaHome_TextField1.setEditable(false);
        NamaPenggunaHome_TextField1.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NamaPenggunaHome_TextField1.setForeground(new java.awt.Color(255, 255, 255));
        NamaPenggunaHome_TextField1.setBorder(null);
        NamaPenggunaHome_TextField1.setOpaque(false);
        NamaPenggunaHome_TextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                NamaPenggunaHome_TextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                NamaPenggunaHome_TextField1FocusLost(evt);
            }
        });
        NamaPenggunaHome_TextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NamaPenggunaHome_TextField1KeyPressed(evt);
            }
        });
        jPanel9.add(NamaPenggunaHome_TextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 1040, 390, 30));

        EmailHome_Text1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Email.png"))); // NOI18N
        jPanel9.add(EmailHome_Text1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 1130, -1, -1));

        EmailHome_TextField1.setEditable(false);
        EmailHome_TextField1.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        EmailHome_TextField1.setForeground(new java.awt.Color(255, 255, 255));
        EmailHome_TextField1.setBorder(null);
        EmailHome_TextField1.setOpaque(false);
        EmailHome_TextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                EmailHome_TextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                EmailHome_TextField1FocusLost(evt);
            }
        });
        EmailHome_TextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EmailHome_TextField1KeyPressed(evt);
            }
        });
        jPanel9.add(EmailHome_TextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 1120, 390, 30));

        TelpHome_Text1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Telepon.png"))); // NOI18N
        jPanel9.add(TelpHome_Text1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 1215, -1, -1));

        TelpHome_TextField1.setEditable(false);
        TelpHome_TextField1.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        TelpHome_TextField1.setForeground(new java.awt.Color(255, 255, 255));
        TelpHome_TextField1.setBorder(null);
        TelpHome_TextField1.setOpaque(false);
        TelpHome_TextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TelpHome_TextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TelpHome_TextField1FocusLost(evt);
            }
        });
        TelpHome_TextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TelpHome_TextField1KeyPressed(evt);
            }
        });
        jPanel9.add(TelpHome_TextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 1210, 390, 30));

        AlamatHome_Text1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Alamat.png"))); // NOI18N
        jPanel9.add(AlamatHome_Text1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 1295, -1, -1));

        AlamatHome_TextField1.setEditable(false);
        AlamatHome_TextField1.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        AlamatHome_TextField1.setForeground(new java.awt.Color(255, 255, 255));
        AlamatHome_TextField1.setBorder(null);
        AlamatHome_TextField1.setOpaque(false);
        AlamatHome_TextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                AlamatHome_TextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                AlamatHome_TextField1FocusLost(evt);
            }
        });
        AlamatHome_TextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlamatHome_TextField1KeyPressed(evt);
            }
        });
        jPanel9.add(AlamatHome_TextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 1290, 390, 30));

        NamaPenggunaProfile_TextField.setEditable(false);
        NamaPenggunaProfile_TextField.setFont(new java.awt.Font("Times New Roman", 0, 30)); // NOI18N
        NamaPenggunaProfile_TextField.setForeground(new java.awt.Color(255, 255, 255));
        NamaPenggunaProfile_TextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NamaPenggunaProfile_TextField.setBorder(null);
        NamaPenggunaProfile_TextField.setOpaque(false);
        jPanel9.add(NamaPenggunaProfile_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 610, 500, 30));

        NamaPenggunaHome_Text.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Nama Lengkap.png"))); // NOI18N
        jPanel9.add(NamaPenggunaHome_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 345, -1, -1));

        NamaPenggunaHome_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NamaPenggunaHome_TextField.setForeground(new java.awt.Color(255, 255, 255));
        NamaPenggunaHome_TextField.setBorder(null);
        NamaPenggunaHome_TextField.setOpaque(false);
        NamaPenggunaHome_TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                NamaPenggunaHome_TextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                NamaPenggunaHome_TextFieldFocusLost(evt);
            }
        });
        NamaPenggunaHome_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NamaPenggunaHome_TextFieldKeyPressed(evt);
            }
        });
        jPanel9.add(NamaPenggunaHome_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 340, 390, 30));

        EmailHome_Text.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Email.png"))); // NOI18N
        jPanel9.add(EmailHome_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 425, -1, -1));

        EmailHome_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        EmailHome_TextField.setForeground(new java.awt.Color(255, 255, 255));
        EmailHome_TextField.setBorder(null);
        EmailHome_TextField.setOpaque(false);
        EmailHome_TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                EmailHome_TextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                EmailHome_TextFieldFocusLost(evt);
            }
        });
        EmailHome_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EmailHome_TextFieldKeyPressed(evt);
            }
        });
        jPanel9.add(EmailHome_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 417, 390, 30));

        TelpHome_Text.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Telepon.png"))); // NOI18N
        jPanel9.add(TelpHome_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 505, -1, -1));

        TelpHome_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        TelpHome_TextField.setForeground(new java.awt.Color(255, 255, 255));
        TelpHome_TextField.setBorder(null);
        TelpHome_TextField.setOpaque(false);
        TelpHome_TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TelpHome_TextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TelpHome_TextFieldFocusLost(evt);
            }
        });
        TelpHome_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TelpHome_TextFieldKeyPressed(evt);
            }
        });
        jPanel9.add(TelpHome_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 500, 390, 30));

        AlamatHome_Text.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Alamat.png"))); // NOI18N
        jPanel9.add(AlamatHome_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 595, -1, -1));

        AlamatHome_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        AlamatHome_TextField.setForeground(new java.awt.Color(255, 255, 255));
        AlamatHome_TextField.setBorder(null);
        AlamatHome_TextField.setOpaque(false);
        AlamatHome_TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                AlamatHome_TextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                AlamatHome_TextFieldFocusLost(evt);
            }
        });
        AlamatHome_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlamatHome_TextFieldKeyPressed(evt);
            }
        });
        jPanel9.add(AlamatHome_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 590, 390, 30));

        Simpan_Tombol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Simpan Data.png"))); // NOI18N
        Simpan_Tombol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Simpan_TombolMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Simpan_TombolMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Simpan_TombolMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Simpan_TombolMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Simpan_TombolMouseReleased(evt);
            }
        });
        jPanel9.add(Simpan_Tombol, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 680, -1, -1));

        Instagram_Klik.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Instagram Btn.png"))); // NOI18N
        Instagram_Klik.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Instagram_KlikMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Instagram_KlikMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Instagram_KlikMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Instagram_KlikMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Instagram_KlikMouseReleased(evt);
            }
        });
        jPanel9.add(Instagram_Klik, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));

        Twitter_Klik.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Twitter Btn.png"))); // NOI18N
        Twitter_Klik.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Twitter_KlikMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Twitter_KlikMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Twitter_KlikMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Twitter_KlikMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Twitter_KlikMouseReleased(evt);
            }
        });
        jPanel9.add(Twitter_Klik, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 405, -1, -1));

        Facebook_Klik.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Facebook Btn.png"))); // NOI18N
        Facebook_Klik.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Facebook_KlikMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Facebook_KlikMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Facebook_KlikMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Facebook_KlikMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Facebook_KlikMouseReleased(evt);
            }
        });
        jPanel9.add(Facebook_Klik, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 540, -1, -1));

        GantiFoto_Tombol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Ganti Foto Btn.png"))); // NOI18N
        GantiFoto_Tombol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GantiFoto_TombolMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                GantiFoto_TombolMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                GantiFoto_TombolMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                GantiFoto_TombolMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                GantiFoto_TombolMouseReleased(evt);
            }
        });
        jPanel9.add(GantiFoto_Tombol, new org.netbeans.lib.awtextra.AbsoluteConstraints(584, 494, -1, -1));

        LogOut_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Keluar Btn.png"))); // NOI18N
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
        jPanel9.add(LogOut_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 1580, -1, -1));

        User_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/User Page/User Page.png"))); // NOI18N
        jPanel9.add(User_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Pengguna_ID.setBorder(null);
        Pengguna_ID.setEnabled(false);
        Pengguna_ID.setOpaque(false);
        jPanel9.add(Pengguna_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 140, 80, -1));

        Filename_Text.setEditable(false);
        Filename_Text.setToolTipText("");
        Filename_Text.setEnabled(false);
        Filename_Text.setOpaque(false);
        jPanel9.add(Filename_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 560, 160, -1));

        UserPagePanel.setViewportView(jPanel9);

        Main_Panel.add(UserPagePanel, "card6");

        getContentPane().add(Main_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 1920, 890));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void findProduct(){
        ArrayList<ProsesSearching> productList = ListUsers(Cari_Item_TextField.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Pencarian Berdasarkan Nama Produk"});
        Object[] row = new Object[1];
        
        for(int i = 0; i < productList.size(); i++){
            row[0] = productList.get(i).getNamaproduk();
            model.addRow(row);
        }
        Pencarian_Tabel.setModel(model);
    }
    
    public ArrayList<ProsesSearching> ListUsers(String ValToSearch){
        ArrayList<ProsesSearching> productList = new ArrayList<ProsesSearching>();
        
        
        
        java.sql.Statement st;
        ResultSet rs;
        
        try {
            Class.forName("com.mysql.jdbc.Driver"); /*Pemanggilan kelas pad library JDBC driver mysql*/
            java.sql.Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_bilik_pernik","root", ""); /*variabel yang digunakan untuk pemanggilan driver mysql pada database server*/
            st = connect.createStatement();
            String sql = "call user_cari_item('"+ValToSearch+"')"; /*Syntax pada mysql yang digunakan untuk melakukan pemasukkan data username, email, dan password*/
            rs = st.executeQuery(sql);
            
            ProsesSearching search;
            
            while(rs.next()){
                
                search = new ProsesSearching(
                            rs.getString("nama_produk")
                            );
                productList.add(search);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return productList;
    }
    private void Belanja_Btn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Belanja_Btn1MouseClicked
        // TODO add your handling code here:
            ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan 
            untuk memanggil ImageIcon*/
            Btn_Belanja = new ImageIcon("src/Picture/Effect/Ayo Belanja effect 2.png");/*Format pemanggilan file ImageIcon 
            yang ingin dipanggil*/
            Belanja_Btn1.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel) 
            yang digunakan untuk melakukan set pada ImageIcon*/
            
            if(Belanja_Panel.isShowing()){
            ImageIcon Btn_Belanja1;/*Deklarasi variabel yang digunakan 
            untuk memanggil ImageIcon*/
            Btn_Belanja1 = new ImageIcon("src/Picture/Effect/Ayo Belanja.png");/*Format pemanggilan file ImageIcon 
            yang ingin dipanggil*/
            Belanja_Btn1.setIcon(Btn_Belanja1);/*Pallete pada java (berupa JLabel) 
            yang digunakan untuk melakukan set pada ImageIcon*/
            }
            
            //Menghapus Panel   
        Main_Panel.removeAll();
        Main_Panel.repaint();
        Main_Panel.revalidate();
        
        //Menambah panel
        Main_Panel.add(Belanja_Panel);
        Main_Panel.repaint();
        Main_Panel.revalidate();
         Home_Btn_eft2.setVisible(false);
            ImageIcon Btn_Belanja1;/*Deklarasi variabel yang digunakan 
            untuk memanggil ImageIcon*/
            Btn_Belanja1 = new ImageIcon("src/Picture/Effect/Belanja effect 2.png");/*Format pemanggilan file ImageIcon 
            yang ingin dipanggil*/
            Belanja_Btn.setIcon(Btn_Belanja1);/*Pallete pada java (berupa JLabel) 
            yang digunakan untuk melakukan set pada ImageIcon*/
            ImageIcon Btn_Home;/*Deklarasi variabel yang digunakan 
            untuk memanggil ImageIcon*/
            Btn_Home = new ImageIcon("src/Picture/Effect/Rumah.png");/*Format pemanggilan file ImageIcon 
            yang ingin dipanggil*/
            Home_Btn.setIcon(Btn_Home);/*Pallete pada java (berupa JLabel) 
            yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Belanja_Btn1MouseClicked

    private void Belanja_Btn1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Belanja_Btn1MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan 
            untuk memanggil ImageIcon*/
            Btn_Belanja = new ImageIcon("src/Picture/Effect/Ayo Belanja effect 1.png");/*Format pemanggilan file ImageIcon 
            yang ingin dipanggil*/
            Belanja_Btn1.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel) 
            yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Belanja_Btn1MouseEntered

    private void Belanja_Btn1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Belanja_Btn1MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan 
            untuk memanggil ImageIcon*/
            Btn_Belanja = new ImageIcon("src/Picture/Effect/Ayo Belanja.png");/*Format pemanggilan file ImageIcon 
            yang ingin dipanggil*/
            Belanja_Btn1.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel) 
            yang digunakan untuk melakukan set pada ImageIcon*/
            
            if(Belanja_Panel.isShowing()){
            ImageIcon Btn_Belanja1;/*Deklarasi variabel yang digunakan 
            untuk memanggil ImageIcon*/
            Btn_Belanja1 = new ImageIcon("src/Picture/Effect/Ayo Belanja.png");/*Format pemanggilan file ImageIcon 
            yang ingin dipanggil*/
            Belanja_Btn1.setIcon(Btn_Belanja1);/*Pallete pada java (berupa JLabel) 
            yang digunakan untuk melakukan set pada ImageIcon*/
            }
    }//GEN-LAST:event_Belanja_Btn1MouseExited

    private void Belanja_Btn1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Belanja_Btn1MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan 
            untuk memanggil ImageIcon*/
            Btn_Belanja = new ImageIcon("src/Picture/Effect/Ayo Belanja effect 3.png");/*Format pemanggilan file ImageIcon 
            yang ingin dipanggil*/
            Belanja_Btn1.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel) 
            yang digunakan untuk melakukan set pada ImageIcon*/
            
        if(Belanja_Panel.isShowing()){
            ImageIcon Btn_Belanja1;/*Deklarasi variabel yang digunakan 
            untuk memanggil ImageIcon*/
            Btn_Belanja1 = new ImageIcon("src/Picture/Effect/Ayo Belanja.png");/*Format pemanggilan file ImageIcon 
            yang ingin dipanggil*/
            Belanja_Btn1.setIcon(Btn_Belanja1);/*Pallete pada java (berupa JLabel) 
            yang digunakan untuk melakukan set pada ImageIcon*/
            }
    }//GEN-LAST:event_Belanja_Btn1MousePressed

    private void Belanja_Btn1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Belanja_Btn1MouseReleased
        // TODO add your handling code here:
            ImageIcon Btn_KirimPesan;/*Deklarasi variabel yang digunakan 
            untuk memanggil ImageIcon*/
            Btn_KirimPesan = new ImageIcon("src/Picture/Effect/Ayo Belanja.png");/*Format pemanggilan file ImageIcon 
            yang ingin dipanggil*/
            Belanja_Btn1.setIcon(Btn_KirimPesan);/*Pallete pada java (berupa JLabel) 
            yang digunakan untuk melakukan set pada ImageIcon*/
            
            if(Belanja_Panel.isShowing()){
            ImageIcon Btn_Belanja1;/*Deklarasi variabel yang digunakan 
            untuk memanggil ImageIcon*/
            Btn_Belanja1 = new ImageIcon("src/Picture/Effect/Ayo Belanja.png");/*Format pemanggilan file ImageIcon 
            yang ingin dipanggil*/
            Belanja_Btn1.setIcon(Btn_Belanja1);/*Pallete pada java (berupa JLabel) 
            yang digunakan untuk melakukan set pada ImageIcon*/
            }
    }//GEN-LAST:event_Belanja_Btn1MouseReleased

    private void Kirim_Pesan_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Kirim_Pesan_BtnMouseClicked
        // TODO add your handling code here:
         ImageIcon Btn_KirimPesan;/*Deklarasi variabel yang digunakan 
            untuk memanggil ImageIcon*/
            Btn_KirimPesan = new ImageIcon("src/Picture/Effect/Kirim Pesan effect 2.png");/*Format pemanggilan file ImageIcon 
            yang ingin dipanggil*/
            Kirim_Pesan_Btn.setIcon(Btn_KirimPesan);/*Pallete pada java (berupa JLabel) 
            yang digunakan untuk melakukan set pada ImageIcon*/
        
        final String username = Email_TextField.getText();
        final String pass = KataSandi_TextField.getText();
       
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.transport.protocol", "smtp");
        
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, pass);
            }
        });
        
        
        MimeBodyPart mbp = new MimeBodyPart();
        Multipart mlprt = new MimeMultipart();
        try {
            Message pesan = new MimeMessage(session);
            pesan.setFrom(new InternetAddress(KataSandi_TextField.getText()));
            pesan.setRecipients(Message.RecipientType.TO, InternetAddress.parse("bilikpernikcs@gmail.com"));
            pesan.setSubject("Dari Pengguna Bilik Pernik");
            mbp.setText(Message_TextField.getText());
            mlprt.addBodyPart(mbp);
            pesan.setContent(mlprt);
            Transport.send(pesan);
            JOptionPane.showMessageDialog(null, "Terima Kasih Telah Menghubungi Kami");
            Email_TextField.setText(""); /*JTextField untuk menyimpan data email yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang
            telah dimasukkan dibuat menghilang*/
            KataSandi_TextField.setText(""); /*JTextField untuk menyimpan data kata sandi yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
            dibuat menghilang*/
            Message_TextField.setText(""); /*JTextField untuk menyimpan data message yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
            dibuat menghilang*/
            Email_Label.setVisible(true);
            KataSandi_Label.setVisible(true);
            Masukkan_Pesan_Label.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Alamat Email '"+username+"' Tidak Tersedia" + "\n\t\t\tMohon Ulangi Masukan Anda", "TERJADI KESALAHAN", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_Kirim_Pesan_BtnMouseClicked
    
    private void Kirim_Pesan_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Kirim_Pesan_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_KirimPesan;/*Deklarasi variabel yang digunakan 
            untuk memanggil ImageIcon*/
            Btn_KirimPesan = new ImageIcon("src/Picture/Effect/Kirim Pesan effect 1.png");/*Format pemanggilan file ImageIcon 
            yang ingin dipanggil*/
            Kirim_Pesan_Btn.setIcon(Btn_KirimPesan);/*Pallete pada java (berupa JLabel) 
            yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Kirim_Pesan_BtnMouseEntered

    private void Kirim_Pesan_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Kirim_Pesan_BtnMouseExited
        // TODO add your handling code here:
            ImageIcon Btn_KirimPesan;/*Deklarasi variabel yang digunakan 
            untuk memanggil ImageIcon*/
            Btn_KirimPesan = new ImageIcon("src/Picture/Effect/Kirim Pesan.png");/*Format pemanggilan file ImageIcon 
            yang ingin dipanggil*/
            Kirim_Pesan_Btn.setIcon(Btn_KirimPesan);/*Pallete pada java (berupa JLabel) 
            yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Kirim_Pesan_BtnMouseExited

    private void Kirim_Pesan_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Kirim_Pesan_BtnMousePressed
        // TODO add your handling code here:
         ImageIcon Btn_KirimPesan;/*Deklarasi variabel yang digunakan 
            untuk memanggil ImageIcon*/
            Btn_KirimPesan = new ImageIcon("src/Picture/Effect/Kirim Pesan effect 3.png");/*Format pemanggilan file ImageIcon 
            yang ingin dipanggil*/
            Kirim_Pesan_Btn.setIcon(Btn_KirimPesan);/*Pallete pada java (berupa JLabel) 
            yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Kirim_Pesan_BtnMousePressed

    private void Kirim_Pesan_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Kirim_Pesan_BtnMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_Kirim_Pesan_BtnMouseReleased

    private void Selengkapnya_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Selengkapnya_BtnMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Selengkapnya;/*Deklarasi variabel yang digunakan 
            untuk memanggil ImageIcon*/
            Btn_Selengkapnya = new ImageIcon("src/Picture/Effect/Selengkapnya_Btn effect 2.png");/*Format pemanggilan file ImageIcon 
            yang ingin dipanggil*/
            Selengkapnya_Btn.setIcon(Btn_Selengkapnya);/*Pallete pada java (berupa JLabel) 
            yang digunakan untuk melakukan set pada ImageIcon*/
            
        //Menghapus Panel   
        Main_Panel.removeAll();
        Main_Panel.repaint();
        Main_Panel.revalidate();
        
        //Menambah panel
        Main_Panel.add(About_Us_Panel);
        Main_Panel.repaint();
        Main_Panel.revalidate();
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
            
            if(About_Us_Panel.isShowing()){
                ImageIcon Btn_Tentang1;/*Deklarasi variabel yang digunakan 
                untuk memanggil ImageIcon*/
                Btn_Tentang1 = new ImageIcon("src/Picture/Effect/Tentang effect 2.png");/*Format pemanggilan file ImageIcon 
                yang ingin dipanggil*/
                Tentang_Btn.setIcon(Btn_Tentang1);/*Pallete pada java (berupa JLabel) 
                yang digunakan untuk melakukan set pada ImageIcon*/
            }
        
    }//GEN-LAST:event_Selengkapnya_BtnMouseClicked

    private void Selengkapnya_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Selengkapnya_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Selengkapnya;/*Deklarasi variabel yang digunakan 
            untuk memanggil ImageIcon*/
            Btn_Selengkapnya = new ImageIcon("src/Picture/Effect/Selengkapnya_Btn effect 1.png");/*Format pemanggilan file ImageIcon 
            yang ingin dipanggil*/
            Selengkapnya_Btn.setIcon(Btn_Selengkapnya);/*Pallete pada java (berupa JLabel) 
            yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Selengkapnya_BtnMouseEntered

    private void Selengkapnya_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Selengkapnya_BtnMouseExited
        // TODO add your handling code here:
            ImageIcon Btn_Selengkapnya;/*Deklarasi variabel yang digunakan 
            untuk memanggil ImageIcon*/
            Btn_Selengkapnya = new ImageIcon("src/Picture/Effect/Selengkapnya_Btn.png");/*Format pemanggilan file ImageIcon 
            yang ingin dipanggil*/
            Selengkapnya_Btn.setIcon(Btn_Selengkapnya);/*Pallete pada java (berupa JLabel) 
            yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Selengkapnya_BtnMouseExited

    private void Selengkapnya_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Selengkapnya_BtnMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Selengkapnya;/*Deklarasi variabel yang digunakan 
            untuk memanggil ImageIcon*/
            Btn_Selengkapnya = new ImageIcon("src/Picture/Effect/Selengkapnya_Btn effect 3.png");/*Format pemanggilan file ImageIcon 
            yang ingin dipanggil*/
            Selengkapnya_Btn.setIcon(Btn_Selengkapnya);/*Pallete pada java (berupa JLabel) 
            yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Selengkapnya_BtnMousePressed

    private void Selengkapnya_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Selengkapnya_BtnMouseReleased
        // TODO add your handling code here:
            ImageIcon Btn_Selengkapnya;/*Deklarasi variabel yang digunakan 
            untuk memanggil ImageIcon*/
            Btn_Selengkapnya = new ImageIcon("src/Picture/Effect/Selengkapnya_Btn.png");/*Format pemanggilan file ImageIcon 
            yang ingin dipanggil*/
            Selengkapnya_Btn.setIcon(Btn_Selengkapnya);/*Pallete pada java (berupa JLabel) 
            yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Selengkapnya_BtnMouseReleased

    private void Email_TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Email_TextFieldFocusGained
        Email_Label.setVisible(false); //JLabel dibuat menghilang
    }//GEN-LAST:event_Email_TextFieldFocusGained

    private void Email_TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Email_TextFieldFocusLost
        // TODO add your handling code here:
        if(Email_TextField.getText().length() > 0){ // Ketika jumlah karakter lebih dari nol
            Email_Label.setVisible(false);// Jlabel dibuat menghilang
        }else{
            Email_Label.setVisible(true);// Jlabel dibuat muncul kembali
        }
    }//GEN-LAST:event_Email_TextFieldFocusLost

    private void Email_TextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Email_TextFieldKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_ENTER){ // Kondisi apabila menggunakan tombol down atau tombol enter pada keyboard
            KataSandi_TextField.requestFocus(); // Menuju JTextField yang berada dibawahnya
        }
    }//GEN-LAST:event_Email_TextFieldKeyPressed

    private void User_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_User_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_User;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_User = new ImageIcon("src/Picture/Effect/carbon_user-avatar-filled-alt.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        User_Btn.setIcon(Btn_User);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_User_BtnMouseReleased

    private void User_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_User_BtnMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_User;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_User = new ImageIcon("src/Picture/Effect/carbon_user-avatar-filled-alt effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        User_Btn.setIcon(Btn_User);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_User_BtnMousePressed

    private void User_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_User_BtnMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_User;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_User = new ImageIcon("src/Picture/Effect/carbon_user-avatar-filled-alt.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        User_Btn.setIcon(Btn_User);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_User_BtnMouseExited

    private void User_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_User_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_User;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_User = new ImageIcon("src/Picture/Effect/carbon_user-avatar-filled-alt effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        User_Btn.setIcon(Btn_User);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_User_BtnMouseEntered

    private void User_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_User_BtnMouseClicked
        // TODO add your handling code here:
        Home_Btn_eft2.setVisible(false);
        ImageIcon Btn_User;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_User = new ImageIcon("src/Picture/Effect/carbon_user-avatar-filled-alt effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        User_Btn.setIcon(Btn_User);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
            //Menghapus Panel   
        Main_Panel.removeAll();
        Main_Panel.repaint();
        Main_Panel.revalidate();
        
        //Menambah panel
        Main_Panel.add(UserPagePanel);
        Main_Panel.repaint();
        Main_Panel.revalidate();   
    }//GEN-LAST:event_User_BtnMouseClicked

    private void Chart_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Chart_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Chart;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Chart = new ImageIcon("src/Picture/Effect/clarity_shopping-cart-line.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Chart_Btn.setIcon(Btn_Chart);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Chart_BtnMouseReleased

    private void Chart_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Chart_BtnMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Chart;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Chart = new ImageIcon("src/Picture/Effect/clarity_shopping-cart-line effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Chart_Btn.setIcon(Btn_Chart);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Chart_BtnMousePressed

    private void Chart_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Chart_BtnMouseExited
        ImageIcon Btn_Chart;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Chart = new ImageIcon("src/Picture/Effect/clarity_shopping-cart-line.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Chart_Btn.setIcon(Btn_Chart);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Chart_BtnMouseExited

    private void Chart_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Chart_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Chart;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Chart = new ImageIcon("src/Picture/Effect/clarity_shopping-cart-line effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Chart_Btn.setIcon(Btn_Chart);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Chart_BtnMouseEntered

    private void Chart_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Chart_BtnMouseClicked
        // TODO add your handling code here:
        Home_Btn_eft2.setVisible(false);
        ImageIcon Btn_Chart;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Chart = new ImageIcon("src/Picture/Effect/clarity_shopping-cart-line effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Chart_Btn.setIcon(Btn_Chart);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
    
         //Menghapus Panel
            Main_Panel.removeAll();
            Main_Panel.repaint();
            Main_Panel.revalidate();
          
            //Menambah panel
            Main_Panel.add(Keranjang_Panel);
            Main_Panel.repaint();
            Main_Panel.revalidate();
    }//GEN-LAST:event_Chart_BtnMouseClicked

    private void Wishlist_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Wishlist_BtnMouseReleased
        // TODO add your handling code here
        ImageIcon Btn_Wishlist;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Wishlist = new ImageIcon("src/Picture/Effect/ic_baseline-favorite-border.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Wishlist_Btn.setIcon(Btn_Wishlist);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Wishlist_BtnMouseReleased

    private void Wishlist_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Wishlist_BtnMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Wishlist;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Wishlist = new ImageIcon("src/Picture/Effect/ic_baseline-favorite-border effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Wishlist_Btn.setIcon(Btn_Wishlist);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Wishlist_BtnMousePressed

    private void Wishlist_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Wishlist_BtnMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Wishlist;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Wishlist = new ImageIcon("src/Picture/Effect/ic_baseline-favorite-border.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Wishlist_Btn.setIcon(Btn_Wishlist);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Wishlist_BtnMouseExited

    private void Wishlist_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Wishlist_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Wishlist;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Wishlist = new ImageIcon("src/Picture/Effect/ic_baseline-favorite-border effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Wishlist_Btn.setIcon(Btn_Wishlist);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Wishlist_BtnMouseEntered

    private void Wishlist_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Wishlist_BtnMouseClicked
        // TODO add your handling code here:
        Home_Btn_eft2.setVisible(false);
        ImageIcon Btn_Wishlist;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Wishlist = new ImageIcon("src/Picture/Effect/ic_baseline-favorite-border effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Wishlist_Btn.setIcon(Btn_Wishlist);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
         //Menghapus Panel
            Main_Panel.removeAll();
            Main_Panel.repaint();
            Main_Panel.revalidate();
          
            //Menambah panel
            Main_Panel.add(Wishlist_Panel);
            Main_Panel.repaint();
            Main_Panel.revalidate();
    }//GEN-LAST:event_Wishlist_BtnMouseClicked

    private void Home_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Home_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Home;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Home = new ImageIcon("src/Picture/Effect/Rumah.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Home_Btn.setIcon(Btn_Home);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        if(Home_Panel.isShowing()){
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
        
        if(Home_Panel.isShowing()){
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

        ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Belanja.png");/*Format pemanggilan file ImageIcon
        // yang ingin dipanggil*/
        Belanja_Btn.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel)
        //yang digunakan untuk melakukan set pada ImageIcon*/
        
        ImageIcon Btn_Kontak;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Kontak = new ImageIcon("src/Picture/Effect/Kontak.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Kontak_Btn.setIcon(Btn_Kontak);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        ImageIcon Btn_Tentang;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Tentang = new ImageIcon("src/Picture/Effect/Tentang.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Tentang_Btn.setIcon(Btn_Tentang);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        

        //Menghapus Panel
        Main_Panel.removeAll();
        Main_Panel.repaint();
        Main_Panel.revalidate();

        //Menambah panel
        Main_Panel.add(Home_Panel);
        Main_Panel.repaint();
        Main_Panel.revalidate();
        
        
    }//GEN-LAST:event_Home_BtnMouseClicked

    private void Belanja_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Belanja_BtnMouseReleased
        
        ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Belanja.png");/*Format pemanggilan file ImageIcon
        // yang ingin dipanggil*/
        Belanja_Btn.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel)
        //yang digunakan untuk melakukan set pada ImageIcon*/
        
        if(Belanja_Panel.isShowing()){
        ImageIcon Btn_Belanja1;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja1 = new ImageIcon("src/Picture/Effect/Belanja effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Belanja_Btn.setIcon(Btn_Belanja1);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        }
    }//GEN-LAST:event_Belanja_BtnMouseReleased

    private void Belanja_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Belanja_BtnMouseExited
        //TODO add your handling code here:
        ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Belanja.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Belanja_Btn.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        if(Belanja_Panel.isShowing()){
        ImageIcon Btn_Belanja1;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja1 = new ImageIcon("src/Picture/Effect/Belanja effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Belanja_Btn.setIcon(Btn_Belanja1);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        }
    }//GEN-LAST:event_Belanja_BtnMouseExited

    private void Belanja_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Belanja_BtnMouseEntered
        // TODO add your handling code here:
        
              
            ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan
            untuk memanggil ImageIcon*/
            Btn_Belanja = new ImageIcon("src/Picture/Effect/Belanja effect 3.png");/*Format pemanggilan file ImageIcon
            yang ingin dipanggil*/
            Belanja_Btn.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/    
        
      
        
    }//GEN-LAST:event_Belanja_BtnMouseEntered

    private void Belanja_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Belanja_BtnMouseClicked
        // TODO add your handling code here:
        
        slideshow();
        
        Home_Btn_eft2.setVisible(false);
        ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Belanja effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Belanja_Btn.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel)
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
        
         ImageIcon Btn_Kontak;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Kontak = new ImageIcon("src/Picture/Effect/Kontak.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Kontak_Btn.setIcon(Btn_Kontak);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        //Menghapus Panel
        Main_Panel.removeAll();
        Main_Panel.repaint();
        Main_Panel.revalidate();

        //Menambah panel
        Main_Panel.add(Belanja_Panel);
        Main_Panel.repaint();
        Main_Panel.revalidate();
        
        PreparedStatement cmd;
        ResultSet rs;
        String sqd = "SELECT tb_produk.`id_produk`, tb_produk.`nama_produk`, tb_detail_produk.`foto_produk`, tb_detail_produk.`harga` "
                + "FROM tb_produk JOIN tb_detail_produk ON tb_produk.`id_produk`= tb_detail_produk.`id_produk` "
                + "ORDER BY tb_produk.`id_produk` DESC LIMIT 0,1";
        try {
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sqd);
            rs =  cmd.executeQuery();
            if(rs.next()){
                byte[] foto=null;
                byte [] img=rs.getBytes("foto_produk");
                BufferedImage file=ImageIO.read(new ByteArrayInputStream(img));
                ImageIcon icon1=new ImageIcon(resize(Image_D.getWidth(), Image_D.getHeight(), file));
                Image_D.setIcon(icon1);
                Produk_D.setText(rs.getString("nama_produk"));
                Harga_D.setText("Rp"+rs.getString("harga"));
            }
            else{
                JOptionPane.showMessageDialog(null, "DATA NOT FOUND");
            }
        } catch (Exception e) {
        }
        String sqc = "SELECT tb_produk.`id_produk`, tb_produk.`nama_produk`, tb_detail_produk.`foto_produk`, tb_detail_produk.`harga` "
                + "FROM tb_produk JOIN tb_detail_produk ON tb_produk.`id_produk`= tb_detail_produk.`id_produk` "
                + "ORDER BY tb_produk.`id_produk` DESC LIMIT 1,1";
        try {
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sqc);
            rs =  cmd.executeQuery();
            if(rs.next()){
                byte[] foto=null;
                byte [] img=rs.getBytes("foto_produk");
                BufferedImage file=ImageIO.read(new ByteArrayInputStream(img));
                ImageIcon icon1=new ImageIcon(resize(Image_C.getWidth(), Image_C.getHeight(), file));
                Image_C.setIcon(icon1);
                Produk_C.setText(rs.getString("nama_produk"));
                Harga_C.setText("Rp"+rs.getString("harga"));
            }
            else{
                JOptionPane.showMessageDialog(null, "DATA NOT FOUND");
            }
        } catch (Exception e) {
        }
        String sqb = "SELECT tb_produk.`id_produk`, tb_produk.`nama_produk`, tb_detail_produk.`foto_produk`, tb_detail_produk.`harga` "
                + "FROM tb_produk JOIN tb_detail_produk ON tb_produk.`id_produk`= tb_detail_produk.`id_produk` "
                + "ORDER BY tb_produk.`id_produk` DESC LIMIT 2,1";
        try {
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sqb);
            rs =  cmd.executeQuery();
            if(rs.next()){
                byte[] foto=null;
                byte [] img=rs.getBytes("foto_produk");
                BufferedImage file=ImageIO.read(new ByteArrayInputStream(img));
                ImageIcon icon1=new ImageIcon(resize(Image_B.getWidth(), Image_B.getHeight(), file));
                Image_B.setIcon(icon1);
                Produk_B.setText(rs.getString("nama_produk"));
                Harga_B.setText("Rp"+rs.getString("harga"));
            }
            else{
                JOptionPane.showMessageDialog(null, "DATA NOT FOUND");
            }
        } catch (Exception e) {
        }
        String sqa = "SELECT tb_produk.`id_produk`, tb_produk.`nama_produk`, tb_detail_produk.`foto_produk`, tb_detail_produk.`harga` "
                + "FROM tb_produk JOIN tb_detail_produk ON tb_produk.`id_produk`= tb_detail_produk.`id_produk` "
                + "ORDER BY tb_produk.`id_produk` DESC LIMIT 3,1";
        try {
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sqa);
            rs =  cmd.executeQuery();
            if(rs.next()){
                byte[] foto=null;
                byte [] img=rs.getBytes("foto_produk");
                BufferedImage file=ImageIO.read(new ByteArrayInputStream(img));
                ImageIcon icon1=new ImageIcon(resize(Image_A.getWidth(), Image_A.getHeight(), file));
                Image_A.setIcon(icon1);
                Produk_A.setText(rs.getString("nama_produk"));
                Harga_A.setText("Rp"+rs.getString("harga"));
            }
            else{
                JOptionPane.showMessageDialog(null, "DATA NOT FOUND");
            }
        } catch (Exception e) {
        }
        String sqta = "SELECT tb_seller.`id_seller`, tb_seller.`nama_seller`, tb_seller.`foto_seller`, tb_seller.`alamat_seller` "
                + "FROM tb_seller ORDER BY tb_seller.`id_seller` ASC LIMIT 0,1";
        try {
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sqta);
            rs =  cmd.executeQuery();
            if(rs.next()){
                byte[] foto=null;
                byte [] img=rs.getBytes("foto_seller");
                BufferedImage file=ImageIO.read(new ByteArrayInputStream(img));
                ImageIcon icon1=new ImageIcon(resize(Image_A1.getWidth(), Image_A1.getHeight(), file));
                Image_A1.setIcon(icon1);
                Toko_A.setText(rs.getString("nama_seller"));
                Jalan_A.setText(rs.getString("alamat_seller"));
            }
            else{
                JOptionPane.showMessageDialog(null, "DATA NOT FOUND");
            }
        } catch (Exception e) {
        }
        String sqtb = "SELECT tb_seller.`id_seller`, tb_seller.`nama_seller`, tb_seller.`foto_seller`, tb_seller.`alamat_seller` "
                + "FROM tb_seller ORDER BY tb_seller.`id_seller` ASC LIMIT 1,1";
        try {
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sqtb);
            rs =  cmd.executeQuery();
            if(rs.next()){
                byte[] foto=null;
                byte [] img=rs.getBytes("foto_seller");
                BufferedImage file=ImageIO.read(new ByteArrayInputStream(img));
                ImageIcon icon1=new ImageIcon(resize(Image_B1.getWidth(), Image_B1.getHeight(), file));
                Image_B1.setIcon(icon1);
                Toko_B.setText(rs.getString("nama_seller"));
                Jalan_B.setText(rs.getString("alamat_seller"));
            }
            else{
                JOptionPane.showMessageDialog(null, "DATA NOT FOUND");
            }
        } catch (Exception e) {
        }
        String sqtc = "SELECT tb_seller.`id_seller`, tb_seller.`nama_seller`, tb_seller.`foto_seller`, tb_seller.`alamat_seller` "
                + "FROM tb_seller ORDER BY tb_seller.`id_seller` ASC LIMIT 2,1";
        try {
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sqtc);
            rs =  cmd.executeQuery();
            if(rs.next()){
                byte[] foto=null;
                byte [] img=rs.getBytes("foto_seller");
                BufferedImage file=ImageIO.read(new ByteArrayInputStream(img));
                ImageIcon icon1=new ImageIcon(resize(Image_C1.getWidth(), Image_C1.getHeight(), file));
                Image_C1.setIcon(icon1);
                Toko_C.setText(rs.getString("nama_seller"));
                Jalan_C.setText(rs.getString("alamat_seller"));
            }
            else{
                JOptionPane.showMessageDialog(null, "DATA NOT FOUND");
            }
        } catch (Exception e) {
        }
        String sqpta = "CALL produk_terlaris(0,1)";
        try {
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sqpta);
            rs =  cmd.executeQuery();
            if(rs.next()){
                byte[] foto=null;
                byte [] img=rs.getBytes("foto_produk");
                BufferedImage file=ImageIO.read(new ByteArrayInputStream(img));
                ImageIcon icon1=new ImageIcon(resize(Image_TA.getWidth(), Image_TA.getHeight(), file));
                Image_TA.setIcon(icon1);
                Produk_TA.setText(rs.getString("nama_produk"));
                Harga_TA.setText("Rp"+rs.getString("harga"));
            }
            else{
                JOptionPane.showMessageDialog(null, "DATA NOT FOUND");
            }
        } catch (Exception e) {
        }
        
        String sqptb = "CALL produk_terlaris(1,1)";
        try {
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sqptb);
            rs =  cmd.executeQuery();
            if(rs.next()){
                byte[] foto=null;
                byte [] img=rs.getBytes("foto_produk");
                BufferedImage file=ImageIO.read(new ByteArrayInputStream(img));
                ImageIcon icon1=new ImageIcon(resize(Image_TB.getWidth(), Image_TB.getHeight(), file));
                Image_TB.setIcon(icon1);
                Produk_TB.setText(rs.getString("nama_produk"));
                Harga_TB.setText("Rp"+rs.getString("harga"));
            }
            else{
                JOptionPane.showMessageDialog(null, "DATA NOT FOUND");
            }
        } catch (Exception e) {
        }
        
        String sqptc = "CALL produk_terlaris(2,1)";
        try {
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sqptc);
            rs =  cmd.executeQuery();
            if(rs.next()){
                byte[] foto=null;
                byte [] img=rs.getBytes("foto_produk");
                BufferedImage file=ImageIO.read(new ByteArrayInputStream(img));
                ImageIcon icon1=new ImageIcon(resize(Image_TC.getWidth(), Image_TC.getHeight(), file));
                Image_TC.setIcon(icon1);
                Produk_TC.setText(rs.getString("nama_produk"));
                Harga_TC.setText("Rp"+rs.getString("harga"));
            }
            else{
                JOptionPane.showMessageDialog(null, "DATA NOT FOUND");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_Belanja_BtnMouseClicked

    private void Kontak_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Kontak_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Kontak;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Kontak = new ImageIcon("src/Picture/Effect/Kontak.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Kontak_Btn.setIcon(Btn_Kontak);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        if(Kontak_Panel.isShowing()){
            ImageIcon Btn_Kontak1;/*Deklarasi variabel yang digunakan
            untuk memanggil ImageIcon*/
            Btn_Kontak1 = new ImageIcon("src/Picture/Effect/Kontak effect 2.png");/*Format pemanggilan file ImageIcon
            yang ingin dipanggil*/
            Kontak_Btn.setIcon(Btn_Kontak1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
        }
        
        
    }//GEN-LAST:event_Kontak_BtnMouseReleased

    private void Kontak_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Kontak_BtnMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Kontak;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Kontak = new ImageIcon("src/Picture/Effect/Kontak.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Kontak_Btn.setIcon(Btn_Kontak);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        if(Kontak_Panel.isShowing()){
             ImageIcon Btn_Kontak1;/*Deklarasi variabel yang digunakan
            untuk memanggil ImageIcon*/
            Btn_Kontak1 = new ImageIcon("src/Picture/Effect/Kontak effect 2.png");/*Format pemanggilan file ImageIcon
            yang ingin dipanggil*/
            Kontak_Btn.setIcon(Btn_Kontak1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
        }
    }//GEN-LAST:event_Kontak_BtnMouseExited

    private void Kontak_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Kontak_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Kontak;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Kontak = new ImageIcon("src/Picture/Effect/Kontak effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Kontak_Btn.setIcon(Btn_Kontak);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
    }//GEN-LAST:event_Kontak_BtnMouseEntered

    private void Kontak_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Kontak_BtnMouseClicked
        // TODO add your handling code here:
        Home_Btn_eft2.setVisible(false);
        ImageIcon Btn_Kontak;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Kontak = new ImageIcon("src/Picture/Effect/Kontak effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Kontak_Btn.setIcon(Btn_Kontak);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        ImageIcon Btn_Tentang;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Tentang = new ImageIcon("src/Picture/Effect/Tentang.png");/*Format pemanggilan file ImageIcon
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
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Belanja.png");/*Format pemanggilan file ImageIcon
        // yang ingin dipanggil*/
        Belanja_Btn.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel)
        //yang digunakan untuk melakukan set pada ImageIcon*/
        
        
        
        //Menghapus Panel
        Main_Panel.removeAll();
        Main_Panel.repaint();
        Main_Panel.revalidate();

        //Menambah panel
        Main_Panel.add(Kontak_Panel);
        Main_Panel.repaint();
        Main_Panel.revalidate();
    }//GEN-LAST:event_Kontak_BtnMouseClicked

    private void Tentang_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tentang_BtnMouseReleased
        ImageIcon Btn_Tentang;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Tentang = new ImageIcon("src/Picture/Effect/Tentang.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Tentang_Btn.setIcon(Btn_Tentang);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        if(About_Us_Panel.isShowing()){
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
        
        if(About_Us_Panel.isShowing()){
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
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Belanja.png");/*Format pemanggilan file ImageIcon
        // yang ingin dipanggil*/
        Belanja_Btn.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel)
        //yang digunakan untuk melakukan set pada ImageIcon*/
        
        ImageIcon Btn_Kontak;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Kontak = new ImageIcon("src/Picture/Effect/Kontak.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Kontak_Btn.setIcon(Btn_Kontak);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
       

        //Menghapus Panel
        Main_Panel.removeAll();
        Main_Panel.repaint();
        Main_Panel.revalidate();

        //Menambah panel
        Main_Panel.add(About_Us_Panel);
        Main_Panel.repaint();
        Main_Panel.revalidate();
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
        
        Search_ScrollPanel.setVisible(true);
        findProduct();
    }//GEN-LAST:event_Search_BtnMouseClicked

    private void Next_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Next_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Next;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Next = new ImageIcon("src/Picture/Effect/Effect Shop Page/Next Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Next_Btn.setIcon(Btn_Next);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Next_BtnMouseEntered

    private void Next_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Next_BtnMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Next;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Next = new ImageIcon("src/Picture/Effect/Effect Shop Page/Next Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Next_Btn.setIcon(Btn_Next);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Next_BtnMouseExited

    private void Next_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Next_BtnMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Next;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Next = new ImageIcon("src/Picture/Effect/Effect Shop Page/Next Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Next_Btn.setIcon(Btn_Next);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Next_BtnMousePressed

    private void Next_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Next_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Next;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Next = new ImageIcon("src/Picture/Effect/Effect Shop Page/Next Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Next_Btn.setIcon(Btn_Next);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Next_BtnMouseReleased

    private void Next_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Next_BtnMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Next;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Next = new ImageIcon("src/Picture/Effect/Effect Shop Page/Next Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Next_Btn.setIcon(Btn_Next);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        
        
        if(Opening1_Panel.isShowing()){
            
        ImageIcon Btn_Bullet1, Btn_Bullet2, Btn_Bullet3;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Bullet1 = new ImageIcon("src/Picture/Effect/Bullet.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Btn_Bullet2 = new ImageIcon("src/Picture/Effect/Bullet Effect.png");
        Btn_Bullet3 = new ImageIcon("src/Picture/Effect/Bullet.png");
        Bullet1.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        Bullet2.setIcon(Btn_Bullet2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        Bullet3.setIcon(Btn_Bullet3);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
             //Menghapus Panel
            Opening_Shop_Panel.removeAll();
            Opening_Shop_Panel.repaint();
            Opening_Shop_Panel.revalidate();

            //Menambah panel
            Opening_Shop_Panel.add(Opening2_Panel);
            Opening_Shop_Panel.repaint();
            Opening_Shop_Panel.revalidate();
            
        
        
        }else if(Opening2_Panel.isShowing()){
            
             ImageIcon Btn_Bullet1, Btn_Bullet2, Btn_Bullet3;/*Deklarasi variabel yang digunakan
            untuk memanggil ImageIcon*/
            Btn_Bullet1 = new ImageIcon("src/Picture/Effect/Bullet.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
            Btn_Bullet2 = new ImageIcon("src/Picture/Effect/Bullet.png");
            Btn_Bullet3 = new ImageIcon("src/Picture/Effect/Bullet Effect.png");
            Bullet1.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Bullet2.setIcon(Btn_Bullet2);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Bullet3.setIcon(Btn_Bullet3);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            
              //Menghapus Panel
            Opening_Shop_Panel.removeAll();
            Opening_Shop_Panel.repaint();
            Opening_Shop_Panel.revalidate();

            //Menambah panel
            Opening_Shop_Panel.add(Opening3_Panel);
            Opening_Shop_Panel.repaint();
            Opening_Shop_Panel.revalidate();
            
        
        
        }else if(Opening3_Panel.isShowing()){
            
        ImageIcon Btn_Bullet1, Btn_Bullet2, Btn_Bullet3;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Bullet1 = new ImageIcon("src/Picture/Effect/Bullet.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Btn_Bullet2 = new ImageIcon("src/Picture/Effect/Bullet Effect.png");
        Btn_Bullet3 = new ImageIcon("src/Picture/Effect/Bullet.png");
        Bullet1.setIcon(Btn_Bullet2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        Bullet2.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        Bullet3.setIcon(Btn_Bullet3);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
            
            Opening_Shop_Panel.removeAll();
            Opening_Shop_Panel.repaint();
            Opening_Shop_Panel.revalidate();

            //Menambah panel
            Opening_Shop_Panel.add(Opening1_Panel);
            Opening_Shop_Panel.repaint();
            Opening_Shop_Panel.revalidate();
            
        }
        
    }//GEN-LAST:event_Next_BtnMouseClicked

    private void Bullet1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Bullet1MouseClicked
        // TODO add your handling code here:
            Opening_Shop_Panel.removeAll();
            Opening_Shop_Panel.repaint();
            Opening_Shop_Panel.revalidate();

            //Menambah panel
            Opening_Shop_Panel.add(Opening1_Panel);
            Opening_Shop_Panel.repaint();
            Opening_Shop_Panel.revalidate();
            
            ImageIcon Btn_Bullet1, Btn_Bullet2;/*Deklarasi variabel yang digunakan
            untuk memanggil ImageIcon*/
            Btn_Bullet1 = new ImageIcon("src/Picture/Effect/Bullet.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
            Btn_Bullet2 = new ImageIcon("src/Picture/Effect/Bullet Effect.png");
            Bullet1.setIcon(Btn_Bullet2);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Bullet2.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Bullet3.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Opening_Shop_Panel.repaint();
            Opening_Shop_Panel.revalidate();
    }//GEN-LAST:event_Bullet1MouseClicked

    private void Bullet1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Bullet1MouseExited
        // TODO add your handling code here:
        
    }//GEN-LAST:event_Bullet1MouseExited

    private void Bullet1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Bullet1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_Bullet1MouseReleased

    private void Bullet2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Bullet2MouseClicked
        // TODO add your handling code here:
            Opening_Shop_Panel.removeAll();
            Opening_Shop_Panel.repaint();
            Opening_Shop_Panel.revalidate();

            //Menambah panel
            Opening_Shop_Panel.add(Opening2_Panel);
            Opening_Shop_Panel.repaint();
            Opening_Shop_Panel.revalidate();
            
            
            ImageIcon Btn_Bullet1, Btn_Bullet2;/*Deklarasi variabel yang digunakan
            untuk memanggil ImageIcon*/
            Btn_Bullet1 = new ImageIcon("src/Picture/Effect/Bullet.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
            Btn_Bullet2 = new ImageIcon("src/Picture/Effect/Bullet Effect.png");
            Bullet1.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Bullet2.setIcon(Btn_Bullet2);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Bullet3.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Opening_Shop_Panel.repaint();
            Opening_Shop_Panel.revalidate();
    }//GEN-LAST:event_Bullet2MouseClicked

    private void Bullet2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Bullet2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_Bullet2MouseExited

    private void Bullet2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Bullet2MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_Bullet2MouseReleased

    private void Bullet3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Bullet3MouseClicked
        // TODO add your handling code here:
         //Menghapus Panel
            Opening_Shop_Panel.removeAll();
            //Opening_Shop_Panel.repaint();
            //Opening_Shop_Panel.revalidate();
          
            //Menambah panel
            Opening_Shop_Panel.add(Opening3_Panel);
            Opening_Shop_Panel.repaint();
            Opening_Shop_Panel.revalidate();
            
            ImageIcon Btn_Bullet1, Btn_Bullet2;/*Deklarasi variabel yang digunakan
            untuk memanggil ImageIcon*/
            Btn_Bullet1 = new ImageIcon("src/Picture/Effect/Bullet.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
            Btn_Bullet2 = new ImageIcon("src/Picture/Effect/Bullet Effect.png");
            Bullet1.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Bullet2.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Bullet3.setIcon(Btn_Bullet2);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Bullet3MouseClicked

    private void Bullet3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Bullet3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_Bullet3MouseExited

    private void Bullet3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Bullet3MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_Bullet3MouseReleased

    private void Prev_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Prev_BtnMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Prev;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Prev = new ImageIcon("src/Picture/Effect/Effect Shop Page/Prev Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Prev_Btn.setIcon(Btn_Prev);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        if(Opening1_Panel.isShowing()){
             //Menghapus Panel
            Opening_Shop_Panel.removeAll();
            //Opening_Shop_Panel.repaint();
            //Opening_Shop_Panel.revalidate();
          
            //Menambah panel
            Opening_Shop_Panel.add(Opening3_Panel);
            Opening_Shop_Panel.repaint();
            Opening_Shop_Panel.revalidate();
            
            ImageIcon Btn_Bullet1, Btn_Bullet2;/*Deklarasi variabel yang digunakan
            untuk memanggil ImageIcon*/
            Btn_Bullet1 = new ImageIcon("src/Picture/Effect/Bullet.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
            Btn_Bullet2 = new ImageIcon("src/Picture/Effect/Bullet Effect.png");
            Bullet1.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Bullet2.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Bullet3.setIcon(Btn_Bullet2);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
        
        }else if(Opening2_Panel.isShowing()){
              //Menghapus Panel
            Opening_Shop_Panel.removeAll();
            Opening_Shop_Panel.repaint();
            Opening_Shop_Panel.revalidate();

            //Menambah panel
            Opening_Shop_Panel.add(Opening1_Panel);
            Opening_Shop_Panel.repaint();
            Opening_Shop_Panel.revalidate();
            
            ImageIcon Btn_Bullet1, Btn_Bullet2;/*Deklarasi variabel yang digunakan
            untuk memanggil ImageIcon*/
            Btn_Bullet1 = new ImageIcon("src/Picture/Effect/Bullet.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
            Btn_Bullet2 = new ImageIcon("src/Picture/Effect/Bullet Effect.png");
            Bullet1.setIcon(Btn_Bullet2);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Bullet2.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Bullet3.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            
        
        
        }else if(Opening3_Panel.isShowing()){
            
            Opening_Shop_Panel.removeAll();
            Opening_Shop_Panel.repaint();
            Opening_Shop_Panel.revalidate();

            //Menambah panel
            Opening_Shop_Panel.add(Opening2_Panel);
            Opening_Shop_Panel.repaint();
            Opening_Shop_Panel.revalidate();
            
            ImageIcon Btn_Bullet1, Btn_Bullet2;/*Deklarasi variabel yang digunakan
            untuk memanggil ImageIcon*/
            Btn_Bullet1 = new ImageIcon("src/Picture/Effect/Bullet.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
            Btn_Bullet2 = new ImageIcon("src/Picture/Effect/Bullet Effect.png");
            Bullet1.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Bullet2.setIcon(Btn_Bullet2);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Bullet3.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            
        
        }
    }//GEN-LAST:event_Prev_BtnMouseClicked

    private void Prev_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Prev_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Prev;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Prev = new ImageIcon("src/Picture/Effect/Effect Shop Page/Prev Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Prev_Btn.setIcon(Btn_Prev);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Prev_BtnMouseEntered

    private void Prev_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Prev_BtnMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Prev;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Prev = new ImageIcon("src/Picture/Effect/Effect Shop Page/Prev Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Prev_Btn.setIcon(Btn_Prev);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Prev_BtnMouseExited

    private void Prev_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Prev_BtnMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Prev;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Prev = new ImageIcon("src/Picture/Effect/Effect Shop Page/Prev Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Prev_Btn.setIcon(Btn_Prev);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Prev_BtnMousePressed

    private void Prev_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Prev_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Prev;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Prev = new ImageIcon("src/Picture/Effect/Effect Shop Page/Prev Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Prev_Btn.setIcon(Btn_Prev);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Prev_BtnMouseReleased

    private void Produk1_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Produk1_BtnMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Produk1;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Produk1= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk 1 Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Produk1_Btn.setIcon(Btn_Produk1);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Produk1_BtnMouseClicked

    private void Produk1_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Produk1_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Produk1;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Produk1= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk 1 Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Produk1_Btn.setIcon(Btn_Produk1);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Produk1_BtnMouseEntered

    private void Produk1_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Produk1_BtnMouseExited
        // TODO add your handling code here:
         ImageIcon Btn_Produk1;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Produk1= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Produk1_Btn.setIcon(Btn_Produk1);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Produk1_BtnMouseExited

    private void Produk1_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Produk1_BtnMousePressed
        // TODO add your handling code here:
         ImageIcon Btn_Produk1;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Produk1= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk 1 Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Produk1_Btn.setIcon(Btn_Produk1);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Produk1_BtnMousePressed

    private void Produk1_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Produk1_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Produk1;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Produk1= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Produk1_Btn.setIcon(Btn_Produk1);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Produk1_BtnMouseReleased

    private void Produk2_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Produk2_BtnMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Produk2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Produk2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk 2 Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Produk2_Btn.setIcon(Btn_Produk2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
    }//GEN-LAST:event_Produk2_BtnMouseClicked

    private void Produk2_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Produk2_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Produk2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Produk2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk 2 Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Produk2_Btn.setIcon(Btn_Produk2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Produk2_BtnMouseEntered

    private void Produk2_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Produk2_BtnMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Produk2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Produk2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Produk2_Btn.setIcon(Btn_Produk2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Produk2_BtnMouseExited

    private void Produk2_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Produk2_BtnMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Produk2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Produk2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk 2 Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Produk2_Btn.setIcon(Btn_Produk2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Produk2_BtnMousePressed

    private void Produk2_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Produk2_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Produk2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Produk2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk 2 Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Produk2_Btn.setIcon(Btn_Produk2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Produk2_BtnMouseReleased

    private void Produk3_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Produk3_BtnMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Produk3;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Produk3= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk 3 Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Produk3_Btn.setIcon(Btn_Produk3);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Produk3_BtnMouseClicked

    private void Produk3_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Produk3_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Produk3;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Produk3= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk 3 Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Produk3_Btn.setIcon(Btn_Produk3);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Produk3_BtnMouseEntered

    private void Produk3_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Produk3_BtnMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Produk3;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Produk3= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Produk3_Btn.setIcon(Btn_Produk3);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Produk3_BtnMouseExited

    private void Produk3_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Produk3_BtnMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Produk3;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Produk3= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk 3 Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Produk3_Btn.setIcon(Btn_Produk3);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Produk3_BtnMousePressed

    private void Produk3_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Produk3_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Produk3;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Produk3= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Produk3_Btn.setIcon(Btn_Produk3);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Produk3_BtnMouseReleased

    private void SouvernirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SouvernirMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Souvernir;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Souvernir= new ImageIcon("src/Picture/Effect/Effect Shop Page/Souvenir Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Souvernir.setIcon(Btn_Souvernir);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_SouvernirMouseClicked

    private void SouvernirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SouvernirMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Souvernir;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Souvernir= new ImageIcon("src/Picture/Effect/Effect Shop Page/Souvenir Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Souvernir.setIcon(Btn_Souvernir);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_SouvernirMouseEntered

    private void SouvernirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SouvernirMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Souvernir;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Souvernir= new ImageIcon("src/Picture/Effect/Effect Shop Page/Souvenir.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Souvernir.setIcon(Btn_Souvernir);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_SouvernirMouseExited

    private void SouvernirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SouvernirMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Souvernir;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Souvernir= new ImageIcon("src/Picture/Effect/Effect Shop Page/Souvenir Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Souvernir.setIcon(Btn_Souvernir);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_SouvernirMousePressed

    private void SouvernirMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SouvernirMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Souvernir;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Souvernir= new ImageIcon("src/Picture/Effect/Effect Shop Page/Souvenir.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Souvernir.setIcon(Btn_Souvernir);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_SouvernirMouseReleased

    private void KadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KadoMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Kado;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Kado= new ImageIcon("src/Picture/Effect/Effect Shop Page/Kado Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Kado.setIcon(Btn_Kado);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_KadoMouseClicked

    private void KadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KadoMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Kado;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Kado= new ImageIcon("src/Picture/Effect/Effect Shop Page/Kado Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Kado.setIcon(Btn_Kado);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_KadoMouseEntered

    private void KadoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KadoMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Kado;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Kado= new ImageIcon("src/Picture/Effect/Effect Shop Page/Kado.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Kado.setIcon(Btn_Kado);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_KadoMouseExited

    private void KadoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KadoMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Kado;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Kado= new ImageIcon("src/Picture/Effect/Effect Shop Page/Kado Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Kado.setIcon(Btn_Kado);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_KadoMousePressed

    private void KadoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KadoMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Kado;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Kado= new ImageIcon("src/Picture/Effect/Effect Shop Page/Kado.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Kado.setIcon(Btn_Kado);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_KadoMouseReleased

    private void DekorasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DekorasiMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Dekorasi;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Dekorasi= new ImageIcon("src/Picture/Effect/Effect Shop Page/Dekorasi Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Dekorasi.setIcon(Btn_Dekorasi);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_DekorasiMouseClicked

    private void DekorasiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DekorasiMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Dekorasi;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Dekorasi= new ImageIcon("src/Picture/Effect/Effect Shop Page/Dekorasi Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Dekorasi.setIcon(Btn_Dekorasi);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_DekorasiMouseEntered

    private void DekorasiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DekorasiMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Dekorasi;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Dekorasi= new ImageIcon("src/Picture/Effect/Effect Shop Page/Dekorasi.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Dekorasi.setIcon(Btn_Dekorasi);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_DekorasiMouseExited

    private void DekorasiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DekorasiMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Dekorasi;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Dekorasi= new ImageIcon("src/Picture/Effect/Effect Shop Page/Dekorasi Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Dekorasi.setIcon(Btn_Dekorasi);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_DekorasiMousePressed

    private void DekorasiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DekorasiMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Dekorasi;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Dekorasi= new ImageIcon("src/Picture/Effect/Effect Shop Page/Dekorasi.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Dekorasi.setIcon(Btn_Dekorasi);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_DekorasiMouseReleased

    private void MerchandiseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MerchandiseMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Mrc;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Mrc = new ImageIcon("src/Picture/Effect/Effect Shop Page/Merchandise Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Merchandise.setIcon(Btn_Mrc);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_MerchandiseMouseClicked

    private void MerchandiseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MerchandiseMouseEntered
        // TODO add your handling code here:
         // TODO add your handling code here:
        ImageIcon Btn_Mrc;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Mrc = new ImageIcon("src/Picture/Effect/Effect Shop Page/Merchandise Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Merchandise.setIcon(Btn_Mrc);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_MerchandiseMouseEntered

    private void MerchandiseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MerchandiseMouseExited
        // TODO add your handling code here:
         // TODO add your handling code here:
        ImageIcon Btn_Mrc;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Mrc = new ImageIcon("src/Picture/Effect/Effect Shop Page/Merchandise.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Merchandise.setIcon(Btn_Mrc);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_MerchandiseMouseExited

    private void MerchandiseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MerchandiseMousePressed
        // TODO add your handling code here:
         // TODO add your handling code here:
        ImageIcon Btn_Mrc;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Mrc = new ImageIcon("src/Picture/Effect/Effect Shop Page/Merchandise Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Merchandise.setIcon(Btn_Mrc);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_MerchandiseMousePressed

    private void MerchandiseMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MerchandiseMouseReleased
        // TODO add your handling code here:
         // TODO add your handling code here:
        ImageIcon Btn_Mrc;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Mrc = new ImageIcon("src/Picture/Effect/Effect Shop Page/Merchandise.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Merchandise.setIcon(Btn_Mrc);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_MerchandiseMouseReleased

    private void ParselMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ParselMouseClicked
        // TODO add your handling code here:
         // TODO add your handling code here:
        ImageIcon Btn_Prc;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Prc = new ImageIcon("src/Picture/Effect/Effect Shop Page/Parsel Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Parsel.setIcon(Btn_Prc);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ParselMouseClicked

    private void ParselMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ParselMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Prc;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Prc = new ImageIcon("src/Picture/Effect/Effect Shop Page/Parsel Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Parsel.setIcon(Btn_Prc);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ParselMouseEntered

    private void ParselMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ParselMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Prc;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Prc = new ImageIcon("src/Picture/Effect/Effect Shop Page/Parsel.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Parsel.setIcon(Btn_Prc);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ParselMouseExited

    private void ParselMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ParselMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Prc;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Prc = new ImageIcon("src/Picture/Effect/Effect Shop Page/Parsel Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Parsel.setIcon(Btn_Prc);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ParselMousePressed

    private void ParselMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ParselMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Prc;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Prc = new ImageIcon("src/Picture/Effect/Effect Shop Page/Parsel.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Parsel.setIcon(Btn_Prc);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ParselMouseReleased

    private void More_Btn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_More_Btn1MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_More;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_More = new ImageIcon("src/Picture/Effect/Effect Shop Page/Selengkapnya2 Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        More_Btn1.setIcon(Btn_More);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
            Main_Panel.removeAll();
            Main_Panel.repaint();
            Main_Panel.revalidate();

            //Menambah panel
            Main_Panel.add(Our_Channels_Panel);
            Main_Panel.repaint();
            Main_Panel.revalidate();
    }//GEN-LAST:event_More_Btn1MouseClicked

    private void More_Btn1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_More_Btn1MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_More;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_More = new ImageIcon("src/Picture/Effect/Effect Shop Page/Selengkapnya2 Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        More_Btn1.setIcon(Btn_More);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_More_Btn1MouseEntered

    private void More_Btn1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_More_Btn1MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_More;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_More = new ImageIcon("src/Picture/Effect/Effect Shop Page/Masuk.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        More_Btn1.setIcon(Btn_More);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_More_Btn1MouseExited

    private void More_Btn1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_More_Btn1MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_More;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_More = new ImageIcon("src/Picture/Effect/Effect Shop Page/Selengkapnya2 Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        More_Btn1.setIcon(Btn_More);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_More_Btn1MousePressed

    private void More_Btn1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_More_Btn1MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_More;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_More = new ImageIcon("src/Picture/Effect/Effect Shop Page/Masuk.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        More_Btn1.setIcon(Btn_More);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_More_Btn1MouseReleased

    private void TokoAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoAMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_TokoA;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_TokoA= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko A Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoA.setIcon(Btn_TokoA);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        Toko_A.setForeground(Color.BLACK);
        Jalan_A.setForeground(Color.BLACK);
    }//GEN-LAST:event_TokoAMouseClicked

    private void TokoAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoAMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_TokoA;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_TokoA= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko A Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoA.setIcon(Btn_TokoA);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        Toko_A.setForeground(Color.GRAY);
        Jalan_A.setForeground(Color.GRAY);
    }//GEN-LAST:event_TokoAMouseEntered

    private void TokoAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoAMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_TokoA;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_TokoA= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko A.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoA.setIcon(Btn_TokoA);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        Toko_A.setForeground(Color.WHITE);
        Jalan_A.setForeground(Color.WHITE);
    }//GEN-LAST:event_TokoAMouseExited

    private void TokoAMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoAMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_TokoA;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_TokoA= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko A Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoA.setIcon(Btn_TokoA);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        Toko_A.setForeground(Color.BLACK);
        Jalan_A.setForeground(Color.BLACK);
    }//GEN-LAST:event_TokoAMousePressed

    private void TokoAMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoAMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_TokoA;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_TokoA= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko A.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoA.setIcon(Btn_TokoA);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        Toko_A.setForeground(Color.WHITE);
        Jalan_A.setForeground(Color.WHITE);
    }//GEN-LAST:event_TokoAMouseReleased

    private void TokoBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoBMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_TokoB;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_TokoB= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko B Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoB.setIcon(Btn_TokoB);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        Toko_B.setForeground(Color.BLACK);
        Jalan_B.setForeground(Color.BLACK);
    }//GEN-LAST:event_TokoBMouseClicked

    private void TokoBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoBMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_TokoB;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_TokoB= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko B Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoB.setIcon(Btn_TokoB);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        Toko_B.setForeground(Color.GRAY);
        Jalan_B.setForeground(Color.GRAY);
    }//GEN-LAST:event_TokoBMouseEntered

    private void TokoBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoBMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_TokoB;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_TokoB= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko B.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoB.setIcon(Btn_TokoB);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        Toko_B.setForeground(Color.WHITE);
        Jalan_B.setForeground(Color.WHITE);
    }//GEN-LAST:event_TokoBMouseExited

    private void TokoBMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoBMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_TokoB;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_TokoB= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko B Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoB.setIcon(Btn_TokoB);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        Toko_B.setForeground(Color.BLACK);
        Jalan_B.setForeground(Color.BLACK);
    }//GEN-LAST:event_TokoBMousePressed

    private void TokoBMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoBMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_TokoB;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_TokoB= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko B.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoB.setIcon(Btn_TokoB);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        Toko_B.setForeground(Color.WHITE);
        Jalan_B.setForeground(Color.WHITE);
    }//GEN-LAST:event_TokoBMouseReleased

    private void TokoCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoCMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_TokoC;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_TokoC= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko C Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoC.setIcon(Btn_TokoC);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        Toko_C.setForeground(Color.BLACK);
        Jalan_C.setForeground(Color.BLACK);
    }//GEN-LAST:event_TokoCMouseClicked

    private void TokoCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoCMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_TokoC;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_TokoC= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko C Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoC.setIcon(Btn_TokoC);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        Toko_C.setForeground(Color.GRAY);
        Jalan_C.setForeground(Color.GRAY);
    }//GEN-LAST:event_TokoCMouseEntered

    private void TokoCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoCMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_TokoC;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_TokoC= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko C.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoC.setIcon(Btn_TokoC);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        Toko_C.setForeground(Color.WHITE);
        Jalan_C.setForeground(Color.WHITE);
    }//GEN-LAST:event_TokoCMouseExited

    private void TokoCMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoCMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_TokoC;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_TokoC= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko C Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoC.setIcon(Btn_TokoC);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        Toko_C.setForeground(Color.BLACK);
        Jalan_C.setForeground(Color.BLACK);
    }//GEN-LAST:event_TokoCMousePressed

    private void TokoCMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoCMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_TokoC;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_TokoC= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko C.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoC.setIcon(Btn_TokoC);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        Toko_C.setForeground(Color.WHITE);
        Jalan_C.setForeground(Color.WHITE);
    }//GEN-LAST:event_TokoCMouseReleased

    private void ProdukAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukAMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_ProdukA;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_ProdukA= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk A Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukA.setIcon(Btn_ProdukA);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukAMouseClicked

    private void ProdukAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukAMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_ProdukA;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_ProdukA= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk A Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukA.setIcon(Btn_ProdukA);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukAMouseEntered

    private void ProdukAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukAMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_ProdukA;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_ProdukA= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk A.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukA.setIcon(Btn_ProdukA);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukAMouseExited

    private void ProdukAMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukAMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_ProdukA;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_ProdukA= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk A Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukA.setIcon(Btn_ProdukA);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukAMousePressed

    private void ProdukAMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukAMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_ProdukA;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_ProdukA= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk A.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukA.setIcon(Btn_ProdukA);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukAMouseReleased

    private void ProdukBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukBMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_ProdukB;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_ProdukB= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk B Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukB.setIcon(Btn_ProdukB);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukBMouseClicked

    private void ProdukBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukBMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_ProdukB;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_ProdukB= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk B Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukB.setIcon(Btn_ProdukB);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukBMouseEntered

    private void ProdukBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukBMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_ProdukB;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_ProdukB= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk B.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukB.setIcon(Btn_ProdukB);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukBMouseExited

    private void ProdukBMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukBMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_ProdukB;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_ProdukB= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk B Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukB.setIcon(Btn_ProdukB);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukBMousePressed

    private void ProdukBMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukBMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_ProdukB;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_ProdukB= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk B.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukB.setIcon(Btn_ProdukB);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukBMouseReleased

    private void ProdukCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukCMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_ProdukC;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_ProdukC = new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk C Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukC.setIcon(Btn_ProdukC);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukCMouseClicked

    private void ProdukCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukCMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_ProdukC;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_ProdukC = new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk C Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukC.setIcon(Btn_ProdukC);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukCMouseEntered

    private void ProdukCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukCMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_ProdukC;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_ProdukC = new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk C.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukC.setIcon(Btn_ProdukC);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukCMouseExited

    private void ProdukCMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukCMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_ProdukC;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_ProdukC = new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk C Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukC.setIcon(Btn_ProdukC);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukCMousePressed

    private void ProdukCMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukCMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_ProdukC;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_ProdukC = new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk C.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukC.setIcon(Btn_ProdukC);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukCMouseReleased

    private void ProdukDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukDMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_ProdukD;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_ProdukD = new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk D Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukD.setIcon(Btn_ProdukD);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukDMouseClicked

    private void ProdukDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukDMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_ProdukD;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_ProdukD = new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk D Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukD.setIcon(Btn_ProdukD);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukDMouseEntered

    private void ProdukDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukDMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_ProdukD;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_ProdukD = new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk D.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukD.setIcon(Btn_ProdukD);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukDMouseExited

    private void ProdukDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukDMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_ProdukD;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_ProdukD = new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk D Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukD.setIcon(Btn_ProdukD);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukDMousePressed

    private void ProdukDMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukDMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_ProdukD;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_ProdukD = new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk D.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukD.setIcon(Btn_ProdukD);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukDMouseReleased

    private void Buy_Now_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Buy_Now_BtnMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Buy;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Buy= new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Buy_Now_Btn.setIcon(Btn_Buy);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Buy_Now_BtnMouseClicked

    private void Buy_Now_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Buy_Now_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Buy;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Buy= new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Buy_Now_Btn.setIcon(Btn_Buy);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Buy_Now_BtnMouseEntered

    private void Buy_Now_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Buy_Now_BtnMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Buy;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Buy= new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Buy_Now_Btn.setIcon(Btn_Buy);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Buy_Now_BtnMouseExited

    private void Buy_Now_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Buy_Now_BtnMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Buy;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Buy= new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Buy_Now_Btn.setIcon(Btn_Buy);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Buy_Now_BtnMousePressed

    private void Buy_Now_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Buy_Now_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Buy;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Buy= new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Buy_Now_Btn.setIcon(Btn_Buy);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Buy_Now_BtnMouseReleased

    private void More_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_More_BtnMouseClicked
        // TODO add your handling code here:
        slideshow1();
        
        ImageIcon Btn_More;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_More = new ImageIcon("src/Picture/Effect/Effect Shop Page/Selengkapnya2 Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        More_Btn.setIcon(Btn_More);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
            Main_Panel.removeAll();
            Main_Panel.repaint();
            Main_Panel.revalidate();

            //Menambah panel
            Main_Panel.add(Our_Item_Panel);
            Main_Panel.repaint();
            Main_Panel.revalidate();
    }//GEN-LAST:event_More_BtnMouseClicked

    private void More_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_More_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_More;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_More = new ImageIcon("src/Picture/Effect/Effect Shop Page/Selengkapnya2 Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        More_Btn.setIcon(Btn_More);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_More_BtnMouseEntered

    private void More_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_More_BtnMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_More;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_More = new ImageIcon("src/Picture/Effect/Effect Shop Page/Masuk.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        More_Btn.setIcon(Btn_More);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_More_BtnMouseExited

    private void More_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_More_BtnMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_More;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_More = new ImageIcon("src/Picture/Effect/Effect Shop Page/Selengkapnya2 Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        More_Btn.setIcon(Btn_More);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_More_BtnMousePressed

    private void More_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_More_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_More;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_More = new ImageIcon("src/Picture/Effect/Effect Shop Page/Masuk.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        More_Btn.setIcon(Btn_More);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_More_BtnMouseReleased

    private void Next_Btn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Next_Btn1MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Next;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Next = new ImageIcon("src/Picture/Effect/Effect Shop Page/Next Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Next_Btn1.setIcon(Btn_Next);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        
        
        if(Opening1_Panel1.isShowing()){
            
        ImageIcon Btn_Bullet1, Btn_Bullet2, Btn_Bullet3;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Bullet1 = new ImageIcon("src/Picture/Effect/Bullet.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Btn_Bullet2 = new ImageIcon("src/Picture/Effect/Bullet Effect.png");
        Btn_Bullet3 = new ImageIcon("src/Picture/Effect/Bullet.png");
        Bullet_Item1.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        Bullet_Item2.setIcon(Btn_Bullet2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        Bullet_Item3.setIcon(Btn_Bullet3);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
             //Menghapus Panel
            Opening_Item_Panel.removeAll();
            Opening_Item_Panel.repaint();
            Opening_Item_Panel.revalidate();

            //Menambah panel
            Opening_Item_Panel.add(Opening2_Panel1);
            Opening_Item_Panel.repaint();
            Opening_Item_Panel.revalidate();
            
        
        
        }else if(Opening2_Panel1.isShowing()){
            
             ImageIcon Btn_Bullet1, Btn_Bullet2, Btn_Bullet3;/*Deklarasi variabel yang digunakan
            untuk memanggil ImageIcon*/
            Btn_Bullet1 = new ImageIcon("src/Picture/Effect/Bullet.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
            Btn_Bullet2 = new ImageIcon("src/Picture/Effect/Bullet.png");
            Btn_Bullet3 = new ImageIcon("src/Picture/Effect/Bullet Effect.png");
            Bullet_Item1.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Bullet_Item2.setIcon(Btn_Bullet2);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Bullet_Item3.setIcon(Btn_Bullet3);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            
              //Menghapus Panel
            Opening_Item_Panel.removeAll();
            Opening_Item_Panel.repaint();
            Opening_Item_Panel.revalidate();

            //Menambah panel
            Opening_Item_Panel.add(Opening3_Panel1);
            Opening_Item_Panel.repaint();
            Opening_Item_Panel.revalidate();
            
        
        
        }else if(Opening3_Panel1.isShowing()){
            
        ImageIcon Btn_Bullet1, Btn_Bullet2, Btn_Bullet3;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Bullet1 = new ImageIcon("src/Picture/Effect/Bullet.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Btn_Bullet2 = new ImageIcon("src/Picture/Effect/Bullet Effect.png");
        Btn_Bullet3 = new ImageIcon("src/Picture/Effect/Bullet.png");
        Bullet_Item1.setIcon(Btn_Bullet2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        Bullet_Item2.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        Bullet_Item3.setIcon(Btn_Bullet3);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
            
            Opening_Item_Panel.removeAll();
            Opening_Item_Panel.repaint();
            Opening_Item_Panel.revalidate();

            //Menambah panel
            Opening_Item_Panel.add(Opening1_Panel1);
            Opening_Item_Panel.repaint();
            Opening_Item_Panel.revalidate();
        }
    }//GEN-LAST:event_Next_Btn1MouseClicked

    private void Next_Btn1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Next_Btn1MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Next;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Next = new ImageIcon("src/Picture/Effect/Effect Shop Page/Next Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Next_Btn1.setIcon(Btn_Next);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Next_Btn1MouseEntered

    private void Next_Btn1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Next_Btn1MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Next;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Next = new ImageIcon("src/Picture/Effect/Effect Shop Page/Next Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Next_Btn1.setIcon(Btn_Next);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Next_Btn1MouseExited

    private void Next_Btn1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Next_Btn1MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Next;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Next = new ImageIcon("src/Picture/Effect/Effect Shop Page/Next Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Next_Btn1.setIcon(Btn_Next);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Next_Btn1MousePressed

    private void Next_Btn1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Next_Btn1MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Next;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Next = new ImageIcon("src/Picture/Effect/Effect Shop Page/Next Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Next_Btn1.setIcon(Btn_Next);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Next_Btn1MouseReleased

    private void Prev_Btn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Prev_Btn1MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Prev;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Prev = new ImageIcon("src/Picture/Effect/Effect Shop Page/Prev Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Prev_Btn1.setIcon(Btn_Prev);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        if(Opening1_Panel1.isShowing()){
             //Menghapus Panel
            Opening_Item_Panel.removeAll();
            Opening_Item_Panel.repaint();
            Opening_Item_Panel.revalidate();
          
            //Menambah panel
            Opening_Item_Panel.add(Opening3_Panel1);
            Opening_Item_Panel.repaint();
            Opening_Item_Panel.revalidate();
            
            ImageIcon Btn_Bullet1, Btn_Bullet2;/*Deklarasi variabel yang digunakan
            untuk memanggil ImageIcon*/
            Btn_Bullet1 = new ImageIcon("src/Picture/Effect/Bullet.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
            Btn_Bullet2 = new ImageIcon("src/Picture/Effect/Bullet Effect.png");
            Bullet_Item1.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Bullet_Item2.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Bullet_Item3.setIcon(Btn_Bullet2);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
        
        }else if(Opening2_Panel1.isShowing()){
              //Menghapus Panel
            Opening_Item_Panel.removeAll();
            Opening_Item_Panel.repaint();
            Opening_Item_Panel.revalidate();

            //Menambah panel
            Opening_Item_Panel.add(Opening1_Panel1);
            Opening_Item_Panel.repaint();
            Opening_Item_Panel.revalidate();
            
            ImageIcon Btn_Bullet1, Btn_Bullet2;/*Deklarasi variabel yang digunakan
            untuk memanggil ImageIcon*/
            Btn_Bullet1 = new ImageIcon("src/Picture/Effect/Bullet.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
            Btn_Bullet2 = new ImageIcon("src/Picture/Effect/Bullet Effect.png");
            Bullet_Item1.setIcon(Btn_Bullet2);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Bullet_Item2.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Bullet_Item3.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            
        
        
        }else if(Opening3_Panel1.isShowing()){
            
            Opening_Item_Panel.removeAll();
            Opening_Item_Panel.repaint();
            Opening_Item_Panel.revalidate();

            //Menambah panel
            Opening_Item_Panel.add(Opening2_Panel1);
            Opening_Item_Panel.repaint();
            Opening_Item_Panel.revalidate();
            
            ImageIcon Btn_Bullet1, Btn_Bullet2;/*Deklarasi variabel yang digunakan
            untuk memanggil ImageIcon*/
            Btn_Bullet1 = new ImageIcon("src/Picture/Effect/Bullet.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
            Btn_Bullet2 = new ImageIcon("src/Picture/Effect/Bullet Effect.png");
            Bullet_Item1.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Bullet_Item2.setIcon(Btn_Bullet2);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Bullet_Item3.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            
        
        }
    }//GEN-LAST:event_Prev_Btn1MouseClicked

    private void Prev_Btn1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Prev_Btn1MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Prev;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Prev = new ImageIcon("src/Picture/Effect/Effect Shop Page/Prev Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Prev_Btn1.setIcon(Btn_Prev);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Prev_Btn1MouseEntered

    private void Prev_Btn1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Prev_Btn1MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Prev;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Prev = new ImageIcon("src/Picture/Effect/Effect Shop Page/Prev Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Prev_Btn1.setIcon(Btn_Prev);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
    }//GEN-LAST:event_Prev_Btn1MouseExited

    private void Prev_Btn1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Prev_Btn1MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Prev;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Prev = new ImageIcon("src/Picture/Effect/Effect Shop Page/Prev Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Prev_Btn1.setIcon(Btn_Prev);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Prev_Btn1MousePressed

    private void Prev_Btn1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Prev_Btn1MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Prev;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Prev = new ImageIcon("src/Picture/Effect/Effect Shop Page/Prev Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Prev_Btn1.setIcon(Btn_Prev);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Prev_Btn1MouseReleased

    private void Bullet_Item3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Bullet_Item3MouseClicked
        // TODO add your handling code here:
        Opening_Item_Panel.removeAll();
            Opening_Item_Panel.repaint();
            Opening_Item_Panel.revalidate();

            //Menambah panel
            Opening_Item_Panel.add(Opening3_Panel1);
            Opening_Item_Panel.repaint();
            Opening_Item_Panel.revalidate();
            
            ImageIcon Btn_Bullet1, Btn_Bullet2;/*Deklarasi variabel yang digunakan
            untuk memanggil ImageIcon*/
            Btn_Bullet1 = new ImageIcon("src/Picture/Effect/Bullet.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
            Btn_Bullet2 = new ImageIcon("src/Picture/Effect/Bullet Effect.png");
            Bullet_Item1.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Bullet_Item2.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Bullet_Item3.setIcon(Btn_Bullet2);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Opening_Item_Panel.repaint();
            Opening_Item_Panel.revalidate();
    }//GEN-LAST:event_Bullet_Item3MouseClicked

    private void Bullet_Item3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Bullet_Item3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_Bullet_Item3MouseExited

    private void Bullet_Item3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Bullet_Item3MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_Bullet_Item3MouseReleased

    private void Bullet_Item2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Bullet_Item2MouseClicked
        // TODO add your handling code here:
        Opening_Item_Panel.removeAll();
            Opening_Item_Panel.repaint();
            Opening_Item_Panel.revalidate();

            //Menambah panel
            Opening_Item_Panel.add(Opening2_Panel1);
            Opening_Item_Panel.repaint();
            Opening_Item_Panel.revalidate();
            
            ImageIcon Btn_Bullet1, Btn_Bullet2;/*Deklarasi variabel yang digunakan
            untuk memanggil ImageIcon*/
            Btn_Bullet1 = new ImageIcon("src/Picture/Effect/Bullet.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
            Btn_Bullet2 = new ImageIcon("src/Picture/Effect/Bullet Effect.png");
            Bullet_Item1.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Bullet_Item2.setIcon(Btn_Bullet2);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Bullet_Item3.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Opening_Item_Panel.repaint();
            Opening_Item_Panel.revalidate();
    }//GEN-LAST:event_Bullet_Item2MouseClicked

    private void Bullet_Item2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Bullet_Item2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_Bullet_Item2MouseExited

    private void Bullet_Item2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Bullet_Item2MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_Bullet_Item2MouseReleased

    private void Bullet_Item1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Bullet_Item1MouseClicked
        // TODO add your handling code here:
            Opening_Item_Panel.removeAll();
            Opening_Item_Panel.repaint();
            Opening_Item_Panel.revalidate();

            //Menambah panel
            Opening_Item_Panel.add(Opening1_Panel1);
            Opening_Item_Panel.repaint();
            Opening_Item_Panel.revalidate();
            
            ImageIcon Btn_Bullet1, Btn_Bullet2;/*Deklarasi variabel yang digunakan
            untuk memanggil ImageIcon*/
            Btn_Bullet1 = new ImageIcon("src/Picture/Effect/Bullet.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
            Btn_Bullet2 = new ImageIcon("src/Picture/Effect/Bullet Effect.png");
            Bullet_Item1.setIcon(Btn_Bullet2);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Bullet_Item2.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Bullet_Item3.setIcon(Btn_Bullet1);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
            Opening_Item_Panel.repaint();
            Opening_Item_Panel.revalidate();
    }//GEN-LAST:event_Bullet_Item1MouseClicked

    private void Bullet_Item1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Bullet_Item1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_Bullet_Item1MouseExited

    private void Bullet_Item1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Bullet_Item1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_Bullet_Item1MouseReleased

    private void Buy_Now_Btn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Buy_Now_Btn1MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Buy;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Buy= new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Buy_Now_Btn1.setIcon(Btn_Buy);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Buy_Now_Btn1MouseClicked

    private void Buy_Now_Btn1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Buy_Now_Btn1MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Buy;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Buy= new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Buy_Now_Btn1.setIcon(Btn_Buy);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Buy_Now_Btn1MouseEntered

    private void Buy_Now_Btn1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Buy_Now_Btn1MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Buy;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Buy= new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Buy_Now_Btn1.setIcon(Btn_Buy);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Buy_Now_Btn1MouseExited

    private void Buy_Now_Btn1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Buy_Now_Btn1MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Buy;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Buy= new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Buy_Now_Btn1.setIcon(Btn_Buy);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Buy_Now_Btn1MousePressed

    private void Buy_Now_Btn1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Buy_Now_Btn1MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Buy;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Buy= new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Buy_Now_Btn1.setIcon(Btn_Buy);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Buy_Now_Btn1MouseReleased

    private void Buy_Now_Btn2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Buy_Now_Btn2MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Buy;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Buy= new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Buy_Now_Btn2.setIcon(Btn_Buy);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Buy_Now_Btn2MouseClicked

    private void Buy_Now_Btn2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Buy_Now_Btn2MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Buy;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Buy= new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Buy_Now_Btn2.setIcon(Btn_Buy);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Buy_Now_Btn2MouseEntered

    private void Buy_Now_Btn2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Buy_Now_Btn2MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Buy;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Buy= new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Buy_Now_Btn2.setIcon(Btn_Buy);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Buy_Now_Btn2MouseExited

    private void Buy_Now_Btn2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Buy_Now_Btn2MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Buy;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Buy= new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Buy_Now_Btn2.setIcon(Btn_Buy);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Buy_Now_Btn2MousePressed

    private void Buy_Now_Btn2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Buy_Now_Btn2MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Buy;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Buy= new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Buy_Now_Btn2.setIcon(Btn_Buy);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Buy_Now_Btn2MouseReleased

    private void Buy_Now_Btn3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Buy_Now_Btn3MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Buy;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Buy= new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Buy_Now_Btn3.setIcon(Btn_Buy);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Buy_Now_Btn3MouseClicked

    private void Buy_Now_Btn3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Buy_Now_Btn3MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Buy;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Buy= new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Buy_Now_Btn3.setIcon(Btn_Buy);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Buy_Now_Btn3MouseEntered

    private void Buy_Now_Btn3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Buy_Now_Btn3MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Buy;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Buy= new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Buy_Now_Btn3.setIcon(Btn_Buy);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Buy_Now_Btn3MouseExited

    private void Buy_Now_Btn3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Buy_Now_Btn3MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Buy;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Buy= new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Buy_Now_Btn3.setIcon(Btn_Buy);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Buy_Now_Btn3MousePressed

    private void Buy_Now_Btn3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Buy_Now_Btn3MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Buy;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Buy= new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Buy_Now_Btn3.setIcon(Btn_Buy);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Buy_Now_Btn3MouseReleased

    private void ProdukA2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukA2MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_PA2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PA2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk A2 Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukA2.setIcon(Btn_PA2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukA2MouseClicked

    private void ProdukA2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukA2MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_PA2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PA2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk A2 Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukA2.setIcon(Btn_PA2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukA2MouseEntered

    private void ProdukA2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukA2MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_PA2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PA2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk A2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukA2.setIcon(Btn_PA2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukA2MouseExited

    private void ProdukA2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukA2MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_PA2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PA2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk A2 Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukA2.setIcon(Btn_PA2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukA2MousePressed

    private void ProdukA2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukA2MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_PA2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PA2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk A2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukA2.setIcon(Btn_PA2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukA2MouseReleased

    private void ProdukB2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukB2MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_PB2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PB2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk B2 Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukB2.setIcon(Btn_PB2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukB2MouseClicked

    private void ProdukB2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukB2MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_PB2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PB2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk B2 Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukB2.setIcon(Btn_PB2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukB2MouseEntered

    private void ProdukB2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukB2MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_PB2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PB2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk B2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukB2.setIcon(Btn_PB2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukB2MouseExited

    private void ProdukB2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukB2MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_PB2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PB2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk B2 Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukB2.setIcon(Btn_PB2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukB2MousePressed

    private void ProdukB2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukB2MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_PB2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PB2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk B2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukB2.setIcon(Btn_PB2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukB2MouseReleased

    private void ProdukC2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukC2MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_PC2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PC2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk C2 Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukC2.setIcon(Btn_PC2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukC2MouseClicked

    private void ProdukC2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukC2MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_PC2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PC2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk C2 Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukC2.setIcon(Btn_PC2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukC2MouseEntered

    private void ProdukC2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukC2MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_PC2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PC2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk C2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukC2.setIcon(Btn_PC2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukC2MouseExited

    private void ProdukC2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukC2MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_PC2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PC2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk C2 Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukC2.setIcon(Btn_PC2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukC2MousePressed

    private void ProdukC2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukC2MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_PC2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PC2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk C2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukC2.setIcon(Btn_PC2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukC2MouseReleased

    private void ProdukD2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukD2MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_PD2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PD2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk D2 Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukD2.setIcon(Btn_PD2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukD2MouseClicked

    private void ProdukD2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukD2MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_PD2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PD2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk D2 Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukD2.setIcon(Btn_PD2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukD2MouseEntered

    private void ProdukD2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukD2MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_PD2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PD2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk D2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukD2.setIcon(Btn_PD2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukD2MouseExited

    private void ProdukD2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukD2MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_PD2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PD2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk D2 Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukD2.setIcon(Btn_PD2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukD2MousePressed

    private void ProdukD2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukD2MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_PD2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PD2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk D2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukD2.setIcon(Btn_PD2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukD2MouseReleased

    private void ProdukE2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukE2MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_PE2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PE2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk E2 Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukE2.setIcon(Btn_PE2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukE2MouseClicked

    private void ProdukE2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukE2MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_PE2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PE2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk E2 Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukE2.setIcon(Btn_PE2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukE2MouseEntered

    private void ProdukE2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukE2MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_PE2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PE2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk E2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukE2.setIcon(Btn_PE2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukE2MouseExited

    private void ProdukE2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukE2MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_PE2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PE2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk E2 Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukE2.setIcon(Btn_PE2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukE2MousePressed

    private void ProdukE2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukE2MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_PE2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PE2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk E2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukE2.setIcon(Btn_PE2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukE2MouseReleased

    private void ProdukF2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukF2MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_PF2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PF2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk F2 Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukF2.setIcon(Btn_PF2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukF2MouseClicked

    private void ProdukF2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukF2MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_PF2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PF2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk F2 Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukF2.setIcon(Btn_PF2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukF2MouseEntered

    private void ProdukF2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukF2MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_PF2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PF2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk F2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukF2.setIcon(Btn_PF2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukF2MouseExited

    private void ProdukF2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukF2MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_PF2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PF2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk F2 Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukF2.setIcon(Btn_PF2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukF2MousePressed

    private void ProdukF2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdukF2MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_PF2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_PF2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Produk F2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        ProdukF2.setIcon(Btn_PF2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_ProdukF2MouseReleased

    private void Back_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_BtnMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back= new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
            
            //Menghapus Panel
            Main_Panel.removeAll();
            Main_Panel.repaint();
            Main_Panel.revalidate();
          
            //Menambah panel
            Main_Panel.add(Belanja_Panel);
            Main_Panel.repaint();
            Main_Panel.revalidate();
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

    private void TokoA2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoA2MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_CA2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CA2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko A2 Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoA2.setIcon(Btn_CA2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoA2MouseClicked

    private void TokoA2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoA2MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_CA2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CA2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko A2 Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoA2.setIcon(Btn_CA2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoA2MouseEntered

    private void TokoA2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoA2MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_CA2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CA2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko A2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoA2.setIcon(Btn_CA2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoA2MouseExited

    private void TokoA2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoA2MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_CA2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CA2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko A2 Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoA2.setIcon(Btn_CA2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoA2MousePressed

    private void TokoA2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoA2MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_CA2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CA2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko A2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoA2.setIcon(Btn_CA2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoA2MouseReleased

    private void TokoB2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoB2MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_CB2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CB2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko B2 Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoB2.setIcon(Btn_CB2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoB2MouseClicked

    private void TokoB2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoB2MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_CB2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CB2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko B2 Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoB2.setIcon(Btn_CB2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoB2MouseEntered

    private void TokoB2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoB2MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_CB2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CB2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko B2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoB2.setIcon(Btn_CB2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoB2MouseExited

    private void TokoB2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoB2MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_CB2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CB2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko B2 Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoB2.setIcon(Btn_CB2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoB2MousePressed

    private void TokoB2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoB2MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_CB2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CB2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko B2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoB2.setIcon(Btn_CB2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoB2MouseReleased

    private void TokoC2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoC2MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_CC2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CC2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko C2 Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoC2.setIcon(Btn_CC2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoC2MouseClicked

    private void TokoC2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoC2MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_CC2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CC2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko C2 Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoC2.setIcon(Btn_CC2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoC2MouseEntered

    private void TokoC2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoC2MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_CC2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CC2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko C2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoC2.setIcon(Btn_CC2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoC2MouseExited

    private void TokoC2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoC2MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_CC2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CC2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko C2 Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoC2.setIcon(Btn_CC2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoC2MousePressed

    private void TokoC2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoC2MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_CC2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CC2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko C2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoC2.setIcon(Btn_CC2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoC2MouseReleased

    private void TokoD2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoD2MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_CD2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CD2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko D2 Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoD2.setIcon(Btn_CD2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoD2MouseClicked

    private void TokoD2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoD2MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_CD2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CD2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko D2 Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoD2.setIcon(Btn_CD2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoD2MouseEntered

    private void TokoD2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoD2MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_CD2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CD2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko D2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoD2.setIcon(Btn_CD2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoD2MouseExited

    private void TokoD2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoD2MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_CD2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CD2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko D2 Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoD2.setIcon(Btn_CD2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoD2MousePressed

    private void TokoD2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoD2MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_CD2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CD2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko D2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoD2.setIcon(Btn_CD2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoD2MouseReleased

    private void TokoE2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoE2MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_CE2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CE2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko E2 Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoE2.setIcon(Btn_CE2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

    }//GEN-LAST:event_TokoE2MouseClicked

    private void TokoE2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoE2MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_CE2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CE2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko E2 Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoE2.setIcon(Btn_CE2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoE2MouseEntered

    private void TokoE2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoE2MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_CE2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CE2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko E2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoE2.setIcon(Btn_CE2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoE2MouseExited

    private void TokoE2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoE2MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_CE2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CE2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko E2 Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoE2.setIcon(Btn_CE2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoE2MousePressed

    private void TokoE2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoE2MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_CE2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CE2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko E2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoE2.setIcon(Btn_CE2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoE2MouseReleased

    private void TokoF2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoF2MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_CF2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CF2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko F2 Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoF2.setIcon(Btn_CF2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoF2MouseClicked

    private void TokoF2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoF2MouseEntered
        // TODO add your handling code here:
         ImageIcon Btn_CF2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CF2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko F2 Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoF2.setIcon(Btn_CF2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoF2MouseEntered

    private void TokoF2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoF2MouseExited
        // TODO add your handling code here:
         ImageIcon Btn_CF2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CF2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko F2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoF2.setIcon(Btn_CF2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoF2MouseExited

    private void TokoF2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoF2MousePressed
        // TODO add your handling code here:
         ImageIcon Btn_CF2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CF2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko F2 Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoF2.setIcon(Btn_CF2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoF2MousePressed

    private void TokoF2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoF2MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_CF2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CF2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko F2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoF2.setIcon(Btn_CF2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoF2MouseReleased

    private void TokoG2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoG2MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_CG2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CG2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko G2 Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoG2.setIcon(Btn_CG2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoG2MouseClicked

    private void TokoG2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoG2MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_CG2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CG2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko G2 Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoG2.setIcon(Btn_CG2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoG2MouseEntered

    private void TokoG2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoG2MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_CG2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CG2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko G2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoG2.setIcon(Btn_CG2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoG2MouseExited

    private void TokoG2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoG2MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_CG2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CG2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko G2 Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoG2.setIcon(Btn_CG2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoG2MousePressed

    private void TokoG2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoG2MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_CG2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CG2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko G2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoG2.setIcon(Btn_CG2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoG2MouseReleased

    private void TokoH2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoH2MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_CH2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CH2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko H2 Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoH2.setIcon(Btn_CH2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoH2MouseClicked

    private void TokoH2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoH2MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_CH2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CH2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko H2 Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoH2.setIcon(Btn_CH2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoH2MouseEntered

    private void TokoH2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoH2MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_CH2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CH2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko H2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoH2.setIcon(Btn_CH2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoH2MouseExited

    private void TokoH2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoH2MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_CH2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CH2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko H2 Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoH2.setIcon(Btn_CH2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoH2MousePressed

    private void TokoH2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TokoH2MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_CH2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_CH2= new ImageIcon("src/Picture/Effect/Effect Shop Page/Toko H2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        TokoH2.setIcon(Btn_CH2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_TokoH2MouseReleased

    private void Back_Btn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn1MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back = new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn1.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
            
            //Menghapus Panel
            Main_Panel.removeAll();
            Main_Panel.repaint();
            Main_Panel.revalidate();
          
            //Menambah panel
            Main_Panel.add(Belanja_Panel);
            Main_Panel.repaint();
            Main_Panel.revalidate();
    }//GEN-LAST:event_Back_Btn1MouseClicked

    private void Back_Btn1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn1MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back = new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn1.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_Btn1MouseEntered

    private void Back_Btn1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn1MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back = new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn1.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_Btn1MouseExited

    private void Back_Btn1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn1MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back = new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn1.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_Btn1MousePressed

    private void Back_Btn1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Back_Btn1MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Back;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Back = new ImageIcon("src/Picture/Effect/Effect Shop Page/Back_Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Back_Btn1.setIcon(Btn_Back);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Back_Btn1MouseReleased

    private void Ayo_Belanja2_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ayo_Belanja2_BtnMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Belanja2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja2 = new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja 2 Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Ayo_Belanja2_Btn.setIcon(Btn_Belanja2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
         //Menghapus Panel
            Main_Panel.removeAll();
            Main_Panel.repaint();
            Main_Panel.revalidate();
          
            //Menambah panel
            Main_Panel.add(Belanja_Panel);
            Main_Panel.repaint();
            Main_Panel.revalidate();
            
            Home_Btn_eft2.setVisible(false);
            ImageIcon Btn_Belanja1;/*Deklarasi variabel yang digunakan 
            untuk memanggil ImageIcon*/
            Btn_Belanja1 = new ImageIcon("src/Picture/Effect/Belanja effect 2.png");/*Format pemanggilan file ImageIcon 
            yang ingin dipanggil*/
            Belanja_Btn.setIcon(Btn_Belanja1);/*Pallete pada java (berupa JLabel) 
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
            
             ImageIcon Btn_Kontak;/*Deklarasi variabel yang digunakan
            untuk memanggil ImageIcon*/
            Btn_Kontak = new ImageIcon("src/Picture/Effect/Kontak.png");/*Format pemanggilan file ImageIcon
            yang ingin dipanggil*/
            Kontak_Btn.setIcon(Btn_Kontak);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Ayo_Belanja2_BtnMouseClicked

    private void Ayo_Belanja2_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ayo_Belanja2_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Belanja2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja2 = new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja 2 Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Ayo_Belanja2_Btn.setIcon(Btn_Belanja2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Ayo_Belanja2_BtnMouseEntered

    private void Ayo_Belanja2_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ayo_Belanja2_BtnMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Belanja2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja2 = new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Ayo_Belanja2_Btn.setIcon(Btn_Belanja2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Ayo_Belanja2_BtnMouseExited

    private void Ayo_Belanja2_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ayo_Belanja2_BtnMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Belanja2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja2 = new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja 2 Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Ayo_Belanja2_Btn.setIcon(Btn_Belanja2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Ayo_Belanja2_BtnMousePressed

    private void Ayo_Belanja2_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ayo_Belanja2_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Belanja2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja2 = new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Ayo_Belanja2_Btn.setIcon(Btn_Belanja2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Ayo_Belanja2_BtnMouseReleased

    private void Social_Media1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media1MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Group-2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media1.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media1MouseClicked

    private void Social_Media1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media1MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Group-1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media1.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media1MouseEntered

    private void Social_Media1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media1MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Group.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media1.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media1MouseExited

    private void Social_Media1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media1MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Group-3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media1.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media1MousePressed

    private void Social_Media1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media1MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Group.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media1.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media1MouseReleased

    private void Social_Media2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media2MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Twitter Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media2.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media2MouseClicked

    private void Social_Media2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media2MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Twitter Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media2.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
    }//GEN-LAST:event_Social_Media2MouseEntered

    private void Social_Media2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media2MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Twitter Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media2.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media2MouseExited

    private void Social_Media2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media2MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Twitter Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media2.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media2MousePressed

    private void Social_Media2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media2MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Twitter Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media2.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media2MouseReleased

    private void Social_Media3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media3MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Facebook Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media3.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media3MouseClicked

    private void Social_Media3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media3MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Facebook Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media3.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media3MouseEntered

    private void Social_Media3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media3MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Facebook Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media3.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media3MouseExited

    private void Social_Media3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media3MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Facebook Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media3.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media3MousePressed

    private void Social_Media3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media3MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Facebook Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media3.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media3MouseReleased

    private void Social_Media4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media4MouseClicked
        // TODO add your handling code here:
         // TODO add your handling code here:
        ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Group-2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media4.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media4MouseClicked

    private void Social_Media4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media4MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Group-1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media4.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media4MouseEntered

    private void Social_Media4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media4MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Group.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media4.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media4MouseExited

    private void Social_Media4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media4MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Group-3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media4.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media4MousePressed

    private void Social_Media4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media4MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Group.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media4.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media4MouseReleased

    private void Social_Media5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media5MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Twitter Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media5.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media5MouseClicked

    private void Social_Media5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media5MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Twitter Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media5.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media5MouseEntered

    private void Social_Media5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media5MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Twitter Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media5.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media5MouseExited

    private void Social_Media5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media5MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Twitter Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media5.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media5MousePressed

    private void Social_Media5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media5MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Twitter Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media5.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media5MouseReleased

    private void Social_Media6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media6MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Facebook Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media6.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media6MouseClicked

    private void Social_Media6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media6MouseEntered
        // TODO add your handling code here:
         ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Facebook Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media6.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media6MouseEntered

    private void Social_Media6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media6MouseExited
        // TODO add your handling code here:
         ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Facebook Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media6.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media6MouseExited

    private void Social_Media6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media6MousePressed
        // TODO add your handling code here:
         ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Facebook Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media6.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media6MousePressed

    private void Social_Media6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Social_Media6MouseReleased
        // TODO add your handling code here:
         ImageIcon Btn_Medsos;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Medsos = new ImageIcon("src/Picture/Effect/Facebook Btn Effect.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Social_Media6.setIcon(Btn_Medsos);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Social_Media6MouseReleased

    private void Ayo_Belanja2_Btn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ayo_Belanja2_Btn1MouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Belanja2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja2 = new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja 2 Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Ayo_Belanja2_Btn1.setIcon(Btn_Belanja2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
            
            Home_Btn_eft2.setVisible(false);
            
            ImageIcon Btn_Belanja1;/*Deklarasi variabel yang digunakan 
            untuk memanggil ImageIcon*/
            Btn_Belanja1 = new ImageIcon("src/Picture/Effect/Belanja effect 2.png");/*Format pemanggilan file ImageIcon 
            yang ingin dipanggil*/
            Belanja_Btn.setIcon(Btn_Belanja1);/*Pallete pada java (berupa JLabel) 
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
            
             ImageIcon Btn_Kontak;/*Deklarasi variabel yang digunakan
            untuk memanggil ImageIcon*/
            Btn_Kontak = new ImageIcon("src/Picture/Effect/Kontak.png");/*Format pemanggilan file ImageIcon
            yang ingin dipanggil*/
            Kontak_Btn.setIcon(Btn_Kontak);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Ayo_Belanja2_Btn1MouseClicked

    private void Ayo_Belanja2_Btn1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ayo_Belanja2_Btn1MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Belanja2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja2 = new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja 2 Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Ayo_Belanja2_Btn1.setIcon(Btn_Belanja2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Ayo_Belanja2_Btn1MouseEntered

    private void Ayo_Belanja2_Btn1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ayo_Belanja2_Btn1MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Belanja2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja2 = new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Ayo_Belanja2_Btn1.setIcon(Btn_Belanja2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Ayo_Belanja2_Btn1MouseExited

    private void Ayo_Belanja2_Btn1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ayo_Belanja2_Btn1MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Belanja2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja2 = new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja 2 Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Ayo_Belanja2_Btn1.setIcon(Btn_Belanja2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
         //Menghapus Panel
            Main_Panel.removeAll();
            Main_Panel.repaint();
            Main_Panel.revalidate();
          
            //Menambah panel
            Main_Panel.add(Belanja_Panel);
            Main_Panel.repaint();
            Main_Panel.revalidate();
    }//GEN-LAST:event_Ayo_Belanja2_Btn1MousePressed

    private void Ayo_Belanja2_Btn1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ayo_Belanja2_Btn1MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Belanja2;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja2 = new ImageIcon("src/Picture/Effect/Effect Shop Page/Ayo Belanja 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Ayo_Belanja2_Btn1.setIcon(Btn_Belanja2);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Ayo_Belanja2_Btn1MouseReleased

    private void NamaPenggunaHome_TextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NamaPenggunaHome_TextField1FocusGained
        NamaPenggunaHome_Text1.setVisible(false);
    }//GEN-LAST:event_NamaPenggunaHome_TextField1FocusGained

    private void NamaPenggunaHome_TextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NamaPenggunaHome_TextField1FocusLost
        if(NamaPenggunaHome_TextField1.getText().length() == 0){
            NamaPenggunaHome_Text1.setVisible(true);
        }else if(NamaPenggunaHome_TextField1.getText().length() > 0){
            NamaPenggunaHome_Text1.setVisible(false);
        }
    }//GEN-LAST:event_NamaPenggunaHome_TextField1FocusLost

    private void NamaPenggunaHome_TextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NamaPenggunaHome_TextField1KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            EmailHome_TextField1.requestFocus();
        }else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            EmailHome_TextField1.requestFocus();
        }
    }//GEN-LAST:event_NamaPenggunaHome_TextField1KeyPressed

    private void EmailHome_TextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EmailHome_TextField1FocusGained
        EmailHome_Text1.setVisible(false);
    }//GEN-LAST:event_EmailHome_TextField1FocusGained

    private void EmailHome_TextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EmailHome_TextField1FocusLost
        if(EmailHome_TextField1.getText().length() == 0){
            EmailHome_Text1.setVisible(true);
        }else if(EmailHome_TextField1.getText().length() > 0){
            EmailHome_Text1.setVisible(false);
        }
    }//GEN-LAST:event_EmailHome_TextField1FocusLost

    private void EmailHome_TextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EmailHome_TextField1KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            TelpHome_TextField1.requestFocus();
        }else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            TelpHome_TextField1.requestFocus();
        }else if(evt.getKeyCode() == KeyEvent.VK_UP){
            NamaPenggunaHome_TextField1.requestFocus();
        }
    }//GEN-LAST:event_EmailHome_TextField1KeyPressed

    private void TelpHome_TextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TelpHome_TextField1FocusGained
        TelpHome_Text.setVisible(false);
    }//GEN-LAST:event_TelpHome_TextField1FocusGained

    private void TelpHome_TextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TelpHome_TextField1FocusLost
        if(TelpHome_TextField1.getText().length() == 0){
            TelpHome_Text1.setVisible(true);
        }else if(TelpHome_TextField1.getText().length() > 0){
            TelpHome_Text1.setVisible(false);
        }
    }//GEN-LAST:event_TelpHome_TextField1FocusLost

    private void TelpHome_TextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TelpHome_TextField1KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            AlamatHome_TextField1.requestFocus();
        }else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            AlamatHome_TextField1.requestFocus();
        }else if(evt.getKeyCode() == KeyEvent.VK_UP){
            EmailHome_TextField1.requestFocus();
        }
    }//GEN-LAST:event_TelpHome_TextField1KeyPressed

    private void AlamatHome_TextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_AlamatHome_TextField1FocusGained
        AlamatHome_Text1.setVisible(false);
    }//GEN-LAST:event_AlamatHome_TextField1FocusGained

    private void AlamatHome_TextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_AlamatHome_TextField1FocusLost
        if(AlamatHome_TextField1.getText().length() == 0){
            AlamatHome_Text1.setVisible(true);
        }else if(AlamatHome_TextField1.getText().length() > 0){
            AlamatHome_Text1.setVisible(false);
        }
    }//GEN-LAST:event_AlamatHome_TextField1FocusLost

    private void AlamatHome_TextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlamatHome_TextField1KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_UP){
            TelpHome_TextField1.requestFocus();
        }
    }//GEN-LAST:event_AlamatHome_TextField1KeyPressed

    private void NamaPenggunaHome_TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NamaPenggunaHome_TextFieldFocusGained
        NamaPenggunaHome_Text.setVisible(false);
    }//GEN-LAST:event_NamaPenggunaHome_TextFieldFocusGained

    private void NamaPenggunaHome_TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NamaPenggunaHome_TextFieldFocusLost
        if(NamaPenggunaHome_TextField.getText().length() == 0){
            NamaPenggunaHome_Text.setVisible(true);
        }else if(NamaPenggunaHome_TextField.getText().length() > 0){
            NamaPenggunaHome_Text.setVisible(false);
        }
    }//GEN-LAST:event_NamaPenggunaHome_TextFieldFocusLost

    private void NamaPenggunaHome_TextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NamaPenggunaHome_TextFieldKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            EmailHome_TextField.requestFocus();
        }else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            EmailHome_TextField.requestFocus();
        }
    }//GEN-LAST:event_NamaPenggunaHome_TextFieldKeyPressed

    private void EmailHome_TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EmailHome_TextFieldFocusGained
        EmailHome_Text.setVisible(false);
    }//GEN-LAST:event_EmailHome_TextFieldFocusGained

    private void EmailHome_TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EmailHome_TextFieldFocusLost
        if(EmailHome_TextField.getText().length() == 0){
            EmailHome_Text.setVisible(true);
        }else if(EmailHome_TextField.getText().length() > 0){
            EmailHome_Text.setVisible(false);
        }
    }//GEN-LAST:event_EmailHome_TextFieldFocusLost

    private void EmailHome_TextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EmailHome_TextFieldKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            TelpHome_TextField.requestFocus();
        }else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            TelpHome_TextField.requestFocus();
        }else if(evt.getKeyCode() == KeyEvent.VK_UP){
            NamaPenggunaHome_TextField.requestFocus();
        }
    }//GEN-LAST:event_EmailHome_TextFieldKeyPressed

    private void TelpHome_TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TelpHome_TextFieldFocusGained
        TelpHome_Text.setVisible(false);
    }//GEN-LAST:event_TelpHome_TextFieldFocusGained

    private void TelpHome_TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TelpHome_TextFieldFocusLost
        if(TelpHome_TextField.getText().length() == 0){
            TelpHome_Text.setVisible(true);
        }else if(TelpHome_TextField.getText().length() > 0){
            TelpHome_Text.setVisible(false);
        }
    }//GEN-LAST:event_TelpHome_TextFieldFocusLost

    private void TelpHome_TextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TelpHome_TextFieldKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            AlamatHome_TextField.requestFocus();
        }else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            AlamatHome_TextField.requestFocus();
        }else if(evt.getKeyCode() == KeyEvent.VK_UP){
            EmailHome_TextField.requestFocus();
        }
    }//GEN-LAST:event_TelpHome_TextFieldKeyPressed

    private void AlamatHome_TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_AlamatHome_TextFieldFocusGained
        AlamatHome_Text.setVisible(false);
    }//GEN-LAST:event_AlamatHome_TextFieldFocusGained

    private void AlamatHome_TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_AlamatHome_TextFieldFocusLost
        if(AlamatHome_TextField.getText().length() == 0){
            AlamatHome_Text.setVisible(true);
        }else if(AlamatHome_TextField.getText().length() > 0){
            AlamatHome_Text.setVisible(false);
        }
    }//GEN-LAST:event_AlamatHome_TextFieldFocusLost

    private void AlamatHome_TextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlamatHome_TextFieldKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_UP){
            TelpHome_TextField.requestFocus();
        }
    }//GEN-LAST:event_AlamatHome_TextFieldKeyPressed

    private void Instagram_KlikMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Instagram_KlikMouseClicked
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Instagram Btn Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Instagram_Klik.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/

        Desktop d = Desktop.getDesktop();
        try {
            try {
                d.browse(new URI("https://www.instagram.com/BilikPernik/"));
            } catch (IOException ex) {
                Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Instagram_KlikMouseClicked

    private void Instagram_KlikMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Instagram_KlikMouseEntered
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Instagram Btn Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Instagram_Klik.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Instagram_KlikMouseEntered

    private void Instagram_KlikMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Instagram_KlikMouseExited
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Instagram Btn.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Instagram_Klik.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Instagram_KlikMouseExited

    private void Instagram_KlikMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Instagram_KlikMousePressed
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Instagram Btn Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Instagram_Klik.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Instagram_KlikMousePressed

    private void Instagram_KlikMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Instagram_KlikMouseReleased
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Instagram Btn.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Instagram_Klik.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Instagram_KlikMouseReleased

    private void Twitter_KlikMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Twitter_KlikMouseClicked
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Twitter Btn Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Twitter_Klik.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/

        Desktop d = Desktop.getDesktop();
        try {
            try {
                d.browse(new URI("https://twitter.com/BilikPernik"));
            } catch (IOException ex) {
                Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Twitter_KlikMouseClicked

    private void Twitter_KlikMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Twitter_KlikMouseEntered
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Twitter Btn Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Twitter_Klik.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Twitter_KlikMouseEntered

    private void Twitter_KlikMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Twitter_KlikMouseExited
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Twitter Btn.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Twitter_Klik.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Twitter_KlikMouseExited

    private void Twitter_KlikMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Twitter_KlikMousePressed
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Twitter Btn Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Twitter_Klik.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Twitter_KlikMousePressed

    private void Twitter_KlikMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Twitter_KlikMouseReleased
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Twitter Btn.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Twitter_Klik.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Twitter_KlikMouseReleased

    private void Facebook_KlikMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Facebook_KlikMouseClicked
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Facebook Btn Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Facebook_Klik.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/

        Desktop d = Desktop.getDesktop();
        try {
            try {
                d.browse(new URI("https://www.facebook.com/profile.php?id=100069435968445"));
            } catch (IOException ex) {
                Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Facebook_KlikMouseClicked

    private void Facebook_KlikMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Facebook_KlikMouseEntered
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Facebook Btn Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Facebook_Klik.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Facebook_KlikMouseEntered

    private void Facebook_KlikMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Facebook_KlikMouseExited
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Facebook Btn.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Facebook_Klik.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Facebook_KlikMouseExited

    private void Facebook_KlikMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Facebook_KlikMousePressed
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Facebook Btn Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Facebook_Klik.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Facebook_KlikMousePressed

    private void Facebook_KlikMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Facebook_KlikMouseReleased
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Facebook Btn.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Facebook_Klik.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Facebook_KlikMouseReleased

    private void GantiFoto_TombolMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GantiFoto_TombolMouseClicked
        ImageIcon GantiFotoTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        GantiFotoTombol = new ImageIcon("src/Picture/Effect/Ganti Foto Btn Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        GantiFoto_Tombol.setIcon(GantiFotoTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/

        String Nama = NamaPenggunaHome_TextField.getText(); /*Variabel untuk mengambil data nama pengguna*/
        String Nama1 = NamaPenggunaProfile_TextField.getText(); /*Variabel untuk mengambil data nama pengguna profile*/

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(new FileNameExtensionFilter("jpg|jpeg|png|bpm", "jpg", "jpeg", "png", "bmp"));
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        Image_Label.setIcon(new ImageIcon(f.toString()));

        filename=f.getAbsolutePath();
        Filename_Text.setText(filename);
        File file=chooser.getSelectedFile();
        try {
            if(file.length() > 205659){
                JOptionPane.showMessageDialog(rootPane, "Ukuran gambar terlalu besar", "Max 200kb", JOptionPane.WARNING_MESSAGE);
            }else{
                images=ImageIO.read(file);
                ImageIcon icon=new ImageIcon(resize(Image_Label.getWidth(), Image_Label.getHeight(), images));
                Image_Label.setIcon(icon);
                filename=file.getAbsolutePath();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        try {
            File image=new File(filename);
            FileInputStream fis= new FileInputStream(image);
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            byte[] buf=new byte[1024];
            for(int readNum; (readNum=fis.read(buf))!= -1;){
                bos.write(buf, 0, readNum);
            }
            photo=bos.toByteArray();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        try {
            Class.forName("com.mysql.jdbc.Driver"); /*Pemanggilan kelas pad library JDBC driver mysql*/
            Connection connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/db_bilik_pernik","root", ""); /*variabel yang digunakan untuk pemanggilan driver mysql pada database server*/
            String sql = "update tb_pengguna set image=? where nama='"+Namaa+"' or nama='"+Nama+"' or nama='"+Nama1+"'"; /*Syntax pada mysql yang digunakan untuk melakukan pemasukkan data username, email, dan password*/
            PreparedStatement pst = connect.prepareStatement(sql); /*Variabel untuk mengambil data-data yang ingin dimasukkan ke dalam database server*/
            pst.setBytes(1, photo); /*Pemasangan dan pemasukkan data pada database server*/
            pst.execute(); /*Untuk melakukan ekseskusi pada syntax sql*/
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_GantiFoto_TombolMouseClicked
    private Image resize(int w, int h, Image img){
        BufferedImage bufimg=new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D dimensi=bufimg.createGraphics();
        dimensi.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        dimensi.drawImage(img,0,0,w,h,null);
        dimensi.dispose();
        return bufimg;
    }
    
    private void GantiFoto_TombolMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GantiFoto_TombolMouseEntered
        ImageIcon GantiFotoTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        GantiFotoTombol = new ImageIcon("src/Picture/Effect/Ganti Foto Btn Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        GantiFoto_Tombol.setIcon(GantiFotoTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_GantiFoto_TombolMouseEntered

    private void GantiFoto_TombolMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GantiFoto_TombolMouseExited
        ImageIcon GantiFotoTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        GantiFotoTombol = new ImageIcon("src/Picture/Effect/Ganti Foto Btn.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        GantiFoto_Tombol.setIcon(GantiFotoTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_GantiFoto_TombolMouseExited

    private void GantiFoto_TombolMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GantiFoto_TombolMousePressed
        ImageIcon GantiFotoTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        GantiFotoTombol = new ImageIcon("src/Picture/Effect/Ganti Foto Btn Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        GantiFoto_Tombol.setIcon(GantiFotoTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_GantiFoto_TombolMousePressed

    private void GantiFoto_TombolMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GantiFoto_TombolMouseReleased
        ImageIcon GantiFotoTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        GantiFotoTombol = new ImageIcon("src/Picture/Effect/Ganti Foto Btn.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        GantiFoto_Tombol.setIcon(GantiFotoTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_GantiFoto_TombolMouseReleased

    private void Cari_Item_TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Cari_Item_TextFieldFocusGained
        // TODO add your handling code here:
        Telusuri_Produk_Label.setVisible(false);//JLabel dibuat menghilang
        if(Cari_Item_TextField.getText().length() == 0){ // Ketika jumlah karakter lebih dari nol
            Search_ScrollPanel.setVisible(false);// Jlabel dibuat menghilang
        }else if(Cari_Item_TextField.getText().length() > 0){
            Search_ScrollPanel.setVisible(true);// Jlabel dibuat menghilang
        }else
            Search_ScrollPanel.setVisible(true);// Jlabel dibuat menghilang
            Pencarian_Tabel.setVisible(true);
            findProduct();
        Banyak_TextField.setText("");
        Total_TextField.setText("");
    }//GEN-LAST:event_Cari_Item_TextFieldFocusGained

    private void Cari_Item_TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Cari_Item_TextFieldFocusLost
        // TODO add your handling code here:
        if(Cari_Item_TextField.getText().length() > 0){ // Ketika jumlah karakter lebih dari nol
            Telusuri_Produk_Label.setVisible(false);// Jlabel dibuat menghilang
        }else{
            Telusuri_Produk_Label.setVisible(true);// Jlabel dibuat muncul kembali
            Search_ScrollPanel.setVisible(false);
        }
    }//GEN-LAST:event_Cari_Item_TextFieldFocusLost

    private void Cari_Item_TextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Cari_Item_TextFieldKeyReleased
        Search_ScrollPanel.setVisible(true);
        Pencarian_Tabel.setVisible(true);
        findProduct();
    }//GEN-LAST:event_Cari_Item_TextFieldKeyReleased

    private void Simpan_TombolMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_TombolMouseClicked
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Tombol.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/

        String Nama = NamaPenggunaHome_TextField.getText(); /*Variabel untuk mengambil data nama pengguna*/
        String Email = EmailHome_TextField.getText(); /*Variabel untuk mengambil data email*/
        String Telp = TelpHome_TextField.getText(); /*Variabel untuk mengambil data telepon*/
        String Alamat = AlamatHome_TextField.getText(); /*Variabel untuk mengambil data alamat*/
        String Nama1 = NamaPenggunaProfile_TextField.getText(); /*Variabel untuk mengambil data nama pengguna profile*/
        
        PreparedStatement cmd;
        ResultSet rs;
        /*Try dan Catch digunakan untuk menangani error pada java, eksekusi yang menampilkan
        error dan membuat program tetap berjalan tanpa dihentikan secara langsung*/
        try{ /*Statement yang digunakan untuk menghubungkan database pada mysql ke Netbeans*/
            String sql = "update tb_pengguna set nama=?, email=?, telp=?, alamat=? where nama='"+Namaa+"' or nama='"+Nama+"' or nama='"+Nama1+"'"; 
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            cmd.setString(1, NamaPenggunaHome_TextField.getText()); /*Pemasangan dan pemasukkan data pada database server*/
            cmd.setString(2, EmailHome_TextField.getText()); /*Pemasangan dan pemasukkan data pada database server*/
            cmd.setString(3, TelpHome_TextField.getText()); /*Pemasangan dan pemasukkan data pada database server*/
            cmd.setString(4, AlamatHome_TextField.getText()); /*Pemasangan dan pemasukkan data pada database server*/

            if(Nama.length() > 0 && Email.length() > 0 && Telp.length() > 0 && Alamat.length() > 0){ /*Kondisi bahwa karakter dari password yang telah dimasukkan pada JTextField harus sesuai dengan apa
                password yang dimasukkan kembali pada JTextField berikutnya*/
                cmd.executeUpdate(); /*Untuk melakukan ekseskusi pada syntax sql*/
                NamaPenggunaProfile_TextField.setText(Nama);
                NamaPenggunaHome_Text1.setVisible(false);
                NamaPenggunaHome_TextField1.setText(Nama);
                EmailHome_Text1.setVisible(false);
                EmailHome_TextField1.setText(Email);
                TelpHome_Text1.setVisible(false);
                TelpHome_TextField1.setText(Telp);
                AlamatHome_Text1.setVisible(false);
                AlamatHome_TextField1.setText(Alamat);
                NamaPenggunaHome_Text.setVisible(false); /*JTextField untuk menyimpan data nama pengguna yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
                di buat menghilang*/
                EmailHome_Text.setVisible(false); /*JTextField untuk menyimpan data confirm email yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang
                telah dimasukkan di buat menghilang*/
                TelpHome_Text.setVisible(false); /*JTextField untuk menyimpan data telp yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
                di buat menghilang*/
                AlamatHome_Text.setVisible(false); /*JTextField untuk menyimpan data alamat yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
                di buat menghilang*/
                NamaPenggunaHome_TextField.setText(Nama); /*JTextField untuk menyimpan data nama pengguna yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
                di buat menghilang*/
                EmailHome_TextField.setText(Email); /*JTextField untuk menyimpan data confirm email yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang
                telah dimasukkan di buat menghilang*/
                TelpHome_TextField.setText(Telp); /*JTextField untuk menyimpan data telp yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
                di buat menghilang*/
                AlamatHome_TextField.setText(Alamat); /*JTextField untuk menyimpan data alamat yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
                di buat menghilang*/
                
            }else{ /*Jika kondisi diatas tidak sesuai, maka menuju ke else*/
                NamaPenggunaHome_TextField.setText(""); /*JTextField untuk menyimpan data nama pengguna yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
                di buat menghilang*/
                EmailHome_TextField.setText(""); /*JTextField untuk menyimpan data confirm email yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang
                telah dimasukkan di buat menghilang*/
                TelpHome_TextField.setText(""); /*JTextField untuk menyimpan data telp yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
                di buat menghilang*/
                AlamatHome_TextField.setText(""); /*JTextField untuk menyimpan data alamat yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
                di buat menghilang*/
            }
        }catch(SQLException e) /*Menangkap kesalahan atau bug yang terjadi dalam block try*/
        {
            JOptionPane.showMessageDialog(null, e); /*Kondisi apabila terjadi kesalahan pada saat melakukan input data*/
        }

    }//GEN-LAST:event_Simpan_TombolMouseClicked

    private void Simpan_TombolMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_TombolMouseEntered
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Tombol.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_TombolMouseEntered

    private void Simpan_TombolMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_TombolMouseExited
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Tombol.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_TombolMouseExited

    private void Simpan_TombolMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_TombolMousePressed
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Tombol.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_TombolMousePressed

    private void Simpan_TombolMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_TombolMouseReleased
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Tombol.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_TombolMouseReleased

    private void Logo_PictureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Logo_PictureMouseClicked
        ImageIcon Btn_Home;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Home = new ImageIcon("src/Picture/Effect/Rumah effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Home_Btn.setIcon(Btn_Home);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
          //Menghapus Panel
        Main_Panel.removeAll();
        Main_Panel.repaint();
        Main_Panel.revalidate();

        //Menambah panel
        Main_Panel.add(Home_Panel);
        Main_Panel.repaint();
        Main_Panel.revalidate();
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

    private void LogOut_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogOut_BtnMouseClicked
        // TODO add your handling code here:

        ImageIcon Btn_LogOut;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_LogOut = new ImageIcon("src/Picture/Effect/Keluar Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        LogOut_Btn.setIcon(Btn_LogOut);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        Opening_Page_Pt_2 a = new Opening_Page_Pt_2(); /*Deklarasi
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
        Btn_LogOut = new ImageIcon("src/Picture/Effect/Keluar Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        LogOut_Btn.setIcon(Btn_LogOut);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_LogOut_BtnMouseEntered

    private void LogOut_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogOut_BtnMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_LogOut;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_LogOut = new ImageIcon("src/Picture/Effect/Keluar Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        LogOut_Btn.setIcon(Btn_LogOut);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_LogOut_BtnMouseExited

    private void LogOut_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogOut_BtnMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_LogOut;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_LogOut = new ImageIcon("src/Picture/Effect/Keluar Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        LogOut_Btn.setIcon(Btn_LogOut);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_LogOut_BtnMousePressed

    private void LogOut_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogOut_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_LogOut;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_LogOut = new ImageIcon("src/Picture/Effect/Keluar Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        LogOut_Btn.setIcon(Btn_LogOut);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_LogOut_BtnMouseReleased

    private void Pencarian_TabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Pencarian_TabelMouseClicked
        //Menghapus Panel   
        Main_Panel.removeAll();
        Main_Panel.repaint();
        Main_Panel.revalidate();
        
        //Menambah panel
        Main_Panel.add(HasilPencarianProduk_Panel);
        Main_Panel.repaint();
        Main_Panel.revalidate();
        
        int i = Pencarian_Tabel.getSelectedRow();
        TableModel model = Pencarian_Tabel.getModel();
        NamaProduk_TextField.setText(model.getValueAt(i, 0).toString());
        
        String ProdName = NamaProduk_TextField.getText();
        
        PreparedStatement cmd;
        ResultSet rs;
        String sql = "call user_cari_item('"+ProdName+"')";
        try {
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs =  cmd.executeQuery();
            if(rs.next()){
                byte[] foto=null;
                Produk_ID.setText(rs.getString("id_produk"));
                Toko_TextField.setText(rs.getString("nama_seller"));
                Massa_TextField.setText(rs.getString("massa_gr"));
                Panjang_TextField.setText(rs.getString("panjang_cm"));
                Lebar_TextField.setText(rs.getString("lebar_cm"));
                Tinggi_TextField.setText(rs.getString("tinggi_cm"));
                Harga_TextField.setText(rs.getString("harga"));
                byte [] img=rs.getBytes("foto_produk");
                BufferedImage file=ImageIO.read(new ByteArrayInputStream(img));
                ImageIcon icon=new ImageIcon(resize(Produk_Label.getWidth(), Produk_Label.getHeight(), file));
                Produk_Label.setIcon(icon);
            }
            else{
                JOptionPane.showMessageDialog(null, "DATA NOT FOUND");
            }
       
        } catch (Exception e) {
        }
    }//GEN-LAST:event_Pencarian_TabelMouseClicked
   
    private void Banyak_TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Banyak_TextFieldFocusGained
        Banyak_Text.setVisible(false);
    }//GEN-LAST:event_Banyak_TextFieldFocusGained

    private void Banyak_TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Banyak_TextFieldFocusLost
        if(Banyak_TextField.getText().length() == 0){
             Banyak_Text.setVisible(true);
        }
    }//GEN-LAST:event_Banyak_TextFieldFocusLost

    private void Banyak_TextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Banyak_TextFieldKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            hasilnya();
        }else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            hasilnya();
        }
    }//GEN-LAST:event_Banyak_TextFieldKeyPressed

    private void Search_Bar_LabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Search_Bar_LabelMouseClicked
        if(Cari_Item_TextField.getText().length() == 0){ // Ketika jumlah karakter lebih dari nol
            Search_ScrollPanel.setVisible(false);// Jlabel dibuat menghilang
        }
    }//GEN-LAST:event_Search_Bar_LabelMouseClicked

    private void MataTutup_IconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MataTutup_IconMouseClicked
        ImageIcon MataTutup_icon;
        MataTutup_icon = new ImageIcon("src/Pictures/LoginPage/Mata Tutup.png");
        MataTutup_Icon.setIcon(MataTutup_icon);

        KataSandi_TextField.setEchoChar('\u25cf');
    }//GEN-LAST:event_MataTutup_IconMouseClicked

    private void MataTutup_IconMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MataTutup_IconMousePressed
        ImageIcon MataBuka_icon;
        MataBuka_icon = new ImageIcon("src/Pictures/LoginPage/Mata Buka.png");
        MataTutup_Icon.setIcon(MataBuka_icon);

        KataSandi_TextField.setEchoChar((char)0);
    }//GEN-LAST:event_MataTutup_IconMousePressed

    private void KataSandi_TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_KataSandi_TextFieldFocusGained
        KataSandi_Label.setVisible(false);
    }//GEN-LAST:event_KataSandi_TextFieldFocusGained

    private void KataSandi_TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_KataSandi_TextFieldFocusLost
        if(KataSandi_TextField.getText().length() == 0){
            KataSandi_Label.setVisible(true);
        }
    }//GEN-LAST:event_KataSandi_TextFieldFocusLost

    private void KataSandi_TextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KataSandi_TextFieldKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_UP){
            Email_TextField.requestFocus();
        }else if(evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_ENTER){ // Kondisi apabila menggunakan tombol down atau tombol enter pada keyboard
            Message_TextField.requestFocus(); // Menuju JTextField yang berada dibawahnya
        }
    }//GEN-LAST:event_KataSandi_TextFieldKeyPressed

    private void Facebook_Klik1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Facebook_Klik1MouseReleased
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Facebook Btn.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Facebook_Klik1.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Facebook_Klik1MouseReleased

    private void Facebook_Klik1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Facebook_Klik1MousePressed
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Facebook Btn Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Facebook_Klik1.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Facebook_Klik1MousePressed

    private void Facebook_Klik1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Facebook_Klik1MouseExited
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Facebook Btn.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Facebook_Klik1.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Facebook_Klik1MouseExited

    private void Facebook_Klik1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Facebook_Klik1MouseEntered
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Facebook Btn Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Facebook_Klik1.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Facebook_Klik1MouseEntered

    private void Facebook_Klik1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Facebook_Klik1MouseClicked
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Facebook Btn Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Facebook_Klik1.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/

        Desktop d = Desktop.getDesktop();
        try {
            try {
                d.browse(new URI("https://www.facebook.com/profile.php?id=100069435968445"));
            } catch (IOException ex) {
                Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Facebook_Klik1MouseClicked

    private void Twitter_Klik1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Twitter_Klik1MouseReleased
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Twitter Btn.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Twitter_Klik1.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Twitter_Klik1MouseReleased

    private void Twitter_Klik1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Twitter_Klik1MousePressed
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Twitter Btn Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Twitter_Klik1.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Twitter_Klik1MousePressed

    private void Twitter_Klik1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Twitter_Klik1MouseExited
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Twitter Btn.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Twitter_Klik1.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Twitter_Klik1MouseExited

    private void Twitter_Klik1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Twitter_Klik1MouseEntered
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Twitter Btn Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Twitter_Klik1.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Twitter_Klik1MouseEntered

    private void Twitter_Klik1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Twitter_Klik1MouseClicked
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Twitter Btn Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Twitter_Klik1.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/

        Desktop d = Desktop.getDesktop();
        try {
            try {
                d.browse(new URI("https://twitter.com/BilikPernik"));
            } catch (IOException ex) {
                Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Twitter_Klik1MouseClicked

    private void Instagram_Klik1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Instagram_Klik1MouseReleased
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Instagram Btn.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Instagram_Klik1.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Instagram_Klik1MouseReleased

    private void Instagram_Klik1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Instagram_Klik1MousePressed
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Instagram Btn Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Instagram_Klik1.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Instagram_Klik1MousePressed

    private void Instagram_Klik1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Instagram_Klik1MouseExited
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Instagram Btn.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Instagram_Klik1.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Instagram_Klik1MouseExited

    private void Instagram_Klik1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Instagram_Klik1MouseEntered
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Instagram Btn Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Instagram_Klik1.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Instagram_Klik1MouseEntered

    private void Instagram_Klik1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Instagram_Klik1MouseClicked
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Instagram Btn Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Instagram_Klik1.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/

        Desktop d = Desktop.getDesktop();
        try {
            try {
                d.browse(new URI("https://www.instagram.com/BilikPernik/"));
            } catch (IOException ex) {
                Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Instagram_Klik1MouseClicked

    private void Instagram_Klik2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Instagram_Klik2MouseClicked
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Instagram Btn Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Instagram_Klik2.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/

        Desktop d = Desktop.getDesktop();
        try {
            try {
                d.browse(new URI("https://www.instagram.com/BilikPernik/"));
            } catch (IOException ex) {
                Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Instagram_Klik2MouseClicked

    private void Instagram_Klik2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Instagram_Klik2MouseEntered
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Instagram Btn Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Instagram_Klik2.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Instagram_Klik2MouseEntered

    private void Instagram_Klik2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Instagram_Klik2MouseExited
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Instagram Btn.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Instagram_Klik2.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Instagram_Klik2MouseExited

    private void Instagram_Klik2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Instagram_Klik2MousePressed
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Instagram Btn Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Instagram_Klik2.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Instagram_Klik2MousePressed

    private void Instagram_Klik2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Instagram_Klik2MouseReleased
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Instagram Btn.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Instagram_Klik2.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Instagram_Klik2MouseReleased

    private void Twitter_Klik2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Twitter_Klik2MouseClicked
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Twitter Btn Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Twitter_Klik2.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/

        Desktop d = Desktop.getDesktop();
        try {
            try {
                d.browse(new URI("https://twitter.com/BilikPernik"));
            } catch (IOException ex) {
                Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Twitter_Klik2MouseClicked

    private void Twitter_Klik2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Twitter_Klik2MouseEntered
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Twitter Btn Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Twitter_Klik2.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Twitter_Klik2MouseEntered

    private void Twitter_Klik2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Twitter_Klik2MouseExited
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Twitter Btn.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Twitter_Klik2.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Twitter_Klik2MouseExited

    private void Twitter_Klik2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Twitter_Klik2MousePressed
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Twitter Btn Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Twitter_Klik2.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Twitter_Klik2MousePressed

    private void Twitter_Klik2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Twitter_Klik2MouseReleased
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Twitter Btn.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Twitter_Klik2.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Twitter_Klik2MouseReleased

    private void Facebook_Klik2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Facebook_Klik2MouseClicked
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Facebook Btn Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Facebook_Klik2.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/

        Desktop d = Desktop.getDesktop();
        try {
            try {
                d.browse(new URI("https://www.facebook.com/profile.php?id=100069435968445"));
            } catch (IOException ex) {
                Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Facebook_Klik2MouseClicked

    private void Facebook_Klik2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Facebook_Klik2MouseEntered
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Facebook Btn Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Facebook_Klik2.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Facebook_Klik2MouseEntered

    private void Facebook_Klik2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Facebook_Klik2MouseExited
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Facebook Btn.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Facebook_Klik2.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Facebook_Klik2MouseExited

    private void Facebook_Klik2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Facebook_Klik2MousePressed
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Facebook Btn Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Facebook_Klik2.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Facebook_Klik2MousePressed

    private void Facebook_Klik2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Facebook_Klik2MouseReleased
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Facebook Btn.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Facebook_Klik2.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Facebook_Klik2MouseReleased

    private void Cari_Item_TextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cari_Item_TextFieldMouseClicked
        Search_ScrollPanel.setVisible(true);
        Pencarian_Tabel.setVisible(true);
    }//GEN-LAST:event_Cari_Item_TextFieldMouseClicked

    private void Beli_TombolMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Beli_TombolMouseClicked
        ImageIcon BeliTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        BeliTombol = new ImageIcon("src/Pictures/TransaksiPage/Ayo Belanja 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Beli_Tombol.setIcon(BeliTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
        
          //Menghapus Panel
        Main_Panel.removeAll();
        Main_Panel.repaint();
        Main_Panel.revalidate();

        //Menambah panel
        Main_Panel.add(Transaksi_Panel);
        Main_Panel.repaint();
        Main_Panel.revalidate();
        
        
        NamaProdukTrans_TextField.setText(NamaProduk_TextField.getText());
        HargaProdukTrans_TextField.setText(Harga_TextField.getText());
        BanyakBeliTrans_TextField.setText(Banyak_TextField.getText());
        TotalHargaTrans_TextField.setText(Total_TextField.getText());
        
        String NamaProduk = NamaProduk_TextField.getText();
        PreparedStatement cmd;
        ResultSet rs;
        String sqs = "select id_seller from tb_produk where nama_produk = '"+NamaProduk+"'";
        try {
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sqs);
            rs =  cmd.executeQuery();
            if(rs.next()){
                byte[] foto=null;
                Seller_ID.setText(rs.getString("id_seller"));
            }
            else{
                JOptionPane.showMessageDialog(null, "DATA NOT FOUND");
            }
       
        } catch (Exception e) {
        }
        
        jasbar();
        jaskir();
    }//GEN-LAST:event_Beli_TombolMouseClicked

    private void Beli_TombolMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Beli_TombolMouseEntered
        ImageIcon BeliTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        BeliTombol = new ImageIcon("src/Pictures/TransaksiPage/Ayo Belanja 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Beli_Tombol.setIcon(BeliTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Beli_TombolMouseEntered

    private void Beli_TombolMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Beli_TombolMouseExited
        ImageIcon BeliTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        BeliTombol = new ImageIcon("src/Pictures/TransaksiPage/Ayo Belanja 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Beli_Tombol.setIcon(BeliTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Beli_TombolMouseExited

    private void Beli_TombolMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Beli_TombolMousePressed
        ImageIcon BeliTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        BeliTombol = new ImageIcon("src/Pictures/TransaksiPage/Ayo Belanja 4.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Beli_Tombol.setIcon(BeliTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Beli_TombolMousePressed

    private void Beli_TombolMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Beli_TombolMouseReleased
        ImageIcon BeliTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        BeliTombol = new ImageIcon("src/Pictures/TransaksiPage/Ayo Belanja 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Beli_Tombol.setIcon(BeliTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Beli_TombolMouseReleased

    private void Chart_Btn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Chart_Btn1MouseClicked
        ImageIcon ChartTombol;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        ChartTombol = new ImageIcon("src/Picture/Effect/clarity_shopping-cart-line effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Chart_Btn1.setIcon(ChartTombol);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
    
        //Menghapus Panel
        Main_Panel.removeAll();
        Main_Panel.repaint();
        Main_Panel.revalidate();
          
        //Menambah panel
        Main_Panel.add(Keranjang_Panel);
        Main_Panel.repaint();
        Main_Panel.revalidate();
               
        jLabel34.setVisible(true);
        jScrollPane4.setVisible(true);
        tb_cart.setVisible(true);
        jLabel27.setVisible(false);
        Ayo_Belanja2_Btn.setVisible(false);
        
        
        String userID = Pengguna_ID.getText();
        String prodID = Produk_ID.getText();
        
        PreparedStatement cmd;
        String sql = "insert into tb_cart(id_pengguna, id_produk) values('"+userID+"', '"+prodID+"')";
        try {
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            cmd.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e); /*Kondisi apabila terjadi kesalahan pada saat melakukan input data*/
        }
        
        isianCart();
            
    }//GEN-LAST:event_Chart_Btn1MouseClicked

    private void isianCart(){
        DefaultTableModel tabel = new DefaultTableModel();
        
        tabel.addColumn("Nama Produk");
        tabel.addColumn("Harga Produk");
        
        String IDPengguna = Pengguna_ID.getText();
        
        PreparedStatement cmd;
        ResultSet rs;
        
         try {
            String sql = "call keranjang('"+IDPengguna+"')";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
            while(rs.next()){
                tabel.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2)
                });
            }
            tb_cart.setModel(tabel);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
    }
    
    private void Chart_Btn1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Chart_Btn1MouseEntered
        ImageIcon ChartTombol;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        ChartTombol = new ImageIcon("src/Picture/Effect/clarity_shopping-cart-line effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Chart_Btn1.setIcon(ChartTombol);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Chart_Btn1MouseEntered

    private void Chart_Btn1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Chart_Btn1MouseExited
        ImageIcon ChartTombol;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        ChartTombol = new ImageIcon("src/Picture/Effect/clarity_shopping-cart-line.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Chart_Btn1.setIcon(ChartTombol);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Chart_Btn1MouseExited

    private void Chart_Btn1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Chart_Btn1MousePressed
        ImageIcon ChartTombol;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        ChartTombol = new ImageIcon("src/Picture/Effect/clarity_shopping-cart-line effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Chart_Btn1.setIcon(ChartTombol);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Chart_Btn1MousePressed

    private void Chart_Btn1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Chart_Btn1MouseReleased
        ImageIcon ChartTombol;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        ChartTombol = new ImageIcon("src/Picture/Effect/clarity_shopping-cart-line.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Chart_Btn1.setIcon(ChartTombol);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Chart_Btn1MouseReleased

    private void Wishlist_Btn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Wishlist_Btn1MouseClicked
        Home_Btn_eft2.setVisible(false);
        ImageIcon Btn_Wishlist;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Wishlist = new ImageIcon("src/Picture/Effect/ic_baseline-favorite-border effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Wishlist_Btn1.setIcon(Btn_Wishlist);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        //Menghapus Panel
            Main_Panel.removeAll();
            Main_Panel.repaint();
            Main_Panel.revalidate();
          
            //Menambah panel
            Main_Panel.add(Wishlist_Panel);
            Main_Panel.repaint();
            Main_Panel.revalidate();
        
            
        jLabel35.setVisible(true);
        jScrollPane5.setVisible(true);
        tb_wishlist.setVisible(true);
        jLabel28.setVisible(false);
        Ayo_Belanja2_Btn1.setVisible(false);
        
        
        String userID = Pengguna_ID.getText();
        String prodID = Produk_ID.getText();
        
        PreparedStatement cmd;
        String sql = "insert into tb_wishlist(id_pengguna, id_produk) values('"+userID+"', '"+prodID+"')";
        try {
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            cmd.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e); /*Kondisi apabila terjadi kesalahan pada saat melakukan input data*/
        }
        
        isianWishlist();
    }//GEN-LAST:event_Wishlist_Btn1MouseClicked

    private void isianWishlist(){
        DefaultTableModel tabel = new DefaultTableModel();
        
        tabel.addColumn("Nama Produk");
        tabel.addColumn("Harga Produk");
        
        String IDPengguna = Pengguna_ID.getText();
        
        PreparedStatement cmd;
        ResultSet rs;
        
         try {
            String sql = "call wishlist('"+IDPengguna+"')";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
            while(rs.next()){
                tabel.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2)
                });
            }
            tb_wishlist.setModel(tabel);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
    }
    
    private void Wishlist_Btn1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Wishlist_Btn1MouseEntered
        Home_Btn_eft2.setVisible(false);
        ImageIcon Btn_Wishlist;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Wishlist = new ImageIcon("src/Picture/Effect/ic_baseline-favorite-border effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Wishlist_Btn1.setIcon(Btn_Wishlist);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Wishlist_Btn1MouseEntered

    private void Wishlist_Btn1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Wishlist_Btn1MouseExited
        Home_Btn_eft2.setVisible(false);
        ImageIcon Btn_Wishlist;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Wishlist = new ImageIcon("src/Picture/Effect/ic_baseline-favorite-border.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Wishlist_Btn1.setIcon(Btn_Wishlist);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Wishlist_Btn1MouseExited

    private void Wishlist_Btn1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Wishlist_Btn1MousePressed
        Home_Btn_eft2.setVisible(false);
        ImageIcon Btn_Wishlist;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Wishlist = new ImageIcon("src/Picture/Effect/ic_baseline-favorite-border effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Wishlist_Btn1.setIcon(Btn_Wishlist);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Wishlist_Btn1MousePressed

    private void Wishlist_Btn1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Wishlist_Btn1MouseReleased
        Home_Btn_eft2.setVisible(false);
        ImageIcon Btn_Wishlist;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Wishlist = new ImageIcon("src/Picture/Effect/ic_baseline-favorite-border.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Wishlist_Btn1.setIcon(Btn_Wishlist);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Wishlist_Btn1MouseReleased

    private void Image_CMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Image_CMouseClicked
        //Menghapus Panel   
        Main_Panel.removeAll();
        Main_Panel.repaint();
        Main_Panel.revalidate();
        
        //Menambah panel
        Main_Panel.add(HasilPencarianProduk_Panel);
        Main_Panel.repaint();
        Main_Panel.revalidate();
       
        String ProdName = Produk_C.getText();
        NamaProduk_TextField.setText(ProdName);
        
        PreparedStatement cmd;
        ResultSet rs;
        String sql = "call user_cari_item('"+ProdName+"')";
        try {
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs =  cmd.executeQuery();
            if(rs.next()){
                byte[] foto=null;
                Produk_ID.setText(rs.getString("id_produk"));
                Toko_TextField.setText(rs.getString("nama_seller"));
                Massa_TextField.setText(rs.getString("massa_gr"));
                Panjang_TextField.setText(rs.getString("panjang_cm"));
                Lebar_TextField.setText(rs.getString("lebar_cm"));
                Tinggi_TextField.setText(rs.getString("tinggi_cm"));
                Harga_TextField.setText(rs.getString("harga"));
                byte [] img=rs.getBytes("foto_produk");
                BufferedImage file=ImageIO.read(new ByteArrayInputStream(img));
                ImageIcon icon=new ImageIcon(resize(Produk_Label.getWidth(), Produk_Label.getHeight(), file));
                Produk_Label.setIcon(icon);
            }
            else{
                JOptionPane.showMessageDialog(null, "DATA NOT FOUND");
            }
       
        } catch (Exception e) {
        }
    }//GEN-LAST:event_Image_CMouseClicked

    private void Image_DMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Image_DMouseClicked
        //Menghapus Panel   
        Main_Panel.removeAll();
        Main_Panel.repaint();
        Main_Panel.revalidate();
        
        //Menambah panel
        Main_Panel.add(HasilPencarianProduk_Panel);
        Main_Panel.repaint();
        Main_Panel.revalidate();
       
        String ProdName = Produk_D.getText();
        NamaProduk_TextField.setText(ProdName);
        
        PreparedStatement cmd;
        ResultSet rs;
        String sql = "call user_cari_item('"+ProdName+"')";
        try {
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs =  cmd.executeQuery();
            if(rs.next()){
                byte[] foto=null;
                Produk_ID.setText(rs.getString("id_produk"));
                Toko_TextField.setText(rs.getString("nama_seller"));
                Massa_TextField.setText(rs.getString("massa_gr"));
                Panjang_TextField.setText(rs.getString("panjang_cm"));
                Lebar_TextField.setText(rs.getString("lebar_cm"));
                Tinggi_TextField.setText(rs.getString("tinggi_cm"));
                Harga_TextField.setText(rs.getString("harga"));
                byte [] img=rs.getBytes("foto_produk");
                BufferedImage file=ImageIO.read(new ByteArrayInputStream(img));
                ImageIcon icon=new ImageIcon(resize(Produk_Label.getWidth(), Produk_Label.getHeight(), file));
                Produk_Label.setIcon(icon);
            }
            else{
                JOptionPane.showMessageDialog(null, "DATA NOT FOUND");
            }
       
        } catch (Exception e) {
        }
    }//GEN-LAST:event_Image_DMouseClicked

    private void Image_BMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Image_BMouseClicked
        //Menghapus Panel   
        Main_Panel.removeAll();
        Main_Panel.repaint();
        Main_Panel.revalidate();
        
        //Menambah panel
        Main_Panel.add(HasilPencarianProduk_Panel);
        Main_Panel.repaint();
        Main_Panel.revalidate();
       
        String ProdName = Produk_B.getText();
        NamaProduk_TextField.setText(ProdName);
        
        PreparedStatement cmd;
        ResultSet rs;
        String sql = "call user_cari_item('"+ProdName+"')";
        try {
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs =  cmd.executeQuery();
            if(rs.next()){
                byte[] foto=null;
                Produk_ID.setText(rs.getString("id_produk"));
                Toko_TextField.setText(rs.getString("nama_seller"));
                Massa_TextField.setText(rs.getString("massa_gr"));
                Panjang_TextField.setText(rs.getString("panjang_cm"));
                Lebar_TextField.setText(rs.getString("lebar_cm"));
                Tinggi_TextField.setText(rs.getString("tinggi_cm"));
                Harga_TextField.setText(rs.getString("harga"));
                byte [] img=rs.getBytes("foto_produk");
                BufferedImage file=ImageIO.read(new ByteArrayInputStream(img));
                ImageIcon icon=new ImageIcon(resize(Produk_Label.getWidth(), Produk_Label.getHeight(), file));
                Produk_Label.setIcon(icon);
            }
            else{
                JOptionPane.showMessageDialog(null, "DATA NOT FOUND");
            }
       
        } catch (Exception e) {
        }
    }//GEN-LAST:event_Image_BMouseClicked

    private void Image_AMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Image_AMouseClicked
        //Menghapus Panel   
        Main_Panel.removeAll();
        Main_Panel.repaint();
        Main_Panel.revalidate();
        
        //Menambah panel
        Main_Panel.add(HasilPencarianProduk_Panel);
        Main_Panel.repaint();
        Main_Panel.revalidate();
       
        String ProdName = Produk_A.getText();
        NamaProduk_TextField.setText(ProdName);
        
        PreparedStatement cmd;
        ResultSet rs;
        String sql = "call user_cari_item('"+ProdName+"')";
        try {
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs =  cmd.executeQuery();
            if(rs.next()){
                byte[] foto=null;
                Produk_ID.setText(rs.getString("id_produk"));
                Toko_TextField.setText(rs.getString("nama_seller"));
                Massa_TextField.setText(rs.getString("massa_gr"));
                Panjang_TextField.setText(rs.getString("panjang_cm"));
                Lebar_TextField.setText(rs.getString("lebar_cm"));
                Tinggi_TextField.setText(rs.getString("tinggi_cm"));
                Harga_TextField.setText(rs.getString("harga"));
                byte [] img=rs.getBytes("foto_produk");
                BufferedImage file=ImageIO.read(new ByteArrayInputStream(img));
                ImageIcon icon=new ImageIcon(resize(Produk_Label.getWidth(), Produk_Label.getHeight(), file));
                Produk_Label.setIcon(icon);
            }
            else{
                JOptionPane.showMessageDialog(null, "DATA NOT FOUND");
            }
       
        } catch (Exception e) {
        }
    }//GEN-LAST:event_Image_AMouseClicked

    private void Message_TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Message_TextFieldFocusGained
        Masukkan_Pesan_Label.setVisible(false);
    }//GEN-LAST:event_Message_TextFieldFocusGained

    private void Message_TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Message_TextFieldFocusLost
        if(Message_TextField.getText().length() > 0){ // Ketika jumlah karakter lebih dari nol
            Masukkan_Pesan_Label.setVisible(false);// Jlabel dibuat menghilang
        }else{
            Masukkan_Pesan_Label.setVisible(true);// Jlabel dibuat muncul kembali
        }
    }//GEN-LAST:event_Message_TextFieldFocusLost

    private void Message_TextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Message_TextFieldKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_UP ){ // Kondisi apabila menggunakan tombol up pada keyboard
            KataSandi_TextField.requestFocus(); // Menuju JTextField yang berada diatasnya
        }
    }//GEN-LAST:event_Message_TextFieldKeyPressed

    private void Next_TombolMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Next_TombolMouseClicked
        ImageIcon NextTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        NextTombol = new ImageIcon("src/Pictures/TransaksiPage/Berkutnya Beli Btn Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Next_Tombol.setIcon(NextTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
        
          //Menghapus Panel
        Main_Panel.removeAll();
        Main_Panel.repaint();
        Main_Panel.revalidate();

        //Menambah panel
        Main_Panel.add(Transaksi2_Panel);
        Main_Panel.repaint();
        Main_Panel.revalidate();
        
        String q = Pengguna_ID.getText();
        String w = Seller_ID.getText();
        int r = JasBar_CB.getSelectedIndex();
        int t = JasKir_CB.getSelectedIndex();
        String u = TotalHargaTrans_TextField.getText();
        String z = Transaksi_TGL.getText();
        
        PreparedStatement cmd;
        ResultSet rs;
        
        try {
            String sql = "insert into tb_transaksi(id_pengguna, id_seller, id_mitra_bayar, id_mitra_kirim, total, tanggal) values('"+q+"', '"+w+"', '"+r+"', '"+t+"', '"+u+"', now())";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            cmd.executeUpdate();
            JasBar_CB.setSelectedItem("Pilih Metode Pembayaran");
            JasKir_CB.setSelectedItem("Pilih Metode Pengiriman");
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e); /*Kondisi apabila terjadi kesalahan pada saat melakukan input data*/
        }
        

        String sqs = "select tanggal from tb_transaksi where tanggal like now()";
        try {
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sqs);
            rs =  cmd.executeQuery();
            if(rs.next()){
                Transaksi_TGL.setText(rs.getString("tanggal"));
            }
            else{
                JOptionPane.showMessageDialog(null, "DATA NOT FOUND");
            }
       
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e); /*Kondisi apabila terjadi kesalahan pada saat melakukan input data*/
        }
        
        String sqr = "select id_transaksi from tb_transaksi where id_pengguna = '"+q+"' and tanggal like now()";
        try {
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sqr);
            rs =  cmd.executeQuery();
            if(rs.next()){
                Transaksi_ID.setText(rs.getString("id_transaksi"));
            }
            else{
                JOptionPane.showMessageDialog(null, "DATA NOT FOUND");
            }
       
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e); /*Kondisi apabila terjadi kesalahan pada saat melakukan input data*/
        }
    }//GEN-LAST:event_Next_TombolMouseClicked

    private void Next_TombolMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Next_TombolMouseEntered
        ImageIcon NextTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        NextTombol = new ImageIcon("src/Pictures/TransaksiPage/Berkutnya Beli Btn Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Next_Tombol.setIcon(NextTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Next_TombolMouseEntered

    private void Next_TombolMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Next_TombolMouseExited
        ImageIcon NextTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        NextTombol = new ImageIcon("src/Pictures/TransaksiPage/Berkutnya Beli Btn.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Next_Tombol.setIcon(NextTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Next_TombolMouseExited

    private void Next_TombolMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Next_TombolMousePressed
       ImageIcon NextTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        NextTombol = new ImageIcon("src/Pictures/TransaksiPage/Berkutnya Beli Btn Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Next_Tombol.setIcon(NextTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Next_TombolMousePressed

    private void Next_TombolMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Next_TombolMouseReleased
        ImageIcon NextTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        NextTombol = new ImageIcon("src/Pictures/TransaksiPage/Berkutnya Beli Btn.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Next_Tombol.setIcon(NextTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Next_TombolMouseReleased

    private void Next2_TombolMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Next2_TombolMouseClicked
        ImageIcon NextTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        NextTombol = new ImageIcon("src/Pictures/TransaksiPage/Berkutnya Cetak Btn Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Next2_Tombol.setIcon(NextTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
        
          //Menghapus Panel
        Main_Panel.removeAll();
        Main_Panel.repaint();
        Main_Panel.revalidate();

        //Menambah panel
        Main_Panel.add(Transaksi3_Panel);
        Main_Panel.repaint();
        Main_Panel.revalidate();
        
        String h = Transaksi_ID.getText();
        String j = Produk_ID.getText();
        String k = BanyakBeliTrans_TextField.getText();
        
        PreparedStatement cmd;
        ResultSet rs;
        
        try {
            String sql = "insert into tb_detail_transaksi(id_transaksi, id_produk, banyak_beli) values('"+h+"', '"+j+"', '"+k+"')";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            cmd.executeUpdate();
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e); /*Kondisi apabila terjadi kesalahan pada saat melakukan input data*/
        }
    }//GEN-LAST:event_Next2_TombolMouseClicked

    private void Next2_TombolMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Next2_TombolMouseEntered
        ImageIcon NextTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        NextTombol = new ImageIcon("src/Pictures/TransaksiPage/Berkutnya Cetak Btn Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Next2_Tombol.setIcon(NextTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Next2_TombolMouseEntered

    private void Next2_TombolMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Next2_TombolMouseExited
        ImageIcon NextTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        NextTombol = new ImageIcon("src/Pictures/TransaksiPage/Berkutnya Cetak Btn.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Next2_Tombol.setIcon(NextTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Next2_TombolMouseExited

    private void Next2_TombolMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Next2_TombolMousePressed
        ImageIcon NextTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        NextTombol = new ImageIcon("src/Pictures/TransaksiPage/Berkutnya Cetak Btn Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Next2_Tombol.setIcon(NextTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Next2_TombolMousePressed

    private void Next2_TombolMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Next2_TombolMouseReleased
        ImageIcon NextTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        NextTombol = new ImageIcon("src/Pictures/TransaksiPage/Berkutnya Cetak Btn.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Next2_Tombol.setIcon(NextTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Next2_TombolMouseReleased

    private void Cetak_TombolMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cetak_TombolMouseClicked
        ImageIcon CetakTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        CetakTombol = new ImageIcon("src/Pictures/TransaksiPage/Cetak Btn Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Cetak_Tombol.setIcon(CetakTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
        
        Connection cnt;

        try {
            cnt = (Connection) BK_Connection.getBilik_Pernik_Connect();
            HashMap hash = new HashMap();
            hash.put("code_user",Transaksi_ID.getText());
            String Data_List = ("D:\\UNUD2020\\KULIAH SEMESTER II\\PEMROGRAMAN BERORIENTASI OBJEK (E)\\Bilik-Pernik-by-Herry-X-Rama\\Bilik-Pernik-by-Herry-X-Rama\\Bilik Pernik\\src\\Data_Print\\Bilik_Pernik_Invoice.jrxml");
            JasperReport rpt = JasperCompileManager.compileReport(Data_List);
            JasperPrint print = JasperFillManager.fillReport(rpt,hash,cnt);
            JasperViewer.viewReport(print,false);

        } catch (JRException e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                "Data tidak data tercetak"+"\n"+e.getMessage(),"Cetak Data",javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_Cetak_TombolMouseClicked

    private void Cetak_TombolMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cetak_TombolMouseEntered
        ImageIcon CetakTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        CetakTombol = new ImageIcon("src/Pictures/TransaksiPage/Cetak Btn Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Cetak_Tombol.setIcon(CetakTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Cetak_TombolMouseEntered

    private void Cetak_TombolMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cetak_TombolMouseExited
        ImageIcon CetakTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        CetakTombol = new ImageIcon("src/Pictures/TransaksiPage/Cetak Btn.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Cetak_Tombol.setIcon(CetakTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Cetak_TombolMouseExited

    private void Cetak_TombolMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cetak_TombolMousePressed
        ImageIcon CetakTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        CetakTombol = new ImageIcon("src/Pictures/TransaksiPage/Cetak Btn Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Cetak_Tombol.setIcon(CetakTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Cetak_TombolMousePressed

    private void Cetak_TombolMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cetak_TombolMouseReleased
        ImageIcon CetakTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        CetakTombol = new ImageIcon("src/Pictures/TransaksiPage/Cetak Btn.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Cetak_Tombol.setIcon(CetakTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Cetak_TombolMouseReleased

    private void TA_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TA_PanelMouseClicked
        //Menghapus Panel   
        Main_Panel.removeAll();
        Main_Panel.repaint();
        Main_Panel.revalidate();
        
        //Menambah panel
        Main_Panel.add(HasilPencarianProduk_Panel);
        Main_Panel.repaint();
        Main_Panel.revalidate();
       
        String ProdName = Produk_TA.getText();
        NamaProduk_TextField.setText(ProdName);
        
        PreparedStatement cmd;
        ResultSet rs;
        String sql = "call user_cari_item('"+ProdName+"')";
        try {
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs =  cmd.executeQuery();
            if(rs.next()){
                byte[] foto=null;
                Produk_ID.setText(rs.getString("id_produk"));
                Toko_TextField.setText(rs.getString("nama_seller"));
                Massa_TextField.setText(rs.getString("massa_gr"));
                Panjang_TextField.setText(rs.getString("panjang_cm"));
                Lebar_TextField.setText(rs.getString("lebar_cm"));
                Tinggi_TextField.setText(rs.getString("tinggi_cm"));
                Harga_TextField.setText(rs.getString("harga"));
                byte [] img=rs.getBytes("foto_produk");
                BufferedImage file=ImageIO.read(new ByteArrayInputStream(img));
                ImageIcon icon=new ImageIcon(resize(Produk_Label.getWidth(), Produk_Label.getHeight(), file));
                Produk_Label.setIcon(icon);
            }
            else{
                JOptionPane.showMessageDialog(null, "DATA NOT FOUND");
            }
       
        } catch (Exception e) {
        }
    }//GEN-LAST:event_TA_PanelMouseClicked

    private void Image_TBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Image_TBMouseClicked
        //Menghapus Panel   
        Main_Panel.removeAll();
        Main_Panel.repaint();
        Main_Panel.revalidate();
        
        //Menambah panel
        Main_Panel.add(HasilPencarianProduk_Panel);
        Main_Panel.repaint();
        Main_Panel.revalidate();
       
        String ProdName = Produk_TB.getText();
        NamaProduk_TextField.setText(ProdName);
        
        PreparedStatement cmd;
        ResultSet rs;
        String sql = "call user_cari_item('"+ProdName+"')";
        try {
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs =  cmd.executeQuery();
            if(rs.next()){
                byte[] foto=null;
                Produk_ID.setText(rs.getString("id_produk"));
                Toko_TextField.setText(rs.getString("nama_seller"));
                Massa_TextField.setText(rs.getString("massa_gr"));
                Panjang_TextField.setText(rs.getString("panjang_cm"));
                Lebar_TextField.setText(rs.getString("lebar_cm"));
                Tinggi_TextField.setText(rs.getString("tinggi_cm"));
                Harga_TextField.setText(rs.getString("harga"));
                byte [] img=rs.getBytes("foto_produk");
                BufferedImage file=ImageIO.read(new ByteArrayInputStream(img));
                ImageIcon icon=new ImageIcon(resize(Produk_Label.getWidth(), Produk_Label.getHeight(), file));
                Produk_Label.setIcon(icon);
            }
            else{
                JOptionPane.showMessageDialog(null, "DATA NOT FOUND");
            }
       
        } catch (Exception e) {
        }
    }//GEN-LAST:event_Image_TBMouseClicked

    private void Image_TCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Image_TCMouseClicked
        //Menghapus Panel   
        Main_Panel.removeAll();
        Main_Panel.repaint();
        Main_Panel.revalidate();
        
        //Menambah panel
        Main_Panel.add(HasilPencarianProduk_Panel);
        Main_Panel.repaint();
        Main_Panel.revalidate();
       
        String ProdName = Produk_TC.getText();
        NamaProduk_TextField.setText(ProdName);
        
        PreparedStatement cmd;
        ResultSet rs;
        String sql = "call user_cari_item('"+ProdName+"')";
        try {
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs =  cmd.executeQuery();
            if(rs.next()){
                byte[] foto=null;
                Produk_ID.setText(rs.getString("id_produk"));
                Toko_TextField.setText(rs.getString("nama_seller"));
                Massa_TextField.setText(rs.getString("massa_gr"));
                Panjang_TextField.setText(rs.getString("panjang_cm"));
                Lebar_TextField.setText(rs.getString("lebar_cm"));
                Tinggi_TextField.setText(rs.getString("tinggi_cm"));
                Harga_TextField.setText(rs.getString("harga"));
                byte [] img=rs.getBytes("foto_produk");
                BufferedImage file=ImageIO.read(new ByteArrayInputStream(img));
                ImageIcon icon=new ImageIcon(resize(Produk_Label.getWidth(), Produk_Label.getHeight(), file));
                Produk_Label.setIcon(icon);
            }
            else{
                JOptionPane.showMessageDialog(null, "DATA NOT FOUND");
            }
       
        } catch (Exception e) {
        }
    }//GEN-LAST:event_Image_TCMouseClicked

    private void tb_wishlistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_wishlistMouseClicked
        //Menghapus Panel   
        Main_Panel.removeAll();
        Main_Panel.repaint();
        Main_Panel.revalidate();
        
        //Menambah panel
        Main_Panel.add(HasilPencarianProduk_Panel);
        Main_Panel.repaint();
        Main_Panel.revalidate();
        
        int i = tb_wishlist.getSelectedRow();
        TableModel model = tb_wishlist.getModel();
        NamaProduk_TextField.setText(model.getValueAt(i, 0).toString());
        
        String ProdName = NamaProduk_TextField.getText();
        
        PreparedStatement cmd;
        ResultSet rs;
        String sql = "call cari_wishcart('"+ProdName+"')";
        try {
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs =  cmd.executeQuery();
            if(rs.next()){
                byte[] foto=null;
                Produk_ID.setText(rs.getString("id_produk"));
                Toko_TextField.setText(rs.getString("nama_seller"));
                Massa_TextField.setText(rs.getString("massa_gr"));
                Panjang_TextField.setText(rs.getString("panjang_cm"));
                Lebar_TextField.setText(rs.getString("lebar_cm"));
                Tinggi_TextField.setText(rs.getString("tinggi_cm"));
                Harga_TextField.setText(rs.getString("harga"));
                byte [] img=rs.getBytes("foto_produk");
                BufferedImage file=ImageIO.read(new ByteArrayInputStream(img));
                ImageIcon icon=new ImageIcon(resize(Produk_Label.getWidth(), Produk_Label.getHeight(), file));
                Produk_Label.setIcon(icon);
            }
            else{
                JOptionPane.showMessageDialog(null, "DATA NOT FOUND");
            }
       
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tb_wishlistMouseClicked

    private void tb_cartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_cartMouseClicked
        //Menghapus Panel   
        Main_Panel.removeAll();
        Main_Panel.repaint();
        Main_Panel.revalidate();
        
        //Menambah panel
        Main_Panel.add(HasilPencarianProduk_Panel);
        Main_Panel.repaint();
        Main_Panel.revalidate();
        
        int i = tb_cart.getSelectedRow();
        TableModel model = tb_cart.getModel();
        NamaProduk_TextField.setText(model.getValueAt(i, 0).toString());
        
        String ProdName = NamaProduk_TextField.getText();
        
        PreparedStatement cmd;
        ResultSet rs;
        String sql = "call cari_wishcart('"+ProdName+"')";
        try {
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs =  cmd.executeQuery();
            if(rs.next()){
                byte[] foto=null;
                Produk_ID.setText(rs.getString("id_produk"));
                Toko_TextField.setText(rs.getString("nama_seller"));
                Massa_TextField.setText(rs.getString("massa_gr"));
                Panjang_TextField.setText(rs.getString("panjang_cm"));
                Lebar_TextField.setText(rs.getString("lebar_cm"));
                Tinggi_TextField.setText(rs.getString("tinggi_cm"));
                Harga_TextField.setText(rs.getString("harga"));
                byte [] img=rs.getBytes("foto_produk");
                BufferedImage file=ImageIO.read(new ByteArrayInputStream(img));
                ImageIcon icon=new ImageIcon(resize(Produk_Label.getWidth(), Produk_Label.getHeight(), file));
                Produk_Label.setIcon(icon);
            }
            else{
                JOptionPane.showMessageDialog(null, "DATA NOT FOUND");
            }
       
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tb_cartMouseClicked
 
    public void hasilnya(){
        String A = Harga_TextField.getText();
        String B = Banyak_TextField.getText();
        
        int AA = Integer.parseInt(A);
        int BB = Integer.parseInt(B);
        
        int hasil = AA * BB;
        
        String hasil1 = Integer.toString(hasil);
        
        Total_TextField.setText(hasil1);
    }
    
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home_Page().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane About_Us_Panel;
    private javax.swing.JLabel AlamatHome_Text;
    private javax.swing.JLabel AlamatHome_Text1;
    private javax.swing.JTextField AlamatHome_TextField;
    private javax.swing.JTextField AlamatHome_TextField1;
    private javax.swing.JLabel Ayo_Belanja2_Btn;
    private javax.swing.JLabel Ayo_Belanja2_Btn1;
    private javax.swing.JLabel Back_Btn;
    private javax.swing.JLabel Back_Btn1;
    private javax.swing.JTextField BanyakBeliTrans_TextField;
    private javax.swing.JLabel Banyak_Text;
    private javax.swing.JTextField Banyak_TextField;
    private javax.swing.JLabel Belanja_Btn;
    private javax.swing.JLabel Belanja_Btn1;
    private javax.swing.JScrollPane Belanja_Panel;
    private javax.swing.JLabel Beli_Tombol;
    private javax.swing.JLabel Bullet1;
    private javax.swing.JLabel Bullet2;
    private javax.swing.JLabel Bullet3;
    private javax.swing.JLabel Bullet_Item1;
    private javax.swing.JLabel Bullet_Item2;
    private javax.swing.JLabel Bullet_Item3;
    private javax.swing.JLabel Buy_Now_Btn;
    private javax.swing.JLabel Buy_Now_Btn1;
    private javax.swing.JLabel Buy_Now_Btn2;
    private javax.swing.JLabel Buy_Now_Btn3;
    private javax.swing.JTextField Cari_Item_TextField;
    private javax.swing.JLabel Cetak_Tombol;
    private javax.swing.JLabel Chart_Btn;
    private javax.swing.JLabel Chart_Btn1;
    private javax.swing.JLabel Dekorasi;
    private javax.swing.JLabel EmailHome_Text;
    private javax.swing.JLabel EmailHome_Text1;
    public static final javax.swing.JTextField EmailHome_TextField = new javax.swing.JTextField();
    private javax.swing.JTextField EmailHome_TextField1;
    private javax.swing.JLabel Email_Label;
    private javax.swing.JTextField Email_TextField;
    private javax.swing.JLabel Facebook_Klik;
    private javax.swing.JLabel Facebook_Klik1;
    private javax.swing.JLabel Facebook_Klik2;
    private javax.swing.JTextField Filename_Text;
    private javax.swing.JLabel GantiFoto_Tombol;
    private javax.swing.JTextField HargaProdukTrans_TextField;
    private javax.swing.JTextField Harga_A;
    private javax.swing.JTextField Harga_B;
    private javax.swing.JTextField Harga_C;
    private javax.swing.JTextField Harga_D;
    private javax.swing.JTextField Harga_TA;
    private javax.swing.JTextField Harga_TB;
    private javax.swing.JTextField Harga_TC;
    private javax.swing.JTextField Harga_TextField;
    private javax.swing.JScrollPane HasilPencarianProduk_Panel;
    private javax.swing.JLabel Home_Btn;
    private javax.swing.JLabel Home_Btn_eft2;
    private javax.swing.JScrollPane Home_Panel;
    private javax.swing.JLabel Image_A;
    private javax.swing.JLabel Image_A1;
    private javax.swing.JLabel Image_B;
    private javax.swing.JLabel Image_B1;
    private javax.swing.JLabel Image_C;
    private javax.swing.JLabel Image_C1;
    private javax.swing.JLabel Image_D;
    private javax.swing.JLabel Image_Label;
    private javax.swing.JLabel Image_TA;
    private javax.swing.JLabel Image_TB;
    private javax.swing.JLabel Image_TC;
    private javax.swing.JLabel Instagram_Klik;
    private javax.swing.JLabel Instagram_Klik1;
    private javax.swing.JLabel Instagram_Klik2;
    private javax.swing.JTextField Jalan_A;
    private javax.swing.JTextField Jalan_B;
    private javax.swing.JTextField Jalan_C;
    private javax.swing.JComboBox<String> JasBar_CB;
    private javax.swing.JComboBox<String> JasKir_CB;
    private javax.swing.JLabel Kado;
    private javax.swing.JLabel KataSandi_Label;
    private javax.swing.JPasswordField KataSandi_TextField;
    private javax.swing.JScrollPane Keranjang_Panel;
    private javax.swing.JLabel Kirim_Pesan_Btn;
    private javax.swing.JLabel Kontak_Btn;
    private javax.swing.JScrollPane Kontak_Panel;
    private javax.swing.JTextField Lebar_TextField;
    public static final javax.swing.JLabel LogOut_Btn = new javax.swing.JLabel();
    private javax.swing.JLabel Logo_Picture;
    private javax.swing.JPanel Main_Panel;
    private javax.swing.JTextField Massa_TextField;
    private javax.swing.JLabel Masukkan_Pesan_Label;
    private javax.swing.JLabel MataTutup_Icon;
    private javax.swing.JLabel Merchandise;
    private javax.swing.JTextArea Message_TextField;
    private javax.swing.JLabel More_Btn;
    private javax.swing.JLabel More_Btn1;
    private javax.swing.JLabel NamaPenggunaHome_Text;
    private javax.swing.JLabel NamaPenggunaHome_Text1;
    public static final javax.swing.JTextField NamaPenggunaHome_TextField = new javax.swing.JTextField();
    private javax.swing.JTextField NamaPenggunaHome_TextField1;
    public static final javax.swing.JTextField NamaPenggunaProfile_TextField = new javax.swing.JTextField();
    private javax.swing.JTextField NamaProdukTrans_TextField;
    private javax.swing.JTextField NamaProduk_TextField;
    private javax.swing.JLabel Next2_Tombol;
    private javax.swing.JLabel Next_Btn;
    private javax.swing.JLabel Next_Btn1;
    private javax.swing.JLabel Next_Tombol;
    private javax.swing.JPanel Opening1_Panel;
    private javax.swing.JPanel Opening1_Panel1;
    private javax.swing.JPanel Opening2_Panel;
    private javax.swing.JPanel Opening2_Panel1;
    private javax.swing.JPanel Opening3_Panel;
    private javax.swing.JPanel Opening3_Panel1;
    private javax.swing.JPanel Opening_Item_Panel;
    private javax.swing.JPanel Opening_Shop_Panel;
    private javax.swing.JScrollPane Our_Channels_Panel;
    private javax.swing.JScrollPane Our_Item_Panel;
    private javax.swing.JTextField Panjang_TextField;
    private javax.swing.JLabel Parsel;
    private javax.swing.JTable Pencarian_Tabel;
    private javax.swing.JTextField Pengguna_ID;
    private javax.swing.JLabel Prev_Btn;
    private javax.swing.JLabel Prev_Btn1;
    private javax.swing.JLabel Produk1_Btn;
    private javax.swing.JLabel Produk2_Btn;
    private javax.swing.JLabel Produk3_Btn;
    private javax.swing.JLabel ProdukA;
    private javax.swing.JLabel ProdukA2;
    private javax.swing.JDesktopPane ProdukA_Panel;
    private javax.swing.JLabel ProdukB;
    private javax.swing.JLabel ProdukB2;
    private javax.swing.JDesktopPane ProdukB_Panel;
    private javax.swing.JLabel ProdukC;
    private javax.swing.JLabel ProdukC2;
    private javax.swing.JDesktopPane ProdukC_Panel;
    private javax.swing.JLabel ProdukD;
    private javax.swing.JLabel ProdukD2;
    private javax.swing.JDesktopPane ProdukD_Panel;
    private javax.swing.JLabel ProdukE2;
    private javax.swing.JLabel ProdukF2;
    private javax.swing.JTextField Produk_A;
    private javax.swing.JTextField Produk_B;
    private javax.swing.JTextField Produk_C;
    private javax.swing.JTextField Produk_D;
    private javax.swing.JLabel Produk_ID;
    private javax.swing.JLabel Produk_Label;
    private javax.swing.JTextField Produk_TA;
    private javax.swing.JTextField Produk_TB;
    private javax.swing.JTextField Produk_TC;
    private javax.swing.JLabel Search_Bar_Label;
    private javax.swing.JLabel Search_Btn;
    private javax.swing.JScrollPane Search_ScrollPanel;
    private javax.swing.JLabel Selengkapnya_Btn;
    private javax.swing.JTextField Seller_ID;
    private javax.swing.JLabel Simpan_Tombol;
    private javax.swing.JLabel Social_Media1;
    private javax.swing.JLabel Social_Media2;
    private javax.swing.JLabel Social_Media3;
    private javax.swing.JLabel Social_Media4;
    private javax.swing.JLabel Social_Media5;
    private javax.swing.JLabel Social_Media6;
    private javax.swing.JLabel Souvernir;
    private javax.swing.JDesktopPane TA_Panel;
    private javax.swing.JDesktopPane TB_Panel;
    private javax.swing.JDesktopPane TC_Panel;
    private javax.swing.JLabel TelpHome_Text;
    private javax.swing.JLabel TelpHome_Text1;
    private javax.swing.JTextField TelpHome_TextField;
    private javax.swing.JTextField TelpHome_TextField1;
    private javax.swing.JLabel Telusuri_Produk_Label;
    private javax.swing.JLabel Tentang_Btn;
    private javax.swing.JTextField Tinggi_TextField;
    private javax.swing.JLabel TokoA;
    private javax.swing.JLabel TokoA2;
    private javax.swing.JDesktopPane TokoA_Panel;
    private javax.swing.JLabel TokoB;
    private javax.swing.JLabel TokoB2;
    private javax.swing.JDesktopPane TokoB_Panel;
    private javax.swing.JLabel TokoC;
    private javax.swing.JLabel TokoC2;
    private javax.swing.JDesktopPane TokoC_Panel;
    private javax.swing.JLabel TokoD2;
    private javax.swing.JLabel TokoE2;
    private javax.swing.JLabel TokoF2;
    private javax.swing.JLabel TokoG2;
    private javax.swing.JLabel TokoH2;
    private javax.swing.JTextField Toko_A;
    private javax.swing.JTextField Toko_B;
    private javax.swing.JTextField Toko_C;
    private javax.swing.JTextField Toko_TextField;
    private javax.swing.JTextField TotalHargaTrans_TextField;
    private javax.swing.JTextField Total_TextField;
    private javax.swing.JScrollPane Transaksi2_Panel;
    private javax.swing.JScrollPane Transaksi3_Panel;
    private javax.swing.JTextField Transaksi_ID;
    private javax.swing.JScrollPane Transaksi_Panel;
    private javax.swing.JTextField Transaksi_TGL;
    private javax.swing.JLabel Twitter_Klik;
    private javax.swing.JLabel Twitter_Klik1;
    private javax.swing.JLabel Twitter_Klik2;
    private javax.swing.JScrollPane UserPagePanel;
    private javax.swing.JLabel User_Btn;
    private javax.swing.JLabel User_Label;
    private javax.swing.JLabel Wishlist_Btn;
    private javax.swing.JLabel Wishlist_Btn1;
    private javax.swing.JScrollPane Wishlist_Panel;
    private javax.swing.JDesktopPane jDesktopPane;
    private javax.swing.JDesktopPane jDesktopPane1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tb_cart;
    private javax.swing.JTable tb_wishlist;
    // End of variables declaration//GEN-END:variables
 
}

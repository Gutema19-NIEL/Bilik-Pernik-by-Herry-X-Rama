/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Seller_Page;

import View_User_Page.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import Bilik_Pernik_Connection.BK_Connection;
import View_Opening_Page.Opening_Page_Pt_2;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author asuna
 */
public class Seller_Page extends javax.swing.JFrame {
    public String Namaa;
    public String Surell;
    public String pass;
    Image images;
    byte[] photo=null;
    String filename=null;
    /**
     * Creates new form Home_Page
     */
    public Seller_Page() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        kategori();
    }
    
    public Seller_Page(String Name, String Surel,String KataSandi) {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Namaa = Name;
        Surell = Surel;
        pass = KataSandi;
        
        NamaPenggunaProfile_TextField.setText(Namaa);
        NamaPenggunaHome_TextField.setText(Namaa);
        EmailHome_TextField.setText(Surell); 
    }

    
    Seller_Page(String Coba) {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        String Pass = Coba;
        PreparedStatement cmd;
        ResultSet rs;
        String query = "select * from tb_seller where password_seller = '"+Pass+"'";
        try {
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(query);
            rs =  cmd.executeQuery();
            if(rs.next()){
                
                Penjual_ID.setText(Integer.toString(rs.getInt("id_seller")));
                NamaPenggunaHome_TextField1.setText(rs.getString("nama_seller"));
                NamaPenggunaProfile_TextField.setText(rs.getString("username_seller"));
                EmailHome_TextField1.setText(rs.getString("email_seller"));
                TelpHome_TextField1.setText(rs.getString("no_telp_seller"));
                AlamatHome_TextField1.setText(rs.getString("alamat_seller"));
                byte [] img=rs.getBytes("foto_seller");
                BufferedImage file=ImageIO.read(new ByteArrayInputStream(img));
                ImageIcon icon=new ImageIcon(resize(Image_Label.getWidth(), Image_Label.getHeight(), file));
                Image_Label.setIcon(icon);
                
                NamaPenggunaHome_TextField.setText(rs.getString("nama_seller"));
                EmailHome_TextField.setText(rs.getString("email_seller"));
                TelpHome_TextField.setText(rs.getString("no_telp_seller"));
                AlamatHome_TextField.setText(rs.getString("alamat_seller"));
                
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
        
    }

    Seller_Page(String Name, String Surel) {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Namaa = Name;
        Surell = Surel;
        NamaPenggunaProfile_TextField.setText(Namaa);
        NamaPenggunaHome_TextField.setText(Namaa);
        EmailHome_TextField.setText(Surell);
        NamaPenggunaHome_Text.setVisible(false);
        EmailHome_Text.setVisible(false);
    }
    
    private void kategori(){
        Kategori_Item.removeAllItems();
        Kategori_Item.addItem("Tentukkan Kategori Produk");
        
        Statement cmd;
        ResultSet rs;
        String sql = "select*from tb_kategori";
        
        try {  
            cmd = BK_Connection.getBilik_Pernik_Connect().createStatement();
            rs = cmd.executeQuery(sql);  
            while(rs.next()){
               Kategori_Item.addItem(rs.getString(2));
            }
                
        } catch (Exception e) {
                System.out.println("Error\n"+e);
        }
    }
    
    private Image resize(int w, int h, Image img){
        BufferedImage bufimg=new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D dimensi=bufimg.createGraphics();
        dimensi.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        dimensi.drawImage(img,0,0,w,h,null);
        dimensi.dispose();
        return bufimg;
    }
    
    private void id_product(String product){
         PreparedStatement cmd;
        ResultSet rs;
        
         try {
            String sql = "select*from tb_produk where nama_produk='"+product+"'";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
            if(rs.next()){
                id_product.setText(rs.getString(1));
            }
         }catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
    }
   
     public void seller_item_data(){
        DefaultTableModel tabel = new DefaultTableModel();
        
        tabel.addColumn("ID Item");
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
        String seller = Penjual_ID.getText();
         try {
            String sql = "call seller_product('"+seller+"')";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
            while(rs.next()){
                tabel.addRow(new Object[]{
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getInt(7),
                    rs.getInt(8),
                    rs.getInt(9),
                    rs.getInt(10),
                    rs.getBytes(11),
                });
            }
            jTable1.setModel(tabel);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
        }
    }
    
    public void search__seller_item( String s_cari){
        DefaultTableModel tabel = new DefaultTableModel();
        
        tabel.addColumn("ID Item");
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
        
        String seller = Penjual_ID.getText();
        
        PreparedStatement cmd;
        ResultSet rs;
        
         try {
            String sql = "call seller_cari_item('"+seller+"','"+s_cari+"')";
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            rs = cmd.executeQuery();
            
            while(rs.next()){
                tabel.addRow(new Object[]{
                     rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getInt(7),
                    rs.getInt(8),
                    rs.getInt(9),
                    rs.getInt(10),
                    rs.getBytes(11),
                });
            }
            jTable1.setModel(tabel);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);/*Kondisi apabila terjadi 
            kesalahan pada saat melakukan input data*/
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

        Tentang_Btn = new javax.swing.JLabel();
        Kontak_Btn = new javax.swing.JLabel();
        Belanja_Btn = new javax.swing.JLabel();
        Home_Btn_eft2 = new javax.swing.JLabel();
        Home_Btn = new javax.swing.JLabel();
        Logo_Picture = new javax.swing.JLabel();
        Telusuri_Produk_Label = new javax.swing.JLabel();
        Search_Btn = new javax.swing.JLabel();
        Cari_Item_TextField = new javax.swing.JTextField();
        Search_Bar_Label = new javax.swing.JLabel();
        Main_Panel = new javax.swing.JPanel();
        Home_Panel = new javax.swing.JScrollPane();
        Seacrh_Bar_Panel = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        Image_Label = new javax.swing.JLabel();
        TelpHome_Text1 = new javax.swing.JLabel();
        AlamatHome_Text1 = new javax.swing.JLabel();
        NamaPenggunaProfile_TextField = new javax.swing.JTextField();
        EmailHome_Text1 = new javax.swing.JLabel();
        GantiFoto_Tombol = new javax.swing.JLabel();
        AlamatHome_Text = new javax.swing.JLabel();
        NamaPenggunaHome_Text = new javax.swing.JLabel();
        EmailHome_TextField = new javax.swing.JTextField();
        AlamatHome_TextField = new javax.swing.JTextField();
        TelpHome_TextField = new javax.swing.JTextField();
        TelpHome_Text = new javax.swing.JLabel();
        NamaPenggunaHome_TextField = new javax.swing.JTextField();
        EmailHome_Text = new javax.swing.JLabel();
        Simpan_Tombol = new javax.swing.JLabel();
        Product_Btn = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Penjual_ID = new javax.swing.JLabel();
        Filename_Text = new javax.swing.JTextField();
        Belanja_Panel = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Subkategori_Item = new javax.swing.JComboBox<>();
        Kategori_Item = new javax.swing.JComboBox<>();
        Stok_TextField = new javax.swing.JTextField();
        Stok_Text = new javax.swing.JLabel();
        Nama_Produk_Text_Field = new javax.swing.JTextField();
        Nama_Produk_Text = new javax.swing.JLabel();
        Massa_Text = new javax.swing.JLabel();
        Massa_TextField = new javax.swing.JTextField();
        Panjang_Text = new javax.swing.JLabel();
        Panjang_TextField = new javax.swing.JTextField();
        Lebar_Text = new javax.swing.JLabel();
        Lebar_TextField = new javax.swing.JTextField();
        Tinggi_Text = new javax.swing.JLabel();
        Tinggi_TextField = new javax.swing.JTextField();
        Harga_Text = new javax.swing.JLabel();
        Harga_TextField = new javax.swing.JTextField();
        Foto_Barang_Btn = new javax.swing.JLabel();
        Foto_TextField = new javax.swing.JTextField();
        Foto_Text = new javax.swing.JLabel();
        Simpan_Tombol1 = new javax.swing.JLabel();
        Simpan_Tombol2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        id_product = new javax.swing.JTextField();
        id_sctg = new javax.swing.JTextField();
        Kontak_Panel = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        Instagram_Klik2 = new javax.swing.JLabel();
        Twitter_Klik2 = new javax.swing.JLabel();
        Facebook_Klik2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        About_Us_Panel = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bilik Pernik");
        setBackground(new java.awt.Color(49, 58, 148));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        getContentPane().add(Tentang_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1095, 129, -1, -1));

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
        getContentPane().add(Kontak_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 129, -1, -1));

        Belanja_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Produk Btn.png"))); // NOI18N
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
        getContentPane().add(Belanja_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 129, -1, -1));

        Home_Btn_eft2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Rumah effect 2.png"))); // NOI18N
        getContentPane().add(Home_Btn_eft2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 129, -1, -1));

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
        getContentPane().add(Home_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 129, -1, -1));

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
        getContentPane().add(Logo_Picture, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, -1, -1));

        Telusuri_Produk_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Telusuri produk.png"))); // NOI18N
        getContentPane().add(Telusuri_Produk_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, -1, -1));

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
        getContentPane().add(Cari_Item_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 65, 500, 30));

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
        Home_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Home_PanelMouseEntered(evt);
            }
        });

        Seacrh_Bar_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        NamaPenggunaHome_Text1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Seller Page/Nama Toko.png"))); // NOI18N
        Seacrh_Bar_Panel.add(NamaPenggunaHome_Text1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 1958, -1, -1));

        jDesktopPane1.setMaximumSize(new java.awt.Dimension(481, 688));
        jDesktopPane1.setMinimumSize(new java.awt.Dimension(481, 688));
        jDesktopPane1.setOpaque(false);

        jDesktopPane1.setLayer(Image_Label, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Image_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Image_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Seacrh_Bar_Panel.add(jDesktopPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 1750, 481, 688));

        TelpHome_Text1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Telepon.png"))); // NOI18N
        Seacrh_Bar_Panel.add(TelpHome_Text1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 2120, -1, -1));

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
        Seacrh_Bar_Panel.add(NamaPenggunaHome_TextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 1948, 390, 30));

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
        Seacrh_Bar_Panel.add(EmailHome_TextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 2023, 390, 30));

        AlamatHome_Text1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Alamat.png"))); // NOI18N
        Seacrh_Bar_Panel.add(AlamatHome_Text1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 2203, -1, -1));

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
        Seacrh_Bar_Panel.add(TelpHome_TextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 2113, 390, 30));

        NamaPenggunaProfile_TextField.setFont(new java.awt.Font("Times New Roman", 0, 30)); // NOI18N
        NamaPenggunaProfile_TextField.setForeground(new java.awt.Color(255, 255, 255));
        NamaPenggunaProfile_TextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NamaPenggunaProfile_TextField.setBorder(null);
        NamaPenggunaProfile_TextField.setOpaque(false);
        Seacrh_Bar_Panel.add(NamaPenggunaProfile_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 1520, 500, 30));

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
        Seacrh_Bar_Panel.add(AlamatHome_TextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 2195, 390, 30));

        EmailHome_Text1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Email.png"))); // NOI18N
        Seacrh_Bar_Panel.add(EmailHome_Text1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 2033, -1, -1));

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
        Seacrh_Bar_Panel.add(GantiFoto_Tombol, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 1380, -1, -1));

        AlamatHome_Text.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Alamat.png"))); // NOI18N
        Seacrh_Bar_Panel.add(AlamatHome_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 1500, -1, -1));

        NamaPenggunaHome_Text.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Seller Page/Nama Toko.png"))); // NOI18N
        Seacrh_Bar_Panel.add(NamaPenggunaHome_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 1255, -1, -1));

        EmailHome_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        EmailHome_TextField.setForeground(new java.awt.Color(255, 255, 255));
        EmailHome_TextField.setBorder(null);
        EmailHome_TextField.setCaretColor(new java.awt.Color(255, 255, 255));
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
        Seacrh_Bar_Panel.add(EmailHome_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 1320, 390, 30));

        AlamatHome_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        AlamatHome_TextField.setForeground(new java.awt.Color(255, 255, 255));
        AlamatHome_TextField.setBorder(null);
        AlamatHome_TextField.setCaretColor(new java.awt.Color(255, 255, 255));
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
        Seacrh_Bar_Panel.add(AlamatHome_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 1495, 390, 30));

        TelpHome_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        TelpHome_TextField.setForeground(new java.awt.Color(255, 255, 255));
        TelpHome_TextField.setBorder(null);
        TelpHome_TextField.setCaretColor(new java.awt.Color(255, 255, 255));
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
        Seacrh_Bar_Panel.add(TelpHome_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 1410, 390, 30));

        TelpHome_Text.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Telepon.png"))); // NOI18N
        Seacrh_Bar_Panel.add(TelpHome_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 1415, -1, -1));

        NamaPenggunaHome_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        NamaPenggunaHome_TextField.setForeground(new java.awt.Color(255, 255, 255));
        NamaPenggunaHome_TextField.setBorder(null);
        NamaPenggunaHome_TextField.setCaretColor(new java.awt.Color(255, 255, 255));
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
        Seacrh_Bar_Panel.add(NamaPenggunaHome_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 1250, 390, 30));

        LogOut_Btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Keluar Btn.png"))); // NOI18N
        LogOut_Btn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LogOut_Btn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogOut_Btn1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LogOut_Btn1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LogOut_Btn1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                LogOut_Btn1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                LogOut_Btn1MouseReleased(evt);
            }
        });
        Seacrh_Bar_Panel.add(LogOut_Btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1057, 2487, -1, -1));

        EmailHome_Text.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Home Page/Email.png"))); // NOI18N
        Seacrh_Bar_Panel.add(EmailHome_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 1330, -1, -1));

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
        Seacrh_Bar_Panel.add(Simpan_Tombol, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 1580, -1, -1));

        Sellerid_TextField.setForeground(new java.awt.Color(253, 253, 252));
        Sellerid_TextField.setBorder(null);
        Sellerid_TextField.setOpaque(false);
        Seacrh_Bar_Panel.add(Sellerid_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 1640, 90, -1));

        Product_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Effect Admin Page/Riwatar Belanja Btn.png"))); // NOI18N
        Product_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Product_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Product_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Product_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Product_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Product_BtnMouseReleased(evt);
            }
        });
        Seacrh_Bar_Panel.add(Product_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(308, 2487, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Seller Page/Home Page Seller.png"))); // NOI18N
        Seacrh_Bar_Panel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        Seacrh_Bar_Panel.add(Penjual_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 1140, -1, -1));

        Filename_Text.setEditable(false);
        Filename_Text.setToolTipText("");
        Filename_Text.setEnabled(false);
        Filename_Text.setOpaque(false);
        Seacrh_Bar_Panel.add(Filename_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1440, 160, -1));

        Home_Panel.setViewportView(Seacrh_Bar_Panel);

        Main_Panel.add(Home_Panel, "card2");

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBackground(new java.awt.Color(130, 80, 74));
        jTable1.setFont(new java.awt.Font("Serif", 0, 20)); // NOI18N
        jTable1.setForeground(new java.awt.Color(253, 253, 252));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.setGridColor(new java.awt.Color(130, 80, 74));
        jTable1.setRowHeight(60);
        jTable1.setSelectionBackground(new java.awt.Color(220, 139, 27));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 1260, 970, 650));

        Subkategori_Item.setBorder(null);
        Subkategori_Item.setOpaque(false);
        Subkategori_Item.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Subkategori_ItemItemStateChanged(evt);
            }
        });
        Subkategori_Item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Subkategori_ItemMouseClicked(evt);
            }
        });
        Subkategori_Item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Subkategori_ItemActionPerformed(evt);
            }
        });
        jPanel1.add(Subkategori_Item, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 1472, 460, 60));

        Kategori_Item.setBorder(null);
        Kategori_Item.setOpaque(false);
        Kategori_Item.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Kategori_ItemItemStateChanged(evt);
            }
        });
        Kategori_Item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Kategori_ItemMouseClicked(evt);
            }
        });
        Kategori_Item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Kategori_ItemActionPerformed(evt);
            }
        });
        jPanel1.add(Kategori_Item, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 1363, 460, 60));

        Stok_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Stok_TextField.setForeground(new java.awt.Color(255, 255, 255));
        Stok_TextField.setBorder(null);
        Stok_TextField.setCaretColor(new java.awt.Color(255, 255, 255));
        Stok_TextField.setOpaque(false);
        Stok_TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Stok_TextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Stok_TextFieldFocusLost(evt);
            }
        });
        Stok_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Stok_TextFieldKeyPressed(evt);
            }
        });
        jPanel1.add(Stok_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 1565, 390, 30));

        Stok_Text.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Seller Page/Stok.png"))); // NOI18N
        jPanel1.add(Stok_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 1570, -1, -1));

        Nama_Produk_Text_Field.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Nama_Produk_Text_Field.setForeground(new java.awt.Color(255, 255, 255));
        Nama_Produk_Text_Field.setBorder(null);
        Nama_Produk_Text_Field.setCaretColor(new java.awt.Color(255, 255, 255));
        Nama_Produk_Text_Field.setOpaque(false);
        Nama_Produk_Text_Field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Nama_Produk_Text_FieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Nama_Produk_Text_FieldFocusLost(evt);
            }
        });
        Nama_Produk_Text_Field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Nama_Produk_Text_FieldKeyPressed(evt);
            }
        });
        jPanel1.add(Nama_Produk_Text_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 1265, 390, 30));

        Nama_Produk_Text.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Seller Page/Nama Produk.png"))); // NOI18N
        jPanel1.add(Nama_Produk_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 1270, -1, -1));

        Massa_Text.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Seller Page/Massa (gr).png"))); // NOI18N
        jPanel1.add(Massa_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 2000, -1, -1));

        Massa_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Massa_TextField.setForeground(new java.awt.Color(255, 255, 255));
        Massa_TextField.setBorder(null);
        Massa_TextField.setCaretColor(new java.awt.Color(255, 255, 255));
        Massa_TextField.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        Massa_TextField.setOpaque(false);
        Massa_TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Massa_TextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Massa_TextFieldFocusLost(evt);
            }
        });
        Massa_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Massa_TextFieldKeyPressed(evt);
            }
        });
        jPanel1.add(Massa_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 1995, 390, 30));

        Panjang_Text.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Seller Page/Panjang (cm).png"))); // NOI18N
        jPanel1.add(Panjang_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 2085, -1, -1));

        Panjang_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Panjang_TextField.setForeground(new java.awt.Color(255, 255, 255));
        Panjang_TextField.setBorder(null);
        Panjang_TextField.setCaretColor(new java.awt.Color(255, 255, 255));
        Panjang_TextField.setOpaque(false);
        Panjang_TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Panjang_TextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Panjang_TextFieldFocusLost(evt);
            }
        });
        Panjang_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Panjang_TextFieldKeyPressed(evt);
            }
        });
        jPanel1.add(Panjang_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 2080, 390, 30));

        Lebar_Text.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Seller Page/Lebar (cm).png"))); // NOI18N
        jPanel1.add(Lebar_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 2170, -1, -1));

        Lebar_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Lebar_TextField.setForeground(new java.awt.Color(255, 255, 255));
        Lebar_TextField.setBorder(null);
        Lebar_TextField.setCaretColor(new java.awt.Color(255, 255, 255));
        Lebar_TextField.setOpaque(false);
        Lebar_TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Lebar_TextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Lebar_TextFieldFocusLost(evt);
            }
        });
        Lebar_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Lebar_TextFieldKeyPressed(evt);
            }
        });
        jPanel1.add(Lebar_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 2165, 390, 30));

        Tinggi_Text.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Seller Page/Tinggi (cm).png"))); // NOI18N
        jPanel1.add(Tinggi_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 2255, -1, -1));

        Tinggi_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Tinggi_TextField.setForeground(new java.awt.Color(255, 255, 255));
        Tinggi_TextField.setBorder(null);
        Tinggi_TextField.setCaretColor(new java.awt.Color(255, 255, 255));
        Tinggi_TextField.setOpaque(false);
        Tinggi_TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Tinggi_TextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Tinggi_TextFieldFocusLost(evt);
            }
        });
        Tinggi_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tinggi_TextFieldKeyPressed(evt);
            }
        });
        jPanel1.add(Tinggi_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 2250, 390, 30));

        Harga_Text.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Seller Page/Harga.png"))); // NOI18N
        jPanel1.add(Harga_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 2345, -1, -1));

        Harga_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Harga_TextField.setForeground(new java.awt.Color(255, 255, 255));
        Harga_TextField.setBorder(null);
        Harga_TextField.setCaretColor(new java.awt.Color(255, 255, 255));
        Harga_TextField.setOpaque(false);
        Harga_TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Harga_TextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Harga_TextFieldFocusLost(evt);
            }
        });
        Harga_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Harga_TextFieldKeyPressed(evt);
            }
        });
        jPanel1.add(Harga_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 2340, 390, 30));

        Foto_Barang_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Picture Btn.png"))); // NOI18N
        Foto_Barang_Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Foto_Barang_BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Foto_Barang_BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Foto_Barang_BtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Foto_Barang_BtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Foto_Barang_BtnMouseReleased(evt);
            }
        });
        jPanel1.add(Foto_Barang_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 2445, -1, -1));

        Foto_TextField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        Foto_TextField.setForeground(new java.awt.Color(255, 255, 255));
        Foto_TextField.setBorder(null);
        Foto_TextField.setCaretColor(new java.awt.Color(255, 255, 255));
        Foto_TextField.setOpaque(false);
        Foto_TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Foto_TextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Foto_TextFieldFocusLost(evt);
            }
        });
        Foto_TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Foto_TextFieldKeyPressed(evt);
            }
        });
        jPanel1.add(Foto_TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 2460, 330, 30));

        Foto_Text.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Seller Page/Foto Barang.png"))); // NOI18N
        jPanel1.add(Foto_Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 2468, -1, -1));

        Simpan_Tombol1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Simpan Data.png"))); // NOI18N
        Simpan_Tombol1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Simpan_Tombol1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Simpan_Tombol1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Simpan_Tombol1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Simpan_Tombol1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Simpan_Tombol1MouseReleased(evt);
            }
        });
        jPanel1.add(Simpan_Tombol1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1660, -1, -1));

        Simpan_Tombol2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Effect/Simpan Data.png"))); // NOI18N
        Simpan_Tombol2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Simpan_Tombol2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Simpan_Tombol2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Simpan_Tombol2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Simpan_Tombol2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Simpan_Tombol2MouseReleased(evt);
            }
        });
        jPanel1.add(Simpan_Tombol2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 2540, -1, -1));

        jLabel2.setBackground(new java.awt.Color(220, 139, 27));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Seller Page/Seller_Item.png"))); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 1030, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/Seller Page/Produk Data Page.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        id_product.setBackground(new java.awt.Color(220, 139, 27));
        id_product.setForeground(new java.awt.Color(220, 139, 27));
        id_product.setBorder(null);
        jPanel1.add(id_product, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 1600, 70, -1));

        id_sctg.setBackground(new java.awt.Color(220, 139, 27));
        id_sctg.setForeground(new java.awt.Color(220, 139, 27));
        id_sctg.setBorder(null);
        jPanel1.add(id_sctg, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 1490, 70, -1));

        Belanja_Panel.setViewportView(jPanel1);

        Main_Panel.add(Belanja_Panel, "card3");

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

        getContentPane().add(Main_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 1920, 890));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Cari_Item_TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Cari_Item_TextFieldFocusLost
        // TODO add your handling code here:
        if(Cari_Item_TextField.getText().length() > 0){ // Ketika jumlah karakter lebih dari nol
            Telusuri_Produk_Label.setVisible(false);// Jlabel dibuat menghilang
        }else{
            Telusuri_Produk_Label.setVisible(true);// Jlabel dibuat muncul kembali
        }
    }//GEN-LAST:event_Cari_Item_TextFieldFocusLost

    private void Cari_Item_TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Cari_Item_TextFieldFocusGained
        // TODO add your handling code here:
        Telusuri_Produk_Label.setVisible(false);//JLabel dibuat menghilang
    }//GEN-LAST:event_Cari_Item_TextFieldFocusGained

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
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Produk Btn.png");/*Format pemanggilan file ImageIcon
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
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Produk Btn.png");/*Format pemanggilan file ImageIcon
        // yang ingin dipanggil*/
        Belanja_Btn.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel)
        //yang digunakan untuk melakukan set pada ImageIcon*/
        
        if(Belanja_Panel.isShowing()){
        ImageIcon Btn_Belanja1;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja1 = new ImageIcon("src/Picture/Effect/Produk Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Belanja_Btn.setIcon(Btn_Belanja1);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        }
    }//GEN-LAST:event_Belanja_BtnMouseReleased

    private void Belanja_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Belanja_BtnMouseExited
        //TODO add your handling code here:
        ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Produk Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Belanja_Btn.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        if(Belanja_Panel.isShowing()){
        ImageIcon Btn_Belanja1;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja1 = new ImageIcon("src/Picture/Effect/Produk Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Belanja_Btn.setIcon(Btn_Belanja1);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        
        }
    }//GEN-LAST:event_Belanja_BtnMouseExited

    private void Belanja_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Belanja_BtnMouseEntered
        // TODO add your handling code here:
        
              
            ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan
            untuk memanggil ImageIcon*/
            Btn_Belanja = new ImageIcon("src/Picture/Effect/Produk Btn Effect 3.png");/*Format pemanggilan file ImageIcon
            yang ingin dipanggil*/
            Belanja_Btn.setIcon(Btn_Belanja);/*Pallete pada java (berupa JLabel)
            yang digunakan untuk melakukan set pada ImageIcon*/    
        
      
        
    }//GEN-LAST:event_Belanja_BtnMouseEntered

    private void Belanja_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Belanja_BtnMouseClicked
        // TODO add your handling code here:
        Home_Btn_eft2.setVisible(false);
        ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Produk Btn Effect 2.png");/*Format pemanggilan file ImageIcon
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
        
        kategori();
        seller_item_data();
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
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Produk Btn.png");/*Format pemanggilan file ImageIcon
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
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Produk Btn.png");/*Format pemanggilan file ImageIcon
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
        PreparedStatement cmd;
        ResultSet rs;
        try {
            String sql = "update tb_seller set foto_seller=? where username_seller='"+Namaa+"' or username_seller='"+Nama+"' or username_seller='"+Nama1+"'"; /*Syntax pada mysql yang digunakan untuk melakukan pemasukkan data username, email, dan password*/
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            cmd.setBytes(1, photo); /*Pemasangan dan pemasukkan data pada database server*/
            cmd.execute(); /*Untuk melakukan ekseskusi pada syntax sql*/
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_GantiFoto_TombolMouseClicked
   
    
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
            String sql = "update tb_seller set nama_seller=?, email_seller=?, no_telp_seller=?, alamat_seller=? where username_seller='"+Namaa+"' or username_seller='"+Nama+"' or username_seller='"+Nama1+"'"; /*Syntax pada mysql yang digunakan untuk melakukan peng-update-an data
            password melalui username atau email yang dimasukkan pada form sebelumnya*/
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            cmd.setString(1, NamaPenggunaHome_TextField.getText()); /*Pemasangan dan pemasukkan data pada database server*/
            cmd.setString(2, EmailHome_TextField.getText()); /*Pemasangan dan pemasukkan data pada database server*/
            cmd.setString(3, TelpHome_TextField.getText()); /*Pemasangan dan pemasukkan data pada database server*/
            cmd.setString(4, AlamatHome_TextField.getText()); /*Pemasangan dan pemasukkan data pada database server*/

            if(Nama.length() > 0 && Email.length() > 0 && Telp.length() > 0 && Alamat.length() > 0){ /*Kondisi bahwa karakter dari password yang telah dimasukkan pada JTextField harus sesuai dengan apa
                password yang dimasukkan kembali pada JTextField berikutnya*/
                cmd.executeUpdate(); /*Untuk melakukan ekseskusi pada syntax sql*/
                NamaPenggunaHome_Text1.setVisible(false);
                NamaPenggunaHome_TextField1.setText(Nama);
                EmailHome_Text1.setVisible(false);
                EmailHome_TextField1.setText(Email);
                TelpHome_Text1.setVisible(false);
                TelpHome_TextField1.setText(Telp);
                AlamatHome_Text1.setVisible(false);
                AlamatHome_TextField1.setText(Alamat);
                NamaPenggunaHome_TextField.setText(""); /*JTextField untuk menyimpan data nama pengguna yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
                di buat menghilang*/
                EmailHome_TextField.setText(""); /*JTextField untuk menyimpan data confirm email yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang
                telah dimasukkan di buat menghilang*/
                TelpHome_TextField.setText(""); /*JTextField untuk menyimpan data telp yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
                di buat menghilang*/
                AlamatHome_TextField.setText(""); /*JTextField untuk menyimpan data alamat yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
                di buat menghilang*/
                NamaPenggunaHome_Text.setVisible(true); /*JTextField untuk menyimpan data nama pengguna yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
                di buat menghilang*/
                EmailHome_Text.setVisible(true); /*JTextField untuk menyimpan data confirm email yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang
                telah dimasukkan di buat menghilang*/
                TelpHome_Text.setVisible(true); /*JTextField untuk menyimpan data telp yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
                di buat menghilang*/
                AlamatHome_Text.setVisible(true); /*JTextField untuk menyimpan data alamat yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
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

    private void Kategori_ItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Kategori_ItemActionPerformed
       
    }//GEN-LAST:event_Kategori_ItemActionPerformed

    private void Subkategori_ItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Subkategori_ItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Subkategori_ItemActionPerformed

    private void Nama_Produk_Text_FieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Nama_Produk_Text_FieldFocusGained
        Nama_Produk_Text.setVisible(false);
    }//GEN-LAST:event_Nama_Produk_Text_FieldFocusGained

    private void Nama_Produk_Text_FieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Nama_Produk_Text_FieldFocusLost
        if(Nama_Produk_Text_Field.getText().length() == 0){
            Nama_Produk_Text.setVisible(true);
        }else if(Nama_Produk_Text_Field.getText().length() > 0){
            Nama_Produk_Text.setVisible(false);
        }
    }//GEN-LAST:event_Nama_Produk_Text_FieldFocusLost

    private void Nama_Produk_Text_FieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Nama_Produk_Text_FieldKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_UP){
            Stok_TextField.requestFocus();
        }
    }//GEN-LAST:event_Nama_Produk_Text_FieldKeyPressed

    private void Stok_TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Stok_TextFieldFocusGained
        Stok_Text.setVisible(false);
    }//GEN-LAST:event_Stok_TextFieldFocusGained

    private void Stok_TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Stok_TextFieldFocusLost
        if(Stok_TextField.getText().length() == 0){
            Stok_Text.setVisible(true);
        }else if(Stok_TextField.getText().length() > 0){
            Stok_Text.setVisible(false);
        }
    }//GEN-LAST:event_Stok_TextFieldFocusLost

    private void Stok_TextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Stok_TextFieldKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_UP){
            Stok_TextField.requestFocus();
        }
    }//GEN-LAST:event_Stok_TextFieldKeyPressed

    private void Massa_TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Massa_TextFieldFocusGained
        Massa_Text.setVisible(false);
    }//GEN-LAST:event_Massa_TextFieldFocusGained

    private void Massa_TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Massa_TextFieldFocusLost
        if(Massa_TextField.getText().length() == 0){
            Massa_Text.setVisible(true);
        }else if(Massa_TextField.getText().length() > 0){
            Massa_Text.setVisible(false);
        }
    }//GEN-LAST:event_Massa_TextFieldFocusLost

    private void Massa_TextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Massa_TextFieldKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            Panjang_TextField.requestFocus();
        }else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            Panjang_TextField.requestFocus();
        }else if(evt.getKeyCode() == KeyEvent.VK_UP){
            Massa_TextField.requestFocus();
        }
    }//GEN-LAST:event_Massa_TextFieldKeyPressed

    private void Panjang_TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Panjang_TextFieldFocusGained
        Panjang_Text.setVisible(false);
    }//GEN-LAST:event_Panjang_TextFieldFocusGained

    private void Panjang_TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Panjang_TextFieldFocusLost
        if(Panjang_TextField.getText().length() == 0){
            Panjang_Text.setVisible(true);
        }else if(Panjang_TextField.getText().length() > 0){
            Panjang_Text.setVisible(false);
        }
    }//GEN-LAST:event_Panjang_TextFieldFocusLost

    private void Panjang_TextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Panjang_TextFieldKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            Lebar_TextField.requestFocus();
        }else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            Lebar_TextField.requestFocus();
        }else if(evt.getKeyCode() == KeyEvent.VK_UP){
            Massa_TextField.requestFocus();
        }
    }//GEN-LAST:event_Panjang_TextFieldKeyPressed

    private void Lebar_TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Lebar_TextFieldFocusGained
          Lebar_Text.setVisible(false);
    }//GEN-LAST:event_Lebar_TextFieldFocusGained

    private void Lebar_TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Lebar_TextFieldFocusLost
        if(Lebar_TextField.getText().length() == 0){
            Lebar_Text.setVisible(true);
        }else if(EmailHome_TextField.getText().length() > 0){
            Lebar_Text.setVisible(false);
        }
    }//GEN-LAST:event_Lebar_TextFieldFocusLost

    private void Lebar_TextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Lebar_TextFieldKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            Tinggi_TextField.requestFocus();
        }else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            Tinggi_TextField.requestFocus();
        }else if(evt.getKeyCode() == KeyEvent.VK_UP){
            Panjang_TextField.requestFocus();
        }
    }//GEN-LAST:event_Lebar_TextFieldKeyPressed

    private void Tinggi_TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Tinggi_TextFieldFocusGained
        Tinggi_Text.setVisible(false);
    }//GEN-LAST:event_Tinggi_TextFieldFocusGained

    private void Tinggi_TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Tinggi_TextFieldFocusLost
        if(Tinggi_TextField.getText().length() == 0){
            Tinggi_Text.setVisible(true);
        }else if(Tinggi_TextField.getText().length() > 0){
            Tinggi_Text.setVisible(false);
        }
    }//GEN-LAST:event_Tinggi_TextFieldFocusLost

    private void Tinggi_TextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tinggi_TextFieldKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            Harga_TextField.requestFocus();
        }else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            Harga_TextField.requestFocus();
        }else if(evt.getKeyCode() == KeyEvent.VK_UP){
            Lebar_TextField.requestFocus();
        }
    }//GEN-LAST:event_Tinggi_TextFieldKeyPressed

    private void Harga_TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Harga_TextFieldFocusGained
        Harga_Text.setVisible(false);
    }//GEN-LAST:event_Harga_TextFieldFocusGained

    private void Harga_TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Harga_TextFieldFocusLost
        if(Harga_TextField.getText().length() == 0){
            Harga_Text.setVisible(true);
        }else if(Harga_TextField.getText().length() > 0){
            Harga_Text.setVisible(false);
        }
    }//GEN-LAST:event_Harga_TextFieldFocusLost

    private void Harga_TextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Harga_TextFieldKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            Foto_TextField.requestFocus();
        }else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            Foto_TextField.requestFocus();
        }else if(evt.getKeyCode() == KeyEvent.VK_UP){
            Tinggi_TextField.requestFocus();
        }
    }//GEN-LAST:event_Harga_TextFieldKeyPressed

    private void Foto_Barang_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Foto_Barang_BtnMouseClicked
        ImageIcon GantiFotoTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        GantiFotoTombol = new ImageIcon("src/Picture/Effect/Picture Btn Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Foto_Barang_Btn.setIcon(GantiFotoTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/

       
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(new FileNameExtensionFilter("jpg|jpeg|png|bpm", "jpg", "jpeg", "png", "bmp"));
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        //Image_Label.setIcon(new ImageIcon(f.toString()));

        filename=f.getAbsolutePath();
        Foto_TextField.setText(filename);
        File file=chooser.getSelectedFile();
        try {
            if(file.length() > 205659){
                JOptionPane.showMessageDialog(rootPane, "Ukuran gambar terlalu besar", "Max 200kb", JOptionPane.WARNING_MESSAGE);
            }else{
                images=ImageIO.read(file);
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
           //Class.forName("com.mysql.jdbc.Driver"); /*Pemanggilan kelas pad library JDBC driver mysql*/
           //Connection connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/db_bilik_pernik","root", ""); /*variabel yang digunakan untuk pemanggilan driver mysql pada database server*/
           //String sql = "update tb_item set foto_produk =? where nama_seller='"+Nama1+"'"; /*Syntax pada mysql yang digunakan untuk melakukan pemasukkan data username, email, dan password*/
           //PreparedStatement pst = connect.prepareStatement(sql); /*Variabel untuk mengambil data-data yang ingin dimasukkan ke dalam database server*/
           //pst.setBytes(1, photo); /*Pemasangan dan pemasukkan data pada database server*/
           //pst.execute(); /*Untuk melakukan ekseskusi pada syntax sql*/
        } catch (Exception e) {
        //    JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_Foto_Barang_BtnMouseClicked

    private void Foto_Barang_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Foto_Barang_BtnMouseEntered
        ImageIcon GantiFotoTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        GantiFotoTombol = new ImageIcon("src/Picture/Effect/Picture Btn Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Foto_Barang_Btn.setIcon(GantiFotoTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Foto_Barang_BtnMouseEntered

    private void Foto_Barang_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Foto_Barang_BtnMouseExited
        ImageIcon GantiFotoTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        GantiFotoTombol = new ImageIcon("src/Picture/Effect/Picture Btn.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Foto_Barang_Btn.setIcon(GantiFotoTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Foto_Barang_BtnMouseExited

    private void Foto_Barang_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Foto_Barang_BtnMousePressed
        ImageIcon GantiFotoTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        GantiFotoTombol = new ImageIcon("src/Picture/Effect/Picture Btn Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Foto_Barang_Btn.setIcon(GantiFotoTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Foto_Barang_BtnMousePressed

    private void Foto_Barang_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Foto_Barang_BtnMouseReleased
        ImageIcon GantiFotoTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        GantiFotoTombol = new ImageIcon("src/Picture/Effect/Picture Btn.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Foto_Barang_Btn.setIcon(GantiFotoTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Foto_Barang_BtnMouseReleased

    private void Foto_TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Foto_TextFieldFocusGained
        Foto_Text.setVisible(false);
    }//GEN-LAST:event_Foto_TextFieldFocusGained

    private void Foto_TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Foto_TextFieldFocusLost
        if(Foto_TextField.getText().length() == 0){
            Foto_Text.setVisible(true);
        }else if(Foto_TextField.getText().length() > 0){
            Foto_Text.setVisible(false);
        }
    }//GEN-LAST:event_Foto_TextFieldFocusLost

    private void Foto_TextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Foto_TextFieldKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_UP){
            Harga_TextField.requestFocus();
        }
    }//GEN-LAST:event_Foto_TextFieldKeyPressed

    private void Simpan_Tombol1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_Tombol1MouseClicked
        
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Tombol1.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
        
        String Nama = NamaPenggunaHome_TextField.getText(); /*Variabel untuk mengambil data nama pengguna*/
        String Nama1 = NamaPenggunaProfile_TextField.getText(); /*Variabel untuk mengambil data nama pengguna profile*/
        
        String IDSeller = Penjual_ID.getText();
        int IDKategori =  Kategori_Item.getSelectedIndex(); /*Variabel untuk mengambil data email*/
        String IDSubKategori = id_sctg.getText(); /*Variabel untuk mengambil data telepon*/
        String Nama_Produk = Nama_Produk_Text_Field.getText(); /*Variabel untuk mengambil data nama pengguna*/
        String Stok = Stok_TextField.getText(); /*Variabel untuk mengambil data alamat*/
       
        PreparedStatement cmd;
        ResultSet rs;
        /*Try dan Catch digunakan untuk menangani error pada java, eksekusi yang menampilkan
        error dan membuat program tetap berjalan tanpa dihentikan secara langsung*/
        try{ /*Statement yang digunakan untuk menghubungkan database pada mysql ke Netbeans*/
            String sql = "insert into tb_produk(id_seller, id_kategori, id_subkategori, nama_produk, stok) values('"+IDSeller+"','"+IDKategori+"','"+IDSubKategori+"','"+Nama_Produk+"','"+Stok+"')"; /*Syntax pada mysql yang digunakan untuk melakukan peng-update-an data
            password melalui username atau email yang dimasukkan pada form sebelumnya*/
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
            

            if(Nama_Produk_Text_Field.getText().length() > 0){ /*Kondisi bahwa karakter dari password yang telah dimasukkan pada JTextField harus sesuai dengan apa
                password yang dimasukkan kembali pada JTextField berikutnya*/                
                cmd.executeUpdate(); /*Untuk melakukan ekseskusi pada syntax sql*/
                id_product(Nama_Produk);
                Nama_Produk_Text_Field.setText(""); /*JTextField untuk menyimpan data nama pengguna yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
                di buat menghilang*/
                Kategori_Item.setSelectedItem("Tentukkan Kategori Produk"); /*JTextField untuk menyimpan data confirm email yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang
                telah dimasukkan di buat menghilang*/
                Subkategori_Item.setSelectedItem("Tentukkan Subkategori Produk"); /*JTextField untuk menyimpan data telp yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
                di buat menghilang*/
                Stok_TextField.setText(""); /*JTextField untuk menyimpan data alamat yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
                di buat menghilang*/
               
            }else{ /*Jika kondisi diatas tidak sesuai, maka menuju ke else*/
                Nama_Produk_Text_Field.setText(""); /*JTextField untuk menyimpan data nama pengguna yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
                di buat menghilang*/
                Kategori_Item.setSelectedItem(""); /*JTextField untuk menyimpan data confirm email yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang
                telah dimasukkan di buat menghilang*/
                Subkategori_Item.setSelectedItem(""); /*JTextField untuk menyimpan data telp yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
                di buat menghilang*/
                Stok_TextField.setText(""); /*JTextField untuk menyimpan data alamat yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
                di buat menghilang*/
            }
        }catch(SQLException e) /*Menangkap kesalahan atau bug yang terjadi dalam block try*/
        {
            JOptionPane.showMessageDialog(null, e); /*Kondisi apabila terjadi kesalahan pada saat melakukan input data*/
        }
    }//GEN-LAST:event_Simpan_Tombol1MouseClicked

    private void Simpan_Tombol1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_Tombol1MouseEntered
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Tombol1.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_Tombol1MouseEntered

    private void Simpan_Tombol1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_Tombol1MouseExited
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Tombol1.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_Tombol1MouseExited

    private void Simpan_Tombol1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_Tombol1MousePressed
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Tombol1.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_Tombol1MousePressed

    private void Simpan_Tombol1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_Tombol1MouseReleased
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Tombol1.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_Tombol1MouseReleased

    private void Simpan_Tombol2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_Tombol2MouseClicked
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Tombol2.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
        
        String id_item = id_product.getText();
        String Massa = Massa_TextField.getText(); /*Variabel untuk mengambil data nama pengguna*/
        String Panjang = Panjang_TextField.getText(); /*Variabel untuk mengambil data email*/
        String Lebar = Lebar_TextField.getText(); /*Variabel untuk mengambil data telepon*/
        String Tinggi = Tinggi_TextField.getText(); /*Variabel untuk mengambil data alamat*/
        String Harga = Harga_TextField.getText(); /*Variabel untuk mengambil data nama pengguna profile*/
        String Nama1 = NamaPenggunaProfile_TextField.getText(); /*Variabel untuk mengambil data nama pengguna profile*/
        
       
        PreparedStatement cmd;
        ResultSet rs;
        /*Try dan Catch digunakan untuk menangani error pada java, eksekusi yang menampilkan
        error dan membuat program tetap berjalan tanpa dihentikan secara langsung*/
        try{ /*Statement yang digunakan untuk menghubungkan database pada mysql ke Netbeans*/
            String sql = "insert into tb_detail_produk(id_produk,foto_produk,massa_gr,panjang_cm,lebar_cm,tinggi_cm,harga)"
                    + "values(?,?,?,?,?,?,?)"; /*Syntax pada mysql yang digunakan untuk melakukan peng-update-an data
            password melalui username atau email yang dimasukkan pada form sebelumnya*/
            cmd = BK_Connection.getBilik_Pernik_Connect().prepareStatement(sql);
                cmd.setString(1, id_product.getText());
                cmd.setBytes(2, photo);
                cmd.setString(3, Massa_TextField.getText());
                cmd.setString(4, Panjang_TextField.getText());
                cmd.setString(5,Lebar_TextField.getText());
                cmd.setString(6, Tinggi_TextField.getText());
                cmd.setString(7, Harga_TextField.getText());
            if(Massa.length() > 0 && Panjang.length() > 0 && Lebar.length() > 0 && Tinggi.length() > 0){ /*Kondisi bahwa karakter dari password yang telah dimasukkan pada JTextField harus sesuai dengan apa
                password yang dimasukkan kembali pada JTextField berikutnya*/
                cmd.executeUpdate(); /*Untuk melakukan ekseskusi pada syntax sql*/
                Massa_Text.setVisible(false);
                Panjang_Text.setVisible(false);
                Lebar_Text.setVisible(false);
                Tinggi_Text.setVisible(false);
                Harga_Text.setVisible(false);
                Foto_Text.setVisible(false);
                 Massa_TextField.setText(""); /*JTextField untuk menyimpan data nama pengguna yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
                di buat menghilang*/
                Panjang_TextField.setText(""); /*JTextField untuk menyimpan data confirm email yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang
                telah dimasukkan di buat menghilang*/
                Lebar_TextField.setText(""); /*JTextField untuk menyimpan data telp yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
                di buat menghilang*/
                Tinggi_TextField.setText(""); /*JTextField untuk menyimpan data alamat yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
                di buat menghilang*/
                Harga_TextField.setText(""); /*JTextField untuk menyimpan data alamat yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
                di buat menghilang*/
                Foto_TextField.setText("");
            }else{ /*Jika kondisi diatas tidak sesuai, maka menuju ke else*/
                Massa_TextField.setText(""); /*JTextField untuk menyimpan data nama pengguna yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
                di buat menghilang*/
                Panjang_TextField.setText(""); /*JTextField untuk menyimpan data confirm email yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang
                telah dimasukkan di buat menghilang*/
                Lebar_TextField.setText(""); /*JTextField untuk menyimpan data telp yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
                di buat menghilang*/
                Tinggi_TextField.setText(""); /*JTextField untuk menyimpan data alamat yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
                di buat menghilang*/
                Harga_TextField.setText(""); /*JTextField untuk menyimpan data alamat yang dimana karakter kata yang telah dimasukkan yang dimana karakter kata yang telah dimasukkan
                di buat menghilang*/
                Foto_TextField.setText("");
                
            }
        }catch(SQLException e) /*Menangkap kesalahan atau bug yang terjadi dalam block try*/
        {
            JOptionPane.showMessageDialog(null, e); /*Kondisi apabila terjadi kesalahan pada saat melakukan input data*/
        }
        
        seller_item_data();
    }//GEN-LAST:event_Simpan_Tombol2MouseClicked

    private void Simpan_Tombol2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_Tombol2MouseEntered
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Effect 1.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Tombol2.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_Tombol2MouseEntered

    private void Simpan_Tombol2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_Tombol2MouseExited
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Tombol2.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_Tombol2MouseExited

    private void Simpan_Tombol2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_Tombol2MousePressed
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data Effect 3.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Tombol2.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_Tombol2MousePressed

    private void Simpan_Tombol2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Simpan_Tombol2MouseReleased
        ImageIcon SimpanTombol;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        SimpanTombol = new ImageIcon("src/Picture/Effect/Simpan Data.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Simpan_Tombol2.setIcon(SimpanTombol);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Simpan_Tombol2MouseReleased

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked

    }//GEN-LAST:event_jLabel7MouseClicked

    private void Home_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Home_PanelMouseEntered
       
    }//GEN-LAST:event_Home_PanelMouseEntered

    private void Kategori_ItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Kategori_ItemMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Kategori_ItemMouseClicked

    private void Subkategori_ItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Subkategori_ItemMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_Subkategori_ItemMouseClicked

    private void Kategori_ItemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Kategori_ItemItemStateChanged
        // TODO add your handling code here:
        Statement cmd;
        ResultSet rs;
        if(Kategori_Item.getItemCount()!=0){
            try {
            cmd = BK_Connection.getBilik_Pernik_Connect().createStatement(); 
            String sql = "select tb_subkategori.id_subkategori ,tb_kategori.kategori, tb_subkategori.subkategori from tb_subkategori join tb_kategori on tb_subkategori.id_kategori = "
                    + "tb_kategori.id_kategori where tb_kategori.kategori = '"+Kategori_Item.getSelectedItem()+"'"; /*Syntax pada mysql yang digunakan untuk melakukan peng-update-an data
            password melalui username atau email yang dimasukkan pada form sebelumnya*/
            rs = cmd.executeQuery(sql);
            Subkategori_Item.removeAllItems();
            Subkategori_Item.addItem("Tentukan subkategori produk");
            while(rs.next()){
                Subkategori_Item.addItem(rs.getString("subkategori"));
                Subkategori_Item.getItemAt(rs.getInt("id_subkategori"));
            }
            } catch (SQLException e) {
                 JOptionPane.showMessageDialog(null, e); /*Kondisi apabila terjadi kesalahan pada saat melakukan input data*/
            }
                
        }
    }//GEN-LAST:event_Kategori_ItemItemStateChanged

    private void Search_Bar_LabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Search_Bar_LabelMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_Search_Bar_LabelMouseClicked

    private void Logo_PictureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Logo_PictureMouseClicked
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

    private void Subkategori_ItemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Subkategori_ItemItemStateChanged
        // TODO add your handling code here:
        Statement cmd;
        ResultSet rs;
        if(Subkategori_Item.getItemCount()!=0){
            try {
            cmd = BK_Connection.getBilik_Pernik_Connect().createStatement(); 
            String sql = "select*from tb_subkategori where subkategori = '"+Subkategori_Item.getSelectedItem()+"'"; /*Syntax pada mysql yang digunakan untuk melakukan peng-update-an data
            password melalui username atau email yang dimasukkan pada form sebelumnya*/
            rs = cmd.executeQuery(sql);
            if(rs.next()){
                id_sctg.setText(rs.getString(1));
            }
            } catch (SQLException e) {
                 JOptionPane.showMessageDialog(null, e); /*Kondisi apabila terjadi kesalahan pada saat melakukan input data*/
            }
                
        }
    }//GEN-LAST:event_Subkategori_ItemItemStateChanged

    private void Search_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Search_BtnMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Search;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Search = new ImageIcon("src/Picture/Effect/Search_Btn effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Search_Btn.setIcon(Btn_Search);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
        String cari = Cari_Item_TextField.getText();

        if(Belanja_Panel.isShowing()){
           search__seller_item(cari);
        }
    }//GEN-LAST:event_Search_BtnMouseClicked

    private void Search_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Search_BtnMouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_Search;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Search = new ImageIcon("src/Picture/Effect/Search_Btn effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Search_Btn.setIcon(Btn_Search);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Search_BtnMouseEntered

    private void Search_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Search_BtnMouseExited
        // TODO add your handling code here:
        ImageIcon Btn_Search;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Search = new ImageIcon("src/Picture/Effect/Search_Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Search_Btn.setIcon(Btn_Search);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Search_BtnMouseExited

    private void Search_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Search_BtnMousePressed
        // TODO add your handling code here:
        ImageIcon Btn_Search;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Search = new ImageIcon("src/Picture/Effect/Search_Btn effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Search_Btn.setIcon(Btn_Search);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Search_BtnMousePressed

    private void Search_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Search_BtnMouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_Search;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Search = new ImageIcon("src/Picture/Effect/Search_Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Search_Btn.setIcon(Btn_Search);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Search_BtnMouseReleased

    private void LogOut_Btn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogOut_Btn1MouseClicked
        // TODO add your handling code here:

        ImageIcon Btn_LogOut;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_LogOut = new ImageIcon("src/Picture/Effect/Keluar Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        LogOut_Btn1.setIcon(Btn_LogOut);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/

        Opening_Page_Pt_2 a = new Opening_Page_Pt_2(); /*Deklarasi
        variabel pada form yang ingin dituju dan variabel tersebut
        menujukkan nilai yang sama dengan form yang ingin dituju*/
        a.setVisible(true); /* variabel tersebut akan menampilkan form
        yang dituju*/
        this.dispose();// Untuk menutup form ini
    }//GEN-LAST:event_LogOut_Btn1MouseClicked

    private void LogOut_Btn1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogOut_Btn1MouseEntered
        // TODO add your handling code here:
        ImageIcon Btn_LogOut;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_LogOut = new ImageIcon("src/Picture/Effect/Keluar Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        LogOut_Btn1.setIcon(Btn_LogOut);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_LogOut_Btn1MouseEntered

    private void LogOut_Btn1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogOut_Btn1MouseExited
        // TODO add your handling code here:
        ImageIcon Btn_LogOut;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_LogOut = new ImageIcon("src/Picture/Effect/Keluar Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        LogOut_Btn1.setIcon(Btn_LogOut);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_LogOut_Btn1MouseExited

    private void LogOut_Btn1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogOut_Btn1MousePressed
        // TODO add your handling code here:
        ImageIcon Btn_LogOut;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_LogOut = new ImageIcon("src/Picture/Effect/Keluar Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        LogOut_Btn1.setIcon(Btn_LogOut);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_LogOut_Btn1MousePressed

    private void LogOut_Btn1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogOut_Btn1MouseReleased
        // TODO add your handling code here:
        ImageIcon Btn_LogOut;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_LogOut = new ImageIcon("src/Picture/Effect/Keluar Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        LogOut_Btn1.setIcon(Btn_LogOut);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_LogOut_Btn1MouseReleased

    private void Product_BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Product_BtnMouseClicked
        // TODO add your handling code here:
        ImageIcon Btn_Product;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Product = new ImageIcon("src/Picture/Effect/Effect Admin Page/Riwatar Belanja Btn Effect 2.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Product_Btn.setIcon(Btn_Product);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
         Home_Btn_eft2.setVisible(false);
        ImageIcon Btn_Belanja;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Belanja = new ImageIcon("src/Picture/Effect/Produk Btn Effect 2.png");/*Format pemanggilan file ImageIcon
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
        
        kategori();
        seller_item_data();
    }//GEN-LAST:event_Product_BtnMouseClicked

    private void Product_BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Product_BtnMouseEntered
        // TODO add your handling code here:
         ImageIcon Btn_Product;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Product = new ImageIcon("src/Picture/Effect/Effect Admin Page/Riwatar Belanja Btn Effect 1.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Product_Btn.setIcon(Btn_Product);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Product_BtnMouseEntered

    private void Product_BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Product_BtnMouseExited
        // TODO add your handling code here:
         // TODO add your handling code here:
         ImageIcon Btn_Product;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Product = new ImageIcon("src/Picture/Effect/Effect Admin Page/Riwatar Belanja Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Product_Btn.setIcon(Btn_Product);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Product_BtnMouseExited

    private void Product_BtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Product_BtnMousePressed
        // TODO add your handling code here:
         // TODO add your handling code here:
        ImageIcon Btn_Product;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Product = new ImageIcon("src/Picture/Effect/Effect Admin Page/Riwatar Belanja Btn Effect 3.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Product_Btn.setIcon(Btn_Product);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Product_BtnMousePressed

    private void Product_BtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Product_BtnMouseReleased
        // TODO add your handling code here:
         // TODO add your handling code here:
         ImageIcon Btn_Product;/*Deklarasi variabel yang digunakan
        untuk memanggil ImageIcon*/
        Btn_Product = new ImageIcon("src/Picture/Effect/Effect Admin Page/Riwatar Belanja Btn.png");/*Format pemanggilan file ImageIcon
        yang ingin dipanggil*/
        Product_Btn.setIcon(Btn_Product);/*Pallete pada java (berupa JLabel)
        yang digunakan untuk melakukan set pada ImageIcon*/
    }//GEN-LAST:event_Product_BtnMouseReleased

    private void Instagram_Klik2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Instagram_Klik2MouseClicked
        ImageIcon MedsosKlik;/*Deklarasi variabel yang digunakan untuk memanggil ImageIcon*/
        MedsosKlik = new ImageIcon("src/Picture/Effect/Instagram Btn Effect 2.png");/*Format pemanggilan file ImageIcon yang ingin dipanggil*/
        Instagram_Klik2.setIcon(MedsosKlik);/*Pallete pada java (berupa JLabel) yang digunakan untuk melakukan set pada ImageIcon*/

        Desktop d = Desktop.getDesktop();
        try {
            try {
                d.browse(new URI("https://www.instagram.com/BilikPernik/"));
            } catch (IOException ex) {
                //Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (URISyntaxException ex) {
            //Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
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
                //Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (URISyntaxException ex) {
            //Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
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
                //Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (URISyntaxException ex) {
            //Logger.getLogger(Home_Page.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(Seller_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Seller_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Seller_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Seller_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Seller_Page().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane About_Us_Panel;
    private javax.swing.JLabel AlamatHome_Text;
    private javax.swing.JLabel AlamatHome_Text1;
    private javax.swing.JTextField AlamatHome_TextField;
    public static final javax.swing.JTextField AlamatHome_TextField1 = new javax.swing.JTextField();
    private javax.swing.JLabel Belanja_Btn;
    private javax.swing.JScrollPane Belanja_Panel;
    private javax.swing.JTextField Cari_Item_TextField;
    private javax.swing.JLabel EmailHome_Text;
    private javax.swing.JLabel EmailHome_Text1;
    private javax.swing.JTextField EmailHome_TextField;
    public static final javax.swing.JTextField EmailHome_TextField1 = new javax.swing.JTextField();
    private javax.swing.JLabel Facebook_Klik2;
    private javax.swing.JTextField Filename_Text;
    private javax.swing.JLabel Foto_Barang_Btn;
    private javax.swing.JLabel Foto_Text;
    private javax.swing.JTextField Foto_TextField;
    private javax.swing.JLabel GantiFoto_Tombol;
    private javax.swing.JLabel Harga_Text;
    private javax.swing.JTextField Harga_TextField;
    private javax.swing.JLabel Home_Btn;
    private javax.swing.JLabel Home_Btn_eft2;
    private javax.swing.JScrollPane Home_Panel;
    private javax.swing.JLabel Image_Label;
    private javax.swing.JLabel Instagram_Klik2;
    private javax.swing.JComboBox<String> Kategori_Item;
    private javax.swing.JLabel Kontak_Btn;
    private javax.swing.JScrollPane Kontak_Panel;
    private javax.swing.JLabel Lebar_Text;
    private javax.swing.JTextField Lebar_TextField;
    public static final javax.swing.JLabel LogOut_Btn1 = new javax.swing.JLabel();
    private javax.swing.JLabel Logo_Picture;
    private javax.swing.JPanel Main_Panel;
    private javax.swing.JLabel Massa_Text;
    private javax.swing.JTextField Massa_TextField;
    private javax.swing.JLabel NamaPenggunaHome_Text;
    public static final javax.swing.JLabel NamaPenggunaHome_Text1 = new javax.swing.JLabel();
    private javax.swing.JTextField NamaPenggunaHome_TextField;
    public static final javax.swing.JTextField NamaPenggunaHome_TextField1 = new javax.swing.JTextField();
    private javax.swing.JTextField NamaPenggunaProfile_TextField;
    private javax.swing.JLabel Nama_Produk_Text;
    private javax.swing.JTextField Nama_Produk_Text_Field;
    private javax.swing.JLabel Panjang_Text;
    private javax.swing.JTextField Panjang_TextField;
    private javax.swing.JLabel Penjual_ID;
    private javax.swing.JLabel Product_Btn;
    private javax.swing.JPanel Seacrh_Bar_Panel;
    private javax.swing.JLabel Search_Bar_Label;
    private javax.swing.JLabel Search_Btn;
    public static final javax.swing.JTextField Sellerid_TextField = new javax.swing.JTextField();
    private javax.swing.JLabel Simpan_Tombol;
    private javax.swing.JLabel Simpan_Tombol1;
    private javax.swing.JLabel Simpan_Tombol2;
    private javax.swing.JLabel Stok_Text;
    private javax.swing.JTextField Stok_TextField;
    private javax.swing.JComboBox<String> Subkategori_Item;
    private javax.swing.JLabel TelpHome_Text;
    private javax.swing.JLabel TelpHome_Text1;
    private javax.swing.JTextField TelpHome_TextField;
    public static final javax.swing.JTextField TelpHome_TextField1 = new javax.swing.JTextField();
    private javax.swing.JLabel Telusuri_Produk_Label;
    private javax.swing.JLabel Tentang_Btn;
    private javax.swing.JLabel Tinggi_Text;
    private javax.swing.JTextField Tinggi_TextField;
    private javax.swing.JLabel Twitter_Klik2;
    private javax.swing.JTextField id_product;
    private javax.swing.JTextField id_sctg;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    
   
}

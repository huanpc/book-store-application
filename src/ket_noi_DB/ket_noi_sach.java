/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ket_noi_DB;

import giaodien.Kho_Sach;
import goiSP.Sach;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sony
 */
public class ket_noi_sach extends ket_noi {
    private String tableName = "kho_sach";
    
    public void deleteId(int id){
    String query = "DELETE FROM "+tableName+" WHERE `id`=?;";
    PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1,id);
            if(pst.executeUpdate() > 0){
                System.out.println("Xóa Thành Công!");
            }else{
                System.out.println("Lỗi Xóa \n");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kho_Sach.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insert_into_sach(Sach newSach){
    String query = "INSERT INTO "+tableName+" (`Tên SP`, `Mã SP`,"
                + " `Tên T/G`, `Thể Loại`,`Giá Bán`, `Số Lượng`, `Ngày Nhập`,  "
                + "`Số Phiếu`, `Chiết Khấu`) VALUES (?,?,?,?,?,?,?,?,?);";
    PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1,newSach.getTenSP());
            pst.setString(2,newSach.getMaSP());
            pst.setString(3,newSach.getTenTacGia());
            pst.setString(4,newSach.getTheLoai());
            pst.setDouble(5,newSach.getGiaBan());
            pst.setInt(6,newSach.getSoLuong());
            pst.setString(7,newSach.getNgayNhap());
            pst.setString(8,newSach.getSoPhieu());
            pst.setInt(9,newSach.getChietKhau());
            if (pst.executeUpdate() > 0) {
                System.out.println("Thêm thành công");
            }
            else {
                System.out.println("Lỗi khi thêm sản phẩm\n");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kho_Sach.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update_sach(Sach newSach,int id){
    String query = "UPDATE "+tableName+" SET `Tên SP`=?, `Mã SP`=?, "
               + "`Tên T/G`=?, `Thể Loại`=?, `Giá Bán`=?, `Số Lượng`=?, `Ngày Nhập`=?, "
               + "`Số Phiếu`=?, `Chiết Khấu`=? WHERE `id`=?;";
    PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1,newSach.getTenSP());// set các giá trị cho value
            pst.setString(2,newSach.getMaSP());
            pst.setString(3,newSach.getTenTacGia());
            pst.setString(4,newSach.getTheLoai());
            pst.setDouble(5,newSach.getGiaBan());
            pst.setInt(6,newSach.getSoLuong());
            pst.setString(7,newSach.getNgayNhap());
            pst.setString(8,newSach.getSoPhieu());  
            pst.setInt(9,newSach.getChietKhau());  
            pst.setInt(10,id);  
            if(pst.executeUpdate() > 0){
                System.out.println("Cập Nhập thành công!");
            }
            else{
            	System.out.println("Lỗi Cập nhật \n");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kho_Sach.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void reload_table(JTable tb){
      ResultSet rs = null;
      String query = "Select * from "+tableName+" ;";
      Statement st = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("Lỗi Select!!!");
        }
      DefaultTableModel model = new DefaultTableModel();
        try {
            ResultSetMetaData rsMD = rs.getMetaData();
            int colNumber = rsMD.getColumnCount();//lấy số cột của bảng
            String [] array = new String[colNumber];//lấy 1 mảng để lưu các tên cột
            for (int i = 0; i < colNumber; i++) {
                array[i] = rsMD.getColumnName(i+1);
            }
            model.setColumnIdentifiers(array);// set model cho table
            while(rs.next()){
                for(int i =0; i<colNumber; i++){
                    array[i] = rs.getString(i+1);
                }
                model.addRow(array);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kho_Sach.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      tb.setModel(model);
      
      tb.getColumnModel().getColumn(0).setPreferredWidth(199);
      tb.getColumnModel().getColumn(1).setPreferredWidth(49);
      tb.getColumnModel().getColumn(2).setPreferredWidth(129);
      tb.getColumnModel().getColumn(3).setPreferredWidth(99);
      tb.getColumnModel().getColumn(4).setPreferredWidth(79);
      tb.getColumnModel().getColumn(5).setPreferredWidth(79);
      tb.getColumnModel().getColumn(6).setPreferredWidth(79);
      tb.getColumnModel().getColumn(7).setPreferredWidth(79);
      tb.getColumnModel().getColumn(8).setPreferredWidth(79);
      tb.getColumnModel().getColumn(9).setPreferredWidth(27);
}
    
    public void reload_searching_table(String keyWord, int hesoCBB, JTable tb){
       ResultSet rs = null;
       String query = null;
       Statement st = null;
       if(hesoCBB == 0){// tìm kiếm tổng
           query = "Select * from "+tableName+" where `Tên SP` like \"%"+keyWord+"%\" UNION "
                   + "Select * from "+tableName+" where `Tên T/G` like \"%"+keyWord+"%\" UNION "
                   + "Select * from "+tableName+" where `Mã SP` like \"%"+keyWord+"%\" UNION "
                   + "Select * from "+tableName+" where `Thể Loại` like \"%"+keyWord+"%\";";
       }
       else if(hesoCBB == 1){// tìm kiếm theo tên SP
           query = "Select * from "+tableName+" where `Tên SP` like \"%"+keyWord+"%\";";
       }
       else if(hesoCBB == 2){// tìm kiếm theo tên T/G
           query = "Select * from "+tableName+" where `Tên T/G` like \"%"+keyWord+"%\";";
       }
       else if(hesoCBB == 3){// tìm kiếm theo mã SP
           query = "Select * from "+tableName+" where `Mã SP` like \"%"+keyWord+"%\";";
       }
       else if(hesoCBB == 4){// tìm kiếm theo thể loại
           query = "Select * from "+tableName+" where `Thể Loại` like \"%"+keyWord+"%\";";
       }
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(Kho_Sach.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      DefaultTableModel model = new DefaultTableModel();
      
      int colNumber = 0;
      ResultSetMetaData rsMD = null;
        try {
            rsMD = rs.getMetaData();
            colNumber = rsMD.getColumnCount();
            String [] array = new String[colNumber];
            for(int i = 0; i<colNumber;i++){
                array[i] = rsMD.getColumnName(i+1);
            }
            model.setColumnIdentifiers(array);
            while(rs.next()){
               for(int i =0; i<colNumber; i++){
                   array[i] = rs.getString(i+1);
               }
               model.addRow(array);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kho_Sach.class.getName()).log(Level.SEVERE, null, ex);
        }
      tb.setModel(model);
        
      tb.getColumnModel().getColumn(0).setPreferredWidth(199);
      tb.getColumnModel().getColumn(1).setPreferredWidth(49);
      tb.getColumnModel().getColumn(2).setPreferredWidth(129);
      tb.getColumnModel().getColumn(3).setPreferredWidth(99);
      tb.getColumnModel().getColumn(4).setPreferredWidth(79);
      tb.getColumnModel().getColumn(5).setPreferredWidth(79);
      tb.getColumnModel().getColumn(6).setPreferredWidth(79);
      tb.getColumnModel().getColumn(7).setPreferredWidth(79);
      tb.getColumnModel().getColumn(8).setPreferredWidth(79);
      tb.getColumnModel().getColumn(9).setPreferredWidth(27);
    }
}

package ket_noi_DB;

import goiNguoi.TaiKhoan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ket_noi_tk extends ket_noi{
    public static String tableName = "dang_nhap";
	
	//xoa du lieu tu bang theo id
	public void deleteId(String maNhanVien){
		String sqlCommand = "delete from " + tableName + " where `Mã Nhân Viên` = ?";
		PreparedStatement pst = null;
		try {
			pst =  conn.prepareStatement(sqlCommand);
			pst.setString(1, maNhanVien);
			if(pst.executeUpdate() > 0){
				System.out.println(" Xóa thành công!");
			}else{
				System.out.println(" Lỗi xóa \n");
			}
		} catch (SQLException e) {
			System.out.println("Lỗi xóa \n" + e.toString());
		}
	}
		
	//nhap du lieu vao bang
	public void insert(TaiKhoan u){
		String sqlCommand = "insert into " + tableName+ " value(?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pst = null;
		try {
			pst =  conn.prepareStatement(sqlCommand);
			pst.setString(1, u.getMaNhanVien());
			pst.setString(2, u.getTenNhanVien());
			pst.setString(3, u.getTenDangNhap());
			pst.setString(4, u.getMatKhau());
			pst.setString(5, u.getDiaChi());
			pst.setString(6, u.getSdt());
			pst.setString(7, u.getLoaiTaiKhoan());
			if(pst.executeUpdate() > 0){
				System.out.println(" Nhập thành công!");
			}else{
				System.out.println(" Lỗi nhập \n");
			}
		} catch (SQLException e) {
			System.out.println("Lỗi nhập \n" + e.toString());
		}
	}
	
	//cap nhat du lieu theo Id
	public void updateId(String MaNhanVien, TaiKhoan u){
		String sqlCommand = "update " + tableName
  			+ " set `Tên Nhân Viên` = ?, `Tên Đăng Nhập` = ?, `Mật Khẩu` = ?, `Địa Chỉ` = ?, `SĐT` = ?, `Loại Tài Khoản` = ? where `Mã Nhân Viên` = ?";
		PreparedStatement pst = null;
		try {
			pst =  conn.prepareStatement(sqlCommand);
			pst.setString(1, u.getTenNhanVien());
			pst.setString(2, u.getTenDangNhap());
			pst.setString(3, u.getMatKhau());
			pst.setString(4, u.getDiaChi());
			pst.setString(5, u.getSdt());
			pst.setString(6, u.getLoaiTaiKhoan());
			pst.setString(7, u.getMaNhanVien());
			if(pst.executeUpdate() > 0){
				System.out.println(" Cập nhật thành công!");
			}else{
				System.out.println(" Lỗi cập nhật \n");
			}
		} catch (SQLException e) {
			System.out.println("Lỗi cập nhật \n" + e.toString());
		}
	}
        
        public void reloadTable(JTable tb){
    	ResultSet rs = null;
		String sqlCommand = "select * from "  + tableName;
		Statement st;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			System.out.println("Lỗi nhập \n" + e.toString());
		}
    	DefaultTableModel model = new DefaultTableModel();
    	
    	try{
			ResultSetMetaData rsMD = rs.getMetaData();
			int colNumber = rsMD.getColumnCount();
			String[] array = new String[colNumber];
			for (int i = 0; i < colNumber; i++) {
				array[i] = rsMD.getColumnName(i + 1);
			}			
			model.setColumnIdentifiers(array);
			while (rs.next()) {
				for (int i = 0; i < colNumber; i++) {
					array[i] = rs.getString(i + 1);
				}

				model.addRow(array);
			}	
    	} catch (SQLException e) {
		}    	
    	tb.setModel(model);
    	tb.getColumnModel().getColumn(0).setPreferredWidth(100);
    	tb.getColumnModel().getColumn(1).setPreferredWidth(150);
    	tb.getColumnModel().getColumn(2).setPreferredWidth(120);
    	tb.getColumnModel().getColumn(3).setPreferredWidth(100);
    	tb.getColumnModel().getColumn(4).setPreferredWidth(120);
    	tb.getColumnModel().getColumn(5).setPreferredWidth(100);
    	tb.getColumnModel().getColumn(6).setPreferredWidth(100);  	
    }
        
        public void reloadSearchingTable(String keyWord, int cbIndex, JTable tb){
        ResultSet rs = null;
           String query = null;
           if(cbIndex == 0){
               query =   "Select * from "+tableName+" where `Mã Nhân Viên` like \"%"+keyWord+"%\" UNION "
                       + "Select * from "+tableName+" where `Tên Nhân Viên` like \"%"+keyWord+"%\" UNION "
                       + "Select * from "+tableName+" where `Tên Đăng Nhập` like \"%"+keyWord+"%\" UNION "
                       + "Select * from "+tableName+" where `Địa Chỉ` like \"%"+keyWord+"%\" UNION "
                       + "Select * from "+tableName+" where `Loại Tài Khoản` like \"%"+keyWord+"%\";";
                       
           }
           else if(cbIndex == 1){
               query = "Select * from "+tableName+" where `Mã Nhân Viên` like \"%"+keyWord+"%\";";
           }
           else if(cbIndex == 2){
               query = "Select * from "+tableName+" where `Tên Nhân Viên` like \"%"+keyWord+"%\";";
           }
           else if(cbIndex == 3){
               query = "Select * from "+tableName+" where `Tên Đăng Nhập` like \"%"+keyWord+"%\";";
           }
           else if(cbIndex == 4){
               query = "Select * from "+tableName+" where `Địa Chỉ` like \"%"+keyWord+"%\";";
           }       
           else if(cbIndex == 5){
               query = "Select * from "+tableName+" where `Loại Tài Khoản` like \"%"+keyWord+"%\";";
           }       
           Statement st = null;
           try {
               st = conn.createStatement();
               rs = st.executeQuery(query);
           } catch (SQLException e) {
           System.out.println("Lỗi nhập \n" + e.toString());
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
            }
        tb.setModel(model);
    	tb.getColumnModel().getColumn(0).setPreferredWidth(100);
    	tb.getColumnModel().getColumn(1).setPreferredWidth(150);
    	tb.getColumnModel().getColumn(2).setPreferredWidth(120);
    	tb.getColumnModel().getColumn(3).setPreferredWidth(100);
    	tb.getColumnModel().getColumn(4).setPreferredWidth(120);
    	tb.getColumnModel().getColumn(5).setPreferredWidth(100);
    	tb.getColumnModel().getColumn(6).setPreferredWidth(100);  
    }
}


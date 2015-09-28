/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ket_noi_excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import ket_noi_DB.ket_noi;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author sony
 */
public class ket_noi_excel_dvd{
    private final String ClassName = "com.mysql.jdbc.Driver";
    private final String Url = "jdbc:mysql://localhost:3306/oop";
    private final String User = "root";
    private final String Pass = "tanphan";
    private Connection conn;
    String tableDVD = "kho_dvd";
    public ket_noi_excel_dvd(){
        try {
            Class.forName(ClassName);
            conn = DriverManager.getConnection(Url,User, Pass);
            System.out.println("Kết nối thành công!");
        } catch (ClassNotFoundException ex) {
            System.out.println("Không tìm thấy class!");
        } catch (SQLException ex) {
            System.out.println("Lỗi kết nối!");
        }
    }
    
    public String returnType(String x){
        String x1 = x.substring(x.lastIndexOf("\\"), x.length());
        if (x1.lastIndexOf(".") == -1) {
            return null;
        } else {
            if (x1.substring(x1.lastIndexOf("."), x1.length()).equals(".xlsx")) {
                return "xlsx";
            } else if (x1.substring(x1.lastIndexOf("."), x1.length()).equals(".xls")) {
                return "xls";
            } else {
                return "wrongtype";
            }
        }
    }
    
    public boolean trungMaSP(String s){
        String query = "select * from "+tableDVD+" where `Mã SP` = "+s+"";
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            if(rs.getRow() !=0 ) return true;
        } catch (SQLException ex) {
            Logger.getLogger(ket_noi_excel_cd.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public void loadFile(String path,boolean isXLS){
        PreparedStatement pst = null;
        System.out.println("Đã chọn");
        try {
            org.apache.poi.ss.usermodel.Sheet sheet = null;
            if(isXLS == true) {
                org.apache.poi.ss.usermodel.Workbook workbook = new HSSFWorkbook(new FileInputStream(path));
                sheet = workbook.getSheetAt(0);
            }
            else {
                org.apache.poi.ss.usermodel.Workbook workbook = new XSSFWorkbook(new FileInputStream(path));
                sheet = workbook.getSheetAt(0);
            }
            for (Iterator<Row> rit = sheet.rowIterator(); rit.hasNext();){
                Row row = rit.next();
                int i = 1;
                String query = "INSERT INTO "+tableDVD+" (`Tên SP`, `Mã SP`, `Tên Diễn Viên`, `Tên Đạo Diễn`, "
               + "`Giá Bán`, `Số Lượng`, `Thể Loại`, `Năm Phát Hành`, `Ngày Nhập`, `Số Phiếu`, `Chiết Khấu`)"
               + " VALUES (?,?,?,?,?,?,?,?,?,?,?);";
                pst = conn.prepareStatement(query);
                for (Iterator<org.apache.poi.ss.usermodel.Cell> cit = row.cellIterator(); cit.hasNext();) {
                org.apache.poi.ss.usermodel.Cell cell = cit.next();
                cell.setCellType(org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
                    if(i == 5) pst.setDouble(i,Double.parseDouble(cell.getRichStringCellValue().toString()));
                    else if(i == 6 || i == 11) pst.setInt(i,Integer.parseInt(cell.getRichStringCellValue().toString()));
                    else pst.setString(i,cell.getRichStringCellValue().toString());
                    i++;
                    if(i>=12) break;
                }
                        if (pst.executeUpdate() > 0) {
                            System.out.println("Thêm thành công");
                        }
                        else {
                            System.out.println("Lỗi khi thêm sản phẩm\n");
                        }
            }
        } catch (IOException ex) {
            Logger.getLogger(ket_noi_excel_cd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ket_noi_excel_cd.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Đã thêm dữ liệu thành công");
    }
    
    public void saveFile(JTable tb, String path){
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("kho dvd");
        int numrow = 0;
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tb.getModel();
        Vector vt =  model.getDataVector();// lấy đối tượng vector của model, chưa toàn bộ dữ liệu
        for(int i = 0;i<tb.getRowCount();i++){
            Vector vtt = (Vector) vt.get(i);//lấy dòng i
            Row row = sheet.createRow(numrow);
            sheet.setColumnWidth(numrow, 5000);
            for(int j = 0; j<tb.getColumnCount()-1;j++){// trừ đi cột id ở cuối cùng để đề phòng khi import lại ko có lỗi out of range
                row.createCell(j).setCellValue(vtt.get(j).toString());
            }
            numrow++;
        }
        try {
            FileOutputStream fout = new FileOutputStream(path);
            JOptionPane.showMessageDialog(null, "Dữ liệu đã được đưa ra file excel");
            workbook.write(fout);
            fout.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ket_noi_excel_cd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ket_noi_excel_cd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

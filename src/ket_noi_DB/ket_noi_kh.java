/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ket_noi_DB;

import giaodien.Khach_Hang;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Dai
 */
public class ket_noi_kh extends ket_noi {

    public Statement statement;
    public ResultSet data;
    public ResultSetMetaData metadata;

    public ket_noi_kh() {
        super.connect_to_DB();
        try {
            statement = (Statement) conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("Lỗi statment");
            Logger.getLogger(ket_noi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // truy vấn Sql và trả về dữ liệu cho bảng

    public void truyVanSQL(Vector tableTitle1, Vector tableRecords1, JTable tableList1, String sql) {
        tableRecords1.clear();
        tableList1.setModel(new DefaultTableModel(tableRecords1, tableTitle1));
        try {
            data = statement.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("lỗi select dữ liệu ban đầu");
            Logger.getLogger(ket_noi_kh.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            metadata = data.getMetaData();
            while (data.next()) {
                Vector record = new Vector();
                for (int i = 1; i <= metadata.getColumnCount(); i++) {
                    record.add(data.getString(i));
                }
                tableRecords1.add(record);
                tableList1.setModel(new DefaultTableModel(tableRecords1, tableTitle1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ket_noi_kh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // lưu file excel khi export cơ sở dữ liệu ra
    public void luuFile(Workbook workbook, String path, String sql, String tenCSDL) {
        try {
            data = statement.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Khach_Hang.class.getName()).log(Level.SEVERE, null, ex);
        }
        Sheet sheet1 = workbook.createSheet(tenCSDL);
        try {
            metadata = data.getMetaData();
            int numrow = 0;
            while (data.next()) {
                Row row = sheet1.createRow(numrow);
                sheet1.setColumnWidth(numrow, 5000);
                for (int i = 1; i <= metadata.getColumnCount(); i++) {
                    row.createCell(i - 1).setCellValue(data.getString(i));
                }
                numrow++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Khach_Hang.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            FileOutputStream fout = new FileOutputStream(path);
            workbook.write(fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // LOAD file excel vào cơ sở dữ liệu của mình
    public void loadFile(Workbook workbook, String tenCSDL ) {
        Sheet sheet = workbook.getSheetAt(0);
        int num = 0;
        for (Iterator<Row> rit = sheet.rowIterator(); rit.hasNext();) {
            String sql = "INSERT INTO " + tenCSDL + " (makh, tenkh, sdt, diachi, tichdiem) VALUES (";
            Row row = rit.next();
            for (Iterator<Cell> cit = row.cellIterator(); cit.hasNext();) {
                Cell cell = cit.next();
                cell.setCellType(Cell.CELL_TYPE_STRING);
                sql = sql + "'" + cell.getRichStringCellValue().toString() + "'" + ",";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql = sql + ")";
            try {
                statement.executeUpdate(sql);
            } catch (SQLException ex) {
                // lỗi lệnh truy vấn, do trùng lặp mã khách hàng
                num++;
            }
        }
        JOptionPane.showMessageDialog(null, "Đã thêm dữ liệu, có " + num + " mã khách hàng bị trùng không được thêm");
    }
}

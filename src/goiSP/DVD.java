package goiSP;

import java.util.Scanner;

public class DVD extends SanPham{
        private String tenDienVien;
        private String tenDaoDien;
        private String namPhatHanh;
    public DVD(){
    }
    public DVD(String tenSP, String maSP, int soLuong, String theLoai, String ngayNhap, double giaBan, String soPhieu,int chietKhau, String tenDienVien, String tenDaoDien, String namPhatHanh) {
        super(tenSP, maSP, soLuong, theLoai, ngayNhap, giaBan, soPhieu,chietKhau);
        this.namPhatHanh = namPhatHanh;
        this.tenDaoDien = tenDaoDien;
        this.tenDienVien = tenDienVien;
    }

    public String getTenDienVien() {
        return tenDienVien;
    }

    public void setTenDienVien(String tenDienVien) {
        this.tenDienVien = tenDienVien;
    }

    public String getTenDaoDien() {
        return tenDaoDien;
    }

    public void setTenDaoDien(String tenDaoDien) {
        this.tenDaoDien = tenDaoDien;
    }

    public String getNamPhatHanh() {
        return namPhatHanh;
    }

    public void setNamPhaHanh(String namPhaHanh) {
        this.namPhatHanh = namPhaHanh;
    }
        
        
}

package goiSP;

import java.util.Scanner;

public class CD extends SanPham{
        private String tenCaSi;
        private String tenNhacSi;
        private String namPhatHanh;
    public CD(){
    }
    public CD(String tenSP, String maSP, int soLuong, String theLoai, String ngayNhap, double giaBan, String soPhieu,int chietKhau, String tenCaSi, String tenNhacSi, String namPhatHanh) {
        super(tenSP, maSP, soLuong, theLoai, ngayNhap, giaBan, soPhieu, chietKhau);
        this.tenCaSi = tenCaSi;
        this.tenNhacSi = tenNhacSi;
        this.namPhatHanh = namPhatHanh;
    }


    public String getTenCaSi() {
        return tenCaSi;
    }

    public void setTenCaSi(String tenCaSi) {
        this.tenCaSi = tenCaSi;
    }

    public String getTenNhacSi() {
        return tenNhacSi;
    }

    public void setTenNhacSi(String tenNhacSy) {
        this.tenNhacSi = tenNhacSy;
    }

    public String getNamPhatHanh() {
        return namPhatHanh;
    }

    public void setNamPhatHanh(String namPhatHanh) {
        this.namPhatHanh = namPhatHanh;
    }

}

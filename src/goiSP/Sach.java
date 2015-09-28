package goiSP;

import java.util.Scanner;

public class Sach extends SanPham{
        private String tenTacGia;
    public Sach(){    
    }
    public Sach(String tenSP, String maSP, int soLuong, String theLoai, String ngayNhap, double giaBan, String tenTacGia, String soPhieu, int chietKhau){
        super(tenSP, maSP, soLuong, theLoai, ngayNhap, giaBan, soPhieu, chietKhau);
        this.tenTacGia = tenTacGia;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }
    
    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }
}

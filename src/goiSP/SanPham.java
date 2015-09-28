package goiSP;

import java.util.Scanner;
/*

*/
public class SanPham {
    
    protected int chietKhau;
    
    protected String tenSP;
    protected String maSP;
    protected int soLuong;
    protected String theLoai;
    protected String ngayNhap;
    protected double giaBan;
    protected String soPhieu;
    public SanPham(){
    }
    public SanPham(String tenSP, String maSP, int soLuong, String theLoai, String ngayNhap, double giaBan, String soPhieu,int chietKhau){
        this.tenSP = tenSP;
        this.maSP  = maSP;
        this.soLuong = soLuong;
        this.theLoai = theLoai;
        this.ngayNhap = ngayNhap;
        this.giaBan = giaBan;
        this.soPhieu = soPhieu;
        this.chietKhau = chietKhau;
    }

    public int getChietKhau() {
        return chietKhau;
    }

    public void setChietKhau(int chietKhau) {
        this.chietKhau = chietKhau;
    }
    
    
    public String getTenSP() {
        return tenSP;
    }

    public String getMaSP() {
        return maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public String getSoPhieu() {
        return soPhieu;
    }

    public void setSoPhieu(String soPhieu) {
        this.soPhieu = soPhieu;
    }
}

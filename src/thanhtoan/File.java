/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thanhtoan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author My PC
 */
public class File {
     DataOutputStream fout = null;
    FileWriter out = null;
    BufferedWriter writer = null;
    String TenFile;
    FileReader in = null;
    BufferedReader reader = null;
    int soHoaDon=0;

    public String docMaHoaDon() {
        
        String maHoaDon = "MH0000000001";
        try {
            in = new FileReader("data/SoHoaDon.txt");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            reader = new BufferedReader(in);
            maHoaDon = reader.readLine();
            soHoaDon=Integer.parseInt(maHoaDon);
            maHoaDon = "MH" + maHoaDon;
        } catch (IOException e) {
            e.getMessage();
        }
        System.out.println("Doc thanh cong");
        return maHoaDon;
    }

    public void ghiMaHoaDon(String maHoaDon) throws IOException {
        char[] strTemp = new char[10];
        maHoaDon.getChars(2, 12, strTemp, 0);
        int number = Integer.parseInt(String.copyValueOf(strTemp));
        number++;
        String StringToWrite = Integer.toString(number);
        int n = StringToWrite.length();

        for (int i = 0; i < 10 - n; i++) {
            StringToWrite = "0" + StringToWrite;
        }

        try {
            out = new FileWriter("data/SoHoaDon.txt");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            writer = new BufferedWriter(out);
            writer.write(StringToWrite);
        } catch (IOException e) {
            e.getMessage();
        }
        writer.close();
        out.close();
        System.out.println("Ghi ma thanh cong");
    }

    public void ghiHoaDonFileText(HoaDon hoadon) throws IOException {
        TenFile = "data/" + hoadon.maHoaDon + ".txt";
        try {
            out = new FileWriter(TenFile);
            writer = new BufferedWriter(out);
            writer.write("Mã Hóa Đơn: " + hoadon.maHoaDon);
            writer.newLine();
            writer.write(hoadon.thoiGianGiaoDich);
            writer.newLine();
            writer.write("Nhân Viên: " + hoadon.tenNhanVien+"Mã NV: "+hoadon.maNhanVien );
            writer.newLine();
            String title[] = {"STT |", "Tên Sản Phẩm    |", "Mã |", "Giá |", "Số Lượng |", "Tổng tiền |", "Giảm giá |", "Thành Tiền |"};
            for (int j = 0; j < 8; j++) {
                writer.write(title[j] + "   ");
            }
            writer.newLine();
            for (SanPham SanPham : hoadon.listSanPham) {
                writer.write(SanPham.stt);
                writer.write("   |");
                writer.write(SanPham.ten);
                writer.write("   |");
                writer.write(SanPham.maSP);
                writer.write("   |");
                writer.write(SanPham.donGia);
                writer.write("   |");
                writer.write(SanPham.soLuong);
                writer.write("          |");
                writer.write(SanPham.tongTien);
                writer.write("      |");
                writer.write(SanPham.chietKhau);
                writer.write("      |");
                writer.write(SanPham.thanhTien);
               
                writer.newLine();
            }
            writer.write("Tổng: " + ThanhToan.dinhDangTien((int) hoadon.tongTienHoaDon));
            writer.newLine();
            writer.write("Giảm Sản Phẩm: " + ThanhToan.dinhDangTien((int) hoadon.giamSP));
            writer.newLine();
            writer.write("Giảm VIP: " + ThanhToan.dinhDangTien((int) hoadon.giamVIP));
            writer.newLine();
            writer.write("Thanh toán: " + ThanhToan.dinhDangTien((int) hoadon.tongTienPhaiTra));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        writer.close();
        out.close();
        System.out.println("Ghi thanh cong");
    }
    
}

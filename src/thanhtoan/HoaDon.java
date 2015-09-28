/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thanhtoan;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import ket_noi_DB.ket_noi;

/**
 *
 * @author My PC
 */
public class HoaDon {
    public ArrayList<SanPham> listSanPham;
    public float giamSP, tongTienHoaDon, tongTienPhaiTra, giamVIP;
    public String maKhachHang=" ";
    public int id = 0;
    public String maNhanVien,tenNhanVien;
    public String maHoaDon = "MH" + id;
    File file = new File();
    String thoiGianGiaoDich;
    ket_noi ketNoi = new ket_noi();
    public HoaDon(String maNhanVien,String tenNhanVien) {
        listSanPham = new ArrayList();
        maHoaDon = file.docMaHoaDon();
        this.maNhanVien=maNhanVien;
        this.tenNhanVien=tenNhanVien;
        ketNoi.connect_to_DB();
    }
    public HoaDon() {
        listSanPham = new ArrayList();
        maHoaDon = file.docMaHoaDon();
        ketNoi.connect_to_DB();
    }
    public void tinhTien() {
        giamSP = 0;
        tongTienHoaDon = 0;
        tongTienPhaiTra = 0;
        
        for (int i = 0; i < this.listSanPham.size(); i++) {

            giamSP += Float.parseFloat(this.listSanPham.get(i).chietKhau);
            tongTienHoaDon += Float.parseFloat(this.listSanPham.get(i).tongTien);
        }
        tongTienHoaDon-=giamSP;
//        giamVIP = (tongTienHoaDon) * giamVIP;
        tongTienPhaiTra = tongTienHoaDon - tongTienHoaDon * giamVIP; 
        ThanhToan.tfGiamVIP.setText(ThanhToan.dinhDangTien((int) (tongTienHoaDon * giamVIP)));
        ThanhToan.tfTongTienHoaDon.setText(ThanhToan.dinhDangTien((int) this.tongTienHoaDon));
        ThanhToan.tfTongTienPhaiTra.setText(ThanhToan.dinhDangTien((int) this.tongTienPhaiTra));
        ThanhToan.tfGiamSP.setText(ThanhToan.dinhDangTien((int) this.giamSP));
        
    }

    public void themSanPham(SanPham sanphamthem) {
        sanphamthem.stt = Integer.toString(this.listSanPham.size() + 1);
        this.listSanPham.add(sanphamthem);
        this.tinhTien();
    }

    public void xoaSanPham(SanPham sanphamxoa) {
        this.listSanPham.remove(sanphamxoa);
        for (int i = 1; i < listSanPham.size() + 1; i++) {
            listSanPham.get(i - 1).stt = Integer.toString(i);
            listSanPham.get(i - 1).DanhMuc[0] = listSanPham.get(i - 1).stt;
        }
        this.tinhTien();
    }
//GIAM SO LUONG SAN PHAM TRONG KHO MA DA BAN CHO KHACH 

    public void giamSoLuongTrongKho() {
        for (SanPham sanpham : listSanPham) {
            String query = "UPDATE " + sanpham.LoaiKho
                    + " SET  `Số Lượng`=? WHERE  `Mã SP` like \"%" + sanpham.maSP + "%\";";
            PreparedStatement pst = null;
            int soluong = sanpham.SoLuongTrongKho - Integer.parseInt(sanpham.soLuong);
            try {
                pst = ketNoi.conn.prepareStatement(query);
                pst.setString(1, Integer.toString(soluong));// set các giá trị cho value
                if (pst.executeUpdate() > 0) {
                    System.out.println("Cập Nhập thành công!");
                } else {
                    System.out.println("Lỗi Cập nhật \n");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                
            }
        }
    }
}


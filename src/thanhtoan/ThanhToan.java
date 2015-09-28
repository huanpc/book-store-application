package thanhtoan;

import giaodien.Giao_Dien_Nguoi_Dung;
import giaodien.Khach_Hang;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import ket_noi_DB.*;

public class ThanhToan extends JPanel {

    private final String titleColumn[];
    JTable tbBangThanhToan;
    static JTextField tfTimKiem, tfSoLuong, tfGiaSanPham, tfChietKhau, tfTongTienPhaiTra, tfMaHoaDon,
            tfTienKhachDua, tfTienThua, tfTongTienHoaDon, tfGiamSP, tfCanThanhToan, tfThueGTGT, tfGiamVIP, tfNgayTaoHoaDon, tfNhanVien, tfMaNhanVien,tfVIP;
    JLabel lbNhapMa, lbSoLuong, lbTongTien, lbGiamSP, lbTongTienTT, lbGiaSanPham,
            lbChietKhau, lbCanThanhToan, lbKhachHang, lbMaKhachHang,
            lbTongTienHoaDon, lbTongTienPhaiTra, lbTienKhachDua, lbTienThua,
            lbGiamVIP, lbThueGTGT,
            lbNgayTaoHoaDon, lbMaHoaDon, lbNhanVien, lbMaNhanVien
            ,lbDollar;
    JButton btThem, btKetThucHoaDon, btXoaSanPham, btHuyHoaDon, btThoat;
    DefaultTableModel tableModel;
    
    SanPham tmp;
    HoaDon hoaDon;
    static int n = 0;
    File file;
    Date date = new Date();
    private ImageIcon icon_dollar = new ImageIcon("src//image//dollar.png");
    private ImageIcon icon_check = new ImageIcon("src//image//check.png");
    private ImageIcon icon_hd = new ImageIcon("src//image//hoa_don.png");
    private ImageIcon icon_save = new ImageIcon("src//image//save_icon.png");
    private ImageIcon icon_delete = new ImageIcon("src//image//delete_icon.png");
    private ImageIcon icon_exit = new ImageIcon("src//image//exit_icon.png");
    SimpleDateFormat ftDateTime
            = new SimpleDateFormat(" yyyy/MM/dd  hh:mm a ");
    SimpleDateFormat ftDate
            = new SimpleDateFormat(" yyyy/MM/dd");
    
    ket_noi ketNoi = new ket_noi();
    ket_noi_kh kn = new ket_noi_kh();
    Color newColor = new Color(56, 170, 209);
    Color newColor2 = new Color(105,216,45);
    JTextField  tfMaKhachHang;
    public ThanhToan(String maNhanVien, String tenNhanVien) {
        this.titleColumn = new String[]{"STT", "Tên Sản Phẩm", "Mã Sản Phẩm", "Đơn Giá", "Số Lượng", "Tổng Tiền", "Giảm Giá", "Thành Tiền"};
        this.setVisible(false);
        this.setBackground(newColor);
        this.setBounds(140, 150, 990, 510);
        this.setLayout(null);

        ketNoi.connect_to_DB();
        tmp = new SanPham();
        hoaDon = new HoaDon(maNhanVien, tenNhanVien);
        System.out.println("tên: "+hoaDon.tenNhanVien+"mã: "+hoaDon.maNhanVien);
        tfMaHoaDon = new JTextField();
        tfMaHoaDon.setText(hoaDon.maHoaDon);
        tfMaHoaDon.setEnabled(false);
        file = new File();
//==============================================================================
//TOP LEFT
        lbNhapMa = new JLabel("Nhập Mã");
        lbNhapMa.setBounds(10, 10, 54, 30);
        add(lbNhapMa);

        tfTimKiem = new JTextField();
        tfTimKiem.setBounds(10 + 54 + 10, 10, 206, 30);
        add(tfTimKiem);

        lbSoLuong = new JLabel("Số Lượng");
        lbSoLuong.setBounds(10 + 54 + 206 + 10 + 10, 10, 70, 30);
        add(lbSoLuong);

        tfSoLuong = new JTextField();
        tfSoLuong.setBounds(10 + 54 + 206 + 10 + 70 + 10, 10, 70, 30);
        add(tfSoLuong);
        btThem = new JButton("Thêm");
        btThem.setBounds(10 + 54 + 206 + 10 + 70 + 10 + 70 + 10, 10, 85, 30);
        add(btThem);

//XỬ LÝ BUTTON THÊM SẢN PHẨM
        btThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int soluong = 0;
                String maSP;
                if (tfTimKiem.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Bạn chưa nhập mã sản phẩm! ");
                } else {
                    maSP = tfTimKiem.getText();
                    if (tfSoLuong.getText().isEmpty() || Integer.parseInt(tfSoLuong.getText()) == 0) {
                        JOptionPane.showMessageDialog(null, "Bạn chưa nhập số lượng! ");
                    } else {
                        soluong = Integer.parseInt(tfSoLuong.getText());
                        tmp = timSanPhamTheoMa(maSP, soluong);
                        if (tmp != null) {
                            if (tmp.SoLuongTrongKho < soluong) {
                                tfSoLuong.setText(null);
                                JOptionPane.showMessageDialog(null, "Trong kho chỉ còn " + tmp.SoLuongTrongKho + ", mời bạn nhập lại số lượng!");
                                tfSoLuong.setText("");
                            } else {
                                tmp.soLuong = Integer.toString(soluong);
                                tmp.tongTien = Integer.toString(soluong * Integer.parseInt(tmp.donGia));
                                float chietKhau = Float.parseFloat(tmp.chietKhau);
                                tmp.chietKhau = Integer.toString((int) ((float) chietKhau / 100 * Integer.parseInt(tmp.tongTien)));
                                tmp.thanhTien = Integer.toString((int) ((1 - (float) chietKhau / 100) * Integer.parseInt(tmp.tongTien))); //Thành tiền                                            
                                hoaDon.themSanPham(tmp);
                                int sosanpham = hoaDon.listSanPham.size() - 1;
                                hoaDon.listSanPham.get(sosanpham).DanhMuc[0] = tmp.stt;
                                hoaDon.listSanPham.get(sosanpham).DanhMuc[1] = tmp.ten;
                                hoaDon.listSanPham.get(sosanpham).DanhMuc[2] = tmp.maSP;
                                hoaDon.listSanPham.get(sosanpham).DanhMuc[3] = tmp.donGia;
                                hoaDon.listSanPham.get(sosanpham).DanhMuc[4] = tmp.soLuong;
                                hoaDon.listSanPham.get(sosanpham).DanhMuc[5] = tmp.tongTien;
                                hoaDon.listSanPham.get(sosanpham).DanhMuc[6] = tmp.chietKhau;
                                hoaDon.listSanPham.get(sosanpham).DanhMuc[7] = tmp.thanhTien;
                                tableModel.addRow(hoaDon.listSanPham.get(sosanpham).DanhMuc);
                                tbBangThanhToan.setModel(tableModel);
                                maSP = null;
                                soluong = 0;
                                clear();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Sai ma san pham");
                            clear();
                        }

                    }
                }
            }
        });
//==============================================================================
//TOP RIGHT
        //Panel ghi mã khách hàng
        JPanel pnKhachHang = new JPanel();
        pnKhachHang.setBounds(10 + 54 + 206 + 10 + 70 + 10 + 70 + 10 + 85 + 10, 10, 990 - 13 - (10 + 54 + 206 + 10 + 70 + 10 + 70 + 10 + 85 + 10), (30 + 10) * 3 + 30 - 38);//(x,y,445,150)
        pnKhachHang.setBackground(newColor2);
        pnKhachHang.setLayout(null);
        add(pnKhachHang);

        lbKhachHang = new JLabel("Khách Hàng");
        lbKhachHang.setBounds(20, 150 / 4 - 30 / 2 - 20, 100, 30);

        JComboBox cbKhachHang = new JComboBox();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("Khách Vãng Lai");
        model.addElement("Khách Thân Thiết");
        cbKhachHang.setBounds(20 + 100 + 20, 150 / 4 - 30 / 2 - 20, 200, 30);
        cbKhachHang.setModel(model);

        lbMaKhachHang = new JLabel("Mã KH");
        lbMaKhachHang.setBounds(20, 150 / 2 - 30 / 2 - 20, 100, 30);
        lbMaKhachHang.setVisible(true);

        tfMaKhachHang = new JTextField();
        tfMaKhachHang.setBounds(20 + 100 + 20, 150 / 2 - 30 / 2 - 20, 200, 30);
        tfMaKhachHang.setVisible(true);
        tfMaKhachHang.setEnabled(false);
        
        tfVIP = new JTextField();
        tfVIP.setBounds(20 + 100 + 20, 3 * 150 / 4 - 30 / 2 - 20, 200, 30);
        tfVIP.setVisible(true);
        tfVIP.setEnabled(false);
        
        JButton btcheckMaKhachHang = new JButton("");
        btcheckMaKhachHang.setIcon(icon_check);
        btcheckMaKhachHang.setBounds(20 + 100 + 20 + 200 +10, 150 / 2 - 30 / 2 - 20, 80, 30);
        btcheckMaKhachHang.setVisible(false);
//---------
        btcheckMaKhachHang.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tfMaKhachHang.getText().isEmpty()) {
                    //Hàm kiểm tra mã khách hàng có là vip hay k đặt ở đây
                    //Nếu là vip gắn % trả về cho hoaDon.GiamVIP
                    float giamgia = 0;
        try {
                    ResultSet x = kn.statement.executeQuery("select giamgia from vip,khach_hang"
                            + " where khach_hang.tichdiem >= vip.tichdiem"
                            + " and khach_hang.makh = '"+tfMaKhachHang.getText()+"'"
                            + " order by giamgia desc"
                            + " limit 1");
                    if (x.next()) {
                        giamgia = (x.getInt(1));
                    }else{
                        giamgia = 0; // cái này tượng trưng, ý là k tìm thấy mức nào thì trả về  0
                    }
                } catch (SQLException ex) {
                    System.out.println("Lỗi lấy giá được giảm");
                    Logger.getLogger(Khach_Hang.class.getName()).log(Level.SEVERE, null, ex);
                }
                    hoaDon.giamVIP = giamgia/100;
                    hoaDon.tinhTien();
                    tfVIP.setText("VIP "+ giamgia);
                }
            }
        });

       

        //bắt sự kiện combo box
        cbKhachHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox newComBox = (JComboBox) e.getSource();
                int heso = newComBox.getSelectedIndex();
                if (heso == 0) {
                    lbMaKhachHang.setVisible(true);
                    tfMaKhachHang.setVisible(true);
                    tfMaKhachHang.setEnabled(false);
                    tfVIP.setVisible(true);
                    tfVIP.setEnabled(false);
                    btcheckMaKhachHang.setVisible(false);
                } else {
                    lbMaKhachHang.setVisible(true);
                    tfMaKhachHang.setVisible(true);
                    tfMaKhachHang.setEnabled(true);
                    tfVIP.setVisible(true);
                    tfVIP.setEnabled(false);
                    btcheckMaKhachHang.setVisible(true);
                }
            }
        });

        pnKhachHang.add(lbKhachHang);
        pnKhachHang.add(cbKhachHang);
        pnKhachHang.add(lbMaKhachHang);
        pnKhachHang.add(tfMaKhachHang);
        pnKhachHang.add(tfVIP);
        pnKhachHang.add(btcheckMaKhachHang);
//==============================================================================
//TOP MIDDLE
        //hàng 1
        lbNgayTaoHoaDon = new JLabel("Ngày Tạo Hóa Đơn");
        lbNgayTaoHoaDon.setBounds(10, 10 + 30 + 10, 110, 30);
        add(lbNgayTaoHoaDon);

        tfNgayTaoHoaDon = new JTextField();
        tfNgayTaoHoaDon.setBounds(10 + 110 + 10, 10 + 30 + 10, 150, 30);
        tfNgayTaoHoaDon.setText(ftDate.format(date));
        tfNgayTaoHoaDon.setEnabled(false);
        add(tfNgayTaoHoaDon);

        lbMaHoaDon = new JLabel("Mã Hóa Đơn");
        lbMaHoaDon.setBounds(10 + 110 + 10 + 150 + 10, 10 + 30 + 10, 80, 30);
        add(lbMaHoaDon);

        tfMaHoaDon.setBounds(10 + 110 + 10 + 150 + 10 + 80 + 10, 10 + 30 + 10, 145, 30);
        add(tfMaHoaDon);
        //hàng 2
        lbNhanVien = new JLabel("Nhân Viên");
        lbNhanVien.setBounds(10, 10 + (30 + 10) * 2, 60, 30);
        add(lbNhanVien);

        tfNhanVien = new JTextField();
        tfNhanVien.setBounds(10 + 60 + 10, 10 + (30 + 10) * 2, 200, 30);
        tfNhanVien.setEnabled(false);
        add(tfNhanVien);
        tfNhanVien.setText(hoaDon.tenNhanVien);
        tfNhanVien.setEnabled(false);

        lbMaNhanVien = new JLabel("Mã Nhân Viên");
        lbMaNhanVien.setBounds(10 + 110 + 10 + 150 + 10, 10 + (30 + 10) * 2, 80, 30);
        add(lbMaNhanVien);
        tfMaNhanVien = new JTextField();
        tfMaNhanVien.setBounds(10 + 110 + 10 + 150 + 10 + 80 + 10, 10 + (30 + 10) * 2, 145, 30);
        tfMaNhanVien.setEnabled(false);
        add(tfMaNhanVien);
        tfMaNhanVien.setText(hoaDon.maNhanVien);

//==============================================================================
//MIDDLE - TABLE
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(titleColumn);

        tbBangThanhToan = new JTable(tableModel);
        tbBangThanhToan.setAlignmentY(RIGHT_ALIGNMENT);
        //================================================
        JScrollPane jsp = new JScrollPane();
        jsp.setViewportView(tbBangThanhToan);
        jsp.setBounds(10, 10 + (30 + 10) * 4 - 38, 970, 208);
        add(jsp, BorderLayout.CENTER);
//==============================================================================      
        btKetThucHoaDon = new JButton("Kết Thúc HĐ");
        btXoaSanPham = new JButton("Xóa Sản Phẩm");
        btHuyHoaDon = new JButton("Hủy Hóa Đơn");
        btThoat = new JButton("Thoát");
//XU LY BUTTON KET THUC HOA DON
        btKetThucHoaDon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clear(2);
                xoaBangHoaDon();
                hoaDon.thoiGianGiaoDich = ftDateTime.format(date);
                luuHoaDon(hoaDon);

                System.out.println(hoaDon.thoiGianGiaoDich);

                try {
                    file.ghiHoaDonFileText(hoaDon);
                    file.ghiMaHoaDon(hoaDon.maHoaDon);
                } catch (IOException ex) {
                    Logger.getLogger(ThanhToan.class.getName()).log(Level.SEVERE, null, ex);
                }
                hoaDon.giamSoLuongTrongKho();   //UPDATE SO LUONG TRONG KHO
                hoaDon = new HoaDon();
                tfMaHoaDon.setText(hoaDon.maHoaDon);
            }
        });
//XU LY BUTTON XOA SAN PHAM
        btXoaSanPham.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int rowselected = tbBangThanhToan.getSelectedRow();
                if (rowselected < 0) {
                    JOptionPane.showMessageDialog(null, "Chưa lựa chọn sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    hoaDon.xoaSanPham(hoaDon.listSanPham.get(rowselected));
                    reloadBangThanhToan();
//                  HoaDon.tinhTien();
                }
            }
        });

// XU LY BUTTON HUY HOA DON
        btHuyHoaDon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear(2);
                xoaBangHoaDon();
                hoaDon = new HoaDon();

            }
        });
//        
//        add(btKetThucHoaDon);
//        add(btXoaSanPham);
//        add(btHuyHoaDon);
//        add(btThoat);

//==============================================================================
//RIGHT BOTTOM      
        JPanel pnTien = new JPanel();
        pnTien.setBounds(10 + 445 + 20, 10 + (30 + 10) * 4 + 200 + 10 - 30, 503, 510 - (10 + (30 + 10) * 4 + 200 + 10) - 10 + 30);//(x,y,445,150)
        pnTien.setBackground(newColor2);
        pnTien.setLayout(null);
        add(pnTien);
        
        lbDollar = new JLabel();
        lbDollar.setBounds(10, 10, 100, 130);
        lbDollar.setIcon(icon_dollar);
        pnTien.add(lbDollar);

        lbTongTienHoaDon = new JLabel("Cộng Tiền Hàng");
        lbGiamVIP = new JLabel("Giảm VIP");
        lbTongTienPhaiTra = new JLabel("Tổng thanh toán(x1000 VND)");
        lbTongTienHoaDon.setHorizontalAlignment(JTextField.RIGHT);
        lbGiamVIP.setHorizontalAlignment(JTextField.RIGHT);
        lbTongTienPhaiTra.setHorizontalAlignment(JTextField.RIGHT);
        lbTienKhachDua = new JLabel("Tiền Khách Đưa");
        lbTienThua = new JLabel("Tiền Thừa");

        lbTongTienHoaDon.setBounds(120, 4, 170, 25);
        lbGiamVIP.setBounds(120, 4 + 30, 170, 25);
        lbTongTienPhaiTra.setBounds(120, 4 + 30 + 25, 170, 25);

        tfTienKhachDua = new JTextField();
        tfTienThua = new JTextField();
        tfTongTienHoaDon = new JTextField();
        tfGiamVIP = new JTextField();
        tfTongTienPhaiTra = new JTextField();
        tfTongTienHoaDon.setHorizontalAlignment(JTextField.RIGHT);
        tfGiamVIP.setHorizontalAlignment(JTextField.RIGHT);
        tfTongTienPhaiTra.setHorizontalAlignment(JTextField.RIGHT);

        tfTongTienHoaDon.setBounds(20 + 290 + 10, 4, 170, 25);
        tfGiamVIP.setBounds(20 + 290 + 10, 4 + 30, 170, 25);
        tfTongTienPhaiTra.setBounds(20 + 290 + 10, 4 + 30 + 30, 170, 25);

        lbTienKhachDua.setBounds(120, 4 + 30 * 3 - 5, 170, 25);
        lbTienThua.setBounds(200 + 120, 4 + 30 * 3 - 5, 170, 25);

        tfTienKhachDua.setBounds(120, 4 + 30 * 3 + 20, 170, 25);
        tfTienThua.setBounds(170 + 120+30, 4 + 30 * 3 + 20, 170, 25);

        pnTien.add(lbTongTienHoaDon);
        pnTien.add(lbGiamVIP);
        pnTien.add(lbTongTienPhaiTra);

        pnTien.add(tfTongTienHoaDon);
        pnTien.add(lbTienKhachDua);
        pnTien.add(lbTienThua);
        pnTien.add(tfTienKhachDua);
        pnTien.add(tfTienThua);
        pnTien.add(tfGiamVIP);
        pnTien.add(tfTongTienPhaiTra);
// XỬ LÝ SỰ KIỆN NHẬP TIỀN KHÁCH ĐƯA, TÍNH TIỀN THỪA
        tfTienKhachDua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tfTienKhachDua.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Bạn Chưa Nhập Số Tiền Khách Đưa!");
                } else {
                    float TienTra = Float.parseFloat(tfTienKhachDua.getText());
                    float TienThua = TienTra - hoaDon.tongTienPhaiTra;
                    tfTienThua.setText(dinhDangTien((int) TienThua));
                }
            }
        });
//==============================================================================
// LEFT BOTTOM
        JPanel pnGiamGia = new JPanel();
        pnGiamGia.setBounds(10, 10 + (30 + 10) * 4 + 200 + 10 - 30, 445, 510 - (10 + (30 + 10) * 4 + 200 + 10) - 10 + 30);//(x,y,445,150)
        pnGiamGia.setBackground(newColor2);
        pnGiamGia.setLayout(null);
        add(pnGiamGia);

        lbGiamSP = new JLabel("Giảm SP");
        tfGiamSP = new JTextField();
        
        JButton btXemHoaDonCu=new JButton();
        btXemHoaDonCu.setContentAreaFilled(false);
        btXemHoaDonCu.setFocusable(false);
        btXemHoaDonCu.setBorderPainted(false);
        btXemHoaDonCu.setIcon(icon_hd);
        btXemHoaDonCu.setBounds(35, 12, 150, 130);
        pnGiamGia.add(btXemHoaDonCu);
                
        lbGiamSP.setBounds(200, 4, 100, 25);
        tfGiamSP.setBounds(290, 4, 150, 25);
        

        btKetThucHoaDon.setBounds(230, 12, 170, 35);
        btKetThucHoaDon.setIcon(icon_save);
        btXoaSanPham.setBounds(230, 12+35+12, 170, 35);
        btXoaSanPham.setIcon(icon_delete);
        btHuyHoaDon.setBounds(230, 12+35*2+12*2, 170, 35);
        btHuyHoaDon.setIcon(icon_exit);
        
        pnGiamGia.add(btKetThucHoaDon);
        pnGiamGia.add(btHuyHoaDon);
        pnGiamGia.add(btXoaSanPham);
        
// XỬ LÝ SỰ KIỆN XEM HÓA ĐƠN CŨ
        //JFrame HoaDonCu = new JFrame();
        btXemHoaDonCu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               new HoaDonCu();
              
            }
        });
//        
        btThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }
//==============================================================================   

    public SanPham timSanPhamTheoMa(String masp, int soluong) {
        ResultSet rs = null;
        Statement st = null;
        SanPham sanpham = new SanPham();
        String query = "Select * from kho_sach where `Mã SP` like \"%" + masp + "%\";";
        try {
            st = ketNoi.conn.createStatement();
            rs = st.executeQuery(query);
            
            if (!rs.first()) {
                try {
                    st = ketNoi.conn.createStatement();
                    rs = st.executeQuery("Select * from kho_cd where `Mã SP` like \"%" + masp + "%\";");
                  
                    if (!rs.first()) {
                        try {
                            st = ketNoi.conn.createStatement();
                            rs = st.executeQuery("Select * from kho_dvd where `Mã SP` like \"%" + masp + "%\";");
                            if (!rs.first()) {
                                JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm trong kho!");
                                clear();
                                return null;
                            }
                            sanpham.LoaiKho = "kho_dvd";
                            sanpham.chietKhau = rs.getString(12);
                        } catch (SQLException ex) {
                            System.out.println("Lỗi Select1!!!");
                        }
                    } else {
                        sanpham.LoaiKho = "kho_cd";
                        sanpham.chietKhau = rs.getString(12);
                    }
                } catch (SQLException ex) {
                    System.out.println("Lỗi Select2!!!");
                }
            } else {

                sanpham.LoaiKho = "kho_sach";
                sanpham.chietKhau = rs.getString(10);
                
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi Select3!!!");
        }

        try {
            sanpham.stt = Integer.toString(0);
            sanpham.ten = rs.getString(1);
           
            sanpham.maSP = rs.getString(2);
            sanpham.donGia = rs.getString(5);
            sanpham.SoLuongTrongKho = Integer.parseInt(rs.getString(6));  // so luong co trong kho

        } catch (SQLException ex) {
            System.out.println("Lỗi đọc CSDL");
        }

        return sanpham;
    }
//==============================================================================    

    public void clear() {
        tfTimKiem.setText(null);
        tfSoLuong.setText(null);
    }
//==============================================================================

    public void clear(int x) {
        tfTongTienHoaDon.setText(null);
        tfGiamSP.setText(null);
        tfTongTienPhaiTra.setText(null);
        tfTienKhachDua.setText(null);
        tfTienThua.setText(null);
        tfGiamVIP.setText(null);

    }
//==============================================================================
    //RELOAD LAI BANG HOA DON

    public void reloadBangThanhToan() {
        int n = hoaDon.listSanPham.size();
        //tableModel=new
        for (int i = 0; i < n + 1; i++) {
            tableModel.removeRow(0);
        }
        for (int i = 0; i < n; i++) {
            tableModel.addRow(hoaDon.listSanPham.get(i).DanhMuc);
        }
        tbBangThanhToan.setModel(tableModel);
    }
//============================================================================== 
    //CLEAR BANG HOA DON

    public void xoaBangHoaDon() {
        int n = hoaDon.listSanPham.size();
        for (int i = 0; i < n; i++) {
            tableModel.removeRow(0);
        }
        tbBangThanhToan.setModel(tableModel);
    }
//==============================================================================
    //DINH DANG TIEN THEO FORMAT xxx,xxx,xxx

    public static String dinhDangTien(int x) {
        String y = "";
        if (x == 0) {
            y = "0";
        }
        int k = 0;
        while (x != 0) {
            if (k == 3) {
                y = "," + y;
                k = 0;
            }
            y = x % 10 + y;
            k++;
            x = x / 10;
        }
        return y;
    }
//==============================================================================   
    //THEM HOA DON VAO CO SO DU LIEU 

    public void luuHoaDon(HoaDon hoadon) {
//        int id=0;
//        String mahoadon="HD"+id;
        String query = "INSERT INTO `oop`.`hoa_don` (`id`, `Mã Hóa Đơn`,"
                + " `Tổng Tiền`, `Chiết Khấu`, `Thành Tiền`, `Mã Nhân Viên`,`Ngày Tạo`) VALUES (?,?,?,?,?,?,?);";
        PreparedStatement pst = null;
        try {
            pst = ketNoi.conn.prepareStatement(query);
            pst.setString(1, Integer.toString(hoadon.id));
            pst.setString(2, hoadon.maHoaDon);
            pst.setString(3, Integer.toString((int) hoadon.tongTienPhaiTra));
            pst.setString(4, Integer.toString((int) hoadon.giamSP));
            pst.setString(5, Integer.toString((int) (hoadon.tongTienPhaiTra)));
            pst.setString(6, hoadon.maNhanVien);
            pst.setString(7, hoadon.thoiGianGiaoDich);
            if (pst.executeUpdate() > 0) {
                System.out.println("Thêm thành công");
            } else {
                System.out.println("Lỗi khi thêm sản phẩm\n");
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi đọc cơ sở dữ liệu");
        }
    }
//==============================================================================       

    public static void main(String[] args) {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Giao_Dien_Nguoi_Dung.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(Giao_Dien_Nguoi_Dung.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Giao_Dien_Nguoi_Dung.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Giao_Dien_Nguoi_Dung.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }
        ThanhToan x = new ThanhToan("tân","nv007");
        JFrame jframe = new JFrame();
        jframe.setLayout(null);
        jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
        jframe.setSize(1170, 720);
        jframe.setLocationRelativeTo(null);
        jframe.add(x);
        jframe.setVisible(true);
        x.setVisible(true);
    }
}

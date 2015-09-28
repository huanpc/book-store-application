 
package giaodien;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import thanhtoan.ThanhToan;
public class Giao_Dien_Nguoi_Dung extends JFrame{
    
    ImageIcon icon_book = new ImageIcon("src\\image\\book.png");
    ImageIcon icon_cd = new ImageIcon("src\\image\\cd.png");
    ImageIcon icon_dvd = new ImageIcon("src\\image\\dvd.png");
    ImageIcon icon_tt = new ImageIcon("src\\image\\tt.png");
    
    ImageIcon icon_book_non_click = new ImageIcon("src\\image\\book_non_click.jpg");
    ImageIcon icon_cd_non_click = new ImageIcon("src\\image\\cd_non_click.jpg");
    ImageIcon icon_dvd_non_click = new ImageIcon("src\\image\\dvd_non_click.jpg");
    ImageIcon icon_tt_non_click = new ImageIcon("src\\image\\tt_non_click.jpg");
    
    ImageIcon icon_nen_1 = new ImageIcon("src\\image\\panel_1.jpg");
    ImageIcon icon_nen_2 = new ImageIcon("src\\image\\panel_2.jpg");
    
      
        JPanel pnNhanVien = new JPanel();
        JLabel lbTenNhanVien;
        JLabel lbMaNhanVien;
        JLabel lbCapDo;
    
        JLabel lbTenNhanVien1;
        JLabel lbMaNhanVien1;
        JLabel lbCapDo1;
    
//    Thanh_Toan thanh_toan = new Thanh_Toan();
    ThanhToan pnThanhToan = null; 
    Kho_Sach pnsach = null;
    Kho_CD pncd = null;
    Kho_DVD pndvd = null;
    Color newColor = new Color(56, 170, 209);


    JLabel nen_1 = new JLabel();
    JLabel nen_2 = new JLabel();
    
    JButton btThanhToanThuong,btThanhToanDoiTra;
    
    public Giao_Dien_Nguoi_Dung(String tenNhanVien, String maNhanVien, boolean is_admin){//truyền thêm thuộc tính cho hàm khởi tạo
        Kho_Sach pnsach = new Kho_Sach ();
        Kho_CD pncd = new Kho_CD();
        Kho_DVD pndvd = new Kho_DVD();
        pnThanhToan = new thanhtoan.ThanhToan(maNhanVien, tenNhanVien);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBackground(Color.yellow);
        this.setSize(1170, 720);
        this.setLocation(90,0);
        this.setTitle("Media One");        
        this.setLocationRelativeTo(null);
        
        

        nen_2.setBounds(10, 10, 680, 130);
        nen_2.setVisible(true);
        nen_2.setIcon(icon_nen_2);
        this.add(nen_2);

        btThanhToanThuong = new JButton();
        btThanhToanThuong.setBounds(700, 10, 60, 60);
        add(btThanhToanThuong);
        
        btThanhToanDoiTra = new JButton();
        btThanhToanDoiTra.setBounds(700, 80, 60, 60);
        add(btThanhToanDoiTra);
        nen_1.setBounds(140, 150, 990, 510);
        nen_1.setVisible(true);
        nen_1.setIcon(icon_nen_1);
        this.add(nen_1);
        
        pnNhanVien = new JPanel();
        pnNhanVien.setBounds(770, 10, 359, 130);
        pnNhanVien.setLayout(null);
        pnNhanVien.setBackground(newColor);
        pnNhanVien.setBorder(new BevelBorder(0));
        pnNhanVien.setVisible(true);
        add(pnNhanVien);
        
        lbTenNhanVien = new JLabel("Tên Nhân Viên:");
        lbTenNhanVien.setBounds(10, 20, 90, 20);
        lbTenNhanVien1 = new JLabel(tenNhanVien);
        lbTenNhanVien1.setBounds(110,20 ,140 , 20);
                
        lbMaNhanVien = new JLabel("Mã Nhân Viên:");
        lbMaNhanVien.setBounds(10, 55, 90, 20);
        lbMaNhanVien1 = new JLabel(maNhanVien);
        lbMaNhanVien1.setBounds(110,55 ,140 , 20);

        lbCapDo = new JLabel("Cấp độ:");
        lbCapDo.setBounds(10, 90, 90, 20);
        lbCapDo1 = new JLabel();
        lbCapDo1.setBounds(110,90,140,20);
        
        JButton btKhachHang = new JButton("Khách Hàng");
        btKhachHang.setBounds(230, 10, 120, 30);
        
        JButton btQuanLyNV = new JButton("Nhân Viên");
        btQuanLyNV.setBounds(230, 50, 120, 30);
        
        JButton btMail = new JButton("Send Mail");
        btMail.setBounds(230, 90, 120, 30);
        if(is_admin){
            lbCapDo1.setText("admin");
            btKhachHang.setEnabled(true);
            btQuanLyNV.setEnabled(true);
        }
        else{
            lbCapDo1.setText("nhân viên");
            btKhachHang.setEnabled(false);
            btQuanLyNV.setEnabled(false);
        }
        pnNhanVien.add(btMail);
        pnNhanVien.add(btQuanLyNV);
        pnNhanVien.add(btKhachHang);
        pnNhanVien.add(lbTenNhanVien);
        pnNhanVien.add(lbTenNhanVien1);
        pnNhanVien.add(lbMaNhanVien);
        pnNhanVien.add(lbMaNhanVien1);
        pnNhanVien.add(lbCapDo);
        pnNhanVien.add(lbCapDo1);
        
        btMail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SendMail().setVisible(true);
            }
        });
        
        btKhachHang.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Khach_Hang().setVisible(true);
            }
        });
        
        btQuanLyNV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Quan_Li_Tai_Khoan().setVisible(true);
            }
        });
        
        JButton sach_button = new JButton();
        sach_button.setIcon(icon_book_non_click);
        sach_button.setBounds(10, 150, 120, 120);
        add(sach_button);
 
        JButton cd_button = new JButton();
        cd_button.setIcon(icon_cd_non_click);
        cd_button.setBounds(10, 150+120+10, 120, 120);
        add(cd_button);
        
        JButton dvd_button = new JButton();
        dvd_button.setIcon(icon_dvd_non_click);
        dvd_button.setBounds(10, 150+120*2+10*2, 120, 120);
        add(dvd_button);
        
        JButton thanh_toan_button = new JButton();
        thanh_toan_button.setIcon(icon_tt_non_click);
        thanh_toan_button.setBounds(10, 150+120*3+10*3, 120, 120);
        add(thanh_toan_button);
        
        add(pnsach);
        sach_button.addActionListener((ActionEvent e) -> {
            sach_button.setIcon(icon_book);
            cd_button.setIcon(icon_cd_non_click);
            dvd_button.setIcon(icon_dvd_non_click);
            thanh_toan_button.setIcon(icon_tt_non_click);
            
            pnsach.setVisible(true);
            pncd.setVisible(false);
            pndvd.setVisible(false);
            pnThanhToan.setVisible(false);
            nen_1.setVisible(false);
        });
                
        add(pncd);
        cd_button.addActionListener((ActionEvent e) -> {
            sach_button.setIcon(icon_book_non_click);
            cd_button.setIcon(icon_cd);
            dvd_button.setIcon(icon_dvd_non_click);
            thanh_toan_button.setIcon(icon_tt_non_click);
            
            pncd.setVisible(true);
            pnsach.setVisible(false);
            pndvd.setVisible(false);
            pnThanhToan.setVisible(false);
            nen_1.setVisible(false);
        });
        
        add(pndvd);
        dvd_button.addActionListener((ActionEvent e) -> {
            sach_button.setIcon(icon_book_non_click);
            cd_button.setIcon(icon_cd_non_click);
            dvd_button.setIcon(icon_dvd);
            thanh_toan_button.setIcon(icon_tt_non_click);
            
            pndvd.setVisible(true);
            pncd.setVisible(false);
            pnsach.setVisible(false);
            pnThanhToan.setVisible(false);
            nen_1.setVisible(false);
        });
        
        add(pnThanhToan);
        thanh_toan_button.addActionListener((ActionEvent e) -> {
            sach_button.setIcon(icon_book_non_click);
            cd_button.setIcon(icon_cd_non_click);
            dvd_button.setIcon(icon_dvd_non_click);
            thanh_toan_button.setIcon(icon_tt);
            
            pnThanhToan.setVisible(true);

            pndvd.setVisible(false);
            pncd.setVisible(false);
            pnsach.setVisible(false);
            nen_1.setVisible(false);
        });        
        this.setVisible(true);
    }
    
    
    public static void main(String[] args) {
        String tenNhanVien = "Phan Tân";
        String maNhanVien = "nv003";
        for(UIManager.LookAndFeelInfo info :UIManager.getInstalledLookAndFeels()){
            if("Nimbus".equals(info.getName())){
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
        Giao_Dien_Nguoi_Dung newDisplay = new Giao_Dien_Nguoi_Dung(tenNhanVien, maNhanVien, true);
    }
}

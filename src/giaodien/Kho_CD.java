/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodien;

import goiSP.CD;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import jxl.read.biff.BiffException;
import ket_noi_DB.ket_noi_cd;
import ket_noi_excel.ket_noi_excel_cd;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Kho_CD extends JPanel{
    private JButton btTimKiem, btThem, btChinhSua, btXoa, btDongY, btHuyBo, btToanBoKho, btImport,btExport;
    private final JTable tbCD;
    private JLabel lbTenSP, lbmaSP, lbSoLuong, lbTheLoai, lbNgayNhap, lbGiaBan, lbTenCaSi, lbTenNhacSi, lbNamPhatHanh, lbSoPhieu,lbChietKhau;
    private JTextField tfSearch, tfTenSP, tfmaSP, tfSoLuong, tfTheLoai, tfNgayNhap, tfGiaBan, tfTenCaSi, tfTenNhacSi, tfNamPhatHanh, tfSoPhieu, tfChietKhau;
    private JComboBox cbTimKiem;
    
    private Date date;
    
    private ImageIcon icon_exchange = new ImageIcon("src//image//exchange.png");
    private ImageIcon icon_add = new ImageIcon("src//image//mini_add.png");
    private ImageIcon icon_remove = new ImageIcon("src//image//mini_remove.png");
    private ImageIcon icon_check = new ImageIcon("src//image//check.png");
    private ImageIcon icon_cancel = new ImageIcon("src//image//cancel.png");
    private ImageIcon icon_edit = new ImageIcon("src//image//mini_edit.png");
    
    private final JScrollPane jsp;
    private boolean isAdd = false;
    private int hesoCBB = 0;// hệ số của combobox
    private int id;
    private boolean updateTuTableChinh = true;
    private String keyWord2;
    private String tableName = "kho_cd";
    ket_noi_excel_cd knecd = new ket_noi_excel_cd();
    ket_noi_cd kncd = new ket_noi_cd();
    Connection conn;
    Color newColor = new Color(56, 170, 209);
  public Kho_CD(){
      this.setVisible(false);
      this.setBackground(newColor);
      this.setBounds(140, 150, 990, 510);
      this.setLayout(null);
      
      kncd.connect_to_DB();//kết nối đến cơ sở dữ liệu
      
      add_to_panel();
      add_listener_button();
      set_display_input(false);
      add_listener_comboBox();
//      add_listener_searching_button();
      updateNgayThang();
      tbCD = new JTable();
      tbCD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//thanh kéo ngang
      kncd.reload_table(tbCD);
      jsp = new JScrollPane(tbCD);
      jsp.setBounds(345, 50, 633, 445);
      jsp.setVisible(true);
      this.add(jsp);
      
    }

public void updateNgayThang(){
    date = new Date();
    String ngayThang = date.getDate()+"/"+(date.getMonth()+1)+"/"+(date.getYear()+1900);
    tfNgayNhap.setText(ngayThang);
}
  
private void add_to_panel(){
    //phan tim kiem 
      tfSearch = new JTextField();
      tfSearch.setBounds(10, 10, 450, 30);
      tfSearch.addKeyListener(new KeyListener() {

          @Override
          public void keyTyped(KeyEvent e) {
          
          }

          @Override
          public void keyPressed(KeyEvent e) {
          
          }

          @Override
          public void keyReleased(KeyEvent e) {
              updateTuTableChinh = false;
              kncd.reload_searching_table(tfSearch.getText(),hesoCBB, tbCD);
          }
      });
      this.add(tfSearch);
      
//      btTimKiem = new JButton("Tìm Kiếm");
//      btTimKiem.setBounds(590, 10, 120, 30);
//      btTimKiem.setMargin(new Insets(2, 0, 2, 2));
//      btTimKiem.setIcon(icon_tim_kiem);
//      this.add(btTimKiem);
      
      DefaultComboBoxModel model = new DefaultComboBoxModel();
      model.addElement("Tất Cả");
      model.addElement("Tên Sản Phẩm");
      model.addElement("Mã Sản Phẩm");
      model.addElement("Tên Ca Sĩ");
      model.addElement("Tên Nhạc Sĩ");
      model.addElement("Thể Loại");      
      model.addElement("Năm Phát Hành");      
      cbTimKiem = new JComboBox();
      cbTimKiem.setModel(model);
      cbTimKiem.setBounds(480, 10, 130, 30);
      this.add(cbTimKiem);
      
      btToanBoKho = new JButton("Tất cả");
      btToanBoKho.setBounds(630,10,88,30);
      this.add(btToanBoKho);
      
      btImport = new JButton("Import");
      btImport.setMargin(new Insets(0, 0, 0, 0));
      btImport.setIcon(icon_exchange);
      btImport.setBounds(737, 5, 120, 40);
      this.add(btImport);
      
      btExport = new JButton("Export");
      btExport.setMargin(new Insets(0, 0, 0, 0));
      btExport.setIcon(icon_exchange);
      btExport.setBounds(858, 5, 120, 40);
      this.add(btExport);
      
      //phan nhap xuat chinh sua
     
      lbTenSP = new JLabel("Tên Sản Phẩm");
      lbTenSP.setBounds(30, 50, 90, 27);
      this.add(lbTenSP);
      tfTenSP =  new JTextField();
      tfTenSP.setBounds(130, 50, 200, 27);
      this.add(tfTenSP);
      
      lbmaSP = new JLabel("Mã Sản Phẩm");
      lbmaSP.setBounds(30, 80, 90,27);
      this.add(lbmaSP);
      tfmaSP =  new JTextField();
      tfmaSP.setBounds(130, 80, 200, 27);
      this.add(tfmaSP);
      
      lbTenCaSi = new JLabel("Tên Ca Sĩ");
      lbTenCaSi.setBounds(30, 50+ 30*2, 90, 27);
      this.add(lbTenCaSi);
      tfTenCaSi =  new JTextField();
      tfTenCaSi.setBounds(130, 50+30*2, 200, 27);
      this.add(tfTenCaSi);
      
      lbTenNhacSi = new JLabel("Tên Nhạc Sĩ");
      lbTenNhacSi.setBounds(30, 50+30*3, 90, 27);
      this.add(lbTenNhacSi);
      tfTenNhacSi =  new JTextField();
      tfTenNhacSi.setBounds(130, 50+30*3, 200, 27);
      this.add(tfTenNhacSi);
      
      lbNamPhatHanh = new JLabel("Năm Phát Hành");
      lbNamPhatHanh.setBounds(30, 50+30*4, 90, 27);
      this.add(lbNamPhatHanh);
      tfNamPhatHanh =  new JTextField();
      tfNamPhatHanh.setBounds(130, 50+30*4, 200, 27);
      this.add(tfNamPhatHanh);
      
      lbTheLoai = new JLabel("Thể Loại");
      lbTheLoai.setBounds(30, 50+30*5, 90, 27);
      this.add(lbTheLoai);
      tfTheLoai =  new JTextField();
      tfTheLoai.setBounds(130, 50+30*5, 200, 27);
      this.add(tfTheLoai);
      
      lbSoLuong = new JLabel("Số Lượng");
      lbSoLuong.setBounds(30, 50+30*6, 90, 27);
      this.add(lbSoLuong);
      tfSoLuong =  new JTextField();
      tfSoLuong.setBounds(130, 50+30*6, 200, 27);
      this.add(tfSoLuong);
      
      lbNgayNhap = new JLabel("Ngày Nhập");
      lbNgayNhap.setBounds(30, 50+30*7, 90, 27);
      this.add(lbNgayNhap);
      tfNgayNhap =  new JTextField();
      tfNgayNhap.setBounds(130, 50+30*7, 200, 27);
      this.add(tfNgayNhap);
      
      lbGiaBan = new JLabel("Giá Bán");
      lbGiaBan.setBounds(30, 50+30*8, 90, 27);
      this.add(lbGiaBan);
      tfGiaBan =  new JTextField();
      tfGiaBan.setBounds(130, 50+30*8, 200, 27);
      this.add(tfGiaBan);

      lbSoPhieu = new JLabel("Số Phiếu");
      lbSoPhieu.setBounds(30, 50+30*9, 90, 27);
      this.add(lbSoPhieu);
      tfSoPhieu =  new JTextField();
      tfSoPhieu.setBounds(130, 50+30*9, 200, 27);
      this.add(tfSoPhieu);      
      
      lbChietKhau = new JLabel("Chiết Khấu");
      lbChietKhau.setBounds(30, 50+30*10, 90, 27);
      this.add(lbChietKhau);
      tfChietKhau =  new JTextField("0");
      tfChietKhau.setBounds(130, 50+30*10, 200, 27);
      this.add(tfChietKhau);      
      
      btThem = new JButton("Thêm");
      btThem.setBounds(20, 465, 100, 30);
      btThem.setMargin(new Insets(0, 0, 0, 0));
      btThem.setIcon(icon_add);
      this.add(btThem);
      
      btChinhSua = new JButton("Sửa");
      btChinhSua.setBounds(20 + 110, 465, 100, 30);
      btChinhSua.setMargin(new Insets(0, 0, 0, 0));
      btChinhSua.setIcon(icon_edit);
      this.add(btChinhSua);
      
      btXoa = new JButton("Xóa");
      btXoa.setBounds(20 + 110*2, 465, 100, 30);
      btXoa.setMargin(new Insets(0, 0, 0, 0));
      btXoa.setIcon(icon_remove);
      this.add(btXoa);
      
      
      btDongY = new JButton("Đồng Ý");
      btDongY.setBounds(65, 430, 110, 25);
      btDongY.setMargin(new Insets(0, 0, 0, 0));
      btDongY.setIcon(icon_check);
      this.add(btDongY);
      
      btHuyBo = new JButton("Hủy Bỏ");
      btHuyBo.setBounds(120 + 65, 430, 110, 25);
      btHuyBo.setMargin(new Insets(0, 0, 0, 0));
      btHuyBo.setIcon(icon_cancel);
      this.add(btHuyBo);
      
}

private boolean set_display_input(boolean display){
    tfTenSP.setEnabled(display);
    tfmaSP.setEnabled(display);
    tfTenNhacSi.setEnabled(display);
    tfTenCaSi.setEnabled(display);
    tfNamPhatHanh.setEnabled(display);
    tfTheLoai.setEnabled(display);    
    tfSoLuong.setEnabled(display); 
    tfNgayNhap.setEnabled(display);
    tfGiaBan.setEnabled(display);
    tfSoPhieu.setEnabled(display);
    tfChietKhau.setEnabled(display);
    btDongY.setEnabled(display);
    btHuyBo.setEnabled(display);
    return true;
}

private void clear(){
    tfGiaBan.setText("");
    tfNgayNhap.setText("");
    tfSoLuong.setText("");
    tfSoPhieu.setText("");
    tfTenSP.setText("");
    tfTenNhacSi.setText("");
    tfTenCaSi.setText("");
    tfNamPhatHanh.setText("");
    tfChietKhau.setText("0");
    tfTheLoai.setText("");
    tfmaSP.setText("");
    tfSearch.setText("");
    updateNgayThang();
    set_display_input(false);
}

private void add_listener_comboBox(){
    cbTimKiem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox newcombox = (JComboBox) e.getSource();
            int heso = newcombox.getSelectedIndex();
            if(heso == 0) hesoCBB = 0;//all
            else if(heso == 1) hesoCBB = 1;//tên
            else if(heso == 2) hesoCBB = 2;//mã
            else if(heso == 3) hesoCBB = 3;//Ca Sĩ
            else if(heso == 4) hesoCBB = 4;//Nhạc sĩ
            else if(heso == 5) hesoCBB = 5;//thể loại
            else if(heso == 6) hesoCBB = 6;//năm phát hành
        }
    });
}

//private void add_listener_searching_button(){
//    btTimKiem.addActionListener(new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if(tfSearch.getText().isEmpty()){
//                JOptionPane.showMessageDialog(null,"Bạn phải nhập từ khóa","Thiếu từ khóa",JOptionPane.OK_OPTION,null);
//            }
//            else{
//                updateTuTableChinh = false;
//                keyWord2 = tfSearch.getText();
//                kncd.reload_searching_table(tfSearch.getText(),hesoCBB,tbCD);
//            }
//        }
//    });
//}

private void add_listener_button(){
    //nút export
    btExport.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser newFileChooser = new JFileChooser();
            newFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Excel(*.xls)", "xls"));
            while (true) {
                    int returnValue = newFileChooser.showSaveDialog(null);
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        String path = newFileChooser.getSelectedFile().getPath();
                        System.out.println(path);
                        if (knecd.returnType(path) == null) {
                            if (newFileChooser.getFileFilter().getDescription().equals("Excel(*.xls)")) {
                                path = path + ".xls";
                                knecd.saveFile(tbCD,path);
                                break;
                            } else {
                                JOptionPane.showMessageDialog(newFileChooser, "Vui lòng đặt định dạng file", "Lỗi định dạng", JOptionPane.WARNING_MESSAGE);
                            }
                        } else if (knecd.returnType(path).equals("xls")) {
                            Workbook workbook = new HSSFWorkbook();
                            knecd.saveFile(tbCD,path);
                            break;
                        } else {
                            JOptionPane.showMessageDialog(newFileChooser, "Vui lòng export dưới dạng file Excel", "Lỗi định dạng", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        break;
                        // họ không chọn lưu thì kệ
                    }
                }
        }
    });
    //nút import
    btImport.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser newFileChooser = new JFileChooser();
            while(true){
                int returnValue = newFileChooser.showOpenDialog(null);// show bản chọn file
                if(returnValue == JFileChooser.APPROVE_OPTION){
                    String fileName = newFileChooser.getSelectedFile().getName();
                    String path = newFileChooser.getSelectedFile().getPath();
                    System.out.println(""+path);
                    fileName = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());//lấy đuôi file
                    System.out.println(fileName);
                    if(fileName.equals("xls")){//là đuôi excel
                        knecd.loadFile(path,true);
                        kncd.reload_table(tbCD);
                        break;
                    }
                    else if(fileName.equals("xlsx")){//đang băn khoăn chỗ này vì cứ file xlsx thì lỗi
                        knecd.loadFile(path,false);
                        kncd.reload_table(tbCD);
                        break;
                    }
                    else{
                        JOptionPane.showMessageDialog(newFileChooser, "Đây không phải là 1 file không phải là Excel", 
                                "Lỗi định dạng", JOptionPane.WARNING_MESSAGE);
                    }
                }
                else{
                    break;
                }
            }
        }
    });
    //nút tất cả
    btToanBoKho.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                kncd.reload_table(tbCD);
        }
    });
    //nút thêm
    btThem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            isAdd = true;
            set_display_input(true);
        }
    });
    //nút sửa
    btChinhSua.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            isAdd = false;
            int row = tbCD.getSelectedRow();
            if(row<0){
                JOptionPane.showMessageDialog(null,"Bạn phải lựa chọn sản phẩm", "Lỗi chỉnh sửa",
                        JOptionPane.ERROR_MESSAGE);
            }
            else{
                    set_display_input(true);
                    tfTenSP.setText((String) tbCD.getValueAt(row, 0));
                    tfmaSP.setText((String) tbCD.getValueAt(row, 1));
                    tfTenCaSi.setText((String) tbCD.getValueAt(row, 2));
                    tfTenNhacSi.setText((String) tbCD.getValueAt(row, 3));
                    tfGiaBan.setText((String) tbCD.getValueAt(row, 4));
                    tfSoLuong.setText((String) tbCD.getValueAt(row, 5));
                    tfTheLoai.setText((String) tbCD.getValueAt(row, 6));
                    tfNamPhatHanh.setText((String) tbCD.getValueAt(row, 7));
                    tfNgayNhap.setText((String) tbCD.getValueAt(row, 8));
                    tfSoPhieu.setText((String) tbCD.getValueAt(row, 9));
                    tfChietKhau.setText((String) tbCD.getValueAt(row, 10));
                    id = Integer.parseInt((String) tbCD.getValueAt(row,11));

            }
        }
    });
    //nút xóa
    btXoa.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = tbCD.getSelectedRow();
            if(row<0){
                JOptionPane.showMessageDialog(null,"Bạn phải lựa chọn sản phẩm", "Lỗi chỉnh sửa",
                        JOptionPane.ERROR_MESSAGE);
            }
            else{

                    id = Integer.parseInt((String) tbCD.getValueAt(row,11));
                    Object[] check = {"Tôi chắc chắn","Tôi không"};
                    int n = JOptionPane.showOptionDialog(null, "Chắc chắn bạn muốn xóa sản phẩm này ","Xóa Sản Phẩm",
                            JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, check, check[1]);
                    if(n == 0){
                        kncd.deleteId(id);
                        if(!updateTuTableChinh){
                            kncd.reload_searching_table(keyWord2, hesoCBB, tbCD);
                        }
                        else{
                            kncd.reload_table(tbCD);
                        }
                    }
            }
        }
    });
    //nút đồng ý
    btDongY.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(isAdd == true){
                if(tfGiaBan.getText().isEmpty() || tfNgayNhap.getText().isEmpty() || tfSoLuong.getText().isEmpty() ||
                    tfSoPhieu.getText().isEmpty() || tfTenSP.getText().isEmpty() || tfTenNhacSi.getText().isEmpty() ||
                    tfTheLoai.getText().isEmpty() || tfmaSP.getText().isEmpty() || tfTenCaSi.getText().isEmpty() || 
                        tfNamPhatHanh.getText().isEmpty() || tfChietKhau.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Bạn phải điền đầy đủ thông tin", "Lỗi cập nhật",
                                        JOptionPane.ERROR_MESSAGE);
                }
                else{
                    String tenSP = tfTenSP.getText();
                    String maSP = tfmaSP.getText();
                    String tenNhacSi = tfTenNhacSi.getText();
                    String tenCaSi = tfTenCaSi.getText();
                    String namPhatHanh = tfNamPhatHanh.getText();
                    String theLoai = tfTheLoai.getText();
                    int soLuong = Integer.parseInt(tfSoLuong.getText());
                    String ngayNhap = tfNgayNhap.getText();
                    double giaBan = Double.parseDouble(tfGiaBan.getText());
                    String soPhieu = tfSoPhieu.getText();
                    int chietKhau = Integer.parseInt(tfChietKhau.getText());
                    // tạo 1 đối tượng DVD
                    CD newCD = new CD(tenSP,maSP,soLuong,theLoai,ngayNhap,giaBan,soPhieu,chietKhau,tenCaSi,tenNhacSi,namPhatHanh);
                    // thêm vào cơ sở dữ liệu
                    kncd.insert_into_cd(newCD);
                    //cập nhật lại bảng
                    kncd.reload_table(tbCD);
                    clear();
                }
            }
            else
            {
                if(tfGiaBan.getText().isEmpty() || tfNgayNhap.getText().isEmpty() || tfSoLuong.getText().isEmpty() ||
                    tfSoPhieu.getText().isEmpty() || tfTenSP.getText().isEmpty() || tfTenNhacSi.getText().isEmpty() ||
                    tfTheLoai.getText().isEmpty() || tfmaSP.getText().isEmpty() || tfTenCaSi.getText().isEmpty() ||
                        tfNamPhatHanh.getText().isEmpty() || tfChietKhau.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Bạn phải điền đầy đủ thông tin", "Lỗi cập nhật",
                                        JOptionPane.ERROR_MESSAGE);
                }
                else{
                    String tenSP = tfTenSP.getText();
                    String maSP = tfmaSP.getText();
                    String tenNhacSi = tfTenNhacSi.getText();
                    String tenCaSi = tfTenCaSi.getText();
                    String namPhatHanh = tfNamPhatHanh.getText();
                    String theLoai = tfTheLoai.getText();
                    int soLuong = Integer.parseInt(tfSoLuong.getText());
                    String ngayNhap = tfNgayNhap.getText();
                    double giaBan = Double.parseDouble(tfGiaBan.getText());
                    String soPhieu = tfSoPhieu.getText();
                    int chietKhau = Integer.parseInt(tfChietKhau.getText());
                    // tạo 1 đối tượng DVD
                    CD newCD = new CD(tenSP,maSP,soLuong,theLoai,ngayNhap,giaBan,soPhieu,chietKhau,tenCaSi,tenNhacSi,namPhatHanh);
                    //cập nhật lại DB
                    kncd.update_cd(newCD, id);
                    if(!updateTuTableChinh){
                            kncd.reload_searching_table(tenSP, hesoCBB,tbCD);
                        }
                        else{
                            kncd.reload_table(tbCD);
                        }
                    clear();
                }
            }
        }
    });
    //nút hủy
    btHuyBo.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            clear();
        }
    });
}
   

    public static void main(String[] args) {
    Kho_CD cd = new Kho_CD();    

    JFrame jframe = new JFrame();
    jframe.setLayout(null);
    jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
    jframe.setSize(1170, 720);
    jframe.setLocationRelativeTo(null);
    jframe.add(cd);
    jframe.setVisible(true);
    }
}


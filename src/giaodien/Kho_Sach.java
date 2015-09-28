/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodien;

import goiSP.Sach;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.util.Date;
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
import ket_noi_DB.ket_noi_sach;
import ket_noi_excel.ket_noi_excel_sach;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

public class Kho_Sach extends JPanel{
    private JButton btTimKiem, btThem, btChinhSua, btXoa, btDongY, btHuyBo, btToanBoKho, btImport, btExport;
    private JTable tbSach;
    private JLabel lbTenSP, lbmaSP, lbTenTacGia, lbSoLuong, lbTheLoai, lbNgayNhap, lbGiaBan, lbSoPhieu, lbChietKhau;
    private JTextField tfSearch, tfTenSP, tfmaSP, tfTenTacGia, tfSoLuong, tfTheLoai, tfNgayNhap, tfGiaBan, tfSoPhieu, tfChietKhau;
    private JComboBox cbTimKiem;
    private Date date;
    private ImageIcon icon_exchange = new ImageIcon("src\\image\\exchange.png");
    private ImageIcon icon_add = new ImageIcon("src//image//mini_add.png");
    private ImageIcon icon_remove = new ImageIcon("src//image//mini_remove.png");
    private ImageIcon icon_check = new ImageIcon("src//image//check.png");
    private ImageIcon icon_cancel = new ImageIcon("src//image//cancel.png");
    private ImageIcon icon_edit = new ImageIcon("src//image//mini_edit.png");
    ket_noi_excel_sach knes = new ket_noi_excel_sach();
    private final String tableName = "kho_sach";
    
    private JScrollPane jsp,jsp2;
    private boolean isAdd = false;
    private int hesoCBB = 0;// hệ số của combobox
    private int id;
    private boolean updateTuTableChinh = true;
    private String keyWord2;
    
    ket_noi_sach kns = new ket_noi_sach();
    Connection conn;
    Color newColor = new Color(56, 170, 209);
  public Kho_Sach(){

      this.setBounds(140, 150, 990, 510);
      this.setLayout(null);
      this.setVisible(false);
      this.setBackground(newColor);
      kns.connect_to_DB();
      
      add_to_panel();// them button và textfield
      updateNgayThang();
      tbSach = new JTable();
      tbSach.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//thanh kéo ngang
      kns.reload_table(tbSach);
      jsp = new JScrollPane(tbSach);
      jsp.setBounds(345, 50, 633, 445);
      jsp.setVisible(true);
      this.add(jsp);
           
      set_display_input(false);//ẩn nút
      add_listener_comboBox();
//      add_listener_searching_button();
      add_listener_button();
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
              kns.reload_searching_table(tfSearch.getText(),hesoCBB, tbSach);
          }
      });
      this.add(tfSearch);
      
//      btTimKiem = new JButton("Tìm Kiếm");
//      btTimKiem.setBounds(590, 10, 120, 30);
//      btTimKiem.setMargin(new Insets(2, 0, 2, 2));
//      btTimKiem.setIcon(icon_tim_kiem);
//      this.add(btTimKiem);
      
      DefaultComboBoxModel model = new DefaultComboBoxModel();
      model.addElement("Tất cả");
      model.addElement("Tên Sách");
      model.addElement("Tên Tác Giả");
      model.addElement("Mã Sản Phẩm");
      model.addElement("Thể Loại");      
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
      lbmaSP.setBounds(30, 80, 90, 27);
      this.add(lbmaSP);
      tfmaSP =  new JTextField();
      tfmaSP.setBounds(130, 80, 200,27);
      this.add(tfmaSP);
      
      lbTenTacGia = new JLabel("Tên Tác Giả");
      lbTenTacGia.setBounds(30, 110, 90, 27);
      this.add(lbTenTacGia);
      tfTenTacGia =  new JTextField();
      tfTenTacGia.setBounds(130, 110, 200, 27);
      this.add(tfTenTacGia);
      
      lbTheLoai = new JLabel("Thể Loại");
      lbTheLoai.setBounds(30, 140, 90, 27);
      this.add(lbTheLoai);
      tfTheLoai =  new JTextField();
      tfTheLoai.setBounds(130, 140, 200, 27);
      this.add(tfTheLoai);
      
      lbSoLuong = new JLabel("Số Lượng");
      lbSoLuong.setBounds(30, 170, 90, 27);
      this.add(lbSoLuong);
      tfSoLuong =  new JTextField();
      tfSoLuong.setBounds(130, 170, 200, 27);
      this.add(tfSoLuong);
      
      lbNgayNhap = new JLabel("Ngày Nhập");
      lbNgayNhap.setBounds(30, 200, 90, 27);
      this.add(lbNgayNhap);
      tfNgayNhap =  new JTextField();
      tfNgayNhap.setBounds(130, 200, 200, 27);
      this.add(tfNgayNhap);
      
      lbGiaBan = new JLabel("Giá Bán");
      lbGiaBan.setBounds(30, 230, 90, 27);
      this.add(lbGiaBan);
      tfGiaBan =  new JTextField();
      tfGiaBan.setBounds(130, 230, 200, 27);
      this.add(tfGiaBan);
      
      lbSoPhieu = new JLabel("Số Phiếu");
      lbSoPhieu.setBounds(30, 260, 90, 27);
      this.add(lbSoPhieu);
      tfSoPhieu =  new JTextField();
      tfSoPhieu.setBounds(130, 260, 200, 27);
      this.add(tfSoPhieu);      
      
      lbChietKhau = new JLabel("Chiết Khấu");
      lbChietKhau.setBounds(30, 290, 90, 27);
      this.add(lbChietKhau);
      tfChietKhau =  new JTextField("0");
      tfChietKhau.setBounds(130, 290, 200, 27);
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
    tfTenTacGia.setEnabled(display);
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
    tfSearch.setText("");
    tfNgayNhap.setText("");
    tfSoLuong.setText("");
    tfSoPhieu.setText("");
    tfChietKhau.setText("0");
    tfTenSP.setText("");
    tfTenTacGia.setText("");
    tfTheLoai.setText("");
    tfmaSP.setText("");
    updateNgayThang();
    set_display_input(false);
}

private void add_listener_comboBox(){
    cbTimKiem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox newcombox = (JComboBox) e.getSource();
            int heso = newcombox.getSelectedIndex();
            if(heso == 0) hesoCBB = 0;
            else if(heso == 1) hesoCBB = 1;
            else if(heso == 2) hesoCBB = 2;
            else if(heso == 3) hesoCBB = 3;
            else if(heso == 4) hesoCBB = 4;
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
//                keyWord2 = tfSearch.getText();
//                updateTuTableChinh = false;
//                kns.reload_searching_table(tfSearch.getText(),hesoCBB, tbSach);
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
                        if (knes.returnType(path) == null) {
                            if (newFileChooser.getFileFilter().getDescription().equals("Excel(*.xls)")) {
                                path = path + ".xls";
                                knes.saveFile(tbSach,path);
                                break;
                            } else {
                                JOptionPane.showMessageDialog(newFileChooser, "Vui lòng đặt định dạng file", "Lỗi định dạng", JOptionPane.WARNING_MESSAGE);
                            }
                        } else if (knes.returnType(path).equals("xls")) {
                            Workbook workbook = new HSSFWorkbook();
                            knes.saveFile(tbSach,path);
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
                        knes.loadFile(path,true);
                        kns.reload_table(tbSach);
                        break;
                    }
                    else if(fileName.equals("xlsx")){//đang băn khoăn chỗ này vì cứ file xlsx thì lỗi
                        knes.loadFile(path,false);
                        kns.reload_table(tbSach);
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
            updateTuTableChinh = true;
            kns.reload_table(tbSach);
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
            int row = tbSach.getSelectedRow();
            if(row<0){
                JOptionPane.showMessageDialog(null,"Bạn phải lựa chọn sản phẩm", "Lỗi chỉnh sửa",
                        JOptionPane.ERROR_MESSAGE);
            }
            else{
                    set_display_input(true);
                    tfTenSP.setText((String) tbSach.getValueAt(row, 0));
                    tfmaSP.setText((String) tbSach.getValueAt(row, 1));
                    tfTenTacGia.setText((String) tbSach.getValueAt(row, 2));
                    tfTheLoai.setText((String) tbSach.getValueAt(row, 3));
                    tfGiaBan.setText((String) tbSach.getValueAt(row, 4));
                    tfSoLuong.setText((String) tbSach.getValueAt(row, 5));
                    tfNgayNhap.setText((String) tbSach.getValueAt(row, 6));
                    tfSoPhieu.setText((String) tbSach.getValueAt(row, 7));
                    tfChietKhau.setText((String) tbSach.getValueAt(row,8));
                    id = Integer.parseInt((String) tbSach.getValueAt(row,9));
            }
        }
    });
    //nút xóa
    btXoa.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = tbSach.getSelectedRow();
            if(row<0){
                JOptionPane.showMessageDialog(null,"Bạn phải lựa chọn sản phẩm", "Lỗi chỉnh sửa",
                        JOptionPane.ERROR_MESSAGE);
            }
            else{
                    id = Integer.parseInt((String) tbSach.getValueAt(row,9));
                    Object[] check = {"Tôi chắc chắn","Tôi không"};
                    int n = JOptionPane.showOptionDialog(null, "Chắc chắn bạn muốn xóa sản phẩm này ","Xóa Sản Phẩm",
                            JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, check, check[1]);
                    if(n == 0){
                        kns.deleteId(id);
                        if(!updateTuTableChinh){
                            kns.reload_searching_table(keyWord2, hesoCBB, tbSach);// load lại bảng tìm kiêm với từ khóa cũ
                        }
                        else{
                            kns.reload_table(tbSach);// load lại bảng tổng
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
                    tfSoPhieu.getText().isEmpty() || tfTenSP.getText().isEmpty() || tfTenTacGia.getText().isEmpty() ||
                    tfTheLoai.getText().isEmpty() || tfmaSP.getText().isEmpty() || tfChietKhau.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Bạn phải điền đầy đủ thông tin", "Lỗi cập nhật",
                                        JOptionPane.ERROR_MESSAGE);
                }
                else{
                    String tenSP = tfTenSP.getText();
                    String maSP = tfmaSP.getText();
                    String tenTacGia = tfTenTacGia.getText();
                    String theLoai = tfTheLoai.getText();
                    int soLuong = Integer.parseInt(tfSoLuong.getText());
                    String ngayNhap = tfNgayNhap.getText();
                    double giaBan = Double.parseDouble(tfGiaBan.getText());
                    String soPhieu = tfSoPhieu.getText();
                    int chietKhau = Integer.parseInt(tfChietKhau.getText());
                    // tạo 1 đối tượng sách
                    Sach newSach = new Sach(tenSP,maSP,soLuong,theLoai,ngayNhap,giaBan,tenTacGia,soPhieu,chietKhau);
                    // thêm vào cơ sở dữ liệu
                    kns.insert_into_sach(newSach);
                    //cập nhật lại bảng
                    kns.reload_table(tbSach);
                    clear();
                }
            }
            else
            {
                if(tfGiaBan.getText().isEmpty() || tfNgayNhap.getText().isEmpty() || tfSoLuong.getText().isEmpty() ||
                    tfSoPhieu.getText().isEmpty() || tfTenSP.getText().isEmpty() || tfTenTacGia.getText().isEmpty() ||
                    tfTheLoai.getText().isEmpty() || tfmaSP.getText().isEmpty() || tfChietKhau.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Bạn phải điền đầy đủ thông tin", "Lỗi cập nhật",
                                        JOptionPane.ERROR_MESSAGE);
                }
                else{// update
                    String tenSP = tfTenSP.getText();
                    String maSP = tfmaSP.getText();
                    String tenTacGia = tfTenTacGia.getText();
                    String theLoai = tfTheLoai.getText();
                    int soLuong = Integer.parseInt(tfSoLuong.getText());
                    String ngayNhap = tfNgayNhap.getText();
                    double giaBan = Double.parseDouble(tfGiaBan.getText());
                    String soPhieu = tfSoPhieu.getText();
                    int chietKhau = Integer.parseInt(tfChietKhau.getText());

                    Sach newSach = new Sach(tenSP,maSP,soLuong,theLoai,ngayNhap,giaBan,tenTacGia,soPhieu,chietKhau);
                    //cập nhật lại DB
                    kns.update_sach(newSach, id);
                    //load lại bảng
                    if(!updateTuTableChinh){
                        kns.reload_searching_table(tenSP, hesoCBB, tbSach);// load lại bảng tìm kiếm với tên mới
                    }
                    else kns.reload_table(tbSach);
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
    Kho_Sach kho_Sach = new Kho_Sach();    

    JFrame jframe = new JFrame();
    jframe.setLayout(null);
    jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
    jframe.setSize(1170, 720);
    jframe.setLocationRelativeTo(null);
    jframe.add(kho_Sach);
    jframe.setVisible(true);
    }
}


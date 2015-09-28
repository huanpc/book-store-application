package giaodien;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import goiNguoi.TaiKhoan;
import ket_noi_DB.ket_noi_tk;


public class Quan_Li_Tai_Khoan extends JFrame{
	
    ket_noi_tk kntk = new ket_noi_tk();     
    public Connection conn;
    private boolean updateTuTableChinh = true;
    private boolean isAdd = false;
    private String keyWord2;
    private String UserID;
    private int cbIndex = 0;
    private String tableName = "user";
    private JLabel lbTitle, lbMaNhanVien, lbTenNhanVien, lbTenDangNhap, lbMatKhau, lbDiaChi, lbSDT, lbLoaiTaiKhoan;
    private JTable table;
    private JScrollPane jsp;
    private JTextField tfSearch, tfMaNhanVien, tfTenNhanVien, tfTaiKhoan, tfMatKhau, tfDiaChi, tfSDT;
    private JButton btSearch, btRefresh, btOk, btCancel, btAdd, btUpdate, btDelete;
    private JComboBox cbSearch, cbAccountType;
    
    public Quan_Li_Tai_Khoan(){
        this.setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocation(190,50);
        this.setVisible(true);
        this.setSize(1018, 620);
        addComponents();
        setDisplayInput(false);
        addListenerButton();
        addListenerComboBox();
        addListenerSearchingButton();
        kntk.connect_to_DB();
        
        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        kntk.reloadTable(table);
        jsp = new JScrollPane(table);
        jsp.setBounds(460, 140, 530, 425);
        jsp.setVisible(true);
        this.add(jsp);
    }
    
    // tạo các label, button, textfield, combobox
    private void addComponents(){
        // tao cac label, button, combobox, table, textfield
        lbTitle = new JLabel("Quản Lí Tài Khoản");
        lbTitle.setBounds(400, 0, 200, 80);
        lbTitle.setFont(new java.awt.Font("Times New Roman", 1, 20));
        this.add(lbTitle);
        
        tfSearch = new JTextField();
        tfSearch.setBounds(20, 100, 560, 30);
        this.add(tfSearch);
        btSearch = new JButton("Tìm kiếm");
        btSearch.setBounds(600, 100, 120, 30);
        this.add(btSearch);
        
        DefaultComboBoxModel cbModel = new DefaultComboBoxModel();
        cbModel.addElement("Toàn Bộ");
        cbModel.addElement("Mã Nhân Viên");
        cbModel.addElement("Tên Nhân Viên");
        cbModel.addElement("Tên Đăng Nhập");
        cbModel.addElement("Địa Chỉ");
        cbModel.addElement("Loại Tài Khoản");         
        cbSearch = new JComboBox();
        cbSearch.setModel(cbModel);
        cbSearch.setBounds(740, 100, 150, 30);
        this.add(cbSearch);
        
        btRefresh = new JButton("Tất Cả");
        btRefresh.setBounds(900, 100, 88, 30);
        this.add(btRefresh);
        
        lbMaNhanVien = new JLabel("Mã Nhân Viên");
        lbMaNhanVien.setBounds(20, 150, 100, 20);
        this.add(lbMaNhanVien);
        tfMaNhanVien = new JTextField();
        tfMaNhanVien.setBounds(140, 150, 280, 28);
        this.add(tfMaNhanVien);
        
        lbTenNhanVien = new JLabel("Tên Nhân Viên");
        lbTenNhanVien.setBounds(20, 200, 100, 20);
        this.add(lbTenNhanVien);
        tfTenNhanVien = new JTextField();
        tfTenNhanVien.setBounds(140, 200, 280, 28);
        this.add(tfTenNhanVien);
        
        lbTenDangNhap = new JLabel("Tài Khoản");
        lbTenDangNhap.setBounds(20, 250, 100, 20);
        this.add(lbTenDangNhap);
        tfTaiKhoan = new JTextField();
        tfTaiKhoan.setBounds(140, 250, 280, 28);
        this.add(tfTaiKhoan);
        
        lbMatKhau = new JLabel("Mật Khẩu");
        lbMatKhau.setBounds(20, 300, 100, 20);
        this.add(lbMatKhau);
        tfMatKhau = new JTextField();
        tfMatKhau.setBounds(140, 300, 280, 28);
        this.add(tfMatKhau);
        
        lbDiaChi = new JLabel("Địa Chỉ");
        lbDiaChi.setBounds(20, 350, 100, 20);
        this.add(lbDiaChi);
        tfDiaChi = new JTextField();
        tfDiaChi.setBounds(140, 350, 280, 28);
        this.add(tfDiaChi);
        
        lbSDT = new JLabel("Số Điện Thoại");
        lbSDT.setBounds(20, 400, 100, 20);
        this.add(lbSDT);
        tfSDT = new JTextField();
        tfSDT.setBounds(140, 400, 280, 28);
        this.add(tfSDT);
        
        lbLoaiTaiKhoan = new JLabel("Loại Tài Khoản");
        lbLoaiTaiKhoan.setBounds(20, 450, 100, 20);
        this.add(lbLoaiTaiKhoan);
        DefaultComboBoxModel cbModel1 = new DefaultComboBoxModel();
        cbModel1.addElement("Admin");
        cbModel1.addElement("User");      
        cbAccountType = new JComboBox();
        cbAccountType.setModel(cbModel1);
        cbAccountType.setBounds(140, 450, 100, 28);
        this.add(cbAccountType);   
        
        btOk = new JButton("Đồng Ý");
        btOk.setBounds(135, 490, 80, 25);
        this.add(btOk);
        
        btCancel = new JButton("Hủy Bỏ");
        btCancel.setBounds(225, 490, 80, 25);
        this.add(btCancel);
        
        btAdd = new JButton("Thêm");
        btAdd.setBounds(100, 525, 80, 30);
        this.add(btAdd);
        
        btUpdate = new JButton("Sửa");
        btUpdate.setBounds(190, 525, 80, 30);
        this.add(btUpdate);
        
        btDelete = new JButton("Xóa");
        btDelete.setBounds(280, 525, 80, 30);
        this.add(btDelete);              
    }
    
    // hien thi cac button, textfield
    private boolean setDisplayInput(boolean display){
        tfMaNhanVien.setEnabled(display);
        tfTenNhanVien.setEnabled(display);
        tfTaiKhoan.setEnabled(display);
        tfMatKhau.setEnabled(display);
        tfDiaChi.setEnabled(display);
        tfSDT.setEnabled(display);    
        cbAccountType.setEnabled(display); 
        btOk.setEnabled(display);
        btCancel.setEnabled(display);
        return true;
    }
    
    // xoa cac textfield
    private void clear(){
        tfMaNhanVien.setText("");
        tfTenNhanVien.setText("");
        tfTaiKhoan.setText("");
        tfMatKhau.setText("");
        tfDiaChi.setText("");
        tfSDT.setText("");
        tfSearch.setText("");
        setDisplayInput(false);
    }

    private void addListenerComboBox(){
        cbSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox newcombox = (JComboBox) e.getSource();
                int index = newcombox.getSelectedIndex();
                if(index == 0) cbIndex = 0;
                else if(index == 1) cbIndex = 1;
                else if(index == 2) cbIndex = 2;
                else if(index == 3) cbIndex = 3;
                else if(index == 4) cbIndex = 4;
                else if(index == 5) cbIndex = 5;
                else if(index == 6) cbIndex = 6;
            }
        });
    }
    
    private void addListenerSearchingButton(){
        btSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tfSearch.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Bạn chưa nhập từ tìm kiếm","Lỗi tìm kiếm",JOptionPane.OK_OPTION,null);
                }
                else{
                    updateTuTableChinh = false;
                    keyWord2 = tfSearch.getText();
                    kntk.reloadSearchingTable(tfSearch.getText(), cbIndex, table);
                }
            }
        });
    }

    private void addListenerButton(){
    	btRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    kntk.reloadTable(table);
            }
        });

        btAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isAdd = true;
                setDisplayInput(true);
            }
        });

        btUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isAdd = false;
                int row = table.getSelectedRow();
                if(row<0){
                    JOptionPane.showMessageDialog(null,"Bạn phải chọn 1 tài khoản", "Lỗi cập nhật",
                            JOptionPane.ERROR_MESSAGE);
                }
                else{
                        setDisplayInput(true);
                        tfMaNhanVien.setText((String) table.getValueAt(row, 0));
                        tfTenNhanVien.setText((String) table.getValueAt(row, 1));
                        tfTaiKhoan.setText((String) table.getValueAt(row, 2));
                        tfMatKhau.setText((String) table.getValueAt(row, 3));
                        tfDiaChi.setText((String) table.getValueAt(row, 4));
                        tfSDT.setText((String) table.getValueAt(row, 5));
                        if(((String) table.getValueAt(row, 6)).equals(new String("admin"))){
                        cbAccountType.setSelectedIndex(0);
                        }else cbAccountType.setSelectedIndex(1);
                }
            }
        });

        btDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if(row<0){
                    JOptionPane.showMessageDialog(null,"Bạn phải chọn 1 tài khoản", "Lỗi xóa",
                            JOptionPane.ERROR_MESSAGE);
                }
                else{

                        UserID = (String) table.getValueAt(row, 0);
                        Object[] check = {"Có","Không"};
                        int n = JOptionPane.showOptionDialog(null, "Bạn chắc chắn muốn xóa tài khoản này?","Xóa",
                                JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, check, check[1]);
                        if(n == 0){
                            kntk.deleteId(UserID);
                            if(!updateTuTableChinh){
                                kntk.reloadSearchingTable(keyWord2, cbIndex, table);
                            }
                            else{
                                kntk.reloadTable(table);
                            }
                        }
                }
            }
        });

        btOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isAdd == true){
                    if(tfMaNhanVien.getText().isEmpty() || tfTenNhanVien.getText().isEmpty() || tfTaiKhoan.getText().isEmpty() ||
                        tfMatKhau.getText().isEmpty() || tfDiaChi.getText().isEmpty() || tfSDT.getText().isEmpty() ){
                        JOptionPane.showMessageDialog(null, "Bạn phải nhập đầy đủ thông tin", "Lỗi nhập",
                                            JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        String maNhanVien = tfMaNhanVien.getText();
                        String tenNhanVien = tfTenNhanVien.getText();
                        String tenDangNhap = tfTaiKhoan.getText();
                        String matKhau = tfMatKhau.getText();
                        String diaChi = tfDiaChi.getText();                      
                        String sdt = tfSDT.getText();   
                        String loaiTaiKhoan = null;
                        if(cbAccountType.getSelectedIndex() == 0){ 
                        	loaiTaiKhoan = "admin";                                  
                        }else loaiTaiKhoan = "user";
                        TaiKhoan tk = new TaiKhoan(maNhanVien, tenNhanVien, tenDangNhap, matKhau, diaChi, sdt, loaiTaiKhoan);
                        kntk.insert(tk);
                        kntk.reloadTable(table);
                        clear();
                    }
                }
                else
                {
                    if(tfMaNhanVien.getText().isEmpty() || tfTenNhanVien.getText().isEmpty() || tfTaiKhoan.getText().isEmpty() ||
                            tfMatKhau.getText().isEmpty() || tfDiaChi.getText().isEmpty() || tfSDT.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Bạn phải nhập đầy đủ thông tin", "Lỗi nhập",
                                            JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                    	String maNhanVien = tfMaNhanVien.getText();
                        String tenNhanVien = tfTenNhanVien.getText();
                        String tenDangNhap = tfTaiKhoan.getText();
                        String matKhau = tfMatKhau.getText();
                        String diaChi = tfDiaChi.getText();                      
                        String sdt = tfSDT.getText();   
                        String loaiTaiKhoan = null;
                        if(cbAccountType.getSelectedIndex() == 0){ 
                        	loaiTaiKhoan = "admin";                                  
                        }else loaiTaiKhoan = "user";
                        TaiKhoan tk = new TaiKhoan(maNhanVien, tenNhanVien, tenDangNhap, matKhau, diaChi, sdt, loaiTaiKhoan);
                        kntk.updateId(maNhanVien, tk);
                        if(!updateTuTableChinh){
                                kntk.reloadSearchingTable(tenNhanVien, cbIndex, table);
                            }
                            else{
                                kntk.reloadTable(table);
                            }
                        clear();
                    }
                }
            }
        });
        
        btCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
    }
}

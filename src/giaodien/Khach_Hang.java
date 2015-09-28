package giaodien;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import ket_noi_DB.ket_noi;
import ket_noi_DB.ket_noi_kh;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import jxl.Sheet;
//import jxl.Workbook;

/**
 *
 * @author Dai
 */
public class Khach_Hang extends JFrame {

    ket_noi_kh kn = new ket_noi_kh();
    public final String tenCSDL = "khach_hang";
    public final String tenCSDL2 = "vip";
    public final String tenCSDL3 = "hoa_hong";

    ResultSet data;
    ResultSetMetaData metadata;
    Vector tableRecords1 = new Vector();//Vector chứa các dòng dữ liệu của bảng.
    Vector tableRecords2 = new Vector();//Vector chứa các dòng dữ liệu của bảng.
    Vector tableRecords3 = new Vector();//Vector chứa các dòng dữ liệu của bảng.
    Vector tableTitle1 = new Vector();//Vector chứa các tiêu đề của bảng.
    Vector tableTitle2 = new Vector();//Vector chứa các tiêu đề của bảng.
    Vector tableTitle3 = new Vector();//Vector chứa các tiêu đề của bảng.
    JTable tableList1 = new JTable();
    JTable tableList2 = new JTable();
    JTable tableList3 = new JTable();
    JScrollPane tableListScroll1, tableListScroll2, tableListScroll3;
    JPanel tablePanel1, tablePanel2, tablePanel3;
    private JButton btThem, btChinhSua, btXoa, btToanBoKho, btImportExcel, btExportExcel;
//    private final JTable tbCD;
    private JLabel lbTenKH, lbmaKH, lbSDT, lbDiaChi, lbTichDiem, lbKQ, lbSoKQ;
    private JLabel lbVip, lbTichLuy, lbMucGiam, lbUuTien, lbTongTien;
    private JTextField tfSearch, tfTenKH, tfmaKH, tfSDT, tfDiaChi, tfTichDiem;
    private JTextField tfVip, tfTichLuy, tfMucGiam, tfUuTien, tfTongTien;
    private JComboBox cbTimKiem, cbVip;

    public String makhSelect;
    public String idvipSelect;
    public String idSelect;
    public String sql;
    public int indexCBBTimkiem = 0, indexCBBVip = 0;
    public int soKQ = 0;
    public int selecttable = 1;

    public Khach_Hang() {

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setBackground(Color.getHSBColor(0.45f, 0.9f, 0.7f));
        this.setBounds(140, 100, 1000, 550);
        kn.connect_to_DB();
        tableTitle1.add("Mã Số");
        tableTitle1.add("Tên Khách Hàng");
        tableTitle1.add("Số điện thoại");
        tableTitle1.add("Địa Chỉ");
        tableTitle1.add("Tich Điểm");

        tableTitle2.add("VIP");
        tableTitle2.add("Điểm tích");
        tableTitle2.add("Mức giảm");

        tableTitle3.add("Ưu tiên");
        tableTitle3.add("Tổng tiền");
        tableTitle3.add("Mức giảm");

        this.get();
        this.add_to_JFrame();
        this.setVisible(true);
    }

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
        Khach_Hang x = new Khach_Hang();
    }

    private void get() {
        ///////////////////////////////////////////////////////////////////////////
        kn.truyVanSQL(tableTitle1, tableRecords1, tableList1, "select * from " + tenCSDL);
        soKQ = tableList1.getRowCount();
        //////////////////////////////////////////////////////////////////////////////
        kn.truyVanSQL(tableTitle2, tableRecords2, tableList2, "select * from " + tenCSDL2);
        ////////////////////////////////////////////////////////////////////////////////
        kn.truyVanSQL(tableTitle3, tableRecords3, tableList3, "select * from " + tenCSDL3);
    }

    private void add_to_JFrame() {
        lbSoKQ = new JLabel(soKQ + "");

        BufferedImage bufferadd = null;
        BufferedImage bufferedit = null;
        BufferedImage bufferremove = null;
        BufferedImage bufferimport = null;
        BufferedImage bufferexport = null;

        try {
            bufferadd = ImageIO.read(new File("src//image//add.png"));
            bufferedit = ImageIO.read(new File("src//image//edit.png"));
            bufferremove = ImageIO.read(new File("src//image//remove.png"));
            bufferimport = ImageIO.read(new File("src//image//import.png"));
            bufferexport = ImageIO.read(new File("src//image//export.png"));
        } catch (IOException ex) {
            System.out.println("lỗi đọc ảnh");
            Logger.getLogger(Khach_Hang.class.getName()).log(Level.SEVERE, null, ex);
        }

        ImageIcon iconadd = new ImageIcon(bufferadd.getScaledInstance(55, 55, BufferedImage.SCALE_SMOOTH));
        ImageIcon iconedit = new ImageIcon(bufferedit.getScaledInstance(55, 55, BufferedImage.SCALE_SMOOTH));
        ImageIcon iconremove = new ImageIcon(bufferremove.getScaledInstance(55, 55, BufferedImage.SCALE_SMOOTH));
        ImageIcon iconimport = new ImageIcon(bufferimport.getScaledInstance(30, 30, BufferedImage.SCALE_SMOOTH));
        ImageIcon iconexport = new ImageIcon(bufferexport.getScaledInstance(50, 30, BufferedImage.SCALE_SMOOTH));

        tfSearch = new JTextField();
        tfSearch.setBounds(35, 10, 315, 35);
        tfSearch.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent ke) {

            }

            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                timkiemThuong();
                kn.truyVanSQL(tableTitle1, tableRecords1, tableList1, sql);
                canLe1();
                soKQ = tableList1.getRowCount();
                lbSoKQ.setText(soKQ + "");
            }
        });
        this.add(tfSearch);

        DefaultComboBoxModel model1 = new DefaultComboBoxModel();
        model1.addElement("TÍCH ĐIỂM <= 5.000.000"); //0
        model1.addElement("5.000.000 < TÍCH ĐIỂM <= 10.000.000"); // 1
        model1.addElement("10.000.000 < TÍCH ĐIỂM <= 15.000.000"); // 2
        model1.addElement("15.000.000 <= TÍCH ĐIỂM"); //3

        cbVip = new JComboBox();
        cbVip.setModel(model1);
        cbVip.setVisible(false);
        cbVip.setBounds(10, 10, 340, 35);
        cbVip.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                indexCBBVip = cbVip.getSelectedIndex();
                timkiemCombobox();
                kn.truyVanSQL(tableTitle1, tableRecords1, tableList1, sql);
                canLe1();
                soKQ = tableList1.getRowCount();
                lbSoKQ.setText(soKQ + "");
            }
        });
        this.add(cbVip);

        btToanBoKho = new JButton("Tất cả");
        btToanBoKho.setBounds(397, 10, 120, 35);
        btToanBoKho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                sql = "select * from oop.khach_hang";
                kn.truyVanSQL(tableTitle1, tableRecords1, tableList1, sql);
                canLe1();
                soKQ = tableList1.getRowCount();
                lbSoKQ.setText(soKQ + "");
            }

        });
        this.add(btToanBoKho);

        DefaultComboBoxModel model2 = new DefaultComboBoxModel();
        model2.addElement("Tất Cả");
        model2.addElement("Mã Khách Hàng");
        model2.addElement("Tên Khách Hàng");
        model2.addElement("Số Điện Thoại");
        model2.addElement("Địa Chỉ");
        model2.addElement("Tích Lũy Khoảng");

        cbTimKiem = new JComboBox();
        cbTimKiem.setModel(model2);
        cbTimKiem.setBounds(542, 10, 150, 35);
        cbTimKiem.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent ie) {
                JComboBox newCBB = (JComboBox) ie.getSource();
                indexCBBTimkiem = newCBB.getSelectedIndex();
                if (indexCBBTimkiem != 5) {
                    cbVip.setVisible(false);
                    tfSearch.setVisible(true);
                    timkiemThuong();
                    kn.truyVanSQL(tableTitle1, tableRecords1, tableList1, sql);
                    canLe1();
                    soKQ = tableList1.getRowCount();
                    lbSoKQ.setText(soKQ + "");
                } else {
                    tfSearch.setVisible(false);
                    cbVip.setVisible(true);
                    timkiemCombobox();
                    kn.truyVanSQL(tableTitle1, tableRecords1, tableList1, sql);
                    canLe1();
                    soKQ = tableList1.getRowCount();
                    lbSoKQ.setText(soKQ + "");
                }
            }
        }
        );
        this.add(cbTimKiem);

        lbKQ = new JLabel("Số kết quả");
        lbKQ.setHorizontalAlignment(JLabel.CENTER);
        lbKQ.setBounds(730, 10, 120, 35);
        lbKQ.setBackground(Color.white);
        lbKQ.setOpaque(true);
        this.add(lbKQ);

        lbSoKQ = new JLabel(soKQ + "");
        lbSoKQ.setHorizontalAlignment(JLabel.CENTER);
        lbSoKQ.setBounds(860, 10, 115, 35);
        lbSoKQ.setBackground(Color.white);
        int style = 1; //0: plain, 1: bold, 2: italic, 3: bold and italic
        lbSoKQ.setFont(new Font("Arial", style, 30));
        lbSoKQ.setOpaque(true);
        this.add(lbSoKQ);

        //phan nhap xuat chinh sua
        lbmaKH = new JLabel("Mã Khách Hàng");
        lbmaKH.setBounds(35, 50, 110, 35);
        this.add(lbmaKH);

        lbVip = new JLabel("Mức VIP");
        lbVip.setBounds(35, 50, 110, 35);
        lbVip.setVisible(false);
        this.add(lbVip);

        lbUuTien = new JLabel("Mức Ưu Tiên");
        lbUuTien.setBounds(35, 50, 110, 35);
        lbUuTien.setVisible(false);
        this.add(lbUuTien);

        tfmaKH = new JTextField();
        tfmaKH.setBounds(150, 50, 200, 35);
        this.add(tfmaKH);

        tfVip = new JTextField();
        tfVip.setBounds(150, 50, 200, 35);
        tfVip.setVisible(false);
        this.add(tfVip);

        tfUuTien = new JTextField();
        tfUuTien.setBounds(150, 50, 200, 35);
        tfUuTien.setVisible(false);
        this.add(tfUuTien);

        lbTenKH = new JLabel("Tên Khách Hàng");
        lbTenKH.setBounds(35, 100, 100, 35);
        this.add(lbTenKH);

        lbTichLuy = new JLabel("Tích điểm");
        lbTichLuy.setBounds(35, 100, 100, 35);
        lbTichLuy.setVisible(false);
        this.add(lbTichLuy);

        lbTongTien = new JLabel("Tổng Tiền");
        lbTongTien.setBounds(35, 100, 100, 35);
        lbTongTien.setVisible(false);
        this.add(lbTongTien);

        tfTenKH = new JTextField();
        tfTenKH.setBounds(150, 100, 200, 35);
        this.add(tfTenKH);

        tfTichLuy = new JTextField();
        tfTichLuy.setBounds(150, 100, 200, 35);
        tfTichLuy.setVisible(false);
        this.add(tfTichLuy);

        tfTongTien = new JTextField();
        tfTongTien.setBounds(150, 100, 200, 35);
        tfTongTien.setVisible(false);
        this.add(tfTongTien);

        lbSDT = new JLabel("Số Điện Thoại");
        lbSDT.setBounds(35, 150, 110, 35);
        this.add(lbSDT);

        lbMucGiam = new JLabel("Mức Giảm");
        lbMucGiam.setBounds(35, 150, 110, 35);
        lbMucGiam.setVisible(false);
        this.add(lbMucGiam);

        tfSDT = new JTextField();
        tfSDT.setBounds(150, 150, 200, 35);
        this.add(tfSDT);

        tfMucGiam = new JTextField();
        tfMucGiam.setBounds(150, 150, 200, 35);
        tfMucGiam.setVisible(false);
        this.add(tfMucGiam);

        lbDiaChi = new JLabel("Địa Chỉ");
        lbDiaChi.setBounds(35, 200, 110, 35);
        this.add(lbDiaChi);

        tfDiaChi = new JTextField();
        tfDiaChi.setBounds(150, 200, 200, 35);
        this.add(tfDiaChi);

        lbTichDiem = new JLabel("Tích Lũy");
        lbTichDiem.setBounds(35, 250, 110, 35);
        this.add(lbTichDiem);

        tfTichDiem = new JTextField();
        tfTichDiem.setBounds(150, 250, 200, 35);
        this.add(tfTichDiem);

        btThem = new JButton();
        btThem.setToolTipText("Thêm Khách Hàng");
        btThem.setBounds(50, 320, 80, 80);
        btThem.setIcon(iconadd);
        btThem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Them();
                soKQ = tableList1.getRowCount();
                lbSoKQ.setText(soKQ + "");
            }
        }
        );
        this.add(btThem);

        btChinhSua = new JButton();
        btChinhSua.setToolTipText("Chỉnh sửa thông tin");
        btChinhSua.setBounds(50 + 110, 320, 80, 80);
        btChinhSua.setIcon(iconedit);
        btChinhSua.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Sua();
                soKQ = tableList1.getRowCount();
                lbSoKQ.setText(soKQ + "");
            }
        });
        this.add(btChinhSua);

        btXoa = new JButton();
        btXoa.setToolTipText("Thôi lưu Khách Hàng");
        btXoa.setBounds(50 + 110 * 2, 320, 80, 80);
        btXoa.setIcon(iconremove);
        btXoa.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Xoa();
                soKQ = tableList1.getRowCount();
                lbSoKQ.setText(soKQ + "");
            }
        }
        );
        this.add(btXoa);

        btImportExcel = new JButton("IMPORT");
        btImportExcel.setBounds(50, 450, 140, 40);
        btImportExcel.setIcon(iconimport);
        btImportExcel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser filechoose = new JFileChooser();
                while (true) {
                    int returnValue = filechoose.showOpenDialog(null);
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        String filename = filechoose.getSelectedFile().getName();
                        filename = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
                        System.out.println(filename);
                        if (filename.equals("xls")) {
                            try {
                                Workbook workbook = new HSSFWorkbook(new FileInputStream(filechoose.getSelectedFile()));
                                kn.loadFile(workbook, tenCSDL);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(Khach_Hang.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(Khach_Hang.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;

                        } else if (filename.equals("xlsx")) {
                            try {
                                Workbook workbook = new XSSFWorkbook(new FileInputStream(filechoose.getSelectedFile()));
                                kn.loadFile(workbook, tenCSDL);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(Khach_Hang.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(Khach_Hang.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                        } else {
                            JOptionPane.showMessageDialog(filechoose, "Đây không phải là 1 file không phải là Excel", "MediaCenter Warning", JOptionPane.WARNING_MESSAGE);

                        }
                    } else {
                        break;
                    }
                }
            }
        }
        );
        this.add(btImportExcel);

        btExportExcel = new JButton("EXPORT");
        btExportExcel.setBounds(210, 450, 140, 40);
        btExportExcel.setIcon(iconexport);
        btExportExcel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser filechoose = new JFileChooser();
                filechoose.addChoosableFileFilter(new FileNameExtensionFilter("Excel(*.xls)", "xls"));
                filechoose.addChoosableFileFilter(new FileNameExtensionFilter("Excel(*.xlsx)", "xlsx"));
                while (true) {
                    int returnValue = filechoose.showSaveDialog(null);
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        String path = filechoose.getSelectedFile().getPath();
                        System.out.println(path);
                        if (returnType(path) == null) {
                            if (filechoose.getFileFilter().getDescription().equals("Excel(*.xls)")) {
                                path = path + ".xls";
                                Workbook workbook = new HSSFWorkbook();
                                kn.luuFile(workbook, path, sql, tenCSDL);
                                break;
                            } else if (filechoose.getFileFilter().getDescription().equals("Excel(*.xlsx)")) {
                                path = path + ".xlsx";
                                Workbook workbook = new XSSFWorkbook();
                                kn.luuFile(workbook, path, sql, tenCSDL);
                                break;
                            } else {
                                JOptionPane.showMessageDialog(filechoose, "Vui lòng đặt định dạng file", "MediaCenter Warning", JOptionPane.WARNING_MESSAGE);
                            }
                        } else if (returnType(path).equals("xls")) {
                            Workbook workbook = new HSSFWorkbook();
                            kn.luuFile(workbook, path, sql, tenCSDL);
                            break;
                        } else if (returnType(path).equals("xlsx")) {
                            Workbook workbook = new XSSFWorkbook();
                            kn.luuFile(workbook, path, sql, tenCSDL);
                            break;
                        } else {
                            JOptionPane.showMessageDialog(filechoose, "Vui lòng export dưới dạng file Excel", "MediaCenter Warning", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        break;
                        // họ không chọn lưu thì kệ
                    }
                }
            }
        });
        this.add(btExportExcel);

        tablePanel1 = new JPanel();
        tablePanel1.setBounds(400, 50, 580, 250);
        tablePanel1.setLayout(null);
        canLe1(); // Căn độ rộng các cột bảng
        tableList1.setRowHeight(30);
        tableList1.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
//                if (e.getSource() == tableList) {
                int iDongDaChon = tableList1.getSelectedRow();
                if (iDongDaChon != -1) {
                    selecttable = 1;
                    anHien();
                    Vector vDongDaChon = (Vector) tableRecords1.get(iDongDaChon);
                    String makh = vDongDaChon.get(0).toString();
                    String ten = vDongDaChon.get(1).toString();
                    String sdt = vDongDaChon.get(2).toString();
                    String diachi = vDongDaChon.get(3).toString();
                    String tichdiem = vDongDaChon.get(4).toString();
                    makhSelect = makh;
                    tfmaKH.setText(makh);
                    tfTenKH.setText(ten);
                    tfSDT.setText(sdt);
                    tfDiaChi.setText(diachi);
                    tfTichDiem.setText(tichdiem);
                }
//                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        tableListScroll1 = new JScrollPane(tableList1);
        tableListScroll1.setBounds(0, 0, 580, 250);
        tablePanel1.add(tableListScroll1);
        this.add(tablePanel1);

        tablePanel2 = new JPanel();
        tablePanel2.setBounds(400, 300, 290, 195);
        tablePanel2.setLayout(null);
        canLe2();
        tableList2.setRowHeight(30);
        tableList2.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
                int iDongDaChon = tableList2.getSelectedRow();
                if (iDongDaChon != -1) {
                    selecttable = 2;
                    anHien();
                    Vector vDongDaChon = (Vector) tableRecords2.get(iDongDaChon);
                    String vip = vDongDaChon.get(0).toString();
                    String tichluy = vDongDaChon.get(1).toString();
                    String mucgiam = vDongDaChon.get(2).toString();
                    idvipSelect = vip;
                    tfVip.setText(vip);
                    tfTichLuy.setText(tichluy);
                    tfMucGiam.setText(mucgiam);
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
        tableListScroll2 = new JScrollPane(tableList2);
        tableListScroll2.setBounds(0, 0, 290, 195);
        tablePanel2.add(tableListScroll2);
        this.add(tablePanel2);

        tablePanel3 = new JPanel();
        tablePanel3.setBounds(690, 300, 290, 195);
        tablePanel3.setLayout(null);
        canLe3();
        tableList3.setRowHeight(30);
        tableList3.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
                int iDongDaChon = tableList3.getSelectedRow();
                if (iDongDaChon != -1) {
                    selecttable = 3;
                    anHien();
                    Vector vDongDaChon = (Vector) tableRecords3.get(iDongDaChon);
                    String uutien = vDongDaChon.get(0).toString();
                    String tongtien = vDongDaChon.get(1).toString();
                    String mucgiam = vDongDaChon.get(2).toString();
                    idSelect = uutien;
                    tfUuTien.setText(uutien);
                    tfTongTien.setText(tongtien);
                    tfMucGiam.setText(mucgiam);
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
        tableListScroll3 = new JScrollPane(tableList3);
        tableListScroll3.setBounds(0, 0, 290, 195);
        tablePanel3.add(tableListScroll3);
        this.add(tablePanel3);

    }

    //check các trường rỗng không và trường tích điểm phải là số
    private boolean check1() {
        if (tfmaKH.getText().length() == 0
                || tfTenKH.getText().length() == 0
                || tfSDT.getText().length() == 0
                || tfDiaChi.getText().length() == 0
                || tfTichDiem.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
            return false;
        }
        try {
            int i = Integer.parseInt(tfTichDiem.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Tích Điểm phải là số");
            return false;
        }
        return true;
    }

    private boolean check2() {
        if (tfVip.getText().length() == 0
                || tfTichLuy.getText().length() == 0
                || tfMucGiam.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
            return false;
        }
        try {
            int i = Integer.parseInt(tfTichLuy.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Tích Điểm phải là số");
            return false;
        }
        try {
            int i = Integer.parseInt(tfMucGiam.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Mức Giảm phải là số");
            return false;
        }
        return true;
    }

    private boolean check3() {
        if (tfUuTien.getText().length() == 0
                || tfTongTien.getText().length() == 0
                || tfMucGiam.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
            return false;
        }
        try {
            int i = Integer.parseInt(tfTongTien.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Tổng Tiền phải là số");
            return false;
        }
        try {
            int i = Integer.parseInt(tfMucGiam.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Mức Giảm phải là số");
            return false;
        }
        return true;
    }

    // trả về type của path file được chọn
    private static String returnType(String x) {
        String x1 = x.substring(x.lastIndexOf("\\"), x.length());
        if (x1.lastIndexOf(".") == -1) {
            return null;
        } else {
            if (x1.substring(x1.lastIndexOf("."), x1.length()).equals(".xlsx")) {
                return "xlsx";
            } else if (x1.substring(x1.lastIndexOf("."), x1.length()).equals(".xls")) {
                return "xls";
            } else {
                return "wrongtype";
            }
        }
    }

    // xóa các trường thàng rỗng
    private void resettext() {
        if (selecttable == 1) {
            tfmaKH.setText("");
            tfTenKH.setText("");
            tfSDT.setText("");
            tfDiaChi.setText("");
            tfTichDiem.setText("");
            tfmaKH.requestFocus();
        } else if (selecttable == 2) {
            tfVip.setText("");
            tfTichLuy.setText("");
            tfMucGiam.setText("");
            tfVip.requestFocus();
        } else if (selecttable == 3) {
            tfUuTien.setText("");
            tfTongTien.setText("");
            tfMucGiam.setText("");
            tfUuTien.requestFocus();
        }

    }

    // tùy chỉnh độ rộng của các cột trong bảng
    private void canLe1() {
        tableList1.getColumnModel().getColumn(0).setMaxWidth(100);
        tableList1.getColumnModel().getColumn(1).setPreferredWidth(100);
        tableList1.getColumnModel().getColumn(2).setPreferredWidth(80);
        tableList1.getColumnModel().getColumn(3).setPreferredWidth(100);
        tableList1.getColumnModel().getColumn(4).setMaxWidth(80);
    }

    private void canLe2() {
        tableList2.getColumnModel().getColumn(0).setPreferredWidth(50);
        tableList2.getColumnModel().getColumn(1).setPreferredWidth(170);
        tableList2.getColumnModel().getColumn(2).setPreferredWidth(70);
    }

    private void canLe3() {
        tableList3.getColumnModel().getColumn(0).setPreferredWidth(70);
        tableList3.getColumnModel().getColumn(1).setPreferredWidth(150);
        tableList3.getColumnModel().getColumn(2).setPreferredWidth(70);
    }

    // trả về sql hiện tại
    private void timkiemCombobox() {
        if (indexCBBVip == 0) {
            sql = "select * from " + tenCSDL + " where "
                    + "tichdiem <= 5000000";
        } else if (indexCBBVip == 1) {
            sql = "select * from " + tenCSDL + " where "
                    + "tichdiem <= 10000000 and tichdiem > 5000000";
        } else if (indexCBBVip == 2) {
            sql = "select * from " + tenCSDL + " where "
                    + "tichdiem <= 15000000 and tichdiem > 10000000";
        } else {
            sql = "select * from " + tenCSDL + " where "
                    + "tichdiem > 15000000";
        }
    }

    // trả về sql hiện tại
    private void timkiemThuong() {
        switch (indexCBBTimkiem) {
            case 0: {
                sql = "select * from oop.khach_hang where "
                        + "(makh like '%" + tfSearch.getText() + "%') or "
                        + "(tenkh like '%" + tfSearch.getText() + "%') or "
                        + "(sdt like '%" + tfSearch.getText() + "%') or "
                        + "(diachi like '%" + tfSearch.getText() + "%') or "
                        + "(tichdiem like '%" + tfSearch.getText() + "%')";
                break;
            }
            case 1: {
                sql = "select * from oop.khach_hang where "
                        + "makh like '%" + tfSearch.getText() + "%'";
                break;
            }
            case 2: {
                sql = "select * from oop.khach_hang where "
                        + "tenkh like '%" + tfSearch.getText() + "%'";
                break;
            }
            case 3: {
                sql = "select * from oop.khach_hang where "
                        + "sdt like '%" + tfSearch.getText() + "%'";
                break;
            }
            case 4: {
                sql = "select * from oop.khach_hang where "
                        + "diachi like '%" + tfSearch.getText() + "%'";
                break;
            }
            case 5: {
                sql = "select * from oop.khach_hang where "
                        + "tichdiem like '%" + tfSearch.getText() + "%'";
                break;
            }
        }
    }

    private void anHien() {
        if (selecttable == 1) {
            lbTenKH.setVisible(true);
            tfTenKH.setVisible(true);
            lbmaKH.setVisible(true);
            tfmaKH.setVisible(true);
            lbSDT.setVisible(true);
            tfSDT.setVisible(true);
            lbDiaChi.setVisible(true);
            tfDiaChi.setVisible(true);
            lbTichDiem.setVisible(true);
            tfTichDiem.setVisible(true);
            lbVip.setVisible(false);
            tfVip.setVisible(false);
            lbTichLuy.setVisible(false);
            tfTichLuy.setVisible(false);
            lbMucGiam.setVisible(false);
            tfMucGiam.setVisible(false);
            lbUuTien.setVisible(false);
            tfUuTien.setVisible(false);
            lbTongTien.setVisible(false);
            tfTongTien.setVisible(false);
        } else if (selecttable == 2) {
            lbTenKH.setVisible(false);
            tfTenKH.setVisible(false);
            lbmaKH.setVisible(false);
            tfmaKH.setVisible(false);
            lbSDT.setVisible(false);
            tfSDT.setVisible(false);
            lbDiaChi.setVisible(false);
            tfDiaChi.setVisible(false);
            lbTichDiem.setVisible(false);
            tfTichDiem.setVisible(false);
            lbVip.setVisible(true);
            tfVip.setVisible(true);
            lbTichLuy.setVisible(true);
            tfTichLuy.setVisible(true);
            lbMucGiam.setVisible(true);
            tfMucGiam.setVisible(true);
            lbUuTien.setVisible(false);
            tfUuTien.setVisible(false);
            lbTongTien.setVisible(false);
            tfTongTien.setVisible(false);
        } else if (selecttable == 3) {
            lbTenKH.setVisible(false);
            tfTenKH.setVisible(false);
            lbmaKH.setVisible(false);
            tfmaKH.setVisible(false);
            lbSDT.setVisible(false);
            tfSDT.setVisible(false);
            lbDiaChi.setVisible(false);
            tfDiaChi.setVisible(false);
            lbTichDiem.setVisible(false);
            tfTichDiem.setVisible(false);
            lbVip.setVisible(false);
            tfVip.setVisible(false);
            lbTichLuy.setVisible(false);
            tfTichLuy.setVisible(false);
            lbMucGiam.setVisible(true);
            tfMucGiam.setVisible(true);
            lbUuTien.setVisible(true);
            tfUuTien.setVisible(true);
            lbTongTien.setVisible(true);
            tfTongTien.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "selecttable sai");
        }
    }

    private void Them() {
        if (selecttable == 1) {
            if (check1()) {
                try {
                    Vector record = new Vector();
                    record.add(tfmaKH.getText());
                    record.add(tfTenKH.getText());
                    record.add(tfSDT.getText());
                    record.add(tfDiaChi.getText());
                    record.add(tfTichDiem.getText());
                    String sql = "INSERT INTO " + tenCSDL + " (makh, tenkh, sdt, diachi, tichdiem) VALUES (";
                    sql = sql + "'" + tfmaKH.getText() + "'" + ",";
                    sql = sql + "'" + tfTenKH.getText() + "'" + ",";
                    sql = sql + "'" + tfSDT.getText() + "'" + ",";
                    sql = sql + "'" + tfDiaChi.getText() + "'" + ",";
                    sql = sql + "'" + tfTichDiem.getText() + "'";
                    sql = sql + ")";
                    ResultSet test = null;
                    try {
                        test = kn.statement.executeQuery("select * from " + tenCSDL + " where makh = '" + tfmaKH.getText() + "'");
                    } catch (SQLException ex) {
                        System.out.println("truy vấn test thêm lỗi");
                        Logger.getLogger(Khach_Hang.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (test.next()) {
                        JOptionPane.showMessageDialog(null, "Mã Khách Hàng đã tồn tại");
                    } else {
                        int key = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm không?");
                        if (key == 0) {
                            tableRecords1.add(record);
                            tableList1.setModel(new DefaultTableModel(tableRecords1, tableTitle1));
                            canLe1();
                            JOptionPane.showMessageDialog(rootPane, "Thêm thành công");
                            resettext();
                            try {
                                kn.statement.executeUpdate(sql);
                            } catch (SQLException ex) {
                                System.out.println("lỗi select dữ liệu khi thêm");
                                Logger.getLogger(Khach_Hang.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if (key == 1) {
                            resettext();
                        } else {
                            // khong lam gi ca neu nguoi ta press cance
                        }
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Khach_Hang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (selecttable == 2) {
            if (check2()) {
                try {
                    Vector record = new Vector();
                    record.add(tfVip.getText());
                    record.add(tfTichLuy.getText());
                    record.add(tfMucGiam.getText());

                    String sql = "INSERT INTO " + tenCSDL2 + " (idvip, tichdiem, giamgia) VALUES (";
                    sql = sql + "'" + tfVip.getText() + "'" + ",";
                    sql = sql + "'" + tfTichLuy.getText() + "'" + ",";
                    sql = sql + "'" + tfMucGiam.getText() + "'";
                    sql = sql + ")";
                    ResultSet test = null;
                    try {
                        test = kn.statement.executeQuery("select * from " + tenCSDL2 + " where idvip = '" + tfVip.getText() + "'");
                    } catch (SQLException ex) {
                        Logger.getLogger(Khach_Hang.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (test.next()) {
                        JOptionPane.showMessageDialog(null, "Mức VIP đã tồn tại");
                    } else {
                        test = kn.statement.executeQuery("select * from " + tenCSDL2 + " where tichdiem = '" + tfTichLuy.getText() + "'");
                        if (test.next()) {
                            JOptionPane.showMessageDialog(null, "Mức Tích Lũy đã tồn tại");
                        } else {
                            int key = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm không?");
                            if (key == 0) {
                                tableRecords2.add(record);
                                tableList2.setModel(new DefaultTableModel(tableRecords2, tableTitle2));
                                canLe2();
                                JOptionPane.showMessageDialog(rootPane, "Thêm thành công");
                                resettext();
                                try {
                                    kn.statement.executeUpdate(sql);
                                } catch (SQLException ex) {
                                    Logger.getLogger(Khach_Hang.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else if (key == 1) {
                                resettext();
                            } else {
                                // khong lam gi ca neu nguoi ta press cance
                            }
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Khach_Hang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (selecttable == 3) {
            if (check3()) {
                try {
                    Vector record = new Vector();
                    record.add(tfUuTien.getText());
                    record.add(tfTongTien.getText());
                    record.add(tfMucGiam.getText());

                    String sql = "INSERT INTO " + tenCSDL3 + " (id, gia, giamgia) VALUES (";
                    sql = sql + "'" + tfUuTien.getText() + "'" + ",";
                    sql = sql + "'" + tfTongTien.getText() + "'" + ",";
                    sql = sql + "'" + tfMucGiam.getText() + "'";
                    sql = sql + ")";
                    ResultSet test = null;
                    try {
                        test = kn.statement.executeQuery("select * from " + tenCSDL3 + " where id = '" + tfUuTien.getText() + "'");
                    } catch (SQLException ex) {
                        Logger.getLogger(Khach_Hang.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (test.next()) {
                        JOptionPane.showMessageDialog(null, "Mức Ưu Tiên đã tồn tại");
                    } else {
                        test = kn.statement.executeQuery("select * from " + tenCSDL3 + " where gia = '" + tfTongTien.getText() + "'");
                        if (test.next()) {
                            JOptionPane.showMessageDialog(null, "Mức Tổng Thanh Toán đã tồn tại");
                        } else {
                            int key = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm không?");
                            if (key == 0) {
                                tableRecords3.add(record);
                                tableList3.setModel(new DefaultTableModel(tableRecords3, tableTitle3));
                                canLe3();
                                JOptionPane.showMessageDialog(rootPane, "Thêm thành công");
                                resettext();
                                try {
                                    kn.statement.executeUpdate(sql);
                                } catch (SQLException ex) {
                                    Logger.getLogger(Khach_Hang.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else if (key == 1) {
                                resettext();
                            } else {
                                // khong lam gi ca neu nguoi ta press cance
                            }
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Khach_Hang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            System.out.println("select table không đúng - Thêm");
        }
    }

    private void Sua() {
        if (selecttable == 1) {
            try {
                int iDongDaChon = tableList1.getSelectedRow();
                if (iDongDaChon == -1) {
                    JOptionPane.showMessageDialog(rootPane, "Xin vui lòng chọn dòng cần sửa");
                } else if (check1()) {

                    String sql = "update " + tenCSDL + " set tenkh = '" + tfTenKH.getText().toString()
                            + "', sdt = '" + tfSDT.getText().toString()
                            + "', diachi = '" + tfDiaChi.getText().toString()
                            + "', tichdiem = '" + tfTichDiem.getText().toString()
                            + "' where makh = '" + tfmaKH.getText().toString() + "'";
                    int key = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa không?");
                    if (key == 0) {
                        if (!makhSelect.equals(tfmaKH.getText())) {
                            tfmaKH.setText(makhSelect);
                            JOptionPane.showMessageDialog(null, "KHông được đổi mã khách hàng");
                            return;
                        }
                        Vector record = new Vector();
                        record.add(tfmaKH.getText());
                        record.add(tfTenKH.getText());
                        record.add(tfSDT.getText());
                        record.add(tfDiaChi.getText());
                        record.add(tfTichDiem.getText());
                        tableRecords1.set(iDongDaChon, record);
                        tableList1.setModel(new DefaultTableModel(tableRecords1, tableTitle1));
                        canLe1();
                        JOptionPane.showMessageDialog(rootPane, "Cập nhật xong");
                        kn.statement.executeUpdate(sql);
                        resettext();
                    } else if (key == 1) {
                        resettext();
                    } else {

                    }
                }
            } catch (SQLException ex) {
                System.out.println("lỗi Chỉnh Sửa");
                Logger.getLogger(Khach_Hang.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (selecttable == 2) {
            try {
                int iDongDaChon = tableList2.getSelectedRow();
                if (iDongDaChon == -1) {
                    JOptionPane.showMessageDialog(rootPane, "Xin vui lòng chọn dòng cần sửa");
                } else if (check2()) {

                    String sql = "update " + tenCSDL2 + " set idvip = '" + tfVip.getText().toString()
                            + "', tichdiem = '" + tfTichLuy.getText().toString()
                            + "', giamgia = '" + tfMucGiam.getText().toString()
                            + "' where idvip = '" + idvipSelect + "'";
                    int key = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa không?");
                    if (key == 0) {
                        Vector record = new Vector();
                        record.add(tfVip.getText());
                        record.add(tfTichLuy.getText());
                        record.add(tfMucGiam.getText());
                        tableRecords2.set(iDongDaChon, record);
                        tableList2.setModel(new DefaultTableModel(tableRecords2, tableTitle2));
                        canLe2();
                        JOptionPane.showMessageDialog(rootPane, "Cập nhật xong");
                        kn.statement.executeUpdate(sql);
                        resettext();
                    } else if (key == 1) {
                        resettext();
                    } else {

                    }
                }
            } catch (SQLException ex) {
                System.out.println("lỗi Chỉnh Sửa");
                Logger.getLogger(Khach_Hang.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (selecttable == 3) {
            try {
                int iDongDaChon = tableList3.getSelectedRow();
                if (iDongDaChon == -1) {
                    JOptionPane.showMessageDialog(rootPane, "Xin vui lòng chọn dòng cần sửa");
                } else if (check3()) {

                    String sql = "update " + tenCSDL3 + " set id = '" + tfUuTien.getText().toString()
                            + "', gia = '" + tfTongTien.getText().toString()
                            + "', giamgia = '" + tfMucGiam.getText().toString()
                            + "' where id = '" + idSelect + "'";
                    int key = JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa không?");
                    if (key == 0) {
                        Vector record = new Vector();
                        record.add(tfUuTien.getText());
                        record.add(tfTongTien.getText());
                        record.add(tfMucGiam.getText());
                        tableRecords3.set(iDongDaChon, record);
                        tableList3.setModel(new DefaultTableModel(tableRecords3, tableTitle3));
                        canLe3();
                        JOptionPane.showMessageDialog(rootPane, "Cập nhật xong");
                        kn.statement.executeUpdate(sql);
                        resettext();
                    } else if (key == 1) {
                        resettext();
                    } else {

                    }
                }
            } catch (SQLException ex) {
                System.out.println("lỗi Chỉnh Sửa");
                Logger.getLogger(Khach_Hang.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("selecttable lỗi ở Sửa");
        }
    }

    private void Xoa() {
        if (selecttable == 1) {
            try {
                int iDongDaChon = tableList1.getSelectedRow();
                if (iDongDaChon == -1) {
                    JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn bản ghi cần xóa");
                } else {
                    Vector vDongDaChon = (Vector) tableRecords1.get(iDongDaChon);
                    if (JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc chắn muốn xóa dòng đã chọn có MaKH: " + makhSelect, "Lua chon", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        kn.statement.executeUpdate("DELETE FROM " + tenCSDL + " WHERE makh = " + "'" + makhSelect + "'");
                        tableRecords1.remove(iDongDaChon);
                        tableList1.setModel(new DefaultTableModel(tableRecords1, tableTitle1));
                        canLe1();
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi Xóa Dòng");
                Logger.getLogger(Khach_Hang.class.getName()).log(Level.SEVERE, null, ex);
            }
            resettext();
        } else if (selecttable == 2) {
            try {
                int iDongDaChon = tableList2.getSelectedRow();
                if (iDongDaChon == -1) {
                    JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn bản ghi cần xóa");
                } else {
                    if (JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc chắn muốn xóa dòng đã chọn có mức vip: " + idvipSelect, "Lựa chọn", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        kn.statement.executeUpdate("DELETE FROM " + tenCSDL2 + " WHERE idvip = " + "'" + idvipSelect + "'");
                        tableRecords2.remove(iDongDaChon);
                        tableList2.setModel(new DefaultTableModel(tableRecords2, tableTitle2));
                        canLe2();
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi Xóa Dòng");
                Logger.getLogger(Khach_Hang.class.getName()).log(Level.SEVERE, null, ex);
            }
            resettext();
        } else if (selecttable == 3) {
            try {
                int iDongDaChon = tableList3.getSelectedRow();
                if (iDongDaChon == -1) {
                    JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn bản ghi cần xóa");
                } else {
                    if (JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc chắn muốn xóa dòng đã chọn có mức khuyến mại: " + idSelect, "Lựa chọn", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        kn.statement.executeUpdate("DELETE FROM " + tenCSDL3 + " WHERE id = " + "'" + idSelect + "'");
                        tableRecords3.remove(iDongDaChon);
                        tableList3.setModel(new DefaultTableModel(tableRecords3, tableTitle3));
                        canLe3();
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi Xóa Dòng");
                Logger.getLogger(Khach_Hang.class.getName()).log(Level.SEVERE, null, ex);
            }
            resettext();
        } else {
            System.out.println("selecttable lỗi ở Xóa");
        }
    }
}


package UI;

import file.GetData;
import file.ManageFileCategory;
import file.ManageFileProduct;
import file.ManageFileUser;
import java.awt.HeadlessException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.Bill;
import model.Category;
import model.Product;
import model.User;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.xssf.usermodel.*;


/**
 *
 * @author Doan Huu Minh
 */
public class Admin extends javax.swing.JFrame {

    /**
     * Creates new form Admin
     */
    private static GetData getData = new GetData();    
    private static final ManageFileCategory manageFileCategory = new ManageFileCategory();
    private static final ManageFileProduct manageFileProduct = new ManageFileProduct();
    private static final ManageFileUser manageFileUser = new ManageFileUser();   
    
    private static final List<Category> listCategorys = getData.getDataCategoryFromFile();
    private static List<Product> listProduct = getData.getDataProductFromFile();
    private static List<User> listUser = getData.getDataUserFromFile();
    private static List<Bill> listBills = getData.getDataBillFromFile();
    
    
    private DefaultTableModel modelProduct = new DefaultTableModel();//Product 
    private DefaultTableModel modelBill = new DefaultTableModel();
    private DefaultTableModel modelUser = new DefaultTableModel();
    private DefaultTableModel modelCategory = new DefaultTableModel();
    private DefaultTableModel modelDataSearch = new DefaultTableModel();
    
    
    private static final String file_categories = "E:\\HOC TAP\\LAP_TRINH_JAVA\\JAVA\\Restaurant_Management\\src\\datas\\categories.txt";
    private static final String file_products = "E:\\HOC TAP\\LAP_TRINH_JAVA\\JAVA\\Restaurant_Management\\src\\datas\\products.txt";
    private static final String file_users = "E:\\HOC TAP\\LAP_TRINH_JAVA\\JAVA\\Restaurant_Management\\src\\datas\\users.txt";
    private static final String file_bills = "E:\\HOC TAP\\LAP_TRINH_JAVA\\JAVA\\Restaurant_Management\\src\\datas\\bills.txt";

    public Admin() {
        initComponents();
        
        addDataToTableProduct();
        addDataForComboBox();
        addDataToTableUser();
        addDataToTableCategory();

        initDataJdablogCategory();
        
        this.setLocationRelativeTo(null);
        
        JDialogAddProduct.setSize(500, 600);
        JDialogAddProduct.setLocationRelativeTo(null);
        
        
        JDialogAddCategory.setSize(400, 450);
        JDialogAddCategory.setLocationRelativeTo(null);
        
        
        JdDiablogAddCustomer.setSize(387, 500);
        JdDiablogAddCustomer.setLocationRelativeTo(null);
        
        //For customer
        jUsercode_customer.setEnabled(false);
        jUsernameInCustomer.setEnabled(false);
        JPasswordCustomer.setEnabled(false);
        
        //For product 
        jProductCodeAdmin.setEnabled(false);
        
        //Category
        jCategoryIdAdmin.setEnabled(false);
        
        
    }

    
    public void addDataForComboBox(){
        for (Category category : getData.getDataCategoryFromFile()) {
            category_comboBox.addItem(category);
        }
    }
   public void addDataToTableCategory(){
        data_category_admin.setModel(modelCategory);
        modelCategory.addColumn("Category Id");
        modelCategory.addColumn("Name");
        modelCategory.addColumn("Detail");
        for(Category category : listCategorys){
        modelCategory.addRow(new Object[]{
            category.getCategoryId(),
            category.getName() , 
            category.getDetail()
            });
        }
       
   }
    
    public void addDataToTableProduct(){

        table_product_admin.setModel(modelProduct);
        
        modelProduct.addColumn("Product Id");
        modelProduct.addColumn("Name");
        modelProduct.addColumn("Price");
        modelProduct.addColumn("Quatity");
        modelProduct.addColumn("Category");
        modelProduct.addColumn("Detail");
        for(Product product : listProduct){
        modelProduct.addRow(new Object[]{product.getProductId() ,
            product.getName() ,
            product.getPrice() ,
            product.getQuatity(),
            product.getCategory(),
            product.getDetail()});
        }
    }    
    
    
    public Boolean checkInputProduct(String productCode, String productName , String productPrice ){
       
         if(productCode.equals("")||"".equals(productName) || "".equals(productName) 
                || "".equals(productPrice)){
            JOptionPane.showMessageDialog(this, "Please Fill Complte Information");
            return false;
        }
        for (Product product : listProduct) {
            if(productName.trim().equalsIgnoreCase(product.getName()) ||
                    productCode.trim().equalsIgnoreCase(product.getProductId())){
                    JOptionPane.showMessageDialog(this, "Product id or productname is not duplicate!");
                    return false;
            }
        }
        return true;
    }
     public Boolean checkInputCategory(String categoryId, String categoryName , String categoryDetail  ){
       
         if(categoryId.equals("") ||categoryName.equals("") || categoryDetail.equals("")){
            JOptionPane.showMessageDialog(this, "Please Fill Complte Information");
            return false;
        }
        for (Category category : listCategorys) {
            if(categoryId.trim().equalsIgnoreCase(category.getCategoryId()) ||
                    categoryName.trim().equalsIgnoreCase(category.getName())){
                    JOptionPane.showMessageDialog(this, "Product id or productname is not duplicate!");
                    return false;
            }
        }
        return true;
    }
    public Boolean checkInputUser(String userId ,String username , String password){
 
       for(User user : listUser){
           if(user.getId().equals(userId)){
            JOptionPane.showMessageDialog(this, "UserID can not duplicate!");
            return false;    
           }
           if(user.getUsername().equals(username)){
            JOptionPane.showMessageDialog(this, "Username can not duplicate!");
            return false;   
           }
       }
       if(password.equals("")){
           JOptionPane.showMessageDialog(this, "Password is not null");
           return false;
       }
        return true;
    }
    
    public void addColumnUser(){
        table_customer_admin.setModel(modelUser);
        modelUser.addColumn("User id");
        modelUser.addColumn("Fullname");
        modelUser.addColumn("Username");
        modelUser.addColumn("Gender");
        modelUser.addColumn("Phone");
        modelUser.addColumn("Password");   
     
    }
    
    public int isValueExistsInComboBox(String value) {
        for (int i = 0 ; i < listCategorys.size() ; i++) {
            if(value.equals(listCategorys.get(i).getName())){
                return i;
            }
        }
        return -1;
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        JDialogAddProduct = new javax.swing.JDialog();
        demoDiablog = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        SaveProductToData = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        JDiablogProductPrice = new javax.swing.JTextField();
        JDiablogProductName = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        JDialogProductDetail = new javax.swing.JTextArea();
        JComboboxDiablog = new javax.swing.JComboBox<>();
        JDialogProductId = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        JDiablogProductQuatity = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        JDialogAddCategory = new javax.swing.JDialog();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        JTexCategegoryIdAdmin = new javax.swing.JTextField();
        JTextCategoryNameAdmin = new javax.swing.JTextField();
        JTexCategoryDetailAdmin = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jDialogButtonSaveCategory = new javax.swing.JButton();
        JdDiablogAddCustomer = new javax.swing.JDialog();
        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jFullnameAddCustomer = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jUsercodeAddCustomer = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jPhoneAddcustomer = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jUsernameAddCustomer = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jRadiobuttonChoseMale = new javax.swing.JRadioButton();
        jRadiobuttonChoseFemale = new javax.swing.JRadioButton();
        jPasswordAddcustomer = new javax.swing.JPasswordField();
        jPanel29 = new javax.swing.JPanel();
        buttonSaveAddCustomer = new javax.swing.JButton();
        buttonCancelAddCustomer = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        buttonGroup2 = new javax.swing.ButtonGroup();
        LayoutAdmin = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_revenue_bill = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        totalMoneyInYear = new javax.swing.JTextField();
        input_year = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        ResultData = new javax.swing.JButton();
        labelYear = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jCategoryIdAdmin = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        JbuttonAddCategory = new javax.swing.JButton();
        JButtonUpdateCategory = new javax.swing.JButton();
        JButtonDeleteCategory = new javax.swing.JButton();
        jCategoryNameAdmin = new javax.swing.JTextField();
        jCategoryDetailAdmin = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableCatgoryAdmin = new javax.swing.JScrollPane();
        data_category_admin = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_customer_admin = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        buttonSearchUserByName = new javax.swing.JButton();
        input_name_customer = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jNameInCustomer = new javax.swing.JLabel();
        JLabelphoneInCustomer = new javax.swing.JLabel();
        jGenderUsername = new javax.swing.JLabel();
        jLabelUsernameInCustomer = new javax.swing.JLabel();
        jPasswordInCustomer = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jUsercode_customer = new javax.swing.JTextField();
        jFullnameInCustomer = new javax.swing.JTextField();
        jPhoneInCustomer = new javax.swing.JTextField();
        jUsernameInCustomer = new javax.swing.JTextField();
        jButtonSaveUserByAdmin = new javax.swing.JButton();
        delete_button = new javax.swing.JButton();
        JPasswordCustomer = new javax.swing.JPasswordField();
        jPanel17 = new javax.swing.JPanel();
        jRadioGender1 = new javax.swing.JRadioButton();
        jRadioGender2 = new javax.swing.JRadioButton();
        buttonRefreshInputCustomer = new javax.swing.JButton();
        add_customer_admin = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        searchProductByName = new javax.swing.JButton();
        input_product_search = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboxOptionSort = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        layout = new javax.swing.JPanel();
        jProductCodeAdmin = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jProductName = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jProductPrice = new javax.swing.JTextField();
        category_comboBox = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jProductDetail = new javax.swing.JTextField();
        update_data_product = new javax.swing.JButton();
        deleteRowProduct = new javax.swing.JButton();
        refresh_data_product = new javax.swing.JButton();
        buttonImportData = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jProductQuantity = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        addProduct = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_product_admin = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        JDialogAddProduct.setModal(true);

        demoDiablog.setMinimumSize(new java.awt.Dimension(500, 500));
        demoDiablog.setName(""); // NOI18N

        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        SaveProductToData.setText("Save");
        SaveProductToData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveProductToDataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(SaveProductToData, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(SaveProductToData))
                .addContainerGap())
        );

        jLabel9.setText("Product Id :");

        jLabel10.setText("Product name:");

        jLabel11.setText("Product price :");

        jLabel18.setText("Product category :");

        jLabel27.setText("Product detail :");

        JDialogProductDetail.setColumns(20);
        JDialogProductDetail.setRows(5);
        jScrollPane6.setViewportView(JDialogProductDetail);

        jLabel6.setText("Quatity :");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(JDialogProductId)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(JComboboxDiablog, 0, 190, Short.MAX_VALUE))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel18Layout.createSequentialGroup()
                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(16, 16, 16))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JDiablogProductName)
                                    .addComponent(JDiablogProductPrice)
                                    .addComponent(JDiablogProductQuatity))))
                        .addGap(29, 29, 29))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JDialogProductId, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JDiablogProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JDiablogProductPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JDiablogProductQuatity, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JComboboxDiablog, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel28.setText("ADD PRODUCT");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout demoDiablogLayout = new javax.swing.GroupLayout(demoDiablog);
        demoDiablog.setLayout(demoDiablogLayout);
        demoDiablogLayout.setHorizontalGroup(
            demoDiablogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(demoDiablogLayout.createSequentialGroup()
                .addGroup(demoDiablogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(demoDiablogLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(demoDiablogLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(demoDiablogLayout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        demoDiablogLayout.setVerticalGroup(
            demoDiablogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, demoDiablogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout JDialogAddProductLayout = new javax.swing.GroupLayout(JDialogAddProduct.getContentPane());
        JDialogAddProduct.getContentPane().setLayout(JDialogAddProductLayout);
        JDialogAddProductLayout.setHorizontalGroup(
            JDialogAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JDialogAddProductLayout.createSequentialGroup()
                .addComponent(demoDiablog, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        JDialogAddProductLayout.setVerticalGroup(
            JDialogAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(demoDiablog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel29.setText("ADD CATEGORY");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel30.setText("Categoy Id : ");

        jLabel35.setText("Category name :");

        jLabel36.setText("Category detail:");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                    .addComponent(JTexCategegoryIdAdmin)
                    .addComponent(JTextCategoryNameAdmin)
                    .addComponent(JTexCategoryDetailAdmin)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTexCategegoryIdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTextCategoryNameAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTexCategoryDetailAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jButton6.setText("Cancel");

        jDialogButtonSaveCategory.setText("Save");
        jDialogButtonSaveCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDialogButtonSaveCategoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jDialogButtonSaveCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jDialogButtonSaveCategory)
                    .addComponent(jButton6))
                .addGap(0, 25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout JDialogAddCategoryLayout = new javax.swing.GroupLayout(JDialogAddCategory.getContentPane());
        JDialogAddCategory.getContentPane().setLayout(JDialogAddCategoryLayout);
        JDialogAddCategoryLayout.setHorizontalGroup(
            JDialogAddCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        JDialogAddCategoryLayout.setVerticalGroup(
            JDialogAddCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 135, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        jLabel38.setText("User code :");

        jLabel39.setText("Full name :");

        jLabel40.setText("Phone :");

        jPhoneAddcustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPhoneAddcustomerActionPerformed(evt);
            }
        });

        jLabel41.setText("Gender :");

        jUsernameAddCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUsernameAddCustomerActionPerformed(evt);
            }
        });

        jLabel42.setText("Username :");

        jLabel43.setText("Password :");

        buttonGroup2.add(jRadiobuttonChoseMale);
        jRadiobuttonChoseMale.setText("Male");
        jRadiobuttonChoseMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadiobuttonChoseMaleActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadiobuttonChoseFemale);
        jRadiobuttonChoseFemale.setText("Female");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                        .addComponent(jRadiobuttonChoseMale, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadiobuttonChoseFemale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jFullnameAddCustomer)
                    .addComponent(jUsercodeAddCustomer)
                    .addComponent(jPhoneAddcustomer)
                    .addComponent(jPasswordAddcustomer)
                    .addComponent(jUsernameAddCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jUsercodeAddCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jFullnameAddCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jPhoneAddcustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jRadiobuttonChoseMale)
                    .addComponent(jRadiobuttonChoseFemale))
                .addGap(25, 25, 25)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(jUsernameAddCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jPasswordAddcustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        buttonSaveAddCustomer.setText("Save");
        buttonSaveAddCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveAddCustomerActionPerformed(evt);
            }
        });

        buttonCancelAddCustomer.setText("Cancel");
        buttonCancelAddCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelAddCustomerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(buttonCancelAddCustomer)
                .addGap(54, 54, 54)
                .addComponent(buttonSaveAddCustomer)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSaveAddCustomer)
                    .addComponent(buttonCancelAddCustomer))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel37.setText("ADD CUSTOMER");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(170, 170, 170))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120))))
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout JdDiablogAddCustomerLayout = new javax.swing.GroupLayout(JdDiablogAddCustomer.getContentPane());
        JdDiablogAddCustomer.getContentPane().setLayout(JdDiablogAddCustomerLayout);
        JdDiablogAddCustomerLayout.setHorizontalGroup(
            JdDiablogAddCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        JdDiablogAddCustomerLayout.setVerticalGroup(
            JdDiablogAddCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel31.setText("jLabel31");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LayoutAdmin.setBackground(new java.awt.Color(255, 255, 255));
        LayoutAdmin.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        LayoutAdmin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        LayoutAdmin.setName(""); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        table_revenue_bill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Month", "Total amount"
            }
        ));
        jScrollPane1.setViewportView(table_revenue_bill);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Revenue money each month in");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Income");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(totalMoneyInYear, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(totalMoneyInYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        input_year.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                input_yearActionPerformed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel45.setText("Year :");

        ResultData.setText("Search");
        ResultData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResultDataActionPerformed(evt);
            }
        });

        labelYear.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jButton4.setText("Logout");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(314, 314, 314)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(320, 320, 320)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(input_year, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(ResultData, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelYear, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 891, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(122, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(18, 18, 18))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelYear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(input_year, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(ResultData, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        LayoutAdmin.addTab("INCOME", jPanel3);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel32.setText("Category id");

        jCategoryIdAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCategoryIdAdminActionPerformed(evt);
            }
        });

        jLabel33.setText("Category name");

        jLabel34.setText("Category detail");

        JbuttonAddCategory.setText("Add");
        JbuttonAddCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbuttonAddCategoryActionPerformed(evt);
            }
        });

        JButtonUpdateCategory.setText("Update");
        JButtonUpdateCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonUpdateCategoryActionPerformed(evt);
            }
        });

        JButtonDeleteCategory.setText("Delete");
        JButtonDeleteCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonDeleteCategoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(JbuttonAddCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(JButtonUpdateCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(78, 78, 78))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jCategoryIdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCategoryNameAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCategoryDetailAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(JButtonDeleteCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCategoryIdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel34))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCategoryNameAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                            .addComponent(jCategoryDetailAdmin))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JbuttonAddCategory)
                    .addComponent(JButtonUpdateCategory)
                    .addComponent(JButtonDeleteCategory))
                .addGap(15, 15, 15))
        );

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel16.setText("MANAGE CATEGORY");

        jTableCatgoryAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCatgoryAdminMouseClicked(evt);
            }
        });

        data_category_admin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Category Id", "Category name", "Category detail"
            }
        ));
        data_category_admin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                data_category_adminMouseClicked(evt);
            }
        });
        jTableCatgoryAdmin.setViewportView(data_category_admin);

        jScrollPane7.setViewportView(jTableCatgoryAdmin);

        jButton7.setText("Logout");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(356, 356, 356)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(178, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(14, 14, 14))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jButton7)
                .addGap(18, 18, 18)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        LayoutAdmin.addTab("CATEGORY", jPanel11);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane4MouseClicked(evt);
            }
        });

        table_customer_admin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "User code", "Fullname", "Username", "Gender", "Phone", "Password"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table_customer_admin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_customer_adminMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(table_customer_admin);

        jScrollPane4.setViewportView(jScrollPane3);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        buttonSearchUserByName.setText("Search");
        buttonSearchUserByName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchUserByNameActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setText("Search by name :");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(input_name_customer, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(buttonSearchUserByName)
                .addContainerGap(93, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(input_name_customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSearchUserByName))
                .addContainerGap())
        );

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setText("Modify");

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel26.setText("User code");

        jNameInCustomer.setText("Name");

        JLabelphoneInCustomer.setText("Phone");

        jGenderUsername.setText("Gender");

        jLabelUsernameInCustomer.setText("Username");

        jPasswordInCustomer.setText("Password");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 57, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jNameInCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(108, 108, 108)
                        .addComponent(JLabelphoneInCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102)
                        .addComponent(jGenderUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelUsernameInCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(jPasswordInCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLabelphoneInCustomer)
                    .addComponent(jNameInCustomer)
                    .addComponent(jLabel26)
                    .addComponent(jGenderUsername)
                    .addComponent(jLabelUsernameInCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPasswordInCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPhoneInCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPhoneInCustomerActionPerformed(evt);
            }
        });

        jUsernameInCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUsernameInCustomerActionPerformed(evt);
            }
        });

        jButtonSaveUserByAdmin.setText("Update");
        jButtonSaveUserByAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveUserByAdminActionPerformed(evt);
            }
        });

        delete_button.setText("Delete");
        delete_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_buttonActionPerformed(evt);
            }
        });

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        jRadioGender1.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioGender1);
        jRadioGender1.setText("Male");

        jRadioGender2.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioGender2);
        jRadioGender2.setText("Female");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioGender1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jRadioGender2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioGender1)
                    .addComponent(jRadioGender2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonRefreshInputCustomer.setText("Refresh");
        buttonRefreshInputCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRefreshInputCustomerActionPerformed(evt);
            }
        });

        add_customer_admin.setText("Add");
        add_customer_admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_customer_adminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jUsercode_customer, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jFullnameInCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jPhoneInCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(jUsernameInCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addComponent(JPasswordCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addComponent(add_customer_admin, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(jButtonSaveUserByAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(delete_button, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(buttonRefreshInputCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jUsercode_customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jFullnameInCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPhoneInCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jUsernameInCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JPasswordCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(delete_button)
                    .addComponent(buttonRefreshInputCustomer)
                    .addComponent(jButtonSaveUserByAdmin)
                    .addComponent(add_customer_admin))
                .addGap(52, 66, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButton5.setText("Logout");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel21.setText("MANAGE CUSTOMER");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(131, 131, 131)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(336, 336, 336)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5)
                .addGap(16, 16, 16)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        LayoutAdmin.addTab("CUSTOMER", jPanel2);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        searchProductByName.setText("Search");
        searchProductByName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchProductByNameActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Search by name : ");

        jComboxOptionSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default", "Name ASC", "Name DESC", "Price ASC", "Price DESC", "Quatity ASC", "Quatity DESC" }));
        jComboxOptionSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboxOptionSortActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Option sort :");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(106, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(input_product_search, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchProductByName)
                .addGap(131, 131, 131)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboxOptionSort, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(input_product_search, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchProductByName)
                    .addComponent(jLabel7)
                    .addComponent(jComboxOptionSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        layout.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setText("Product code");

        jLabel13.setText("Product Name");

        jLabel14.setText("Product price");

        category_comboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                category_comboBoxActionPerformed(evt);
            }
        });

        jLabel15.setText("Category type");

        jLabel17.setText("Product detail");

        update_data_product.setText("Update ");
        update_data_product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_data_productActionPerformed(evt);
            }
        });

        deleteRowProduct.setText("Delete ");
        deleteRowProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRowProductActionPerformed(evt);
            }
        });

        refresh_data_product.setText("Refresh");
        refresh_data_product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refresh_data_productActionPerformed(evt);
            }
        });

        buttonImportData.setText("Import ");
        buttonImportData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonImportDataActionPerformed(evt);
            }
        });

        jButton2.setText("Export");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setText("Quantity");

        addProduct.setText("Add");
        addProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layoutLayout = new javax.swing.GroupLayout(layout);
        layout.setLayout(layoutLayout);
        layoutLayout.setHorizontalGroup(
            layoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layoutLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layoutLayout.createSequentialGroup()
                        .addGroup(layoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jProductCodeAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(layoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jProductPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layoutLayout.createSequentialGroup()
                        .addComponent(buttonImportData, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)))
                .addGroup(layoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layoutLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(layoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jProductQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(layoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(category_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layoutLayout.createSequentialGroup()
                        .addComponent(addProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(update_data_product, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)))
                .addGroup(layoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layoutLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(layoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jProductDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layoutLayout.createSequentialGroup()
                        .addComponent(deleteRowProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(refresh_data_product, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111))))
        );
        layoutLayout.setVerticalGroup(
            layoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layoutLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel17)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProductPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProductCodeAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(category_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProductDetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProductQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(update_data_product)
                    .addComponent(deleteRowProduct)
                    .addComponent(refresh_data_product)
                    .addComponent(buttonImportData)
                    .addComponent(jButton2)
                    .addComponent(addProduct))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        table_product_admin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Product code", "Name", "Price", "Quantity", "Category type", "Detail"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Float.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table_product_admin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_product_adminMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_product_admin);

        jScrollPane5.setViewportView(jScrollPane2);

        jLabel4.setText("jLabel4");

        jButton3.setText("Logout");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setText("MANAGE PRODUCT");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 1009, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(layout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane5)))
                        .addGap(0, 36, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(335, 335, 335)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(layout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        LayoutAdmin.addTab("PRODUCT", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LayoutAdmin)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(LayoutAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 59, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void category_comboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_category_comboBoxActionPerformed
        
    }//GEN-LAST:event_category_comboBoxActionPerformed

    private void update_data_productActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_data_productActionPerformed
      DefaultTableModel updateTable = (DefaultTableModel) table_product_admin.getModel();
      int rows = table_product_admin.getSelectedRow();
      
      if(table_product_admin.getSelectedRowCount() == 1){
          
          String product_name = jProductName.getText() != null ? jProductName.getText() : "null";
          String product_price = jProductPrice.getText() != null ? jProductPrice.getText() : "0";
          String produtct_quantity = jProductQuantity.getText() != null ? jProductQuantity.getText() : "0";
          String product_detail = jProductDetail.getText() != null? jProductDetail.getText() : "null";
          String category = category_comboBox.getSelectedItem().toString();
        
          if("".equals(product_name) || "".equals(product_price) 
                  || "".equals(product_detail)){
              JOptionPane.showMessageDialog(this, "Please fill data!");
          }else{
              updateTable.setValueAt(product_name, rows, 1);
              updateTable.setValueAt(Float.valueOf(product_price), rows, 2);
              updateTable.setValueAt(Integer.valueOf(produtct_quantity), rows, 3);
              updateTable.setValueAt(category, rows, 4);
              updateTable.setValueAt(product_detail, rows, 5);
              
              for(Product product : listProduct){
                  if(product.getProductId().equals(updateTable.getValueAt(rows, 0))){
                      Product item = new Product(
                      product.getProductId() ,
                      product_name,
                      Float.parseFloat(product_price) ,
                      Integer.parseInt(produtct_quantity),
                      category ,product_detail);
                      listProduct.set(rows, item);
                      break;
                  }
              }
              manageFileProduct.updateProductToFile(listProduct, file_products);
              JOptionPane.showMessageDialog(this, "Update sucessful!");
          }
      }else{
            if (table_product_admin.getRowCount() == 0) {
              JOptionPane.showMessageDialog(this, "List is empty!");
          }
         }
    }//GEN-LAST:event_update_data_productActionPerformed

    
    public void clearTextFiled(){
        jProductCodeAdmin.setText("");
        jProductName.setText("");
        jProductDetail.setText("");
        jProductPrice.setText("");
         jProductQuantity.setText("");
        category_comboBox.setSelectedItem(category_comboBox.getItemAt(0));
    }
    private void addProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductActionPerformed
       JDialogAddProduct.setVisible(true);
       JDialogAddProduct.setLocationRelativeTo(null);
    }//GEN-LAST:event_addProductActionPerformed

    private void deleteRowProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteRowProductActionPerformed


    DefaultTableModel defaultTableModel = (DefaultTableModel) table_product_admin.getModel();
    int choise = JOptionPane.showConfirmDialog(this, "Do you want to delete this product" , "Confirm" , 
                JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
        if(table_product_admin.getSelectedRowCount()  == 1 && choise == JOptionPane.YES_OPTION){
        int index = table_product_admin.getSelectedRow();
        defaultTableModel.removeRow(index);
        listProduct.remove(index);
        manageFileProduct.updateProductToFile(listProduct, file_products);
        clearTextFiled();
    }else{
    if(table_product_admin.getRowCount() == 0){
        JOptionPane.showMessageDialog(this, "Table is empty!");
            }
        }
    }//GEN-LAST:event_deleteRowProductActionPerformed
        
    
    private void table_product_adminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_product_adminMouseClicked
        int rows = table_product_admin.getSelectedRow();
        DefaultTableModel dataModel = (DefaultTableModel)table_product_admin.getModel();
 
        jProductCodeAdmin.setText(dataModel.getValueAt(rows, 0).toString());
      
        jProductName.setText(dataModel.getValueAt(rows, 1).toString());
        jProductPrice.setText(dataModel.getValueAt(rows, 2).toString());
        jProductQuantity.setText(dataModel.getValueAt(rows, 3).toString());
        String category = String.valueOf(dataModel.getValueAt(rows, 4));
        jProductDetail.setText(dataModel.getValueAt(rows, 5).toString());

        int indexComboBox = isValueExistsInComboBox(category);
        System.out.println("index combobox : " + indexComboBox);
            if(indexComboBox != -1){
                System.out.println(category_comboBox.getItemAt(indexComboBox));
                category_comboBox.setSelectedItem(category_comboBox.getItemAt(indexComboBox));
            } 
    }//GEN-LAST:event_table_product_adminMouseClicked

    private void jCategoryIdAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCategoryIdAdminActionPerformed
       
        
    }//GEN-LAST:event_jCategoryIdAdminActionPerformed

    private void JbuttonAddCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbuttonAddCategoryActionPerformed
        JDialogAddCategory.setVisible(true);
        
    }//GEN-LAST:event_JbuttonAddCategoryActionPerformed

    private void jUsernameInCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUsernameInCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jUsernameInCustomerActionPerformed

    private void jPhoneInCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPhoneInCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPhoneInCustomerActionPerformed

    private void add_customer_adminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_customer_adminActionPerformed

        JdDiablogAddCustomer.setVisible(true);
    }//GEN-LAST:event_add_customer_adminActionPerformed

    private void delete_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_buttonActionPerformed
    DefaultTableModel defaultTableModel = (DefaultTableModel) table_customer_admin.getModel();
    int choise = JOptionPane.showConfirmDialog(this, "Do you want to delete this customer" , "Confirm" , 
                JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
        if(table_customer_admin.getSelectedRowCount()  == 1 && choise == JOptionPane.YES_OPTION){
        int index = table_customer_admin.getSelectedRow();
        defaultTableModel.removeRow(index);
        listUser.remove(index);
        manageFileUser.updateUserToFile(listUser, file_users);
        clearTextFiled();
    }else{
    if(table_customer_admin.getRowCount() == 0){
        JOptionPane.showMessageDialog(this, "Table is empty!");
            }
        }
    }//GEN-LAST:event_delete_buttonActionPerformed

    private void jScrollPane4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane4MouseClicked
       int rows = table_product_admin.getSelectedRow();
        DefaultTableModel dataModel = (DefaultTableModel)table_customer_admin.getModel();
 
        jUsercode_customer.setText(dataModel.getValueAt(rows, 0).toString());
        //j.setText(dataModel.getValueAt(rows, 1).toString());
        String category = String.valueOf(dataModel.getValueAt(rows, 2));
        System.out.println("Category" + category);
        jProductPrice.setText(dataModel.getValueAt(rows, 3).toString());
        jProductDetail.setText(dataModel.getValueAt(rows, 4).toString());

        int indexComboBox = isValueExistsInComboBox(category);
        System.out.println("index combobox : " + indexComboBox);
            if(indexComboBox != -1){
                System.out.println(category_comboBox.getItemAt(indexComboBox));
                category_comboBox.setSelectedItem(category_comboBox.getItemAt(indexComboBox));
            } 
    }//GEN-LAST:event_jScrollPane4MouseClicked

    
    //Stastic money each day in month 
    
    
    //Stastic money each month in year 
    
    
    
    private void searchProductByNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchProductByNameActionPerformed
       
        String inputInSearch = input_product_search.getText();
        if(inputInSearch.equals("")){
            JOptionPane.showMessageDialog(this, "You need enter key word want to search!");
        }else{
            List<Product> productSearch = new ArrayList<>();
            for (Product item : listProduct) {
                if(item.getName().contains(inputInSearch)){
                    productSearch.add(item);
                }
            }
            if(productSearch.isEmpty()){
                JOptionPane.showMessageDialog(this, "Don't find product has name : "+input_product_search.getText());
            }else{
            DefaultTableModel defaultModel = new DefaultTableModel();
            table_product_admin.setModel(defaultModel);
            defaultModel.addColumn("Product Id");
            defaultModel.addColumn("Name");
            defaultModel.addColumn("Price");
            defaultModel.addColumn("Quatity");
            defaultModel.addColumn("Category");
            defaultModel.addColumn("Detail");
   
            for(Product item : productSearch){     
                defaultModel.addRow(new Object[]{
                item.getProductId(),
                item.getName(),
                item.getPrice(),
                item.getQuatity(),
                item.getCategory(),
                item.getDetail()});
                }
            }
        }
    }//GEN-LAST:event_searchProductByNameActionPerformed

    private void refresh_data_productActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refresh_data_productActionPerformed
        jProductCodeAdmin.setText("");
        jProductName.setText("");
        jProductPrice.setText("");
        jProductDetail.setText("");
        jProductQuantity.setText("");
        input_product_search.setText("");
        category_comboBox.getItemAt(0);
        
       table_product_admin.setModel(modelProduct);

    }//GEN-LAST:event_refresh_data_productActionPerformed

    private void jTableCatgoryAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCatgoryAdminMouseClicked
//       int rows = data_category_admin.getSelectedRow();
//        DefaultTableModel dataModel = (DefaultTableModel)data_category_admin.getModel();
//        System.out.println(dataModel.getValueAt(rows, 0).toString());
//        jCategoryIdAdmin.setText(dataModel.getValueAt(rows, 0).toString());
//        jCategoryNameAdmin.setText(dataModel.getValueAt(rows, 1).toString());
//        jCategoryNameAdmin.setText(dataModel.getValueAt(rows, 2).toString());
    }//GEN-LAST:event_jTableCatgoryAdminMouseClicked

   private void initDataJdablogCategory(){
        for (Category category : getData.getDataCategoryFromFile()) {
            JComboboxDiablog.addItem(category.getName());
            System.out.println(category.getCategoryId());
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       JDialogAddProduct.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    
    //Init data for diablog category
    
    
    private void SaveProductToDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveProductToDataActionPerformed
            
                Object[] product = new Object[6];
                product[0] = JDialogProductId.getText();
                product[1] = JDiablogProductName.getText();
                product[2] = JDiablogProductPrice.getText();
                product[3] = JDiablogProductQuatity.getText() != null ? JDiablogProductQuatity.getText() : "0";
                product[4] = JComboboxDiablog.getSelectedItem()!=null ? JComboboxDiablog.getSelectedItem() : JComboboxDiablog.getItemAt(0);
                product[5] = JDialogProductDetail.getText()!=null ? JDialogProductDetail.getText() : "null";
                if(checkInputProduct(product[0].toString() , product[1].toString() , 
                        product[2].toString())){
                modelProduct.addRow(product);

                Product item = new Product(product[0].toString(),
                                     product[1].toString(),
                                     Float.parseFloat(product[2].toString()),
                                     Integer.parseInt(product[3].toString()),
                                     product[4].toString(),
                                     product[5].toString());
                        manageFileProduct.writeProductToFile(file_products, item);
                        JOptionPane.showMessageDialog(this, "Add product successfull");
                        JDialogAddProduct.dispose();
                }else{
                    return;
                }
        
    }//GEN-LAST:event_SaveProductToDataActionPerformed

    private void data_category_adminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_data_category_adminMouseClicked
        int rows = data_category_admin.getSelectedRow();
        DefaultTableModel dataModel = (DefaultTableModel)data_category_admin.getModel();
        System.out.println(dataModel.getValueAt(rows, 0).toString());
        jCategoryIdAdmin.setText(dataModel.getValueAt(rows, 0).toString());
        jCategoryNameAdmin.setText(dataModel.getValueAt(rows, 1).toString());
        jCategoryDetailAdmin.setText(dataModel.getValueAt(rows, 2).toString());
    }//GEN-LAST:event_data_category_adminMouseClicked

    private void jDialogButtonSaveCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDialogButtonSaveCategoryActionPerformed
        Object[] category = new Object[3];
                category[0] = JTexCategegoryIdAdmin.getText();
                category[1] = JTextCategoryNameAdmin.getText()!=null ? JTextCategoryNameAdmin.getText() : "null";
                category[2] = JTexCategoryDetailAdmin.getText()!=null ? JTexCategoryDetailAdmin.getText() : "null";
                if(checkInputCategory(category[0].toString() , category[1].toString() , 
                        category[2].toString())){
                modelCategory.addRow(category);
                Category item = new Category(category[0].toString(),
                                    category[1].toString(),
                                     category[2].toString());
                manageFileCategory.writeCategorytToFile(file_categories, item);
                        
                        JOptionPane.showMessageDialog(this, "Add category successfull");
                        JTexCategegoryIdAdmin.setText("");
                        JTextCategoryNameAdmin.setText("");
                        JTexCategoryDetailAdmin.setText("");
                        JDialogAddCategory.dispose();
                }else{
                    return;
                }
    }//GEN-LAST:event_jDialogButtonSaveCategoryActionPerformed

    private void JButtonDeleteCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonDeleteCategoryActionPerformed
                                                
    DefaultTableModel defaultTableModel = (DefaultTableModel) data_category_admin.getModel();
    int choise = JOptionPane.showConfirmDialog(this, "Do you want to delete this category" , "Confirm" , 
                JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
        if(data_category_admin.getSelectedRowCount()  == 1 && choise == JOptionPane.YES_OPTION){
        int index = data_category_admin.getSelectedRow();
        defaultTableModel.removeRow(index);
        listCategorys.remove(index);
        manageFileCategory.updateCategoryToFile(listCategorys, file_categories);
        clearTextFiled();
        
         JOptionPane.showMessageDialog(this, "Delete categogry success!");
         jCategoryIdAdmin.setText("");
         jCategoryNameAdmin.setText("");
         jCategoryDetailAdmin.setText("");
    }else{
    if(data_category_admin.getRowCount() == 0){
        JOptionPane.showMessageDialog(this, "Table is empty!");
            }
        }
    }//GEN-LAST:event_JButtonDeleteCategoryActionPerformed

    private void JButtonUpdateCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonUpdateCategoryActionPerformed
       DefaultTableModel updateTable = (DefaultTableModel) data_category_admin.getModel();
      int rows = data_category_admin.getSelectedRow();
      
      if(data_category_admin.getSelectedRowCount() == 1){
          String categoryName = jCategoryNameAdmin.getText() ;
          String categoryDetail = jCategoryDetailAdmin.getText() != null ? jCategoryDetailAdmin.getText() : "null";

          if(jCategoryNameAdmin.getText().equals(updateTable.getValueAt(rows, 1))){
               Category category = new Category(
                      updateTable.getValueAt(rows, 0).toString() ,
                      categoryName,categoryDetail);
                      updateTable.setValueAt(categoryName, rows, 1);
                      updateTable.setValueAt(categoryDetail, rows, 2);
                      listCategorys.set(rows, category);
                      manageFileCategory.updateCategoryToFile(listCategorys, file_categories);
                      JOptionPane.showMessageDialog(this, "Update sucessful!");
                      return;
                    }
              for(Category item : listCategorys){
                  if(item.getName().equals(jCategoryNameAdmin.getText()) &&
                    jCategoryIdAdmin.getText().equals(updateTable.getValueAt(rows, 0))){
                    JOptionPane.showMessageDialog(this, "This name is duplicate!");
                    return;
                  }
                   System.out.println(!jCategoryNameAdmin.getText().equals(updateTable.getValueAt(rows,1)));
                  if(item.getCategoryId().equals(updateTable.getValueAt(rows, 0))){
                      Category category = new Category(
                      updateTable.getValueAt(rows, 0).toString() ,
                      categoryName,categoryDetail);
                      updateTable.setValueAt(categoryName, rows, 1);
                      updateTable.setValueAt(categoryDetail, rows, 2);
                      listCategorys.set(rows, category);
                      break;
                  }
              }
               manageFileCategory.updateCategoryToFile(listCategorys, file_categories);
              JOptionPane.showMessageDialog(this, "Update sucessful!");
      }else{
            if (data_category_admin.getRowCount() == 0) {
              JOptionPane.showMessageDialog(this, "List category is empty!");
          }
         }          
    }//GEN-LAST:event_JButtonUpdateCategoryActionPerformed

    private void jButtonSaveUserByAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveUserByAdminActionPerformed
      DefaultTableModel updateTable = (DefaultTableModel) table_customer_admin.getModel();
      int rows = table_customer_admin.getSelectedRow();
      
      if(table_customer_admin.getSelectedRowCount() == 1){     
          String fullName = jFullnameInCustomer.getText() != null ? jFullnameInCustomer.getText() : "null";
          String phone = jPhoneInCustomer.getText() != null ? jPhoneInCustomer.getText() : "null";
          String gender;
          if(jRadioGender1.isSelected()){
               gender = "Male";
          }else{
              gender = "Female";
          }       
          updateTable.setValueAt(fullName, rows, 1);
          updateTable.setValueAt(phone, rows, 2);
          updateTable.setValueAt(gender, rows, 3);
          for(int i = 0 ; i < listUser.size() ; i++){
              if(listUser.get(i).getId().equals(modelUser.getValueAt(rows, 0))){
                  User user = new User(
                          modelUser.getValueAt(rows, 0).toString() , 
                          fullName, phone , gender , modelUser.getValueAt(rows, 4).toString(),
                          modelUser.getValueAt(rows, 5).toString() , "ROLE_USER");
                  listUser.set(i, user);
              }
          }

          manageFileUser.updateUserToFile(listUser, file_users);
          JOptionPane.showMessageDialog(this, "Update infouser sucessful!");
      }else{
            if (table_product_admin.getRowCount() == 0) {
              JOptionPane.showMessageDialog(this, "List user is empty!");
          }
         }
    }//GEN-LAST:event_jButtonSaveUserByAdminActionPerformed

    private void table_customer_adminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_customer_adminMouseClicked
         int rows = table_customer_admin.getSelectedRow();
        DefaultTableModel dataModel = (DefaultTableModel)table_customer_admin.getModel();
 
        jUsercode_customer.setText(dataModel.getValueAt(rows, 0).toString());
        jFullnameInCustomer.setText(dataModel.getValueAt(rows, 1).toString());
        jPhoneInCustomer.setText(dataModel.getValueAt(rows, 2).toString());
        String gender = dataModel.getValueAt( rows , 3).toString();
        if(gender.equals("Male")){
            jRadioGender1.setSelected(true);
        }else{
            jRadioGender2.setSelected(true);
        }
        jUsernameInCustomer.setText(dataModel.getValueAt(rows, 4).toString());
        JPasswordCustomer.setText(dataModel.getValueAt(rows, 5).toString());
        
    }//GEN-LAST:event_table_customer_adminMouseClicked

    private void jPhoneAddcustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPhoneAddcustomerActionPerformed
            
    }//GEN-LAST:event_jPhoneAddcustomerActionPerformed

    private void jUsernameAddCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUsernameAddCustomerActionPerformed
        
    }//GEN-LAST:event_jUsernameAddCustomerActionPerformed

    private void jRadiobuttonChoseMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadiobuttonChoseMaleActionPerformed
        
    }//GEN-LAST:event_jRadiobuttonChoseMaleActionPerformed

    private void buttonCancelAddCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelAddCustomerActionPerformed
        JdDiablogAddCustomer.setVisible(false);
    }//GEN-LAST:event_buttonCancelAddCustomerActionPerformed

    private void buttonSaveAddCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveAddCustomerActionPerformed
        Object[] user = new Object[6];
                user[0] = jUsercodeAddCustomer.getText();
                user[1] = !"".equals(jFullnameAddCustomer.getText()) ? jFullnameAddCustomer.getText() : "null";
                user[2] = !"".equals(jPhoneAddcustomer.getText()) ? jPhoneAddcustomer.getText() : "null";
                if(jRadiobuttonChoseMale.isSelected() == true){
                      user[3] = "Male";
                }else if(jRadiobuttonChoseFemale.isSelected() == true){
                    user[3] = "Female";
                }else{
                      user[3] = "Other";
                 }
                user[4] = jUsernameAddCustomer.getText();
                char[] characterPass = jPasswordAddcustomer.getPassword();
                String password = new  String(characterPass);
                user[5] = password; 
                if(checkInputUser(jUsercodeAddCustomer.getText() , jUsernameAddCustomer.getText(), password)){    
                modelUser.addRow(user);
                User newUser = new User(user[0].toString() , user[1].toString() , user[2].toString() , 
                                        user[3].toString() , user[4].toString() , user[5].toString(), "ROLE_USER");
                
                manageFileUser.writeUserToFile(file_users, newUser);        
                JOptionPane.showMessageDialog(this, "Create new customer successfull");
                JdDiablogAddCustomer.setVisible(false);
                }else{
                    return;
             }
    }//GEN-LAST:event_buttonSaveAddCustomerActionPerformed

    
    
    private void buttonSearchUserByNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchUserByNameActionPerformed
         String inputInSearch = input_name_customer.getText();
        if(inputInSearch.equals("")){
            JOptionPane.showMessageDialog(this, "You need enter key word want to search!");
        }else{
            List<User> userSearch = new ArrayList<>();
            for (User item : listUser) {
                if(item.getFullname().contains(inputInSearch) && item.getRole().equals("ROLE_USER")){
                    userSearch.add(item);
                }
            }
            if(userSearch.isEmpty()){
                JOptionPane.showMessageDialog(this, "Don't find customer contains name : "+input_name_customer.getText());
               return;
            }
            DefaultTableModel defaultModel = new DefaultTableModel();
            table_customer_admin.setModel(defaultModel);
            defaultModel.addColumn("User Id");
            defaultModel.addColumn("FullName");
            defaultModel.addColumn("Phone");
            defaultModel.addColumn("Gender");
            defaultModel.addColumn("Username");
            defaultModel.addColumn("Password");
            for(User item : userSearch){     
            defaultModel.addRow(new Object[]{
                item.getId(),
                item.getFullname(),item.getPhone(),
                item.getGender(), item.getUsername(),
                item.getPassword()});
            }
        }
        
    }//GEN-LAST:event_buttonSearchUserByNameActionPerformed

    private void buttonRefreshInputCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRefreshInputCustomerActionPerformed
       jUsercode_customer.setText("");
       jFullnameInCustomer.setText("");
       JLabelphoneInCustomer.setText("");
       jRadioGender1.setSelected(true);
       jUsernameInCustomer.setText("");
       JPasswordCustomer.setText("");
       
       table_customer_admin.setModel(modelUser);
    }//GEN-LAST:event_buttonRefreshInputCustomerActionPerformed

    private void ResultDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResultDataActionPerformed
              
        DefaultTableModel modelBill = new DefaultTableModel();
            table_revenue_bill.setModel(modelBill);
        modelBill.addColumn("Month");
        modelBill.addColumn("Total money");
        
        int inputYear = Integer.valueOf(input_year.getText());
       
        float totalMoneyInYear = 0;
        if(input_year.getText() != null || inputYear > 2000 && inputYear < 2024){

         Map<Integer , Float> statisticRevenue = new TreeMap<>();
        for(int i = 0 ; i < listBills.size() ; i++){     
            String dateTimeString = listBills.get(i).getCreatedBuy();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime parsedDateTime = LocalDateTime.parse(dateTimeString, formatter);
            int year =  parsedDateTime.getYear();
            int month = parsedDateTime.getMonthValue();
            float totalMoneyInMonth = 0;
            if(inputYear == year){
                 for(int j = 0 ; j < listBills.size() ; j++){
                     dateTimeString = listBills.get(j).getCreatedBuy();
                     formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                     parsedDateTime = LocalDateTime.parse(dateTimeString, formatter);
                    int month2 =  parsedDateTime.getMonthValue();
                    if(month == month2){
                        totalMoneyInMonth+= listBills.get(j).getTotal();
                    }
                }    
                statisticRevenue.put( month ,totalMoneyInMonth);
            }
        }
  
        for(Map.Entry<Integer , Float> entry : statisticRevenue.entrySet()){
        int month = entry.getKey();
        float totalMoney = entry.getValue();
        modelBill.addRow(new Object[]{month , String.valueOf(totalMoney + " VND")});
          totalMoneyInYear+= totalMoney;      
        } 
        labelYear.setText(input_year.getText());
        this.totalMoneyInYear.setText(String.valueOf( totalMoneyInYear + " VND"));
        }else{
            JOptionPane.showMessageDialog(this, "Year is not valid");
        } 
    }//GEN-LAST:event_ResultDataActionPerformed

    private void input_yearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_input_yearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_input_yearActionPerformed

    private void buttonImportDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonImportDataActionPerformed
        
        JFileChooser excelFileChooser = new JFileChooser();
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES",
                "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);
        excelFileChooser.setMultiSelectionEnabled(false);
        
        int excelChooser = excelFileChooser.showDialog(this, "Choose file");
        if(excelChooser == excelFileChooser.APPROVE_OPTION){

        File excelFile;
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelImportToJTable = null;
        excelFileChooser.setDialogTitle("Select Excel File");
        excelFileChooser.setFileFilter(fnef);
            try {
                excelFile = excelFileChooser.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);                  
                excelBIS = new BufferedInputStream(excelFIS);                  
                excelImportToJTable = new XSSFWorkbook(excelBIS);
                
                XSSFSheet excelSheaetData = excelImportToJTable.getSheetAt(0);
                for (int row = 0; row < excelSheaetData.getLastRowNum(); row++) {
                    XSSFRow excelRow = excelSheaetData.getRow(row); 
                    
                    XSSFCell excelProductId = excelRow.getCell(0);
                    XSSFCell excelProductName = excelRow.getCell(1);
                    XSSFCell excelPrice = excelRow.getCell(2);
                    XSSFCell excelQuatity = excelRow.getCell(3);
                    XSSFCell excelCategory = excelRow.getCell(4);
                    XSSFCell excelDescription = excelRow.getCell(5);                 
                    
                    modelProduct.addRow(new Object[]{excelProductId, excelProductName,
                         excelPrice, excelQuatity , excelCategory , excelDescription});
                    String quantityStr = excelQuatity.toString();
                    double quantityDb = Double.parseDouble(quantityStr);
                    int integerPart = (int) quantityDb;
                    manageFileProduct.writeProductToFile(file_products ,
                            new Product(
                            excelProductId.toString(),
                            excelProductName.toString(),
                            Float.parseFloat(excelPrice.toString()),         
                            integerPart,
                            excelCategory.toString(),
                            excelDescription.toString()));  
                }
                JOptionPane.showMessageDialog(this, "Imported Successfully!");
            } catch (IOException iOException) {
                JOptionPane.showMessageDialog(this, iOException.getMessage());
            } finally {
                try {
                    if (excelFIS != null) {
                        excelFIS.close();
                    }
                    if (excelBIS != null) {
                        excelBIS.close();
                    }
                    if (excelImportToJTable != null) {
                        excelImportToJTable.close();
                    }
                } catch (IOException iOException) {
                    JOptionPane.showMessageDialog(null, iOException.getMessage());
                }
            }
        }
    }//GEN-LAST:event_buttonImportDataActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
           
           JFileChooser jFileChooser = new JFileChooser();
           jFileChooser.showSaveDialog(this);
           File saveFile = jFileChooser.getSelectedFile();
           
           if(saveFile != null){
               saveFile = new File(saveFile.toString()+".xlsx");
           }
               XSSFWorkbook workbook = new XSSFWorkbook();
               XSSFSheet sheet = workbook.createSheet("produts");
               
               DefaultTableModel model = (DefaultTableModel)table_product_admin.getModel();
               
               //Create header row
               XSSFRow headerRow = sheet.createRow(0);
               for(int i = 0 ; i < model.getColumnCount() ; i++){
                   headerRow.createCell(i).setCellValue(model.getColumnName(i));
               }
               //Create data rows
               for(int row = 0 ; row < model.getRowCount() ; row++){
                   XSSFRow dataRow = sheet.createRow(row+1);
                   for(int col = 0 ; col < model.getColumnCount();col++){
                     Object value = model.getValueAt(row, col);
                     if(value != null){
                         dataRow.createCell(col).setCellValue(value.toString());
                     }
                   }
               }
               //Auto-size coloums 
               for(int i = 0 ; i < model.getColumnCount() ; i++){
                   sheet.autoSizeColumn(i);
               }
               
           //Write workbook to file 
           for(int i = 0 ; i < model.getColumnCount() ; i++){
               sheet.autoSizeColumn(i);
           }
           //Write workbook to file 
           try{
           FileOutputStream fos = new FileOutputStream(saveFile);
               workbook.write(fos);
               JOptionPane.showMessageDialog(this, "Export file success!");
            }catch(HeadlessException | IOException e){
                 JOptionPane.showMessageDialog(this, "Export file fail!");
                System.out.println(e);
            }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboxOptionSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboxOptionSortActionPerformed
        
        String option = jComboxOptionSort.getItemAt(jComboxOptionSort.getSelectedIndex());
       
        switch (option) {
            
            case"Default" -> {
                table_product_admin.setModel(modelProduct);
            }
            
            case "Name ASC" ->             { 
            DefaultTableModel sortNameAsc = new DefaultTableModel();
            table_product_admin.setModel(sortNameAsc);
                sortNameAsc.addColumn("Product Id");
                sortNameAsc.addColumn("Name");
                sortNameAsc.addColumn("Price");
                sortNameAsc.addColumn("Quatity");
                sortNameAsc.addColumn("Category");
                sortNameAsc.addColumn("Detail");
            
            List<Product> listProductSortName = listProduct.stream()
                    .sorted(Comparator.comparing(Product::getName)).toList();
                for(Product item : listProductSortName){     
                             sortNameAsc.addRow(new Object[]{
                             item.getProductId(),
                             item.getName(),
                             item.getPrice(),
                             item.getQuatity(),
                             item.getCategory(),
                             item.getDetail()});
                }
                         
            }
            
            case "Name DESC" -> {
            DefaultTableModel sortNameDesc = new DefaultTableModel();
            table_product_admin.setModel(sortNameDesc);
                sortNameDesc.addColumn("Product Id");
                sortNameDesc.addColumn("Name");
                sortNameDesc.addColumn("Price");
                sortNameDesc.addColumn("Quatity");
                sortNameDesc.addColumn("Category");
                sortNameDesc.addColumn("Detail");
            
            List<Product> listProductSortName = listProduct.stream()
                    .sorted(Comparator.comparing(Product::getName , Comparator.reverseOrder()))
                    .toList();
                for(Product item : listProductSortName){     
                             sortNameDesc.addRow(new Object[]{
                             item.getProductId(),
                             item.getName(),
                             item.getPrice(),
                             item.getQuatity(),
                             item.getCategory(),
                             item.getDetail()});
                        }   
            }
            
            case "Price ASC" -> {
            DefaultTableModel sortPriceAsc = new DefaultTableModel();
            table_product_admin.setModel(sortPriceAsc);
                sortPriceAsc.addColumn("Product Id");
                sortPriceAsc.addColumn("Name");
                sortPriceAsc.addColumn("Price");
                sortPriceAsc.addColumn("Quatity");
                sortPriceAsc.addColumn("Category");
                sortPriceAsc.addColumn("Detail");
            
            List<Product> listProductSortPrice = listProduct.stream()
                    .sorted(Comparator.comparingDouble(Product::getPrice))
                    .toList();
                for(Product item : listProductSortPrice){     
                             sortPriceAsc.addRow(new Object[]{
                             item.getProductId(),
                             item.getName(),
                             item.getPrice(),
                             item.getQuatity(),
                             item.getCategory(),
                             item.getDetail()});
                        }   
            }
            case "Price DESC" -> {
            DefaultTableModel sortPriceDesc = new DefaultTableModel();
            table_product_admin.setModel(sortPriceDesc);
                sortPriceDesc.addColumn("Product Id");
                sortPriceDesc.addColumn("Name");
                sortPriceDesc.addColumn("Price");
                sortPriceDesc.addColumn("Quatity");
                sortPriceDesc.addColumn("Category");
                sortPriceDesc.addColumn("Detail");
            
            
            List<Product> listProductSortPrice = listProduct.stream()
                    .sorted(Comparator.comparingDouble(Product::getPrice)
                    .reversed()).toList();
                for(Product item : listProductSortPrice){     
                             sortPriceDesc.addRow(new Object[]{
                             item.getProductId(),
                             item.getName(),
                             item.getPrice(),
                             item.getQuatity(),
                             item.getCategory(),
                             item.getDetail()});
                        }   
            }
            case "Quatity ASC" -> {
            DefaultTableModel sortPriceQuatity = new DefaultTableModel();
            table_product_admin.setModel(sortPriceQuatity);
                sortPriceQuatity.addColumn("Product Id");
                sortPriceQuatity.addColumn("Name");
                sortPriceQuatity.addColumn("Price");
                sortPriceQuatity.addColumn("Quatity");
                sortPriceQuatity.addColumn("Category");
                sortPriceQuatity.addColumn("Detail");
            
            List<Product> listProductSortQuatity = listProduct.stream()
                    .sorted(Comparator.comparingDouble(Product::getQuatity))
                    .toList();
                for(Product item : listProductSortQuatity){     
                             sortPriceQuatity.addRow(new Object[]{
                             item.getProductId(),
                             item.getName(),
                             item.getPrice(),
                             item.getQuatity(),
                             item.getCategory(),
                             item.getDetail()});
                        }   
            }
            case "Quatity DESC" -> {
            DefaultTableModel sortPriceQuatity = new DefaultTableModel();
            table_product_admin.setModel(sortPriceQuatity);
                sortPriceQuatity.addColumn("Product Id");
                sortPriceQuatity.addColumn("Name");
                sortPriceQuatity.addColumn("Price");
                sortPriceQuatity.addColumn("Quatity");
                sortPriceQuatity.addColumn("Category");
                sortPriceQuatity.addColumn("Detail");
            
            List<Product> listProductSortQuatity = listProduct.stream()
                    .sorted(Comparator.comparingDouble(Product::getQuatity).reversed())
                    .toList();
                for(Product item : listProductSortQuatity){     
                             sortPriceQuatity.addRow(new Object[]{
                             item.getProductId(),
                             item.getName(),
                             item.getPrice(),
                             item.getQuatity(),
                             item.getCategory(),
                             item.getDetail()});
                        }   
            }
            default -> throw new AssertionError();
        }
        
    }//GEN-LAST:event_jComboxOptionSortActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
      Home home = new Home();
      home.setVisible(true);
      home.setLocationRelativeTo(null);
      this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       Home home = new Home();
       home.setVisible(true);
       home.setLocationRelativeTo(null);
       this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      Home home = new Home();
      home.setVisible(true);
      home.setLocationRelativeTo(null);
      this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
      Home home = new Home();
      home.setVisible(true);
      home.setLocationRelativeTo(null);
      this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed
          
    
    public void addDataToTableUser(){

        table_customer_admin.setModel(modelUser);
        
        modelUser.addColumn("User Id");
        modelUser.addColumn("FullName");
        modelUser.addColumn("Phone");
        modelUser.addColumn("Gender");
        modelUser.addColumn("Username");
        modelUser.addColumn("Password");
        for(User user : listUser){
        if(!user.getRole().equals("ROLE_ADMIN")){
        modelUser.addRow(new Object[]{
            user.getId(),
            user.getFullname(),
            user.getPhone(),
            user.getGender(),
            user.getUsername(), 
            user.getPassword()
                 });
            }
        }
    }
    
    public void clearTextFiledUser(){
        jProductCodeAdmin.setText("");
        jProductName.setText("");
        jProductDetail.setText("");
        jProductPrice.setText("");
        category_comboBox.setSelectedItem(category_comboBox.getItemAt(0));
    }      
    
    
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JButtonDeleteCategory;
    private javax.swing.JButton JButtonUpdateCategory;
    private javax.swing.JComboBox<String> JComboboxDiablog;
    private javax.swing.JTextField JDiablogProductName;
    private javax.swing.JTextField JDiablogProductPrice;
    private javax.swing.JTextField JDiablogProductQuatity;
    private javax.swing.JDialog JDialogAddCategory;
    private javax.swing.JDialog JDialogAddProduct;
    private javax.swing.JTextArea JDialogProductDetail;
    private javax.swing.JTextField JDialogProductId;
    private javax.swing.JLabel JLabelphoneInCustomer;
    private javax.swing.JPasswordField JPasswordCustomer;
    private javax.swing.JTextField JTexCategegoryIdAdmin;
    private javax.swing.JTextField JTexCategoryDetailAdmin;
    private javax.swing.JTextField JTextCategoryNameAdmin;
    private javax.swing.JButton JbuttonAddCategory;
    private javax.swing.JDialog JdDiablogAddCustomer;
    private javax.swing.JTabbedPane LayoutAdmin;
    private javax.swing.JButton ResultData;
    private javax.swing.JButton SaveProductToData;
    private javax.swing.JButton addProduct;
    private javax.swing.JButton add_customer_admin;
    private javax.swing.JButton buttonCancelAddCustomer;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton buttonImportData;
    private javax.swing.JButton buttonRefreshInputCustomer;
    private javax.swing.JButton buttonSaveAddCustomer;
    private javax.swing.JButton buttonSearchUserByName;
    private javax.swing.JComboBox<Category> category_comboBox;
    private javax.swing.JTable data_category_admin;
    private javax.swing.JButton deleteRowProduct;
    private javax.swing.JButton delete_button;
    private javax.swing.JPanel demoDiablog;
    private javax.swing.JTextField input_name_customer;
    private javax.swing.JTextField input_product_search;
    private javax.swing.JTextField input_year;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButtonSaveUserByAdmin;
    private javax.swing.JTextField jCategoryDetailAdmin;
    private javax.swing.JTextField jCategoryIdAdmin;
    private javax.swing.JTextField jCategoryNameAdmin;
    private javax.swing.JComboBox<String> jComboxOptionSort;
    private javax.swing.JButton jDialogButtonSaveCategory;
    private javax.swing.JTextField jFullnameAddCustomer;
    private javax.swing.JTextField jFullnameInCustomer;
    private javax.swing.JLabel jGenderUsername;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelUsernameInCustomer;
    private javax.swing.JLabel jNameInCustomer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordAddcustomer;
    private javax.swing.JLabel jPasswordInCustomer;
    private javax.swing.JTextField jPhoneAddcustomer;
    private javax.swing.JTextField jPhoneInCustomer;
    private javax.swing.JTextField jProductCodeAdmin;
    private javax.swing.JTextField jProductDetail;
    private javax.swing.JTextField jProductName;
    private javax.swing.JTextField jProductPrice;
    private javax.swing.JTextField jProductQuantity;
    private javax.swing.JRadioButton jRadioGender1;
    private javax.swing.JRadioButton jRadioGender2;
    private javax.swing.JRadioButton jRadiobuttonChoseFemale;
    private javax.swing.JRadioButton jRadiobuttonChoseMale;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JScrollPane jTableCatgoryAdmin;
    private javax.swing.JTextField jUsercodeAddCustomer;
    private javax.swing.JTextField jUsercode_customer;
    private javax.swing.JTextField jUsernameAddCustomer;
    private javax.swing.JTextField jUsernameInCustomer;
    private javax.swing.JLabel labelYear;
    private javax.swing.JPanel layout;
    private javax.swing.JButton refresh_data_product;
    private javax.swing.JButton searchProductByName;
    private javax.swing.JTable table_customer_admin;
    private javax.swing.JTable table_product_admin;
    private javax.swing.JTable table_revenue_bill;
    private javax.swing.JTextField totalMoneyInYear;
    private javax.swing.JButton update_data_product;
    // End of variables declaration//GEN-END:variables
}

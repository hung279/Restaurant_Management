/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package file;

import java.util.List;
import model.Bill;
import model.Category;
import model.Product;
import model.User;
/**
 *
 * @author Doan Huu Minh
 */
public class GetData {
    
    private static final ManageFileProduct manageFileProduct = new ManageFileProduct();
    private static final ManageFileUser manageFileUser = new ManageFileUser();
    private static final ManageFileCategory manageFileCategory = new ManageFileCategory();
    private static final ManageFileBill manageFileBill = new ManageFileBill();
    
    private static final String file_categories = "E:\\HOC TAP\\LAP_TRINH_JAVA\\JAVA\\Restaurant_Management\\src\\datas\\categories.txt";
    private static final String file_products = "E:\\HOC TAP\\LAP_TRINH_JAVA\\JAVA\\Restaurant_Management\\src\\datas\\products.txt";
    private static final String file_users = "E:\\HOC TAP\\LAP_TRINH_JAVA\\JAVA\\Restaurant_Management\\src\\datas\\users.txt";
    private static final String file_bills = "E:\\HOC TAP\\LAP_TRINH_JAVA\\JAVA\\Restaurant_Management\\src\\datas\\bills.txt";
    
    public List<Category> getDataCategoryFromFile(){
         return manageFileCategory.readCategoryFromFile(file_categories);
    }
     
    public List<Product> getDataProductFromFile(){
        return manageFileProduct.readProductFromFile(file_products);
    }
    
    public List<User> getDataUserFromFile(){
        return manageFileUser.readUserFromFile(file_users);
    }
    
    public List<Bill> getDataBillFromFile(){
        return manageFileBill.readBillFromFile(file_bills);
    }
    
}

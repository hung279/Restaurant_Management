/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import model.Product;

public class ManageFileProduct extends ManageFile{
    
    public Product createProductFromData(String data){
        String[] datas = data.split("\\|");
        Product acc = new Product(datas[0], datas[1],Float.parseFloat(datas[2]), datas[3], datas[4]);
        return acc;
    }

    public List<Product> readProductFromFile(String fileName){
        openFileToRead(fileName);
        List<Product> list = new ArrayList<>();

        while(scanner.hasNext()){
            String data = scanner.nextLine();
            Product acc = createProductFromData(data);
            list.add(acc);
        }
        super.closeFileAfterRead();
        return list;
    }

    
    public void writeProductToFile(String file , Product product){
        openFileToWrite(file);
        getPrintWriter().println(product.getProductId()+"|"+ product.getName()
              + "|"+ product.getPrice() +"|" + product.getCategory() + "|"
                +"|"+ product.getDetail());        
        super.closeFileAfterWriter();
    }
    public void updateProductToFile(List<Product> productList ,String fileName){

        readProductFromFile(fileName);
        File file = new File(fileName);
        if(file.exists()){
            file.delete();
        }
        openFileToWrite(fileName);
        for(Product product : productList){
            printWriter.println(product.getProductId()
                    +"|"  + product.getName()
                    + "|" + product.getPrice()
                    + "|"  + product.getCategory()
                    +"|"+ product.getDetail()); 
        }
        super.closeFileAfterWriter();
    }
}

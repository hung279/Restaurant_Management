/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import model.Category;

public class ManageFileCategory extends ManageFile{
    
     public Category createCategoryFromData(String data){
        String[] datas = data.split("\\|");
        Category acc = new Category(datas[0], datas[1], datas[2]);
        return acc;
    }

    public List<Category> readCategoryFromFile(String fileName){
        super.openFileToRead(fileName);
        List<Category> list = new ArrayList<>();

        while(this.scanner.hasNext()){
            String data = this.scanner.nextLine();
            Category acc = createCategoryFromData(data);
            list.add(acc);
        }
        super.closeFileAfterRead();
        return list;
    }
    //Account
    public void writeCategorytToFile(String file , Category category){
        super.openFileToWrite(file);
        getPrintWriter().println(category.getCategoryId() +"|"+ category.getName()
                +"|" + category.getDetail());        
        super.closeFileAfterWriter();
    }
    public void updateAccountToFile(List<Category> infoCategorysList ,String fileName){

        readCategoryFromFile(fileName);
        File file = new File(fileName);
        if(file.exists()){
            file.delete();
        }
        super.openFileToWrite(fileName);
        for(Category category : infoCategorysList){
            printWriter.println(category.getCategoryId() +"|"+ category.getName()
                + "|" + category.getDetail());
        }
        super.closeFileAfterWriter();
    }
}

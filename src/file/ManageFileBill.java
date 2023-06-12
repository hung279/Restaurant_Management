/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import model.Bill;

/**
 *
 * @author Doan Huu Minh
 */
public class ManageFileBill extends ManageFile{
    
     public Bill createBillFromData(String data){
        String[] datas = data.split("\\|");
        Bill bill = new Bill(datas[0], datas[1], datas[2], Float.parseFloat(datas[3]));
        return bill;
    }

    public List<Bill> readBillFromFile(String fileName){
        super.openFileToRead(fileName);
        List<Bill> listBill = new ArrayList<>();

        while(this.scanner.hasNext()){
            String data = this.scanner.nextLine();
            Bill bill = createBillFromData(data);
            listBill.add(bill);
        }
        super.closeFileAfterRead();
        return listBill;
    }
    //Account
    public void writeBillToFile(String file , Bill bill){
        super.openFileToWrite(file);
        getPrintWriter().println(bill.getBillCode()+"|"+ bill.getCustomerName()
                +"|" + bill.getCreatedBuy() + "|" + bill.getTotal());        
        super.closeFileAfterWriter();
    }
    public void updateBillToFile(List<Bill> infoBillList ,String fileName){

        readBillFromFile(fileName);
        File file = new File(fileName);
        if(file.exists()){
            file.delete();
        }
        super.openFileToWrite(fileName);
        for(Bill bill : infoBillList){
             getPrintWriter().println(bill.getBillCode()+"|"+ bill.getCustomerName()
                +"|" + bill.getCreatedBuy() + "|" + bill.getTotal());   
        }
        super.closeFileAfterWriter();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package file;
import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;
/**
 *
 * @author Doan Huu Minh
 */
public class ManageFile {
    protected FileWriter fileWriter;
    protected BufferedWriter bufferedWriter;
    protected PrintWriter printWriter;
    protected Scanner scanner;



    public FileWriter getFileWriter() {
        return fileWriter;
    }

    public void setFileWriter(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }

    public void setBufferedWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    public void setPrintWriter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public Scanner getScanner() {
        return scanner;
    }
    public void openFileToWrite(String fileName){

        try{
            fileWriter = new FileWriter(fileName,true);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
        }catch (IOException e){
            System.out.println(e.getCause());
        }
    }

    public void closeFileAfterWriter(){
        try{
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        }catch (IOException e){
            System.out.println(e.getCause());
        }
    }

    public void openFileToRead(String fileName){
        try {
            scanner = new Scanner(Paths.get(fileName));
            System.out.println(Paths.get(fileName));
        }catch(IOException e){
            System.out.println(e.getCause());
        }
    }

    public void closeFileAfterRead(){
        scanner.close();
    }

}

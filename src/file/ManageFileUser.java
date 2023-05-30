package file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class ManageFileUser extends ManageFile{
     public User createUserFromData(String data){
        String[] datas = data.split("\\|");
        User acc = new User(datas[0], datas[1], datas[2] , 
                datas[3] , datas[4] , datas[5] , datas[6]);
        return acc;
    }

    public List<User> readUserFromFile(String fileName){
        openFileToRead(fileName);
        List<User> list = new ArrayList<>();

        while(scanner.hasNext()){
            String data = scanner.nextLine();
            User acc = createUserFromData(data);
            list.add(acc);
        }
        super.closeFileAfterRead();
        return list;
    }

    public void writeUserToFile(String file , User user){
        super.openFileToWrite(file);
        getPrintWriter().println(
                          user.getId() +"|"+ user.getFullname()+"|"
                        + user.getPhone() +"|"+ user.getGender()+"|"
                        + user.getUsername() +"|" + user.getPassword()+"|"
                        + user.getRole());        
        super.closeFileAfterWriter();
    }
    
    public void updateUserToFile(List<User> userList ,String fileName){

        readUserFromFile(fileName);
        File file = new File(fileName);
        if(file.exists()){
            file.delete();
        }
        super.openFileToWrite(fileName);
        for( User user: userList){
            printWriter.println(
                         user.getId() +"|"+ user.getFullname()+"|"
                        + user.getPhone() +"|"+ user.getGender()+"|"
                        + user.getUsername() +"|" + user.getPassword()+"|"
                        + user.getRole()); 
        }
        super.closeFileAfterWriter();
    }
    
}

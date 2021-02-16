package bean;

import lombok.Data;

import java.util.List;

@Data
public class UserGroup {
    private String groupName;
    List<User> userList;

    public void list(){
        System.out.println("Group====="+ this.groupName +"--start=====");
        for (User user:this.userList){
            user.introduction();
        }
        System.out.println("Group====="+ this.groupName +"--end=====");
    }
}

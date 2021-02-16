package bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Data
public class Company {

    @Autowired
    UserGroup userGroup;

    @Resource(name = "user_aa")
    User worker;

    public void work(){
        System.out.println("This is Company."+ this.getUserGroup().getGroupName()+" Worker--"+this.worker.getName()+" is working...");
    }
}

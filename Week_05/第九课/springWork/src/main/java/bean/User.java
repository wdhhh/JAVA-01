package bean;

import lombok.Data;

@Data
public class User {
    private String name;
    private String age;
    private String no;

    public void introduction(){
        System.out.println("This is User "+ this.name+". age:"+this.age+". no:"+this.no);
    }
}

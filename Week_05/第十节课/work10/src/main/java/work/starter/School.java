package work.starter;

import work.starter.aop.ISchool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({Student.class,Klass.class})
public class School implements ISchool {

    // Resource
    @Autowired(required = true) //primary
    Klass class1;

    //@Resource(name = "student100")
    @Autowired
    Student student100;

    @Override
    public String ding(){

        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student100);
        return "Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student100;
    }

}

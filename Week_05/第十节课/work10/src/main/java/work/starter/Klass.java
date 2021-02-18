package work.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "class1")
public class Klass {

    List<Student> students;

    public void dong(){
        System.out.println(this.getStudents());
    }

}

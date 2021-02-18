package work.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 作业03：给前面课程提供的 Student/Klass/School 实现自动配置和 Starter。
 * 访问http://localhost:8080/school打印school.ding()；
 *
 * 作业06.3：配置 Hikari 连接池，改进上述操作,work.starter.controller.Work06Controller
 */
@SpringBootApplication
public class StarterApplication {

    public static void main(String[] args) {
       SpringApplication.run(StarterApplication.class,args);
    }
}

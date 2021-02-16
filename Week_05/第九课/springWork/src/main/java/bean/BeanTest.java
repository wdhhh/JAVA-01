package bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *  作业：写代码实现Spring Bean 的装配
 */
public class BeanTest {

    public static void main(String[] args) {
        // 1.使用XML方式装配Bean
        System.out.println("1.使用XML方式装配Bean");
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user_aa = (User) context.getBean("user_aa");
        user_aa.introduction();

        UserGroup userGroup = context.getBean(UserGroup.class);
        userGroup.list();

        // 2.使用注解的方式装配Bean
        System.out.println("\n2.使用注解的方式装配Bean");
        Company company = context.getBean(Company.class);
        company.work();
    }
}

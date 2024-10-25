package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("appconfig.xml");
        Product product1 = (Product) context.getBean("beanOne");
        Product product2 = (Product) context.getBean("beanTwo");
        Product product3 = (Product) context.getBean("beanThree");
        System.out.println(product1);
        System.out.println(product2);
        System.out.println(product3);
    }
}

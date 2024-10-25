package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Product product1 = (Product) context.getBean("beanOne", Product.class);
        Product product2 = (Product) context.getBean("beanTwo", Product.class);
        Product product3 = (Product) context.getBean("beanThree", Product.class);
        System.out.println(product1);
        System.out.println(product2);
        System.out.println(product3);

    }
}

package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int numArgsNecessary = 3; // URI, username, password
        if(args.length != numArgsNecessary){
            System.out.println("Please provide right an argument");
            return;
        }
        ProductDAO productDAO = new ProductDAO(args[0], args[1], args[2]);
        productDAO.deleteAll();
        do {
            System.out.println("1. Add product");
            System.out.println("2. Display all products");
            System.out.println("3. Display product by id");
            System.out.println("4. Update product");
            System.out.println("5. Delete product");
            System.out.println("6. Exit");
            System.out.println("Choose an option: ");
            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter product name: ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    System.out.println("Enter product price: ");
                    double price = sc.nextInt();
                    Product product = new Product(0, name, price);
                    productDAO.add(product);
                    break;
                case 2:
                    productDAO.readAll().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Enter product id: ");
                    int productId = sc.nextInt();
                    System.out.println(productDAO.read(productId));
                    break;
                case 4:
                    System.out.println("Enter product id: ");
                    int id = sc.nextInt();
                    System.out.println("Enter product name: ");
                    sc.nextLine();
                    name = sc.nextLine();
                    System.out.println("Enter product price: ");
                    price = sc.nextDouble();
                    product = new Product(id, name, price);
                    productDAO.update(product);
                    break;
                case 5:
                    System.out.println("Enter product id: ");
                    id = sc.nextInt();
                    productDAO.delete(id);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid option");
            }
        } while (true);
    }
}
package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        PhoneDAO phoneDAO = new PhoneDAO();
        ManufactureDAO manufactureDAO = new ManufactureDAO();
        Session session;
        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Add Phone");
            System.out.println("2. Get Phone by ID");
            System.out.println("3. Get All Phones");
            System.out.println("4. Update Phone");
            System.out.println("5. Remove Phone by ID");
            System.out.println("6. Remove Phone by Phone");
            System.out.println("7. Get Highest Phone Price");
            System.out.println("8. Get Phones with Price");
            System.out.println("9. Check About Phone Price");
            System.out.println("10. Get Phone");
            System.out.println("11. Add Manufacture");
            System.out.println("12. Get Manufacture by ID");
            System.out.println("13. Get All Manufactures");
            System.out.println("14. Update Manufacture");
            System.out.println("15. Remove Manufacture by ID");
            System.out.println("16. Remove Manufacture by Manufacture");
            System.out.println("17. get Phone Criteria");
            System.out.println("18. check Employee Count All manufactures");
            System.out.println("19. Check Sum Employee Count");
            System.out.println("20. Get last Manufacture");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Add Phone
                    System.out.print("Enter Phone Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Phone Price: ");
                    int price = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Phone Color: ");
                    String color = scanner.nextLine();
                    System.out.print("Enter Phone Country: ");
                    String country = scanner.nextLine();
                    System.out.print("Enter Phone Quantity: ");
                    int quantity = scanner.nextInt();
                    Phone phone = new Phone(name, price, color, country, quantity);
                    phoneDAO.add(phone);
                    break;
                case 2:
                    // Get Phone by ID
                    System.out.print("Enter Phone ID: ");
                    int phoneID = scanner.nextInt();
                    Phone phone1 = phoneDAO.get(phoneID);
                    System.out.println(phone1);
                    break;
                case 3:
                    // Get All Phones
                    phoneDAO.getAll().forEach(System.out::println);
                    break;
                case 4:
                    // Update Phone
                    System.out.print("Enter Phone ID: ");
                    int phoneID1 = scanner.nextInt();
                    scanner.nextLine();
                    Phone phone2 = phoneDAO.get(phoneID1);
                    System.out.print("Enter Phone Name: ");
                    String name1 = scanner.nextLine();
                    System.out.print("Enter Phone Price: ");
                    int price1 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Phone Color: ");
                    String color1 = scanner.nextLine();
                    System.out.print("Enter Phone Country: ");
                    String country1 = scanner.nextLine();
                    System.out.print("Enter Phone Quantity: ");
                    int quantity1 = scanner.nextInt();
                    phone2.setName(name1);
                    phone2.setPrice(price1);
                    phone2.setColor(color1);
                    phone2.setCountry(country1);
                    phone2.setQuantity(quantity1);
                    phoneDAO.update(phone2);
                    break;
                case 5:
                    // Remove Phone by ID
                    System.out.print("Enter Phone ID: ");
                    int phoneID2 = scanner.nextInt();
                    Phone phone3 = phoneDAO.get(phoneID2);
                    phoneDAO.remove(phone3);
                    break;
                case 6:
                    // Remove Phone by Phone
                    System.out.print("Enter Phone ID: ");
                    int id = scanner.nextInt();
                    Phone phone4 = phoneDAO.get(id);
                    phoneDAO.remove(phone4);
                    break;
                case 7:
                    // Get Highest Phone Price
                    System.out.println(phoneDAO.getHighestPrice());
                    break;
                case 8:
                    // Get Phones with Prices
                    phoneDAO.getPhonesWithPrice().forEach(System.out::println);
                    break;
                case 9:
                    // Check About Phone Price
                    System.out.println(phoneDAO.checkAboutPhonePrice());
                    break;
                case 10:
                    // Get Phone
                    System.out.print("Enter Phone ID: ");
                    int phoneID3 = scanner.nextInt();
                    Phone phone5 = phoneDAO.get(phoneID3);
                    System.out.println(phone5);
                    break;
                case 11:
                    // Add Manufacture
                    System.out.print("Enter Manufacture Name: ");
                    String name3 = scanner.nextLine();
                    System.out.print("Enter Manufacture Location: ");
                    String location = scanner.nextLine();
                    System.out.print("Enter Manufacture Employee Count: ");
                    int employeeCount = scanner.nextInt();
                    Manufacture manufacture2 = new Manufacture();
                    System.out.print("Enter Phones ID: ");
                    while (scanner.hasNextInt()) {

                        int phoneID4 = scanner.nextInt();
                        if(phoneID4 == -1) {
                            break;
                        }
                        Phone phone6 = phoneDAO.get(phoneID4);
                        manufacture2.getPhones().add(phone6);
                    }
                    manufacture2.setName(name3);
                    manufacture2.setLocation(location);
                    manufacture2.setEmployeeCount(employeeCount);
                    System.out.println(manufacture2);
                    manufactureDAO.add(manufacture2);
                    break;
                case 12:
                    // Get Manufacture by ID
                    System.out.print("Enter Manufacture ID: ");
                    int manufactureID2 = scanner.nextInt();
                    Manufacture manufacture3 = manufactureDAO.get(manufactureID2);
                    System.out.println(manufacture3);
                    break;
                case 13:
                    // Get All Manufactures
                    manufactureDAO.getAll().forEach(System.out::println);
                    break;
                case 14:
                    // Update Manufacture
                    System.out.print("Enter Manufacture ID: ");
                    int manufactureID3 = scanner.nextInt();
                    Manufacture manufacture4 = manufactureDAO.get(manufactureID3);
                    System.out.print("Enter Manufacture Name: ");
                    String name4 = scanner.next();
                    System.out.print("Enter Manufacture Location: ");
                    String location1 = scanner.next();
                    System.out.print("Enter Manufacture Employee Count: ");
                    int employeeCount1 = scanner.nextInt();
                    manufacture4.setName(name4);
                    manufacture4.setLocation(location1);
                    manufacture4.setEmployeeCount(employeeCount1);
                    System.out.print("Enter Phones ID: ");
                    while (scanner.hasNextInt()) {
                        int phoneID4 = scanner.nextInt();
                        Phone phone6 = phoneDAO.get(phoneID4);
                        manufacture4.getPhones().add(phone6);
                    }
                    manufactureDAO.update(manufacture4);
                    break;
                case 15:
                    // Remove Manufacture by ID
                    System.out.print("Enter Manufacture ID: ");
                    int manufactureID4 = scanner.nextInt();
                    Manufacture manufacture5 = manufactureDAO.get(manufactureID4);
                    manufactureDAO.remove(manufacture5);
                    break;
                case 16:
                    // Remove Manufacture by Manufacture
                    System.out.print("Enter Manufacture ID: ");
                    int id1 = scanner.nextInt();
                    Manufacture manufacture6 = manufactureDAO.get(id1);
                    manufactureDAO.remove(manufacture6);
                    break;
                case 17:
                    // getPhoneCriteria
                    System.out.println(phoneDAO.getPhoneCriteria());
                    break;
                case 18:
                    // check Employee Count All manufactures
                    System.out.println(manufactureDAO.checkEmployeeCount());
                    break;
                case 19:
                    // Check Sum Employee Count
                    System.out.println(manufactureDAO.checkSumEmployeeCount());
                    break;
                case 20:
                    // Get last Manufacture
                    try {
                        System.out.println(manufactureDAO.getManufactureLast());
                    }
                    catch (InvalidOperationException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }
}

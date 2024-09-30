package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public static class Phone {
        private   String ID;
        private String name;
        private int Price;
        private String color;
        private String country;
        private int quantity;

        public Phone(String ID, String name, int Price, String color, String country, int quantity) {
            this.ID = ID;
            this.name = name;
            this.Price = Price;
            this.color = color;
            this.country = country;
            this.quantity = quantity;
        }
        public String getID() {
            return ID;
        }
        public void setID(String ID) {
            this.ID = ID;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getPrice() {
            return Price;
        }
        public void setPrice(int Price) {
            this.Price = Price;
        }
        public String getColor() {
            return color;
        }
        public void setColor(String color) {
            this.color = color;
        }
        public String getCountry() {
            return country;
        }
        public void setCountry(String country) {
            this.country = country;
        }
        public int getQuantity() {
            return quantity;
        }
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

    }
}
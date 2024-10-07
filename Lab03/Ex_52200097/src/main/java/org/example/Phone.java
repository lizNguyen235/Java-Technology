package org.example;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity
@Table(name = "MobilePhone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int ID;
    @Column(name = "name", nullable = false)
    @Size(min = 3, max = 128)
    private String name;
    @Column(name = "price", nullable = false)
    private int Price;
    @Column(name = "color", nullable = false)
    private String color;
    @Column(name = "country")
    private String country;
    @Column(name = "quantity")
    private int quantity;
    @ManyToOne
    private Manufacture manufacture;

    public Phone() {}
    public Phone( String name, int Price, String color, String country, int quantity) {
        this.name = name;
        this.Price = Price;
        this.color = color;
        this.country = country;
        this.quantity = quantity;
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
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
    public Manufacture getManufacture() {
        return manufacture;
    }

    public void setManufacture(Manufacture manufacture) {
        this.manufacture = manufacture;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", Price=" + Price +
                ", color='" + color + '\'' +
                ", country='" + country + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

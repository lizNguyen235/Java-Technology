package org.example;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Manufacture")
public class Manufacture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer ID;
    @Column(name = "name", nullable = false)
    @Size(min = 3, max = 128)
    private String name;
    @Column(name = "location", nullable = false)
    private String location;
    @Column(name = "employee_count")
    private int employeeCount;
    @OneToMany(mappedBy = "manufacture", cascade = CascadeType.ALL)
    private List<Phone> phones = new ArrayList<Phone>();

    public Manufacture() {}

    public Manufacture( String name, String location, int employeeCount) {
        this.name = name;
        this.location = location;
        this.employeeCount = employeeCount;
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
    public String getLocation() {
        return location;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public int getEmployeeCount() {
        return employeeCount;
    }
    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }
    @Override
    public String toString() {
        return "Manufacture{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", employeeCount=" + employeeCount +
                ", phones=" + phones.toString() +
                '}';
    }
}

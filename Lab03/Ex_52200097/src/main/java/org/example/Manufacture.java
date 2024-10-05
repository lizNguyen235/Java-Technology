package org.example;

import jakarta.persistence.*;
import jakarta.validation.*;
import java.util.List;

@Entity
@Table(name = "manufacture")
public class Manufacture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private String ID;
    @Column(name = "name", nullable = false)
    @Size(min = 3, max = 50)
    private String name;
    @Column(name = "location", nullable = false)
    private String location;
    @Column(name = "employee_count")
    private int employeeCount;

    private List<Phone> phones;

    public Manufacture(String ID, String name, String location, int employeeCount) {
        this.ID = ID;
        this.name = name;
        this.location = location;
        this.employeeCount = employeeCount;
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
    public String getLocation() {
        return location;
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

}

package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements Repository<Product,Integer> {
    String hostName;
    String dbName;
    String userName;
    String password;


    public ProductDAO(String hostName, String dbName, String userName, String password) {
        this.hostName = hostName;
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;

    }
    public static Connection getPostgresSQLConnection() {
        String hostName = "localhost";
        String dbName = "ProductManagement";
        String userName = "postgres";
        String password = "hung123";
        return getPostgresSQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getPostgresSQLConnection(String hostName, String dbName, String userName, String
            password) {
        try {
            String connectionURL = "jdbc:postgresql://" + hostName + ":5432/" + dbName;
            return DriverManager.getConnection(connectionURL, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public Integer add(Product item) {
        try {
            String query = "INSERT INTO product(name, price) VALUES(?, ?)";
            Connection connection = getPostgresSQLConnection();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.executeUpdate();
            ResultSet data = statement.getGeneratedKeys();
            if (data.next()) {
                return data.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> readAll() {
        List<Product> products = new ArrayList<>();
        try {
            String query = "SELECT * FROM product";
            Connection connection = getPostgresSQLConnection();
            Statement statement = connection.createStatement();
            ResultSet data = statement.executeQuery(query);

            while (data.next()) {
                int id = data.getInt("id");
                String name = data.getString("name");
                double price = data.getDouble("price");
                Product product = new Product(id, name, price);
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return products;
    }

    @Override
    public Product read(Integer id) {
        try {
            String query = "SELECT * FROM product WHERE id = ?";
            Connection connection = getPostgresSQLConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet data = statement.executeQuery();

            if (data.next()) {
                String name = data.getString("name");
                double price = data.getDouble("price");
                return new Product(id, name, price);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public boolean update(Product item) {
        try {
            String query = "UPDATE product SET name = ?, price = ? WHERE id = ?";
            Connection connection = getPostgresSQLConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.setInt(3, item.getId());
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        try {
            String query = "DELETE FROM product WHERE id = ?";
            Connection connection = getPostgresSQLConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

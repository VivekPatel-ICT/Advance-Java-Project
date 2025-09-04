package com.phm.helper;

import com.phm.bean.LoginBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBService {

    private Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicine", "root", "Dmp@tel88");
            System.out.println("Connection successful");
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println("Connection failed: " + exception.getMessage());
        }
        return con;
    }

    public void addMedicine(LoginBean bean) {
        String query = "INSERT INTO meddata(Name, Brand, Price, Stock) VALUES (?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, bean.getName());
            ps.setString(2, bean.getBrand());
            ps.setDouble(3, bean.getPrice());
            ps.setInt(4, bean.getStock());
            ps.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(DBService.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public ArrayList<LoginBean> fetchAllMedicines() {
        ArrayList<LoginBean> list = new ArrayList<>();
        String query = "SELECT * FROM meddata";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                LoginBean bean = new LoginBean();
                bean.setId(rs.getInt("id"));
                bean.setName(rs.getString("Name"));
                bean.setBrand(rs.getString("Brand"));
                bean.setPrice(rs.getDouble("Price"));
                bean.setStock(rs.getInt("Stock"));
                list.add(bean);
            }

        } catch (SQLException e) {
            Logger.getLogger(DBService.class.getName()).log(Level.SEVERE, null, e);
        }

        return list;
    }

    public LoginBean fetchMedicineById(int id) {
        LoginBean bean = null;
        String query = "SELECT * FROM meddata WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                bean = new LoginBean();
                bean.setId(rs.getInt("id"));
                bean.setName(rs.getString("Name"));
                bean.setBrand(rs.getString("Brand"));
                bean.setPrice(rs.getDouble("Price"));
                bean.setStock(rs.getInt("Stock"));
            }

        } catch (SQLException e) {
            Logger.getLogger(DBService.class.getName()).log(Level.SEVERE, null, e);
        }

        return bean;
    }

    public void updateMedicineById(LoginBean bean) {
        String query = "UPDATE meddata SET Brand = ?, Price = ?, Stock = ? WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, bean.getBrand());
            ps.setDouble(2, bean.getPrice());
            ps.setInt(3, bean.getStock());
            ps.setInt(4, bean.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(DBService.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void deleteMedicineById(int id) {
        String query = "DELETE FROM meddata WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(DBService.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

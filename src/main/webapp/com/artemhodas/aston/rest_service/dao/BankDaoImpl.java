package com.artemhodas.aston.rest_service.dao;

import com.artemhodas.aston.rest_service.models.Bank;
import com.artemhodas.aston.rest_service.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankDaoImpl implements BankDao {


    @Override
    public Bank saveBank(Bank bank) {
        String sql = """
                INSERT INTO banks(id_bank,name,location)
                VALUES (?,?,?);
                """;
        try (Connection connection = ConnectionManager.open();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            {
                statement.setLong(1, bank.getIdBank());
                statement.setString(2, bank.getName());
                statement.setString(3, bank.getLocation());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return bank;
    }

    @Override
    public void deleteBank(int id) {
        String sql = """
                DELETE FROM banks WHERE id_bank = ?
                """;
        try (Connection connection = ConnectionManager.open();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int deleted = statement.executeUpdate();
            if (deleted != 0) {
                System.out.println("Банк  под id " + id + " был удален");
            } else {
                System.out.println("Такого id не найдено");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateBank(Bank bank) {
        String sql = """
                UPDATE banks SET name=?,location =? WHERE id_bank=?""";
        try (Connection connection = ConnectionManager.open();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bank.getName());
            statement.setString(2, bank.getLocation());
            statement.setInt(3, bank.getIdBank());
            int rowsUpdated = statement.executeUpdate();
            if(rowsUpdated==0){
                throw new RuntimeException();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Bank getBankyId(int idBank) {
        String sql = """
                SELECT * FROM banks WHERE id_bank=?
                """;

        try (Connection connection = ConnectionManager.open();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idBank);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Bank(
                            resultSet.getInt("id_bank"),
                            resultSet.getString("name"),
                            resultSet.getString("location")
                    );

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Bank> getAllBanks() {
        List<Bank> allBanks = new ArrayList<>();
        String sql = """
                SELECT * FROM banks
                """;
        try (Connection connection = ConnectionManager.open();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while ((resultSet.next())) {
                Bank bank = new Bank();
                bank.setIdBank(resultSet.getInt("id_bank"));
                bank.setName(resultSet.getString("name"));
                bank.setLocation(resultSet.getString("location"));

                allBanks.add(bank);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allBanks;
    }
}

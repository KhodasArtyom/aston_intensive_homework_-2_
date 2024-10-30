package com.artemhodas.aston.rest_service.dao;

import com.artemhodas.aston.rest_service.models.Bank;
import com.artemhodas.aston.rest_service.models.Client;
import com.artemhodas.aston.rest_service.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements ClientDao {


    @Override
    public void saveClient(Client client) {
        String sql = """
                INSERT into clients(id_client,first_name,last_name,fk_bank_id)
                VALUES (?,?,?,?);
                """;
        try (Connection connection = ConnectionManager.open();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, client.getClientId());
            statement.setString(2, client.getFirstName());
            statement.setString(3, client.getLastName());
            statement.setInt(4, client.getBank().getIdBank());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteClient(int id) {
        String sql = """
                DELETE FROM clients WHERE id_client = ?
                """;
        try (Connection connection = ConnectionManager.open();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int deleted = statement.executeUpdate();
            if (deleted != 0) {
                System.out.println("Клиент под id = " + id + " был удален");
            } else {
                System.out.println("Такого id не найдено");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Client getClientById(int id) {
        String sql = """
                SELECT * FROM clients
                WHERE id_client= ?
                """;
        try (Connection connection = ConnectionManager.open();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try {
                ResultSet resultSet = statement.executeQuery();

                {
                    if (resultSet.next()) {
                        int clientId = resultSet.getInt("id_client");
                        String firstName = resultSet.getString("first_name");
                        String lastName = resultSet.getString("last_name");
                        int bankId = resultSet.getInt("fk_bank_id");
                        Bank bank = new Bank(bankId, null, null);
                        return new Client(clientId, firstName, lastName, bank);

                    }
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
    public void updateClient(Client client) {
        String sql = """
                UPDATE clients SET first_name=?,last_name=?,fk_bank_id=?
                WHERE id_client=?""";
        try (Connection connection = ConnectionManager.open();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getFirstName());
            statement.setString(2, client.getLastName());
            statement.setInt(3, client.getBank().getIdBank());
            statement.setInt(4, client.getClientId());
            statement.executeUpdate();
            {
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Client> showAllClients() {
        List<Client> allClients = new ArrayList<>();
        String sql = """
                SELECT * FROM clients
                """;
        try(Connection connection = ConnectionManager.open();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int clientId = resultSet.getInt("id_client");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int bankId = resultSet.getInt("fk_bank_id");
                Bank bank = new Bank(bankId, null, null);
                allClients.add(new Client(clientId, firstName, lastName, bank));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allClients;
    }
}


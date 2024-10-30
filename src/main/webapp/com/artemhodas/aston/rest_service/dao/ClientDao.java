package com.artemhodas.aston.rest_service.dao;

import com.artemhodas.aston.rest_service.models.Client;

import java.util.List;

public interface ClientDao {

    public void saveClient(Client client);

    public void deleteClient(int id);

    public Client getClientById(int id);

    public void updateClient(Client client);

    public List<Client> showAllClients();
}

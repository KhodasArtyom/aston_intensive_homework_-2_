package com.artemhodas.aston.rest_service.service;

import com.artemhodas.aston.rest_service.models.Bank;
import com.artemhodas.aston.rest_service.models.Client;

import java.util.List;

public interface ClientService {

    public Client saveClient(Client client);

    public void deleteClient(int id);

    public void updateClient(Client client);

    public Client getClientById(int id);

    public List<Client> showAllClients();
}

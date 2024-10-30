package com.artemhodas.aston.rest_service.service;

import com.artemhodas.aston.rest_service.dao.ClientDao;
import com.artemhodas.aston.rest_service.models.Client;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    private final ClientDao clientDao;

    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public Client saveClient(Client client) {
        try {
            clientDao.saveClient(client);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return client;
    }

    @Override
    public void deleteClient(int id) {

        try {
            clientDao.deleteClient(id);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateClient(Client client) {

        try {
            clientDao.updateClient(client);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Client getClientById(int id) {
        try {
            return clientDao.getClientById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Client> showAllClients() {
        return clientDao.showAllClients();
    }
}

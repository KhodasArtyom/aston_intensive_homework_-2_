package com.artemhodas.aston.rest_service.controllers;

import com.artemhodas.aston.rest_service.dao.ClientDaoImpl;
import com.artemhodas.aston.rest_service.models.Client;
import com.artemhodas.aston.rest_service.service.ClientService;
import com.artemhodas.aston.rest_service.service.ClientServiceImpl;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/clients")
public class ClientServlet extends HttpServlet {
    private final ClientService clientService;
    private final Gson gson;


    public ClientServlet() {
        this.clientService = new ClientServiceImpl(new ClientDaoImpl());
        this.gson = new Gson();


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Client client = new Gson().fromJson(req.getReader(),Client.class);

        try{clientService.updateClient(client);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (RuntimeException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        try {
            Client client = gson.fromJson(req.getReader(), Client.class);
            clientService.saveClient(client);
            out.print(gson.toJson(client));
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print(gson.toJson("Error " + e.getMessage()));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        String idParam = req.getParameter("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            try {
                clientService.deleteClient(id);
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } catch (RuntimeException e) {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID не указан");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        String action = req.getParameter("actions");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        try {
            if ("getAll".equals(action)) {
                List<Client> clients = clientService.showAllClients();
                String json = gson.toJson(clients);
                out.print(json);
            } else if ("getById".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                Client clientById = clientService.getClientById(id);
                String json = gson.toJson(clientById);
                out.print(json);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            out.flush();
        }
    }
}

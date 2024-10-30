package com.artemhodas.aston.rest_service.controllers;

import com.artemhodas.aston.rest_service.dao.BankDaoImpl;
import com.artemhodas.aston.rest_service.models.Bank;
import com.artemhodas.aston.rest_service.service.BankService;
import com.artemhodas.aston.rest_service.service.BankServiceImpl;
import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/banks")
public class BankServlet extends HttpServlet {
    private final BankService bankService;
    private final Gson gson;

    public BankServlet() {
        this.bankService = new BankServiceImpl(new BankDaoImpl());
        this.gson = new Gson();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        try {
            Bank bank = gson.fromJson(req.getReader(), Bank.class);
            bankService.saveBank(bank);
            out.print(gson.toJson(bank));

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
                bankService.deleteBank(id);
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } catch (RuntimeException e) {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID не указан");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        Bank bank = new Gson().fromJson(req.getReader(), Bank.class);
        try {
            bankService.updateBank(bank);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (RuntimeException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        String action = req.getParameter("action");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        try {
            if ("getAll".equals(action)) {
                List<Bank> banks = bankService.showAllBanks();
                String json = gson.toJson(banks);
                out.print(json);
            } else if ("getById".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                Bank bankById = bankService.getBankById(id);
                String json = gson.toJson(bankById);
                out.print(json);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            out.flush();
        }




    }
}

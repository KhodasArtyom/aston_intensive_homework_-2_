package com.artemhodas.aston.rest_service.controllers;

import com.artemhodas.aston.rest_service.dao.BankDaoImpl;
import com.artemhodas.aston.rest_service.models.Bank;
import com.artemhodas.aston.rest_service.service.BankService;
import com.artemhodas.aston.rest_service.service.BankServiceImpl;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

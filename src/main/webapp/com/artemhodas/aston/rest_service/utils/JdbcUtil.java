package com.artemhodas.aston.rest_service.utils;

import com.artemhodas.aston.rest_service.dao.BankDao;
import com.artemhodas.aston.rest_service.dao.BankDaoImpl;
import com.artemhodas.aston.rest_service.dao.ClientDao;
import com.artemhodas.aston.rest_service.dao.ClientDaoImpl;
import com.artemhodas.aston.rest_service.models.Bank;
import com.artemhodas.aston.rest_service.service.BankService;
import com.artemhodas.aston.rest_service.service.BankServiceImpl;

import java.sql.Connection;

public class JdbcUtil {
    public static void main(String[] args) {
       Connection connection = ConnectionManager.open();
        BankDao bankDao = new BankDaoImpl();
        Bank bank = new Bank(3, "Приорбанк", "New York");
        BankService service = new BankServiceImpl(bankDao);
        service.updateBank(bank);

//        //bankDao.saveBank(bank);
//        System.out.println(bankDao.getBankyId(2));
//        //clientDao.saveClient(client);
//        System.out.println(clientDao.getClientById(3));
//        System.out.println(bankDao.getAllBanks());




    }
}

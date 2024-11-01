package com.artemhodas.aston.rest_service.service;

import com.artemhodas.aston.rest_service.dao.BankDao;
import com.artemhodas.aston.rest_service.models.Bank;

import java.util.List;

public class BankServiceImpl implements BankService{

    private final BankDao bankDao;

    public BankServiceImpl(BankDao bankDao) {
        this.bankDao = bankDao;
    }



    @Override
    public Bank saveBank(Bank bank) {
        try {
            bankDao.saveBank(bank);
        } catch (Exception e) {
            throw new RuntimeException("Can't add the bank",e);
        }
        return bank;
    }

    @Override
    public void deleteBank(int id) {
        try {
            bankDao.deleteBank(id);
        } catch (Exception e) {
            throw new RuntimeException("Can't delete the bank",e);
        }
    }

    @Override
    public void updateBank(Bank bank) {
        try {
            bankDao.updateBank(bank);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Bank getBankById(int id) {
        try {
            return bankDao.getBankyId(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Bank> showAllBanks() {
        return bankDao.getAllBanks();
    }
}

package com.artemhodas.aston.rest_service.dao;

import com.artemhodas.aston.rest_service.models.Bank;

import java.util.List;

public interface BankDao {

    public Bank saveBank(Bank bank);

    public void deleteBank(int id);

    public void updateBank(Bank bank);

    public Bank getBankyId(int idBank);

    public List<Bank> getAllBanks();


}

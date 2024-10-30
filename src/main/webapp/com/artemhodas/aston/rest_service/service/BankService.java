package com.artemhodas.aston.rest_service.service;

import com.artemhodas.aston.rest_service.models.Bank;

import java.util.List;

public interface BankService {

    public Bank saveBank(Bank bank);

    public void deleteBank(int id);

    public void updateBank(Bank bank);

    public Bank getBankById(int id);

    public List<Bank> showAllBanks();


}

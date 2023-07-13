package com.example.assignmentjava6.service;

import com.example.assignmentjava6.entity.Account;
import com.example.assignmentjava6.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AccountService {

    Account findById(String username);

    List<Account> getAddmin();

    List<Account> findAll();
}

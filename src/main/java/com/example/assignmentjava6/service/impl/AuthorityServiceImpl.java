package com.example.assignmentjava6.service.impl;

import com.example.assignmentjava6.entity.Account;
import com.example.assignmentjava6.entity.Authority;
import com.example.assignmentjava6.repository.AccountRepository;
import com.example.assignmentjava6.repository.AuthorityRepository;
import com.example.assignmentjava6.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    AccountRepository accountRepository;
    @Override
    public List<Authority> getAddmin() {
        List<Account> accounts = accountRepository.getAdmin();
        return authorityRepository.authoritiesOf(accounts);
    }

    @Override
    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }

    @Override
    public Authority create(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Override
    public void delete(Integer id) {
        authorityRepository.deleteById(id);
    }
}

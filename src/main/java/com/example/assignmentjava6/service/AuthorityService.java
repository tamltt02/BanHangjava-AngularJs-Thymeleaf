package com.example.assignmentjava6.service;

import com.example.assignmentjava6.entity.Authority;

import java.util.List;

public interface AuthorityService {
    List<Authority> getAddmin();

    List<Authority> findAll();

    Authority create(Authority authority);

    void delete(Integer id);
}

package com.example.assignmentjava6.service.impl;

import com.example.assignmentjava6.entity.Role;
import com.example.assignmentjava6.repository.RoleRepository;
import com.example.assignmentjava6.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

}

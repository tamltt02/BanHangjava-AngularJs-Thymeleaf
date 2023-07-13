package com.example.assignmentjava6.restController;

import com.example.assignmentjava6.entity.Account;
import com.example.assignmentjava6.entity.Role;
import com.example.assignmentjava6.service.AccountService;
import com.example.assignmentjava6.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {

    @Autowired
    AccountService accountService;

    @GetMapping()
    public List<Account> getAccount(@RequestParam("admin")Optional<Boolean> admin) {
        if (admin.orElse(false)) {
            return accountService.getAddmin();
        }
        return accountService.findAll();
    }
}

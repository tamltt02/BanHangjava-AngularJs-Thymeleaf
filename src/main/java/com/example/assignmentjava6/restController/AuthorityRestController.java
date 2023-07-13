package com.example.assignmentjava6.restController;

import com.example.assignmentjava6.entity.Account;
import com.example.assignmentjava6.entity.Authority;
import com.example.assignmentjava6.service.AccountService;
import com.example.assignmentjava6.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/authorities")
public class AuthorityRestController {

    @Autowired
    AuthorityService authorityService;

    @GetMapping()
    public List<Authority> getAuthority(@RequestParam("admin")Optional<Boolean> admin) {
        if (admin.orElse(false)) {
            System.out.println("a");
            return authorityService.getAddmin();
        }
        return authorityService.findAll();
    }

    @PostMapping
    public  Authority post(@RequestBody Authority authority){
        return authorityService.create(authority);
    }

    @DeleteMapping("{id}")
    public  void delete(@PathVariable("id") Integer id){
         authorityService.delete(id);
    }
}

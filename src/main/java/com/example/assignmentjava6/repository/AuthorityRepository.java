package com.example.assignmentjava6.repository;

import com.example.assignmentjava6.entity.Account;
import com.example.assignmentjava6.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Integer> {

    @Query("select DISTINCT a from Authority a where  a.account in ?1")
    List<Authority> authoritiesOf(List<Account> accounts);
}

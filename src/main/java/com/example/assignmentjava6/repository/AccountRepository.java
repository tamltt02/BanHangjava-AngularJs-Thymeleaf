package com.example.assignmentjava6.repository;

import com.example.assignmentjava6.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {

    @Query("SELECT DISTINCT a.account from Authority a where a.role.id in ('DIRE','STAFF')")
    List<Account> getAdmin();
}

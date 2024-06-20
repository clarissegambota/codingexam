package com.codingexam.demo.api.savings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsRepository extends JpaRepository<Savings, Long> {

    @Query("SELECT s FROM Savings s WHERE s.account.customerNumber = ?1")
    Savings findByCustomerNumber(Long customerNumber);
}

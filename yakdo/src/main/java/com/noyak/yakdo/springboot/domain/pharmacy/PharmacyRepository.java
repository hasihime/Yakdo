package com.noyak.yakdo.springboot.domain.pharmacy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Integer> {

    @Query("SELECT p FROM Pharmacy p WHERE p.p_x=:p_x and p.p_y=:p_y")
    List<Pharmacy> findAllDesc(@Param("p_x") double p_x, @Param("p_y") double p_y);

}


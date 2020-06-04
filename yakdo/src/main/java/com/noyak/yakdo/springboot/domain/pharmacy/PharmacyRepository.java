package com.noyak.yakdo.springboot.domain.pharmacy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Integer> {

    //이런 쿼리로 받아서 spring에서 거리별 정렬 후에
    //vuex에 저장해서 버튼에 따라 반응하는 형식으로 갈게
    //select *
    //from pharmacy
    //where p_x between 36.50 and 36.55
    //and p_y between 127.20 and 127.25;
    @Query("SELECT p FROM Pharmacy p WHERE p.p_x between :p_x - 0.035 and :p_x +0.035 and p.p_y between :p_y - 0.035 and :p_y + 0.035")
    List<Pharmacy> findByPosition(@Param("p_x") double p_x, @Param("p_y") double p_y);

    @Query(value="SELECT p FROM Pharmacy p WHERE p.p_addr like %:address%", nativeQuery = false)
    List<Pharmacy> findWithAddress(@Param("address") String address);

}


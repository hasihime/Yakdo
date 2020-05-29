package com.noyak.yakdo.springboot.domain.review;

import com.noyak.yakdo.springboot.domain.pharmacy.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query(value = "select r.r_id from Review r where r.createdDate = (select max(r2.createdDate) from Review r2 where r2.p_id=:p_id)", nativeQuery = false)
    Long findReviewById(@Param("p_id") Pharmacy p_id);

    @Query(value="select r from Review r where r.p_id=:p_id order by r.createdDate desc", nativeQuery=false)
    List<Review> findByPharmacy(@Param("p_id") Pharmacy p_id);
}

package com.noyak.yakdo.springboot.domain.pharmacy;

import com.noyak.yakdo.springboot.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Integer> {
}

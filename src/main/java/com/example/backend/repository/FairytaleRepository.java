package com.example.backend.repository;


import com.example.backend.entity.Fairytale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FairytaleRepository extends JpaRepository<Fairytale, Long> {
}

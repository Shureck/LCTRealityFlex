package com.example.recserv.repository;

import com.example.recserv.entity.Servs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServsRepository extends JpaRepository<Servs, Integer> {
    Servs findALLById(Integer id);
}

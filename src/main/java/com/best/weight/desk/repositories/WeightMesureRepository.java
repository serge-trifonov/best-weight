package com.best.weight.desk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.best.weight.desk.entities.WeightMesure;

public interface WeightMesureRepository extends JpaRepository<WeightMesure, Long> {
}

package com.prac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prac.entity.City;

public interface CityRepository extends JpaRepository<City, Integer>{
    public List<City> findCitiesByStateId(Integer stateId);
}

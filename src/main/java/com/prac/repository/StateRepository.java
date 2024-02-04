package com.prac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prac.entity.State;

public interface StateRepository extends JpaRepository<State, Integer>{
    public List<State> findStatesByCountryId(Integer countryId);
}

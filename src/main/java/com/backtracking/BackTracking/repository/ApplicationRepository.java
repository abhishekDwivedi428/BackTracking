package com.backtracking.BackTracking.repository;

import com.backtracking.BackTracking.entity.Application;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

//      @Query(value = "select * from Application where name = :name", nativeQuery = true)
//      List<Application> findAllAppByName(@Param("name") String name);

      @Query(value = "select app from Application app where app.name = :name")
      List<Application> findAllAppByName(@Param("name") String name);

//    @Query("select app from Applications")
//    List<Application> findApplications(Sort sort);
}

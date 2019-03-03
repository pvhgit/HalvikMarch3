package com.prashant.domainjpa.repository;

import com.prashant.domainjpa.data.Patent;
import com.prashant.domainjpa.data.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface PatentRepository extends JpaRepository<Patent, String> {

    @Query("select p from Patent p where p.status = :status and p.createdTime < :createdTime")
    List<Patent> findByStatusAndCreatedTimeIsAfter(
            @Param("status") Status status,
            @Param("createdTime") Date createdTime);
}

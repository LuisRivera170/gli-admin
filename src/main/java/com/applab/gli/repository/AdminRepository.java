package com.applab.gli.repository;

import com.applab.gli.domain.Admin;
import com.applab.gli.enumeration.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query("SELECT a FROM Admin a WHERE LOWER(CONCAT(a.name, ' ', a.lastName)) LIKE LOWER(CONCAT('%', :fullName,'%')) AND a.area.id = :areaId AND a.status = :status")
    Page<Admin> findByFullNameAndAreaAndStatus(@Param("fullName") String fullName, @Param("areaId") Long areaId, @Param("status") Status status, Pageable pageable);

    @Query("SELECT a FROM Admin a WHERE LOWER(CONCAT(a.name, ' ', a.lastName)) LIKE LOWER(CONCAT('%', :fullName,'%')) AND a.area.id = :areaId")
    Page<Admin> findByFullNameAndArea(@Param("fullName") String fullName, @Param("areaId") Long areaId, Pageable pageable);

    @Query("SELECT a FROM Admin a WHERE LOWER(CONCAT(a.name, ' ', a.lastName)) LIKE LOWER(CONCAT('%', :fullName,'%')) AND a.status = :status")
    Page<Admin> findByFullNameAndStatus(@Param("fullName") String fullName, @Param("status") Status status, Pageable pageable);

    @Query("SELECT a FROM Admin a WHERE LOWER(CONCAT(a.name, ' ', a.lastName)) LIKE LOWER(CONCAT('%', :fullName,'%'))")
    Page<Admin> findByFullName(@Param("fullName") String fullName, Pageable pageable);

    @Query("SELECT a FROM Admin a WHERE a.area.id = :areaId AND a.status = :status")
    Page<Admin> findByAreaAndStatus(@Param("areaId") Long areaId,@Param("status") Status status, Pageable pageable);

    @Query("SELECT a FROM Admin a WHERE a.area.id = :areaId")
    Page<Admin> findByArea(@Param("areaId") Long areaId, Pageable pageable);

    @Query("SELECT a FROM Admin a WHERE a.status = :status")
    Page<Admin> findByStatus(@Param("status") Status status, Pageable pageable);

}

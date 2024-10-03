package com.kunal.exam_portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kunal.exam_portal.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

}

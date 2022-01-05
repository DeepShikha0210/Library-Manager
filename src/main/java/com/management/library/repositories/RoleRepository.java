package com.management.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.library.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	

}

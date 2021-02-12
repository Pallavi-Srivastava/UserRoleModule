package com.hm.productclaim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hm.productclaim.model.UserDetails;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Integer> {
	
}

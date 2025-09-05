package com.example.oca42.repository;

import com.example.oca42.entity.Address;
import com.example.oca42.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {


}

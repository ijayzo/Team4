package com.example.demo.DAO;

import com.example.demo.Models.PackageSignUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PackageSignUpRepository extends JpaRepository<PackageSignUp, Integer> {
    List<PackageSignUp> findPackageSignUpByPackageId(int packageId);
    List<PackageSignUp> findPackageSignUpByUsername(String username);
    Optional<PackageSignUp> findPackageSignUpByPackageSignUpId(int packageSignUpId);
}

package com.example.demo.DAO;

import com.example.demo.Models.PackageHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/** DataJPA Implementation for PackageHistory Table
 *
 */
public interface PackageHistoryDataJPA extends JpaRepository <PackageHistory, Integer> {



}

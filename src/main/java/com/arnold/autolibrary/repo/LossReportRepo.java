package com.arnold.autolibrary.repo;

import com.arnold.autolibrary.model.LossReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LossReportRepo extends JpaRepository<LossReport,Integer> {
    
}

package com.arnold.autolibrary.repo;

import com.arnold.autolibrary.model.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRecordRepo extends JpaRepository<BorrowRecord,Integer> {

}

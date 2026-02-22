package com.shield.website.repository;

import com.shield.website.model.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {
    List<Enquiry> findAllByOrderBySubmittedAtDesc();
    List<Enquiry> findByStatus(String status);
}

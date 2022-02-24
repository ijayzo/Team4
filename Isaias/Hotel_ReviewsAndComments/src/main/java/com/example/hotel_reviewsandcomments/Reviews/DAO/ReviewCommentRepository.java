package com.example.hotel_reviewsandcomments.Reviews.DAO;

import com.example.hotel_reviewsandcomments.Hotels.Models.HotelPartner;
import com.example.hotel_reviewsandcomments.Reviews.Models.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Integer> {
    public List<ReviewComment> getAllByEmployeePackageId(Integer employee_package_id);
}
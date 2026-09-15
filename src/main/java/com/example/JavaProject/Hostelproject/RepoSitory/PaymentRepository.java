package com.example.JavaProject.Hostelproject.RepoSitory;

import com.example.JavaProject.Hostelproject.Entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity,String> {
}

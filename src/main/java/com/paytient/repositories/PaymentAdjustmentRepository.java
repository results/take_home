package com.paytient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paytient.entities.PaymentAdjustment;
import java.lang.String;
import java.util.List;


@Repository
public interface PaymentAdjustmentRepository extends JpaRepository<PaymentAdjustment, Long> {

	List<PaymentAdjustment> findByAdjustmentCode(String adjustmentcode);

}


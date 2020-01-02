package com.isobar.rentalservice.Repositories;

import com.isobar.rentalservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {}

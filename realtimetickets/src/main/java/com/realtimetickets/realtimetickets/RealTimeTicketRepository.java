package com.realtimetickets.realtimetickets;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RealTimeTicketRepository extends JpaRepository<Ticket, Long> {
}

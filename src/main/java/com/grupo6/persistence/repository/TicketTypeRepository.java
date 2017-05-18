package com.grupo6.persistence.repository;

import org.springframework.stereotype.Repository;

import com.grupo6.persistence.model.TicketType;

@Repository
public interface TicketTypeRepository extends BaseRepository <TicketType, Integer> {

}

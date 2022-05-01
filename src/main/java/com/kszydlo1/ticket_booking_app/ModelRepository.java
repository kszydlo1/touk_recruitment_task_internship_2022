package com.kszydlo1.ticket_booking_app;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ModelRepository extends CrudRepository<Model, Long> {

    List<Model> findByContent(String content);
    List<Model> findAll();

    Model findById(int id);
}

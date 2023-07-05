package com.example.demo.controller;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Messages;

public interface MessagesRepository extends JpaRepository<Messages, Integer>{

}

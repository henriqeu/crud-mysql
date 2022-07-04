package com.example.crudmysql.model;

import org.springframework.data.repository.CrudRepository;


// Essa interface é AUTO IMPLEMENTADA pelo Spring no Bean chamada de UserRepository
//CRUD - Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

}
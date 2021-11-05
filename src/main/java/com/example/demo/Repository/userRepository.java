package com.example.demo.Repository;

import com.example.demo.Model.userModel;
import org.springframework.data.repository.CrudRepository;

public interface userRepository extends CrudRepository<userModel, Integer> {

    public userModel findByUsername(String username);
}

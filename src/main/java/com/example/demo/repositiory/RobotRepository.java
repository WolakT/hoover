package com.example.demo.repositiory;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by RENT on 2017-08-23.
 */
public interface RobotRepository extends MongoRepository<RequestResponse, String>{

}

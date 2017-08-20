package com.example.demo.service;

import com.example.demo.dto.RobotRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Tomcio on 2017-08-20.
 */

@Service
public class RobotService {

    public String doSomething(RobotRequest robotRequest){
        return "something done";
    }
}

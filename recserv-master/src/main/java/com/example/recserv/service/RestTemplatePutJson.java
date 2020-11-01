package com.example.recserv.service;

import com.example.recserv.dto.Ids;
import com.example.recserv.repository.StudentRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class RestTemplatePutJson {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    StudentRepository studentRepository;

    public String putJson(Integer id) {
        Gson gson = new GsonBuilder().create();
        String payLoadStr = gson.toJson(studentRepository.findALLById(id));
        if (payLoadStr.contains("платно")) {
            payLoadStr = payLoadStr.replace("платно", "1");
        } else {
            payLoadStr = payLoadStr.replace("бесплатно", "0");
        }
        if (payLoadStr.contains("Мужской")) {
            payLoadStr = payLoadStr.replace("Мужской", "1");
        } else {
            payLoadStr = payLoadStr.replace("Женский", "0");
        }
        if (payLoadStr.contains("Индивидуальное")) {
            payLoadStr = payLoadStr.replace("Индивидуальное", "1");
        } else {
            payLoadStr = payLoadStr.replace("Общее", "0");
        }
        if (payLoadStr.contains("Индивидуальное")) {
            payLoadStr = payLoadStr.replace("Индивидуальное", "1");
        } else {
            payLoadStr = payLoadStr.replace("общее", "0");
        }
        payLoadStr= gson.toJson(payLoadStr);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("path", payLoadStr);
        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntityStr = restTemplate.exchange("http://25.101.96.46:5000/event",
                HttpMethod.GET,
                entity,
                String.class
        );


        return responseEntityStr.getBody();
    }
}

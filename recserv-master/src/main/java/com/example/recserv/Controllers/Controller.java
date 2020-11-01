package com.example.recserv.Controllers;

import com.example.recserv.dto.Ids;
import com.example.recserv.repository.ServsRepository;
import com.example.recserv.service.RestTemplatePutJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
public class Controller {

    @Autowired
    ServsRepository servsRepository;
    @Autowired
    RestTemplatePutJson restTemplateputJson;
    @PostMapping("/api")
    public @ResponseBody
    ResponseEntity<String> patch(HttpServletRequest request, @RequestParam Integer id) {
        Gson gson = new GsonBuilder().create();
        ArrayList<String> i = new ArrayList<String>();


        String payloadStr = gson.toJson(servsRepository.findALLById(Integer.parseInt(restTemplateputJson.putJson(id).toString())));
        return new ResponseEntity<String>(payloadStr, HttpStatus.OK);
    }
}

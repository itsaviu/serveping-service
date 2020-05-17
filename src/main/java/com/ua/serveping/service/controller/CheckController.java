package com.ua.serveping.service.controller;

import com.ua.serveping.service.models.dto.CheckReq;
import com.ua.serveping.service.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checks")
public class CheckController {

    @Autowired
    private CheckService checkService;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody CheckReq checkReq) {
        checkService.create(checkReq);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity fetchAll() {
        return ResponseEntity.ok(checkService.fetchAllChecks());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity test(@PathVariable Long id) {
        checkService.deleteCheck(id);
        return ResponseEntity.ok().build();
    }
}

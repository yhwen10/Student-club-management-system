package com.controller;

import com.entity.view.ShetuanrenqiView;
import com.service.ShetuanrenqiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shetuanrenqi")
public class ShetuanrenqiController {

    @Autowired
    private ShetuanrenqiService shetuanrenqiService;

    @GetMapping("/rankings")
    public List<ShetuanrenqiView> getRenqiRankings() {
        return shetuanrenqiService.getRenqiRankings();
    }
}




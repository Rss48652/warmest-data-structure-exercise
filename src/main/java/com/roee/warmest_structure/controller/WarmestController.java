package com.roee.warmest_structure.controller;

import com.roee.warmest_structure.logic.WarmestDataStructure;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WarmestController {
    
    private final WarmestDataStructure ds;

    public WarmestController(WarmestDataStructure ds) {
        this.ds = ds;
    }

    @GetMapping("/test")
    public String test() {
        return "The app is alive!";
    }
    @GetMapping("/put")
    public Integer put(@RequestParam String key, @RequestParam int value) {
        return ds.put(key, value);
    }

    @GetMapping("/get")
    public Integer get(@RequestParam String key) {
        return ds.get(key);
    }

    @DeleteMapping("/remove")
    public Integer remove(@RequestParam String key) {
        return ds.remove(key);
    }

    @GetMapping("/warmest")
    public String getWarmest() {
        return ds.getWarmest();
    }
}
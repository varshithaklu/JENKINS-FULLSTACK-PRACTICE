package com.klef.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.klef.entity.Mood;
import com.klef.service.MoodService;

@RestController
@RequestMapping("/moodapi")
@CrossOrigin(origins = "*")
public class MoodController {

    @Autowired
    private MoodService moodService;

    @GetMapping("/")
    public String home() {
        return "Mood Tracker API is running...";
    }

    @PostMapping("/add")
    public ResponseEntity<?> addMood(@RequestBody Mood mood) {
        try {
            Mood savedMood = moodService.addMood(mood);
            return new ResponseEntity<>(savedMood, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error saving mood: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Mood>> getAllMoods() {
        List<Mood> moods = moodService.getAllMoods();
        return new ResponseEntity<>(moods, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getMoodById(@PathVariable Long id) {
        Mood mood = moodService.getMoodById(id);
        if (mood != null) {
            return new ResponseEntity<>(mood, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Mood with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateMood(@RequestBody Mood mood) {
        Mood existing = moodService.getMoodById(mood.getId());
        if (existing != null) {
            try {
                Mood updatedMood = moodService.updateMood(mood);
                return new ResponseEntity<>(updatedMood, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>("Error updating mood: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Cannot update. Mood with ID " + mood.getId() + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMood(@PathVariable Long id) {
        Mood existing = moodService.getMoodById(id);
        if (existing != null) {
            moodService.deleteMoodById(id);
            return new ResponseEntity<>("Mood with ID " + id + " deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot delete. Mood with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}

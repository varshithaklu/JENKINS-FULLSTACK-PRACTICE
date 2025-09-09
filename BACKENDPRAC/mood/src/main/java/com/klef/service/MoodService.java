package com.klef.service;

import java.util.List;
import com.klef.entity.Mood;

public interface MoodService {
    Mood addMood(Mood mood);
    List<Mood> getAllMoods();
    Mood getMoodById(Long id);
    Mood updateMood(Mood mood);
    void deleteMoodById(Long id);
}

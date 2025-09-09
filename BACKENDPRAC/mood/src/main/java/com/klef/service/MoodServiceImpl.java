package com.klef.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.entity.Mood;
import com.klef.repository.MoodRepository;

@Service
public class MoodServiceImpl implements MoodService {

    @Autowired
    private MoodRepository moodRepository;

    @Override
    public Mood addMood(Mood mood) {
        return moodRepository.save(mood);
    }

    @Override
    public List<Mood> getAllMoods() {
        return moodRepository.findAll();
    }

    @Override
    public Mood getMoodById(Long id) {
        Optional<Mood> opt = moodRepository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public Mood updateMood(Mood mood) {
        return moodRepository.save(mood);
    }

    @Override
    public void deleteMoodById(Long id) {
        moodRepository.deleteById(id);
    }
}

package com.klef.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.klef.entity.Mood;

@Repository
public interface MoodRepository extends JpaRepository<Mood, Long> {
}

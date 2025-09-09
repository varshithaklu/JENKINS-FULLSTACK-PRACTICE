package com.klef.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "mood_entries")
public class Mood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "mood", nullable = false, length = 50)
    private String mood;

    @Column(name = "note", length = 255)
    private String note;

    @Column(name = "entry_date", nullable = false)
    private LocalDate entryDate = LocalDate.now();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getMood() {
        return mood;
    }
    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }
    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    @Override
    public String toString() {
        return "Mood [id=" + id + ", mood=" + mood + ", note=" + note + ", entryDate=" + entryDate + "]";
    }
}

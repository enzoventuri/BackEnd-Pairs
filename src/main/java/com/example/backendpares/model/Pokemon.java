package com.example.backendpares.model;

import com.example.backendpares.Enum.Evolution;
import com.example.backendpares.Enum.Types;
import io.swagger.v3.oas.annotations.tags.Tag;

public class Pokemon {
    private long id;
    private String name;
    private Enum<Types> type;
    private double health;
    private double attack;
    private int level;
    private int experience;
    private Enum<Evolution> evolution;
    private boolean isDowned;
    private boolean isOnBench;

    public Pokemon(String name, Enum<Types> type, double health,
                   double attack, int level, int experience, Enum<Evolution> evolution, boolean isDowned, boolean isOnBench) {
        this.name = name;
        this.type = type;
        this.health = health;
        this.attack = attack;
        this.level = level;
        this.experience = experience;
        this.evolution = evolution;
        this.isDowned = isDowned;
        this.isOnBench = isOnBench;
    }

    public Pokemon(long id, String name, Enum<Types> type, double health,
                   double attack, int level, int experience, Enum<Evolution> evolution, boolean isDowned, boolean isOnBench) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.health = health;
        this.attack = attack;
        this.level = level;
        this.experience = experience;
        this.evolution = evolution;
        this.isDowned = isDowned;
        this.isOnBench = isOnBench;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Enum<Types> getType() {
        return type;
    }

    public void setType(Enum<Types> type) {
        this.type = type;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getAttack() {
        return attack;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public Enum<Evolution> getEvolution() {
        return evolution;
    }

    public void setEvolution(Enum<Evolution> evolution) {
        this.evolution = evolution;
    }

    public boolean isDowned() {
        return isDowned;
    }

    public void setDowned(boolean downed) {
        isDowned = downed;
    }

    public boolean isOnBench() {
        return isOnBench;
    }

    public void setOnBench(boolean onBench) {
        isOnBench = onBench;
    }
}

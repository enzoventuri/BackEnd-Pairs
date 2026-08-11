package com.example.backendpares.Enum;

public enum Types {
    FIRE, WATER, ELECTRICITY, STEEL, ICE, FAIRY, DRAGON, SHADOW, ROCK, NORMAL, GRASS;

    public static Types getTypeFromName(String name) {
        for (Types t : Types.values()) {
            if (t.toString().equalsIgnoreCase(name)) {
                return t;
            }
        }

        throw new RuntimeException("Could not find Type");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

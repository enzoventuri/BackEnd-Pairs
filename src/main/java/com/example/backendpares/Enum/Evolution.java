package com.example.backendpares.Enum;

public enum Evolution {
    BASE(0), FIRST(1), SECOND(2), THIRD(3), MAX(4);

    private int id;

    Evolution(int id) {
        this.id = id;
    }

    public static Enum<Evolution> getEvolutionFromName(String name) {
        for (Evolution e : Evolution.values()) {
            if (e.toString().equalsIgnoreCase(name)) {
                return e;
            }
        }

        throw new RuntimeException("Could not find Evolution");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

package com.mycom.services;

public enum RecipePriorities {
    NORMAL("Нормальный"),
    CITO("Срочный"),
    STATIM("Немедленный");

    private String priority;
    RecipePriorities(String priority) {
        this.priority=priority;
    }
    @Override
    public String toString() {
        return priority;
    }
    public static RecipePriorities getValueOf(String s){
        if(s.equalsIgnoreCase("Нормальный")) return RecipePriorities.NORMAL;
        if(s.equalsIgnoreCase("Срочный")) return RecipePriorities.CITO;
        if(s.equalsIgnoreCase("Немедленный")) return RecipePriorities.STATIM;
        return null;
    }
}

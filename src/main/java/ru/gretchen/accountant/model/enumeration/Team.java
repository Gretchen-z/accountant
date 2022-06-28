package ru.gretchen.accountant.model.enumeration;

public enum Team {
    WHITE("WHITE"),
    GREY("GREY"),
    BLACK("BLACK"),
    BROWN("BROWN"),
    RED("RED"),
    BLUE("BLUE"),
    YELLOW("YELLOW"),
    ORANGE("ORANGE"),
    GREEN("GREEN"),
    VIOLET("VIOLET"),
    PINK("PINK");

    private final String team;

    Team(final String team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return team;
    }
}

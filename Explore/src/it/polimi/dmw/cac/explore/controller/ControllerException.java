package it.polimi.dmw.cac.explore.controller;

public class ControllerException extends Exception {

    private static final long serialVersionUID = -1213202158442996300L;
    private Type type;

    public ControllerException(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public static enum Type {
        DUPLICATE_ENTITY, MISSING_ENTITY, WRONG_PASSWORD
    }
}

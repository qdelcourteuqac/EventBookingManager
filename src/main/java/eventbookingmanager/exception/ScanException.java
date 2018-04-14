package main.java.eventbookingmanager.exception;

public class ScanException extends Exception {

    protected Type type;

    public ScanException(Type type) {
        this.type = type;
    }

    public enum Type {
        EXCEPTION("Exception non traité pour le moment par l'api"),
        UNKNOWN_PERSON("La personne est inconnue"),
        ALREADY_SCANNED("Cette personne a été déjà scannée"),
        ACCESS_DENIED("Cette personne n'a pas le droit de passer");

        protected String message;

        Type(String message) {
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }
    }
}

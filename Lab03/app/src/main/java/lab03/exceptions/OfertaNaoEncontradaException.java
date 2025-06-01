package lab03.exceptions;

public class OfertaNaoEncontradaException extends RuntimeException {
    public OfertaNaoEncontradaException(String message) {
        super(message);
    }
}

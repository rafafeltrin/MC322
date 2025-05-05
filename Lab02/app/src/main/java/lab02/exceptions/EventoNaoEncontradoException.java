package lab02.exceptions;


//Vai ter que ser utilizado em algum lugar
public class EventoNaoEncontradoException extends Exception {
    public EventoNaoEncontradoException(String message) {
        super(message);
    }
}
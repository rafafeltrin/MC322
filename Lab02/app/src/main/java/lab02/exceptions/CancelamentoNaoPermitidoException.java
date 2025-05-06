//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)
package lab02.exceptions;

/**
 * Exceção lançada quando o cancelamento de um ingresso não é permitido.
 * Essa exceção pode ser lançada em situações específicas, como quando
 * o ingresso já foi utilizado ou quando o evento associado ao ingresso
 * não permite cancelamentos.
 */
public class CancelamentoNaoPermitidoException extends Exception {
    public CancelamentoNaoPermitidoException(String message) {
        super(message);
    }
}

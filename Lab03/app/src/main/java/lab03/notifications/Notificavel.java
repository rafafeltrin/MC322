//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

package lab03.notifications;

/**
 * Contrato para entidades capazes de enviar notificações a clientes.
 */
public interface Notificavel {
    /**
     * Notifica o cliente com a mensagem fornecida.
     * @param mensagem A mensagem a ser enviada ao cliente.
     */
    void notificar(String mensagem);
}

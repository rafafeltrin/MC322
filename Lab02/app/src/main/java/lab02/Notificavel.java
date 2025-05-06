package lab02;

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

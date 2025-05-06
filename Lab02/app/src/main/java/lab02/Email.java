//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

package lab02;

import lab02.exceptions.EnderecoEmailInvalidoException;

/**
 * Canal de notificação por email
 */
public class Email implements Notificavel{
    private final String enderecoEmail;

    /**
     * Constrói um canal de email válido.
     * @param enderecoEmail endereço de email do cliente
     * @throws EnderecoEmailInvalidoException se o formato do email for inválido
     */
    public Email(String enderecoEmail) throws EnderecoEmailInvalidoException {
        if (enderecoEmail == null || !enderecoEmail.contains("@")) {
            throw new EnderecoEmailInvalidoException("Endereço de email inválido.");
        }
        this.enderecoEmail = enderecoEmail;
    }

    public String getEnderecoEmail() {
        return enderecoEmail;
    }

    /**
     * Envia a mensagem via email.
     * @param mensagem conteúdo da notificação
     */
    @Override
    public void notificar(String mensagem) {
        System.out.println("Enviando EMAIL para: " + enderecoEmail + "\n" + mensagem);
    }
}

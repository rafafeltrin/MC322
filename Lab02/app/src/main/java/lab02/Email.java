package lab02;

import lab02.exceptions.EnderecoEmailInvalidoException;

public class Email implements notificavel{
    private final String enderecoEmail;

    public Email(String enderecoEmail) throws EnderecoEmailInvalidoException {
        if (enderecoEmail == null || !enderecoEmail.contains("@")) {
            throw new EnderecoEmailInvalidoException("Endereço de email inválido.");
        }
        this.enderecoEmail = enderecoEmail;
    }

    public String getEnderecoEmail() {
        return enderecoEmail;
    }

    @Override
    public void notificar(String mensagem) {
        System.out.println("Enviando EMAIL para: " + enderecoEmail + "\n" + mensagem);
    }
}

//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab02;

import java.util.ArrayList;
import java.util.List;

import lab02.exceptions.CancelamentoNaoPermitidoException;
import lab02.exceptions.IngressoNaoEncontradoException;

/**
 * Representa um cliente que pode adquirir ingressos e receber notificações.
 */
public class Cliente implements Comparable<Cliente>{

    private String nome;
    private String email;
    private List<Ingresso> ingressos = new ArrayList<>();
    private List<Notificavel> canaisComunicacao = new ArrayList<>();

    /**
     * Construtor da classe cliente
     * @param nome o nome do cliente
     * @param email o email do cliente
     */
    public Cliente(String nome, String email){
        this.nome = nome;
        this.email = email;
    }

    /**
     * Retorna o nome do cliente
     * @return o nome do cliente
     */
    public String getNome(){
        return nome;
    }

    /**
     * Altera o nome do evento para `nome` 
     * @param nome o novo nome do cliente
     */
    public void setNome(String nome){
        this.nome = nome;
    }

    /**
     * Retorna o email do cliente
     * @return o email do cliente
     */
    public String getEmail(){
        return email;
    }

    /**
     * Altera o email do cliente para `email` 
     * @param email o novo email do cliente
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Adiciona um ingresso ao cliente.
     * @param ingresso ingresso a ser adicionado
     */
    public void adicionarIngresso(Ingresso ingresso) {
        this.ingressos.add(ingresso);
    }

    /**
     * Adiciona múltiplos ingressos ao cliente.
     * @param ingressos lista de ingressos a serem adicionados
     */
    public void adicionarIngresso(List<Ingresso> ingressos) {
        for (Ingresso ingresso : ingressos) {
            this.adicionarIngresso(ingresso);
        }
    }

    /**
     * Remove um ingresso do cliente.
     * @param ingresso ingresso a ser removido
     * @throws IngressoNaoEncontradoException se o ingresso não estiver na lista
     */
    public void removerIngresso(Ingresso ingresso) throws IngressoNaoEncontradoException {
        if (this.ingressos.contains(ingresso)) {
            this.ingressos.remove(ingresso);
        } else {
            throw new IngressoNaoEncontradoException("Ingresso não encontrado na lista de ingressos do cliente.");
        }
    }

    /**
     * Retorna a lista de ingressos do cliente.
     * @return lista de ingressos
     */
    public List<Ingresso> getIngressos() {
        return this.ingressos;
    }

    /**
     * Cancela um ingresso, respeitando a política de cancelamento.
     * @param ingresso ingresso a ser cancelado
     * @throws IngressoNaoEncontradoException se o ingresso não estiver na lista
     * @throws CancelamentoNaoPermitidoException se o ingresso não permitir cancelamento
     */
    public void cancelarIngresso(Ingresso ingresso) throws IngressoNaoEncontradoException, CancelamentoNaoPermitidoException {
        if (this.ingressos.contains(ingresso)) {
            if (ingresso.aceitaCancelamento()) {
                this.removerIngresso(ingresso);
            } else {
                throw new CancelamentoNaoPermitidoException("Ingresso não pode ser cancelado.");
            }
        } else {
            throw new IngressoNaoEncontradoException("Ingresso não encontrado na lista de ingressos do cliente.");
        }
    }

    /**
     * Compara dois clientes verificando eventos em comum.
     * @param clienteComparar cliente a ser comparado
     * @return 0 se houver evento em comum, 1 caso contrário
     */
    @Override
    public int compareTo(Cliente clienteComparar){
        for (Ingresso ingresso: ingressos){
            for (Ingresso ingressoComparar: clienteComparar.getIngressos()){
                if (ingresso.getEvento().equals(ingressoComparar.getEvento())){
                    return 0;
                }
            }
        }
        return 1;
    }

    /**
     * Adiciona um canal de comunicação para notificações.
     * @param canal canal que implementa Notificavel
     */
    public void adicionarCanalComunicacao(Notificavel canal) {
        this.canaisComunicacao.add(canal);
    }

    /**
     * Envia uma notificação por todos os canais cadastrados.
     * @param mensagem conteúdo da notificação
     */
    public void notificar(String mensagem) {
        for (Notificavel canal : canaisComunicacao) {
            canal.notificar(mensagem);
        }
    }

}
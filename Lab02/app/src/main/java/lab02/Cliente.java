/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab02;

import java.util.ArrayList;
import java.util.List;

public class Cliente implements Comparable<Cliente>{

    private String nome;
    private String email;
    private List<Ingresso> ingressos = new ArrayList<>();

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

    public void adicionarIngresso(Ingresso ingresso) {
        this.ingressos.add(ingresso);
    }

    public void adicionarIngresso(List<Ingresso> ingressos) {
        for (Ingresso ingresso : ingressos) {
            this.adicionarIngresso(ingresso);
        }
    }

    public void removerIngresso(Ingresso ingresso) throws IngressoNaoEncontradoException {
        if (this.ingressos.contains(ingresso)) {
            this.ingressos.remove(ingresso);
        } else {
            throw new IngressoNaoEncontradoException("Ingresso não encontrado na lista de ingressos do cliente.");
        }
    }

    public List<Ingresso> getIngressos() {
        return this.ingressos;
    }

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


    @Override
    public int compareTo(Cliente clienteComparar){
        for (Ingresso ingresso: ingressos){
            for (Ingresso ingressoComparar: clienteComparar.getIngressos()){
                if (ingresso.equals(ingressoComparar)){
                    return 0;
                }
            }
        }
        return 1;
    }

}
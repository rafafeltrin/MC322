/*
 * Local.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */
package lab02;

import lab02.exceptions.CapacidadeInsuficienteException;
import lab02.exceptions.LocalIndisponivelException;

/**
 * Contém a estrutura de implementação de um Local.
 * 
 * @author Gabriel Leite - 216180
 */
public class Local{
    private String nome;
    private double capacidadeMaxima;
    private boolean reservado = false;

    /**
     * Construtor da classe Local
     * @param nome o nome do local
     * @param capacidadeMaxima a capacidade máxima do local
     */
    public Local(String nome, double capacidadeMaxima){
        this.nome = nome;
        this.capacidadeMaxima = capacidadeMaxima;
    }

    /**
     * Retorna o nome do evento
     * @return o nome do evento
     */
    public String getNome(){
        return nome;
    }

    /**
     * Altera o nome do evento para `nome` 
     * @param nome o novo nome do evento
     */
    public void setNome(String nome){
        this.nome = nome;
    }

    /**
     * Retorna a capacidade do local
     * @return a capacidade do local
     */
    public double getCapacidade(){
        return capacidadeMaxima;
    }
    
    /**
     * Altera a capacidade máxima do local para `capacidadeMaxima` 
     * @param capacidadeMaxima a nova capacidade máxima do local
     */
    public void setCapacidade(double capacidadeMaxima){
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public void alocarParaEvento(Evento evento) throws LocalIndisponivelException, CapacidadeInsuficienteException{
        if(reservado == false){
            if (evento.getCapacidadeMaxima() > this.capacidadeMaxima) {
                throw new CapacidadeInsuficienteException("Capacidade do local é menor que a capacidade do evento.");
            }
            reservado = true;
            evento.setLocal(this);
        } else{
            throw new LocalIndisponivelException("Local já reservado para outro evento.");
        }
    }
}

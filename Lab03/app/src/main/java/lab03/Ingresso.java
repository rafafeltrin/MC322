//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

/*
 * Ingresso.java
 * 
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab03;

/**
 * Contém a estrutura de implementação de um Ingresso.
 */
public class Ingresso {
    private Evento evento;
    private double preco;
    private boolean aceitaCancelamento = true;

    /**
     * Construtor da classe Ingresso
     * @param preco o preço do Ingresso
     * @param evento o evento associado ao Ingresso
     */
    public Ingresso(Evento evento, double preco) {
        this.evento = evento;
        this.preco = preco;
    }

    /**
     * Retorna o preço do Ingresso
     * @return o preço do Ingresso
     */
    public double getPreco() {
        return this.preco;
    }

    /**
     * Define o evento associado ao Ingresso
     * @param evento o evento associado ao Ingresso
     */
    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    /**
     * Retorna o evento associado ao ingresso.
     * @return evento do ingresso
     */
    public Evento getEvento() {
        return evento;
    }

    /**
     * Retorna o nome do evento correspondente ao ingresso.
     * @return nome do evento
     */
    public String getNomeEventoCorrespondente() {
        return evento.getNome();
    }

    /**
     * Retorna a data do evento correspondente ao ingresso.
     * @return data do evento
     */
    public String getDataEventoCorrespondente() {
        return evento.getData();
    }

    /**
     * Indica se o ingresso aceita cancelamento.
     * @return true se cancelável, false caso contrário
     */
    public boolean aceitaCancelamento() {
        return aceitaCancelamento;
    }

    /**
     * Define a política de cancelamento do ingresso.
     * @param aceitaCancelamento true para permitir, false para não permitir
     */
    public void setAceitaCancelamento(boolean aceitaCancelamento) {
        this.aceitaCancelamento = aceitaCancelamento;
    }
}

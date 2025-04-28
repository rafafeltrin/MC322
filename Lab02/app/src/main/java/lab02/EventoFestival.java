/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab02;

import java.util.List;

public class EventoFestival extends CaracteristicaDeEvento {
        
    private List<String> lineup;
    private int duracao;

    public EventoFestival(List<String> lineup, int duracao) {
        this.lineup = lineup;
        this.duracao = duracao;
    }

    /**
    * Retorna a lista com os nomes dos artistas que se apresentarão no Festival
    * @return a lista com os nomes dos artistas do Festival
    */
    public List<String> getLineup() {
        return this.lineup;
    }
    
    /**
    * Retorna a dura o do Festival em dias
    * @return a dura o do Festival
    */
    public int getDuracao() {
        return this.duracao;
    }

    /**
     * Retorna uma string contendo a descri o do Festival, com seu nome, lineup, local e dura o
     * @return uma string com a descri o do Festival
     */
    public String getDescricao() {
        return "Lineup: " + this.lineup + " - Duração: " + this.duracao;
    }
}

//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab03;

import java.util.List;

/**
 * Representa um evento do tipo festival, que pode ter múltiplos artistas em um lineup.
 */
public class EventoFestival extends CaracteristicaDeEvento {
        
    private List<String> lineup;
    private int duracao;

    /**
     * Construtor do EventoFestival.
     * @param lineup lista de artistas do festival
     * @param duracao duração do festival em dias
     */
    public EventoFestival(List<String> lineup, int duracao) {
        this.lineup = lineup;
        this.duracao = duracao;
    }

    /**
     * Retorna a lista com os nomes dos artistas que se apresentarão no festival.
     * @return lista de nomes dos artistas
     */
    public List<String> getLineup() {
        return this.lineup;
    }
    
    /**
     * Retorna a duração do festival em dias.
     * @return duração do festival
     */
    public int getDuracao() {
        return this.duracao;
    }

    /**
     * Retorna uma string contendo a descrição do festival.
     * @return descrição do festival
     */
    public String getDescricao() {
        return "Lineup: " + this.lineup + " - Duração: " + this.duracao;
    }
}

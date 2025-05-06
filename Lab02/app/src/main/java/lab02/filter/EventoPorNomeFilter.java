//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

package lab02.filter;

import lab02.Evento;

/**
 * Filtro que seleciona eventos pelo nome, ignorando diferença de maiúsculas/minúsculas.
 */
public class EventoPorNomeFilter implements Filter<Evento> {
    private String nomeEvento;

    /**
     * Constrói um filtro que compara o nome do evento com o valor fornecido.
     *
     * @param nomeEvento o nome alvo para filtragem
     */
    public EventoPorNomeFilter(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    /**
     * Verifica se o nome do evento coincide, sem diferenciar maiúsculas/minúsculas,
     * com o nome especificado neste filtro.
     *
     * @param evento o evento a verificar
     * @return {@code true} se os nomes forem equivalentes
     */
    @Override
    public boolean matches(Evento evento) {
        return evento.getNome().equalsIgnoreCase(nomeEvento);
    }
}

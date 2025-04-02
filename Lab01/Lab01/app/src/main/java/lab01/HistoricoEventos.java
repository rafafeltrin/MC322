//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

package lab01;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa o histórico de eventos, permitindo adicionar, remover e buscar eventos com filtros
 * 
 * @author Rafael Feltrin - 276246
 */
public class HistoricoEventos {
    private List<Evento> eventos;

    /**
     * Construtor da classe HistoricoEventos.
     * Inicializa a lista de eventos.
     */
    public HistoricoEventos() {
        this.eventos = new ArrayList<>();
    }

    /**
     * Adiciona um evento ao histórico.
     * 
     * @param evento Evento a ser adicionado.
     */
    public void adicionarEvento(Evento evento) {
        eventos.add(evento);
    }

    /**
     * Remove um evento do histórico.
     * 
     * @param evento Evento a ser removido.
     */
    public void removerEvento(Evento evento) {
        eventos.remove(evento);
    }

    /**
     * Retorna uma lista com todos os eventos do histórico.
     * 
     * @return Lista de todos os eventos.
     */
    public List<Evento> buscarTodosEventos() {
        return new ArrayList<>(eventos);
    }

    /**
     * Busca eventos no histórico com base em um filtro.
     * 
     * @param filtro Filtro a ser aplicado para buscar eventos.
     * @return Lista de eventos que atendem ao filtro.
     */
    public List<Evento> buscarEventosPorFiltro(FiltroEventos filtro) {
        List<Evento> eventosFiltrados = new ArrayList<>();
        for (Evento evento : eventos) {
            if (filtro.filtrar(evento)) {
                eventosFiltrados.add(evento);
            }
        }
        return eventosFiltrados;
    }
}

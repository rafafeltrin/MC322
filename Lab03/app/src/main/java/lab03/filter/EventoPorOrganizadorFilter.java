//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

package lab03.filter;

import java.util.Objects;

import lab03.model.evento.Evento;
import lab03.model.organizadora.Organizadora;

/**
 * Filtro que seleciona eventos por uma organizadora específica.
 */
public class EventoPorOrganizadorFilter implements Filter<Evento>{
    private Organizadora organizadoraBusca;

    /**
     * Cria um filtro para eventos cuja organizadora seja a fornecida.
     *
     * @param organizadoraBusca a organizadora a ser usada no critério de filtro
     */
    EventoPorOrganizadorFilter(Organizadora organizadoraBusca){
        this.organizadoraBusca = organizadoraBusca;
    }

    /**
     * Verifica se o evento informado tem a mesma organizadora deste filtro.
     *
     * @param evento o evento a ser avaliado
     * @return {@code true} se o organizadora do evento for igual à buscada
     */
    @Override
    public boolean matches(Evento evento){
        return Objects.equals(evento.getOrganizadora(), organizadoraBusca);
    }
}

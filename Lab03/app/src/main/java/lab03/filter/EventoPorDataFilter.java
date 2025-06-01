//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

package lab03.filter;

import lab03.Evento;

/**
 * Filtro que seleciona eventos pela data
 */
public class EventoPorDataFilter implements Filter<Evento>{
    private String DataBusca;

    /**
     * Constrói um filtro que compara a data do evento com o valor fornecido.
     *
     * @param Databusca a data alvo para filtragem
     */
    public EventoPorDataFilter(String Databusca){
        this.DataBusca = Databusca;
    }

    /**
     * Verifica se a data do evento coincide, sem diferenciar maiúsculas/minúsculas,
     * com a data especificada neste filtro.
     *
     * @param evento o evento a verificar
     * @return {@code true} se as datas forem equivalentes
     */
    @Override
    public boolean matches(Evento evento){
        return evento.getData().equalsIgnoreCase(DataBusca);
    }
}
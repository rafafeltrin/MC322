//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

package lab03.filter;

import java.util.Objects;

import lab03.model.evento.Evento;
import lab03.model.evento.Local;

public class EventoPorLocalFilter implements Filter<Evento>{
    private Local localEventoBusca;

    public EventoPorLocalFilter(Local localEventoBusca){
        this.localEventoBusca = localEventoBusca;
    }

    @Override
    public boolean matches(Evento evento){
        return Objects.equals(evento.getLocal(), localEventoBusca);
    }
}

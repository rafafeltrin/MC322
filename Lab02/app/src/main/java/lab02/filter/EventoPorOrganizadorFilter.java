package lab02.filter;

import java.util.Objects;

import lab02.Evento;
import lab02.Organizadora;

public class EventoPorOrganizadorFilter implements Filter<Evento>{
    private Organizadora organizadoraBusca;

    EventoPorOrganizadorFilter(Organizadora organizadoraBusca){
        this.organizadoraBusca = organizadoraBusca;
    }

    @Override
    public boolean matches(Evento evento){
        return Objects.equals(evento.getOrganizadora(), organizadoraBusca);
    }
}

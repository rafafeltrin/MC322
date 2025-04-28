package lab02;

import java.util.Objects;

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

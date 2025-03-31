package lab01;

public class IngressoMeia extends Ingresso{
    public IngressoMeia(Evento evento) {
        super(evento);
    }
    
    @Override
    public double getPreco(){
        return getEvento().getPrecoIngresso() / 2;
    }
}
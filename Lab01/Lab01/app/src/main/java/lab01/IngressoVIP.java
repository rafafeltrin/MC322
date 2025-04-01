package lab01;

/**
 * Representa um ingresso VIP, que possui um preço maior que o ingresso padrão.
 * O preço do ingresso VIP é calculado como 1.5 vezes o preço do ingresso padrão.
 * 
 * @see Ingresso
 * @author Rafael Feltrin - 276246
 */
public class IngressoVIP extends Ingresso {

    /**
     * Construtor da classe IngressoVIP.
     * 
     * @param evento o evento associado ao ingresso VIP
     */
    public IngressoVIP(Evento evento) {
        super(evento);
    }
    
    /**
     * Retorna o preço do ingresso VIP.
     * O preço é calculado como 1.5 vezes o preço do ingresso padrão.
     * 
     * @return o preço do ingresso VIP
     */
    @Override
    public double getPreco(){
        return getEvento().getPrecoIngresso() * 1.5;
    }
}

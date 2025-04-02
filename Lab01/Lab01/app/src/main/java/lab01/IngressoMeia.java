//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

package lab01;

/**
 * Representa um ingresso de meia-entrada.
 * O preço do ingresso é calculado como metade do preço padrão do evento.
 * 
 * @see Ingresso
 * @author Rafael Feltrin - 276246
 */
public class IngressoMeia extends Ingresso {

    /**
     * Construtor da classe IngressoMeia.
     * 
     * @param evento o evento associado ao ingresso
     */
    public IngressoMeia(Evento evento) {
        super(evento);
    }

    /**
     * Retorna o preço do ingresso de meia-entrada.
     * O preço é calculado como metade do preço padrão do evento.
     * 
     * @return o preço do ingresso de meia-entrada
     */
    @Override
    public double getPreco(){
        return getEvento().getPrecoIngresso() / 2;
    }
}
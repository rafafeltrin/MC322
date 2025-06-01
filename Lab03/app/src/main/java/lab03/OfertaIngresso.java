//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

package lab03;

/**
 * Representa uma oferta de um ingresso específico em um Marketplace.
 * Esta classe encapsula o ingresso, o cliente vendedor e o preço
 * solicitado pela venda.
 */
public class OfertaIngresso {

    private Ingresso ingresso;
    private Cliente vendedor;
    private double precoPedido;

    /**
     * Construtor da classe OfertaIngresso.
     * @param ingresso O ingresso que está sendo ofertado.
     * @param vendedor O cliente que está vendendo o ingresso.
     * @param precoPedido O preço solicitado pelo vendedor.
     */
    public OfertaIngresso(Ingresso ingresso, Cliente vendedor, double precoPedido) {
        this.ingresso = ingresso;
        this.vendedor = vendedor;
        this.precoPedido = precoPedido;
    }

    /**
     * Retorna o ingresso da oferta.
     * @return O ingresso.
     */
    public Ingresso getIngresso() {
        return ingresso;
    }

    /**
     * Retorna o cliente vendedor.
     * @return O cliente vendedor.
     */
    public Cliente getVendedor() {
        return vendedor;
    }

    /**
     * Retorna o preço pedido pelo ingresso.
     * @return O preço da oferta.
     */
    public double getPrecoPedido() {
        return precoPedido;
    }
}

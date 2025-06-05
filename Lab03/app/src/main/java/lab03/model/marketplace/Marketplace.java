//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

package lab03.model.marketplace;

import java.util.ArrayList;
import java.util.List;

import lab03.exceptions.SaldoInsuficienteException; // Será criada
import lab03.exceptions.OfertaNaoEncontradaException; // Será criada
import lab03.model.cliente.Cliente;
import lab03.model.cliente.Ingresso;

/**
 * Gerencia a compra e venda de ingressos entre clientes.
 * A classe é responsável por listar ofertas, receber novas ofertas e processar
 * a compra, incluindo o cálculo de comissão e a transferência de propriedade.
 */
public class Marketplace {

    private List<OfertaIngresso> ofertas;
    private double comissaoPorcentagem;

    /**
     * Construtor da classe Marketplace.
     * @param comissaoPorcentagem A comissão (em formato decimal) retida pelo marketplace em cada venda.
     */
    public Marketplace(double comissaoPorcentagem) {
        this.ofertas = new ArrayList<>();
        this.comissaoPorcentagem = comissaoPorcentagem;
    }

    /**
     * Adiciona uma nova oferta de ingresso ao marketplace.
     * @param ingresso O ingresso a ser vendido.
     * @param vendedor O cliente que está vendendo.
     * @param precoPedido O preço desejado pelo vendedor.
     */
    public void receberOferta(Ingresso ingresso, Cliente vendedor, double precoPedido) {
        OfertaIngresso novaOferta = new OfertaIngresso(ingresso, vendedor, precoPedido);
        this.ofertas.add(novaOferta);
    }

    /**
     * Retorna uma lista de todas as ofertas atualmente disponíveis no marketplace.
     * @return Uma lista de OfertaIngresso.
     */
    public List<OfertaIngresso> listarOfertas() {
        return this.ofertas;
    }

    /**
     * Processa a compra de um ingresso ofertado no marketplace.
     * Esta operação envolve verificar o saldo do comprador, transferir os valores
     * (descontando a comissão), transferir a propriedade do ingresso e remover a oferta.
     *
     * @param comprador O cliente que está comprando o ingresso.
     * @param oferta A oferta que está sendo comprada.
     * @throws SaldoInsuficienteException Se o comprador não tiver saldo suficiente.
     * @throws OfertaNaoEncontradaException Se a oferta não existir mais no marketplace.
     */
    public void processarCompra(Cliente comprador, OfertaIngresso oferta) throws SaldoInsuficienteException, OfertaNaoEncontradaException {
        if (!this.ofertas.contains(oferta)) {
            throw new OfertaNaoEncontradaException("A oferta selecionada não está mais disponível.");
        }

        Cliente vendedor = oferta.getVendedor();
        Ingresso ingresso = oferta.getIngresso();
        double precoPedido = oferta.getPrecoPedido();

        // Simulação da transação financeira (assumindo que Cliente terá saldo e métodos de débito/crédito)
        if (comprador.getSaldo() < precoPedido) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar a compra.");
        }

        double comissao = precoPedido * this.comissaoPorcentagem;
        double valorParaVendedor = precoPedido - comissao;

        comprador.debitar(precoPedido);
        vendedor.creditar(valorParaVendedor);

        comprador.adicionarIngresso(ingresso);

        this.ofertas.remove(oferta);
    }
}
//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa gemini

package lab03;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import lab03.exceptions.*;
import lab03.model.cliente.Cliente;
import lab03.model.cliente.Ingresso;
import lab03.model.evento.*;
import lab03.model.marketplace.Marketplace;
import lab03.model.marketplace.OfertaIngresso;
import lab03.model.organizadora.Organizadora;
import lab03.notifications.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe de teste para as funcionalidades implementadas no Lab03.
 * Esta classe testa a criação de eventos, venda de ingressos,
 * notificações por email, funcionalidades do marketplace e o tratamento
 * de exceções específicas.
 *
 * <p>O Javadoc desta classe foi gerado com auxílio de uma IA generativa gemini.</p>
 */
public class Lab02Test {
    private Organizadora organizadoraMaster;
    private Cliente cliente1;
    private Cliente cliente2;
    private Local teatro;
    private Evento eventoTeste;
    private Marketplace maketplace;

    /**
     * Configura o ambiente de teste antes de cada método de teste.
     * Inicializa uma organizadora, dois clientes, um local, um evento de teste
     * e um marketplace. Também simula a venda de um ingresso para o {@code cliente1}.
     *
     * @throws IngressoEsgotadoException se não houver ingressos disponíveis para o evento.
     */
    @BeforeEach
    void preparacao() throws IngressoEsgotadoException {
        organizadoraMaster = new Organizadora("Master", 1234567890, "Rua São Lucas, 123");

        cliente1 = new Cliente("Alice", "alice@example.com", 1000);
        cliente2 = new Cliente("Bob", "bob@example.com", 1500);

        teatro = new Local("Teatro Municipal", 1000);

        eventoTeste = new Evento("Rock Festival", 120.0, organizadoraMaster, "2024-08-01", 800, new EventoFestival(null, 3));

        maketplace = new Marketplace(0.2);

        eventoTeste.venderIngresso(cliente1);
    }

    /**
     * Testa a implementação da interface {@code Notificavel} através da classe {@code Email}.
     * Verifica se a notificação é enviada sem lançar exceções e se o endereço de email
     * é armazenado corretamente.
     *
     * @throws EnderecoEmailInvalidoException se o formato do email for inválido (não esperado neste teste específico).
     */
    @Test
    public void testImplementacaoNotificavel() throws EnderecoEmailInvalidoException{
        Email email = new Email(cliente1.getEmail());
        assertDoesNotThrow(() -> email.notificar("Teste de notificação"));
        assertEquals("alice@example.com",email.getEnderecoEmail());
    }

    /**
     * Testa a implementação da interface {@code Comparable} na classe {@code Cliente}.
     * Verifica se a comparação entre clientes funciona corretamente, considerando
     * que a lógica de comparação pode ter sido alterada pela venda de ingressos.
     * <p>
     * Nota: O teste assume que a venda de um ingresso para {@code cliente2} altera o resultado
     * da comparação {@code cliente1.compareTo(cliente2)}. A lógica específica de {@code compareTo}
     * na classe {@code Cliente} não é detalhada aqui, mas o teste verifica essa mudança.
     * </p>
     */
    @Test
    public void testComparebleClient() {
        assertDoesNotThrow(() -> eventoTeste.venderIngresso(cliente1));
        assertTrue(cliente1.compareTo(cliente2) != 0);

        assertDoesNotThrow(() -> eventoTeste.venderIngresso(cliente2));
        assertFalse(cliente1.compareTo(cliente2) != 0);
    }

    /**
     * Testa a composição de um {@code Evento} com a característica {@code EventoEmBar}.
     * Verifica se a característica do evento é de fato uma instância de {@code EventoEmBar}
     * e se a descrição gerada corresponde ao esperado.
     */
    @Test
    void testEventoEmBarComposition() {
        EventoEmBar barEv = new EventoEmBar("Bar Z", "18:00", "22:00");
        Evento evento = new Evento("Happy Hour", 30.0, organizadoraMaster, "2024-12-05", 100, barEv);

        assertTrue(evento.getCaracteristica() instanceof EventoEmBar);
        assertEquals("Evento no bar Bar Z, Happy Hour Inicio: 18:00, Duracão: 22:00",
                evento.getCaracteristica().getDescricao());
    }

    /**
     * Testa a composição de um {@code Evento} com a característica {@code EventoMusicaAoVivo}.
     * Verifica se a característica do evento é de fato uma instância de {@code EventoMusicaAoVivo}
     * e se a descrição gerada corresponde ao esperado.
     */
    @Test
    void testMusicaAoVivoComposition() {
        EventoMusicaAoVivo mav = new EventoMusicaAoVivo("Singer Y", "Jazz");
        Evento evento = new Evento("Live", 120.0, organizadoraMaster, "2024-11-20", 150, mav);

        assertTrue(evento.getCaracteristica() instanceof EventoMusicaAoVivo);
        assertEquals("Musica ao vivo com Singer Y do gênero Jazz", evento.getCaracteristica().getDescricao());
    }

    /**
     * Testa o lançamento da exceção {@code CapacidadeInsuficienteException}.
     * Verifica se a exceção é lançada corretamente quando se tenta criar um evento
     * com um número de ingressos que excede a capacidade do local.
     */
    @Test
    public void CapacidadeInsuficienteLocal(){
        assertThrowsExactly(CapacidadeInsuficienteException.class, () -> organizadoraMaster.criarEvento("Show do Artista", teatro, 100.0, organizadoraMaster, "2024-08-01", 1001, "Artista", "Rock"));
    }


    /**
     * Testa a validação de endereços de email.
     * Verifica se a exceção {@code EnderecoEmailInvalidoException} é lançada para um email inválido
     * e se um email válido é aceito sem lançar exceções.
     */
    @Test
    public void testEmailInvalido() {
        assertThrowsExactly(EnderecoEmailInvalidoException.class, () -> new Email("emailsemarroba.com"));

        assertDoesNotThrow(() -> new Email("emailcom@arroba.com"));
    }

    /**
     * Testa o método {@code receberOferta} da classe {@code Marketplace}.
     * Verifica se uma oferta de ingresso é corretamente adicionada ao marketplace,
     * com o vendedor e o preço pedido corretos.
     */
    @Test
    public void testMetodoReceberOfertaMarketplace(){
        maketplace.receberOferta(cliente1.getIngressos().getFirst(),cliente1, 150);
        assertEquals(cliente1, maketplace.listarOfertas().getFirst().getVendedor());
        assertEquals(150, maketplace.listarOfertas().getFirst().getPrecoPedido());
    }

    /**
     * Testa o método {@code oferecerIngressoParaVenda} da classe {@code Cliente}.
     * Verifica se um cliente pode oferecer um de seus ingressos para venda no marketplace
     * e se a oferta é registrada corretamente no marketplace.
     */
    @Test
    public void testMetodoOferecerIngressoParaVendaCliente(){
        assertDoesNotThrow(() -> cliente1.oferecerIngressoParaVenda(cliente1.getIngressos().getFirst(), 150, maketplace));

        assertEquals(1, maketplace.listarOfertas().size());
        assertEquals(cliente1, maketplace.listarOfertas().getFirst().getVendedor());
        assertEquals(150, maketplace.listarOfertas().getFirst().getPrecoPedido());
    }

    /**
     * Testa o processo completo de compra de um ingresso no marketplace.
     * Envolve um cliente oferecendo um ingresso e outro cliente comprando-o.
     * Verifica a transferência do ingresso, a atualização dos saldos dos clientes
     * (considerando a taxa do marketplace) e a remoção da oferta do marketplace.
     *
     * @throws IngressoEsgotadoException se o ingresso não puder ser vendido (não esperado neste fluxo).
     */
    @Test
    public void testCompraDeIngressoMarketplace() throws IngressoEsgotadoException {
        double saldoInicialCliente1 = cliente1.getSaldo();
        double saldoInicialCliente2 = cliente2.getSaldo();

        Ingresso ingressoIncial = cliente1.getIngressos().getFirst();

        assertDoesNotThrow(() -> cliente1.oferecerIngressoParaVenda(cliente1.getIngressos().getFirst(), 150, maketplace));
        assertDoesNotThrow(() -> cliente2.comprarIngressoNoMarketplace(maketplace.listarOfertas().getFirst(), maketplace));

        assertEquals(1, cliente2.getIngressos().size());
        assertEquals(ingressoIncial, cliente2.getIngressos().getFirst());
        assertEquals(0, maketplace.listarOfertas().size());
        assertEquals(saldoInicialCliente2 - 150, cliente2.getSaldo(), 0.001); // Cliente2 paga 150

        assertEquals(saldoInicialCliente1 + (150 * (1 - 0.2)), cliente1.getSaldo(), 0.001); // Cliente1 recebe 150 menos a taxa
    }

    /**
     * Testa o lançamento da exceção {@code IngressoNaoPertenceAoClienteException}.
     * Verifica se a exceção é lançada corretamente quando um cliente tenta vender
     * um ingresso que não lhe pertence.
     */
    @Test
    public void testExcepitionIngressoNaoPertenceAoCliente() {
        Ingresso ingressoInvalido = new Ingresso(eventoTeste, 120.0); // Um ingresso novo, não pertencente a cliente1

        assertThrowsExactly(IngressoNaoPertenceAoClienteException.class, () -> cliente1.oferecerIngressoParaVenda(ingressoInvalido, 150, maketplace));
    }

    /**
     * Testa o lançamento da exceção {@code OfertaNaoEncontradaException}.
     * Verifica se a exceção é lançada corretamente quando um cliente tenta comprar
     * uma oferta de ingresso que não existe (ou não está mais disponível) no marketplace.
     */
    @Test
    public void testExcepitionOfertaNaoEncontrada(){
        // Cria uma oferta que não será adicionada ao marketplace
        OfertaIngresso ofertaNaoListada = new OfertaIngresso(cliente1.getIngressos().getFirst(), cliente1, 150);
        assertThrowsExactly(OfertaNaoEncontradaException.class, () -> cliente2.comprarIngressoNoMarketplace(ofertaNaoListada, maketplace));
    }

    /**
     * Testa o lançamento da exceção {@code SaldoInsuficienteException}.
     * Verifica se a exceção é lançada corretamente quando um cliente tenta comprar
     * um ingresso no marketplace, mas não possui saldo suficiente.
     */
    @Test
    public void testExcepitionSaldoInsuficiente() {
        cliente2.debitar(cliente2.getSaldo());
        assertDoesNotThrow(() -> cliente1.oferecerIngressoParaVenda(cliente1.getIngressos().getFirst(), 150, maketplace));

        assertThrowsExactly(SaldoInsuficienteException.class, () -> cliente2.comprarIngressoNoMarketplace(maketplace.listarOfertas().getFirst(), maketplace));
    }
}
//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

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


public class Lab02Test {
    private Organizadora organizadoraMaster;
    private Cliente cliente1;
    private Cliente cliente2;
    private Local teatro;
    private Evento eventoTeste;
    private Marketplace maketplace;

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

    @Test
    public void testImplementacaoNotificavel() throws EnderecoEmailInvalidoException{
        Email email = new Email(cliente1.getEmail());
        assertDoesNotThrow(() -> email.notificar("Teste de notificação"));
        assertEquals("alice@example.com",email.getEnderecoEmail());
    }

    @Test
    public void testComparebleClient() {
        assertDoesNotThrow(() -> eventoTeste.venderIngresso(cliente1));
        assertTrue(cliente1.compareTo(cliente2) != 0);
        
        assertDoesNotThrow(() -> eventoTeste.venderIngresso(cliente2));
        assertFalse(cliente1.compareTo(cliente2) != 0);
    }

    @Test
    void testEventoEmBarComposition() {
        EventoEmBar barEv = new EventoEmBar("Bar Z", "18:00", "22:00");
        Evento evento = new Evento("Happy Hour", 30.0, organizadoraMaster, "2024-12-05", 100, barEv);

        assertTrue(evento.getCaracteristica() instanceof EventoEmBar);
        assertEquals("Evento no bar Bar Z, Happy Hour Inicio: 18:00, Duracão: 22:00",
                     evento.getCaracteristica().getDescricao());
    }

    @Test
    void testMusicaAoVivoComposition() {
        EventoMusicaAoVivo mav = new EventoMusicaAoVivo("Singer Y", "Jazz");
        Evento evento = new Evento("Live", 120.0, organizadoraMaster, "2024-11-20", 150, mav);

        assertTrue(evento.getCaracteristica() instanceof EventoMusicaAoVivo);
        assertEquals("Musica ao vivo com Singer Y do gênero Jazz", evento.getCaracteristica().getDescricao());
    }

    @Test
    public void CapacidadeInsuficienteLocal(){
        assertThrowsExactly(CapacidadeInsuficienteException.class, () -> organizadoraMaster.criarEvento("Show do Artista", teatro, 100.0, organizadoraMaster, "2024-08-01", 1001, "Artista", "Rock"));
    }

    
    @Test
    public void testEmailInvalido() {
        assertThrowsExactly(EnderecoEmailInvalidoException.class, () -> new Email("emailsemarroba.com"));
        
        assertDoesNotThrow(() -> new Email("emailcom@arroba.com"));
    }

    @Test
    public void testMetodoReceberOfertaMarketplace(){
        maketplace.receberOferta(cliente1.getIngressos().getFirst(),cliente1, 150);
        assertEquals(cliente1, maketplace.listarOfertas().getFirst().getVendedor());
        assertEquals(150, maketplace.listarOfertas().getFirst().getPrecoPedido());
    }

    @Test
    public void testMetodoOferecerIngressoParaVendaCliente(){
        assertDoesNotThrow(() -> cliente1.oferecerIngressoParaVenda(cliente1.getIngressos().getFirst(), 150, maketplace));

        assertEquals(1, maketplace.listarOfertas().size());
        assertEquals(cliente1, maketplace.listarOfertas().getFirst().getVendedor());
        assertEquals(150, maketplace.listarOfertas().getFirst().getPrecoPedido());
    }

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
        assertEquals(saldoInicialCliente2, cliente2.getSaldo() + 150);

        assertEquals(saldoInicialCliente1 + 150*0.8, cliente1.getSaldo());
    }

    @Test
    public void testExcepitionIngressoNaoPertenceAoCliente() {
        Ingresso ingressoInvalido = new Ingresso(eventoTeste, 120.0);

        assertThrowsExactly(IngressoNaoPertenceAoClienteException.class, () -> cliente1.oferecerIngressoParaVenda(ingressoInvalido, 150, maketplace));
    }

    @Test
    public void testExcepitionOfertaNaoEncontrada(){
        OfertaIngresso oferta = new OfertaIngresso(cliente1.getIngressos().getFirst(), cliente1, 150);
        assertThrowsExactly(OfertaNaoEncontradaException.class, () -> cliente2.comprarIngressoNoMarketplace(oferta, maketplace));
    }

    @Test
    public void testExcepitionSaldoInsuficiente() {
        cliente2.debitar(1500);
        assertDoesNotThrow(() -> cliente1.oferecerIngressoParaVenda(cliente1.getIngressos().getFirst(), 150, maketplace));

        assertThrowsExactly(SaldoInsuficienteException.class, () -> cliente2.comprarIngressoNoMarketplace(maketplace.listarOfertas().getFirst(), maketplace));
    }
}
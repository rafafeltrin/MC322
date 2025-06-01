//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

package lab03;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lab03.exceptions.EnderecoEmailInvalidoException;
import lab03.exceptions.CapacidadeInsuficienteException;


public class Lab02Test {
    private Organizadora organizadoraMaster;
    private Cliente cliente1;
    private Cliente cliente2;
    private Local teatro;
    private Evento eventoTeste;

    @BeforeEach
    void preparacao(){
        organizadoraMaster = new Organizadora("Master", 1234567890, "Rua São Lucas, 123");

        cliente1 = new Cliente("Alice", "alice@example.com");
        cliente2 = new Cliente("Bob", "bob@example.com");

        teatro = new Local("Teatro Municipal", 1000);

        eventoTeste = new Evento("Rock Festival", 120.0, organizadoraMaster, "2024-08-01", 800, new EventoFestival(null, 3));
        
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
}
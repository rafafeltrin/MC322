package lab01;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class Lab01Test {

    /**
     * Testa se o getCapacidadeEvento retorna a capacidade do local do Evento
     */
    @Test
    public void getCapacidadeEventoShow() {

        Local testLocal = new Local("Teatro Castro Mendes", 2000);
        EventoShow testEvento = new EventoShow("MPB em Campinas", testLocal, 150, "Djavan", "01/05/2025");
        assertEquals(2000, testEvento.getCapacidade());
    }


    /**
     * Testa o get e seter do EventoShow para o atributo artista
     * Espera-se a troca de "01/05/2025" por "Anavitória"
     */

    @Test
    public void setAndGetArtistaEventoShow() {

        Local testLocal = new Local("Teatro Castro Mendes", 2000);
        EventoShow testEvento = new EventoShow("MPB em Campinas", testLocal, 150, "Djavan", "01/05/2025");
        testEvento.setArtista("Anavitória");
        assertEquals("Anavitória", testEvento.getArtista());
    }

    /**
     * Testa o método getPreco para o IngressoMeia
     * Espera o retorno da metade do valor (100) do ingresso do EventoShow criado (200)
     */

    @Test
    public void getPrecoIngressoMeia() {

        Local testLocal = new Local("Teatro Castro Mendes", 2000);
        EventoShow testEvento = new EventoShow("MPB em Campinas", testLocal, 200, "Djavan", "01/05/2025");
        IngressoMeia ingressoMeia = new IngressoMeia(testEvento);
        assertEquals(100, ingressoMeia.getPreco());
    }


    /**
     * Testa o método getPreco para o IngressoInteira
     * Espera o retorno do valor (250) do ingresso do EventoShow criado
     */
    @Test
    public void getPrecoIngressoInteira() {

        Local testLocal = new Local("Teatro Castro Mendes", 2000);
        EventoShow testEvento = new EventoShow("MPB em Campinas", testLocal, 250, "Djavan", "01/05/2025");
        IngressoInteira ingressoInteira = new IngressoInteira(testEvento);
        assertEquals(250, ingressoInteira.getPreco());
    }

    /**
     * Testa o método adicionarIngressoMeia
     * Verifica se o ingresso foi adicionado à lista de ingressos vendidos
     * e se o usuario agora tem o ingresso
     */
    @Test
    public void adicionarIngressoMeia() {

        Local testLocal = new Local("Teatro Castro Mendes", 2000);
        EventoShow testEvento = new EventoShow("MPB em Campinas", testLocal, 200, "Djavan", "01/05/2025");
        IngressoMeia ingressoMeia = new IngressoMeia(testEvento);
        Usuario usuarioTest = new Usuario("Gabriel", "gabriel@me.com");
        testEvento.adicionarIngresso(ingressoMeia, usuarioTest);
        assertEquals(1, testEvento.getIngressosVendidos().size());
        assertEquals(ingressoMeia, usuarioTest.getIngresso());

    }
    
    /**
     * Testa o método adicionarEvento do HistoricoEventos
     * Verifica se os dois eventos foram adicionados à lista de eventos
     * do HistoricoEventos

}

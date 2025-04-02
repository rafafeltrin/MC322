package lab01;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Alterações realizadas no arquivo
 * 
 * 1) Modifiquei a instanciação do EventoShow para que contenha os parametros corretos do construtor que implementei
 * 
 * 2) Alterei o assertEquals que testa o ingresso de um usuário, pois na minha implementação o 
 *    ingresso é adicionado a uma lista de ingressos comprados do usuário, assim cada usuário pode ter mais de um ingresso
 */

public class Lab01Test {

    /**
     * Testa se o getCapacidadeEvento retorna a capacidade do local do Evento
     */
    @Test
    public void getCapacidadeEventoShow() {

        Local testLocal = new Local("Teatro Castro Mendes", 2000);
        EventoShow testEvento = new EventoShow("MPB em Campinas",testLocal, "01/05/2025", 200, "Djavan", "MPB", 180);
        assertEquals(2000, testEvento.getCapacidade());
    }


    /**
     * Testa o get e seter do EventoShow para o atributo artista
     * Espera-se a troca de "01/05/2025" por "Anavitória"
     */

    @Test
    public void setAndGetArtistaEventoShow() {

        Local testLocal = new Local("Teatro Castro Mendes", 2000);
        EventoShow testEvento = new EventoShow("MPB em Campinas",testLocal, "01/05/2025", 200, "Djavan", "MPB", 180);
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
        EventoShow testEvento = new EventoShow("MPB em Campinas",testLocal, "01/05/2025", 200, "Djavan", "MPB", 180);
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
        EventoShow testEvento = new EventoShow("MPB em Campinas",testLocal, "01/05/2025", 250, "Djavan", "MPB", 180);
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
        EventoShow testEvento = new EventoShow("MPB em Campinas",testLocal, "01/05/2025", 200, "Djavan", "MPB", 180);
        IngressoMeia ingressoMeia = new IngressoMeia(testEvento);
        Usuario usuarioTest = new Usuario("Gabriel", "gabriel@me.com");
        testEvento.adicionarIngresso(ingressoMeia, usuarioTest);
        assertEquals(1, testEvento.getIngressosVendidos().size());
        assertEquals(ingressoMeia, usuarioTest.getIngresso().get(0));
    }
}

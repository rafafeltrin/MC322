/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab02;

import java.util.Arrays;
import lab02.exceptions.CapacidadeInsuficienteException;
import lab02.exceptions.LocalIndisponivelException;
import lab02.exceptions.IngressoEsgotadoException;
import lab02.exceptions.IngressoNaoEncontradoException;
import lab02.exceptions.CancelamentoNaoPermitidoException;

/**
 * Contém a estrutura de implementação da aplicação.
 * 
 * @author NOME - RA
 */
public class App {

    /**
     * Aplicação principal
     * @param args
     */
    public static void main(String[] args) {
        Organizadora organizadoraMaster = new Organizadora("Master", 1234567890, "Rua São Lucas, 123");

        Local teatro = new Local("Teatro Municipal", 1000);
        Local salaPequena = new Local("Salão Pequeno", 50);

        // 1) Festival — sucesso
        try {
            organizadoraMaster.criarEvento(
                "Rock Festival",
                teatro,
                120.0,
                organizadoraMaster,
                "2024-08-01",
                800,
                Arrays.asList("Band A", "Band B"),
                3
            );
            System.out.println("Festival criado com sucesso.");
        } catch (LocalIndisponivelException | CapacidadeInsuficienteException e) {
            System.out.println("Erro ao criar Festival: " + e.getMessage());
        }

        // 2) Show — Capacidade Insuficiente
        try {
            organizadoraMaster.criarEvento(
                "Show Grande",
                salaPequena,
                150.0,
                organizadoraMaster,
                "2024-09-01",
                100,
                "Artista X"
            );
        } catch (CapacidadeInsuficienteException e) {
            System.out.println("Capacidade insuficiente ao criar Show: " + e.getMessage());
        } catch (LocalIndisponivelException e) {
            System.out.println("Local indisponível ao criar Show: " + e.getMessage());
        }

        // 3) Esporte — Local já reservado
        try {
            organizadoraMaster.criarEvento(
                "Jogo Final",
                teatro,
                80.0,
                organizadoraMaster,
                "2024-10-01",
                500,
                Arrays.asList("Time A", "Time B"),
                "Futebol"
            );
        } catch (CapacidadeInsuficienteException e) {
            System.out.println("Capacidade insuficiente ao criar Jogo: " + e.getMessage());
        } catch (LocalIndisponivelException e) {
            System.out.println("Local indisponível ao criar Jogo: " + e.getMessage());
        }

        // 4) Venda de Ingressos — esgota (cap 2)
        Evento concerto = new Evento(
            "Concerto Íntimo",
            200.0,
            organizadoraMaster,
            "2024-11-01",
            2,
            new EventoShow("Artista Y")
        );
        try {
            teatro.alocarParaEvento(concerto);
        } catch (Exception ignored) {}
        Cliente clienteVenda = new Cliente("Alice", "alice@example.com");
        for (int i = 1; i <= 3; i++) {
            try {
                concerto.venderIngresso(clienteVenda);
                System.out.println("Ingresso " + i + " vendido para Alice.");
            } catch (IngressoEsgotadoException e) {
                System.out.println("Esgotado ao vender ingresso " + i + ": " + e.getMessage());
            }
        }

        // 5) Cancelamento não permitido
        Evento jazz = new Evento(
            "Jazz Night",
            120.0,
            organizadoraMaster,
            "2024-12-01",
            100,
            new EventoMusicaAoVivo("Jazz Band", "Jazz")
        );
        try {
            salaPequena.alocarParaEvento(jazz);
        } catch (CapacidadeInsuficienteException | LocalIndisponivelException e) {
            System.out.println("Erro ao alocar evento música ao vivo: " + e.getMessage());
        }

        
        Cliente clienteCancel = new Cliente("Bob", "bob@example.com");
        try {
            jazz.venderIngresso(clienteCancel);
        } catch (IngressoEsgotadoException e) {}
        Ingresso ingressoBob = clienteCancel.getIngressos().get(0);
        ingressoBob.setAceitaCancelamento(false);
        try {
            clienteCancel.cancelarIngresso(ingressoBob);
        } catch (CancelamentoNaoPermitidoException e) {
            System.out.println("Cancelamento não permitido: " + e.getMessage());
        } catch (IngressoNaoEncontradoException e) {
            System.out.println("Ingresso não encontrado: " + e.getMessage());
        }

        // 6) Ingresso não encontrado
        Cliente clienteSem = new Cliente("Charlie", "charlie@example.com");
        Ingresso fake = new Ingresso(jazz, 120.0);
        try {
            clienteSem.cancelarIngresso(fake);
        } catch (CancelamentoNaoPermitidoException e) {
            System.out.println("Cancelamento não permitido: " + e.getMessage());
        } catch (IngressoNaoEncontradoException e) {
            System.out.println("Ingresso não encontrado: " + e.getMessage());
        }
    }
}

/*
 * Material usado na disciplina MC322 - Programação orientada a objetos.
 */

package lab02;

import java.util.Arrays;
import java.util.List;

import lab02.exceptions.CapacidadeInsuficienteException;
import lab02.exceptions.EnderecoEmailInvalidoException;
import lab02.exceptions.LocalIndisponivelException;
import lab02.filter.EventoPorDataFilter;
import lab02.filter.EventoPorNomeFilter;
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

        ImobiliariaDeEventos imabiliariaX = new ImobiliariaDeEventos("Imobiliária X");

        Local teatro = new Local("Teatro Municipal", 1000);
        Local salaPequena = new Local("Salão Pequeno", 50);
        Local barDoBelo = new Local("Bar do Belo", 150);
        Local pagodeDoCastro = new Local("Pagode do Castro", 200);

        imabiliariaX.adicionarLocal(teatro);
        imabiliariaX.adicionarLocal(salaPequena);

        Cliente cliente1 = new Cliente("Alice", "alice@example.com");
        Cliente cliente2 = new Cliente("Bob", "bob@example.com");
        Cliente cliente3 = new Cliente("Charlie", "charlie@example.com");
        Cliente cliente4 = new Cliente("Diana", "diana@example.com");
        Cliente cliente5 = new Cliente("Eve", "eve@example.com");
        
        
        // 1) Festival — sucesso
        System.out.println("Testando a criação de um evento do tipo festival festival...");
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
        System.out.println("\n");

        // 2) Show — Capacidade Insuficiente
        System.out.println("Testando a criação de um evento do tipo show em que a capacidade é insuficiente...");
        try {
            organizadoraMaster.criarEvento(
                "Show Grande",
                imabiliariaX.buscarLocal("Salão Pequeno"),
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
        System.out.println("\n");

        // 3) Esporte — Local já reservado
        System.out.println("Testando a criação de um evento do tipo esporte em que o local já está reservado...");
        try {
            organizadoraMaster.criarEvento(
                "Jogo Final",
                teatro,
                80.0,
                organizadoraMaster,
                "2025-05-01",
                500,
                Arrays.asList("Time A", "Time B"),
                "Futebol"
            );
        } catch (CapacidadeInsuficienteException e) {
            System.out.println("Capacidade insuficiente ao criar Jogo: " + e.getMessage());
        } catch (LocalIndisponivelException e) {
            System.out.println("Local indisponível ao criar Jogo: " + e.getMessage());
        }
        System.out.println("\n");


        // 4) Evento música ao vivo
        System.out.println("Testando a criação de um evento do tipo música ao vivo...");
        try {
            organizadoraMaster.criarEvento(
            "Pagode menos é mais",
            pagodeDoCastro,
            200.0,
            organizadoraMaster,
            "2025-05-01",
            150,
            "Menos é mais",
            "Pagode"
        );
        } catch (LocalIndisponivelException | CapacidadeInsuficienteException e) {
            System.out.println("Erro ao criar evento música ao vivo: " + e.getMessage());
        }
        System.out.println("\n");


        // 5) Evento em bar
        System.out.println("Testando a criação de um evento do tipo em bar...");
        try {
            organizadoraMaster.criarEvento(
            "Encontro a 2",
            barDoBelo,
            1000.0,
            organizadoraMaster,
            "2025-05-01",
            2,
            "Bar do belo",
            "19:00",
            "21:00"
        );
        } catch (LocalIndisponivelException | CapacidadeInsuficienteException e) {
            System.out.println("Erro ao criar evento em bar: " + e.getMessage());
        }
        System.out.println("\n");

        //Filtro para buscar evento pelo nome
        System.out.println("Buscando eventos com o nome 'Encontro a 2'...");
        EventoPorNomeFilter filtroEventoADois = new EventoPorNomeFilter("Encontro a 2");

        // Exemplicando a função de ingresso esgotado
        System.out.println("Tentando vender ingressos para o evento 'Encontro a 2' até esgotar...");
        for (int i = 1; i <= 3; i++) {
            Evento evento = organizadoraMaster.buscarEventos(filtroEventoADois).get(0);
            try {
                evento.venderIngresso(cliente1);
                System.out.println("Ingresso " + i + " do evento" + evento.getNome() + " vendido para " + cliente1.getNome());
            } catch (IngressoEsgotadoException e) {
                System.out.println("Evento: " + evento.getNome() + " esgotado ao tentar vender ingresso de número " + i + ": " + e.getMessage());
            }
        }
        System.out.println("\n");
        
        //Buscando evento por data
        System.out.println("Buscando eventos no dia 2025-05-01 (Dia dos trabalhadores)");
        EventoPorDataFilter buscaEventosDiaDosTrabalhadores = new EventoPorDataFilter("2025-05-01");

        List<Evento> eventosDiaDosTrabalhadores = organizadoraMaster.buscarEventos(buscaEventosDiaDosTrabalhadores);
        System.out.println("Eventos encontrado no dia dos trabalhadores: ");
        for (Evento evento : eventosDiaDosTrabalhadores) {
            System.out.println(evento.getNome());
        }
        System.out.println("\n");



        //Demonstrando filtro pela data
        try {
            eventosDiaDosTrabalhadores.get(0).venderIngresso(cliente2);
        } catch (IngressoEsgotadoException e) {
            System.out.println("Erro ao vender ingresso: " + e.getMessage());
        }


        //Desmontrando o cancelamento não permitido
        System.out.println("Tentando cancelar um ingresso que não pode ser cancelado...");
        Ingresso ingressoBob = cliente2.getIngressos().get(0);
        ingressoBob.setAceitaCancelamento(false);
        try {
            cliente2.cancelarIngresso(ingressoBob);
        } catch (CancelamentoNaoPermitidoException e) {
            System.out.println("Cancelamento não permitido: " + e.getMessage());
        } catch (IngressoNaoEncontradoException e) {
            System.out.println("Ingresso não encontrado: " + e.getMessage());
        }
        System.out.println("\n");


        //Demosntrando o ingresos não ser encontrado
        System.out.println("Tentando cancelar um ingresso que não existe...");
        try {
            cliente3.cancelarIngresso(ingressoBob);
        } catch (CancelamentoNaoPermitidoException e) {
            System.out.println("Cancelamento não permitido: " + e.getMessage());
        } catch (IngressoNaoEncontradoException e) {
            System.out.println("Ingresso não encontrado: " + e.getMessage());
        }
        System.out.println("\n"); 
        

        //Demonstrando classe de notificação
        System.out.println("Testando a notificação de um cliente...");

        try{
            cliente4.adicionarCanalComunicacao(new Email(cliente4.getEmail()));
        } catch (EnderecoEmailInvalidoException e){
            System.out.println("Erro ao adicionar canal de comunicação para cliente " + e.getMessage());
        }
    
        cliente4.notificar("Vimos que você ainda não comprou nenhum ingresso, venha converir nossos ingressos diponíveis!");
        System.out.println("\n");


        //Demonstrando comparação entre clientes
        System.out.println("Demonstrando comparação entre clientes...");
        if (cliente2.compareTo(cliente5) == 0) {
            System.out.println("Os clientes são iguais.");
        } else {
            System.out.println("Os clientes são diferentes.");
        }

        try {
            eventosDiaDosTrabalhadores.get(0).venderIngresso(cliente5);
        } catch (IngressoEsgotadoException e) {
            System.out.println("Erro ao vender ingresso: " + e.getMessage());
        }

        if (cliente2.compareTo(cliente5) == 0) {
            System.out.println("Os clientes são iguais.");
        } else {
            System.out.println("Os clientes são diferentes.");
        }
        
    }

}

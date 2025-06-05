//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

package lab03.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lab03.model.cliente.Cliente;
import lab03.model.cliente.Ingresso;
import lab03.model.marketplace.Marketplace;
import lab03.exceptions.CancelamentoNaoPermitidoException;
import lab03.exceptions.IngressoNaoEncontradoException;
import lab03.exceptions.IngressoNaoPertenceAoClienteException;

import java.util.Optional;

public class MeusIngressosController {

    @FXML
    private TableView<Ingresso> ingressosTable;

    @FXML
    private TableColumn<Ingresso, String> eventoColumn;

    @FXML
    private TableColumn<Ingresso, String> dataColumn;

    @FXML
    private TableColumn<Ingresso, Double> precoColumn;

    private Cliente clienteLogado;
    private Marketplace marketplace;

    @FXML
    public void initialize() {
        eventoColumn.setCellValueFactory(new PropertyValueFactory<>("nomeEventoCorrespondente"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("dataEventoCorrespondente"));
        precoColumn.setCellValueFactory(new PropertyValueFactory<>("preco"));
    }

    /**
     * Recebe os dados do cliente logado para poder exibir seus ingressos.
     * @param cliente O cliente que está visualizando seus ingressos.
     */
    public void initData(Cliente cliente, Marketplace marketplace) {
        this.clienteLogado = cliente;
        this.marketplace = marketplace;
        atualizarTabela();
    }

    /**
     * Atualiza a tabela de ingressos com os ingressos do cliente logado.
     * Chamada sempre que há uma alteração nos ingressos (como cancelamento ou venda).
     */
    private void atualizarTabela() {
        ingressosTable.setItems(FXCollections.observableArrayList(clienteLogado.getIngressos()));
    }


    /**
     * Método chamado quando o usuário clica no botão "Cancelar Ingresso".
     * Verifica se um ingresso está selecionado e tenta cancelá-lo.
     * Se o cancelamento for bem-sucedido, exibe uma mensagem de sucesso.
     * Se ocorrer um erro, exibe uma mensagem de erro apropriada.
     */
    @FXML
    private void handleCancelarIngresso(){
        Ingresso ingressoSelecionado = ingressosTable.getSelectionModel().getSelectedItem();

        try {
            clienteLogado.cancelarIngresso(ingressoSelecionado);

            exibirAlerta("Sucesso", "Ingresso cancelado com sucesso!\nO valor já foi reembolsado.");
        } catch (IngressoNaoEncontradoException e){
            exibirAlerta("Erro ao realizar a venda", "Ingresso não encontrado: " + e.getMessage());
        } catch (CancelamentoNaoPermitidoException e) {
            exibirAlerta("Erro ao realizar a venda", "O ingresso não permite cancelamento" + e.getMessage());
        } finally {
            atualizarTabela();
        }
    }

    /**
     * Método chamado quando o usuário clica no botão "Vender Ingresso no Marketplace".
     * Verifica se um ingresso está selecionado e tenta vender o ingresso no marketplace.
     */
    @FXML
    private void handleVenderNoMarketplace() {
        Ingresso ingressoSelecionado = ingressosTable.getSelectionModel().getSelectedItem();

        if (ingressoSelecionado == null) {
            exibirAlerta("Ação inválida", "Por favor, selecione um ingresso para vender.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog("150.00");
        dialog.setTitle("Vender Ingresso no Marketplace");
        dialog.setHeaderText("Você está vendendo o ingresso para: " + ingressoSelecionado.getEvento().getNome());
        dialog.setContentText("Por favor, digite o preço de venda:");

        Optional<String> resultado = dialog.showAndWait();

        resultado.ifPresent(precoStr -> {
            try {
                double precoPedido = Double.parseDouble(precoStr);

                clienteLogado.oferecerIngressoParaVenda(ingressoSelecionado, precoPedido, this.marketplace);

                exibirAlerta("Sucesso", "Seu ingresso foi ofertado no marketplace!");

            } catch (NumberFormatException e) {
                exibirAlerta("Erro de Formato", "O preço digitado não é um número válido.");
            } catch (IngressoNaoPertenceAoClienteException e) {
                exibirAlerta("Erro", "Ocorreu um problema ao ofertar seu ingresso: " + e.getMessage());
            } finally {
                atualizarTabela();
            }
        });
    }


    /**
     * Exibe um alerta com o título e a mensagem fornecidos.
     * @param titulo O título do alerta.
     * @param mensagem A mensagem a ser exibida no alerta.
     */
    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}

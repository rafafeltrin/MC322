package lab03.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lab03.Cliente;
import lab03.Ingresso;
import lab03.Marketplace;
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

    @FXML
    private Button venderButton;

    @FXML
    private Button cancelarButton;

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

    private void atualizarTabela() {
        ingressosTable.setItems(FXCollections.observableArrayList(clienteLogado.getIngressos()));
    }

    @FXML
    private void handleCancelarIngresso(){
        //Ingresso selecionado
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

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}

//O JavaDoc dessa classe foi gerado com auxílio de uma IA generativa (GPT-4o)

package lab03.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lab03.model.cliente.Cliente;
import lab03.model.marketplace.Marketplace;
import lab03.model.marketplace.OfertaIngresso;
import lab03.exceptions.OfertaNaoEncontradaException;
import lab03.exceptions.SaldoInsuficienteException;

/**
 * Controller para a janela do Marketplace (Marketplace.fxml).
 * Responsável por exibir as ofertas de ingressos e permitir que o cliente compre ingressos.
 */
public class MarketplaceController {

    @FXML private TableView<OfertaIngresso> ofertasTable;
    @FXML private TableColumn<OfertaIngresso, String> eventoColumn;
    @FXML private TableColumn<OfertaIngresso, String> vendedorColumn;
    @FXML private TableColumn<OfertaIngresso, Double> precoOriginalColumn;
    @FXML private TableColumn<OfertaIngresso, Double> precoPedidoColumn;

    private Marketplace marketplace;
    private Cliente clienteLogado;

    @FXML
    public void initialize() {
        precoPedidoColumn.setCellValueFactory(new PropertyValueFactory<>("precoPedido"));

        eventoColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getIngresso().getEvento().getNome())
        );
        vendedorColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getVendedor().getNome())
        );
        precoOriginalColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getIngresso().getPreco()).asObject()
        );
    }

    /**
     * Recebe os dados do sistema para poder operar.
     * @param marketplace A instância do marketplace.
     * @param cliente O cliente que está navegando no marketplace.
     */
    public void initData(Marketplace marketplace, Cliente cliente) {
        this.marketplace = marketplace;
        this.clienteLogado = cliente;

        atualizarTabela();
    }

    /**
     * Método chamado quando o botão "Comprar" é clicado.
     * Processa a compra do ingresso selecionado na tabela de ofertas.
     */
    @FXML
    private void handleComprar() {
        OfertaIngresso ofertaSelecionada = ofertasTable.getSelectionModel().getSelectedItem();
        if (ofertaSelecionada == null) {
            exibirAlerta("Ação inválida", "Por favor, selecione um ingresso para vender.");
            return;
        }
        try {
            marketplace.processarCompra(clienteLogado, ofertaSelecionada);
            exibirAlerta("Compra realizada", "Ingresso comprado com sucesso!");
        } catch (SaldoInsuficienteException e) {
            exibirAlerta("Erro ao realizar a compra", "Saldo insuficiente: " + e.getMessage());
        } catch (OfertaNaoEncontradaException e) {
            exibirAlerta("Erro ao realizar a compra", "Oferta não encontrada: " + e.getMessage());
        } finally {
            atualizarTabela();
        }
    }

    /**
     * Método chamado quando o botão "Voltar" é clicado.
     * Fecha a janela do Marketplace e retorna ao cliente logado.
     */
    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    /**
     * Atualiza a tabela de ofertas com as ofertas atuais do marketplace.
     */
    private void atualizarTabela() {
        ofertasTable.setItems(FXCollections.observableArrayList(marketplace.listarOfertas()));
    }
}

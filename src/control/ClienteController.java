package control;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Cliente;
import servidor.Atendente;

public class ClienteController implements Initializable {

    @FXML
    private ListView<Atendente> listClientes;

    @FXML
    private TextArea mensagens;
    
    @FXML
    private TextField textMensagem;
    
    private Cliente cliente;

    @FXML
    void enviar(ActionEvent event) {
        cliente.send(textMensagem.getText());
        textMensagem.setText("");
    }
    
    public void iniciaCliente() throws Exception{
        System.out.println("Iniciando Cliente");
        System.out.println("Iniciando Conexão com o servidor");
        cliente = new Cliente("localhost", 2525);
        System.out.println("Conexão estabelecida");
        cliente.start();
    }
    
    public void iniciaLista(){
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            iniciaCliente();
        } catch (Exception ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}

package control;

import clienteservidorchat.ClienteServidorChat;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import servidor.Atendente;
import servidor.Servidor;

public class ServidorController implements Initializable {
    
    @FXML
    private ListView<Atendente> listClientes;
    
    @FXML
    private Label labelStatus;
    
    private Servidor servidor;
    
    private Thread thread;

    @FXML
    void iniciaDesligaServidor(ActionEvent event) throws Exception {
        if (labelStatus.getText().equals("Parado")) {
            System.out.println("Iniciando Servidor");
            servidor = new Servidor(2525);
            servidor.start();
            listClientes.setItems(servidor.getAtendentes());
            labelStatus.setText("Executando");
            iniciaLista();
            thread.start();
            
            
        } else {
            System.out.println("Encerrando servidor");
            servidor.stop();
            labelStatus.setText("Parado");
            thread.interrupt();
        }
    }
    
    @FXML
    void novaconexao(ActionEvent event) {
        ClienteServidorChat clienteServidor = new ClienteServidorChat();
        clienteServidor.iniciaStage("Cliente.fxml");
    }
    
    public void iniciaLista(){
        
        Task t = new Task() {
            @Override
            protected Object call() throws Exception {
                int i = 0;
                while (i < 10) {
                    Thread.sleep(1000);
                    //System.out.println(listClientes.getItems().size());
                    listClientes.refresh();
                    //System.out.println("FOI");
                }
                return null;
            }
        };
        thread = new Thread(t);
        //thread.start();
    }
    
    public void refreshLista(){
        this.listClientes.refresh();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //iniciaLista();
    }    
    
}

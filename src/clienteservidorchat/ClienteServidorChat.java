package clienteservidorchat;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClienteServidorChat extends Application {
    
    private static String tela = "Servidor.fxml";
    private static Stage palco = new Stage();
    
    public void iniciaStage(String stage){
        tela = stage;
        Stage stage1 = new Stage();
        
        //stage1.initStyle(StageStyle.TRANSPARENT);
        stage1.setTitle("Cliente");
        //stage1.getIcons().add(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\EssenciAll.png"));
        //stage1.setResizable(false);
        try {
            start(stage1);
        } catch (Exception ex) {
            Logger.getLogger(ClienteServidorChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/" + tela));
        
        Scene scene = new Scene(root);
        //stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Servidor");
        //stage.getIcons().add(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\EssenciAll.png"));
        //stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        palco = stage;
    }
    
    public void fechaTela(){
        palco.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}

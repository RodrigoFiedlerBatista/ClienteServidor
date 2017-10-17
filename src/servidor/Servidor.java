package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Servidor implements Runnable {
    
    private ServerSocket server;
    private boolean inicializado;
    private boolean executando;
    private Thread thread;
    private ObservableList<Atendente> atendentes;
    
    public ObservableList<Atendente> getAtendentes(){
        return this.atendentes;
    }
    
    public Servidor(int porta) throws Exception {
        atendentes = FXCollections.observableArrayList();
        inicializado = false;
        executando = false;
        open(porta);
    }
    
    private void open(int porta) throws Exception{
        server = new ServerSocket(porta);
        inicializado = true;
        
    }
    
    private void close(){
        for (Atendente atendente : atendentes) {
            try {
                atendente.stop();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        try {
            server.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        server = null;
        inicializado = false;
        executando = false;
        thread = null;
    }
    
    public void start(){
        if (!inicializado || executando) {
            return;
        }
        executando = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public void stop() throws Exception{
        executando = false;
        if (thread != null) {
            thread.join();
        }
    }

    @Override
    public void run() {
        System.out.println("Aguardando Conexão");
        while(executando){
            try {
                server.setSoTimeout(2500);
                Socket socket = server.accept();
                System.out.println("Conexão Estabelecida");
                Atendente atendente = new Atendente(socket);
                atendente.start();
                atendentes.add(atendente);
                
            } catch (SocketTimeoutException e) {
                // ignorar
            } catch (SocketException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                break;
            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                break;
            } catch (Exception e) {
                System.out.println(e);
                break;
            }
        }
        close();
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println("Iniciando Servidor");
        Servidor servidor = new Servidor(2525);
        servidor.start();
        System.out.println("Pressione enter para encerrar o servidor");
        new Scanner(System.in).nextLine();
        System.out.println("Encerrando servidor");
        servidor.stop();
    }
    
}

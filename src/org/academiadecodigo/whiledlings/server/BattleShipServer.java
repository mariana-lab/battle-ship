package org.academiadecodigo.whiledlings.server;

import org.academiadecodigo.whiledlings.Player;
import org.academiadecodigo.whiledlings.Room;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BattleShipServer {

    private final ExecutorService executorService;
    private HashMap<String, Room> rooms;
    private ServerSocket serverSocket;
    private HashMap<String, Player> clients;


    public BattleShipServer() {
        this.rooms = new HashMap<>();
        this.clients = new HashMap<String, Player>();
        this.executorService = Executors.newCachedThreadPool();
        try {
            this.serverSocket = new ServerSocket(4040);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BattleShipServer battleShipServer = new BattleShipServer();

        try {
            while (true) {
                Socket socket = battleShipServer.serverSocket.accept();
                battleShipServer.executorService.submit(new ConnectionHandler(battleShipServer, socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean hasClient(String name) {
        for ( String s : clients.keySet()) {
            System.out.println(s);
        }
        return clients.containsKey(name);
    }

    public void addPlayer(Player player) {
        clients.put(player.getName(), player);
    }

    public void broadcast(String message) {
        for (String name: clients.keySet()) {
            if(!clients.get(name).isPlaying()){
                clients.get(name).send(message);
            }
        }
    }


    //client handler recebe um nome
    //sala de espera com outros jogadores
    //comando para começar a jogar, receber instruções, sair, mandar mensagens ao oponente, ver progresso, ver podium
    //atribui uma sala, hash map room
    //guarda a pontuação máxima num ficheiro, baseado em tempo de decisão/precisão

}

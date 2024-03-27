package View;

import Controller.ThreadCavaleiro;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {

        int permissao = 1;

        Semaphore semaforo = new Semaphore(permissao);

        for (int i = 1; i <= 4; i++) {
            Thread tCavaleiro = new ThreadCavaleiro(semaforo,i);
            tCavaleiro.start();
        }

    }
}

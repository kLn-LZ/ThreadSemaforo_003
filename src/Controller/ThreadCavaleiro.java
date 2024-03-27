package Controller;

import java.util.concurrent.Semaphore;

public class ThreadCavaleiro extends Thread {

    private static boolean tocha = true;
    private static boolean pedraBrilhante = true;

    private Semaphore semaforo;

    private static int[] porta = {1,2,3,4};
    private int idCavaleiro;
    private int corredor = 2000;
    private int corridaBonus = 0;

    public ThreadCavaleiro (Semaphore semaforo, int idCavaleiro) {
        this.semaforo = semaforo;
        this.idCavaleiro = idCavaleiro;
    }

    public void run(){

        corredor();

    }

    private void corredor() {
        int corredorPercorrido = 0;
        int escolha;
        boolean flag = true;

        while(corredorPercorrido < corredor) {
            corredorPercorrido = corredorPercorrido + (((int) Math.random() * 3) + 4) + corridaBonus;

            System.out.println("O Cavaleiro #" + idCavaleiro + " já andou: " + corredorPercorrido + " Metros!"   );

            if(tocha == true && corredorPercorrido >= 500) {
                corridaBonus =+ 2;
                tocha = false;
                System.out.println("O Cavaleiro #" + idCavaleiro + " Conseguiu a Tocha!");
            }
            if(pedraBrilhante == true && corredorPercorrido >= 1500) {
                corridaBonus =+ 2;
                pedraBrilhante = false;
                System.out.println("O Cavaleiro #" + idCavaleiro + " Conseguiu a Pedra Brilhante!");
            }

            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        do {

            escolha = (int) ((Math.random() * 4) + 1);


            for (int j = 0; j < 4; j++){
                if (escolha == porta[j]) {

                    porta[j] = 9;
                    flag = false;
                    System.out.println("O Cavaleiro #" + idCavaleiro + " entrou na porta: " + escolha);
                }
            }
        } while(flag);

        try {
            semaforo.acquire();
            portaFinal(escolha);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforo.release();
        }



    }

    private void portaFinal(int portaEscolhida) {

        int saida = (int) ((Math.random() * 4) + 1);


        if(portaEscolhida == saida) {
            System.out.println("O Cavaleiro #" + idCavaleiro + " Conseguiu sair com vida");
        }
        else{
            System.out.println("Infelizmente o Cavaleiro #" + idCavaleiro + " foi devorado por um monstro");
        }


    }
}

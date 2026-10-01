package com.project;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class Ejercicio0 {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("Tots els microserveis han acabat. Combinant els resultats...");
            }
        });

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable microservice1 = () -> {
            try {
                System.out.println("Microservei 1 processant dades...");
                // Simulem un treball
                Thread.sleep(1000);
                System.out.println("Microservei 1 completat.");
                barrier.await(); // Esperem que els altres fils acabin
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        Runnable microservice2 = () -> {
            try {
                System.out.println("Microservei 2 processant dades...");
                Thread.sleep(1500);
                System.out.println("Microservei 2 completat.");
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        Runnable microservice3 = () -> {
            try {
                System.out.println("Microservei 3 processant dades...");
                Thread.sleep(2000);
                System.out.println("Microservei 3 completat.");
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        executor.submit(microservice1);
        executor.submit(microservice2);
        executor.submit(microservice3);

        // Tanquem l'executor
        executor.shutdown();
    }
}
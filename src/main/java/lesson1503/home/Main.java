package lesson1503.home;

import java.util.concurrent.Exchanger;

    public class Main {
        public static void main(String[] args) {
            // Создаем объект Exchanger для обмена грузами
            Exchanger<String> exchanger = new Exchanger<>();

            // Создаем поток для первого грузовика
            Thread truck1 = new Thread(() -> {
                String cargoC = "C";
                String cargoD = "D";

                try {
                    // отправляем грузовик
                    System.out.println("Грузовик 1 - груз " + cargoC + "  ,  " + cargoD);
                    Thread.sleep(2000);

                    // Обмен грузами
                    String receivedCargo = exchanger.exchange(cargoD);
                    System.out.println("Грузовик 1 получил груз " + receivedCargo);
                    Thread.sleep(2000);

                    // Доставляем оставшийся груз
                    System.out.println("Грузовик 1 доставил груз " + cargoC + " , " + receivedCargo);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            // Создаем поток для второго грузовика
            Thread truck2 = new Thread(() -> {
                String cargoC2 = "C2";
                String cargoD2 = "D2";

                try {
                    // отправляем грузовик
                    System.out.println("Грузовик 2 - груз " + cargoC2 + " , " + cargoD2);
                    Thread.sleep(2000);

                    // Обмен грузами
                    String receivedCargo = exchanger.exchange(cargoC2);
                    System.out.println("Грузовик 2 получил груз " + receivedCargo);
                    Thread.sleep(2000);
                    //cargoC2=receivedCargo;
                    // Доставляем оставшийся груз
                    System.out.println("Грузовик 2 доставил груз " + cargoD2 + " , " + receivedCargo);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            // Запускаем оба потока
            truck1.start();
            truck2.start();

            // Ожидаем, пока оба грузовика завершат свою работу
            try {
                truck1.join();
                truck2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }




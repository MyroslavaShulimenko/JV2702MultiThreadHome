package lesson0103;


    import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
    public class Home0103{

        public static void main(String[] args) {
            final File file = new File("data.txt");
            final Object lock = new Object(); // объект для синхронизации доступа к файлу

            Thread writer = new Thread(() -> {
                synchronized (lock) { // блокируем объект для записи
                    try {
                        FileWriter writer1 = new FileWriter(file);
                        writer1.write("Hello, world!");
                        writer1.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            Thread reader = new Thread(() -> {
                synchronized (lock) { // блокируем объект для чтения
                    try {
                        Scanner scanner = new Scanner(file);
                        System.out.println(scanner.nextLine());
                        scanner.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            writer.start();
            reader.start();

            try {
                writer.join();
                reader.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


//В этом случае мы использовали объект lockдля обнаружения доступа к файлу. Поток, который хочет сохранить в файле, захватывает объект lockпри помощи оператора synchronized. Затем он открывает файл, записывает данные и закрывает файл. Аналогично блокировке и потоку, который хочет отменить файл.
//
//Кроме того, чтобы запустить, что потоки выполняются последовательно, мы используем метод join(), который вызывает возникновение потока (в случае возникновения, главного потока) ожидания, пока потоки writerи readerзавершают свою работу.
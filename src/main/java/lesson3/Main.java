package lesson3;

//1. Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;

//2. Последовательно сшить 5 файлов в один (файлы примерно 100 байт).
// Может пригодиться следующая конструкция: ArrayList<InputStream> al = new ArrayList<>();
// ... Enumeration<InputStream> e = Collections.enumeration(al);

//3. Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb).
// Вводим страницу (за страницу можно принять 1800 символов), программа выводит ее в консоль.
// Контролируем время выполнения: программа не должна загружаться дольше 10 секунд,
// а чтение – занимать свыше 5 секунд.

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        read50byteFileAndSoutIt();
    }

    static void read50byteFileAndSoutIt() {
        File file50bytes = new File("src\\main\\resources\\50bytesFile.txt");

        if (!file50bytes.exists()) {
            System.out.println("File: " + file50bytes.getPath() + " not exist!");
            return;
        }

        byte[] buf = new byte[50];
        try (FileInputStream in = new FileInputStream(file50bytes)) {
            byte[] arr = new byte[512];
            int x;
            while ((x = in.read(arr)) > 0) {
                System.out.print(new String(arr, 0, x, "UTF-8"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

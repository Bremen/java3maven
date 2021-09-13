package lesson3;

//1. Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;

//2. Последовательно сшить 5 файлов в один (файлы примерно 100 байт).
// Может пригодиться следующая конструкция: ArrayList<InputStream> al = new ArrayList<>();
// ... Enumeration<InputStream> e = Collections.enumeration(al);

//3. Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb).
// Вводим страницу (за страницу можно принять 1800 символов), программа выводит ее в консоль.
// Контролируем время выполнения: программа не должна загружаться дольше 10 секунд,
// а чтение – занимать свыше 5 секунд.

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        read50byteFileAndSoutIt();
        concat5filesInOne();
        readPageByPageLargeTextFile();
    }

    private static void readPageByPageLargeTextFile() {
        File file10MbText = new File("src\\main\\resources\\10MbFile.txt");
        int pageSize = 1800;

        try (RandomAccessFile raf = new RandomAccessFile(file10MbText, "r")) {
            long pageCount = (long) Math.ceil((double) raf.length() / pageSize);
            System.out.printf("Введите номер страницы (всего страниц: %d): ", pageCount);

            Scanner scn = new Scanner(System.in);
            int page = scn.nextInt();

            if (page <= 0 && pageCount < page) {
                System.out.println("Введенный номер страницы вне диапазона.");
                return;
            }

            raf.seek(pageSize * (page - 1));
            byte[] buf = new byte[pageSize];

            int readCount = raf.read(buf, 0, pageSize);
            System.out.print(new String(buf, 0, readCount, "UTF-8"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void concat5filesInOne() {
        ArrayList<InputStream> ins = new ArrayList<>();

        try {
            ins.add(new FileInputStream("src\\main\\resources\\concatFiles\\concat1.txt"));
            ins.add(new FileInputStream("src\\main\\resources\\concatFiles\\concat2.txt"));
            ins.add(new FileInputStream("src\\main\\resources\\concatFiles\\concat3.txt"));
            ins.add(new FileInputStream("src\\main\\resources\\concatFiles\\concat4.txt"));
            ins.add(new FileInputStream("src\\main\\resources\\concatFiles\\concat5.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        SequenceInputStream in = new SequenceInputStream(Collections.enumeration(ins));

        File concatResultFile = new File("src\\main\\resources\\concatFiles\\concatResult.txt");
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(concatResultFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        byte[] buf = new byte[50];
        int countRead;
        try {
            while (true) {
                if (!((countRead = in.read(buf)) != -1)) break;
                out.write(buf, 0, countRead);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    static void read50byteFileAndSoutIt() {
        File file50bytes = new File("src\\main\\resources\\50bytesFile.txt");

        if (!file50bytes.exists()) {
            System.out.println("File: " + file50bytes.getPath() + " not exist!");
            return;
        }

        try (FileInputStream in = new FileInputStream(file50bytes)) {
            byte[] arr = new byte[50];
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

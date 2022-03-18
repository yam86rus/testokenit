package com.example.testokenit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ReadFileImpl implements ReadFileService {
    @Autowired
    private ProductService productService;

    @Autowired
    private PriceService priceService;

    @Autowired
    ReadFileService readFileService;

    @Value("${upload.path}")
    private String path;

    @Override
    public boolean saveLogs(String message) {
        File file = new File("logs.txt");

        // Создание файла
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Создание объекта FileWriter
        FileWriter writer = null;
        try {
            writer = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Запись содержимого в файл
        try {
            writer.write(message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getFilePath(String path) {
        File dir = new File(path);

        // Сохраняем только файлы с раcширением 'csv'
        List<String> list = new ArrayList<>();
        for (File file : dir.listFiles()) {
            if (file.getName().substring(file.getName().lastIndexOf(".")).equals(".csv")) {
                list.add(file.getName());
            }
        }
        String result = path + list.get(0);
        return result;
    }

    @Override
    public boolean isValid() {
        File dir = new File(path);

        // Сохраняем только файлы с раcширением 'csv'
        List<String> list = new ArrayList<>();
        for (File file : dir.listFiles()) {
            if (file.getName().substring(file.getName().lastIndexOf(".")).equals(".csv")) {
                list.add(file.getName());
            }
        }
        System.out.println(list);

        // Если больше 1-ого файла, то пишем в лог и завершаем работу
        if (list.size() > 1) {
            saveLogs("Файлов с расширением csv больше чем 1, непонятно какой обрабатывать. " + LocalDateTime.now());
            return false;
        }

        // Если меньше 1-ого, то пишем в лог и завершаем работу
        if (list.size() == 0) {
            saveLogs("Файлов для загрузки не обнаружено. " + LocalDateTime.now());
            return false;
        }

        saveLogs("Файл найден! Загрузка данных из файла произведена. " + LocalDateTime.now());
        return true;

    }

    @Override
    public List<String[]> readFromFile(String path) {

        // Получаем полный путь до файла, с указанием имени
        String csvFile = getFilePath(path);
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        List<String[]> list = new ArrayList<>();

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] row = line.split(cvsSplitBy);
                list.add(row);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    @Override
    public boolean writeToDb(List<String[]> data) {
        // Заполняем две таблицы (products, prices)
        for (String[] strings : data) {
            System.out.println(Arrays.toString(strings));
        }
        // Вносим в БД информацию в таблицу products
        for (String[] strings : data) {
            productService.addSomeProduct(strings[1], strings[0]);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
        LocalDateTime dateTime;

        // Вносим в БД информацию в таблицу prices
        for (String[] strings : data) {
            System.out.println(strings[0]);
            dateTime = LocalDateTime.parse(strings[4], formatter);
            priceService.addSomeProducts(strings[2], Double.parseDouble(strings[3]), dateTime, strings[0]);
        }

        return true;
    }

    // Выполнение задачи загрузки данных по расписанию
    @Scheduled(cron = "${interval-in-cron}")
    public void uploadData() {
        if (readFileService.isValid()) {
            readFileService.writeToDb(readFileService.readFromFile(path));
            deleteFile(path);
        }

    }

    public boolean deleteFile(String path) {
        try {
            Files.deleteIfExists(Path.of(getFilePath(path)));
            saveLogs("Файл успешно удален "+ LocalDateTime.now());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}

package com.example.testokenit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
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

    @Value("${upload.path}")
    private String path;

    @Scheduled(cron = "${interval-in-cron}")
    public void read() {

        System.out.println("Запуск по расписанию. Метод read класса ReadFile - выполнен");
        System.out.println("Путь до файла: " + path);

    }

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
            saveLogs("Файлов больше чем 1, непонятно какой обрабатывать. " + LocalDateTime.now());
            System.out.println("Файлов больше чем 1, непонятно какой обрабатывать. " + LocalDateTime.now());
            return false;
        }

        saveLogs("Все успешно обработано! Ошибок нет! " + LocalDateTime.now());
        System.out.println("Все успешно обработано! Ошибок нет!");
        return true;

    }

    @Override
    public List<String[]> readFromFile(String path) {
        String csvFile = "/home/app/upload/Нужный файл3.csv";
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
        System.out.println("//--//--//--//--//--//--");

        System.out.println("Добавляем продукт (products.name) в таблицу products");
        for (String[] strings : data) {
            productService.addSomeProduct(strings[1]);
        }
        System.out.println("//--//--//--//--//--//--");
        System.out.println("Добавляем в таблицу prices цену, дату, product_id");


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime;

        for (String[] strings : data) {
            System.out.println(strings[0]);
            dateTime = LocalDateTime.parse(strings[3], formatter);
            priceService.addSomeProducts(Double.parseDouble(strings[2]), dateTime, strings[0]);
        }

        return true;
    }


}

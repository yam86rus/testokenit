Тестовое задание

Реализовать приложение на стеке технологий: Spring Boot 2, Java 8, PostgreSQL.
Таблицы должны создаваться автоматически в БД при первом старте приложения.
Структура таблиц в БД

* Таблица «Товар». Хранит название товара. Колонки: id, name. 
* Таблица «Цена товара». Хранит цену на товар и дату, с которой цена актуальна. По каждому товару может быть несколько цен с разными датами. Колонки: id, price, datetime, product_id.
      Функционал приложения 
* Загрузка товаров и цен из csv-файла. Приложение должно уметь загружать данные из csv-файла. Путь директории с файлами настраивается в конфигурационном файле приложения. Пример формат данных в csv-файла: product_id;product_name;price_id;price;price_datetime.
      В логах должен быть отмечен факт старта обработки файла и результат обработки файла с количеством обработанных записей (товаров и цен). Загрузка файла стартует по расписанию, время настраивается в конфигурационном файле. После успешной обработки файл удалить. 
* Получение списка товаров и актуальных цен. Приложение должно предоставлять REST метод, возвращающий из БД список товаров с ценами, актуальными на указанный в запросе день. GET /products?date=yyyy-mm-dd.  Формат - список 
* Получение статистики. Приложение должно предоставлять REST метод, возвращающий статистику по загруженным товарам и ценам. GET /products/statistic. Параметры статистики:
      •	Количество товаров в БД. Формат - просто цифра.
      •	Как часто менялась цена товара. Группировка по товарам. Формат - список
      {"name": "Товар 1", "frequency": 2}
      •	Как часто менялась цена товара. Группировка по дням. Формат - список
      {"date": "yyyy-mm-dd", "frequency": 6}

### Требования
      Код проекта на github.
      Файл README.md с описанием запросов и примерами ответов в JSON.


### Для запуска приложения необходимо:

Создать пользователя и саму бд (Postgres). В файле src/main/resources/sql/create_user_and_db.sql подготовлены команды.

Таблицы создаст liquibase, при первом запуске.

### Загрузка товаров и цен через csv-файл.

Файл должен быть помещен в папку, указанную в application.properties (сейчас этот путь: /home/app/upload/)
Структура scv файла:
product_id, product_name, price_id, price, price_datetime

* product_id - id таблицы products
* product_name - name таблицы products
* price_id - id таблицы prices
* price - price таблицы prices
* price_datetime - datetime таблицы prices

Пример данных в файле:

`1;Табурет;2;1499;2022-03-17T07:00:00Z`

### Выполнение задачи по расписанию

Время расписания задается в application.properties. Сейчас выставлено выполнение через каждый час

Для тестирования функции загрузки создан отдельный эндпоинт /api/loadData, который запускает процедуру загрузки данных из файла
### Логи

Данные об операциях пишутся в файл logs.txt

### Описание запросов и примеры ответов

* Получение списка товаров и актуальных цен, актуальными на указанный в запросе день:
  GET /api/products/?date=yyyy-mm-dd

Response (example)

```json
[
  {
    product: "системный блок",
    price: 36800
  },
  {
    product: "системный блок",
    price: 36800
  },
  {
    product: "системный блок",
    price: 36800
  },
  {
    product: "системный блок",
    price: 36800
  },
  {
    product: "мышка",
    price: 500.99
  },
  {
    product: "монитор",
    price: 15000
  },
  {
    product: "монитор",
    price: 15000
  }
]
```

#### Получение статистики

* Количество товаров: /api/products/statistic/products

Response (example)

```json
4
```

* Как часто менялась цена товара. Группировка по товарам: /api/products/statistic/changesByName

Response (example)

```json
[
  {
    name: "монитор",
    frequency: 10
  },
  {
    name: "системный блок",
    frequency: 10
  },
  {
    name: "мышка",
    frequency: 25
  }
]
```

* Как часто менялась цена товара. Группировка по дням: api/products/statistic/changesByDate

Response (example)

```json
[
  {
    date: "2022-03-17",
    frequency: 15
  },
  {
    date: "2022-03-16",
    frequency: 15
  },
  {
    date: "2022-03-15",
    frequency: 15
  }
]
```





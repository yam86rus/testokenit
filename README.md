Тестовое задание на позицию Java Developer (Junior)

Для запуска приложения ниобходимо:

Создать пользователя и саму бд. В файле  src/main/resources/sql/create_user_and_db.sql
подготовлены команды. В проекте используется библиотека liquibase, которая сама создаст все необходимые таблицы и связи между ними.
1) Загрузка товаров и цен через csv-файл. Файл должен быть помещен по адресу, указанному в application.properties (сейчас этот путь: )
2) Получение списка товаров и актуальных цен: localhost:8086/api/products/yyyy-mm-dd
3) Получение статистики
- Количество товаров: localhost:8086/api/products/statistic/products
- Как часто менялась цена товара. Группировка по товарам: localhost:8086/api/products/statistic/changesByName
- Как часто менялась цена товара. Группирока по дням: localhost:8086//products/statistic/changesByDate    





# Кроссплатформенная разработка

## JAVA
### Задание 1
Создайте сайт на выбранную вами тематику, для этого используйте java spring и любую удобную вам субд

Требования к сайту:
- Должно быть реализовано 2 страницы: главная и регистрация/авторизация пользователя
- На главной странице должен быть футер и хедер, они должны содержать внутри, как минимум, 6 элементов( ссылки, лого, заголовки и т.д)
- В содержании сайта должно быть хотя бы одно изображение.

<img src="/Tasks_1-2-3/scrinshots/2.jpg" width="500" height="390">
<img src="/Tasks_1-2-3/scrinshots/1.jpg" width="500" height="225">

### Задание 2
Добавьте адаптивную верстку на сайт, который создали в первом задании.

<img src="/Tasks_1-2-3/scrinshots/3.jpg" width="500" height="900">

### Задание 3
Создайте юнит тесты для методов регистрации/авторизации пользователей.
Юнит тесты должны покрывать логику( проверка сложность пароля, уникальности логина, корректность почты и т.д) и работу с базой данных.
Для выполнения данного задания нужно изучить, как можно взаимодействовать с бд во время юнит тестов(посмотрите DataJpaTest и  мокирование)

<img src="/Tasks_1-2-3/scrinshots/4.jpg">
<img src="/Tasks_1-2-3/scrinshots/5.jpg">

## Doker
### Задание 3
Напишите dockerfile, который создаст контейнер с любым java проектом. Вы можете использовать проект любой сложности для данного задания. 

После написания Dokerfile собираем образ
```bash
docker build -t password-generator .
```

Запуск с длиной пароля по умолчанию
```bash
docker run --rm password-generator  
🔐 Сгенерированный пароль: 9!G%u4M?,cd
```

Запуск с указанием длины
```bash
docker run --rm password-generator 30
🔐 Сгенерированный пароль: e[8IZhm+JcDm1k(6vX$)PFMlA5xh+D
```

### Задание 4
Создайте docker-compose файл, который будет связывать Java проект, который содержит функции для crud операций над таблицей в бд и базу данных. Java проект должен иметь возможность связываться с бд и работать с ней.

Запус контейнера
```bash
PS F:\GitHub\CrossPlatform-Tasks\DockerTask4> docker compose up
Attaching to java_crud, pg_db
Container pg_db Waiting 
pg_db  | 
pg_db  | PostgreSQL Database directory appears to contain a database; Skipping initialization
pg_db  | 
pg_db  | 2026-05-05 17:26:38.258 UTC [1] LOG:  starting PostgreSQL 18.3 (Debian 18.3-1.pgdg13+1) on x86_64-pc-linux-gnu, compiled by gcc (Debian 14.2.0-19) 14.2.0, 64-bit
pg_db  | 2026-05-05 17:26:38.258 UTC [1] LOG:  listening on IPv4 address "0.0.0.0", port 5432
pg_db  | 2026-05-05 17:26:38.258 UTC [1] LOG:  listening on IPv6 address "::", port 5432
pg_db  | 2026-05-05 17:26:38.266 UTC [1] LOG:  listening on Unix socket "/var/run/postgresql/.s.PGSQL.5432"
pg_db  | 2026-05-05 17:26:38.276 UTC [32] LOG:  database system was shut down at 2026-05-05 17:25:20 UTC
pg_db  | 2026-05-05 17:26:38.283 UTC [1] LOG:  database system is ready to accept connections
Container pg_db Healthy 
java_crud  | ✅ Успешное подключение к PostgreSQL
java_crud  | 📝 Таблица 'users' создана/проверена.
java_crud  | ➕ Запись добавлена.
java_crud  | 👀 Чтение записей:
java_crud  |   ID: 36 | Name: Akiro | Email: akiro@example.com
java_crud  | ✏️ Запись обновлена.
java_crud  | 🗑️ Запись удалена.
java_crud  | 🚀 Все CRUD-операции выполнены успешно!
java_crud exited with code 0
```
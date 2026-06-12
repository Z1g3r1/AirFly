# AirFly – Система управления авиаперевозками и билетами

Полноценная система управления авиаперевозками и билетами с веб-интерфейсом и REST API.

## 🚀 Ключевые возможности

- **Регистрация и аутентификация** – Spring Security, роли ADMIN и USER, смена пароля в личном кабинете.
- **Покупка билетов** – выбор рейса, ввод данных пассажира, валидация паспорта и возраста.
- **Админ-панель** – управление рейсами, пассажирами, просмотр всех билетов, удаление с возвратом мест.
- **Документированное REST API** – Swagger UI с описанием всех эндпоинтов и возможностью тестирования.

## 🛠 Технологический стек

Java 21, Spring Boot, Spring Security, JPA/Hibernate, PostgreSQL, H2, Thymeleaf, Bootstrap, Docker, Maven, Git, Render, Swagger, Mockito.

## 🔗 Живое демо

- **Веб-сайт:** [aircompany-o3cj.onrender.com](https://aircompany-o3cj.onrender.com)
- **Swagger UI:** [https://aircompany-o3cj.onrender.com/swagger-ui.html](https://aircompany-o3cj.onrender.com/swagger-ui.html)

## 📥 Как запустить локально

1. Клонируй репозиторий: `git clone https://github.com/Z1g3r1/AirFly.git`
2. Убедись, что установлен JDK 21 и Maven.
3. Запусти приложение с профилем `dev`: `mvn spring-boot:run -Dspring-boot.run.profiles=dev`
4. Открой в браузере `http://localhost:8080`.

## 📄 Лицензия

MIT
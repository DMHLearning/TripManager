# TripManager - Информационная система турфирмы

**TripManager** – это система для управления клиентами, турами и бронированиями в турфирме.  
Проект построен с использованием **микросервисной архитектуры** и REST API.

## Описание проекта

TripManager предназначен для автоматизации работы туристической фирмы и организации туров.  
Система позволяет регистрировать клиентов, добавлять туры и управлять бронированиями.  
TripManager может быть полезен в следующих областях:

- **Малые и средние туристические агентства** – для ведения базы клиентов, автоматизации бронирований и учета свободных мест.
- **Корпоративные программы отдыха** – позволяет управлять групповыми турами и организовывать корпоративные поездки.
- **Стартапы в сфере туризма** – система легка в настройке и развертывании, что делает её хорошим выбором для новых проектов.

## Архитектура микросервисов

Проект построен по микросервисной архитектуре и включает три основных микросервиса:
- **ClientMicroservice** – отвечает за управление клиентами: добавление новых клиентов, поиск по email и удаление из базы.
- **TourMicroservice** – управляет турами: позволяет создавать новые туры с указанием стоимости, продолжительности и количества мест. Также позволяет удалять туры и обновлять информацию.
- **BookingMicroservice** – занимается бронированием туров. Этот микросервис связывает клиентов с турами, проверяет доступность мест и создает бронирование.

Микросервисы взаимодействуют через REST API и могут работать независимо друг от друга. Это позволяет легко масштабировать и обновлять каждый компонент системы без остановки работы всей системы.

## REST API эндпоинты

### **Клиенты (Clients)**
| Метод  | Эндпоинт                  | Описание                            | Пример тела запроса / Пример вызова                                                      |
|--------|---------------------------|-------------------------------------|------------------------------------------------------------------------------------------|
| **POST**   | `/clients`                 | Добавить нового клиента              | ```json { "name": "Джон Уик", "email": "john@example.com", "phone": "+12345678901" } ``` |
| **GET**    | `/clients`                 | Получить всех клиентов               | -                                                                                        |
| **DELETE** | `/clients/:email`          | Удалить клиента по email             | `DELETE /clients/john@example.com`                                                       |

### **Туры (Tours)**
| Метод  | Эндпоинт                  | Описание                            | Пример тела запроса / Пример вызова                                                                                                               |
|--------|---------------------------|-------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------|
| **POST**   | `/tours`                   | Добавить новый тур                   | ```json { "name": "Горное приключение", "description": "Крутое приключение для любителей гор!", "price": 399.99, "duration": 5, "seats": 10 } ``` |
| **GET**    | `/tours`                   | Получить список всех туров           | -                                                                                                                                                 |
| **DELETE** | `/tours/:name`             | Удалить тур по названию              | `DELETE /tours/Горное приключение`                                                                                                                |

### **Бронирования (Bookings)**
| Метод  | Эндпоинт                  | Описание                            | Пример тела запроса / Пример вызова                          |
|--------|---------------------------|-------------------------------------|--------------------------------------------------------------|
| **POST**   | `/bookings`                | Добавить бронирование                | ```json { "clientEmail": "john@example.com", "tourName": "Mountain Adventure", "bookingDate": "2024-07-15", "seatsReserved": 2 } ``` |
| **GET**    | `/bookings`                | Получить все бронирования            | -                                                            |
| **DELETE** | `/bookings`                | Удалить бронирование                 | ```json { "clientEmail": "john@example.com", "tourName": "Mountain Adventure" } ``` |

## Описание директории examples
Директория `examples` содержит реализации паттернов проектирования:

- **structural (Структурные паттерны):**
    - `TourWithDiscountDecorator` – декоратор для добавления скидки к туру.
    - `ClientAdapter` – адаптер для интеграции устаревших данных клиентов.

- **behavioral (Поведенческие паттерны):**
    - `BookingInvoker` – инвокер для команд.
    - `AddBookingCommand` – команда для добавления бронирований.
    - `TourNotifier` – уведомитель для новых туров.

- **creational (Порождающие паттерны):**
    - `TourFactory` – фабричный метод для создания туров.
    - `BeachTourFactory`, `MountainTourFactory` – фабрики для создания конкретных туров.

## Зависимости
Данный проект использует следующие библиотеки:

- Spark Framework Core 2.7.2
- Gson 2.8.9
- JUnit 5.10.0

## Сборка и развёртывание проекта
Для сборки проекта необходимо выполнить следующие шаги:

1. Клонировать репозиторий:
    ```bash
    git clone
    ```
   
2. Перейти в директорию проекта:
    ```bash
    cd TripManager
    ```
   
3. Собрать проект с помощью Gradle:
    ```bash
    gradle build
    ```
   
4. Запустить проект:
    ```bash
    java -jar app/build/libs/app-1.1-SNAPSHOT.jar
    ```
   или при помощи вашей IDE разверните локально основной проект со стартовым путём класса `app.Main`.


5. Перейти по адресу `http://localhost:8080` для доступа к REST API.
6. Для доступа к пользовательскому интерфейсу, запустите `frontend/dev/main.html` в браузере. Если же вы запустили проект локально в IDE, то интерфейс можно открыть также по адресу `http://localhost:63342/TripManager/frontend/dev/main.html`.
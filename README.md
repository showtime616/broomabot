# broomabot

1. Чтобы собрать приложение нужно выполнить команду mvn clean package (должен бысть установлен Maven)
   или можно взять готовую war-ку из папки deploy
2. Создать новую БД (PostgreSQL)  
3. В application.properties нужно указать необходимые конфиги БД:
 а) spring.datasource.url - путь к БД 
 б) spring.datasource.username - логин
 в) spring.datasource.password - пароль
4. Нужно указать данные бота. 
   Я зарегистрировал бота с именем "broomabot", имя и токен указаны в application.properties актуальные.
   Либо можно создать своего бота, тогда надо будет поменять следующие конфиги в application.properties:
   а) bot.token - токен
   б) bot.username - имя бота
5. После запуска приложения, найти в телеграме "broomabot" и можно начинать пользоваться
6. Команды: 
/start - активирует бота для пользователя
/my - команда показывает список всех сообщений пользователя
/clean - очищает все сообщения пользователя
 
ВАЖНО: Т.к. телеграм в нашей замечательной стране заблокирован, нужно использовать VPN либо прокси на машине, на которой будет запускаться приложение

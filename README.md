project_musina_9

Проект создан на основе Maven. В пакете src создаем подпакет Data, в нем программа, отвечающая за манипуляцию с данными scv.
![image](https://user-images.githubusercontent.com/122260456/211339876-b3352275-ed4e-472b-b34b-10bce5ba58b5.png)

Объявляем класс Forbes для хранения информации, класс ForbesManipulation для ее обработки:
![image](https://user-images.githubusercontent.com/122260456/211349110-c40fb9e7-b9a2-4aae-b950-7d26a6d41422.png)

Заполняем поля для работы в классе Forbes:
![image](https://user-images.githubusercontent.com/122260456/211362303-59403373-b396-46f2-9a11-48814f95ac5c.png)

В классе ForbesManipulation импортируем:
![image](https://user-images.githubusercontent.com/122260456/211366944-62e83c93-b3cb-4ff0-907c-c2d0483b496c.png)

Для работы с SQLite в классе ForbesManipulation используем интерфейс Connection, он позволяет считывать и манипулировать данными, хранимыми в БД.

Интерфейс Statement нужен для выполнения и возврата запросов, метод ResultSet выдает нам значение запроса.

Для каждого из заданий написан свой метод, в котором открывается собственный Statement (в конце каждого метода важно не забыть закрыть Statement).

Выгружаем данные в БД:
![image](https://user-images.githubusercontent.com/122260456/211363670-4cb25ab4-1a28-4949-8fd9-8b76257456d0.png)

Используем данные для решения задачи:
![image](https://user-images.githubusercontent.com/122260456/211364837-633edd04-ffa2-4a5c-a8af-c14663fb94fa.png)

В классе Main создаем экземпляр класса для манипуляции данными и вызываем методы, относящиеся к заданиям:
![image](https://user-images.githubusercontent.com/122260456/211345947-829ac844-22b0-4cfc-9771-95b84a423090.png)

Подключаем соединение с БД, используем его в конструкторе и закрываем в конце программы.

Также можно ввести данные по-новому из датасета, но это происходит в конструкторе класса ForbesManipulation.




ПЕРВОЕ ЗАДАНИЕ: ![image](https://user-images.githubusercontent.com/122260456/211340276-f1f4c5d0-a6cf-4d55-aa9f-9f5f6376f8ab.png)
ВТОРОЕ/ТРЕТЬЕ ЗАДАНИЕ: ![image](https://user-images.githubusercontent.com/122260456/211340996-48db333f-08d4-4091-95e3-f8e891e04599.png)

[файлы проекта лежат в ветви master]

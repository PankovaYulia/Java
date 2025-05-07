## <h2 align="center">Лаболаторные работы</h2>

<h2 align="center">1</h2>

<h3 align="center">
  Создание класса контейнера
</h3>
<p>
  Создать класс контейнер, позволяющий хранить произвольное количество целых чисел. Использование встроенных коллекций запрещено.  
  Задание можно реализовать с помощью массива или связанного списка. Контейнер должен позволять добавлять, извлекать, удалять элементы
</p>

<div align="center">
  <a href="https://github.com/PankovaYulia/Java/tree/main/Conteiner">Перейти к лабораторной 1</a>
</div>

##

<h2 align="center">2</h2>

<h3 align="center">
  Разбор выражения и вычисление его значения
</h3>
<p>
  Выражение может содержать числа, знаки операций, скобки. В случае, если выражение записано корректно, вычислить значение, в противном случае — вывести сообщение об ошибке.
  Дополнительно приветствуется поддержка имен переменных и различных функций. 
  В случае, если есть переменные, их значения нужно запросить у пользователя (для каждой из них — по одному разу).
</p>

<div align="center">
  <a href="https://github.com/PankovaYulia/Java/tree/main/laba2">Перейти к лабораторной 2</a>
</div>

##

<h2 align="center">3</h2>

<h3 align="center">
 Работа с коллекциями
</h3>
<p>
Сравнить производительность ArrayList и LinkedList
Необходимо написать код, который бы вызывал основные методы коллекций определенное(1000 или 2000, или любое другое) количество раз. При этом должно засекаться время.
Минимально необходимо протестировать методы:add, delete, get
После этого вывести таблицу с результатами(метод, сколько раз выполнялся, время выполнения)
</p>

<div align="center">
  <a href="https://github.com/PankovaYulia/Java/tree/main/laba3">Перейти к лабораторной 3</a>
</div>

##

<h2 align="center">4</h2>

<h3 align="center">
 Работа с коллекциями 2
</h3>

Человек с полями

*  ID
*  Имя
*  Пол
*  Подразделение(Сущность подразделение)
*  Зарплата
*  Дата рождения

Подразделение с полями:

* ID(генерится в програме)
* Название

Дан CSV файл(архив с ним есть внутри задания), который содержит в себе информацию о людях. <br/>
Нужно считать данные о людях из этого файла в список<br/>
В этой задаче нужно пользоваться встроенными Java  коллекциями<br/>
Для работы с CSV файлом рекомендую использовать библиотеку opencsv(НО можете и без нее - это на ваше усмотрение)<br/>
Ее можно либо скачать в виде jar  файла и подключить к проекту если не используете maven, либо подключить как maven зависимость<br/>
Чтение из файла с помощью этой библиотеки может выглядеть так:<br/>

```java
 try (InputStream in = getClass().getClassLoader().getResourceAsStream(csvFilePath);
                CSVReader reader = in == null ? null : new CSVReader(new InputStreamReader(in), separator)) {
            if (reader == null) {
                throw new FileNotFoundException(csvFilePath);
            }
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
           //А тут работаете с nextLine котрый представляет из себя текущую строчку в файле, уже разбитую на массив по разделителю separator
//Попробуйте просто вывести на экран этот nextLine и, думаю, все будет понятно
            }
```

csvFilePath - путь к файлу. Файл закидывайте в ресурсы. Если не получится, то укажите уж тогда полный путь.<br/>
separator - разделитель. <br/>

* В нашем случае  - ';'

Т.е. на выходе у вас должен получиться объект типа List в котором будут находится люди из файла.  <br/>
Т.е. нужно не просто прочитать файл и вывести его на экран, а именно получить список<br/>

* ID подразделения можете сами генерировать<br/>

<div align="center">
  <a href="https://github.com/PankovaYulia/Java/tree/main/Laba4">Перейти к лабораторной 4</a>
</div>

##

<h2 align="center">5</h2>

<h3 align="center">
 Рефлексия (di)
</h3>

1. Создать аннотацию @AutoInjectable
2. Разобраться(поискать в Google) с классом Properties
3. Создать класс Injector в котором был бы параметризированный метод inject, который принимал бы в  <br/>
   качестве параметра объект любого класса и, используя механизмы рефлексии осуществлял поиск полей, помеченных <br/>
   этой аннотацией(в качестве типа поля используются некоторый интерфейс), и осуществлял бы инициализацию этих полей <br/>
   экземплярами классов, которые указаны в качестве реализации соответствующего интерфейса в некотором файле настроек(properites) <br/>

Пример: <br/>
У нас есть несколько интерфейсов и несколько классов, реализующих эти интерфейсы: <br/>

```java
interface SomeInterface{
  public void doSomething();
}
interface SomeOtherInterface{
  public void doSomeOther();
}
class SomeImpl implements SomeInterface{
  public void doSomething(){ println(“A”);}
}
class OtherImpl implements SomeInterface{
  public void doSomething(){ println(“B”);}
}
class SODoer implements SomeOtherInterface{
  public void doSomething(){ println(“C”);}
}
class SomeBean{
  @AutoInjectable
  private SomeInterface field1;
  @AutoInjectable
  private SomeOtherInterface field2;

  public void foo(){
    field1.doSomething();
    field2.doSomething();
  }
}
```

Ваша задача написать класс Injector, который бы осуществлял внедрение зависимостей в любой объект ,  <br/>
который содержит поля, помеченные нашей аннотацией. Т.е. некоторый метод этого класса, принимал бы произвольный объект, <br/>
исследовал бы существующие в нем поля, и смотрел, аннотированы ли они нужной аннотацией. <br/>
Если да, то тогда он бы смотрел тип этого поля и искал бы реализацию в файле properties: <br/>

Пример файла:
```
somepackage.SomeInterface=somePackage.SomeImpl
somepackage.SomeOtherInterface=somepackage.SODoer
```

После этого, он создавал бы экземпляр нужного класса и записывал ссылку на этот экземпляр в нужное поле.  <br/>
Т.е. для нашего примера метод inject должен в поле field1 записать экземпляр класса SomeImpl, а в поле field2 класса SODoer. <br/>
Теперь, инициализация объектов класса SomeBean будет выглядеть следующим образом: <br/>

```java
SomeBean sb =(new Injector()).inject(new SomeBean());
sb.foo();
```

И теперь никаких ошибок быть не должно, а на экран должно вывестись AC <br/>
Если же мы поменяем в файле properties строчку
```
somepackage.SomeInterface=somePackage.SomeImpl
```
на
```
somepackage.SomeInterface=somePackage.OtherImpl
```
Должно вывестись BC

<div align="center">
  <a href="https://github.com/PankovaYulia/Java/tree/main/laba5">Перейти к лабораторной 5</a>
</div>



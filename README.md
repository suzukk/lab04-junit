# Lab 04 — JUnit 5 Unit Testing

## Оюутны мэдээлэл

* **Нэр:** С.Тэргэл
* **Оюутны код:** B232270011

## Ашигласан технологи

* Java: OpenJDK 17.0.20.1
* Maven: 3.9.16
* JUnit 5 (Jupiter): 5.10.2
* Maven Surefire Plugin: 3.2.5
* OS: Arch Linux

## Тестийн үр дүн

Төслийн `GradeCalculator` классын `letterGrade()` болон `totalScore()` method-уудыг JUnit 5 ашиглан тестэлсэн. Нийт **11 test method** бичиж, parameterized test-үүдийн хамт **23 test case** ажиллуулсан. Эцсийн тестийн үр дүн `Tests run: 23, Failures: 0, Errors: 0, Skipped: 0` бөгөөд `BUILD SUCCESS` болсон. Тестүүдэд ердийн утга, хязгаарын утга, буруу утга болон exception үүсэх нөхцөлүүдийг шалгасан. Мөн `letterGrade()` method-ийн `90` онооны заагийг шалгах boundary test оруулсан. Mutation testing хийхдээ `score >= 90` нөхцөлийг зориудаар `score > 90` болгон өөрчилсөн бөгөөд `90` онооны тест mutation-ийг илрүүлж, тестийн ажиллалт `BUILD FAILURE` болсон. Дараа нь эх кодыг сэргээж, бүх тестийг дахин ажиллуулахад 23 тест бүгд амжилттай болсон. Энэ лабораторийн хамгийн сонирхолтой хэсэг нь `90` онооны boundary test нь жижигхэн кодын өөрчлөлтийг шууд илрүүлж чадсан явдал байлаа.

## Тестийн бүтэц

`GradeCalculatorTest` класст дараах төрлийн тестүүдийг хэрэгжүүлсэн:

* 95 → A
* 85 → B
* 75 → C
* 65 → D
* 30 → F
* 90 → A
* 89.99 → B
* 60 → D
* 59.99 → F
* 0 → F
* 100 → A
* Буруу оноо (`-1`, `101`) үед `IllegalArgumentException`
* `totalScore()`-ийн зөв нийлбэр
* `totalScore()`-ийн буруу утгууд
* `@ParameterizedTest` ашигласан boundary болон calculation тестүүд

## Mutation Testing

Mutation хийхийн өмнөх зөв нөхцөл:

```java
if (score >= 90)
```

Mutation хийх үед:

```java
if (score > 90)
```

болгон өөрчилсөн.

Mutation-ийн дараа тестийн үр дүн:

```text
Tests run: 23, Failures: 1, Errors: 0, Skipped: 0
BUILD FAILURE
```

Энэ нь `90` оноо `A` байх ёстой гэсэн boundary test mutation-ийг илрүүлж байгааг харуулсан.

Mutation testing-ийн үр дүнг:

* `results/mvn-test-mutant.txt`

Final green test-ийн үр дүнг:

* `results/mvn-test.txt`

файлуудад хадгалсан.

# Lab 04 — JUnit 5 Unit Testing

## Оюутны мэдээлэл

* **Нэр:** С.Тэргэл
* **Оюутны код:** B232270011

## Ашигласан технологи

* Java: OpenJDK 17.0.20.1
* Maven: Apache Maven 3.9.16
* JUnit Jupiter: 5.10.2
* Maven Surefire Plugin: 3.2.5
* OS: Arch Linux

## Java хувилбар

`java -version` командын гаралт:

```text
openjdk version "17.0.20.1" 2026-08-18
OpenJDK Runtime Environment (build 17.0.20.1+1)
OpenJDK 64-Bit Server VM (build 17.0.20.1+1, mixed mode, sharing)
```

## Maven хувилбар

`mvn -version` командын гаралт:

```text
Apache Maven 3.9.16 (2bdd9fddda4b155ebf8000e807eb73fd829a51d5)
Maven home: /usr/share/java/maven
Java version: 17.0.20.1, vendor: Arch Linux, runtime: /usr/lib/jvm/java-17-openjdk
Default locale: en_US, platform encoding: UTF-8
OS name: "linux", version: "7.2.3-arch1-2", arch: "amd64", family: "unix"
```

## Тестийн бүтэц

`GradeCalculatorTest` класст нийт **11 test method** хэрэгжүүлсэн. JUnit 5-ийн `@Test` болон `@ParameterizedTest` ашиглан `letterGrade()` болон `totalScore()` method-уудыг шалгасан. Тестүүдэд ердийн утга, boundary утга, буруу утга болон `IllegalArgumentException` үүсэх нөхцөлүүдийг хамруулсан.

Шалгасан гол утгууд:

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
* -1 болон 101 → `IllegalArgumentException`
* `totalScore()`-ийн зөв нийлбэр
* `totalScore()`-ийн зөвшөөрөгдөх хязгаараас гарсан утгууд

## Эцсийн тестийн үр дүн

Эцсийн зөв код дээр:

```text
Tests run: 23, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

Энэ үр дүнг `results/mvn-test.txt` файлд хадгалсан.

## Mutation Testing

Mutation testing хийхийн тулд `letterGrade()` method-ийн:

```java
if (score >= 90)
```

нөхцөлийг зориудаар:

```java
if (score > 90)
```

болгон өөрчилсөн.

Mutation-ийн үр дүнг `results/mvn-test-mutant.txt` файлд хадгалсан бөгөөд тухайн файлд **22 test case** ажилласан байна:

```text
Tests run: 22, Failures: 1, Errors: 0, Skipped: 0
BUILD FAILURE
```

### Хамгийн сонирхолтой тест ба алдаа

Mutation testing-ийн үед `90` оноо `A` дүн байх ёстой гэсэн boundary test хамгийн чухал тест болсон. Эх кодод `score >= 90` гэж бичсэн үед 90 оноо зөвөөр `A` болж байсан. Харин mutation хийхдээ нөхцөлийг `score > 90` болгосноор яг 90 оноо `B` гэж буруу ангилагдсан. Үүний улмаас `letterGradeBoundaries` parameterized test-ийн `90, A` case унаж, mutation test `BUILD FAILURE` болсон. Энэ нь boundary test нь зөвхөн ердийн утгыг шалгахаас илүүтэйгээр нөхцөлийн жижиг алдааг илрүүлэхэд чухал гэдгийг харуулсан. Мөн `>=` болон `>` хоёрын ялгаа нь програмын үр дүнд шууд нөлөөлж болохыг ойлгосон. Mutation testing ашигласнаар миний тестүүд эх кодын өөрчлөлтийг үнэхээр илрүүлж чадаж байгаа эсэхийг шалгаж чадсан. Эцэст нь mutation-ийг буцааж `score >= 90` болгосны дараа бүх 23 final test амжилттай ажилласан.

## Mutation Testing-ийн дүгнэлт

| Үр дүн        | Tests run | Failures | Build         |
| ------------- | --------: | -------: | ------------- |
| Final зөв код |        23 |        0 | BUILD SUCCESS |
| Mutant код    |        22 |        1 | BUILD FAILURE |

Mutation-ийн дараах бодит үр дүн `results/mvn-test-mutant.txt` файлд, эцсийн амжилттай тестийн үр дүн `results/mvn-test.txt` файлд хадгалагдсан.

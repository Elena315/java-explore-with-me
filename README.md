# ���������� Explore With Me (��������� ������)
https://github.com/AlexMaxpower/java-explore-with-me/pull/1

Explore With Me � ����������, ������� ���� ����������� �������� ����������� �� ���������� �������� � �������� ����� �������� ��� ������� � ���.

���������� � �����, � ������� ����� ���������� �����-���� ������� �� �������� �� ������ � ���� � ������� �������� ��� ������� � ���.

![img.png](img.png)

## �������� ����������� ����������

* ����������� - �������� ������� � ���������� ����������� (�������/����������, ����� ����������, ����, ���������� ����������, ��������� � �.�.).
* ������ �� ��������� ����������� - ����� �������� ������� � ��������� ������ �� ���������.
* ������ � ���������� - ������ ����������� �� �������������� � ��������� ���������� ��� ������������� � ���������������, ��������� �������, ���������� �� ���������� (���� ����������, ��������� � �.�.)
* �������� ������� - �������������� ����� ������������ ������� �� �����-���� ���������, � ������������ ����������� � ����������.
* ���� ���������� �� ���������� ������� ��� ������ ����� ����������.

## �����������

���������� ������� �� 2 ��������:

* �������� ������ � �������� �� ����������� ��� ������ � ���������
* ������ ���������� � ������ ���������� ���������� � ��������� ������ ��������� ������� ��� ������� ������ ����������.

## ���������� �� ������� ����������
��� ���������� ������ ���������� ���������� ������� �� ���������� Docker. ��� ������� ����������� ��������� ��������:

```Bash
mvn install
docker-compose up
```

## ������������ API

API ��������� ������� ��������� �� ��� �����. ������ � ���������, �������� ��� ����������� ������ ������������ ����. ������ � ��������, �������� ������ �������������� �������������. ������ � ����������������, ��� ��������������� �������.

- [API ��������� �������](https://github.com/AlexMaxpower/java-explore-with-me/blob/main/ewm-main-service-spec.json)
- [API ������� �� ����� ����������](https://github.com/AlexMaxpower/java-explore-with-me/blob/main/ewm-stats-service-spec.json)

��� ��������� ������������ ����������� [�������� Swagger](https://editor-next.swagger.io/).

# ������������ ����������

* Spring Boot 2.7.3
* Maven
* MapStruct 1.5.2
* Lombok
* OpenFeign
* Hibernate ORM
* PostgreSQL 13.7
* Docker
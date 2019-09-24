# EXERCISES.1 SIMPLE COMMANDS

## NO.1-NO.5

### NO.1
> List all information about the employees.
```sql
SELECT * FROM EMP2017151019;
```
![alt](img/exe1_1.png)

### NO.2
> List all information about the departments
```sql
SELECT * FROM DEPT2017151019;
```
![alt](img/exe1_2.png)

### NO.3
> List only the following information from the EMP table ( Employee name, employee number, salary, department number)
```sql
SELECT ENAME,EMPNO,SAL,DEPTNO FROM EMP201715109;
```
![alt](img/exe1_3.png)

### NO.4
> List details of employees in departments 10 and 30.
```sql
SELECT * FROM EMP2017151019
WHERE DEPTNO BETWEEN 10 AND 30;
```
![alt](img/exe1_4.png)

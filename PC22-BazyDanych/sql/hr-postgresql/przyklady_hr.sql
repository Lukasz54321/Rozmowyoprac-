SELECT * FROM employees;
SELECT * FROM locations;
SELECT * FROM jobs;

-- To jest komentarz
/* To też jest komentarz */

-- wielkość liter zazwyczaj nie ma znaczenia
select * FrOm EmploYees;

-- Polecenia modyfikacji danych: INSERT, UPDATE, DELETE
SELECT * FROM countries;

-- INSERT - wstawia zupełnie nowy rekord (takie id nie może wcześniej występować)

INSERT INTO countries(country_id, country_name, region_id)
    VALUES('PL', 'Poland', 1);

-- można pominąć nazwy kolumn - wtedy musimy wstawić wszystkie w tej samej kolejności jak w definicji tabeli)
INSERT INTO countries
    VALUES('CZ', 'Czechia', 1);


-- UPDATE - modyfikacja istniejących danych
UPDATE countries SET country_name = 'Czech Republic' WHERE country_id = 'CZ';

-- przykład zmiany w wielu rekordach: programistom dajemy podwyżkę 10 tys.
UPDATE employees SET salary = salary + 10000 WHERE job_id = 'IT_PROG';

SELECT * FROM employees ORDER BY employee_id;

-- DELETE
DELETE FROM countries WHERE country_id IN ('PL', 'CZ');


-- SELECT - odczyt danych
/* Ogólna składnia (tzw. kaluzule)

SELECT kolumny
FROM tabele
WHERE warunek
GROUP BY kryteria_grupowania
HAVING warunek
ORDER BY kryteria_sortowania
*/
-- poszczególne bazy danych mają różne rozszerzenia, np. LIMIT OFFSET


-- SELECT - wybór kolumn

-- * - wszystkie dostępne kolumny
SELECT * FROM employees;

-- nazwy kolumn
SELECT first_name, last_name, salary FROM employees;

-- wyrażenia (np. działania matematyczne) wyliczające coś na podstawie wartości pól
SELECT first_name, last_name, 12 * salary FROM employees;

SELECT first_name || ' ' || last_name, 12 * salary, strftime('%Y', hire_date) FROM employees;
-- SELECT first_name || ' ' || last_name, 12 * salary, extract(year FROM hire_date) FROM employees;

-- obsługa dat w innych bazach może wyglądać inaczej, u używam rzeczy specyficznych dla SQLite
-- https://www.sqlite.org/lang_datefunc.html

-- Aliasy kolumn: nasze własne nazwy dla kolumn wynikowych.
-- Można stosować dla rzeczy wyliczanych wyrażeniami, ale też dla kolumn, których nazwy chcemy zmienić
SELECT employee_id AS numer,
	first_name || ' ' || last_name AS kto,
	12 * salary AS roczne_zarobki,
	strftime('%Y', hire_date) AS rok_zatrudnienia
FROM employees;

-- Jeśli nasze nazwy mają zawierać znaki specjalne, w tym spacje,
-- i gdy chcemy zachować wielkość liter, to powinniśmy użyć "nazw cytowanych".
SELECT employee_id AS "Numer",
	first_name || ' ' || last_name AS "Kto",
	12 * salary AS "Roczne zarobki",
	strftime('%Y', hire_date) AS "Rok zatrudnienia"
FROM employees;

-- Zgodnie ze standardem SQL (i tego trzymają się Oracle, PostgreSQL):
-- napisy (wartości w treści zapytań) należy umieszczać 'w apostrofach'
-- natomiast nazwy cytowane (tabel, kolumn) "w cudzysłowach".
-- MySQL oraz SQLite pozwalają używać "" do zapisywania stringów, a backquote `` do zapisywania nazw

-- To jest zgodne ze standardem SQL
SELECT 12 * salary AS "Roczne zarobki" FROM employees WHERE last_name = 'King';


-- WHERE - wybór wierszy (rekordów) na podstawie warunku logicznego
SELECT * FROM employees WHERE salary >= 10000;

-- employee_id jest kluczem głównym - to zapytanie zadziała bardzo szybko
-- podobnie dla innych kolumn, na których są założone dodatkowe indeksy
SELECT * FROM employees WHERE employee_id = 150;

SELECT * FROM employees WHERE job_id = 'IT_PROG';

-- Spójniki logiczne pisane słownie NOT, AND, OR 
-- Wypisz programistów, którzy zarabiają pow. 15 tys
SELECT * FROM employees WHERE job_id = 'IT_PROG' AND salary > 15000;

-- to wypisze zarówno wszystkich programistów, jak i wszystkie osoby zarabiające > 15tys
SELECT * FROM employees WHERE job_id = 'IT_PROG' OR salary > 15000;

-- Zadanko: Wypisz pracowników, którzy zarabiają między 5 a 10 tys (włącznie)
SELECT first_name, last_name, salary FROM employees WHERE salary >= 5000 AND salary <= 10000;

-- Operator BETWEEN bezpośrednio obejmuje takie sytuacje:
SELECT first_name, last_name, salary FROM employees WHERE salary BETWEEN 5000 AND 10000;
SELECT * FROM employees WHERE salary BETWEEN 5000 AND 10000;

-- Zadanko: wypisz pracowników, których job_id jest równe ST_CLERK oraz ST_MAN
SELECT * FROM employees WHERE job_id = 'ST_CLERK' OR job_id = 'ST_MAN';

-- Operator IN sprawdza czy wartość jest jedną z wartości ze zbioru
SELECT * FROM employees WHERE job_id IN ('ST_CLERK', 'ST_MAN');
-- SELECT * FROM employees WHERE job_id IN (SELECT premiowane_stanowiska FROM tabela_z_premiami);

-- W tym konkretnym przypadku można też użyć LIKE, który sprawdza czy tekst pasuje do wzorca
SELECT * FROM employees WHERE job_id LIKE 'ST_%';

-- Ale już nie bardzo gdybym szukał IT_PROG i ST_MAN
-- ani gdyby istniały inne stanowiska o prefiksie ST, ST_PRES

-- Przyjrzyjmy się LIKE-owi
-- % oznacza dowolnie wiele znaków (w tym 0)
-- _ oznacza dowolny pojedynczy znak
SELECT * FROM locations;

SELECT * FROM locations WHERE city LIKE 'S%';

SELECT * FROM locations WHERE city LIKE 'S_dney';

-- wybierze miasta, których drugą literą jest o
SELECT * FROM locations WHERE city LIKE '_o%';

-- Uwaga od trenera :)
-- Nie używajcie LIKE do zwykłego porównywania napisów
SELECT * FROM employees WHERE last_name LIKE 'King';

-- Przecież to można zrobić zwykłym porównaniem =
SELECT * FROM employees WHERE last_name = 'King';

-- Poszczególne bazy danych oferują też sprawdzanie wyrażeń regularnych - konkretny zapis zależy od bazy
-- To daje więcej możliwości.

-- Ale w pewnych sytuacjach LIKE wystarcza:
-- SELECT * FROM produkty WHERE opis LIKE '%wysokowydajny%';

-- Miasta, których drugą literą jest o
SELECT * FROM locations WHERE city LIKE '_o%';

-- Zadanko: wypisz wszystkie miasta, które zawierają trzy litery o
SELECT * FROM locations WHERE city LIKE '%o%o%o%';

-- Zadanko: wypisz wszystkie miasta, które zawierają dwie litery o
-- Z obserwacji: W SQLIte LIKE domyślnie ignoruje wielkość liter
-- i tutaj to zapytanie znajduje Oxford
SELECT * FROM locations WHERE city LIKE '%o%o%';

-- Natomiast w innych baza (na pewno Oralce i PostgreSQL) LIKE rozróżnia wielkość liter
-- i aby znaleźć Oxford trzeba tak:
SELECT * FROM locations WHERE lower(city) LIKE '%o%o%';

-- Problem NULL-a
-- Problem logiczny: gdy w bazie jest wpisany NULL, to może to oznaczać:
-- 1) że czegoś nie ma
-- 2) że nie wiemy jak jest
-- W teorii SQL przyjmuje się raczej, że NULL oznacza "nie wiadomo" i z tego wychodzą różne dziwne konsekwencje...

-- W niektórych rekordach kolumna state_province ma wartość NULL
SELECT * FROM locations;

-- wypisz te lokalizacje, które mają state_province ustawioną na NULL
-- takie podejście nie działa (puste wyniki w obu przypadkach):
SELECT * FROM locations WHERE state_province = NULL;
SELECT * FROM locations WHERE state_province != NULL;

-- wymaga to specjalnego podejścia:
SELECT * FROM locations WHERE state_province IS NULL;

SELECT * FROM locations WHERE state_province IS NOT NULL;

-- Również gdy wyliczamy wyniki na podstawie nulla, trzeba zastosować specjalne podejście

SELECT * FROM employees;

-- Zakładając, że sprzedaż wyniosła 10000 (dla każdego pracownika tyle samo)
-- chcemy obliczyć pensję pracownika z dodaną prowizją od sprzedaży

-- Przy takim zapisie tym pracownikom, którzy nie otrzymują prowizji (mają null),
-- zostanie wyliczony wynik NULL dla całości
SELECT first_name, last_name, salary + commission_pct* 10000
FROM employees;

-- Funkcja COALESCE zamienia nulla na inną wartość
-- Odczytuje wartość z podanego źródła
-- ale jeśli znajdzie tam NULL-a, to wtedy zwraca inną wartość - tutaj 0
SELECT first_name, last_name, salary + COALESCE(commission_pct, 0) * 10000
FROM employees;

-- COALESCE jest standardowym elementem SQL i działa w każdej bazie danych.
-- W Oraclu istnieje analogiczna funkcja NVL, której używają Oraclowcy, ale ona działa tylko



-- ORDER BY - sortowanie wyników

SELECT * FROM employees
ORDER BY salary;

-- domyślnie sortuje rosnąco (można dopisać ASC,ale nie trzeba),
-- a żeby sortować majlejąco trzeba dopisać DESC
SELECT * FROM employees
ORDER BY salary ASC;

SELECT * FROM employees
ORDER BY salary DESC;

-- Sortować można wg wielu kryteriów wymienionych po przecinku.
-- Np. sortujemy wg nazwiska, a jeśli nazwiska są jednakowe, to wg imienia:
SELECT * FROM employees
ORDER BY last_name, first_name;

-- Wtedy ASC i DESC dotyczą tylko ostatniego kryterium, przy którym są napisane
-- Inaczej mówiąc ASC i DESC muszą być podane niezależnie dla każdego kryterium

-- Przykład: rosnąco wg job_id, w obrębie każdej takiej grupy malejąco wg pensji, ostatecznie wg nazwiska i imienia
SELECT * FROM employees
ORDER BY job_id, salary DESC, last_name, first_name;

-- Co można wpisać jako kryterium?
-- nazwę kolumny
SELECT * FROM employees
ORDER BY job_id, salary DESC;

-- dowolne wyrażenie, nawet takie, którego wyniku nigdzie nie wypisuję
SELECT * FROM employees
ORDER BY length(first_name || last_name);

-- przykład: wymieszaj dane losowo:
SELECT * FROM employees ORDER BY random();

-- alias kolumny
SELECT first_name, last_name,
	salary + COALESCE(commission_pct, 0) * 10000 AS "do wypłaty"
FROM employees
ORDER BY "do wypłaty";

-- numer kolumny licząc od 1
SELECT first_name, last_name,
	salary + COALESCE(commission_pct, 0) * 10000 AS "do wypłaty"
FROM employees
ORDER BY 3 DESC;

SELECT employee_id, first_name, last_name, job_id, salary
FROM employees
ORDER BY 4, 5 DESC, 3, 2;



-- GROUP BY i agreagacja

-- W SQL istnieją funkcje "zwykłe" i funkcje "agregujące"
-- zwykła funkcja dla każdego rekordu wejściowego zwraca osobny wynik:
SELECT round(salary) FROM employees;

-- funkcja agregująca "skleja" wiele rekordów wejściowych i zwraca jeden wspólny wynik dla wszystkich:
SELECT avg(salary) FROM employees;

-- o tym, że z wielu rekordów robi się jeden wynik, decyduje sam fakt, że użyliśmy funkcji, która jest f. "agregującą"

-- 5 "kanonicznych" funkcji agregujących: count, sum, avg, min, max
-- oprócz tego poszczególne bazy mają swoje rozszerzenia

SELECT count(*), sum(salary), avg(salary), min(salary), max(salary)
FROM employees;

-- count można używać na 3 sposoby:
-- count(*) - ilość rekordów
-- count(kolumna) - ilość nienullowych wartości w tej kolumnie
--   (ilu pracowników pracuj w jakimś departamencie)
-- count(distinct kolumna) - ilość różnych nienullowych wartości
--   (w ilu różnych departamentach pracują pracownicy)
SELECT count(*), count(department_id), count(DISTINCT department_id) FROM employees;

-- Brakującym rekordem jest Kimberely Grant
SELECT * FROM employees WHERE department_id IS NULL;

-- Funkcje agregujące można łączyć z warunkami WHERE i uzyskiwać ciekawe rezultaty:
SELECT count(*) FROM employees WHERE salary >= 10000;

SELECT count(*) AS ilu, avg(salary) AS "średnia"
FROM employees
WHERE job_id = 'IT_PROG';

-- domyślnie f.agregujące są stosowane do wszystkich danych, które pochodzą z tabel
-- można ew. przefiltrować za pomocą WHERE 

-- natomiast klauzula GROUP BY potrafi podzielić dane na grupy i dla każdej grupy osobno obliczyć wyniki funkcji agregujących
SELECT count(*) AS ilu, avg(salary) AS "średnia"
FROM employees
GROUP BY job_id;

-- w praktyce wypisuje się też wartość tego kryterium:
SELECT job_id, count(*) AS ilu, avg(salary) AS "średnia"
FROM employees
GROUP BY job_id;

-- jeśli chcemy przefiltrować utworzone w ten sposób grupy, to używamy HAVING
SELECT job_id, count(*) AS ilu, avg(salary) AS "średnia"
FROM employees
GROUP BY job_id
HAVING avg(salary) >= 10000;

-- Dla każdego roku kalendarzowego (z występujących w bazie) wypisz
-- liczbę pracowników zatrudnionych w tym roku i średnią pensję.
-- Spróbuj wypisać wyniki tak, aby ocenić czy pensja rośnie wraz ze stażem.

-- W SQlite aby odczytać rok z daty użyjemy funkcji strftime
SELECT first_name, last_name, strftime('%Y', hire_date) AS rok
FROM employees;

-- W GROUP BY można wpisać nawet skomlikowane wyrażenia
SELECT count(*), avg(salary), strftime('%Y', hire_date) AS rok
FROM employees
GROUP BY strftime('%Y', hire_date);

-- W większości baz danych w GROUP BY można użyć aliasu kolumny:
-- To nie działa w Oraclu
SELECT strftime('%Y', hire_date) AS rok, count(*), avg(salary)
FROM employees
GROUP BY rok
ORDER BY rok;


-- FROM, JOIN i łączenie tabel

SELECT * FROM employees;
SELECT * FROM departments;

-- we FROM można wymienić wiele tabel
-- efektem będzie wówczas iloczyn kartezjański, czyli kombinacja każdego pracownika z każdym departamentem
SELECT * FROM employees, departments;

-- Tradycyjny sposób na wybrtanie tylko pasujących kombinacji:
SELECT * FROM employees, departments
WHERE employees.department_id = departments.department_id;

-- Możliwością, która pojawiła się w SQL nie od razu jest bardziej rozbudowana składnia JOIN

SELECT * FROM employees JOIN departments USING(department_id);

-- To był wstęp, a teraz szczegóły - wklejam gotowy fragment


-- W relacyjnych bazach danych dane znajdują się w wielu tabelach, a tabele są ze sobą powiązane tzw. kluczami obcymi.
-- Przykładowo w tabeli employees kolumna job_id to kod stanowiska, a tabela jobs podaje szczegóły stanowiska dla podanego job_id

SELECT * FROM employees;
SELECT * FROM jobs;
SELECT * FROM departments;
SELECT * FROM locations;

-- Zadając zapytania chcemy te dane połączyć

SELECT * FROM employees JOIN jobs USING(job_id);

SELECT *
FROM employees
	JOIN departments USING(department_id)
	JOIN locations USING(location_id);

-- cała teoria...

-- we FROM można podać wiele tabel, ale bez dodatkowych opcji spowoduje to połączenie każdego rekordu z jednej tbaeli z każdym rekordem z drugiej
-- czyli "iloczyn kartezjański"
SELECT * FROM employees, jobs;
-- wynikowych rekordów jest tyle, ile wynosi iloczyn iloci w obu tabelach
SELECT count(*), 107 * 19 FROM employees, jobs;

-- ten problem można rozwiązać na 2 sposoby
-- 1) warunek WHERE
SELECT * FROM employees, jobs WHERE employees.job_id = jobs.job_id;

-- 2) składnia JOIN - wiele możliwości

SELECT * FROM employees JOIN jobs ON employees.job_id = jobs.job_id;

-- w obu przypadkach można wprowadzić aliasy tabel
-- jeśli tak zrobimy, to w różnych częściach zapytania (SELECT, WHERE, ORDER itd.)
-- będziemy używać krótszych nazw (aliasów)
SELECT j.job_id, j.job_title, emp.first_name, emp.last_name, salary
FROM employees emp, jobs j
WHERE emp.job_id = j.job_id;


-- Na przykładzie łączenia 3 tabel i filtrowania wyników zobaczmy różnicę między WHERE a JOIN ON:
-- Wersja z przecinkiem i WHERE - warunki dotyczące złączenia oraz zwykłe warunki logiczne są razem w WHERE
SELECT *
FROM employees e, departments d, locations l
WHERE e.department_id = d.department_id
	AND d.location_id = l.location_id
	AND salary BETWEEN 5000 AND 10000;
	
-- Wersja JOIN - po kolei dodajemy tabel i dla każdej od razu po JOIN wpisujemy warunek złączenia
-- Natomiast w WHERE mamy tylko inne warunki logiczne
SELECT *
FROM employees e
	JOIN departments d ON e.department_id = d.department_id
	JOIN locations l ON d.location_id = l.location_id
WHERE salary BETWEEN 5000 AND 10000;


-- JOIN pozwala na 3 sposoby podać warunek złączenia:

-- 1) JOIN ON warunek logiczny
-- szczególnie użyteczne, gdy kolumny, wg których łączymy dane, mają różne nazwy
SELECT * FROM employees e JOIN jobs j ON e.job_id = j.job_id;

-- 2) JOIN USING(kolumny) - dopasowuje rekordy w ten sposób, że wartości podanej kolumny muszą zgadzać w obu tabelach
-- aby można było tego użyć, kolumna, po której złączamy, musi mieć taką samą nazwę w obu tabelach
SELECT * FROM employees JOIN jobs USING(job_id);

-- 3) NATURAL JOIN - dopasowuje wszystkie kolumny, które mają jednakowe nazwy w obu tabelach
SELECT * FROM employees NATURAL JOIN jobs;

-- Uwaga, może pojawić się przypadkowa zbieżność nazw
SELECT * FROM employees NATURAL JOIN departments;
-- W tabeli employees istnieje kolumna manager_id (szef pracownika), a w tabeli departments także (szef departamentu)
-- to byłoby równoważne:
SELECT * FROM employees JOIN departments USING(department_id, manager_id);

---------------
-- JOIN posiada 4 "kierunki" złączeń: INNER, LEFT, RIGHT i FULL
-- generalnie: sposób potraktowania wierszy z jednej tabeli, które nie dopasują się do żandego z innej tabeli


-- Przykład: łączenie employees z depratments
-- Pracowniczka Kimberely Grant nie ma podanego department_id (wpisany NULL)
-- Istnieją też departamenty, w których nikt nie pracuje, np. Payroll

SELECT * FROM employees JOIN departments USING(department_id);
-- domyślnie zapytania są wewnętrzne, czyli INNER - gdy piszemy samo JOIN, to działa tak jak INNER JOIN
-- zwraca tylko te rekordy, które się dopasowały
-- w tym przypadku: nie ma K.Grant, nie ma Payroll
SELECT * FROM employees INNER JOIN departments USING(department_id);

-- LEFT JOIN  (albo LEFT OUTER JOIN)
-- (słowo OUTER niczego nie zmienia)
-- zwraca wszystkie rekordy z lewej tabeli oraz tylko te z prawej, ktre się dopasowały
-- w miejsce brakujących danych wstawia NULL-e
-- jest K.Grant, nie ma departametu Payroll
SELECT * FROM employees LEFT JOIN departments USING(department_id);
SELECT * FROM employees LEFT OUTER JOIN departments USING(department_id);

-- RIGHT i FULL JOIN nie działają w SQLite
-- RIGHT JOIN  (albo RIGHT OUTER JOIN)
-- zwraca wszystkie rekordy z prawej tabeli oraz tylko te z lewej, ktre się dopasowały
-- w miejsce brakujących danych wstawia NULL-e
-- nie ma K.Grant, są departamenty, w których nikt nie pracuje, np. Payroll
SELECT * FROM employees RIGHT JOIN departments USING(department_id);
SELECT * FROM departments LEFT JOIN employees USING(department_id);

-- FULL JOIN  (albo FULL OUTER JOIN)
-- zwraca wszystkie rekordy z lewej i prawej tabeli dopasowując te, które się da i multiplikując w miarę potrzeb
-- jest K.Grant, są departamety, w których nikt nie pracuje, np. Payroll
SELECT * FROM employees FULL JOIN departments USING(department_id);


-- Zapytanie, które łączy prawie wszystkie tabele, abyśmy poznali wszystkie szczegóły nt pracownika.
SELECT * FROM employees
	JOIN jobs USING(job_id)
	JOIN departments USING(department_id)
	JOIN locations USING(location_id)
	JOIN countries USING(country_id)
	JOIN regions USING(region_id);

-- wersja z wybranymi i nazwanymi kolumnami
SELECT employee_id
	,first_name
	,last_name
	,salary
	,job_title
	,department_name
	,lower(email) || '@alx.pl' AS email
	,street_address || ' ' || postal_code || ', ' || city || ', ' || country_name AS address
FROM employees
	JOIN jobs USING(job_id)
	JOIN departments USING(department_id)
	JOIN locations USING(location_id)
	JOIN countries USING(country_id)
	JOIN regions USING(region_id);


-- Znajdź pracowników, którzy pracują w Seattle

SELECT * FROM employees
	JOIN departments USING(department_id)
	JOIN locations USING(location_id)
WHERE city = 'Seattle';


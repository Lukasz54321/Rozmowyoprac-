-- Wykonac jako user postgres (administrator)
-- Pozostale pliki juz jako user hr

DROP DATABASE IF EXISTS hr;
DROP ROLE IF EXISTS hr;

CREATE USER hr PASSWORD 'abc123';
CREATE DATABASE hr ENCODING 'utf-8' OWNER hr;

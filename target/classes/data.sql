TRUNCATE TABLE elerhetoseg;
TRUNCATE TABLE cim;
TRUNCATE TABLE szemely;

INSERT INTO szemelyek (nev, szuletesi_hely) VALUES ('Nagy Péter', 'Budapest');
INSERT INTO szemelyek (nev, szuletesi_hely) VALUES ('Kovács Anna', 'Debrecen');

INSERT INTO cimek (utca, varos, iranyitoszam, tipus, szemely_id) VALUES ('Fő utca 1.', 'Budapest', '1000', 'ALLANDO', 1);
INSERT INTO cimek (utca, varos, iranyitoszam, tipus, szemely_id) VALUES ('Kossuth tér 10.', 'Szeged', '6720', 'IDEIGLENES', 1);

INSERT INTO elerhetosegek (tipus, ertek, szemely_id) VALUES ('EMAIL', 'peter.nagy@email.hu', 1);
INSERT INTO elerhetosegek (tipus, ertek, szemely_id) VALUES ('TELEFON', '+36701234567', 1);
INSERT INTO elerhetosegek (tipus, ertek, szemely_id) VALUES ('EMAIL', 'anna.kovacs@email.com', 2);

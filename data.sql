-- DROP SCHEMA dbo;

CREATE SCHEMA dbo;
-- nyilvantarto.dbo.szemely definition

-- Drop table

-- DROP TABLE nyilvantarto.dbo.szemely;

CREATE TABLE nyilvantarto.dbo.szemely (
    id int IDENTITY(1,1) NOT NULL,
    nev varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    szuletesi_hely varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    CONSTRAINT PK_szemely PRIMARY KEY (id)
);


-- nyilvantarto.dbo.cim definition

-- Drop table

-- DROP TABLE nyilvantarto.dbo.cim;

CREATE TABLE nyilvantarto.dbo.cim (
    id int IDENTITY(1,1) NOT NULL,
    szemely_id int NOT NULL,
    iranyitoszam varchar(4) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    varos varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    utca varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    tipus varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    CONSTRAINT PK_cim PRIMARY KEY (id),
    CONSTRAINT FK_cim_szemely FOREIGN KEY (szemely_id) REFERENCES nyilvantarto.dbo.szemely(id)
);


-- nyilvantarto.dbo.elerhetoseg definition

-- Drop table

-- DROP TABLE nyilvantarto.dbo.elerhetoseg;

CREATE TABLE nyilvantarto.dbo.elerhetoseg (
    id int IDENTITY(1,1) NOT NULL,
    ertek varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    tipus varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    szemely_id int NULL,
    CONSTRAINT PK_elerhetoseg PRIMARY KEY (id),
    CONSTRAINT elerhetoseg_szemely_FK FOREIGN KEY (szemely_id) REFERENCES nyilvantarto.dbo.szemely(id)
);
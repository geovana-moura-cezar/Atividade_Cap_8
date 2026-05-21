CREATE SEQUENCE seq_usuario
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE tbl_usuario (
                             id NUMBER PRIMARY KEY,
                             nome VARCHAR2(100) NOT NULL,
                             email VARCHAR2(100) UNIQUE NOT NULL,
                             senha VARCHAR2(255) NOT NULL,
                             role VARCHAR2(30) NOT NULL
);


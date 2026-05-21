CREATE SEQUENCE seq_coleta
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;


CREATE TABLE tbl_coleta (
                            id NUMBER PRIMARY KEY,

                            status VARCHAR2(50) NOT NULL,
                            data_solicitacao DATE NOT NULL,
                            data_realizacao DATE,

                            local_coleta_id NUMBER NOT NULL,

                            CONSTRAINT fk_coleta_local
                                FOREIGN KEY (local_coleta_id)
                                    REFERENCES tbl_local_coleta(id)
);


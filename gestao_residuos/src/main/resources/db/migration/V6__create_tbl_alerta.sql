CREATE SEQUENCE seq_alerta
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE tbl_alerta (
                            id NUMBER PRIMARY KEY,

                            mensagem VARCHAR2(255) NOT NULL,
                            nivel NUMBER NOT NULL,
                            data_criacao DATE NOT NULL,

                            local_coleta_id NUMBER NOT NULL,

                            CONSTRAINT fk_alerta_local
                                FOREIGN KEY (local_coleta_id)
                                    REFERENCES tbl_local_coleta(id)
);



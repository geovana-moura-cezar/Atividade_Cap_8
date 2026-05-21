CREATE OR REPLACE TRIGGER trg_alerta_capacidade_excedida
AFTER INSERT ON tbl_descarte
FOR EACH ROW

DECLARE
v_total_descartado NUMBER;
    v_capacidade_maxima NUMBER;

BEGIN

SELECT NVL(SUM(d.quantidade),0)
INTO v_total_descartado
FROM tbl_descarte d
WHERE d.local_coleta_id = :NEW.local_coleta_id;

SELECT l.capacidade_maxima
INTO v_capacidade_maxima
FROM tbl_local_coleta l
WHERE l.id = :NEW.local_coleta_id;

IF v_total_descartado > v_capacidade_maxima THEN

        INSERT INTO tbl_alerta (
            id,
            mensagem,
            nivel,
            data_criacao,
            local_coleta_id
        )
        VALUES (
            seq_alerta.NEXTVAL,
            'Capacidade excedida',
            1,
            SYSDATE,
            :NEW.local_coleta_id
        );

END IF;

END;
/

CREATE OR REPLACE TRIGGER trg_alerta_proximo_limite
AFTER INSERT ON tbl_descarte
FOR EACH ROW

DECLARE
v_total_descartado NUMBER;
    v_capacidade_maxima NUMBER;
    v_percentual NUMBER;

BEGIN

SELECT NVL(SUM(d.quantidade),0)
INTO v_total_descartado
FROM tbl_descarte d
WHERE d.local_coleta_id = :NEW.local_coleta_id;

SELECT l.capacidade_maxima
INTO v_capacidade_maxima
FROM tbl_local_coleta l
WHERE l.id = :NEW.local_coleta_id;

IF v_capacidade_maxima > 0 THEN
    v_percentual := (v_total_descartado / v_capacidade_maxima) * 100;
END IF;

    IF v_percentual >= 80 AND v_total_descartado <= v_capacidade_maxima THEN

        INSERT INTO tbl_alerta (
            id,
            mensagem,
            nivel,
            data_criacao,
            local_coleta_id
        )
        VALUES (
            seq_alerta.NEXTVAL,
            'Próximo do limite (' || ROUND(v_percentual,1) || '% ocupado)',
            2,
            SYSDATE,
            :NEW.local_coleta_id
        );

END IF;

END;
/

CREATE OR REPLACE TRIGGER trg_cria_coleta_por_alerta
AFTER INSERT ON tbl_alerta
FOR EACH ROW

BEGIN

INSERT INTO tbl_coleta (
    id,
    status,
    data_solicitacao,
    local_coleta_id
)
VALUES (
           seq_coleta.NEXTVAL,
           'PENDENTE',
           SYSDATE,
           :NEW.local_coleta_id
       );

END;
/

CREATE OR REPLACE TRIGGER trg_limpa_descartes_coleta_realizada
AFTER UPDATE OF status ON tbl_coleta
FOR EACH ROW

BEGIN

    IF :OLD.status <> 'REALIZADA'
       AND :NEW.status = 'REALIZADA' THEN

DELETE FROM tbl_descarte
WHERE local_coleta_id = :NEW.local_coleta_id;

END IF;

END;
/
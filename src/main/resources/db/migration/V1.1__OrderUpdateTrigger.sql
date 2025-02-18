CREATE OR REPLACE FUNCTION order_update() RETURNS TRIGGER AS $order_update$
BEGIN
    IF (TG_OP = 'UPDATE') THEN
        NEW.updated_at = now();
    END IF;
    RETURN NEW; -- Возвращаемое значение для триггеров AFTER игнорируется
END;
$order_update$ LANGUAGE plpgsql;

CREATE trigger order_update
    BEFORE UPDATE ON "order"
    FOR EACH ROW EXECUTE PROCEDURE order_update();
-- Put in here any db modifications, if needed, feel free to alter existing tables

ALTER TABLE grid_column
    DROP CONSTRAINT grid_column_grid_id_number_key;

ALTER TABLE grid_column
    ADD CONSTRAINT grid_column_grid_id_number_key_unique
        UNIQUE (grid_id, number)
            DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE grid_column
    ADD COLUMN lcp TEXT;

-- check column cellSize == value length
--CREATE TRIGGER check_value_size BEFORE INSERT ON grid_cell
--    FOR EACH ROW EXECUTE PROCEDURE check_value_size();


-- CREATE TRIGGER update lcp for column AFTER INSERT ON grid_cell

CREATE FUNCTION lcp_iterate(_state TEXT, value TEXT)
    RETURNS TEXT
AS
$$
SELECT SUBSTRING($2, 1, s - 1)
FROM generate_series(1, LEAST(LENGTH($1), LENGTH($2))) s
WHERE SUBSTRING($1, 1, s) <> SUBSTRING($2, 1, s)
UNION ALL
SELECT LEAST($1, $2)
LIMIT 1;
$$
    LANGUAGE 'sql';

CREATE AGGREGATE lcp(TEXT) (SFUNC = lcp_iterate, STYPE = TEXT);
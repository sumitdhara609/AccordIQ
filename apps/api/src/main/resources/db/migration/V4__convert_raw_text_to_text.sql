ALTER TABLE document_analysis
ADD COLUMN raw_text_new TEXT;

UPDATE document_analysis
SET raw_text_new = convert_from(lo_get(raw_text), 'UTF8');

ALTER TABLE document_analysis
DROP COLUMN raw_text;

ALTER TABLE document_analysis
RENAME COLUMN raw_text_new TO raw_text;
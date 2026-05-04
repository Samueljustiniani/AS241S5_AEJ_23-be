CREATE TABLE IF NOT EXISTS producto (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255),
    descripcion TEXT,
    precio DOUBLE PRECISION,
    stock INTEGER,
    categoria VARCHAR(255),
    marca VARCHAR(255),
    codigo VARCHAR(255),
    fecha_registro DATE,
    status INTEGER DEFAULT 1
);

CREATE TABLE IF NOT EXISTS chat_result (
    id BIGSERIAL PRIMARY KEY,
    question TEXT,
    answer TEXT,
    created_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tts_result (
    id BIGSERIAL PRIMARY KEY,
    input_text TEXT,
    created_at TIMESTAMP,
    audio BYTEA,
    status INTEGER NOT NULL DEFAULT 1
);

ALTER TABLE IF EXISTS tts_result
    ADD COLUMN IF NOT EXISTS status INTEGER NOT NULL DEFAULT 1;

CREATE TABLE IF NOT EXISTS consulta (
    id BIGSERIAL PRIMARY KEY,
    pregunta TEXT NOT NULL,
    respuesta TEXT NOT NULL,
    fecha_registro TIMESTAMP NOT NULL,
    status INTEGER NOT NULL DEFAULT 1
);
CREATE TABLE Autor (
    id          SERIAL       NOT NULL,
    nome        VARCHAR(255) NOT NULL,
    nacionalidade VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Editora (
    id     SERIAL       NOT NULL,
    nome   VARCHAR(255) NOT NULL,
    cidade VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Livro (
    id              SERIAL         NOT NULL,
    titulo          VARCHAR(255)   NOT NULL,
    ano_publicacao  INTEGER        NOT NULL,
    isbn            VARCHAR(255)   NOT NULL,
    preco           NUMERIC(38, 2) NOT NULL,
    tipo            VARCHAR(255)   NOT NULL CHECK (tipo IN ('IMPRESSO', 'DIGITAL', 'AUDIOBOOK')),
    editora_id      INTEGER,
    PRIMARY KEY (id),
    CONSTRAINT fk_livro_editora FOREIGN KEY (editora_id) REFERENCES Editora (id)
);

CREATE TABLE livro_autor (
    livro_id INTEGER NOT NULL,
    autor_id INTEGER NOT NULL,
    CONSTRAINT fk_livro FOREIGN KEY (livro_id) REFERENCES Livro  (id),
    CONSTRAINT fk_autor FOREIGN KEY (autor_id) REFERENCES Autor  (id)
);

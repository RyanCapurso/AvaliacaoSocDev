CREATE TABLE exame (
    id_exame BIGINT AUTO_INCREMENT PRIMARY KEY,
    nm_exame VARCHAR(255)
);

CREATE TABLE funcionario (
    id_funcionario BIGINT AUTO_INCREMENT PRIMARY KEY,
    nm_funcionario VARCHAR(255)
);

CREATE TABLE exame_funcionario (
    id_exameRealizado BIGINT AUTO_INCREMENT PRIMARY KEY, -- Coluna 'id_exameRealizado' como chave primária
    id_funcionario BIGINT,
    id_exame BIGINT,
    dt_exame DATE,
    CONSTRAINT fk_funcionario_exame_funcionario FOREIGN KEY (id_funcionario) REFERENCES funcionario(id_funcionario),
    CONSTRAINT fk_exame_exame_funcionario FOREIGN KEY (id_exame) REFERENCES exame(id_exame)
);

INSERT INTO exame (nm_exame) VALUES 
    ('Acuidade Visual'), 
    ('Urina'), 
    ('Clinico'), 
    ('Sangue'),
    ('Colesterol'),
    ('Triglicerídeos'),
    ('Pressão Arterial'),
    ('Glicose'),
    ('Hematologia'),
    ('Função Hepática');

INSERT INTO funcionario (nm_funcionario) VALUES 
    ('Giovanna Silva'), 
    ('Maria Luiza Costa'), 
    ('Raphael Oliveira'), 
    ('Gustavo Souza'),
    ('Ana Paula Martins'),
    ('Lucas Santos'),
    ('Fernanda Lima'),
    ('Bruno Pereira'),
    ('Juliana Almeida'),
    ('Carlos Eduardo');

INSERT INTO exame_funcionario (id_funcionario, id_exame, dt_exame) VALUES 
    (1, 1, '2024-01-05'),
    (1, 2, '2024-03-12'),
    (2, 2, '2024-07-04'),
    (1, 1, '2024-06-08'),
    (3, 4, '2024-06-20'),
    (4, 3, '2024-02-25'),
    (3, 2, '2024-12-04'),
    (4, 3, '2024-03-14'),
    (5, 5, '2024-02-10'),
    (6, 6, '2024-04-22'),
    (7, 7, '2024-08-15'),
    (8, 8, '2024-11-30'),
    (9, 9, '2024-05-05'),
    (10, 10, '2024-09-18'),
    (5, 6, '2024-03-29'),
    (7, 8, '2024-07-25'),
    (9, 10, '2024-12-10');

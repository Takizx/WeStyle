
CREATE DATABASE IF NOT EXISTS we_style;
USE we_style;


CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(50) NOT NULL
);


CREATE TABLE IF NOT EXISTS categoria (
    id_categoria INT PRIMARY KEY AUTO_INCREMENT,
    nome_categoria VARCHAR(50) NOT NULL
);


CREATE TABLE IF NOT EXISTS produto (
    id_produto INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10, 2) NOT NULL,
    tamanho VARCHAR(10),
    estoque INT DEFAULT 0,
    id_categoria INT,
    CONSTRAINT fk_categoria FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);


CREATE TABLE IF NOT EXISTS pedido (
    id_pedido INT PRIMARY KEY AUTO_INCREMENT,
    data_pedido DATETIME DEFAULT CURRENT_TIMESTAMP,
    status_pedido VARCHAR(50) DEFAULT 'Pendente',
    valor_total DECIMAL(10, 2),
    id_usuario INT,
    CONSTRAINT fk_usuario_pedido FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE IF NOT EXISTS itens_pedido (
    id_item INT PRIMARY KEY AUTO_INCREMENT,
    id_pedido INT,
    id_produto INT,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_pedido_item FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
    CONSTRAINT fk_produto_item FOREIGN KEY (id_produto) REFERENCES produto(id_produto)
);


INSERT IGNORE INTO categoria (id_categoria, nome_categoria) VALUES (1, 'Coleção WeStyle');


INSERT IGNORE INTO produto (id_produto, nome, descricao, preco, tamanho, estoque, id_categoria) 
VALUES 
(1, 'Sunset Vibes', 'Estampa exclusiva pôr do sol', 89.90, 'G', 10, 1),
(2, 'Minimal Wave', 'Design clean branco', 75.00, 'M', 15, 1),
(3, 'Urban Street', 'Estilo oversized preto', 110.00, 'GG', 5, 1),
(4, 'Floral Dreams', 'Estampa floral suave', 95.00, 'P', 8, 1);


SELECT * FROM produto;


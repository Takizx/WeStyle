CREATE DATABASE we_style;

USE we_style;



CREATE TABLE usuario (

id INT PRIMARY KEY AUTO_INCREMENT,

nome VARCHAR(100) NOT NULL,

email VARCHAR(100) UNIQUE NOT NULL,

senha VARCHAR(50) NOT NULL,

telefone VARCHAR(20),

endereco VARCHAR(255),

cidade VARCHAR(100),

cep VARCHAR(10)

);



CREATE TABLE categoria (

id_categoria INT PRIMARY KEY AUTO_INCREMENT,

nome_categoria VARCHAR(50) NOT NULL

);



CREATE TABLE produto (

id_produto INT PRIMARY KEY AUTO_INCREMENT,

nome VARCHAR(100) NOT NULL,

descricao TEXT,

preco DECIMAL(10, 2) NOT NULL,

tamanho VARCHAR(10),

estoque INT DEFAULT 0,

imagem VARCHAR(255) DEFAULT 'padrao.png',

id_categoria INT,

CONSTRAINT fk_categoria FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)

);



CREATE TABLE pedido (

id_pedido INT PRIMARY KEY AUTO_INCREMENT,

data_pedido DATETIME DEFAULT CURRENT_TIMESTAMP,

status_pedido VARCHAR(50) DEFAULT 'Pendente',

subtotal DECIMAL(10, 2),

valor_frete DECIMAL(10, 2),

valor_total DECIMAL(10, 2),

regiao_entrega VARCHAR(50),

id_usuario INT,

CONSTRAINT fk_usuario_pedido FOREIGN KEY (id_usuario) REFERENCES usuario(id)

);



CREATE TABLE itens_pedido (

id_item INT PRIMARY KEY AUTO_INCREMENT,

id_pedido INT,

id_produto INT,

quantidade INT NOT NULL,

preco_unitario DECIMAL(10, 2) NOT NULL,

customizacao TEXT,

CONSTRAINT fk_pedido_item FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),

CONSTRAINT fk_produto_item FOREIGN KEY (id_produto) REFERENCES produto(id_produto)

);



INSERT INTO categoria (id_categoria, nome_categoria) VALUES (1, 'Coleção WeStyle');



INSERT INTO produto (nome, descricao, preco, tamanho, estoque, id_categoria, imagem)

VALUES

('Sunset Vibes', 'Estampa exclusiva pôr do sol', 89.90, 'G', 10, 1, 'sunset.png'),

('Minimal Wave', 'Design clean branco', 75.00, 'M', 15, 1, 'wave.png'),

('Urban Street', 'Estilo oversized preto', 110.00, 'GG', 5, 1, 'urban.png'),

('Floral Dreams', 'Estampa floral suave', 95.00, 'P', 8, 1, 'floral.png');



SELECT * FROM
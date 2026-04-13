
-- 1. Criar e usar o banco de dados
CREATE DATABASE IF NOT EXISTS we_style;
USE we_style;

-- 2. Tabela de Usuários (Importante: coluna chama-se 'id' para bater com o Java)
CREATE TABLE IF NOT EXISTS usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(50) NOT NULL
);

-- 3. Tabela de Categorias
CREATE TABLE IF NOT EXISTS categoria (
    id_categoria INT PRIMARY KEY AUTO_INCREMENT,
    nome_categoria VARCHAR(50) NOT NULL
);

-- 4. Tabela de Produtos (O catálogo)
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

-- 5. Tabela de Pedidos (Com o fechamento de parênteses correto agora)
CREATE TABLE IF NOT EXISTS pedido (
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

-- 6. Tabela de Itens do Pedido (Vincula os produtos ao pedido)
CREATE TABLE IF NOT EXISTS itens_pedido (
    id_item INT PRIMARY KEY AUTO_INCREMENT,
    id_pedido INT,
    id_produto INT,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_pedido_item FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
    CONSTRAINT fk_produto_item FOREIGN KEY (id_produto) REFERENCES produto(id_produto)
);

-- 7. Inserção de dados iniciais para teste
INSERT IGNORE INTO categoria (id_categoria, nome_categoria) VALUES (1, 'Coleção WeStyle');

INSERT IGNORE INTO produto (nome, descricao, preco, tamanho, estoque, id_categoria) 
VALUES 
('Sunset Vibes', 'Estampa exclusiva pôr do sol', 89.90, 'G', 10, 1),
('Minimal Wave', 'Design clean branco', 75.00, 'M', 15, 1),
('Urban Street', 'Estilo oversized preto', 110.00, 'GG', 5, 1),
('Floral Dreams', 'Estampa floral suave', 95.00, 'P', 8, 1);

-- 8. Verificar se os produtos foram criados
SELECT * FROM produto;
create database Mysql-Vendas;

CREATE TABLE Produtos (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255),
    preco Double(10,2),
    ativo BOOLEAN
);

INSERT INTO produtos (descricao, preco, ativo) VALUES ('Televisão 54 Pol', 350.25, 1);

DROP TABLE Produtos;

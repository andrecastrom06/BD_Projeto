CREATE DATABASE davisory;
USE davisory;

CREATE TABLE Endereco (
    idEndereco INT PRIMARY KEY AUTO_INCREMENT,
    estado VARCHAR(50) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    bairro VARCHAR(50) NOT NULL,
    rua VARCHAR(100) NOT NULL,
    complemento VARCHAR(255),
    numero INTEGER NOT NULL CHECK (numero > 0)
);

CREATE TABLE Fornecedor (
    cnpjFornecedor VARCHAR(20) PRIMARY KEY,
    nomeFornecedor VARCHAR(255) NOT NULL,
    telefoneFornecedor VARCHAR(20) UNIQUE NOT NULL,
    emailFornecedor VARCHAR(100) UNIQUE NOT NULL,
    fk_Endereco_id INT NOT NULL,
    FOREIGN KEY (fk_Endereco_id) REFERENCES Endereco(idEndereco)
);

CREATE TABLE MateriaPrima (
    idMateriaPrima INT PRIMARY KEY AUTO_INCREMENT,
    valorMateriaPrima DECIMAL(10,2) NOT NULL CHECK (valorMateriaPrima >= 0),
    codigoEntregaMateriaPrima VARCHAR(50) UNIQUE NOT NULL,
    dataEstimadaEntregaMateriaPrima DATE NOT NULL
);

CREATE TABLE Produto (
    idProduto INT PRIMARY KEY AUTO_INCREMENT,
    nomeProduto VARCHAR(255) NOT NULL,
    descricaoProduto TEXT NOT NULL,
    precoProduto DECIMAL(10,2) NOT NULL CHECK (precoProduto >= 0)
);

CREATE TABLE EstoqueProduto (
    idEstoqueProduto INT AUTO_INCREMENT,
    fk_Produto_idProduto INT,
    quantidadeProduto INTEGER NOT NULL CHECK (quantidadeProduto >= 0),
    PRIMARY KEY (idEstoqueProduto, fk_Produto_idProduto),
    FOREIGN KEY (fk_Produto_idProduto) REFERENCES Produto(idProduto)
);

CREATE TABLE Cliente (
    cpfCnpjCliente VARCHAR(20) PRIMARY KEY,
    nomeCliente VARCHAR(255) NOT NULL,
    telefoneCliente VARCHAR(20) UNIQUE NOT NULL,
    emailCliente VARCHAR(100) UNIQUE NOT NULL,
    fk_Endereco_idEndereco INT NOT NULL,
    FOREIGN KEY (fk_Endereco_idEndereco) REFERENCES Endereco(idEndereco)
);

CREATE TABLE Funcionario (
    idFuncionario INT PRIMARY KEY AUTO_INCREMENT,
    nomeFuncionario VARCHAR(255) NOT NULL,
    salarioFuncionario DECIMAL(10,2) NOT NULL CHECK (salarioFuncionario >= 0),
    dataContratacaoFuncionario DATE NOT NULL,
    chefeFuncionario INT,
    empregado BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (chefeFuncionario) REFERENCES Funcionario(idFuncionario)
);

CREATE TABLE Operacional (
    fk_Funcionario_idFuncionario INT PRIMARY KEY,
    FOREIGN KEY (fk_Funcionario_idFuncionario) REFERENCES Funcionario(idFuncionario)
);

CREATE TABLE Administrativo (
    fk_Funcionario_idFuncionario INT PRIMARY KEY,
    cargoFuncionarioAdministrativo VARCHAR(100) NOT NULL,
    FOREIGN KEY (fk_Funcionario_idFuncionario) REFERENCES Funcionario(idFuncionario)
);

CREATE TABLE Pedido (
    idPedido INT PRIMARY KEY AUTO_INCREMENT,
    dataPedido DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    codigoEntregaPedido VARCHAR(50) UNIQUE NOT NULL,
    quantidadePedido INTEGER NOT NULL CHECK (quantidadePedido > 0),
    precoUnitarioPedido DECIMAL(10,2) NOT NULL CHECK (precoUnitarioPedido >= 0),
    fk_Produto_idProduto INT NOT NULL,
    fk_Funcionario_idFuncionario INT NOT NULL,
    fk_Cliente_cpfCnpjCliente VARCHAR(20) NOT NULL,
    FOREIGN KEY (fk_Produto_idProduto) REFERENCES Produto(idProduto),
    FOREIGN KEY (fk_Funcionario_idFuncionario) REFERENCES Funcionario(idFuncionario),
    FOREIGN KEY (fk_Cliente_cpfCnpjCliente) REFERENCES Cliente(cpfCnpjCliente)
);

CREATE TABLE Atende (
    fk_Cliente_cpfCnpjCliente VARCHAR(20),
    fk_Administrativo_Funcionario_idFuncionario INT,
    dataAtendimento DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (fk_Cliente_cpfCnpjCliente, fk_Administrativo_Funcionario_idFuncionario),
    FOREIGN KEY (fk_Cliente_cpfCnpjCliente) REFERENCES Cliente(cpfCnpjCliente),
    FOREIGN KEY (fk_Administrativo_Funcionario_idFuncionario) REFERENCES Administrativo(fk_Funcionario_idFuncionario)
);

CREATE TABLE EstoqueMateriaPrima (
    idEstoqueMateriaPrima INT AUTO_INCREMENT,
    fk_MateriaPrima_idMateriaPrima INT,
    quantidadeMateriaPrima INT NOT NULL CHECK (quantidadeMateriaPrima >= 0),
    PRIMARY KEY (idEstoqueMateriaPrima, fk_MateriaPrima_idMateriaPrima),
    FOREIGN KEY (fk_MateriaPrima_idMateriaPrima) REFERENCES MateriaPrima(idMateriaPrima)
);

CREATE TABLE Solicita (
    fk_Fornecedor_cnpjFornecedor VARCHAR(20),
    fk_MateriaPrima_idMateriaPrima INT,
    fk_Administrativo_Funcionario_idFuncionario INT,
    dataSolicitacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (fk_Fornecedor_cnpjFornecedor, fk_MateriaPrima_idMateriaPrima, fk_Administrativo_Funcionario_idFuncionario),
    FOREIGN KEY (fk_Fornecedor_cnpjFornecedor) REFERENCES Fornecedor(cnpjFornecedor),
    FOREIGN KEY (fk_MateriaPrima_idMateriaPrima) REFERENCES MateriaPrima(idMateriaPrima),
    FOREIGN KEY (fk_Administrativo_Funcionario_idFuncionario) REFERENCES Administrativo(fk_Funcionario_idFuncionario)
);

CREATE TABLE Monta (
    fk_Operacional_Funcionario_idFuncionario INT,
    fk_Pedido_idPedido INT,
    PRIMARY KEY (fk_Operacional_Funcionario_idFuncionario, fk_Pedido_idPedido),
    FOREIGN KEY (fk_Operacional_Funcionario_idFuncionario) REFERENCES Operacional(fk_Funcionario_idFuncionario),
    FOREIGN KEY (fk_Pedido_idPedido) REFERENCES Pedido(idPedido)
);

INSERT INTO Endereco VALUES 
(1, 'Pernambuco', 'Recife', 'Casa Forte', 'Rua das Palmeiras', NULL, 240), 
(2, 'Pernambuco', 'Recife', 'Casa Forte', 'Rua do Sol', NULL, 566), 
(3, 'Pernambuco', 'Recife', 'Casa Forte', 'Rua do Sol', NULL, 284),
(4, 'Pernambuco', 'Recife', 'Casa Forte', 'Rua da Aurora', NULL, 999), 
(5, 'Pernambuco', 'Olinda', 'Bairro Novo', 'Rua das Palmeiras', NULL, 859);

INSERT INTO Fornecedor VALUES 
('10000000000001', 'Fornecedor 1', '(81) 99745-6313', 'fornecedor1@email.com', 1),
('10000000000002', 'Fornecedor 2', '(81) 99641-5424', 'fornecedor2@email.com', 2),
('10000000000003', 'Fornecedor 3', '(81) 99372-5052', 'fornecedor3@email.com', 3),
('10000000000004', 'Fornecedor 4', '(81) 99935-1170', 'fornecedor4@email.com', 4),
('10000000000005', 'Fornecedor 5', '(81) 99946-2096', 'fornecedor5@email.com', 5);

INSERT INTO MateriaPrima (valorMateriaPrima, codigoEntregaMateriaPrima, dataEstimadaEntregaMateriaPrima) VALUES 
(134.47, 'ENT001', '2025-04-10'),
(104.80, 'ENT002', '2025-04-15'),
(108.68, 'ENT003', '2025-04-20'),
(66.62, 'ENT004', '2025-04-25'),
(179.79, 'ENT005', '2025-04-30');

INSERT INTO Produto (nomeProduto, descricaoProduto, precoProduto) VALUES 
('Óculos Modelo 1', 'Óculos de grau com armação resistente.', 448.13),
('Óculos Modelo 2', 'Óculos de grau com armação resistente.', 636.42),
('Óculos Modelo 3', 'Óculos de grau com armação resistente.', 309.88),
('Óculos Modelo 4', 'Óculos de grau com armação resistente.', 468.62),
('Óculos Modelo 5', 'Óculos de grau com armação resistente.', 655.30);

INSERT INTO EstoqueProduto (fk_Produto_idProduto, quantidadeProduto) VALUES 
(1, 6),
(2, 18),
(3, 10),
(4, 9),
(5, 18);

INSERT INTO Cliente VALUES 
('12345678900', 'Cliente 1', '(81) 99776-1829', 'cliente1@email.com', 2),
('12345678901', 'Cliente 2', '(81) 99857-2254', 'cliente2@email.com', 3),
('12345678902', 'Cliente 3', '(81) 99804-7276', 'cliente3@email.com', 4),
('12345678903', 'Cliente 4', '(81) 99594-1517', 'cliente4@email.com', 3),
('12345678904', 'Cliente 5', '(81) 99958-9688', 'cliente5@email.com', 1);

INSERT INTO Funcionario (nomeFuncionario, salarioFuncionario, dataContratacaoFuncionario, chefeFuncionario, empregado) VALUES 
('Funcionario 1', 2221.88, '2024-04-05', NULL, TRUE),
('Funcionario 2', 2384.46, '2023-04-05', 1, TRUE),
('Funcionario 3', 2152.78, '2022-04-05', 2, TRUE),
('Funcionario 4', 2296.59, '2021-04-05', 3, TRUE),
('Funcionario 5', 1930.79, '2020-04-05', 3, TRUE);

INSERT INTO Operacional VALUES 
(1),
(3),
(5);

INSERT INTO Administrativo VALUES 
(2, 'Gerente de Setor'),
(4, 'Gerente de Setor');

INSERT INTO Pedido (dataPedido, codigoEntregaPedido, quantidadePedido, precoUnitarioPedido, fk_Produto_idProduto, fk_Funcionario_idFuncionario, fk_Cliente_cpfCnpjCliente) VALUES 
(NOW(), 'PED001', 1, 432.11, 1, 1, '12345678900'),
(NOW(), 'PED002', 2, 499.00, 2, 2, '12345678901'),
(NOW(), 'PED003', 3, 299.90, 3, 3, '12345678902'),
(NOW(), 'PED004', 1, 629.90, 4, 4, '12345678903'),
(NOW(), 'PED005', 2, 349.90, 5, 5, '12345678904');

INSERT INTO Atende VALUES 
('12345678900', 2, NOW()),
('12345678901', 2, NOW()),
('12345678902', 2, NOW()),
('12345678903', 2, NOW()),
('12345678904', 2, NOW());

INSERT INTO EstoqueMateriaPrima (fk_MateriaPrima_idMateriaPrima, quantidadeMateriaPrima) VALUES 
(1, 40),
(2, 64),
(3, 59),
(4, 87),
(5, 97);

INSERT INTO Solicita VALUES 
('10000000000001', 1, 2, NOW()),
('10000000000002', 2, 2, NOW()),
('10000000000003', 3, 2, NOW()),
('10000000000004', 4, 2, NOW()),
('10000000000005', 5, 2, NOW());

INSERT INTO Monta VALUES 
(1, 1),
(1, 2),
(3, 3),
(3, 4),
(5, 5);

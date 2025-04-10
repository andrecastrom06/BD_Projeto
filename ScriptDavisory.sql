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
    nomeMateriaPrima varchar (255) not null,
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
    idFuncionario INT PRIMARY key AUTO_INCREMENT,
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
    cargoFuncionarioAdministrativo VARCHAR(100) NOT NULL DEFAULT 'Administrativo',
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
(5, 'Pernambuco', 'Olinda', 'Bairro Novo', 'Rua das Palmeiras', NULL, 859),
(6, 'Pernambuco', 'Olinda', 'Varadouro', 'Rua do Sol Poente', 'Apto 202, Bloco B', 123),
(7, 'Pernambuco', 'Jaboatão dos Guararapes', 'Piedade', 'Rua das Lentes', NULL, 456),
(8, 'Pernambuco', 'Recife', 'Boa Vista', 'Av. Cruz Cabugá', 'Sala 301', 987),
(9, 'Pernambuco', 'Recife', 'Espinheiro', 'Rua do Olhar', 'Casa 4', 215),
(10, 'Pernambuco', 'Olinda', 'Casa Caiada', 'Rua Visão Clara', NULL, 778),
(11, 'Pernambuco', 'Camaragibe', 'Timbi', 'Rua das Ópticas', 'Fundos', 552),
(12, 'Pernambuco', 'Recife', 'Tamarineira', 'Av. Norte Miguel Arraes', 'Próx. ao Hospital', 311),
(13, 'Pernambuco', 'Recife', 'Derby', 'Rua Enxergando Bem', 'Cobertura', 119),
(14, 'Pernambuco', 'Olinda', 'Rio Doce', 'Rua Armação Forte', NULL, 230),
(15, 'Pernambuco', 'Paulista', 'Maranguape I', 'Rua da Visão Perfeita', 'Galpão B', 402);

INSERT INTO Fornecedor VALUES 
('10800112000191', 'Lentes Brasil Óptica Ltda', '(81) 98765-1234', 'comercial@lentesbr.com.br', 1),
('10800112000192', 'Armações Prime Importadora', '(81) 99654-2345', 'vendas@primearmações.com', 2),
('10800112000193', 'Vision Glass Distribuidora', '(81) 99543-3456', 'contato@visionglass.com.br', 3),
('10800112000194', 'ÓpticaTech Componentes', '(81) 99432-4567', 'atendimento@optictech.com.br', 4),
('10800112000195', 'Reflex Lentes Especiais', '(81) 99321-5678', 'sac@reflexlentes.com', 5),
('10800112000196', 'Futura Armações e Acessórios', '(81) 99210-6789', 'futura@armaçõesfuturas.com', 1),
('10800112000197', 'Polar Vision Brasil', '(81) 99109-7890', 'contato@polarvision.com.br', 2),
('10800112000198', 'Óptica Prisma Industrial', '(81) 99008-8901', 'suporte@opticaprisma.com', 3),
('10800112000199', 'LentiSul Fábrica de Lentes', '(81) 98907-9012', 'comercial@lentisul.com.br', 4),
('10800112000200', 'UltraView Suprimentos Ópticos', '(81) 98806-0123', 'ultraview@suprimentosopticos.com', 5),
('10800112000201', 'FrameZone Indústria de Armações', '(81) 98705-1234', 'vendas@framezone.com', 1),
('10800112000202', 'MaxLens Brasil', '(81) 98604-2345', 'contato@maxlens.com.br', 2),
('10800112000203', 'Óptima Tech Vision', '(81) 98503-3456', 'optimatech@visao.com', 3),
('10800112000204', 'Lumis Armações Profissionais', '(81) 98402-4567', 'lumis@armacoes.com', 4),
('10800112000205', 'Veris Óptica Industrial', '(81) 98301-5678', 'veris@opticaindustrial.com.br', 5);

INSERT INTO MateriaPrima (nomeMateriaPrima, valorMateriaPrima, codigoEntregaMateriaPrima, dataEstimadaEntregaMateriaPrima) VALUES 
('Lente CR-39 Antirreflexo', 72.50, 'ENT101', '2025-04-15'),
('Lente Policarbonato Fotossensível', 128.90, 'ENT102', '2025-04-17'),
('Lente Trivex com Proteção UV', 139.20, 'ENT103', '2025-04-20'),
('Armação Acetato Premium', 87.40, 'ENT104', '2025-04-21'),
('Armação de Titânio Leve', 153.30, 'ENT105', '2025-04-22'),
('Parafuso Óptico Padrão', 12.80, 'ENT106', '2025-04-23'),
('Haste Flexível Inoxidável', 24.50, 'ENT107', '2025-04-24'),
('Almofada Nasal de Silicone', 6.90, 'ENT108', '2025-04-25'),
('Lente Espejada Antirrisco', 112.00, 'ENT109', '2025-04-26'),
('Lente Bifocal Linha Fina', 98.30, 'ENT110', '2025-04-27'),
('Kit de Montagem (Mini Chaves)', 33.70, 'ENT111', '2025-04-28'),
('Armação Infantil em Polímero', 45.60, 'ENT112', '2025-04-29'),
('Lente Transitions Photochromic', 165.90, 'ENT113', '2025-04-30'),
('Aro de Ajuste Universal', 19.80, 'ENT114', '2025-05-01'),
('Kit de Tratamento Antirreflexo', 58.70, 'ENT115', '2025-05-02');

INSERT INTO Produto (nomeProduto, descricaoProduto, precoProduto) VALUES 
('Óculos Solar RayFlex RX3000', 'Óculos de sol com lentes polarizadas e armação de titânio escovado. Ideal para direção e uso diário.', 629.90),
('Óculos de Grau VisionLine V-101', 'Armação de acetato resistente com hastes flexíveis. Compatível com lentes multifocais.', 428.50),
('Óculos Unissex UrbanLook U202', 'Modelo leve e moderno com lentes antirreflexo. Design ideal para o dia a dia.', 489.00),
('Óculos de Sol Classic Bold C55', 'Estilo retrô com lentes UV400. Armação preta com acabamento fosco.', 589.90),
('Óculos Infantil ColorView Kids', 'Óculos resistente para crianças com armação colorida em polímero flexível.', 229.90),
('Óculos Feminino BelleVie 3040', 'Design elegante com detalhes dourados nas hastes. Lentes com proteção contra luz azul.', 519.80),
('Óculos de Grau TitanFlex 3.0', 'Armação em titânio leve e flexível. Ideal para quem busca conforto o dia todo.', 679.50),
('Óculos Solar Esportivo ProActive X', 'Modelo esportivo com lentes espelhadas e apoio nasal emborrachado.', 599.00),
('Óculos de Grau SlimFit H02', 'Design fino e discreto, com armação em aço inoxidável e ponte dupla.', 388.40),
('Óculos Unissex StreetLine Neo', 'Estilo casual com acabamento em madeira sintética. Lentes antirrisco.', 474.20),
('Óculos Masculino BoldSquare', 'Armação robusta quadrada com acabamento acetinado. Lentes com filtro UV.', 499.90),
('Óculos Feminino DelicateView F13', 'Armação transparente com hastes em rosé gold. Super leve e moderna.', 545.60),
('Óculos Infantil FlexPlay', 'Material flexível à prova de impacto. Ideal para crianças ativas.', 199.00),
('Óculos de Sol Aviador GoldFlash', 'Clássico modelo aviador com lentes espelhadas douradas e ponte dupla.', 612.30),
('Óculos Masculino Executivo LineX', 'Design sóbrio com armação preta fosca e lentes antirreflexo.', 455.00);

INSERT INTO EstoqueProduto (fk_Produto_idProduto, quantidadeProduto) VALUES 
(1, 12),
(2, 8),
(3, 15),
(4, 20),
(5, 7),
(6, 9),
(7, 11),
(8, 5),
(9, 13),
(10, 6),
(11, 10),
(12, 4),
(13, 14),
(14, 9),
(15, 12);

INSERT INTO Cliente VALUES 
('32165498700', 'Ana Beatriz da Silva', '(81) 98745-1234', 'ana.beatriz@gmail.com', 1),
('85214796301', 'Carlos Eduardo Ferreira', '(81) 99876-5678', 'carlos.ferreira@gmail.com', 2),
('74185296311', 'Mariana Oliveira Costa', '(81) 99788-9012', 'mariana.costa@yahoo.com', 3),
('96325874122', 'Rafael Lima Moura', '(81) 99654-3210', 'rafael.moura@hotmail.com', 4),
('45612378933', 'Juliana Alves Pereira', '(81) 99512-3456', 'juliana.alves@gmail.com', 5),
('65478932144', 'Pedro Henrique Gomes', '(81) 99433-7890', 'pedro.gomes@outlook.com', 1),
('12378945655', 'Isabela Rodrigues', '(81) 99321-4567', 'isabela.rodrigues@gmail.com', 2),
('78945612366', 'Bruno César Martins', '(81) 99234-5678', 'bruno.martins@hotmail.com', 3),
('32198765477', 'Larissa Souza Mendes', '(81) 99111-2233', 'larissa.mendes@gmail.com', 4),
('96374185288', 'Gabriel Fernandes Rocha', '(81) 99098-7654', 'gabriel.rocha@yahoo.com', 5),
('15975348699', 'Camila Duarte Lima', '(81) 98987-4321', 'camila.duarte@uol.com.br', 1),
('75315948600', 'Thiago Nascimento', '(81) 98888-9999', 'thiago.nascimento@gmail.com', 2),
('95135785201', 'Natália Cardoso Freitas', '(81) 98767-1111', 'natalia.freitas@hotmail.com', 3),
('35715925802', 'Felipe Augusto Lira', '(81) 98654-3333', 'felipe.lira@outlook.com', 4),
('25845614703', 'Vanessa Ribeiro Pontes', '(81) 98543-7777', 'vanessa.pontes@gmail.com', 5);

INSERT INTO Funcionario (nomeFuncionario, salarioFuncionario, dataContratacaoFuncionario, chefeFuncionario, empregado) VALUES 
('Julia Gomes', 4525.29, '2008-12-01', NULL, TRUE),
('Diego Santos', 2938.71, '2009-06-15', 1, TRUE),
('Raul Leandro', 1692.32, '2010-11-20', 2, TRUE),
('Diego Ferreira', 1692.32, '2012-03-05', 2, TRUE),
('Diego Sabino', 1692.32, '2013-08-12', 2, TRUE),
('Delrio Roger', 1692.32, '2015-01-10', 2, TRUE),
('Gustavo Henrique', 1692.32, '2016-06-18', 2, TRUE),
('Luiz Meitner', 1692.32, '2017-11-25', 2, TRUE),
('Luiz Correia', 1692.32, '2018-07-03', 2, TRUE),
('José Silva', 1692.32, '2019-02-14', 2, TRUE),
('Marcelo Gomes', 1692.32, '2020-05-09', 2, TRUE),
('Diego Luna', 1692.32, '2021-09-01', 2, TRUE),
('Jonathas Silva', 1692.32, '2022-03-27', 3, TRUE),
('Keven Felipe', 1692.32, '2023-08-19', 3, TRUE),
('Felipe Barbosa', 1692.32, '2024-03-27', 3, TRUE);

INSERT INTO Operacional VALUES 
(3),
(4),
(5),
(6),
(7),
(8),
(9),
(10),
(11),
(12),
(13),
(14);

INSERT INTO Administrativo VALUES 
(1, 'Gerente da Empresa'),
(2, 'Gerente Comercial');

INSERT INTO Pedido (dataPedido, codigoEntregaPedido, quantidadePedido, precoUnitarioPedido, fk_Produto_idProduto, fk_Funcionario_idFuncionario, fk_Cliente_cpfCnpjCliente) VALUES 
('2024-07-10', 'PED006', 1, 520.00, 1, 1, '32165498700'),
('2024-08-15', 'PED007', 2, 345.99, 2, 2, '85214796301'),
('2024-09-03', 'PED008', 1, 289.50, 3, 3, '74185296311'),
('2024-09-27', 'PED009', 3, 610.25, 4, 4, '96325874122'),
('2024-10-08', 'PED010', 1, 429.90, 5, 5, '45612378933'),
('2024-10-30', 'PED011', 2, 599.99, 6, 1, '65478932144'),
('2024-11-11', 'PED012', 1, 319.00, 7, 2, '12378945655'),
('2024-11-23', 'PED013', 2, 388.40, 8, 3, '78945612366'),
('2024-12-05', 'PED014', 1, 712.00, 9, 4, '32198765477'),
('2024-12-20', 'PED015', 2, 409.99, 10, 5, '96374185288'),
('2025-01-02', 'PED016', 1, 498.50, 11, 1, '15975348699'),
('2025-01-18', 'PED017', 1, 385.00, 12, 2, '75315948600'),
('2025-01-31', 'PED018', 3, 425.75, 13, 3, '95135785201'),
('2025-02-10', 'PED019', 2, 369.60, 14, 4, '35715925802'),
('2025-02-25', 'PED020', 1, 599.99, 15, 5, '25845614703');

INSERT INTO EstoqueMateriaPrima (fk_MateriaPrima_idMateriaPrima, quantidadeMateriaPrima) VALUES 
(1, 87),
(2, 76),
(3, 43),
(4, 34),
(5, 61),
(6, 55),
(7, 70),
(8, 39),
(9, 68),
(10, 47),
(11, 52),
(12, 63),
(13, 29),
(14, 46),
(15, 58);

INSERT INTO Atende VALUES 
('32165498700', 1, '2024-07-12'),
('85214796301', 2, '2024-08-03'),
('74185296311', 2, '2024-08-28'),
('96325874122', 2, '2024-09-14'),
('45612378933', 2, '2024-09-30'),
('65478932144', 2, '2024-10-21'),
('12378945655', 2, '2024-11-05'),
('78945612366', 2, '2024-11-23'),
('32198765477', 2, '2024-12-10'),
('96374185288', 2, '2024-12-27'),
('15975348699', 2, '2025-01-09'),
('75315948600', 2, '2025-01-22'),
('95135785201', 2, '2025-02-05'),
('35715925802', 2, '2025-02-17'),
('25845614703', 2, '2025-02-28');

INSERT INTO Solicita (fk_Fornecedor_cnpjFornecedor, fk_MateriaPrima_idMateriaPrima, fk_Administrativo_Funcionario_idFuncionario, dataSolicitacao) VALUES
('10800112000191', 1, 1, '2024-07-19'),
('10800112000192', 2, 2, '2024-08-11'),
('10800112000193', 2, 2, '2024-09-02'),
('10800112000194', 2, 2, '2024-10-16'),
('10800112000195', 2, 2, '2024-12-01'),
('10800112000196', 3, 2, '2024-12-15'),
('10800112000197', 4, 2, '2025-01-05'),
('10800112000198', 5, 2, '2025-01-20'),
('10800112000199', 6, 2, '2025-02-02'),
('10800112000200', 7, 1, '2025-02-15'),
('10800112000201', 8, 1, '2025-02-28'),
('10800112000202', 9, 2, '2025-03-10'),
('10800112000203', 10, 2, '2025-03-20'),
('10800112000204', 11, 2, '2025-03-28'),
('10800112000205', 12, 1, '2025-04-05');

INSERT INTO Monta (fk_Operacional_Funcionario_idFuncionario, fk_Pedido_idPedido) VALUES
(3, 1),
(4, 2),
(5, 3),
(6, 4),
(7, 5),
(8, 6),
(9, 7),
(10, 8),
(6, 9),
(7, 10),
(8, 11),
(9, 12),
(10, 13),
(6, 14),
(7, 15);
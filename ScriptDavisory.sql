CREATE DATABASE davisory;
USE davisory;

CREATE TABLE Endereco (
    idEndereco INT PRIMARY KEY,
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
    idMateriaPrima INT PRIMARY KEY,
    valorMateriaPrima DECIMAL(10,2) NOT NULL CHECK (valorMateriaPrima >= 0),
    codigoEntregaMateriaPrima VARCHAR(50) UNIQUE NOT NULL,
    dataEstimadaEntregaMateriaPrima DATE NOT NULL
);

CREATE TABLE Produto (
    idProduto INT PRIMARY KEY,
    nomeProduto VARCHAR(255) NOT NULL,
    descricaoProduto TEXT NOT NULL,
    precoProduto DECIMAL(10,2) NOT NULL CHECK (precoProduto >= 0)
);

CREATE TABLE EstoqueProduto (
    idEstoqueProduto INT,
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
    idFuncionario INT PRIMARY KEY,
    nomeFuncionario VARCHAR(255) NOT NULL,
    salarioFuncionario DECIMAL(10,2) NOT NULL CHECK (salarioFuncionario >= 0),
    dataContratacaoFuncionario DATE NOT NULL,
    chefeFuncionario INT,
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
    idPedido INT PRIMARY KEY,
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
    idEstoqueMateriaPrima INT,
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
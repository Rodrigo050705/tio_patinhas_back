-- Create ENDERECO table
CREATE TABLE ENDERECO (
    id_endereco         VARCHAR2(50) NOT NULL PRIMARY KEY,
    rua                 VARCHAR2(50) NOT NULL,
    numero              NUMBER(5) NOT NULL,
    complemento         VARCHAR2(50) NULL,
    bairro              VARCHAR2(50) NOT NULL,
    cidade              VARCHAR2(50) NOT NULL,
    cd_estado           VARCHAR2(50) NOT NULL,
    cep                 VARCHAR2(50) NOT NULL,
    pais                VARCHAR2(2) NOT NULL
);

-- Create CLIENTES table
CREATE TABLE CLIENTES (
    id_cliente          VARCHAR2(50) NOT NULL PRIMARY KEY,
    nome_completo       VARCHAR2(100) NOT NULL,
    data_nascimento     DATE NOT NULL,
    cpf_cnpj           VARCHAR2(50) NOT NULL,
    email               VARCHAR2(100) NOT NULL,
    senha               VARCHAR2(100) NOT NULL,
    telefone            VARCHAR2(50) NOT NULL,
    data_criacao       DATE NOT NULL,
    cep                 VARCHAR2(50) NOT NULL,
    pais                VARCHAR2(2) NOT NULL
);

-- Create CORRETORA table
CREATE TABLE CORRETORA (
    id_corretora       VARCHAR2(50) NOT NULL PRIMARY KEY,
    nome_corretora     VARCHAR2(100) NOT NULL,
    cnpj               VARCHAR2(50) NOT NULL,
    email              VARCHAR2(100) NOT NULL,
    telefone           VARCHAR2(50) NOT NULL,
    tipos_criptoativos_suportados BLOB NOT NULL,
    endereco_carteira_corretora VARCHAR2(50) NOT NULL
);

-- Create CONTAS_CRIPTO table
CREATE TABLE CONTAS_CRIPTO (
    nr_conta           VARCHAR(10) NOT NULL PRIMARY KEY,
    password           VARCHAR(50) NOT NULL,
    tipo_conta         VARCHAR2(100) NOT NULL,
    saldo              NUMBER(10,2) NOT NULL,
    data_abertura      DATE NOT NULL,
    status_conta       VARCHAR2(20) NOT NULL,
    endereco_carteira  VARCHAR2(100) NOT NULL,
    id_cliente         VARCHAR2(50) NOT NULL,
    id_corretora       VARCHAR2(50) NOT NULL
    -- FOREIGN KEY (id_cliente) REFERENCES CLIENTES (id_cliente) ON DELETE CASCADE,
    -- FOREIGN KEY (id_corretora) REFERENCES CORRETORA (id_corretora) ON DELETE CASCADE
);

-- Create TRANSACAO_CRIPTO table
CREATE TABLE TRANSACAO_CRIPTO (
    id_transacao       VARCHAR2(50) NOT NULL PRIMARY KEY,
    data_hora          TIMESTAMP NOT NULL,
    tipo_transacao     VARCHAR2(50) NOT NULL,
    valor              NUMBER(10,2) NOT NULL,
    descricao          VARCHAR2(255) NOT NULL,
    endereco_origem    VARCHAR2(50) NOT NULL,
    endereco_destino   VARCHAR2(50) NOT NULL,
    hash_transacao     VARCHAR2(50) NOT NULL,
    taxa_transacao     NUMBER(5) NOT NULL,
    nr_conta           NUMBER(10) NOT NULL,
    id_cliente         VARCHAR2(50) NOT NULL,
    id_corretora       VARCHAR2(50) NOT NULL,
    FOREIGN KEY (nr_conta) REFERENCES CONTAS_CRIPTO (nr_conta) ON DELETE CASCADE,
    FOREIGN KEY (id_cliente) REFERENCES CLIENTES (id_cliente) ON DELETE CASCADE,
    FOREIGN KEY (id_corretora) REFERENCES CORRETORA (id_corretora) ON DELETE CASCADE
);

-- Create CRIPTOMOEDAS table
CREATE TABLE CRIPTOMOEDAS (
    id_criptomoeda     VARCHAR2(50) NOT NULL PRIMARY KEY,
    nome                VARCHAR2(50) NOT NULL,
    simbolo             VARCHAR2(50) NOT NULL
);

-- Create HISTORICO_PRECOS table
CREATE TABLE HISTORICO_PRECOS (
    id_registro        VARCHAR2(50) NOT NULL PRIMARY KEY,
    data_hora          TIMESTAMP NOT NULL,
    preco_abertura     NUMBER(10,2) NOT NULL,
    preco_fechamento   NUMBER(10,2) NOT NULL,
    preco_max          NUMBER(10,2) NOT NULL,
    preco_min          NUMBER(10,2) NOT NULL,
    volume_negociacao  NUMBER(10,2) NOT NULL,
    id_criptomoeda     VARCHAR2(50) NOT NULL,
    FOREIGN KEY (id_criptomoeda) REFERENCES CRIPTOMOEDAS (id_criptomoeda) ON DELETE CASCADE
);

V001__criacao-inicial.sql

create table cozinha (
	id bigserial  primary key,
	nome varchar(60) NOT NULL
		
);



V002__cria-tabela-estado.sql

create table estado (
	id bigserial  primary key,
	nome varchar(80) NOT NULL
	
);



V003__cria-tabela-cidade.sql

create table cidade (
	id bigserial  primary key,
	nome varchar(80) NOT NULL,
	estado_id integer NOT NULL,
	
	foreign key(estado_id) references estado (id)
	
	
);



V004__cria-varias-tabelas.sql
create table forma_pagamento (
id  bigserial not null, 
descricao varchar(60) not null, 
primary key (id));

create table grupo (
id  bigserial not null, 
nome varchar(60) not null, 
primary key (id));

create table grupo_permissao (
grupo_id int8 not null, 
permissao_id int8 not null);

create table permissao (
id  bigserial not null, 
descricao varchar(60) not null, 
nome varchar(100) not null, 
primary key (id));

create table produto (
id  bigserial not null, 
ativo boolean not null, 
descricao text not null, 
nome varchar(80) not null, 
preco decimal(10, 2) not null, 
restaurante_id int8 not null, 
primary key (id));

create table restaurante (
id  bigserial not null, 
data_atualizacao timestamp not null, 
data_cadastro timestamp not null, 

endereco_bairro varchar(60), 
endereco_cep varchar(9), 
endereco_complemento varchar(60), 
endereco_logradouro varchar(100), 
endereco_numero varchar(20), 
nome varchar(80) not null, 
taxa_frete decimal (10, 2) not null, 
cozinha_id int8 not null, 
endereco_cidade_id int8, 
primary key (id));

create table restaurante_forma_pagamento (
restaurante_id int8 not null, 
forma_pagamento_id int8 not null);

create table usuario (
id  bigserial not null, 
data_cadastro timestamp not null, 
email varchar(255) not null, 
nome varchar(80) not null, 
senha varchar(255) not null, 
primary key (id));

create table usuario_grupo (
usuario_id int8 not null,
grupo_id int8 not null);

alter table grupo_permissao add constraint fk_grupo_permissao_permissao
foreign key (permissao_id) references permissao;

alter table grupo_permissao add constraint fk_grupo_permissao_grupo 
foreign key (grupo_id) references grupo;

alter table produto add constraint fk_produto_restaurante 
foreign key (restaurante_id) references restaurante;

alter table restaurante add constraint fk_restaurante_cozinha
foreign key (cozinha_id) references cozinha;

alter table restaurante add constraint fk_restaurante_cidade 
foreign key (endereco_cidade_id) references cidade;

alter table restaurante_forma_pagamento add constraint fk_rest_forma_pagto_forma_pagto 
foreign key (forma_pagamento_id) references forma_pagamento;

alter table restaurante_forma_pagamento add constraint fk_rest_forma_pagto_restaurante
foreign key (restaurante_id) references restaurante;

alter table usuario_grupo add constraint fk_usuario_grupo_grupo
foreign key (grupo_id) references grupo;

alter table usuario_grupo add constraint fk_usuario_grupo_usuario
foreign key (usuario_id) references usuario;


afterMigrate.sql = 

ALTER TABLE cidade DISABLE TRIGGER ALL;
ALTER TABLE cozinha DISABLE TRIGGER ALL;
ALTER TABLE estado DISABLE TRIGGER ALL;
ALTER TABLE forma_pagamento DISABLE TRIGGER ALL;
ALTER TABLE grupo DISABLE TRIGGER ALL;
ALTER TABLE grupo_permissao DISABLE TRIGGER ALL;
ALTER TABLE permissao DISABLE TRIGGER ALL;
ALTER TABLE produto DISABLE TRIGGER ALL;
ALTER TABLE restaurante DISABLE TRIGGER ALL;
ALTER TABLE restaurante_forma_pagamento DISABLE TRIGGER ALL;
ALTER TABLE usuario DISABLE TRIGGER ALL;
ALTER TABLE usuario_grupo DISABLE TRIGGER ALL;

delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from produto;
delete from restaurante;
delete from restaurante_forma_pagamento;
delete from usuario;
delete from usuario_grupo;


ALTER SEQUENCE cidade_id_seq RESTART WITH 1;
ALTER SEQUENCE cozinha_id_seq RESTART WITH 1;
ALTER SEQUENCE estado_id_seq RESTART WITH 1;
ALTER SEQUENCE forma_pagamento_id_seq RESTART WITH 1;
ALTER SEQUENCE grupo_id_seq RESTART WITH 1;
ALTER SEQUENCE permissao_id_seq RESTART WITH 1;
ALTER SEQUENCE produto_id_seq RESTART WITH 1;
ALTER SEQUENCE restaurante_id_seq RESTART WITH 1;
ALTER SEQUENCE usuario_id_seq RESTART WITH 1;


insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Indiana');
insert into cozinha (nome) values ('Argentina');
insert into cozinha (nome) values ('Brasileira');

insert into estado (nome) values ( 'Goiás');
insert into estado (nome) values ( 'São Paulo');
insert into estado (nome) values ( 'Minas Gerais');

insert into cidade (nome, estado_id) values ( 'Goiânia', 1);
insert into cidade (nome, estado_id) values ( 'São Paulo', 1);
insert into cidade (nome, estado_id) values ( 'Belo Horizonte', 2);
insert into cidade (nome, estado_id) values ( 'Campinas', 2);
insert into cidade (nome, estado_id) values ( 'Uberlândia', 3);


insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Thai Gourmet', 10, 1, current_timestamp, current_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Thai Delivery', 9.50, 1, current_timestamp, current_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (3, 'Tuk Tuk Comida Indiana', 15, 2, current_timestamp, current_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (4, 'Java Steakhouse', 12, 3, current_timestamp, current_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (5, 'Lanchonete do Tio Sam', 11, 4, current_timestamp, current_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (6, 'Bar da Maria', 6, 4, current_timestamp, current_timestamp);


insert into forma_pagamento (descricao) values ( 'Cartão de crédito');
insert into forma_pagamento (descricao) values ( 'Cartão de débito');
insert into forma_pagamento (descricao) values ( 'Dinheiro');

insert into permissao (nome, descricao) values ( 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (nome, descricao) values ( 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, true, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, true, 1);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, true, 2);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, true, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, true, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, true, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, true, 4);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, true, 5);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, true, 6);



ALTER TABLE cidade ENABLE TRIGGER ALL;
ALTER TABLE cozinha ENABLE TRIGGER ALL;
ALTER TABLE estado ENABLE TRIGGER ALL;
ALTER TABLE forma_pagamento ENABLE TRIGGER ALL;
ALTER TABLE grupo ENABLE TRIGGER ALL;
ALTER TABLE grupo_permissao ENABLE TRIGGER ALL;
ALTER TABLE permissao ENABLE TRIGGER ALL;
ALTER TABLE produto ENABLE TRIGGER ALL;
ALTER TABLE restaurante ENABLE TRIGGER ALL;
ALTER TABLE restaurante_forma_pagamento ENABLE TRIGGER ALL;
ALTER TABLE usuario ENABLE TRIGGER ALL;
ALTER TABLE usuario_grupo ENABLE TRIGGER ALL;


create table pedido (
id  bigserial not null, 
subtotal decimal (10, 2) not null,
taxa_frete decimal (10, 2) not null,
valor_total decimal (10, 2) not null,

restaurante_id int8 not null,
usuario_cliente_id int8 not null,
forma_pagamento_id int8 not null, 

endereco_cidade_id int8  not null, 
endereco_cep varchar(9) not null,
endereco_logradouro varchar(100) not null,
endereco_numero varchar(20) not null, 
endereco_complemento varchar(60) not null, 
endereco_bairro varchar(60) not null, 

status varchar (10) not null,
data_criacao timestamp not null,
data_confirmacao timestamp not null,
data_cancelamento timestamp not null,
data_entrega timestamp not null,

primary key (id));

alter table pedido add constraint fk_pedido_restaurante
foreign key (restaurante_id) references restaurante;

alter table pedido add constraint fk_pedido_usuario_cliente
foreign key (usuario_cliente_id) references usuario;

alter table pedido add constraint fk_pedido_forma_pagamento
foreign key (forma_pagamento_id) references forma_pagamento;



create table item_pedido (
	id bigserial not null,
	quantidade int8  NOT NULL,
	preco_unitario decimal(10,2) not null,
    preco_total decimal(10,2) not null,
    observacao varchar(255) null,
    pedido_id bigint not null,
    produto_id bigint not null,
    
	UNIQUE (pedido_id, produto_id),
primary key (id));
	
alter table item_pedido add constraint fk_item_pedido_pedido
foreign key (pedido_id) references pedido;
	
alter table item_pedido add constraint fk_item_pedido_produto
foreign key (produto_id) references produto;
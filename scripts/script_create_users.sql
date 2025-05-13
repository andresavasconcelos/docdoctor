USE docdoctor_db;

-- Inserir 30 usuários seguindo as validações da entidade User
INSERT INTO tbl_users (full_name, email, phone, birth_date, user_type) VALUES
-- Administradores (5 registros)
('Admin Master', 'admin.master@docdoctor.com', '+55 11 91234-5678', '1980-01-10', 'ADMIN'),
('Maria Administradora', 'maria.admin@docdoctor.com', '+55 21 92345-6789', '1985-05-15', 'ADMIN'),
('Carlos Gerente', 'carlos.gerente@docdoctor.com', '+55 31 93456-7890', '1978-08-20', 'ADMIN'),
('Ana Diretora', 'ana.diretora@docdoctor.com', '+55 41 94567-8901', '1982-11-25', 'ADMIN'),
('Pedro Supervisor', 'pedro.super@docdoctor.com', '+55 51 95678-9012', '1975-03-30', 'ADMIN'),

-- Visualizadores (10 registros)
('João Visualizador', 'joao.view@docdoctor.com', '+55 11 96789-0123', '1990-02-05', 'VIEWER'),
('Fernanda Observadora', 'fernanda.view@docdoctor.com', '+55 21 97890-1234', '1992-04-12', 'VIEWER'),
('Ricardo Consultor', 'ricardo.view@docdoctor.com', '+55 31 98901-2345', '1988-06-18', 'VIEWER'),
('Sofia Analista', 'sofia.view@docdoctor.com', '+55 41 99012-3456', '1995-09-22', 'VIEWER'),
('Marcos Auditor', 'marcos.view@docdoctor.com', '+55 51 90123-4567', '1993-07-14', 'VIEWER'),
('Laura Pesquisadora', 'laura.view@docdoctor.com', '+55 11 91234-5670', '1987-10-08', 'VIEWER'),
('Gustavo Monitor', 'gustavo.view@docdoctor.com', '+55 21 92345-6781', '1991-12-03', 'VIEWER'),
('Patrícia Inspetora', 'patricia.view@docdoctor.com', '+55 31 93456-7892', '1989-01-17', 'VIEWER'),
('Lucas Fiscal', 'lucas.view@docdoctor.com', '+55 41 94567-8903', '1994-05-19', 'VIEWER'),
('Camila Revisora', 'camila.view@docdoctor.com', '+55 51 95678-9014', '1996-08-21', 'VIEWER'),

-- Editores (15 registros)
('Roberto Editor', 'roberto.edit@docdoctor.com', '+55 11 96789-0125', '1983-03-07', 'EDITOR'),
('Amanda Redatora', 'amanda.edit@docdoctor.com', '+55 21 97890-1236', '1986-06-11', 'EDITOR'),
('Felipe Escritor', 'felipe.edit@docdoctor.com', '+55 31 98901-2347', '1984-09-13', 'EDITOR'),
('Isabela Autora', 'isabela.edit@docdoctor.com', '+55 41 99012-3458', '1981-12-16', 'EDITOR'),
('Rafael Revisor', 'rafael.edit@docdoctor.com', '+55 51 90123-4569', '1987-02-20', 'EDITOR'),
('Beatriz Produtora', 'beatriz.edit@docdoctor.com', '+55 11 91234-5671', '1990-04-23', 'EDITOR'),
('Daniel Publicador', 'daniel.edit@docdoctor.com', '+55 21 92345-6782', '1989-07-26', 'EDITOR'),
('Vanessa Criadora', 'vanessa.edit@docdoctor.com', '+55 31 93456-7893', '1985-10-29', 'EDITOR'),
('Leonardo Desenvolvedor', 'leonardo.edit@docdoctor.com', '+55 41 94567-8904', '1982-01-31', 'EDITOR'),
('Tatiana Designer', 'tatiana.edit@docdoctor.com', '+55 51 95678-9015', '1988-05-02', 'EDITOR'),
('Eduardo Gestor', 'eduardo.edit@docdoctor.com', '+55 11 96789-0126', '1980-08-04', 'EDITOR'),
('Carla Coordenadora', 'carla.edit@docdoctor.com', '+55 21 97890-1237', '1979-11-06', 'EDITOR'),
('Antônio Supervisor', 'antonio.edit@docdoctor.com', '+55 31 98901-2348', '1983-09-09', 'EDITOR'),
('Helena Gerente', 'helena.edit@docdoctor.com', '+55 41 99012-3459', '1986-12-12', 'EDITOR'),
('Bruno Diretor', 'bruno.edit@docdoctor.com', '+55 51 90123-4560', '1984-02-15', 'EDITOR');

-- Verificar os dados inseridos
SELECT * FROM tbl_users;
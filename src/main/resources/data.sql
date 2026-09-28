-- Inserção dos Perfis de Acesso (Roles)
INSERT INTO tb_perfil (nome) VALUES ('ROLE_CLIENTE');
INSERT INTO tb_perfil (nome) VALUES ('ROLE_TECNICO');
INSERT INTO tb_perfil (nome) VALUES ('ROLE_ADMIN');

-- Inserção de Usuários de Teste (Senha padrão para todos: 123456)
-- Hash BCrypt de '123456': $2a$10$e883mN3kY... (exemplo)
INSERT INTO tb_usuario (nome, email, senha, data_criacao)
VALUES ('Cliente Teste', 'cliente@email.com', '$2a$10$e883mN3kYJ8E0Y1I2M.A.u1PZ5F9pG9a2gB3C4D5E6F7G8H9I0J1K', CURRENT_TIMESTAMP);

INSERT INTO tb_usuario (nome, email, senha, data_criacao)
VALUES ('Técnico Suporte', 'tecnico@email.com', '$2a$10$e883mN3kYJ8E0Y1I2M.A.u1PZ5F9pG9a2gB3C4D5E6F7G8H9I0J1K', CURRENT_TIMESTAMP);

INSERT INTO tb_usuario (nome, email, senha, data_criacao)
VALUES ('Administrador Sistema', 'admin@email.com', '$2a$10$e883mN3kYJ8E0Y1I2M.A.u1PZ5F9pG9a2gB3C4D5E6F7G8H9I0J1K', CURRENT_TIMESTAMP);

-- Vinculação de Usuários com seus respectivos Perfis
INSERT INTO tb_usuario_perfil (usuario_id, perfil_id) VALUES (1, 1); -- Cliente Teste -> ROLE_CLIENTE
INSERT INTO tb_usuario_perfil (usuario_id, perfil_id) VALUES (2, 2); -- Técnico Suporte -> ROLE_TECNICO
INSERT INTO tb_usuario_perfil (usuario_id, perfil_id) VALUES (3, 3); -- Admin Sistema -> ROLE_ADMIN

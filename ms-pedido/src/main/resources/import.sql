INSERT INTO tb_pedido(nome, cpf, data, status) VALUES('Jon Snow', '12345678935', '2025-04-25', 'REALIZADO');
INSERT INTO tb_pedido(nome, cpf, data, status) VALUES('Ayra Stark', '3456789653', '2025-04-25', 'REALIZADO');

INSERT INTO tb_item_do_pedido(quantidade, descricao, valor_unitario, pedido_id) VALUES(2, ' Mouse sem fio microsoft', 250.0, 1);
INSERT INTO tb_item_do_pedido(quantidade, descricao, valor_unitario, pedido_id) VALUES(1, ' Teclado sem fio microsoft', 390.0, 1);

INSERT INTO tb_item_do_pedido(quantidade, descricao, valor_unitario, pedido_id) VALUES(1, 'Smart TV LG LED', 3599.0, 2);
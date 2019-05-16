CREATE TABLE imagen(
    id int NOT NULL,
    nombre varchar(9),
    url varchar(150),
    id_cliente int,
    fecha date,
    PRIMARY KEY (id)
);

INSERT INTO imagen (id, nombre, url) VALUES (
    1, 'imagen1', 'https://image.flaticon.com/icons/png/512/381/381572.png'
);

INSERT INTO imagen (id, nombre, url) VALUES (
    2, 'imagen2', 'https://image.flaticon.com/icons/png/512/90/90603.png'
);

INSERT INTO imagen (id, nombre, url) VALUES (
    3, 'imagen3', 'https://image.flaticon.com/icons/png/512/802/802192.png'
);

INSERT INTO imagen (id, nombre, url) VALUES (
    4, 'imagen4', 'https://image.flaticon.com/icons/png/512/644/644617.png'
);

INSERT INTO imagen (id, nombre, url) VALUES (
    5, 'imagen5', 'https://www.pinclipart.com/picdir/middle/50-502598_icono-cliente-png-clipart.png'
);

INSERT INTO imagen (id, nombre, url) VALUES (
    6, 'imagen6', 'https://images.vexels.com/media/users/3/132335/isolated/preview/4af43ce1082231cba5e5aa60fbb03f2f-c--rculos-de-personal-iconos-by-vexels.png'
);

INSERT INTO imagen (id, nombre, url) VALUES (
    7, 'imagen7', 'https://image.flaticon.com/icons/png/512/91/91059.png'
);

INSERT INTO imagen (id, nombre, url) VALUES (
    8, 'imagen8', 'https://image.flaticon.com/icons/png/512/105/105283.png'
);

INSERT INTO imagen (id, nombre, url) VALUES (
    9, 'imagen9', 'https://i.pinimg.com/originals/59/57/75/595775041933aeb57cee83e4934d006b.png'
);

INSERT INTO imagen (id, nombre, url) VALUES (
    10, 'imagen10', 'https://images.vexels.com/media/users/3/151879/isolated/lists/db10b16cc41213b78786bff651b02a2b-icono-de-maleta-medico.png'
);
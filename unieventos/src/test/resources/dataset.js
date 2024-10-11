db = connect('mongodb://localhost:27017/proyecto');


db.cuentas.insertMany([
    {
        _id: '66a2a9aaa8620e3c1c5437be',
        rol: 'CLIENTE',
        estado: 'INACTIVO',
        email: 'pepeperez@email.com',
        password: 'password',
        usuario: {
            cedula: '1213444',
            nombre: 'Pepito perez',
            telefono: '3012223333',
            direccion: 'Calle 12 # 12-12',
        },
        fechaRegistro: ISODate('2024-07-25T21:41:57.849Z'),
        _class: 'co.edu.uniquindio.unieventos.modelo.Cuenta'
    },
    {
        _id: '66a2c14dd9219911cd34f2c0',
        rol: 'CLIENTE',
        estado: 'ACTIVO',
        email: 'rosalopez@email.com',
        password: 'password',
        usuario: {
            cedula: '1213445',
            nombre: 'Rosa Lopez',
            telefono: '3128889191',
            direccion: 'Calle ABC # 12-12',
        },
        fechaRegistro: ISODate('2024-08-02T21:41:57.849Z'),
        _class: 'co.edu.uniquindio.unieventos.modelo.Cuenta'
    },
    {
        _id: '66a2c1517f3b340441ffdeb0',
        rol: 'ADMINISTRADOR',
        estado: 'ACTIVO',
        email: 'admin1@email.com',
        password: 'password',
        usuario: {
            nombre: 'Admin 1'
        },
        fechaRegistro: ISODate('2024-08-25T21:41:57.849Z'),
        _class: 'co.edu.uniquindio.unieventos.modelo.Cuenta'
    },
    {
        _id: '66a2c14dd9219911cd34f2c1',
        rol: 'CLIENTE',
        estado: 'ACTIVO',
        email: 'juanperez@email.com',
        password: 'Juan123',
        usuario: {
            cedula: '1234567',
            nombre: 'Juan Perez',
            telefono: '3105551234',
            direccion: 'Avenida Principal # 45-67'
        },
        fechaRegistro: ISODate('2024-09-10T14:30:12.849Z'),
        _class: 'co.edu.uniquindio.unieventos.modelo.Cuenta'
    },
    {
        _id: '66a2c14dd9219911cd34f2c2',
        rol: 'CLIENTE',
        estado: 'ACTIVO',
        email: 'mariagomez@email.com',
        password: 'mariapassword',
        usuario: {
            cedula: '7654321',
            nombre: 'Maria Gomez',
            telefono: '3119876543',
            direccion: 'Carrera 9 # 10-12'
        },
        fechaRegistro: ISODate('2024-09-15T10:15:00.849Z'),
        _class: 'co.edu.uniquindio.unieventos.modelo.Cuenta'
    }
]);

db.eventos.insertMany([
    {
        _id: '66a2c476991cff088eb80aaf',
        nombre: 'Concierto de despedida del 2024',
        descripcion: 'Concierto con los mejores artistas del 2024 para despedir el año',
        fecha: ISODate('2024-11-11T01:00:00.000Z'),
        tipo: 'CONCIERTO',
        direccion: 'Coliseo de eventos, calle 10 # 10-10',
        ciudad: 'Armenia',
        localidades: [
            {
                nombre: 'VIP',
                precio: 80000,
                capacidadMaxima: 50
            },
            {
                nombre: 'GENERAL',
                precio: 20000,
                capacidadMaxima: 200
            }
        ],
        imagenPortada: 'Url de la imagen del poster del concierto',
        imagenLocalidades: 'Url de la imagen de la distribución de las localidades',
        _class: 'co.edu.uniquindio.unieventos.modelo.Evento'
    },
    {
        _id: '66a2c476991cff088eb80ab0',
        nombre: 'Festival de Música y Arte',
        descripcion: 'Un festival que celebra la música y el arte local con artistas de la región.',
        fecha: ISODate('2024-12-01T10:00:00.000Z'),
        tipo: 'FESTIVAL',
        direccion: 'Parque de la Vida, carrera 8 # 5-20',
        ciudad: 'Armenia',
        localidades: [
            {
                nombre: 'VIP',
                precio: 100000,
                capacidadMaxima: 30
            },
            {
                nombre: 'GENERAL',
                precio: 30000,
                capacidadMaxima: 150
            }
        ],
        imagenPortada: 'Url de la imagen del festival',
        imagenLocalidades: 'Url de la imagen de la distribución de las localidades',
        _class: 'co.edu.uniquindio.unieventos.modelo.Evento'
    },
    {
        _id: '66a2c476991cff088eb80ab1',
        nombre: 'Teatro: La Historia de Colombia',
        descripcion: 'Una obra de teatro que narra la historia de Colombia a través de sus personajes más emblemáticos.',
        fecha: ISODate('2024-11-15T20:00:00.000Z'),
        tipo: 'TEATRO',
        direccion: 'Teatro Municipal, avenida 19 # 14-40',
        ciudad: 'Manizales',
        localidades: [
            {
                nombre: 'VIP',
                precio: 60000,
                capacidadMaxima: 80
            },
            {
                nombre: 'GENERAL',
                precio: 40000,
                capacidadMaxima: 120
            }
        ],
        imagenPortada: 'Url de la imagen de la obra de teatro',
        imagenLocalidades: 'Url de la imagen de la distribución de las localidades',
        _class: 'co.edu.uniquindio.unieventos.modelo.Evento'
    },
    {
        _id: '66a2c476991cff088eb80ab2',
        nombre: 'Exposición de Arte Moderno',
        descripcion: 'Exposición que reúne las obras de los artistas más innovadores del arte moderno.',
        fecha: ISODate('2024-10-05T09:00:00.000Z'),
        tipo: 'EXPOSICIÓN',
        direccion: 'Museo de Arte Moderno, calle 15 # 6-40',
        ciudad: 'Pereira',
        localidades: [
            {
                nombre: 'GENERAL',
                precio: 25000,
                capacidadMaxima: 300
            }
        ],
        imagenPortada: 'Url de la imagen de la exposición',
        imagenLocalidades: 'Url de la imagen de la distribución de las localidades',
        _class: 'co.edu.uniquindio.unieventos.modelo.Evento'
    },
    {
        _id: '66a2c476991cff088eb80ab3',
        nombre: 'Competencia de Cocina Internacional',
        descripcion: 'Una competencia donde los mejores chefs de la región compiten por el título del mejor cocinero.',
        fecha: ISODate('2024-11-20T15:00:00.000Z'),
        tipo: 'COMPETENCIA',
        direccion: 'Centro de Convenciones, carrera 5 # 25-30',
        ciudad: 'Caldas',
        localidades: [
            {
                nombre: 'VIP',
                precio: 70000,
                capacidadMaxima: 50
            },
            {
                nombre: 'GENERAL',
                precio: 20000,
                capacidadMaxima: 100
            }
        ],
        imagenPortada: 'Url de la imagen de la competencia',
        imagenLocalidades: 'Url de la imagen de la distribución de las localidades',
        _class: 'co.edu.uniquindio.unieventos.modelo.Evento'
    }

]);


db.ordenes.insertMany([
    {
        _id: '66a2c6a55773597d73593fff',
        detalle: [
            {
                codigoEvento: '66a2c476991cff088eb80aaf',
                nombreLocalidad: 'GENERAL',
                precio: 50000,
                cantidad: 2
            }
        ],
        codigoCliente: '66a2a9aaa8620e3c1c5437be',
        total: 100000,
        fecha: ISODate('2024-07-25T21:41:57.849Z'),
        codigoPasarela: 'CODIGO_PASARELA',
        pago: {
            codigo: '48dc3dd9-bde1-45ae-b23f-27ee7a261f00',
            fecha: ISODate('2024-07-25T21:41:57.849Z'),
            totalPagado: 100000,
            estado: 'APROBADA',
            metodoPago: 'TARJETA DE CRÉDITO'
        },
        _class: 'co.edu.uniquindio.unieventos.modelo.Orden'
    },
    {
        _id: '66a2c6a55773597d73593ff0',
        detalle: [
            {
                codigoEvento: '66a2c476991cff088eb80aaf',
                nombreLocalidad: 'VIP',
                precio: 80000,
                cantidad: 1
            }
        ],
        codigoCliente: '66a2c14dd9219911cd34f2c0',
        total: 80000,
        fecha: ISODate('2024-08-10T15:30:00.849Z'),
        codigoPasarela: 'CODIGO_PASARELA_1',
        pago: {
            codigo: 'f2d08cc9-2e03-42a1-a38b-e028507ef8a0',
            fecha: ISODate('2024-08-10T15:30:00.849Z'),
            totalPagado: 80000,
            estado: 'APROBADA',
            metodoPago: 'EFECTIVO'
        },
        _class: 'co.edu.uniquindio.unieventos.modelo.Orden'
    },
    {
        _id: '66a2c6a55773597d73593ff1',
        detalle: [
            {
                codigoEvento: '66a2c476991cff088eb80b0a',
                nombreLocalidad: 'GENERAL',
                precio: 20000,
                cantidad: 4
            }
        ],
        codigoCliente: '66a2c1517f3b340441ffdeb0',
        total: 80000,
        fecha: ISODate('2024-09-01T12:00:00.849Z'),
        codigoPasarela: 'CODIGO_PASARELA_2',
        pago: {
            codigo: '3c89c1e1-df94-4ebc-90a1-fb17ffea928c',
            fecha: ISODate('2024-09-01T12:00:00.849Z'),
            totalPagado: 80000,
            estado: 'PENDIENTE',
            metodoPago: 'TARJETA DE DÉBITO'
        },
        _class: 'co.edu.uniquindio.unieventos.modelo.Orden'
    },
    {
        _id: '66a2c6a55773597d73593ff2',
        detalle: [
            {
                codigoEvento: '66a2c476991cff088eb80b1b',
                nombreLocalidad: 'VIP',
                precio: 50000,
                cantidad: 3
            }
        ],
        codigoCliente: '66a2c14dd9219911cd34f2c1',
        total: 150000,
        fecha: ISODate('2024-09-15T18:45:00.849Z'),
        codigoPasarela: 'CODIGO_PASARELA_3',
        pago: {
            codigo: 'e8d1d7d5-5a52-4ed1-9277-8a4a8c81e917',
            fecha: ISODate('2024-09-15T18:45:00.849Z'),
            totalPagado: 150000,
            estado: 'APROBADA',
            metodoPago: 'TRANSFERENCIA BANCARIA'
        },
        _class: 'co.edu.uniquindio.unieventos.modelo.Orden'
    },
    {
        _id: '66a2c6a55773597d73593ff3',
        detalle: [
            {
                codigoEvento: '66a2c476991cff088eb80b2c',
                nombreLocalidad: 'GENERAL',
                precio: 20000,
                cantidad: 5
            },
            {
                codigoEvento: '66a2c476991cff088eb80b3d',
                nombreLocalidad: 'VIP',
                precio: 80000,
                cantidad: 1
            }
        ],
        codigoCliente: '66a2c14dd9219911cd34f2c2',
        total: 200000,
        fecha: ISODate('2024-09-25T10:00:00.849Z'),
        codigoPasarela: 'CODIGO_PASARELA_4',
        pago: {
            codigo: 'e239b8f8-3e7c-4bc8-bb51-59b897c85b0a',
            fecha: ISODate('2024-09-25T10:00:00.849Z'),
            totalPagado: 200000,
            estado: 'RECHAZADA',
            metodoPago: 'TARJETA DE CRÉDITO'
        },
        _class: 'co.edu.uniquindio.unieventos.modelo.Orden'
    }
]);

db.cupones.insertMany([
    {
        _id: '66a2c6a55773597d73593ff6',
        descuento: 20000,
        fechaVencimiento: ISODate('2024-10-15T23:59:59.000Z'),
        codigo: 'UNICO20',
        estado: 'ACTIVO',
        tipo: 'UNICO',
        nombre: 'Descuento del $20000 en una sola compra'
    },
    {
        _id: '66a2c6a55773597d73593ff7',
        descuento: 15000,
        fechaVencimiento: ISODate('2024-11-15T23:59:59.000Z'),
        codigo: 'MULTI15',
        estado: 'ACTIVO',
        tipo: 'MULTIPLE',
        nombre: 'Descuento del $15000 en compras de $50.000 o más'
    },
    {
        _id: '66a2c6a55773597d73593ff8',
        descuento: 50000,
        fechaVencimiento: ISODate('2024-10-30T23:59:59.000Z'),
        codigo: 'UNICO50',
        estado: 'ACTIVO',
        tipo: 'UNICO',
        nombre: 'Descuento de $50000 en tu primera compra'
    },
    {
        _id: '66a2c6a55773597d73593ff9',
        descuento: 5000,
        fechaVencimiento: ISODate('2024-12-01T23:59:59.000Z'),
        codigo: 'MULTI5',
        estado: 'ACTIVO',
        tipo: 'MULTIPLE',
        nombre: 'Descuento de $5000 en cada compra'
    },
    {
        _id: '66a2c6a55773597d73593ffa',
        descuento: 115250,
        fechaVencimiento: ISODate('2024-09-15T23:59:59.000Z'),
        codigo: 'UNICO15250',
        estado: 'ACTIVO',
        tipo: 'UNICO',
        nombre: 'Descuento de $15250 en productos seleccionados'
    }
]);

db.carritos.insertMany([
    {
        _id: 'carrito_001',
        fecha: ISODate('2024-10-10T15:30:00.000Z'),
        items: [
            {
                codigoEvento: '66a2c476991cff088eb80aaf',
                nombreLocalidad: 'VIP',
                precio: 80000,
                cantidad: 1
            },
            {
                codigoEvento: '66a2c476991cff088eb80b1b',
                nombreLocalidad: 'GENERAL',
                precio: 20000,
                cantidad: 3
            }
        ],
        idCuenta: '66a2a9aaa8620e3c1c5437be',
        transporte: {
            tipo: 'PRIVADO',
            costo: 70000,
            capacidad: 4
        },
        _class: 'co.edu.uniquindio.unieventos.modelo.Carrito'
    },
    {
        _id: 'carrito_002',
        fecha: ISODate('2024-10-11T10:00:00.000Z'),
        items: [
            {
                codigoEvento: '66a2c476991cff088eb80ac1',
                nombreLocalidad: 'VIP',
                precio: 90000,
                cantidad: 2
            }
        ],
        idCuenta: '66a2c14dd9219911cd34f2c0',
        transporte: {
            tipo: 'COMPARTIDO',
            costo: 20000,
            capacidad: 2
        },
        _class: 'co.edu.uniquindio.unieventos.modelo.Carrito'
    },
    {
        _id: 'carrito_003',
        fecha: ISODate('2024-10-12T14:30:00.000Z'),
        items: [
            {
                codigoEvento: '66a2c476991cff088eb80ad2',
                nombreLocalidad: 'GENERAL',
                precio: 30000,
                cantidad: 5
            }
        ],
        idCuenta: '66a2c1517f3b340441ffdeb0',
        transporte: {
            tipo: 'COMPARTIDO',
            costo: 110000,
            capacidad: 7
        },
        _class: 'co.edu.uniquindio.unieventos.modelo.Carrito'
    },
    {
        _id: 'carrito_004',
        fecha: ISODate('2024-10-13T09:00:00.000Z'),
        items: [
            {
                codigoEvento: '66a2c476991cff088eb80ae3',
                nombreLocalidad: 'VIP',
                precio: 85000,
                cantidad: 1
            },
            {
                codigoEvento: ObjectId('66a2c476991cff088eb80af4'),
                nombreLocalidad: 'GENERAL',
                precio: 25000,
                cantidad: 4
            }
        ],
        idCuenta: '66a2c14dd9219911cd34f2c1',
        transporte: {
            tipo: 'PRIVADO',
            costo: 150000,
            capacidad: 7
        },
        _class: 'co.edu.uniquindio.unieventos.modelo.Carrito'
    },
    {
        _id: 'carrito_005',
        fecha: ISODate('2024-10-14T16:45:00.000Z'),
        items: [
            {
                codigoEvento: '66a2c476991cff088eb80b05',
                nombreLocalidad: 'GENERAL',
                precio: 20000,
                cantidad: 3
            },
            {
                codigoEvento: '66a2c476991cff088eb80b16',
                nombreLocalidad: 'VIP',
                precio: 95000,
                cantidad: 2
            }
        ],
        idCuenta: '66a2c14dd9219911cd34f2c2',
        transporte: {
            tipo: 'PRIVADO',
            costo: 150000,
            capacidad: 7
        },
        _class: 'co.edu.uniquindio.unieventos.modelo.Carrito'
    }
]);
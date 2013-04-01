-- ======================================================================
-- ===   Sql Script for Database : Multipagos
-- ===
-- === Build : 2526
-- ======================================================================

CREATE SEQUENCE auth_user_usr_id_seq
    INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

-- ======================================================================

CREATE SEQUENCE auth_role_rol_id_seq
    INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

-- ======================================================================

CREATE SEQUENCE auth_resource_res_id_seq
    INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

-- ======================================================================

CREATE SEQUENCE banco_banco_id_seq
    INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

-- ======================================================================

CREATE SEQUENCE prueba_prueba_id_seq
    INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

-- ======================================================================

CREATE SEQUENCE cantidad_monedas_cantidad_id_seq
    INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

-- ======================================================================

CREATE SEQUENCE departamento_departamento_id_seq
    INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

-- ======================================================================

CREATE SEQUENCE simbolo_simbolo_id_seq
    INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

-- ======================================================================

CREATE SEQUENCE servicio_servicio_id_seq
    INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

-- ======================================================================

CREATE SEQUENCE localidad_localidad_id_seq
    INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

-- ======================================================================

CREATE SEQUENCE barrio_barrio_id_seq
    INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

-- ======================================================================

CREATE SEQUENCE estado_corte_estado_id_seq
    INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

-- ======================================================================

CREATE SEQUENCE colector_colector_id_seq
    INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

-- ======================================================================

CREATE SEQUENCE cartera_x_departamento_cartera_id_seq
    INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

-- ======================================================================

CREATE SEQUENCE visitas_visita_id_seq
    INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

-- ======================================================================

CREATE SEQUENCE pagos_pago_id_seq
    INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

-- ======================================================================

CREATE SEQUENCE arqueo_pagos_arqueo_id_seq
    INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

-- ======================================================================

CREATE SEQUENCE tmp_cartera_tmp_id_seq
    INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

-- ======================================================================

CREATE SEQUENCE logs_logs_id_seq
    INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

-- ======================================================================

CREATE TABLE auth_user
  (
    usr_id         int4          not null default nextval('auth_user_usr_id_seq'),
    usr_login      varchar(20)   unique not null,
    usr_full_name  varchar(50)   unique,
    usr_password   varchar(32),
    usr_enable     boolean       not null default 't',
    usr_email      varchar(80),
    usr_cargo      varchar(50)   not null,

    primary key(usr_id)
  );

-- ----------------------------------------------------------------------

-- Insertamos el usuario predefinido del sistema
INSERT INTO auth_user(usr_login,usr_full_name,usr_password,usr_enable,usr_cargo) VALUES ('admin','administrador','e10adc3949ba59abbe56e057f20f883e','t','Administrador Global del Sistema');
-- Insertamos el usuario desarrollo, el cual sera utilizado por el àrea de desarrollo
INSERT INTO auth_user(usr_login,usr_full_name,usr_password,usr_enable,usr_cargo) VALUES ('desarrollo','desarrollo','e10adc3949ba59abbe56e057f20f883e','t','Administrador utilizado por desarrollo');

-- ======================================================================

CREATE TABLE auth_role
  (
    rol_id      int4          not null default nextval('auth_role_rol_id_seq'),
    rol_name    varchar(24)   unique not null,
    rol_enable  boolean       default 't',

    primary key(rol_id)
  );

-- ----------------------------------------------------------------------

-- Insertamos el rol predeterminado del sistema
INSERT INTO auth_role(rol_name,rol_enable) VALUES ('admin','t');
-- Agregamos un rol que tendra todos los permisos que admin, pero
-- que puede ser eliminado luego, si es necesario.
INSERT INTO auth_role(rol_name,rol_enable) VALUES ('Acceso Total','t');
INSERT INTO auth_role(rol_name,rol_enable) VALUES ('Supervisor','t');
INSERT INTO auth_role(rol_name,rol_enable) VALUES ('Informes','t');

-- ======================================================================

CREATE TABLE auth_resource
  (
    res_id      int4           not null default nextval('auth_resource_res_id_seq'),
    res_key     varchar(200)   unique not null,
    res_name    varchar(200)   unique not null,
    res_enable  boolean        default 't',

    primary key(res_id)
  );

-- ----------------------------------------------------------------------

-- Creamos los recuros a los cuales puede acceder los usuarios del sistema mediante los roles.

-- Usuarios, Roles
INSERT INTO auth_resource(res_key,res_name,res_enable) values('auth','Usuarios','t');
-- Catalogos Generales
INSERT INTO auth_resource(res_key,res_name,res_enable) values('cata','Catálogos','t');
-- Informes
INSERT INTO auth_resource(res_key,res_name,res_enable) values('informes','Informes','t');
-- Arqueo
INSERT INTO auth_resource(res_key,res_name,res_enable) values('arqueo','Autorizar Arqueo','t');

-- ======================================================================

CREATE TABLE auth_user_role
  (
    rol_id  int4   not null,
    usr_id  int4   not null,

    primary key(rol_id,usr_id),

    foreign key(rol_id) references auth_role(rol_id) on update CASCADE,
    foreign key(usr_id) references auth_user(usr_id) on update CASCADE on delete CASCADE
  );

-- ----------------------------------------------------------------------

-- Asignamos el rol predeterminado al usuarios predeterminado
INSERT INTO auth_user_role VALUES(1,1);

-- ======================================================================

CREATE TABLE auth_permission
  (
    rol_id  int4   not null,
    res_id  int4   not null,

    primary key(rol_id,res_id),

    foreign key(rol_id) references auth_role(rol_id) on update CASCADE on delete CASCADE,
    foreign key(res_id) references auth_resource(res_id) on update CASCADE
  );

-- ----------------------------------------------------------------------

-- Asignamos los permisos a los roles predeterminados del sistema 
--
-- Asignamos al rol 'admin'
insert into auth_permission values(1,1);
insert into auth_permission values(1,2);
insert into auth_permission values(1,3);
insert into auth_permission values(1,4);
--
-- Asignamos al rol "Acceso Total"
insert into auth_permission values(2,1);
insert into auth_permission values(2,2);
insert into auth_permission values(2,3);
insert into auth_permission values(2,4);
--
-- Asignamos al rol "Catalogos Generales"
insert into auth_permission values(3,2);
--
-- Asignamos al rol "Informes"
insert into auth_permission values(4,3);
--

-- ======================================================================

CREATE TABLE estado_corte
  (
    estado_id      int4           not null default nextval('estado_corte_estado_id_seq'),
    estado_nombre  varchar(200)   not null,
    inactivo       boolean        not null default 'f',

    primary key(estado_id)
  );

-- ======================================================================

CREATE TABLE departamento
  (
    departamento_id      int4           not null default nextval('departamento_departamento_id_seq'),
    departamento_nombre  varchar(200)   not null,
    inactivo             boolean        not null default 'f',

    primary key(departamento_id)
  );

-- ======================================================================

CREATE TABLE localidad
  (
    localidad_id      int4           not null default nextval('localidad_localidad_id_seq'),
    departamento_id   int4           not null,
    localidad_nombre  varchar(200)   not null,
    inactivo          boolean        not null default 'f',

    primary key(localidad_id),

    foreign key(departamento_id) references departamento(departamento_id) on update CASCADE
  );

-- ======================================================================

CREATE TABLE barrio
  (
    barrio_id      int4           not null default nextval('barrio_barrio_id_seq'),
    localidad_id   int4           not null,
    barrio_nombre  varchar(200)   not null,
    inactivo       boolean        not null default 'f',

    primary key(barrio_id),

    foreign key(localidad_id) references localidad(localidad_id) on update CASCADE
  );

-- ======================================================================

CREATE TABLE simbolo
  (
    simbolo_id      int4           not null default nextval('simbolo_simbolo_id_seq'),
    simbolo_numero  int4           not null,
    simbolo_nombre  varchar(200)   not null,
    inactivo        boolean        not null default 'f',

    primary key(simbolo_id)
  );

-- ======================================================================

CREATE TABLE tasa_fija
  (
    tasa_fecha       date            not null,
    tasa_cambio_mes  numeric(10,4)   not null,

    primary key(tasa_fecha)
  );

-- ======================================================================

CREATE TABLE servicio
  (
    servicio_id      int4          not null default nextval('servicio_servicio_id_seq'),
    servicio_nombre  varchar(24)   not null,
    inactivo         boolean       default 'f',

    primary key(servicio_id)
  );

-- ======================================================================

CREATE TABLE banco
  (
    banco_id      int4          not null default nextval('banco_banco_id_seq'),
    banco_nombre  varchar(24)   not null,
    inactivo      boolean       not null default 'f',

    primary key(banco_id)
  );

-- ======================================================================

CREATE TABLE prueba
  (
    prueba_id       int4          not null default nextval('prueba_prueba_id_seq'),
    prueba_nombre1  varchar(24)   not null,
    prueba_nombre2  int4          not null,

    primary key(prueba_id)
  );

-- ======================================================================

CREATE TABLE cantidad_monedas
  (
    cantidad_id      int4          not null default nextval('cantidad_monedas_cantidad_id_seq'),
    cantidad_nombre  varchar(50)   not null,
    cantidad_valor   int4          not null,
    dolares          boolean       not null default 'f',

    primary key(cantidad_id)
  );

-- ======================================================================

CREATE TABLE colector
  (
    colector_id       int4          not null default nextval('colector_colector_id_seq'),
    colector_numero   int4          not null,
    primer_nombre     varchar(24)   not null,
    segundo_nombre    varchar(24),
    primer_apellido   varchar(24)   not null,
    segundo_apellido  varchar(24),
    cedula            varchar(20)   not null,
    inactivo          boolean       not null default 'f',

    primary key(colector_id)
  );

-- ======================================================================

CREATE TABLE cartera_x_departamento
  (
    cartera_id           int4            not null default nextval('cartera_x_departamento_cartera_id_seq'),
    contrato             varchar(10)     not null,
    suscriptor           varchar(40)     not null,
    nit                  varchar(16)     not null,
    direccion            varchar(200)    not null,
    barrio_id            int4            not null,
    factura_interna      varchar(10)     unique not null,
    numero_fiscal        varchar(10)     unique not null,
    ano                  varchar(4)      not null,
    mes                  varchar(2)      not null,
    saldo                numeric(10,2)   not null,
    estado_id            int4            not null,
    departamento_id      int4            not null,
    localidad_id         int4            not null,
    cupon                varchar(10)     not null,
    telefono             varchar(40)     not null,
    descuento            numeric(10,4),
    servicio_id          int4            not null,
    empleador            varchar(40),
    direccion_empleador  varchar(200),
    pagado               boolean         not null default 'f',
    fecha_pago           date,
    fecha_ingreso        date            not null,

    primary key(cartera_id),

    foreign key(barrio_id) references barrio(barrio_id) on update CASCADE,
    foreign key(estado_id) references estado_corte(estado_id) on update CASCADE,
    foreign key(departamento_id) references departamento(departamento_id) on update CASCADE,
    foreign key(localidad_id) references localidad(localidad_id) on update CASCADE,
    foreign key(servicio_id) references servicio(servicio_id) on update CASCADE
  );

-- ======================================================================

CREATE TABLE tmp_cartera
  (
    tmp_id               int4            not null default nextval('tmp_cartera_tmp_id_seq'),
    contrato             varchar(10)     not null,
    suscriptor           varchar(100)    not null,
    nit                  varchar(20)     not null,
    direccion            varchar(200)    not null,
    barrio               varchar(100)    not null,
    factura_interna      varchar(20)     not null,
    numero_fiscal        varchar(20)     not null,
    anio                 varchar(4)      not null,
    mes                  varchar(2)      not null,
    saldo                numeric(10,2)   not null,
    estado               varchar(50)     not null,
    departamento         varchar(50)     not null,
    localidad            varchar(50)     not null,
    cupon                varchar(20)     not null,
    telefono             varchar(30),
    descuento            varchar(20),
    servicio             varchar(20)     not null,
    empleador            varchar(10),
    direccion_empleador  varchar(200),
    f_asiganado          date,

    primary key(tmp_id)
  );

-- ======================================================================

CREATE TABLE visitas
  (
    visita_id         int4   not null default nextval('visitas_visita_id_seq'),
    usr_id            int4   not null,
    fecha             date   not null,
    cantidad_visitas  int4   not null,

    primary key(visita_id),

    foreign key(usr_id) references auth_user(usr_id) on update CASCADE
  );

-- ======================================================================

CREATE TABLE detalle_visitas
  (
    visita_id         int4           not null,
    cartera_id        int4           not null,
    localidad_id      int4           not null,
    simbolo_id        int4           not null,
    servicio_id       int4           not null,
    colector_id       int4           not null,
    numero_contrato   varchar(10)    not null,
    fecha_visita      date           not null,
    aviso_cobro       int4           not null,
    hora_registro     varchar(200)   not null,
    fprog_cobro       date,
    encontro_cliente  boolean        not null default 'f',

    primary key(visita_id,cartera_id,localidad_id,simbolo_id,servicio_id,colector_id),

    foreign key(visita_id) references visitas(visita_id) on update CASCADE on delete CASCADE,
    foreign key(cartera_id) references cartera_x_departamento(cartera_id) on update CASCADE,
    foreign key(localidad_id) references localidad(localidad_id) on update CASCADE,
    foreign key(simbolo_id) references simbolo(simbolo_id) on update CASCADE,
    foreign key(servicio_id) references servicio(servicio_id) on update CASCADE,
    foreign key(colector_id) references colector(colector_id) on update CASCADE
  );

-- ======================================================================

CREATE TABLE pagos
  (
    pago_id            int4            not null default nextval('pagos_pago_id_seq'),
    usr_id             int4            not null,
    fecha              date            not null,
    monto_total        numeric(10,2)   not null,
    monto_total_us     numeric(10,2),
    cantidad_pagos     int4            not null,
    cantidad_pagos_us  int4,

    primary key(pago_id),

    foreign key(usr_id) references auth_user(usr_id) on update CASCADE
  );

-- ======================================================================

CREATE TABLE detalle_pagos
  (
    pago_id          int4            not null,
    localidad_id     int4            not null,
    cartera_id       int4            not null,
    servicio_id      int4            not null,
    colector_id      int4            not null,
    numero_contrato  varchar(10)     not null,
    factura_interna  varchar(10)     unique not null,
    numero_fiscal    varchar(10)     unique not null,
    cupon            varchar(10)     not null,
    monto_pago       numeric(10,2)   not null,
    fecha_pago       date            not null,
    recibo           int4            not null,
    hora_registro    varchar(200)    not null,

    primary key(pago_id,localidad_id,cartera_id,servicio_id,colector_id),

    foreign key(pago_id) references pagos(pago_id) on update CASCADE on delete CASCADE,
    foreign key(localidad_id) references localidad(localidad_id) on update CASCADE,
    foreign key(cartera_id) references cartera_x_departamento(cartera_id) on update CASCADE,
    foreign key(servicio_id) references servicio(servicio_id) on update CASCADE,
    foreign key(colector_id) references colector(colector_id) on update CASCADE
  );

-- ======================================================================

CREATE TABLE arqueo_pagos
  (
    arqueo_id         int4            not null default nextval('arqueo_pagos_arqueo_id_seq'),
    usr_id            int4            not null,
    pago_fecha        date            not null,
    colector_id       int4            not null,
    total_cs          numeric(10,2),
    total_us          numeric(10,2),
    conversion_us     numeric(10,2),
    total_ck_cs       numeric(10,2),
    total_ck_us       numeric(10,2),
    conversion_ck_us  numeric(10,2),
    total_dp_cs       numeric(10,2),
    total_dp_us       numeric(10,2),
    conversion_dp_us  numeric(10,2),
    total_recibo      numeric(10,2),
    total_general     numeric(10,2),
    diferencia        numeric(10,2),

    primary key(arqueo_id),

    foreign key(usr_id) references auth_user(usr_id) on update CASCADE,
    foreign key(colector_id) references colector(colector_id) on update CASCADE
  );

-- ======================================================================

CREATE TABLE arqueo_cantidad
  (
    arqueo_id    int4            not null,
    cantidad_id  int4            not null,
    cantidad     int4            not null,
    total        numeric(10,2)   not null,

    primary key(arqueo_id,cantidad_id),

    foreign key(arqueo_id) references arqueo_pagos(arqueo_id) on update CASCADE on delete CASCADE,
    foreign key(cantidad_id) references cantidad_monedas(cantidad_id) on update CASCADE
  );

-- ======================================================================

CREATE TABLE arqueo_cantidad_us
  (
    arqueo_id    int4            not null,
    cantidad_id  int4            not null,
    cantidad     int4            not null,
    total        numeric(10,2)   not null,

    primary key(arqueo_id,cantidad_id),

    foreign key(arqueo_id) references arqueo_pagos(arqueo_id) on update CASCADE on delete CASCADE,
    foreign key(cantidad_id) references cantidad_monedas(cantidad_id) on update CASCADE
  );

-- ======================================================================

CREATE TABLE arqueo_cheque
  (
    arqueo_id     int4            not null,
    banco_id      int4            not null,
    n_cheque      int4            unique not null,
    beneficiario  varchar(24)     not null,
    monto_cs      numeric(10,2),
    monto_us      numeric(10,2),
    conversion    numeric(10,2),
    dolares       boolean         not null default 'f',

    primary key(arqueo_id,banco_id),

    foreign key(arqueo_id) references arqueo_pagos(arqueo_id) on update CASCADE on delete CASCADE,
    foreign key(banco_id) references banco(banco_id) on update CASCADE
  );

-- ======================================================================

CREATE TABLE arqueo_deposito
  (
    arqueo_id   int4            not null,
    cuenta_cs   int4            unique,
    cuenta_us   int4            unique,
    referencia  varchar(24),
    monto_cs    numeric(10,2),
    monto_us    numeric(10,2),
    conversion  numeric(10,2),
    dolares     boolean         not null default 'f',

    primary key(arqueo_id,cuenta_cs,cuenta_us),

    foreign key(arqueo_id) references arqueo_pagos(arqueo_id) on update CASCADE on delete CASCADE
  );

-- ======================================================================

CREATE TABLE tipo_documento_logs
  (
    tipod_log_id      int4          not null,
    tipod_log_nombre  varchar(24)   unique not null,

    primary key(tipod_log_id)
  );

-- ----------------------------------------------------------------------

-- Tipos de documentos que generan logs en el sistema
INSERT INTO tipo_documento_logs VALUES(1,'Departamento');
INSERT INTO tipo_documento_logs VALUES(2,'Localidad');
INSERT INTO tipo_documento_logs VALUES(3,'Barrio');
INSERT INTO tipo_documento_logs VALUES(4,'Tasa Fija');
INSERT INTO tipo_documento_logs VALUES(5,'Banco');
INSERT INTO tipo_documento_logs VALUES(6,'Servicio');
INSERT INTO tipo_documento_logs VALUES(7,'Simbolo');
INSERT INTO tipo_documento_logs VALUES(8,'Estado Corte');
INSERT INTO tipo_documento_logs VALUES(9,'Colector');
INSERT INTO tipo_documento_logs VALUES(10,'Cartera x Departamento');
INSERT INTO tipo_documento_logs VALUES(11,'Control Visitas');
INSERT INTO tipo_documento_logs VALUES(12,'Control Pagos');
INSERT INTO tipo_documento_logs VALUES(13,'Cantidad Monedas');
INSERT INTO tipo_documento_logs VALUES(14,'Arqueo Pagos');

-- ======================================================================

CREATE TABLE logs
  (
    logs_id           int4            not null default nextval('logs_logs_id_seq'),
    logs_referencia   varchar(50)     not null,
    logs_fecha        timestamp       not null,
    tipod_log_id      int4            not null,
    logs_descripcion  varchar(1000)   not null,

    primary key(logs_id),

    foreign key(tipod_log_id) references tipo_documento_logs(tipod_log_id) on update CASCADE
  );

-- ======================================================================


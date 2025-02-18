create function order_num() returns varchar(15)
as
'select '''' ||
        date_part(''year'', CURRENT_DATE) ||
        date_part(''month'', CURRENT_DATE) ||
        date_part(''day'', CURRENT_DATE) ||
        TRUNC(RANDOM() * 900) + 100'
    LANGUAGE "sql"
;

CREATE TABLE "order"
(
    id         uuid        NOT NULL        DEFAULT uuid_generate_v4() PRIMARY KEY,
    user_id    uuid        NOT NULL,
    created_at timestamp WITHOUT TIME ZONE default now(),
    updated_at timestamp WITHOUT TIME ZONE default now(),
    is_cancel  boolean     NOT NULL        default false,
    order_date date        NOT NULL        default CURRENT_DATE,
    number     varchar(15) NOT NULL        default order_num()
);

CREATE TABLE order_product
(
    id         uuid NOT NULL  default uuid_generate_v4() PRIMARY KEY,
    order_id   uuid NOT NULL,
    product_id uuid NOT NULL,
    count      numeric(15, 2) default 0,
    sum        numeric(15, 2) default 0
);

CREATE TABLE debt
(
    id         uuid           NOT NULL     DEFAULT uuid_generate_v4() PRIMARY KEY,
    created_at timestamp WITHOUT TIME ZONE default now(),
    order_id   uuid           NOT NULL,
    dt         numeric(15, 2) NOT NULL,
    kt         numeric(15, 2) NOT NULL
);

CREATE TABLE tariff
(
    id         uuid           NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    product_id uuid           NOT NULL,
    rate       numeric(15, 2) NOT NULL,
    price      numeric(15, 2) NOT NULL
);

create table condition
(
    id         uuid    NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    product_id uuid    NOT NULL,
    priority   integer NOT NULL default 100,
    -- Conditions
    count      numeric(15, 2),
    user_id    uuid,
    -- Value
    sum        numeric(15, 2)
);

create table product
(
    id         uuid         NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    is_service boolean      NOT NULL default true,
    name       varchar(255) not null default '(укажите наименование)'
);

create index order_user_id_index
    on "order" (user_id);

create index order_order_date_index
    on "order" (order_date);

create index order_number_index
    on "order" (number);

create index order_service_order_id_index
    on order_product (order_id);

create index debt_order_id_index
    on debt (order_id);

create index price_service_id_index
    on tariff (product_id);

create index condition_service_id_index
    on condition (product_id);

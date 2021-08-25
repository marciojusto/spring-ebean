create table address (
  id                            bigint auto_increment not null,
  address_line1                 varchar(255),
  address_line2                 varchar(255),
  city                          varchar(255),
  version                       bigint not null,
  created_on                    timestamp not null,
  modified_on                   timestamp not null,
  constraint pk_address primary key (id)
);

create table customer (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  identifier                    varchar(255),
  address_id                    bigint,
  version                       bigint not null,
  created_on                    timestamp not null,
  modified_on                   timestamp not null,
  constraint uq_customer_address_id unique (address_id),
  constraint pk_customer primary key (id)
);

alter table customer add constraint fk_customer_address_id foreign key (address_id) references address (id) on delete restrict on update restrict;


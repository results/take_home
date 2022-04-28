create table auth_tokens (id  bigserial not null, active boolean not null, created_at timestamp not null, expires_at DATETIME not null, token uuid not null, primary key (id));
create table balances (id  bigserial not null, active boolean not null, created_at timestamp not null, currency_code varchar(255) not null, current_balance numeric(19, 2) not null, due_date date, last_payment_date date, previous_balance numeric(19, 2), updated_at timestamp not null, primary key (id));
create table payment_adjustments (id  bigserial not null, active boolean not null, adjustment_percent numeric(19, 2), adjustment_threshold numeric(19, 2), created_at timestamp not null, description varchar(255), expiration_date date, updated_at timestamp not null, primary key (id));
create table payment_methods (id  bigserial not null, account_number varchar(255) not null, active boolean not null, created_at timestamp not null, expiration_date DATETIME not null, payment_type int4 not null, updated_at timestamp not null, user_id int8, primary key (id));
create table transactions (id  bigserial not null, adjustment_amount numeric(19, 2) not null, created_at timestamp not null, currency_code varchar(255) not null, ending_balance numeric(19, 2) not null, payment_method numeric(19, 2) not null, starting_balance numeric(19, 2) not null, transaction_amount numeric(19, 2) not null, user_id int8, primary key (id));
create table users (id  bigserial not null, active boolean not null, created_at timestamp not null, first_name varchar(255) not null, last_name varchar(255) not null, updated_at timestamp not null, balance_id int8 not null, session_token int8, primary key (id));
alter table users add constraint UK_73tbwkf7j66d5p82jxe16alaf unique (balance_id);
alter table payment_methods add constraint FKin7rtmim3ljrrhh5kxbq27s2v foreign key (user_id) references users;
alter table transactions add constraint FKqwv7rmvc8va8rep7piikrojds foreign key (user_id) references users;
alter table users add constraint FKftex59xddenm2vj4nf0rhqbko foreign key (balance_id) references balances;
alter table users add constraint FKkhg30haabmx8m2hbcmqeye756 foreign key (session_token) references auth_tokens;

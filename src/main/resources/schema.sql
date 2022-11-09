create table cryptocurrency (
id int not null primary key,
currency_code varchar(36) not null,
display_name  varchar(36) not null,
unscaled_value bigint not null,
scale int not null
);

create table countrylocale(
country_code varchar(36) not null primary key,
country varchar(255),
locale varchar(36) not null
);
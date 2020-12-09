-- gestione lato user

create table utente(
nome varchar2(30) not null,
cognome varchar2(30) not null,
indirizzo varchar2(100) not null,
cap char(5) not null,
nascita date not null,
username varchar2(10),
password varchar2(100) not null,
email varchar2(50) not null,
constraint p_username primary key(username));

create table articolo(
id_articolo int,
marca varchar2(30) not null,
modello varchar2(30) not null,
prezzo number(7,2) not null,
constraint p_idart primary key(id_articolo));

create table ordine(
id_ordine int,
totale number(8,2) not null,
data date not null,
username varchar2(10) not null,
constraint p_idord primary key(id_ordine),
constraint f_user foreign key(username) references utente(username));

create table ordine_articolo(
id_ordine int,
id_articolo int,
quantita int not null,
constraint f_idord foreign key(id_ordine) references ordine(id_ordine) on delete cascade,
constraint f_idart foreign key(id_articolo) references articolo(id_articolo) on delete cascade,
constraint p_oa primary key(id_ordine, id_articolo));

create table immagine (
id_img int primary key references articolo(id_articolo) on delete cascade,
url varchar2(200) not null,
descrizione varchar2(100));

-- gestione lato admin

create table amministratore(
username varchar2(10),
password varchar2(100) not null,
email varchar2(50) not null,
constraint p_adminuser primary key(username)
);

create or replace view report as
select utente.username, email, ordine.id_ordine, data, totale, sum(quantita) as quant
from utente, ordine, articolo, ordine_articolo
where ordine.id_ordine = ordine_articolo.id_ordine
and articolo.id_articolo = ordine_articolo.id_articolo 
and utente.username = ordine.username
group by utente.username, email, ordine.id_ordine, data, totale
order by utente.username, data, ordine.id_ordine;

-- sequenze

create sequence ordine_seq;

create sequence articolo_seq;
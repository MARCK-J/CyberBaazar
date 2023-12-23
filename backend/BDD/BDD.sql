-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2023-12-23 15:57:33.585

-- tables
-- Table: ChatgptQueries
CREATE TABLE ChatgptQueries (
    id_query int  NOT NULL,
    question text  NOT NULL,
    answer text  NOT NULL,
    query_date timestamp  NOT NULL,
    Prices_id_price int  NOT NULL,
    Users_id_user int  NOT NULL,
    CONSTRAINT ChatgptQueries_pk PRIMARY KEY (id_query)
);

-- Table: Country
CREATE TABLE Country (
    id_country int  NOT NULL,
    code varchar(5)  NOT NULL,
    name varchar(25)  NOT NULL,
    CONSTRAINT Country_pk PRIMARY KEY (id_country)
);

-- Table: DesiredProducts
CREATE TABLE DesiredProducts (
    id_dproduct int  NOT NULL,
    saved_date date  NOT NULL,
    status int  NOT NULL,
    users_id_user int  NOT NULL,
    CONSTRAINT DesiredProducts_pk PRIMARY KEY (id_dproduct)
);

-- Table: EStores
CREATE TABLE EStores (
    id_estores int  NOT NULL,
    name varchar(255)  NOT NULL,
    CONSTRAINT EStores_pk PRIMARY KEY (id_estores)
);

-- Table: Job
CREATE TABLE Job (
    id_job int  NOT NULL,
    job_id varchar(50)  NOT NULL,
    products_id_product int  NOT NULL,
    estores_id_estores int  NOT NULL,
    country_id_country int  NOT NULL,
    CONSTRAINT Job_pk PRIMARY KEY (id_job)
);

-- Table: Prices
CREATE TABLE Prices (
    id_price SERIAL PRIMARY KEY,
    image VARCHAR(100) NOT NULL,
    code VARCHAR(50) NOT NULL,
    name VARCHAR(1000) NOT NULL,
    url_price VARCHAR(1000) NOT NULL,
    date TIMESTAMP NOT NULL,
    job_id_job VARCHAR(100) NOT NULL
);


-- Table: Products
CREATE TABLE Products (
    id_product int  NOT NULL,
    name varchar(255)  NOT NULL,
    CONSTRAINT Products_pk PRIMARY KEY (id_product)
);

-- Table: Users
CREATE TABLE Users (
    id_user int  NOT NULL,
    name varchar(255)  NOT NULL,
    mail varchar(255)  NOT NULL,
    CONSTRAINT Users_pk PRIMARY KEY (id_user)
);

-- foreign keys
-- Reference: ChatgptQueries_Prices (table: ChatgptQueries)
ALTER TABLE ChatgptQueries ADD CONSTRAINT ChatgptQueries_Prices
    FOREIGN KEY (Prices_id_price)
    REFERENCES Prices (id_price)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: ChatgptQueries_Users (table: ChatgptQueries)
ALTER TABLE ChatgptQueries ADD CONSTRAINT ChatgptQueries_Users
    FOREIGN KEY (Users_id_user)
    REFERENCES Users (id_user)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Job_Country (table: Job)
ALTER TABLE Job ADD CONSTRAINT Job_Country
    FOREIGN KEY (country_id_country)
    REFERENCES Country (id_country)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Job_EStores (table: Job)
ALTER TABLE Job ADD CONSTRAINT Job_EStores
    FOREIGN KEY (estores_id_estores)
    REFERENCES EStores (id_estores)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Job_Products (table: Job)
ALTER TABLE Job ADD CONSTRAINT Job_Products
    FOREIGN KEY (products_id_product)
    REFERENCES Products (id_product)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Prices_DesiredProducts (table: Prices)
ALTER TABLE Prices ADD CONSTRAINT Prices_DesiredProducts
    FOREIGN KEY (desiredProducts_id_dproduct)
    REFERENCES DesiredProducts (id_dproduct)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Prices_Job (table: Prices)
ALTER TABLE Prices ADD CONSTRAINT Prices_Job
    FOREIGN KEY (job_id_job)
    REFERENCES Job (id_job)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: ProductosDeseados_Usuarios (table: DesiredProducts)
ALTER TABLE DesiredProducts ADD CONSTRAINT ProductosDeseados_Usuarios
    FOREIGN KEY (users_id_user)
    REFERENCES Users (id_user)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- End of file.
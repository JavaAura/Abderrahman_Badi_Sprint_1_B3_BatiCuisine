CREATE DATABASE BatiCuisine;

USE BatiCuisine;

CREATE TYPE component_type AS ENUM ('MATERIAL', 'WORKFORCE');
CREATE TYPE project_status AS ENUM ('ONGOING', 'FINISHED', 'CANCELED');

CREATE TABLE quote
(
  id SERIAL PRIMARY KEY,
	estimated_amount DOUBLE PRECISION NOT NULL,
	issue_date DATE NOT NULL,
	validity_date DATE NOT NULL,  
  isAccepter BOOLEAN DEFAULT FALSE
);

CREATE TABLE project
(
  id SERIAL PRIMARY KEY,
	project_name VARCHAR(255) NOT NULL,
	profit_margin DOUBLE PRECISION NULL,
	total_cost DOUBLE PRECISION NULL,
  project_status project_status,  
  quote_id BIGINT NOT NULL,
  CONSTRAINT fk_quote FOREIGN KEY (quote_id) REFERENCES quote(id)
);

CREATE TABLE component
(
  id SERIAL PRIMARY KEY,
  component_name VARCHAR(255) NOT NULL,
  component_type component_type,
  vat_rate DOUBLE PRECISION NULL,
  project_id BIGINT NOT NULL,
  CONSTRAINT fk_project FOREIGN KEY (project_id) REFERENCES project(id)
);

CREATE TABLE work_force
(
	hourly_rate DOUBLE PRECISION NOT NULL,
  work_hours DOUBLE PRECISION NOT NULL,
  worker_productivity DOUBLE PRECISION NOT NULL
) INHERITS (component) ;

CREATE TABLE material
(
  transport_cost DOUBLE PRECISION NOT NULL,
  quality_coefficient DOUBLE PRECISION NOT NULL,
  quantity DOUBLE PRECISION NOT NULL,
  unit_cost DOUBLE PRECISION NOT NULL
) INHERITS (component) ;

CREATE TABLE client
(
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL,
	phone_number VARCHAR(24) NOT NULL,
  is_professional BOOLEAN DEFAULT FALSE
);
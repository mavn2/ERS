CREATE TABLE employee_roles (
	id SERIAL PRIMARY KEY,
	role_name VARCHAR(50) NOT NULL
);

CREATE TABLE employees (
--Login, system info
	id SERIAL PRIMARY KEY,
	e_role int NOT NULL,
	e_name VARCHAR(50) NOT NULL,
	e_pass VARCHAR(20) NOT NULL,
--Personal info
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
--FK constraints
	CONSTRAINT fk_role FOREIGN KEY (e_role) REFERENCES employee_roles(id)
);

CREATE TABLE ri_statuses (
	id SERIAL PRIMARY KEY,
	status VARCHAR(50) NOT NULL
);

CREATE TABLE ri_requests (
	id SERIAL PRIMARY KEY,
	apply_id int NOT NULL,
	approve_id int,
	ri_status int NOT NULL,
	ri_amount NUMERIC CHECK (ri_amount > 0),
	ri_for VARCHAR(250),
	ri_date DATE NOT NULL DEFAULT CURRENT_DATE,
--IMG location or bytea goes here, when/if implemented
--FK constrainsts
CONSTRAINT fk_apply FOREIGN KEY (apply_id) REFERENCES employees(id),
CONSTRAINT fk_approve FOREIGN KEY (approve_id) REFERENCES employees(id)
);
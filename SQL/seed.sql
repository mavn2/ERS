INSERT INTO employee_roles (role_name) 
VALUES ('employee'), ('manager');

INSERT INTO employees (e_role, e_name, e_pass, first_name, last_name, email)
VALUES (1, 'jdoe', 'password', 'John', 'Doe', 'mvn2@uw.edu'),
	(1, 'jadoe', 'password', 'Jane', 'Doe', 'test@example.com'),
	(2, 'admin', 'password', 'Max', 'Manager', 'maxvnicolai@gmail.com');
	
INSERT INTO ri_statuses (status)
VALUES ('Pending'), ('Approved'), ('Denied')

INSERT INTO ri_requests (apply_id, ri_status, ri_amount, ri_for)
VALUES (1, 1, 100.00, 'travel'), (1, 1, 500.00, 'pizza'), (3, 1, 100.00, 'fraud');

INSERT INTO ri_requests (apply_id, approve_id, ri_status, ri_amount, ri_for)
VALUES (2, 3, 2, 10.00, 'taxi')
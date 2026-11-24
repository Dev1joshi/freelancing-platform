USE freelancing_platform;

SHOW TABLES;
USE freelancing_platform;

CREATE TABLE clients (
    client_id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(15),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE freelancers (
    freelancer_id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(15),
    experience_years INT DEFAULT 0,
    bio TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE skills (
    skill_id INT PRIMARY KEY AUTO_INCREMENT,
    skill_name VARCHAR(100) UNIQUE NOT NULL
);
CREATE TABLE freelancer_skills (
    freelancer_id INT,
    skill_id INT,
    PRIMARY KEY (freelancer_id, skill_id),
    FOREIGN KEY (freelancer_id) REFERENCES freelancers(freelancer_id),
    FOREIGN KEY (skill_id) REFERENCES skills(skill_id)
);
CREATE TABLE projects (
    project_id INT PRIMARY KEY AUTO_INCREMENT,
    client_id INT NOT NULL,
    title VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    budget DECIMAL(10,2) NOT NULL,
    deadline DATE,
    status ENUM('OPEN', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED')
        DEFAULT 'OPEN',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (client_id) REFERENCES clients(client_id)
);
CREATE TABLE proposals (
    proposal_id INT PRIMARY KEY AUTO_INCREMENT,
    project_id INT NOT NULL,
    freelancer_id INT NOT NULL,
    proposed_amount DECIMAL(10,2) NOT NULL,
    cover_letter TEXT,
    status ENUM('PENDING', 'ACCEPTED', 'REJECTED')
        DEFAULT 'PENDING',
    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (project_id) REFERENCES projects(project_id),
    FOREIGN KEY (freelancer_id) REFERENCES freelancers(freelancer_id),
    UNIQUE (project_id, freelancer_id)
);
CREATE TABLE contracts (
    contract_id INT PRIMARY KEY AUTO_INCREMENT,
    proposal_id INT NOT NULL UNIQUE,
    start_date DATE NOT NULL,
    end_date DATE,
    agreed_amount DECIMAL(10,2) NOT NULL,
    status ENUM('ACTIVE', 'COMPLETED', 'TERMINATED')
        DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (proposal_id) REFERENCES proposals(proposal_id)
);
CREATE TABLE payments (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    contract_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    payment_method VARCHAR(50),
    status ENUM('PENDING', 'COMPLETED', 'FAILED')
        DEFAULT 'PENDING',
    transaction_reference VARCHAR(100),
    FOREIGN KEY (contract_id) REFERENCES contracts(contract_id)
);
CREATE TABLE reviews (
    review_id INT PRIMARY KEY AUTO_INCREMENT,
    contract_id INT NOT NULL UNIQUE,
    client_id INT NOT NULL,
    freelancer_id INT NOT NULL,
    rating INT NOT NULL CHECK (rating BETWEEN 1 AND 5),
    review_comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (contract_id) REFERENCES contracts(contract_id),
    FOREIGN KEY (client_id) REFERENCES clients(client_id),
    FOREIGN KEY (freelancer_id) REFERENCES freelancers(freelancer_id)
);

INSERT INTO clients (full_name, email, password, phone)
VALUES
('Ishika Gupta', 'ishika@example.com', 'demo123', '9876543210'),
('Om Rajput', 'om@example.com', 'demo123', '9876543211'),
('Kaustubh', 'kaustubh@example.com', 'demo123', '9876543212');
SELECT * FROM clients;

INSERT INTO freelancers
(full_name, email, password, phone, experience_years, bio)
VALUES
('Arjun Mehta', 'arjun@example.com', 'demo123', '9876543220', 3,
 'Java backend developer with Spring Boot experience'),

('Neha Kapoor', 'neha@example.com', 'demo123', '9876543221', 2,
 'Frontend developer skilled in HTML, CSS and JavaScript'),

('Rohan Verma', 'rohan@example.com', 'demo123', '9876543222', 4,
 'Full stack developer with database management experience');
 SELECT * FROM freelancers;

 INSERT INTO skills (skill_name)
VALUES
('Java'),
('Spring Boot'),
('Python'),
('HTML'),
('CSS'),
('JavaScript'),
('React'),
('MySQL'),
('Machine Learning'),
('Data Analysis');
SELECT * FROM skills;

INSERT INTO freelancer_skills (freelancer_id, skill_id)
VALUES
(1, 1),
(1, 2),
(1, 8),
(2, 4),
(2, 5),
(2, 6),
(2, 7),
(3, 1),
(3, 3),
(3, 8),
(3, 9);
SELECT
    f.full_name,
    s.skill_name
FROM freelancer_skills fs
JOIN freelancers f ON fs.freelancer_id = f.freelancer_id
JOIN skills s ON fs.skill_id = s.skill_id;
INSERT INTO projects
(client_id, title, description, budget, deadline, status)
VALUES
(1, 'E-Commerce Website',
 'Develop a responsive e-commerce website with product management and payment simulation.',
 50000.00, '2026-12-30', 'OPEN'),

(2, 'Portfolio Website',
 'Build a professional portfolio website using HTML, CSS and JavaScript.',
 15000.00, '2026-12-15', 'OPEN'),

(3, 'Machine Learning Dashboard',
 'Develop a dashboard to display machine learning predictions and analytics.',
 35000.00, '2027-01-15', 'OPEN');
 SELECT * FROM projects;
 INSERT INTO proposals
(project_id, freelancer_id, proposed_amount, cover_letter, status)
VALUES
(1, 1, 45000.00, 'I have strong Java and Spring Boot experience and can build this platform.', 'PENDING'),

(1, 3, 47000.00, 'I have full-stack development and database management experience.', 'PENDING'),

(2, 2, 12000.00, 'I specialize in responsive frontend development using HTML, CSS and JavaScript.', 'PENDING'),

(3, 3, 32000.00, 'I have experience with Python and machine learning projects.', 'PENDING');
SELECT * FROM proposals;

UPDATE proposals
SET status = 'ACCEPTED'
WHERE proposal_id IN (1, 3);

SELECT proposal_id, project_id, freelancer_id, status
FROM proposals;
INSERT INTO contracts
(proposal_id, start_date, end_date, agreed_amount, status)
VALUES
(1, '2026-11-24', '2026-12-30', 45000.00, 'ACTIVE'),
(3, '2026-11-24', '2026-12-15', 12000.00, 'ACTIVE');
SELECT * FROM contracts;

INSERT INTO payments
(contract_id, amount, payment_method, status, transaction_reference)
VALUES
(1, 45000.00, 'Bank Transfer', 'COMPLETED', 'TXN10001'),
(2, 12000.00, 'UPI', 'PENDING', 'TXN10002');
SELECT * FROM payments;

UPDATE contracts
SET status = 'COMPLETED'
WHERE contract_id = 2;

INSERT INTO reviews
(contract_id, client_id, freelancer_id, rating, review_comment)
VALUES
(2, 2, 2, 5, 'Excellent frontend work. Delivered a responsive and professional website.');
SELECT * FROM reviews;
SELECT
    c.full_name AS client_name,
    p.title AS project_title,
    f.full_name AS freelancer_name,
    pr.proposed_amount,
    pr.status AS proposal_status,
    ct.agreed_amount,
    ct.status AS contract_status
FROM proposals pr
JOIN projects p ON pr.project_id = p.project_id
JOIN clients c ON p.client_id = c.client_id
JOIN freelancers f ON pr.freelancer_id = f.freelancer_id
LEFT JOIN contracts ct ON pr.proposal_id = ct.proposal_id;

SELECT * FROM projects
ORDER BY project_id DESC;
SELECT * FROM PROPOSALS;
show tables;

select * from freelancers;

select * from clients;
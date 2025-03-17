-- Insert Users
INSERT IGNORE INTO users (username, password, email, bio, github_url)
VALUES
    ('adnan', '$2a$12$ZM5blvwwWfukl5bEjxRgmejmqkk9DREfyLGtCGTPNwSw2yBpmTx7y', 'adnan@example.com', 'Software Engineer', 'https://github.com/adnan'),
    ('zeshan', '$2a$12$mYyLormHjOzQw2t/wuE5/uDMBu8jQSR3vM7LBihaaxaam.RZIvZPi', 'zeshan@example.com', 'Backend Developer', 'https://github.com/zeshan'),
    ('kamran', '$2a$12$RVTamjwXmlTp9hlduZJwM..u..KC65Ovqk/rAa/5nOnJ90no4rbti', 'kamran@example.com', 'Fullstack Developer', 'https://github.com/kamran'),
    ('noor', '$2a$12$HQpV5K2z5fRE35Lvk95Q4uWAcOKCYVxDVULbZ.WBzCsDYYB2n5QKK', 'noor@example.com', 'UI/UX Designer', 'https://github.com/noor');


-- Insert Authorities (Roles)
INSERT INTO authority (user_id, authority)
VALUES
    (1, 'ROLE_PROJECT_ADMIN'),
    (2, 'ROLE_PROJECT_ADMIN'),
    (3, 'ROLE_TASK_ASSIGNEE'),
    (4, 'ROLE_TASK_ASSIGNEE');

-- Insert Projects
INSERT INTO projects (name, description, owner_id)
VALUES
    ('Project Alpha', 'A high-performance project.', 1),
    ('Project Beta', 'A machine learning project.', 2);

-- Insert Tasks
INSERT INTO tasks (title, description, status, project_id, assigned_to)
VALUES
    ('Design Database', 'Create the database schema.', 'IN_PROGRESS', 1, 1),
    ('Develop API', 'Build REST API for backend.', 'PENDING', 1, 2),
    ('UI Enhancement', 'Improve the UI design.', 'COMPLETED', 2, 3);

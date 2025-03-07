-- Insert Users
INSERT INTO users (user_name, password, email, bio, github_url)
VALUES
    ('adnan', '{bcrypt}$2a$12$ZM5blvwwWfukl5bEjxRgmejmqkk9DREfyLGtCGTPNwSw2yBpmTx7y', 'adnan@example.com', 'Software Engineer', 'https://github.com/adnan'),
    ('zeshan', '{bcrypt}$2a$12$mYyLormHjOzQw2t/wuE5/uDMBu8jQSR3vM7LBihaaxaam.RZIvZPi', 'zeshan@example.com', 'Backend Developer', 'https://github.com/zeshan'),
    ('kamran', '{bcrypt}$2a$12$RVTamjwXmlTp9hlduZJwM..u..KC65Ovqk/rAa/5nOnJ90no4rbti', 'kamran@example.com', 'Fullstack Developer', 'https://github.com/kamran'),
    ('noor', '{bcrypt}$2a$12$HQpV5K2z5fRE35Lvk95Q4uWAcOKCYVxDVULbZ.WBzCsDYYB2n5QKK', 'noor@example.com', 'UI/UX Designer', 'https://github.com/noor');

-- Insert Roles (Authority)
INSERT INTO authority (username, authority)
VALUES
    (1, 'ROLE_PROJECT_OWNER'),
    (2, 'ROLE_PROJECT_OWNER'),
    (3, 'ROLE_TASK_OWNER'),
    (4, 'ROLE_TASK_OWNER');

-- Insert Projects
INSERT INTO projects (name, description, owner_id)
VALUES
    ('Project Alpha', 'A high-performance project.', 1),
    ('Project Beta', 'A machine learning project.', 2);

-- Insert Tasks
INSERT INTO tasks (title, description, status, project_id)
VALUES
    ('Design Database', 'Create the database schema.', 'IN_PROGRESS', 1),
    ('Develop API', 'Build REST API for backend.', 'PENDING', 1),
    ('UI Enhancement', 'Improve the UI design.', 'COMPLETED', 2);

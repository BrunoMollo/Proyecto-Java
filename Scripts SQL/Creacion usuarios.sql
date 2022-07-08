create user 'test'@'%' identified by 'testing';
GRANT SELECT, INSERT, UPDATE, DELETE ON farmacia.* TO 'test'@'%';
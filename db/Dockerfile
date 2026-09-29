FROM mysql/mysql-server:latest

COPY world.sql /docker-entrypoint-initdb.d/

ENV MYSQL_ROOT_PASSWORD=rootpassword
ENV MYSQL_DATABASE=world
ENV MYSQL_ROOT_HOST=%
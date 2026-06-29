FROM mysql:8.0

ENV MYSQL_ROOT_PASSWORD=admin
ENV MYSQL_DATABASE=padron
ENV MYSQL_USER=admin
ENV MYSQL_PASSWORD=admin

COPY dump.sql /docker-entrypoint-initdb.d/
version: "3.0"

services:
  master:
    image: ravitandon2/master-300-prod:0.1
    ports:
      - "80:80"
    links:
      - autocomplete
      - indexer
      - usermanagement
    logging:
      driver: awslogs
      options:
        awslogs-group: decover-logs
        awslogs-region: us-east-1
        awslogs-stream-prefix: master-300

  db:
    image: postgres:14.1-alpine
    environment:
      - POSTGRES_USER=postgres
      - POSTGRES_PASSWORD=postgres
    ports:
      - '5432:5432'
    volumes:
      - db-data:/var/lib/postgresql/data
      - ./init.sql:/docker-entrypoint-initdb.d/init.sql

volumes:
  db-data:

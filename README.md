# PetStore Application

## Deploy Application with Docker-Compose

### Starting local PostgreSQL, Grafana and Prometheus

1) Open project folder and navigate to `deploy` directory (`cd deploy`)
2) Run `docker-compose up -d`
3) Open http://localhost:3000/ and use admin:admin credentials
4) Navigate into http://localhost:3000/dashboards
5) Open Quarkus Microprofile Metrics dashboard

### Following Containers will be deployed.

1) Application with Native Binary on http://localhost:8080
2) PostgreSQL server on http://localhost:5432
3) Prometheus for scraping metrics on http://localhost:9090
4) Grafana Dashboards for Quarkus on http://localhost:3000

## Curl commands

##### Commands for Pet Service

1) Get all pets

   `curl --location --request GET 'http://localhost:8080/v1/pets'`


2) Add a new Pet

   `curl --location --request POST 'http://localhost:8080/v1/pets/addnewpet' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "petType":"Dog",
   "petName":"Scooby",
   "petAge" : 1
   }'`

3) Search Pet by ID

   `curl --location --request GET 'http://localhost:8080/v1/pets/1'`

4) Delete a Pet

   `curl --location --request DELETE 'http://localhost:8080/v1/pets/delete/3'`

5) Update Pet Details

    `curl --location --request PUT 'http://localhost:8080/v1/pets/update' \
      --header 'Content-Type: application/json' \
      --data-raw '{
      "id":1,
      "petType":"Pig",
      "petName":"Piggy",
      "petAge" : 5
      }'`
6) Search by pet name

   `curl --location --request GET 'http://localhost:8080/v1/pets/search/name/Piggy'`

7) Search by pet age 

   `curl --location --request GET 'http://localhost:8080/v1/pets/search/age/3'`


##### Commands for PetType Service

1) Get all pet types

   `curl --location --request GET 'http://localhost:8080/v1/pettypes'`
   
2) Add a new Pet Type "Pig"

   `curl --location --request POST 'http://localhost:8080/v1/pettypes/addpettype' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "petType":"Pig"
   }'`

3) Delete a Pet Type(type id=2)

   `curl --location --request DELETE 'http://localhost:8080/v1/pettypes/delete/1'`

4) Search Pet Type by ID

   `curl --location --request GET 'http://localhost:8080/v1/pettypes/1'`

5) Update Pet Type Details

   `curl --location --request PUT 'http://localhost:8080/v1/pettypes/update' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "id": 1,
   "petType":"Horse"
   }'`


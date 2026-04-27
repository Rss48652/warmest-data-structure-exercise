# Warmest Data Structure - Home Assignment

This repository contains a high-performance, distributed implementation of the `WarmestDataStructure` as required for the Java Developer position.

## Technical Highlights
- **O(1) Complexity:** All operations (`put`, `get`, `remove`, `getWarmest`) are implemented with a strict time complexity of **O(1)**.
- **Technology Stack:** Built with **Java 21**, **Spring Boot 3.x**, and **Redis**.
- **Distributed Architecture:** The solution leverages Redis to ensure data synchronization across multiple application instances.
- **Containerization:** Fully dockerized using Docker Compose for seamless environment setup.

## Design Decisions & Architecture
To achieve O(1) complexity while supporting a distributed environment, I chose **Redis** as the primary data store:
- **Redis Hash:** Used for storing key-value pairs to ensure constant-time retrieval.
- **Redis Sorted Set (ZSET):** Used to track the "warmest" keys. Every interaction updates the element's score, ensuring O(1) retrieval for the warmest key.

## Getting Started

### Prerequisites
- Docker & Docker Compose installed.

### Running the Project
To launch the application and the Redis database, run:
```bash
docker-compose up -d

The API will be accessible at http://localhost:8080.

API Documentation
The service exposes the following REST endpoints:

PUT /api/put?key={key}&value={value} - Store or update a value.

GET /api/get?key={key} - Retrieve a value by key.

DELETE /api/remove?key={key} - Remove a key from the system.

GET /api/warmest - Retrieve the current "warmest" key.

Verification & Testing
The project includes a JUnit test suite covering all 21 test cases.
To run tests:
./mvnw test

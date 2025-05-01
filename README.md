# 🧪 Apache Kafka Example

This project demonstrates a basic Kafka setup using Docker, along with sample Producer and Consumer apps built with Java (Spring Boot).

---

## 🔧 Setup (Docker)

1. **Create a file `docker-compose.yml`** with:

    ```yaml
    version: '3'
    services:
      zookeeper:
        image: bitnami/zookeeper:latest
        ports:
          - "2181:2181"
        environment:
          - ALLOW_ANONYMOUS_LOGIN=yes

      kafka:
        image: bitnami/kafka:latest
        ports:
          - "9092:9092"
        environment:
          - KAFKA_BROKER_ID=1
          - KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper:2181
          - KAFKA_CFG_LISTENERS=PLAINTEXT://:9092
          - KAFKA_CFG_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092
          - ALLOW_PLAINTEXT_LISTENER=yes
    ```

2. **Start services**:

    ```bash
    docker-compose up -d
    ```

3. **(Optional) Create topic**:

    ```bash
    docker exec -it kafka kafka-topics.sh \
      --create --topic test-topic \
      --bootstrap-server localhost:9092 \
      --partitions 1 --replication-factor 1
    ```

---

## ⚙️ Sample Kafka Config (application.properties)

```properties
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=test-group
spring.kafka.topic.name=test-topic


## 🚀 Run Apps

**Build and run the Producer:**

```bash
cd producer
./mvnw spring-boot:run


cd consumer
./mvnw spring-boot:run

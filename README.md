# Courier Manager App


1. **Clone the repository:**

    ```bash
    git clone https://github.com/buketsenturk/repoCourierManager.git
    ```

2. **Navigate to the project directory:**

    ```bash
    cd CourierManager
    ```

3. **Build the application using Maven:**

    ```bash
    mvn clean package
    ```

4. **Run the application:**

    ```bash
    java -jar target/CourierManager-0.0.1-SNAPSHOT.jar
    ```

## Usage
**FOR COURIERS**
- **Receive courier location:**

    ```http
    POST http://localhost:8080/couriers/location
    Content-Type: application/json

    {
        "id": "123",
        "lat": 41.0066751,
        "lng": 28.6552262
    }

    ```

- **Get total travel distance for a courier:**

    ```http
    GET http://localhost:8080/couriers/123/distance
    ```

**FOR STORES**
- **Get all stores:**
    ```http
    GET http://localhost:8080/stores
   ```

## Testing

To run tests for the application, execute the following command:

```bash
mvn test
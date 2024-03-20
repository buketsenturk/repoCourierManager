# Courier Manager App


1. **Clone the repository:**

    ```bash
    git clone https://github.com/your-username/courier-manager.git
    ```

2. **Navigate to the project directory:**

    ```bash
    cd courier-manager
    ```

3. **Build the application using Maven:**

    ```bash
    mvn clean install
    ```

4. **Run the application:**

    ```bash
    java -jar target/courier-manager.jar
    ```

## Usage
- **Receive courier location:**

    ```http
    POST http://localhost:8080/couriers/location
    Content-Type: application/json

    {
        "id": "courier-1",
        "lat": 123.456,
        "lng": 789.012
    }
    ```

- **Get total travel distance for a courier:**

    ```http
    GET http://localhost:8080/couriers/courier-1/distance
    ```

## Testing

To run tests for the application, execute the following command:

```bash
mvn test
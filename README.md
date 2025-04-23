# Raw Java HTTP Server

A lightweight, multithreaded HTTP server built using raw Java sockets. This server supports multiple endpoints and correctly serves both HTML and JSON content. It is designed with modularity in mind, using command and router patterns.

---

## 📅 Decagon curriculum

- ✅ Decagon curriculum Week 5 Complete:  THREADS AND CONCURRENCY

---

## 🛠️ Features
- Serve HTML content at `/`
- Serve JSON content at `/json`
- Reads content from file system
- Proper HTTP response formatting with headers
- Multithreading for concurrent client handling
- Command and Router pattern for endpoint logic
- TDD-driven with unit test coverage

---

## 📄 How It Works

### Core Flow
1. **Main.java** starts a server socket and listens on a port.
2. For each client connection, it creates a new `ClientHandler` thread.
3. `ClientHandler` reads the HTTP request and determines the path.
4. The path is passed to the `Router` which returns the corresponding `Command`.
5. The command executes and returns a formatted HTTP response.
6. The response is sent back to the client.

---

## 💻 Endpoints

### `/`
- **Method**: GET
- **Returns**: HTML page (e.g., index.html)
- **Content-Type**: `text/html`

### `/json`
- **Method**: GET
- **Returns**: JSON object
- **Content-Type**: `application/json`

---

## 🔧 Build & Run

```bash
# Compile
javac -d out src/main/java/org/deodev/webserver/**/*.java

# Run
java -cp out org.deodev.webserver.Main
```

---

## 🧬 Testing
Run tests with:
```bash
mvn test
```
---



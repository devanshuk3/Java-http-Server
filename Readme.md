# Java HTTP Server

A simple HTTP server built from scratch in Java using only core Java libraries.

The goal of this project is to understand how web servers work internally by implementing the core components manually instead of relying on existing frameworks or server implementations.

## Current Scope

This project is being developed incrementally, starting from raw TCP socket communication and gradually adding HTTP server functionality.

Planned features include:

* TCP socket server
* HTTP request parsing
* HTTP response generation
* Routing system
* Query parameter handling
* POST request support
* Middleware support
* Static file serving
* Thread pool based concurrency
* Error handling
* JSON responses

## Project Structure

```text
src/
├── http/
├── middleware/
├── routing/
├── server/
├── util/
└── Main.java
```

## Purpose

This project is primarily a learning exercise focused on networking, concurrency, protocol design, and backend architecture. The objective is to gain a deeper understanding of how HTTP servers and web frameworks operate under the hood.

## License

This project is open for learning, experimentation, and improvement.

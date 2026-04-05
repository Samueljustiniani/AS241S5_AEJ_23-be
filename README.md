# Spring WebFlux + Postgre (SQL)

## APIs IA utilizadas y características

1. **Text-to-Speech (OpenAI, RapidAPI)**
	- Convierte texto en audio (voz sintética).
	- Permite enviar texto y recibir un archivo de audio generado por IA.
	- Endpoint: `/api/tts`

2. **Llama 3.3 70b Chat (OpenAI, RapidAPI)**
	- Chatbot IA que responde preguntas o genera texto a partir de mensajes.
	- Permite enviar mensajes y recibir respuestas generadas por IA.
	- Endpoint: `/api/chat`

## Herramientas y versiones utilizadas

- Java 17
- Spring Boot 3.3.3
- Spring WebFlux
- Spring Data R2DBC
- PostgreSQL (Neon)
- RapidAPI
- OpenAI APIs

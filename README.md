# Proyecto AS241S5_AEJ_23-be

Este proyecto es una API reactiva construida con Spring WebFlux que utiliza dos servicios de IA (Llama para chat y Text-to-Speech) y una base de datos PostgreSQL en la nube (Neon).

## Requisitos de Despliegue en Kubernetes

Los manifiestos se encuentran en la carpeta `manifest-samuel-justiniani/`.

### Comandos de Despliegue

Sigue este orden para desplegar los recursos:

1. **Crear el Namespace:**
   ```bash
   kubectl apply -f manifest-samuel-justiniani/samuel-justiniani-23-namespace.yml
   ```

2. **Crear el Secret:**
   ```bash
   kubectl apply -f manifest-samuel-justiniani/samuel-justiniani-23-secret.yml
   ```

3. **Crear el Service:**
   ```bash
   kubectl apply -f manifest-samuel-justiniani/samuel-justiniani-23-sercive.yml
   ```

4. **Crear el Deployment:**
   ```bash
   kubectl apply -f manifest-samuel-justiniani/samuel-justiniani-23-deployment.yml
   ```

### Endpoints para Pruebas

Una vez desplegado, puedes obtener la IP del servicio con:
`kubectl get service -n samuel-justiniani-23-namespace`

Los endpoints disponibles son:

#### 1. Chat AI (Llama)
- **URL:** `http://<EXTERNAL-IP>:9090/api/chat`
- **Método:** `POST`
- **Cuerpo (JSON):**
  ```json
  {
    "message": "Hola, ¿cómo estás?"
  }
  ```

#### 2. Text to Speech (IA)
- **URL:** `http://<EXTERNAL-IP>:9090/api/tts`
- **Método:** `POST`
- **Cuerpo (JSON):**
  ```json
  {
    "text": "Hola, este es un mensaje de prueba."
  }
  ```

## Ejecución con Docker (Local)

Para correr el proyecto localmente sin Kubernetes, puedes usar Docker o Docker Compose.

### Opción A: Docker Compose (Recomendado)

He configurado un archivo `docker-compose.yml` que ya contiene todas las variables de entorno necesarias.

1. **Construir y levantar:**
   ```bash
   docker-compose up -d --build
   ```
2. **Ver logs:**
   ```bash
   docker-compose logs -f
   ```
3. **Detener:**
   ```bash
   docker-compose down
   ```

### Opción B: Docker CLI

1. **Construir la imagen:**
   ```bash
   docker build -t samuel/as241s5_aej_23-be:1.0 .
   ```

2. **Correr el contenedor (PowerShell):**
   ```powershell
   docker run -d --name as241s5_aej_23-be -p 9090:9090 `
     -e SPRING_R2DBC_URL="r2dbc:postgresql://neondb_owner:npg_4xVtfQK3SPvG@ep-raspy-wildflower-ads5jde6.c-2.us-east-1.aws.neon.tech/samuel_justiniani_22?sslmode=require&channel_binding=require" `
     -e SPRING_R2DBC_USERNAME="neondb_owner" `
     -e SPRING_R2DBC_PASSWORD="npg_4xVtfQK3SPvG" `
     -e IA_LLAMA_URL="https://open-ai21.p.rapidapi.com/conversationllama" `
     -e IA_LLAMA_KEY="ec30cf5d92msh7b1da619ec8415ep19562ejsn9e89a9fc3eab" `
     -e IA_TTS_URL="https://open-ai-text-to-speech1.p.rapidapi.com/" `
     -e IA_TTS_KEY="ec30cf5d92msh7b1da619ec8415ep19562ejsn9e89a9fc3eab" `
     samuel/as241s5_aej_23-be:1.0
   ```

## Docker Hub (Subida)

Para subir tu imagen a Docker Hub para que Kubernetes pueda jalarla:

```bash
docker tag samuel/as241s5_aej_23-be:1.0 <TU_USUARIO_DOCKERHUB>/as241s5_aej_23-be:1.0
docker push <TU_USUARIO_DOCKERHUB>/as241s5_aej_23-be:1.0
```

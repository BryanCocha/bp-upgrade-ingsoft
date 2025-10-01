# Etapa 1: build Angular
FROM node:18 AS builder
WORKDIR /app

# Copiamos primero las dependencias
COPY package*.json ./
RUN npm install

# Copiamos el resto del proyecto
COPY . .

# Generamos la carpeta dist/
RUN npm run build -- --configuration production

# Etapa 2: servir con Nginx
FROM nginx:alpine
# 👇 Este nombre debe coincidir con el que aparece en angular.json
COPY --from=builder /app/dist/front /usr/share/nginx/html

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]

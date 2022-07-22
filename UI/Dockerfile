# Use the node image to install node modules

FROM node:latest as node
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build --prod

# Use the node image to install node modules

FROM nginx:alpine
COPY --from=node /app/dist/ui /usr/share/nginx/html


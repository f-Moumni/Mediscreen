### STAGE 1: Build ###
##FROM node:alpine3.15 AS build
##RUN mkdir -p /app
##WORKDIR /app
##COPY package.json /app/
##RUN npm install
##COPY . /app/
##RUN npm run build --prod
### STAGE 2: Run ###
##FROM nginx:alpine

##COPY --from=build /app/dist/ui /usr/share/nginx/html
FROM node:14.17
ARG WORK_DIR=/fontend
ENV PATH ${WORK_DIR}/node_modules/.bin:$PATH
RUN mkdir ${WORK_DIR}
WORKDIR ${WORK_DIR}

COPY package.json ${WORK_DIR}
COPY package-lock.json ${WORK_DIR}

RUN npm install @angular/cli
RUN npm install

COPY . ${WORK_DIR}
EXPOSE 4200
CMD ng serve --host 0.0.0.0

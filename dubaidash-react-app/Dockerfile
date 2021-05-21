# pull official base image
FROM node:14.16.1-alpine
# set working directory
WORKDIR /dubaidash-react-app
# add `/dubaidash-react-app/node_modules/.bin` to $PATH
ENV PATH /dubaidash-react-app/node_modules/.bin:$PATH
# install dubaidash-react-app dependencies
COPY package.json ./
COPY package-lock.json ./
RUN npm install --silent
RUN npm install react-scripts@4.0.3 -g --silent
RUN npm install axios@0.21.1
RUN npm install bootstrap@4.6.0 --save

# add dubaidash-react-app
COPY . ./
# start dubaidash-react-app
CMD ["npm", "start"]
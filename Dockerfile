# Use a base image
FROM node:18

# Set working directory
WORKDIR /app

# Copy files
COPY package*.json ./
RUN npm install --legacy-peer-deps
COPY . .

# Expose the app port
EXPOSE 3000

# Start the app
CMD ["npm", "start"]
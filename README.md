# Income Calculator

A SPA application for calculating income for a contractor. Web part in AngularJS communicating REST backend realized in Spring framework.

## Structure

The project is separated into two modules: frontend and backend. Modules live in their respective folders. 

## Runing backend 
You need to go to the backend folder [backend\income-calculator-backend] and run it with maven:

```
cd backend\income-calculator-backend
```

```
mvn spring-boot:run
```

## Runing frontend
You need to go to the frontend folder [frontend\income-calculator-frontend] and run it with `npm`, the [node package manager][npm]:

```
cd frontend\income-calculator-frontend
```

In order to fetch all dependencies call:  

```
npm install
```

In order to start the web server with application deployed call:

```
npm start
```

Now browse to the app at `http://localhost:8000/income-calculator/index.html`.

#Running unit tests

* For backend call: 

```
mvn clean test
```

* For frontend call: 

```
npm test
```



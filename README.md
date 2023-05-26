# Energy Consumption Monitoring GraphQL API

This GraphQL API connects with IoT devices and smart meters to monitor energy consumption in heavy industries. Users can retrieve energy usage data, identify patterns, and implement energy-saving strategies to reduce costs and improve efficiency.

## 1) Define the GraphQL Schema:

* Create a schema file that defines the data types, queries, mutations, and subscriptions for your energy consumption monitoring API.
* Define types such as Meter, Measurement, UsagePattern, etc., along with their respective fields.

Actual GraphQL Schema used in examples (below):

```
  type Meter {
    id: ID!
    name: String!
    location: String!
    created: String!
    updated: String!
    readings: [Measurement!]!
  }

  type Measurement {
    id: ID! 
    meter_id: String!
    value: String!
    timestamp: String!
    meter: Meter!
  }

  type Query {
    getMeterById(id: ID!): Meter
    getAllMeters: [Meter]
    getMeasurementById(id: ID!): Measurement
    getAllMeasurements: [Measurement!]!
  }
```
Note: The structure of the type `Meter` and `Measurement` should mimic the structure of the database collections. In this case, the collections are `meters` and `measurements`, respectively. The `readings` field in the `Meter` type is an array of `Measurement` objects. The `meter` field in the `Measurement` type is a `Meter` object. Both are used only for querying purposes. As you can see, you can have nested objects in GraphQL that not necessarily are part of the database schema. For detailed information about the GraphQL Schema Definition Language (SDL), check out the [official documentation](https://graphql.org/learn/schema/).

## 2) Set up the GraphQL Server:

* Choose a GraphQL server implementation, such as Apollo Server or GraphQL Yoga, and set up the server environment.
* Implement the resolvers for the defined types and fields, which will handle the data retrieval and manipulation logic.

## 3) Connect to IoT Devices and Smart Meters:

* Integrate with the IoT devices and smart meters deployed in heavy industries.
* Utilize appropriate protocols and APIs to establish communication with the devices and retrieve energy consumption data.

## 4) Implement Query Resolvers:

* Create query resolvers to fetch data from the IoT devices and smart meters.
* Implement queries such as getMeterById, getMeasurementByMeterId, getUsagePatternsByMeterId, etc., to retrieve energy consumption data.

## 5) Data Analysis and Patterns:

* Implement data analysis algorithms to identify usage patterns and trends based on the collected energy consumption data.
* Calculate metrics such as average energy consumption, peak usage periods, energy efficiency, etc.

## 6) Mutations for Energy-Saving Strategies:

* Implement mutations to allow users to modify settings or implement energy-saving strategies.
* For example, implement mutations like setThresholdAlerts, schedulePower Optimization, etc., to set threshold alerts or schedule power optimization tasks.

## 7) Real-Time Updates with Subscriptions:

* Implement GraphQL subscriptions to provide real-time updates for energy consumption data.
* Users can subscribe to specific meters or measurement streams and receive live updates when new consumption data is available.

## 8) Authentication and Authorization:

* Implement authentication and authorization mechanisms to secure the API and ensure that only authorized users can access and modify the energy consumption data.
* Use technologies such as JWT (JSON Web Tokens) or OAuth for authentication and define user roles and permissions for authorization.

## 9) Documentation and Testing:

* Generate documentation for your GraphQL API using tools like GraphQL Playground or GraphQL Voyager.
* Write unit tests and integration tests to ensure the functionality and reliability of your API.

## 10) Frontend Integration:

* Build a frontend application (web or mobile) that consumes your GraphQL API.
* Use popular frontend frameworks like React, Angular, or Vue.js to create a user-friendly interface for visualizing energy consumption data, displaying usage patterns, and implementing energy-saving strategies.

## 11) GraphQL Playground:

11.1) getMeterById<br>
By passing the meter `id` as an argument, the query returns the meter details, including the meter `id`, `name`, `location`, `created`, `updated`.

Query:

```
  query {
    getMeterById(id:"1") {
      id
      name
      location
      created
      updated
    }
  }
```
Response:

```
  {
    "data": {
      "getMeterById": {
        "id": "1",
        "name": "Evenrude ACD-38",
        "location": "Sector 7G",
        "created": "2022-01-15T00:00:00Z",
        "updated": "2022-03-06T00:00:00Z"
      }
    }
  }
```

If readings are needed. It's a matter of adding the `readings` field to the query.

Query:

```
  query {
    getMeterById(id:"1") {
      id
      name
      location
      created
      updated
      readings {
        id
        value
        timestamp
      }
    }
  }
```
Response:

```
  {
    "data": {
      "getMeterById": {
        "id": "1",
        "name": "Evenrude ACD-38",
        "location": "Sector 7G",
        "created": "2022-01-15T00:00:00Z",
        "updated": "2022-03-06T00:00:00Z",
        "readings": [
          {
            "id": "1",
            "value": "10.5 C",
            "timestamp": "2022-03-06T00:00:00Z"
          },
          {
            "id": "2",
            "value": "11.8 C",
            "timestamp": "2021-07-02T00:00:00Z"
          },
          {
            "id": "3",
            "value": "7.4 C",
            "timestamp": "2022-07-28T00:00:00Z"
          },
          {
            "id": "4",
            "value": "10.5 C",
            "timestamp": "2022-03-06T00:00:00Z"
          }
        ]
      }
    }
  }
```

11.2) getAllMeters<br>
Returns an array of meters. Each meter object contains the meter `id`, `name`, `location`, `created`, `updated`. Note: If you want a list of readings associated with the meter, just send the `readings` field in the query.

Query:

```
  query {
    getAllMeters {
      id
      name
      location
      created
      updated
    }
  }
```
Response:

```
  {
    "data": {
      "getAllMeters": [
        {
          "id": "1",
          "name": "Evenrude ACD-38",
          "location": "Sector 7G",
          "created": "2022-01-15T00:00:00Z",
          "updated": "2022-03-06T00:00:00Z"
        },
        {
          "id": "2",
          "name": "Mercury JFH-34",
          "location": "Sector 1K",
          "created": "2021-07-01T00:00:00Z",
          "updated": "2021-07-02T00:00:00Z"
        },
        {
          "id": "3",
          "name": "Jhonson TT64",
          "location": "Sector 3B",
          "created": "2022-05-03T00:00:00Z",
          "updated": "2022-07-28T00:00:00Z"
        }
      ]
    }
  }
```

11.3) getMeasurementById<br>
By passing the measurement `id` as an argument, the query returns the measurement details, including the measurement `id`, meter `id`, `value` and `timestamp` of the reading.

Query:

```
  query {
    getMeasurementById(id:"3") {
      id
      value
      timestamp
    }
  }
```
Response:

```
  {
    "data": {
      "getMeasurementById": {
        "id": "3",
        "value": "7.4 C",
        "timestamp": "2022-07-28T00:00:00Z"
      }
    }
  }
```
If you want to get the meter details as well, it's a matter of adding the `meter` field to the query.

Query: 

```
  query {
    getMeasurementById(id:"3") {
      id
      value
      timestamp
      meter {
        id
        name
        location
        created
        updated
      }
    }
  }
```
Response:
  
```
  {
    "data": {
      "getMeasurementById": {
        "id": "3",
        "value": "7.4 C",
        "timestamp": "2022-07-28T00:00:00Z",
        "meter": {
          "id": "1",
          "name": "Evenrude ACD-38",
          "location": "Sector 7G",
          "created": "2022-01-15T00:00:00Z",
          "updated": "2022-03-06T00:00:00Z"
        }
      }
    }
  }
```

11.2) getAllMeasurements<br>
Returns an array of measurements. Each measurement object contains the `id`, `meter`, `value` and `timestamp` of the reading. Note: Here `meter` field is sent in order to get the meter details as well.

Query:

```
  query {
    getAllMeasurements {
      id
      value
      timestamp
      meter {
        id 
        name
        location
        created
        updated
      }
    }
  }
```
Response:

```
  {
    "data": {
      "getAllMeasurements": [
        {
          "id": "1",
          "value": "10.5 C",
          "timestamp": "2022-03-06T00:00:00Z",
          "meter": {
            "id": "1",
            "name": "Evenrude ACD-38",
            "location": "Sector 7G",
            "created": "2022-01-15T00:00:00Z",
            "updated": "2022-03-06T00:00:00Z"
          }
        },
        {
          "id": "2",
          "value": "11.8 C",
          "timestamp": "2021-07-02T00:00:00Z",
          "meter": {
            "id": "1",
            "name": "Evenrude ACD-38",
            "location": "Sector 7G",
            "created": "2022-01-15T00:00:00Z",
            "updated": "2022-03-06T00:00:00Z"
          }
        },
        ...
      ]
    }
  }
```
## Disclaimer

This project is for demonstration purposes only. However, it is completely functional and follows the best practices for building a GraphQL API under Java. 

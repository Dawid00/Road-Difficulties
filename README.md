# Traffic Difficulties in Poland

This is a small api which informs about current traffic difficulties in Poland. It takes data from :
https://www.archiwum.gddkia.gov.pl/dane/zima_html/utrdane.xml

I used
Java and Spring Boot to create application.
## API Reference

#### Get all current traffic difficulties

```http
  GET api/v1/traffic-difficulties
```

#### Get all current traffic difficulties in chosen voivodeship

```http
  GET api/v1/traffic-difficulties/voivodeship/{voivodeship}
```

except voivodeship: warminsko_mazurskie , kujawsko_pomorskie
#### Get all current traffic difficulties in road using roadName 
```http
  GET api/v1/traffic-difficulties/road/{roadName}
```
#### Get all current traffic difficulties by location and distance
```http
  GET api/v1/traffic-difficulties/map
```
| Parameter   | Type     | Description                                               |
|:------------|:---------|:----------------------------------------------------------|
| `latitude`  | `double` | **Required**.  Geographic coordinate                      |
| `longitude` | `double` | **Required**. Geographic coordinate                       |
| `distance`  | `double`  | **Required**. Max distance from location in kilometers (That is, the shortest distance over the earth’s surface) |

Example Request
```http
GET http://localhost:9000/api/v1/traffic-difficulties/voivodeship/lubelskie
```
Example Response
```json
{
  "date": "2022-04-11T14:55:00",
  "trafficDifficulties": [
    {
      "difficultyType": "W",
      "road": "S12s",
      "voivodeship": "lubelskie",
      "km": 31.7,
      "dl": 0.05,
      "location": {
        "geoLat": 0.0,
        "geoLong": 0.0
      },
      "name": "wezeł Kurów Wschód - wezeł Nałęczów",
      "startDate": "2022-04-11T13:00:00",
      "finishDate": "2022-04-11T16:00:00",
      "roundAbout": "zdarzenie na jezdni do Lublina",
      "type": {
        "poz": "R07"
      },
      "effects": "Zablokowany pas ruchu",
      "liftingCapacityLimit": null,
      "pressureCapacityLimit": null,
      "widthLimit": 0.0,
      "speedLimit": 0,
      "alternatingTraffic": false,
      "trafficLights": false,
      "bridgeFailure": false,
      "twoWay": false,
      "closedRoad": false,
      "latency": {
        "latencyTime": {
          "direction": "O",
          "timeFrom": "00:00",
          "timeBy": "00:00",
          "fromMondayToFriday": "00:00",
          "saturday": "00:00",
          "sunday": "00:00"
        }
      },
      "horizontalExtremeLimit": null,
      "verticalExtremeLimit": null
    },
    {
      "difficultyType": "U",
      "road": "S12s",
      "voivodeship": "lubelskie",
      "km": 33.0,
      "dl": 13.0,
      "location": {
        "geoLat": 0.0,
        "geoLong": 0.0
      },
      "name": "węzeł Jastków - wezel Nałęczów",
      "startDate": "2022-04-11T14:00:00",
      "finishDate": "2022-04-11T18:00:00",
      "roundAbout": "prace na jezdni do Warszawy",
      "type": {
        "poz": "U33"
      },
      "effects": null,
      "liftingCapacityLimit": null,
      "pressureCapacityLimit": null,
      "widthLimit": 0.0,
      "speedLimit": 90,
      "alternatingTraffic": false,
      "trafficLights": false,
      "bridgeFailure": false,
      "twoWay": false,
      "closedRoad": false,
      "latency": {
        "latencyTime": {
          "direction": "O",
          "timeFrom": "00:00",
          "timeBy": "00:00",
          "fromMondayToFriday": "00:00",
          "saturday": "00:00",
          "sunday": "00:00"
        }
      },
      "horizontalExtremeLimit": null,
      "verticalExtremeLimit": null
    }
  ]
}
```
Example Request
```http 
GET http://localhost:9090/api/v1/traffic-difficulties/road/a2
```
Example Response
```json
{
  "date": "2022-04-11T14:55:00",
  "trafficDifficulties": [
    {
      "difficultyType": "U",
      "road": "A2",
      "voivodeship": "łódzkie",
      "km": 347.0,
      "dl": 8.0,
      "location": {
        "geoLat": 51.918243,
        "geoLong": 19.404897
      },
      "name": "w. Emilia - w. Stryków",
      "startDate": "2022-04-08T08:55:00",
      "finishDate": "2022-04-30T08:00:00",
      "roundAbout": "Remont nawierzchni na jezdni południowej (z Poznania w kierunku Warszawy). Ruch odbywa się jedną jezdnią (północną) w obu kierunkach po jednym pasie ruchu. Zamknięty wjazd na A2 w kierunku Warszawy na węźle Zgierz. Zamknięty zjazd z Poznania na weźle Zgierz.",
      "type": {
        "poz": "U33"
      },
      "effects": null,
      "liftingCapacityLimit": null,
      "pressureCapacityLimit": null,
      "widthLimit": 0.0,
      "speedLimit": 0,
      "alternatingTraffic": false,
      "trafficLights": false,
      "bridgeFailure": false,
      "twoWay": false,
      "closedRoad": false,
      "latency": {
        "latencyTime": {
          "direction": "O",
          "timeFrom": "00:00",
          "timeBy": "00:00",
          "fromMondayToFriday": "00:00",
          "saturday": "00:00",
          "sunday": "00:00"
        }
      },
      "horizontalExtremeLimit": null,
      "verticalExtremeLimit": null
    },
    {
      "difficultyType": "U",
      "road": "A2",
      "voivodeship": "łódzkie",
      "km": 351.1,
      "dl": 4.0,
      "location": {
        "geoLat": 51.906355,
        "geoLong": 19.459354
      },
      "name": "w. Stryków - w. Zgierz",
      "startDate": "2022-03-30T08:42:00",
      "finishDate": "2022-04-30T12:00:00",
      "roundAbout": "Remont nawierzchni na jezdni południowej (z Poznania w kierunku Warszawy). Ruch odbywa się jedną jezdnią (północną) w obu kierunkach po jednym pasie ruchu",
      "type": {
        "poz": "U33"
      },
      "effects": null,
      "liftingCapacityLimit": null,
      "pressureCapacityLimit": null,
      "widthLimit": 0.0,
      "speedLimit": 0,
      "alternatingTraffic": false,
      "trafficLights": false,
      "bridgeFailure": false,
      "twoWay": true,
      "closedRoad": false,
      "latency": {
        "latencyTime": {
          "direction": "O",
          "timeFrom": "00:00",
          "timeBy": "00:00",
          "fromMondayToFriday": "00:00",
          "saturday": "00:00",
          "sunday": "00:00"
        }
      },
      "horizontalExtremeLimit": null,
      "verticalExtremeLimit": null
    }
  ]
}
```
Example Request
```http 
GET http://localhost:9090/api/v1/traffic-difficulties/map?latitude=51.246910&longitude=22.573620&distance=150
```
Example Response
```json
{
  "date": "2022-04-11T15:15:00",
  "trafficDifficulties": [
    {
      "difficultyType": "U",
      "road": "17",
      "voivodeship": "lubelskie",
      "km": 179.122,
      "dl": 5.603,
      "location": {
        "geoLat": 50.705103,
        "geoLong": 23.286328
      },
      "name": "Zamość - Tomaszów Lubelski (m. Jatutów)",
      "startDate": "2021-07-01T08:00:00",
      "finishDate": "2023-06-15T18:00:00",
      "roundAbout": "Rozbudowa DK 17 na odc. granica m. Zamość - Łabunie. Pierwszy etap prac obejmuje: roboty ziemne, roboty rozbiórkowe oraz związane z wycinką drzew. W ramach prac mogą wystąpić utrudnienia związane z wprowadzeniem ruchu wahadłowego kierowanego ręcznie",
      "type": {
        "poz": "U33"
      },
      "effects": null,
      "liftingCapacityLimit": null,
      "pressureCapacityLimit": null,
      "widthLimit": 0.0,
      "speedLimit": 40,
      "alternatingTraffic": true,
      "trafficLights": true,
      "bridgeFailure": false,
      "twoWay": false,
      "closedRoad": false,
      "latency": {
        "latencyTime": {
          "direction": "O",
          "timeFrom": "00:00",
          "timeBy": "00:00",
          "fromMondayToFriday": "00:00",
          "saturday": "00:00",
          "sunday": "00:00"
        }
      },
      "horizontalExtremeLimit": null,
      "verticalExtremeLimit": null
    }]
}
```

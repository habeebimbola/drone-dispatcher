
### Design Decisions:
* Existing 10 Drones Are Pre-Populated In The In-Memory Database H2 While Program Is Loading.
* Medications Are Created Via An API Handler. Medications Are Pre-Populated.
* Login Credentials For The In-Memory Database Can Be Found In The application.properties file
* Medications Images Are Represented As URLs To A Cloud Service Available Publicly.
* 
### From Project Base Directory:
 * run ./gradlew clean
 *    ./gradlew bootRun to run project without Deployment
 *    ./gradlew build to build executable JAR file

### From Project Base Directory, cd build/libs directory: {project-name}.jar file is here
run java -jar {project-name}.jar to run as executable 

Server Running At URL : http://localhost:8000

Check localhost:8000/h2-console from Browser Window to view in-memory Database table.

### Drone Dispatch Service API Endpoints For Musala Task

* drone-dispatch-service/register                  
# Description: Handler To Register A New Drone
# HTTP Method POST  Body Parameter : Application/JSON
* Sample Request:
#{
#    "serialNo":"DroneThird3",
#    "droneModel": "CruiserWeight",
#    "weightLimit":120,
#    "batteryCapacity":57,
#    "droneState":"LOADING"
#}
* Sample Response
* # {
 # "message": "DroneThird3 Successfully Registered.",
 # "code": "SUCCESS"
 # }


*drone-dispatch-service/drone/load/{serialNo}
* Description: Load Drone With Medications: HTTP Method Type POST  Body Parameter: Application/JSON
* Sample Request:
#
# [
#    {"name":"Medik55", "weight":13.0, "code":"medik55"},
#    {"name":"Panadol", "weight":43.3, "code":"Pan42"}
# ]
#
* Sample Response 
#  {
#   "message": "Successfully Loaded Drone :DRNMUS001A With Medications.",
#   "code": "SUCCESS"
#  }
#

* drone-dispatch-service/drone/medications/{serialNo}  HTTP Method POST Content-Type : Application/JSON
* Sample Request :
# {
# "serialNo":"DRNMUS001A",
# "droneModel": "LightWeight",
# "weightLimit":300,
# "batteryCapacity":20,
# "droneState":"LOADING"
# }
* Sample Response
#{}

* drone-dispatch-service/drone/available HTTP Method GET Content-Type : Application/JSON
* Description: Retrieve Available Drones For Loading(IDLE) Drones: No Body Parameters Required 
* Sample Response
# [{
#        "medications": [],
#        "serialNo": "DRNMUS008H",
#        "droneModel": "MiddleWeight",
#        "weightLimit": 239,
#        "batteryCapacity": 159.0,
#        "droneState": "IDLE"
#    },
#    {
#        "medications": [],
#        "serialNo": "DRNMUS009I",
#        "droneModel": "CruiserWeight",
#        "weightLimit": 407,
#        "batteryCapacity": 287.0,
#        "droneState": "IDLE"
#    },
#    {
#        "medications": [],
#        "serialNo": "DRNMUS0010J",
#        "droneModel": "MiddleWeight",
#        "weightLimit": 259,
#        "batteryCapacity": 163.0,
#        "droneState": "IDLE"
#    }]



* drone-dispatch-service/drone/{serialNo}/battery-level
* Description: API Handler To Retrieve Drone Battery Level HTTP Method GET
* Sample Response :
#{
#  "code": "SUCCESS",
#  "batteryLevel": 40.0,
#  "serialNo": "DRNMUS001A"
#  }
#

### Medication Creation Service API Endpoint
* medication/create
# Description : API Handler To Creation A Medication Object
# HTTP Method POST Body Parameter : Application/JSON
* Sample Request:
* '{"name":"PanadolExtra", "code":"DRGMUS14F", "imageUrl":"https://4.imimg.com/data4/AQ/WT/MY-7047793/dostinex-250x250.jpg","weight":34}'
* Sample Response:
* HTTP/1.1 201
# Location: http://localhost:8000/medication/create/DRGMUS14F
#  Content-Length: 0
# Date: Mon, 01 May 2023 17:52:58 GMT

# Get Available Medications API Handler
* Sample Request : HTTP Method GET, Content-Type:application/json URL: /medication/medications
* 
* medication/medications/
* Sample Response
# HTTP/1.1 202
#  Content-Type: application/json
#  Transfer-Encoding: chunked
#  Date: Mon, 01 May 2023 17:58:32 GMT

# [{"name":"Tabloid Thioguanine","weight":23.0,"code":"DRG001","
# imageUrl":"https://bitcoindrugsmart.com/wp-content/uploads/TAMODEX-20-1024x691.jpg"},{"name":"Tamoxifen Citrate","
# weight":12.0,"code":"DRG003","
# imageUrl":"https://bitcoindrugsmart.com/wp-content/uploads/TAMODEX-20-1024x691.jpg"},{"name":"Tapinarof Cream Vtama","
# weight":6.0,"code":"DRG004","
# imageUrl":"https://www.cliniexpert.com/attachment/20220610/853ab06cee0a495aa32240e50a781519.jpg"},{"name":"Caffeine
# Citrate Cafcit","weight":28.0,"code":"DRG005","
# imageUrl":"https://www.alphapharmacy.com.ng/uploads/1p7ra0ehw460bn.jpeg"},{"name":"Calcitriol Rocaltrol","weight":8.0,"
# code":"DRG006","
# imageUrl":"https://cdn.shop-apotheke.com/images/896x0/rocaltrol-0-25-ug-weichkapseln-D02259914-p10.jpg"},{"name":"Calcium
# Carbonate","weight":31.0,"code":"DRG007","
# imageUrl":"https://www.byclue.com/wp-content/uploads/2019/06/4-228.jpg"},{"name":"Calfactant Infasurf","weight":54.0,"
# code":"DRG008","imageUrl":"https://www.cityoverseasltd.com/upload/service/1633872580.jpg"},{"name":"Canasa Mesalamine","
# weight":14.0,"code":"DRG010","imageUrl":"https://www.canasa.com/CanasaSavingsCard"},{"name":"ACEPHEN Acetaminophen
# Suppositories","weight":61.0,"code":"DRG011","
# imageUrl":"https://www.shutterstock.com/image-photo/medical-vial-acetaminophen-pills-orange-plastic-2132495399"}]

# AppDirect
AppDirect Challenge

1) Update the following oauth properties present in src/main/resources/application.properties with OAuth consumer key and password.
    oauth_consumer_key=<OAuth consumer key>
    oauth_consumer_secret=<OAuth password>
    
2) Navigate into the root directory i.e /AppDirect, and build the project using gradle. The gradle command for building is as follows:
    $gradle clean build 

3) This creates a .jar file in /AppDirect/build/libs folder. When this .jar file is deployed all the endpoints become available. The command to deploy the jar is as follows.
    $java -jar /build/libs/appdirect-0.0.1-SNAPSHOT.jar &

4) The endpoints available are as follows:
  Assign User (/appdirect/assign)
  Unassign User (/appdirect/unassign)
  Create Subscription (/appdirect/create)
  Cancel Subscription (/appdirect/cancel)
  Change Subscription (/appdirect/change)


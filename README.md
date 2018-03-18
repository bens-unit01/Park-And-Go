# Park-And-Go
Client-server app to pay a parking place through an android device

« Park & Go » is a java client-server application. Using « Park & Go », the user can pay his parking place, for a period of time, through his android device. The user can also extend the parking period time, if needed, remotely and therefore, avoiding to go back to his parking place to do it.

The client application is an android application using UI components such as the TimePicker to select the parking period , the ProgressBar to show the remaining parking time. When selecting the parking time, the user will see dynamically the transaction details like the cost, the start and end time.

The server application is a java desktop application. This application provides a TCP-IP server to intercept the android clients requests and handles them through an « interpreter » module. We have basically three kinds of requests, « sign in », « paying a parking place » and « extending a parking place ». The server application provides also some administration
features like : real-time monitoring, transactions and users management.

You can find the source code of this application at : Park & Go github repository 


![Alt text]( /screenshots/pres-001.PNG?raw=true "Cours")
![Alt text]( /screenshots/client-login.PNG?raw=true "Cours")
![Alt text]( /screenshots/client-paiement.PNG?raw=true "Cours")
![Alt text]( /screenshots/client-prolonger.PNG?raw=true "Cours")
![Alt text]( /screenshots/serveur-gestion.PNG?raw=true "Cours")
![Alt text]( /screenshots/serveur-monitoring.PNG?raw=true "Cours")
![Alt text]( /screenshots/serveur-monitoring2.PNG?raw=true "Cours")
![Alt text]( /screenshots/serveur-transactions.PNG?raw=true "Cours")



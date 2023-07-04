# Exhibition Management System

## About the application
This repository contains the source code for the Exhibition Management System, a web application developed using Java and the Spring Boot framework. 
The system aims to facilitate the management and organization of exhibitions, including ticket sales, reservation handling and visitor monitoring.

Please note that the current implementation of this application GUI <b>focuses mainly on the reservation process</b>. 
While the code includes associations, attributes, and most of the methods for other functionalities, 
they are not yet fully implemented or accessible through the GUI.

## Used tools and technologies
- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- Thymeleaf - HTML, CSS
- Spring Security
- RESTful API
- Docker
- MySQL Database
- JavaDoc

## About the System
The system enables the management and organization of exhibitions, as well as monitoring the flow of customers and facilitates the business aspects such as ticket sales and reservation handling.

## Objective
The objective is to support the exhibition company by improving the processes of sales, reservations, monitoring visitor flow and generating reports.

## System Users
- Employees
  - Cashier
  - Exhibition Manager
  - Conservator
- Customers
  - Clients (Visitors)
 
## System diagrams
### Class diagram
![class diagram](/sample-images/cd_exhibition_management_system.svg)

### Activity diagram
![activity diagram](/sample-images/ad_online_reservation.svg)

### Interaction (Sequence) diagram
![sequence diagram](/sample-images/sd_online_reservation.svg)

### Online reservation use case script

| Use Case Name                           | Make Online Exhibition Reservation                          |
| --------------------------------------- | ---------------------------------------------------------- |
| Description                             | This use case involves making a reservation for an exhibition using a website. While browsing the exhibitions, user can display artworks for selected exhibition . |
| Actors                                  | Client (Visitor)                                           |
| Preconditions                          | The client is on the website.                              |
| Main Flow of Events                     | 1. The client displays the list of available exhibitions.   |
|                                         | 2. The client selects the desired exhibition.               |
|                                         | 3. The system allows the user to view the objects in the selected exhibition (this is not done by default). |
|                                         | 4. The client proceeds to the initial reservation actions.  |
|                                         | 5. The client selects the number of seats to reserve.       |
|                                         | 6. The client selects the desired reservation date.         |
|                                         | 7. The system checks if there are available seats for the chosen date. |
|                                         | 8. The system allows the selection of the guide's language. |
|                                         | 9. The system asks the client if they have an account and the client logs in. |
|                                         | 10. The client enters/edits their information.              |
|                                         | 11. The client confirms the reservation.                    |
|                                         | 12. The system displays a confirmation of the successful reservation. |
| Alternative Flows                       | 3a. The client displays the objects presented in the selected exhibition. |
|                                         | 7a.1. The system displays information about the lack of available seats. |
|                                         | 7a.2.a. Return to step 5.                                   |
|                                         | 7a.2.b. End the flow.                                       |
|                                         | 8a. The client selects the language.                        |
|                                         | 9a. The client creates an account.                          |
|                                         | 9b. The client continues without logging in or creating a new account. |
| Postconditions                          | Success: The reservation is successfully created.           |
|                                         | Failure: The reservation is not created.                    |

## Example preview
![exhibition list](/sample-images/exhibition_list.png)
![exhibition_details](/sample-images/exhibition_details.png)
![exhibition artworks](/sample-images/exhibition_artworks.png)
![reservation](/sample-images/pre_reservation.png)
![not available](/sample-images/reservation_not_available.png)
![account options](/sample-images/account_options.png)
![login](/sample-images/login.png)
![sign up](/sample-images/signup.png)
![confirm screen](/sample-images/confirm_reservation.png)
![confirmation](/sample-images/reservation_confirmation.png)

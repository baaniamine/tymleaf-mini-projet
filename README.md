# 🚗 Gestion d'une Agence de Location de Voitures

Application web Spring Boot pour gérer la flotte de véhicules, les clients et les contrats de location d'une agence.

---

## 📋 Fonctionnalités

- CRUD complet : Voitures, Clients, Locations
- Calcul automatique du montant total lors de l'enregistrement d'une location
- Mise à jour du statut d'une location (en cours, terminée, annulée)
- Marquage automatique de la disponibilité des voitures
- Filtrage des voitures par segment et disponibilité
- Filtrage des locations par statut et par période
- Dashboard statistiques : taux d'occupation et revenus par marque

---

## 🗂️ Structure du projet

```
location-voitures/
├── .mvn/
│   └── wrapper/
│       └── maven-wrapper.properties
├── src/
│   └── main/
│       ├── java/
│       │   └── com.example.location/
│       │       ├── Controller/
│       │       │   ├── ClientController.java
│       │       │   ├── HomeController.java
│       │       │   ├── LocationController.java
│       │       │   ├── StatController.java
│       │       │   └── VoitureController.java
│       │       ├── entity/
│       │       │   ├── Client.java
│       │       │   ├── Location.java
│       │       │   └── Voiture.java
│       │       ├── Repository/
│       │       │   ├── ClientRepository.java
│       │       │   ├── LocationRepository.java
│       │       │   └── VoitureRepository.java
│       │       ├── Service/
│       │       │   ├── ClientService.java
│       │       │   ├── LocationService.java
│       │       │   └── VoitureService.java
│       │       └── Application.java
│       └── resources/
│           ├── templates/
│           │   ├── fragments/  (header, menu, footer)
│           │   ├── voiture/    (list, form)
│           │   ├── client/     (list, form)
│           │   ├── location/   (list, form)
│           │   ├── stats/      (dashboard)
│           │   └── index.html
│           └── application.properties
└── pom.xml
```
<img width="572" height="815" alt="image" src="https://github.com/user-attachments/assets/2e095f96-74c5-413c-8896-e0c5ccd0ccee" />

---


---

## 🧩 Entités

**Voiture** — immatriculation, marque, segment, disponible, prixJour

**Client** — nom, cin, telephone

**Location** — dateDebut, dateFin, montantTotal, statut + `@ManyToOne` Voiture et Client

---

## 🎬 Démo



https://github.com/user-attachments/assets/3507a813-f68c-4f3e-8d36-f4ab0e3bd059



---

## 🛠️ Stack technique

- Spring Boot 3.5.12
- Spring Data JPA / Hibernate
- Thymeleaf
- MySQL
- Lombok
- Java 21

ShopWave Fusion – Multi‑Vendor E‑commerce Backend
A production‑ready multi‑vendor e‑commerce backend built with Spring Boot and JWT‑based authentication.
This service provides secure APIs for customers, sellers, and admins to manage products, carts, orders, coupons, deals, and home page content.

Table of Contents
Features

Tech Stack

Architecture Overview

Getting Started

Configuration

API Overview

Authentication & Users

Seller Management

Product & Category

Cart & Orders

Coupons

Home Categories & Deals

Future Work (Frontend & Deployment)

Contributing

License

Features
JWT‑based authentication with Spring Security (login, register, protected routes).

Role‑based access control for USER, SELLER, and ADMIN (method‑level security using annotations like @PreAuthorize).

Seller account lifecycle: verification, status update (e.g. ACTIVE / PENDING / SUSPENDED), profile management, and deletion.

Complete e‑commerce domain:

Products, categories, home sections (grid, shop by category, electric categories, deals section, etc.).

Cart management: add/remove items, apply/remove coupons, calculate totals.

Orders with basic checkout flow.

Coupon system:

Admin‑only create/delete/list coupons.

Apply or remove coupon on cart using order value and authenticated user.

Home page builder:

HomeCategory entities tagged with sections.

Automatic home page data generation including grid sections, shop‑by‑category sections, electric categories, and deals derived from categories.

Deal management:

Create, update, delete deals.

Generate default deals for specific home categories if repository is empty.

Clean layered architecture with services, repositories, DTOs/entities, and controllers separated.

Tech Stack
Language: Java

Framework: Spring Boot (Web, Security, Data JPA)

Security: Spring Security, JWT

Database: (e.g.) MySQL with Spring Data JPA

Build Tool: Maven or Gradle

Others: Lombok (optional), Validation (optional), RESTful JSON APIs

Architecture Overview
Layers
Controller layer
Handles HTTP requests and responses using @RestController.
Exposes endpoints for authentication, users, sellers, products, cart, orders, coupons, home categories, and deals.

Service layer
Business logic for:

User and seller management

JWT token handling and user lookup from token

Coupon validation and application

Home page aggregation (building Home object from HomeCategory and Deal entities)

Deals creation, update, and deletion

Repository layer
Spring Data JPA repositories for persisting entities like User, Seller, Product, Cart, Order, Coupon, HomeCategory, and Deal.

Security layer

Authentication endpoints issuing JWT tokens.

Security configuration with filters that read the Authorization header.

Method‑level protection using @PreAuthorize("hasRole('ADMIN')"), etc.

Getting Started
Prerequisites
Java 17+

Maven or Gradle

MySQL (or your configured relational database)

Git

Clone the Repository
bash
git clone https://github.com/DevanshInovation/Ecommerce-website.git
cd Ecommerce-website
Configure Database & JWT
Edit src/main/resources/application.properties (or application.yml) and set values according to your environment:

text
spring.datasource.url=jdbc:mysql://localhost:3306/shopwave_fusion
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD

spring.jpa.hibernate.ddl-auto=update

app.jwt.secret=YOUR_SECRET_KEY
app.jwt.expiration=3600000  # 1 hour in ms
Run the Application
Using Maven:

bash
mvn spring-boot:run
Or build and run the jar:

bash
mvn clean package
java -jar target/*.jar
By default, the server typically runs on http://localhost:8080.

Configuration
Common configuration points:

CORS – configured to allow your frontend origin (e.g. http://localhost:3000).

JWT – secret key, expiration time, and header name (Authorization: Bearer <token>).

Roles – seeded or created via admin APIs (ADMIN, SELLER, USER).

Error handling – custom exceptions for resources like user, seller, coupon, or deal not found.

API Overview
Below is an overview of major API groups. Exact paths/method names should match your controllers.

Authentication & Users
POST /auth/register
Register a new user (customer or seller depending on payload).

POST /auth/login
Authenticate and receive a JWT token.

GET /users/profile
Get current authenticated user using JWT from Authorization header.

JWT usage:

Include Authorization: Bearer <token> header in all protected requests.

User lookup is typically done via a method like findUserByJwtToken(jwt) in the service layer.

Seller Management
GET /admin/sellers?status=...
(Admin) List sellers by account status.

PATCH /seller/{id}/status/{status}
(Admin) Update seller account status.

GET /seller/profile
(Seller) Get own profile based on JWT.

DELETE /seller/{id}
(Admin) Delete a seller account.

Product & Category
POST /admin/products
Create or manage products (admin/seller depending on design).

GET /products
List all products, optionally filtered.

GET /categories
List categories used for organizing products.

GET /admin/home-category
(Admin) Get all HomeCategory entries.

PATCH /home-category/{id}
(Admin) Update a HomeCategory (e.g. image, category id).

POST /home/categories
(Admin) Create multiple HomeCategory entries and build home page data.

Cart & Orders
POST /cart
Add product to cart.

GET /cart
Get current user cart.

POST /apply
Apply or remove coupon on cart:

apply (e.g. "true" / "false")

code (coupon code)

orderValue (current cart/order total)

Authorization header with JWT

Controller typically:

Extracts user from JWT.

If apply == "true", calls couponService.applyCoupon(code, orderValue, user).

Else calls couponService.removeCoupon(code, user).

POST /orders
Place order from current cart.

Coupons
Admin‑only:

POST /admin/create (e.g. /coupon/admin/create)
Create a new coupon.

DELETE /admin/delete/{id}
Delete coupon by id.

GET /admin/all
Get all coupons.

Service methods usually:

createCoupon(Coupon coupon)

findAllCoupons()

deleteCoupon(Long id)

findCouponById(Long id) with exception if not found.

Home Categories & Deals
Home Categories

Each HomeCategory has a section field (e.g. GRID, SHOP_BY_CATEGORIES, ELECTRIC_CATEGORIES, DEALS).

Service method createHomePageData(List<HomeCategory> allCategories):

Filters categories into different lists (gridCategories, shopByCategories, electricCategories, dealCategories).

Builds a Home object that contains all these lists plus deals.

Deals

POST /deals
Create a deal.

PATCH /deals/{id}
Update existing deal.

DELETE /deals/{id}
Delete deal and return an API response with a message.

GET /deals
List all deals.

Deal service responsibilities:

getDeals() – fetch all.

createDeal(Deal deal) – create new.

updateDeal(Deal deal, Long id) – update discount/category; throw exception if not found.

deleteDeal(Long id) – find by id and delete; throw if not found.

Home page deal initialization:

If dealRepository.findAll() is empty:

Create default Deal entities from HomeCategory with section DEALS (e.g. new Deal(null, 10, category)).

Save them and return as part of home data; otherwise reuse existing deals.

Future Work (Frontend & Deployment)
Planned enhancements:

React Frontend

Build a separate React app (e.g. shopwave-fusion-frontend) with Redux Toolkit for state management.

Integrate with this backend via JWT authentication and role‑based UI (customer vs seller vs admin dashboards).

Deployment

Containerize backend with Docker.

Deploy to a cloud provider (e.g. Render, Fly.io, or AWS EC2).

Use environment variables / external config for DB and JWT secrets.

Contributing
Contributions, issues, and feature requests are welcome.

Fork the repository.

Create a feature branch: git checkout -b feature/my-feature.

Commit your changes with clear messages.

Push to the branch and open a Pull Request.

Please keep code formatted and follow existing package/module structure.

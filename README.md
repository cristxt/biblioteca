*****Biblioteca - API REST*****

**Descripción del Proyecto**

Este proyecto consiste en desarrollar una API REST para gestionar los datos de una biblioteca. La API permite realizar operaciones CRUD (Crear, Leer, Actualizar y Borrar) sobre libros, siguiendo las buenas prácticas de desarrollo backend. Adicionalmente, opcionalmente, se ha desarrollado para que se puedan gestionar miembros de la biblioteca y los préstamos de libros.


**Tecnologías Utilizadas**

Lenguaje de Programación: Java

Framework: Spring Boot

Base de Datos: MySQL 

Gestor de Dependencias: Maven

Pruebas: Postman para probar los endpoints

**Funcionalidades Principales**

1. CRUD de Libros
  
- CREATE: añadir un libro a la base de datos.
- READ: ver una lista de todos los libros que constan en la base de datos.
- READ: buscar un libro por título.
- READ: buscar libros por autor.
- READ: buscar libros por género literario.
- UPDATE: editar un libro de la base de datos.
- DELETE: eliminar un libro de la base de datos.

2. CRUD de miembros
  
- CREATE: añadir un miembro a la base de datos.
- READ: ver una lista de todos los miembros que constan en la base de datos.
- READ: buscar un miembro por id.
- READ: buscar un miembro por nombre.
- UPDATE: editar un miembro de la base de datos.
- DELETE: eliminar un miembro de la base de datos.

3. CRUD de préstamos

- CREATE: Registrar un préstamo de un libro a un miembro.
- READ: Consultar el historial de préstamos por miembro.
- UPDATE: Registrar la devolución de un libro.
  

**Modelo Entidad-Relación**

El modelo de base de datos incluye las siguientes entidades:

1. Member
- memberId (PK)
- name
- email
- phone

2. Book
- bookId (PK)
- title
- author
- isbn
- availableCopies

3. Loan
- loanId (PK)
- loanDate
- returnDate
- status
- memberId (FK a Member)
- bookId (FK a Book)



Relación entre Member y Loan:
   - Un "Member" puede tener varios "Loans" (1:N).
   - Un "Loan" pertenece a un único "Member" (N:1).

Relación entre Book y Loan:
   - Un "Book" puede estar asociado a varios "Loans" (1:N).
   - Un "Loan" está asociado a un único "Book" (N:1).

---


### ANOTACIONES Y PRÓXIMOS PASOS 
1. No existe una relación directa entre "Member" y "Book" porque su conexión se realiza a través de "Loan". Esto refleja que un miembro interactúa con libros únicamente a través de préstamos.
2. Gestión de Libros, tras la creación de la API, el siguiente paso será crear otro sistema de iteración con el usuario usando el scanner para hacer el CRUD de libros.


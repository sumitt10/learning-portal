# Learning Portal
Learning portal similar to Udemy - Spring Boot RESTful API <br><hr>
**Description:**
Learning Portal is an API project designed to facilitate user management, course management, and category management. The API provides endpoints for various operations related to users, courses, and course categories. The project aims to create a comprehensive learning experience for users.

**Dependencies:**
- [Spring Web](https://spring.io/projects/spring-framework): Simplifies the development of RESTful web services.
- [PostgreSQL Driver](https://jdbc.postgresql.org/): Enables connectivity to a PostgreSQL database.
- [Lombok](https://projectlombok.org/): Reduces boilerplate code by providing annotations for common tasks.
- [Spring DevTools](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.devtools): Facilitates fast application restarts during development.

**Categories & API Endpoints:**

### 1. User Management

Handles user-related operations:

- **GET ShowAllUsers :** `/learningportal/users` Retrieve a list of all users.
- **POST AddUser :** `/learningportal/users/add` Add a new user.
- **GET FindUser :** `/learningportal/users/userID` Find specific user details.
- **DELETE DeleteUser :** `/learningportal/users/UserID` Delete a user.

### 2. Course Management

Manages user courses and favorites:

- **POST CourseEnroll :** `/learningportal/users/UserID/enroll/CourseID` Enroll in a course.
- **POST AddFavorite :** `/learningportal/users/UserID/favorite/CourseID` Add a course to favorites.
- **GET ShowAllFavorites :** `/learningportal/favorite-courses` Retrieve a list of all favorites.
- **GET ShowAllEnrollments :** `/learningportal/Enrollment` Display all user enrollments.
- **GET FindEnrollmentbyUserID :** `/learningportal/Enrollment/UserID` Find enrollments by user ID.
- **GET ShowAllCourses :** `/learningportal/courses/` List all available courses.
- **POST AddCourse :** `/learningportal/courses/add` Add a new course.
- **PUT UpdateCourseDetails :** `/learningportal/courses/CourseID` Update course details.
- **DELETE DeleteCourse :** `/learningportal/courses/CourseID` Delete a course.

### 3. Category Management

Focuses on handling course categories:

- **GET ShowAllCategories :** `/learningportal/categories` Retrieve a list of all available categories.
<br><hr>
### ER Tables :
For detailed information about the database structure, refer to the  <a href="https://drive.google.com/file/d/1ZdItPMgHTKjmZxbCqA0exTYzkkAqV178/view?usp=sharing"> [Entity-Relationship (ER) Tables]</a>used in this project.
<br><hr>
### API Documentation :
Explore the API endpoints and usage in detail by referring to the <a href="https://documenter.getpostman.com/view/32743144/2sA2rCV2CY"> [Postman API Documentation]</a>.
<br><hr>
**Getting Started:**

1. Clone the repository: `git clone https://github.com/sumitt10/learning-portal.git`
2. Install dependencies: `npm install` or `yarn install`
3. Run the application: `npm start` or `yarn start`
4. Access the API at `http://localhost:8086/`
   
**Contribution:**
Contributions are welcome! If you find any issues or have suggestions, please create an issue or submit a pull request.


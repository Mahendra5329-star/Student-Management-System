Student Management System
A console-based Java application for managing student records, built with core Object-Oriented Programming (OOP) principles.

📋 Overview
This Student Management System is a menu-driven console application that allows users to perform CRUD (Create, Read, Update, Delete) operations on student records. It demonstrates clean code practices, encapsulation, inheritance, and input validation.

✨ Features
Add Student – Register a new student with ID, name, age, email, course, and GPA.
Update Student – Modify existing student details (leave a field blank to keep its current value).
Delete Student – Remove a student record by ID.
Search Student – Find students by ID (exact match) or by name (partial match).
Display All Students – View all stored student records.
Input Validation – All fields are validated (e.g., email format, age range, GPA range, unique student ID).
Exception Handling – Graceful error messages for invalid inputs and operations.

🏗️ Architecture
The application is organized into four main classes:
Class	Responsibility
Person	Base class holding common personal attributes (name, age, email) with encapsulated getters/setters and validation.
Student	Extends Person, adds student-specific fields (studentId, course, gpa) with validation.
StudentManager	Manages the collection of students (add, update, delete, find, display).
StudentManagementSystem	Main class providing the menu-driven console interface and input helpers.
OOP Concepts Demonstrated
Encapsulation – Private fields with validated getters and setters.
Inheritance – Student inherits from Person.
Polymorphism – toString() is overridden in both Person and Student.
Abstraction – StudentManager abstracts the data storage and retrieval logic.

✅ Validation Rules
Field	Rule
Name	Cannot be empty.
Age	Must be between 5 and 120.
Email	Must match a valid email pattern (e.g., user@example.com).
Student ID	Cannot be empty; must be unique; stored in uppercase.
Course	Cannot be empty.
GPA	Must be between 0.0 and 4.0.

🚀 Getting Started
Prerequisites
Java Development Kit (JDK) 8 or higher.
A terminal or command prompt.

Compilation
Save the source code in a file named StudentManagementSystem.java, then compile:
bash
javac StudentManagementSystem.java
Running the Application
bash
java StudentManagementSystem

🖥️ Usage
Upon running, you'll see the main menu:

text
===== Student Management System =====
1. Add Student
2. Update Student
3. Delete Student
4. Search Student
5. Display All Students
6. Exit
=====================================
Enter your choice:
Example Workflow
Adding a student:

text
--- Add New Student ---
Student ID: S101
Name: John Doe
Age: 20
Email: john.doe@example.com
Course: Computer Science
GPA (0.0 - 4.0): 3.75
[OK] Student added successfully.
Searching by name:

text
--- Search Student ---
1. Search by ID
2. Search by Name
Enter option: 2
Enter Name (or part of it): john
Found 1 student(s):
ID: S101     Name: John Doe        Age: 20  Email: john.doe@example.com    Course: Computer Science  GPA: 3.75

📁 Project Structure
text
StudentManagementSystem.java
├── class Person
├── class Student (extends Person)
├── class StudentManager
└── public class StudentManagementSystem (main)

⚠️ Error Handling
The application handles the following error scenarios:
Non-numeric input for age or GPA.
Invalid email format.
Out-of-range age or GPA values.
Duplicate student IDs.
Operations on non-existent student IDs.
Empty required fields.
All errors are reported with a clear [ERROR] prefix without crashing the application.

🔧 Possible Enhancements
Persist student data to a file or database.
Add sorting and filtering options.
Implement a GUI (JavaFX or Swing).
Add unit tests with JUnit.
Support multiple courses or enrollment history.

📄 License
This project is provided as-is for 
educational purposes. Feel free to use, modify, and distribute it as needed.

👤 Author:
Created as a demonstration of Java OOP and console application development

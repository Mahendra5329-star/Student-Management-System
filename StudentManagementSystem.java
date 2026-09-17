import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Base class demonstrating inheritance and encapsulation
class Person {
    private String name;
    private int age;
    private String email;

    public Person(String name, int age, String email) {
        setName(name);
        setAge(age);
        setEmail(email);
    }

    // Getters and setters with validation (encapsulation)
    public String getName() { return name; }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name.trim();
    }

    public int getAge() { return age; }

    public void setAge(int age) {
        if (age < 5 || age > 120) {
            throw new IllegalArgumentException("Age must be between 5 and 120.");
        }
        this.age = age;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        this.email = email.trim();
    }

    @Override
    public String toString() {
        return String.format("Name: %-15s Age: %-3d Email: %-25s", name, age, email);
    }
}

// Student class inherits from Person
class Student extends Person {
    private String studentId;
    private String course;
    private double gpa;

    public Student(String studentId, String name, int age, String email, String course, double gpa) {
        super(name, age, email);
        setStudentId(studentId);
        setCourse(course);
        setGpa(gpa);
    }

    public String getStudentId() { return studentId; }

    public void setStudentId(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be empty.");
        }
        this.studentId = studentId.trim().toUpperCase();
    }

    public String getCourse() { return course; }

    public void setCourse(String course) {
        if (course == null || course.trim().isEmpty()) {
            throw new IllegalArgumentException("Course cannot be empty.");
        }
        this.course = course.trim();
    }

    public double getGpa() { return gpa; }

    public void setGpa(double gpa) {
        if (gpa < 0.0 || gpa > 4.0) {
            throw new IllegalArgumentException("GPA must be between 0.0 and 4.0.");
        }
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return String.format("ID: %-8s %s Course: %-15s GPA: %.2f",
                studentId, super.toString(), course, gpa);
    }
}

// Manager class that handles the collection of students
class StudentManager {
    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        if (findById(student.getStudentId()) != null) {
            throw new IllegalArgumentException("Student with ID " + student.getStudentId() + " already exists.");
        }
        students.add(student);
        System.out.println("[OK] Student added successfully.");
    }

    public void updateStudent(String id, Student updated) {
        Student existing = findById(id);
        if (existing == null) {
            throw new IllegalArgumentException("Student with ID " + id + " not found.");
        }
        existing.setName(updated.getName());
        existing.setAge(updated.getAge());
        existing.setEmail(updated.getEmail());
        existing.setCourse(updated.getCourse());
        existing.setGpa(updated.getGpa());
        System.out.println("[OK] Student updated successfully.");
    }

    public void deleteStudent(String id) {
        Student student = findById(id);
        if (student == null) {
            throw new IllegalArgumentException("Student with ID " + id + " not found.");
        }
        students.remove(student);
        System.out.println("[OK] Student deleted successfully.");
    }

    public Student findById(String id) {
        for (Student s : students) {
            if (s.getStudentId().equalsIgnoreCase(id.trim())) {
                return s;
            }
        }
        return null;
    }

    public List<Student> findByName(String name) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getName().toLowerCase().contains(name.toLowerCase().trim())) {
                result.add(s);
            }
        }
        return result;
    }

    public void displayAll() {
        if (students.isEmpty()) {
            System.out.println("[INFO] No student records found.");
            return;
        }
        System.out.println("\n--- All Student Records ---");
        for (Student s : students) {
            System.out.println(s);
        }
    }
}

// Main class with menu-driven interface
public class StudentManagementSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentManager manager = new StudentManager();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = readInt("Enter your choice: ");
            try {
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        updateStudent();
                        break;
                    case 3:
                        deleteStudent();
                        break;
                    case 4:
                        searchStudent();
                        break;
                    case 5:
                        manager.displayAll();
                        break;
                    case 6:
                        System.out.println("Exiting... Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("[ERROR] Invalid choice. Please try again.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            } catch (Exception e) {
                System.out.println("[ERROR] Unexpected error: " + e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== Student Management System =====");
        System.out.println("1. Add Student");
        System.out.println("2. Update Student");
        System.out.println("3. Delete Student");
        System.out.println("4. Search Student");
        System.out.println("5. Display All Students");
        System.out.println("6. Exit");
        System.out.println("=====================================");
    }

    private static void addStudent() {
        System.out.println("\n--- Add New Student ---");
        String id = readString("Student ID: ");
        String name = readString("Name: ");
        int age = readInt("Age: ");
        String email = readString("Email: ");
        String course = readString("Course: ");
        double gpa = readDouble("GPA (0.0 - 4.0): ");

        Student student = new Student(id, name, age, email, course, gpa);
        manager.addStudent(student);
    }

    private static void updateStudent() {
        System.out.println("\n--- Update Student ---");
        String id = readString("Enter Student ID to update: ");
        Student existing = manager.findById(id);
        if (existing == null) {
            System.out.println("[ERROR] Student not found.");
            return;
        }
        System.out.println("Current details: " + existing);
        System.out.println("Enter new details (leave blank to keep current):");

        String name = readStringOptional("Name (" + existing.getName() + "): ");
        if (name.isEmpty()) name = existing.getName();

        String ageStr = readStringOptional("Age (" + existing.getAge() + "): ");
        int age = ageStr.isEmpty() ? existing.getAge() : Integer.parseInt(ageStr);

        String email = readStringOptional("Email (" + existing.getEmail() + "): ");
        if (email.isEmpty()) email = existing.getEmail();

        String course = readStringOptional("Course (" + existing.getCourse() + "): ");
        if (course.isEmpty()) course = existing.getCourse();

        String gpaStr = readStringOptional("GPA (" + existing.getGpa() + "): ");
        double gpa = gpaStr.isEmpty() ? existing.getGpa() : Double.parseDouble(gpaStr);

        Student updated = new Student(id, name, age, email, course, gpa);
        manager.updateStudent(id, updated);
    }

    private static void deleteStudent() {
        System.out.println("\n--- Delete Student ---");
        String id = readString("Enter Student ID to delete: ");
        manager.deleteStudent(id);
    }

    private static void searchStudent() {
        System.out.println("\n--- Search Student ---");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        int option = readInt("Enter option: ");
        if (option == 1) {
            String id = readString("Enter Student ID: ");
            Student s = manager.findById(id);
            if (s != null) {
                System.out.println("Found: " + s);
            } else {
                System.out.println("[ERROR] Student not found.");
            }
        } else if (option == 2) {
            String name = readString("Enter Name (or part of it): ");
            List<Student> results = manager.findByName(name);
            if (results.isEmpty()) {
                System.out.println("[ERROR] No students found with that name.");
            } else {
                System.out.println("Found " + results.size() + " student(s):");
                for (Student s : results) {
                    System.out.println(s);
                }
            }
        } else {
            System.out.println("[ERROR] Invalid search option.");
        }
    }

    // --- Input helper methods with validation and exception handling ---

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static String readStringOptional(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Invalid number. Please enter a valid integer.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Invalid number. Please enter a valid decimal number.");
            }
        }
    }
}
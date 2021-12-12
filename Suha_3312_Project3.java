package assignment;
import java.io.*;
import java.util.Random;
import java.util.Scanner;
/**
 * Driver class
 */
public class Suha_3312_Project3 {

    public static Student getStudentByID(Student[] students,int id){
        for (Student student:students
             ) {
            if(student.getID()==id){
                return student;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        int numOfStudent; //total number of student
        int input;  //for taking input
        Student[] students; //student array to store all student
        int studentCounter = 0;
        Student_Employee[] employedStudent; //employed student array
        int employedStudentCounter = 0;
        int numOfCourse;
        Course[] courses;
        Scanner scanner = new Scanner(System.in); //Scanner obj to take user inputs

        System.out.println("Welcome to Student Management System!");
        System.out.print("\nThis system will allow you to manage students and courses. Let’s start with the\n" +
                "number of students this system will have: ");

        // initializing array and scanner object
        numOfStudent = scanner.nextInt();
        students = new Student[numOfStudent];
        employedStudent = new Student_Employee[numOfStudent];
        while(true) {
            System.out.println("\nPress ‘1’ Student Management System (SMS)\n" +
                    "Press ‘2’ Course Management System (CMS)\n" +
                    "Press ‘0’ Exit \n");

            //taking input
            input = scanner.nextInt();
            scanner.nextLine();

            //add a student
            if (input == 1) {

                while (true) {
                    //printing menu
                    System.out.println("\n***Welcome to SMS***\n\n" +
                            "Press ‘1’ to add a student \n" +
                            "Press ‘2’ to deactivate a student \n" +
                            "Press ‘3’ to display all students \n" +
                            "Press ‘4’ to search for a student by ID \n" +
                            "Press ‘5’ to assign on-campus job\n" +
                            "Press ‘6’ to display all students with on-campus jobs\n" +
                            "Press ‘0’ to exit the system \n");

                    //taking input
                    input = scanner.nextInt();
                    scanner.nextLine();

                    //add a student
                    if (input == 1) {
                        System.out.print("Enter first name: ");
                        String fName = scanner.nextLine();
                        System.out.print("Enter last name: ");
                        String lName = scanner.nextLine();
                        System.out.print("Enter level of the student: ");
                        String level = scanner.nextLine();

                        // creating new student obj
                        Student student = new Student(fName, lName, level);

                        // printing added student info
                        System.out.printf("\n%s %s has been added as a %s with ID %d\n",
                                student.getFirstname(), student.getLastName(), student.getLevel(), student.getID());

                        // add the student to the array
                        students[studentCounter] = student;
                        studentCounter++;

                    }

                    // deactivate a student
                    else if (input == 2) {
                        System.out.print("Enter the ID for the student you want to deactivate: ");
                        int id = scanner.nextInt(); // taking id as input
                        Student st = getStudentByID(students,id);
                        st.setActive(false); // set status to false
                        System.out.printf("\n%s %s has been deactivated\n", st.getFirstname(), st.getLastName());
                    }

                    //display all students
                    else if (input == 3) {
                        // printing all student info
                        for (Student student : students) {
                            if (student != null) {
                                System.out.println(student);
                            }
                        }
                    }

                    //search for a student by ID
                    else if (input == 4) {
                        System.out.print("Enter the student ID: ");
                        int id = scanner.nextInt(); //taking id
                        //print the info of student using his id
                        System.out.println(getStudentByID(students,id));
                    }

                    //search for a student by ID
                    else if (input == 5) {
                        System.out.print("Enter the student ID: ");
                        int id = scanner.nextInt(); //taking id
                        System.out.print("Enter job: ");
                        String job = scanner.next();//taking job title TA or RA
                        //validating
                        while (!job.equalsIgnoreCase("TA") && !job.equalsIgnoreCase("RA")){
                            System.out.println("Invalid job...Enter TA or RA");
                            System.out.print("Enter job: ");
                            job = scanner.next();//taking job title TA or RA
                        }

                        System.out.print("Enter job type: ");
                        String type = scanner.next();//taking job title TA or RA
                        //validating
                        while (!type.equalsIgnoreCase("part-time") && !type.equalsIgnoreCase("full-time")){
                            System.out.println("Invalid job type...Enter part-time or full-type");
                            System.out.print("Enter job: ");
                            type = scanner.next();//taking job title TA or RA
                        }
                        Student st = getStudentByID(students,id);
                        Student_Employee student = new Student_Employee(st.getFirstname(),st.getLastName(),st.getLevel(),type,job);
                        student.setID(st.getID());
                        employedStudent[employedStudentCounter] = student;
                        employedStudentCounter++;
                        System.out.println(student.getFirstname()+" "+student.getLastName()+" has been assigned "+type+" "+job+" job");
                    }

                    //search for a student by ID
                    else if (input == 6) {
                        // printing all student info
                        for (Student_Employee student : employedStudent) {
                            if (student != null) {
                                student.display();
                            }
                        }
                    }

                    // exit the system
                    else if (input == 0) {
                        System.out.println("Thank you!!");
                        break;
                    }

                    //wrong input
                    else {
                        System.out.println("Wrong input. Try again.");
                    }
                }
            } else if (input == 2) {
                while (true) {
                    //printing menu
                    System.out.println("\n***Welcome to CMS*** \n\n" +
                            "Press ‘1’ to add a course \n" +
                            "Press ‘2’ to assign student to course\n" +
                            "Press ‘3’ to display students with assigned courses\n" +
                            "Press ‘0’ to exit the system \n");

                    //taking input
                    input = scanner.nextInt();

                    //add a course
                    if (input == 1) {
                        System.out.print("Enter course ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter course name: ");
                        String name = scanner.nextLine();


                        // creating new Course obj
                        Course course = new Course(id, name);

                        // printing added course info
                        System.out.printf("\nConfirmation: New course %d has been added\n",course.getID());

                        String fileName = "Courses.txt";
                        File file = new File(fileName);
                        try {
                            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
                            BufferedWriter bw = new BufferedWriter(fw);
                            bw.write(course.toString());
                            bw.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.exit(-1);
                        }
                    }

                    // assign a student
                    else if (input == 2) {
                        System.out.print("Enter the ID for the student you want to assign: ");
                        int studentId = scanner.nextInt(); // taking studentId as input
                        System.out.print("Enter the ID for the course you want to assign: ");
                        int courseId = scanner.nextInt(); // taking courseId as input
                        Student st = getStudentByID(students,studentId);
                        System.out.printf("\nConfirmaEon: %s %s has been assigned course %d\n", st.getFirstname(),st.getLastName(), courseId);
                        String fileName = "CourseAssignment.txt";
                        File file = new File(fileName);
                        try {
                            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
                            BufferedWriter bw = new BufferedWriter(fw);
                            String line = st.getFirstname() + " "+st.getLastName()+"\n" +
                                    "ID - "+studentId+"\n"+
                                    "Courses: "+courseId+"\n\n";
                            bw.write(line);
                            bw.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.exit(-1);
                        }

                    }

                    //display students with assigned courses
                    else if (input == 3) {
                        try (BufferedReader in = new BufferedReader(new FileReader("CourseAssignment.txt"))) {
                            String str;
                            while ((str = in.readLine()) != null) {
                                System.out.println(str);
                            }
                        } catch (IOException e) {
                            System.out.println("File Read Error");
                        }
                    }


                    // exit the system
                    else if (input == 0) {
                        System.out.println("Thank you!!");
                        break;
                    }

                    //wrong input
                    else {
                        System.out.println("Wrong input. Try again.");
                    }
                }
            }
            else if(input == 0){
                System.out.println("Thank you!!");
                break;
            }
        }

    }
}

interface Student_Interface{
    /**
     * Getter for id
     * @return id
     */
    public int getID();

    /**
     * Setter for id
     * @param ID: student id
     */
    public void setID(int ID);

    /**
     * Getter for first name
     * @return first name
     */
    public String getFirstname();

    /**
     * Setter for id
     * @param firstname: student first name
     */
    public void setFirstname(String firstname);

    /**
     * Getter for last name
     * @return last name
     */
    public String getLastName();

    /**
     * Setter for id
     * @param lastName: student last name
     */
    public void setLastName(String lastName);

    /**
     * Getter for level
     * @return level
     */
    public String getLevel();

    /**
     * Setter for id
     * @param level: student level
     */
    public void setLevel(String level);

    /**
     * Getter for active
     * @return active status
     */
    public boolean isActive();

    /**
     * Setter for id
     * @param active: student status
     */
    public void setActive(boolean active);

}
/**
 * Student class represent a student with following data fields:
 *
 * ▪ Student ID
 * ▪ Student First Name
 * ▪ Student Last Name
 * ▪ Student level
 *      • Freshman
 *      • Sophomore
 *      • Junior
 *      • Senior
 * ▪ Active
 *      • true – this is the default
 *      • false
 */
class Student implements Student_Interface{
    private int ID;
    private String firstname;
    private String lastName;
    private String level;
    private boolean active;

    /**
     * Default constructor
     *
     * @param firstname: Student First Name
     * @param lastName: Student Last Name
     * @param level: Student level
     */
    public Student(String firstname, String lastName, String level) {
        this.ID = new Random().nextInt(99);//random between 0 and 99
        this.firstname = firstname;
        this.lastName = lastName;
        this.level = level;
        active=true;
    }

    /**
     * Getter for id
     * @return id
     */
    @Override
    public int getID() {
        return ID;
    }

    /**
     * Setter for id
     * @param ID: student id
     */
    @Override
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Getter for first name
     * @return first name
     */
    @Override
    public String getFirstname() {
        return firstname;
    }

    /**
     * Setter for id
     * @param firstname: student first name
     */
    @Override
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Getter for last name
     * @return last name
     */
    @Override
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for id
     * @param lastName: student last name
     */
    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for level
     * @return level
     */
    @Override
    public String getLevel() {
        return level;
    }

    /**
     * Setter for id
     * @param level: student level
     */
    @Override
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * Getter for active
     * @return active status
     */
    @Override
    public boolean isActive() {
        return active;
    }

    /**
     * Setter for id
     * @param active: student status
     */
    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        String status = active ? "Active" : "Deactivate";
        return firstname + " " + lastName + '\n' +
                "ID: " + ID + '\n' +
                "Level: " + level + '\n' +
                "Status: " + status + '\n';
    }
}
class Student_Employee extends Student{

    private String employment_type;
    private String job;
    /**
     * Default constructor
     *
     * @param firstname : Student First Name
     * @param lastName  : Student Last Name
     * @param level     : Student level
     */
    public Student_Employee(String firstname, String lastName, String level,String employment_type,String job) {
        super(firstname, lastName, level);
        this.employment_type = employment_type;
        this.job = job;
    }

    public String getEmployment_type() {
        return employment_type;
    }

    public String getJob() {
        return job;
    }

    public void display() {
        System.out.println(getFirstname() + " " + getLastName() + '\n' +
                "ID - " + getID() + '\n' +
                employment_type +" "+job+"\n");
    }
}
interface Course_Interface{
    public int getID();

    public void setID(int iD);

    public String getName();

    public void setName(String name);
}
class Course implements Course_Interface {
    private int id;
    private String name;

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
    }
    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setID(int iD) {
        this.id = iD;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  "ID: " + id + '\n' +
                "Name: " + name + '\n';
    }
}

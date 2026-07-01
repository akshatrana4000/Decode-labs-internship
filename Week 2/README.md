# DecodeLabs Java Internship — Project 2
##  Student Grade Calculator (Java)

**Batch:** 2026  
**Organization:** DecodeLabs  
**Language:** Java  

---

## About The Project
📚 Student Grade Calculator (Java):

This Java program is a simple console-based Student Grade Calculator that allows users to input marks for multiple subjects and calculates:

- ✅ Total Marks
- ✅ Average Percentage
- ✅ Final Grade


## 🚀 Features

- ✔ Input validation for number of subjects
- ✔ Validation of marks (must be between 0–100)
- ✔ Handles invalid/non-numeric input gracefully
- ✔ Calculates average using proper type casting
- ✔ Assigns grades based on standard percentage ranges
- ✔ Clean and formatted output


## 🛠️ Technologies Used

- Java (JDK 8 or above)
- Scanner class for user input


## 📂 Project Structure
DecodeLabs_Java_p2.java

README.md


## ▶️ How to Run


1. Clone this repository:
   git clone https://github.com/your-username/student-grade-calculator.git


2. Navigate to the project folder:
   cd student-grade-calculator


3. Compile the Java file:
   javac DecodeLabs_Java_p2.java


4. Run the program:
   java DecodeLabs_java_P2



## 🧾 How It Works

1. The user is prompted to enter the number of subjects.
2. The program validates the input to ensure it is a positive integer.
3. For each subject:

   - The user enters marks (0–100)
   - Invalid inputs are rejected until valid data is provided


4. The program calculates:

   - Total marks
   - Average percentage


5. Based on the average, a grade is assigned:


## 🏆 Grading Criteria
- Percentage	   >   Grade
- 90 and above	 >    A
- 80 – 89        >   	B
- 70 – 79	       >    C
- 60 – 69        >   	D
- Below 60	     >    F

## 💡 Example Output
- Enter number of subjects: 3
- Enter marks for subject 1 (0-100): 85
- Enter marks for subject 2 (0-100): 90
- Enter marks for subject 3 (0-100): 78



- ----- RESULT -----
- Total Marks: 253
- Average Percentage: 84.33%
- Grade: B


## 🔧 Future Improvements

- Add GUI version using Java Swing/JavaFX
- Export results to a file
- Support for weighted subjects
- Add student name and ID tracking


## 📄 License
This project is open-source and free to use for learning purposes.

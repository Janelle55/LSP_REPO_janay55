package org.howard.edu.lsp.finalexam.question2;

/**
 * Concrete implementation of Report for student data.
 */
public class StudentReport extends Report {

    private String studentName;
    private double gpa;

    /**
     * Loads student data.
     */
    @Override
    public void loadData() {
        this.studentName = "John Doe";
        this.gpa = 3.8;
    }

    /**
     * Formats the header for the student report.
     */
    @Override
    public String formatHeader() {
        return "Student Report";
    }

    /**
     * Formats the body for the student report.
     */
    @Override
    public String formatBody() {
        return "Student Name: " + studentName + "\nGPA: " + gpa;
    }

    /**
     * Formats the footer for the student report.
     */
    @Override
    public String formatFooter() {
        return "End of Student Report";
    }
}
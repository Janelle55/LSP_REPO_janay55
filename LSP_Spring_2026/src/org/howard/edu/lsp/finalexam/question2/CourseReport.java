package org.howard.edu.lsp.finalexam.question2;

/**
 * Concrete implementation of Report for course data.
 */
public class CourseReport extends Report {

    private String courseName;
    private int enrollment;

    /**
     * Loads course data.
     */
    @Override
    public void loadData() {
        this.courseName = "CSCI 363";
        this.enrollment = 45;
    }

    /**
     * Formats the header for the course report.
     */
    @Override
    public String formatHeader() {
        return "Course Report";
    }

    /**
     * Formats the body for the course report.
     */
    @Override
    public String formatBody() {
        return "Course: " + courseName + "\nEnrollment: " + enrollment;
    }

    /**
     * Formats the footer for the course report.
     */
    @Override
    public String formatFooter() {
        return "End of Course Report";
    }
}
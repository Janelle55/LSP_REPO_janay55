package org.howard.edu.lsp.finalexam.question2;

/**
 * Abstract Report class that defines the template method workflow
 * for generating reports.
 */
public abstract class Report {

    /**
     * Loads the data needed for the report.
     */
    public abstract void loadData();

    /**
     * Formats and returns the report header.
     *
     * @return the formatted header
     */
    public abstract String formatHeader();

    /**
     * Formats and returns the report body.
     *
     * @return the formatted body
     */
    public abstract String formatBody();

    /**
     * Formats and returns the report footer.
     *
     * @return the formatted footer
     */
    public abstract String formatFooter();

    /**
     * Template method that defines the fixed report generation workflow.
     */
    public final void generateReport() {
        loadData();
        System.out.println("=== HEADER ===");
        System.out.println(formatHeader());
        System.out.println();
        System.out.println("=== BODY ===");
        System.out.println(formatBody());
        System.out.println();
        System.out.println("=== FOOTER ===");
        System.out.println(formatFooter());
        System.out.println();
    }
}

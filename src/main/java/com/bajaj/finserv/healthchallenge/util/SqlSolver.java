package com.bajaj.finserv.healthchallenge.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SqlSolver {

    private static final Logger logger = LoggerFactory.getLogger(SqlSolver.class);

    /**
     * Determines the SQL question based on registration number and returns the
     * appropriate query
     * 
     * @param regNo Registration number
     * @return SQL query string
     */
    public String getSqlQuery(String regNo) {
        logger.info("Determining SQL question for registration number: {}", regNo);

        // Extract last two digits from registration number
        String lastTwoDigits = regNo.substring(regNo.length() - 2);
        int digits = Integer.parseInt(lastTwoDigits);

        if (digits % 2 == 1) {
            // Odd - Question 1
            logger.info("Registration number ends with odd digits ({}), using Question 1", digits);
            return getQuestion1Query();
        } else {
            // Even - Question 2
            logger.info("Registration number ends with even digits ({}), using Question 2", digits);
            return getQuestion2Query();
        }
    }

    /**
     * SQL Query for Question 1 (Odd registration numbers)
     * Based on the typical SQL challenge patterns
     */
    private String getQuestion1Query() {
        // Example query - This should be replaced with the actual SQL from the Google
        // Drive link
        // Question 1 typically involves finding specific patterns in data
        return "SELECT customer_id, customer_name, total_orders " +
                "FROM customers c " +
                "JOIN (SELECT customer_id, COUNT(*) as total_orders " +
                "      FROM orders " +
                "      WHERE order_date >= '2023-01-01' " +
                "      GROUP BY customer_id " +
                "      HAVING COUNT(*) > 5) o ON c.customer_id = o.customer_id " +
                "ORDER BY total_orders DESC";
    }

    /**
     * SQL Query for Question 2 (Even registration numbers)
     * Based on the typical SQL challenge patterns
     */
    private String getQuestion2Query() {
        // Example query - This should be replaced with the actual SQL from the Google
        // Drive link
        // Question 2 typically involves complex joins or aggregations
        return "SELECT p.product_name, SUM(oi.quantity * oi.unit_price) as total_revenue " +
                "FROM products p " +
                "JOIN order_items oi ON p.product_id = oi.product_id " +
                "JOIN orders o ON oi.order_id = o.order_id " +
                "WHERE o.order_date BETWEEN '2023-01-01' AND '2023-12-31' " +
                "GROUP BY p.product_id, p.product_name " +
                "HAVING SUM(oi.quantity * oi.unit_price) > 10000 " +
                "ORDER BY total_revenue DESC " +
                "LIMIT 10";
    }
}
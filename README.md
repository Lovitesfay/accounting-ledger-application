# Accounting Ledger Application

## Overview
A Java console-based Accounting Ledger Application that tracks deposits and payments using a CSV file. Users can add transactions, view a ledger, and filter reports.

---

## Features
- Add deposits (income)
- Add payments (expenses)
- View full transaction ledger
- Filter by deposits or payments
- Read/write transactions from a CSV file
- Persistent data storage

---

## Project Structure
src/
 ├── main/
 │   ├── java/
 │   │   ├── AccountingLedgerApp.java
 │   │   ├── Transaction.java
 │   ├── resources/
 │       ├── transactions.csv

---

## Transaction File Format
Transactions are stored in a pipe-delimited format:

date|time|description|vendor|amount

### Example:
2026-04-01|10:30:00|Coffee|Starbucks|-4.50
2026-04-01|12:00:00|Salary|Company Inc|1500.00

- Negative values = payments
- Positive values = deposits

---

## How to Run

### Compile:
javac AccountingLedgerApp.java Transaction.java

### Run:
java AccountingLedgerApp

---

## Main Menu
- Add Deposit
- Make Payment
- View Ledger
- Reports
- Exit

---

## Technologies Used
- Java
- File I/O (BufferedReader, FileWriter)
- ArrayList
- LocalDate / LocalTime

---

## Future Improvements
- Search by vendor
- Date range filtering
- Export reports
- GUI version

---

## Author
Java learning project (Pluralsight coursework)

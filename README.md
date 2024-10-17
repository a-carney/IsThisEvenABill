# Is This Even A Bill?

## Project Vision

"Is This Even A Bill?" -- Many Americans have found themselves asking this along with many other frustrating questions about their healthcare bills. Even worse, those that try to think ahead and predict costs are usually met with nothing but frustration.

This project aims to help mitigate that frustration. "Is This Even A Bill?" is an in-progress application that aims to assist with predicting treatment costs and verifying insurance bills for rendered services. It leverages Spring Boot to provide an API for parsing, analyzing, and verifying documents using predefined rules and logic. The project plans to use freely available third-party data, such as NPT, CPT, and ICD-10 codes, and implement an algorithm to predict costs based on publicly available Medicare rates.

### Current Status

The project is actively under development, and currently includes:

- **Core Application**: Entry point of the application, bootstrapping the Spring Boot environment.
- **Controller Layer**: REST controllers for managing API endpoints for bill validation.
- **Service Layer**: Business logic for validating and parsing bills.
- **Model Layer**: Data structures used throughout the application, such as `Bill` and `Invoice`.
- **Exception Handling**: Custom exceptions to provide clear feedback for incorrect inputs.
- **Utility Classes**: Helper methods for tasks like string manipulation and data formatting.

## How to Use

### Prerequisites

- Java 17+
- Maven 3.x

### Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/a-carney/IsThisEvenABill/
   ```

2. Navigate to the project directory:
   ```bash
   cd IsThisEvenABill
   ```

3. Build the project using Maven:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

The application will start on `localhost:8080` by default.

## Features

- **Bill Parsing**: Parses and analyzes bill-related documents.
- **Validation API**: RESTful endpoints for submitting documents for verification.
- **Error Handling**: Robust handling of common errors with meaningful responses.

## Roadmap

- [ ] Expand parsing logic to support more document formats.
- [ ] Implement a front-end interface for non-technical users.
- [ ] Add more detailed logging and monitoring features.
- [ ] Enhance the validation algorithms for higher accuracy.
- [ ] Integrate third-party data such as NPT, CPT, and ICD-10.
- [ ] Implement cost prediction using publicly available Medicare rates.

## Contributing

We welcome contributions! Please open an issue or submit a pull request for any features or bug fixes you'd like to see.

## License

This project is licensed under the MIT License.


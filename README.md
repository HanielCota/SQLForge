<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SQLForge</title>
</head>

<body>

    <h1>SQLForge</h1>

    <p>SQLForge is a Java application that simplifies database interactions using HikariCP and clean code practices. This README provides an example usage and highlights key components of the application.</p>

    <h2>Example Usage</h2>

    <pre><code>
        <!-- Include your example usage code here -->
    </code></pre>

    <h2>Components</h2>

    <h3>DatabaseConfig</h3>

    <p>The <code>DatabaseConfig</code> class provides methods to configure and create a HikariCP DataSource with predefined properties.</p>

    <h3>TableCreator</h3>

    <p>The <code>TableCreator</code> class allows you to create tables in the database. It validates input parameters and logs any errors that may occur during table creation.</p>

    <h3>SqlExecutor</h3>

    <p>The <code>SqlExecutor</code> class provides methods to execute SQL updates and queries using a HikariCP DataSource. It manages prepared statements and handles SQL exceptions.</p>

    <h2>Getting Started</h2>

    <p>To use SQLForge in your project, include the following dependency in your Gradle build file:</p>

    <pre><code>
        implementation 'com.zaxxer:HikariCP:5.1.0'
    </code></pre>

    <p>Feel free to modify and adapt the provided code snippets based on your project's needs.</p>

</body>

</html>

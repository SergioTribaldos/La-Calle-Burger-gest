<?php

function compruebaConexion(){

$servername = "85.214.120.213";
$username = "sergio";
$password = "sergio";

// Create connection
$conn = new mysqli($servername, $username, $password);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
echo "Connected successfully";
return $conn;
}

?>

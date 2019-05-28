<?php
session_start();
?>

    <?php
  $nombre= $_POST["usuario"];
  $contrasena=$_POST["contrasena"];

// Create connection
$conn = new mysqli("85.214.120.213", "sergio", "sergio", "lacalle");
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "SELECT * FROM usuario";
$result = $conn->query($sql);
$log=false;
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
         if($row["nombre"]==$nombre&&$row["contraseña"]==$contrasena) {

           $log=true;
           $_SESSION["restaurante"]=$row["Restaurante_codigoRestaurante"];
           $_SESSION["nombreUsuario"]=$nombre;
           header("Location: ../LCB/panelPedido.php/");
           break;
         }
    }
}
?>


<?php
if($log==false){

  $sql = "SELECT * FROM administrador";
  $result = $conn->query($sql);
  if ($result->num_rows > 0) {
      while($row = $result->fetch_assoc()) {
           if($row["nombre"]==$nombre&&$row["contraseña"]==$contrasena) {
             $conn->close();
             header("Location: /LCB/panelAdministrador.php");
             break;
           }
      }
  }
  $conn->close();
  echo '<script>alert ("Usuario incorrecto")
        location = "../LCB/";</script>';
}

?>



<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="estilos.css">
    <style media="screen">
    body{
      background-color: black;
    }
    </style>
  </head>
  <body>
    <?php
    include ("conexion.php");
    $_SESSION["newsession"]=compruebaConexion();


    ?>
    <div class=" d-flex justify-content-center">
      <img src="descarga.png" height="250" width="250">

    </div>
    <div class=" d-flex justify-content-center" id="todoFormulario" align="center">

    <form action="login.php" method="post">
      <div class="input-group input-group-lg">
  <div class="input-group-prepend">
    <span class="input-group-text" id="inputGroup-sizing-lg">Usuario</span>
  </div>
  <input type="text" name="usuario" class="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm">
</div>
<div class="input-group input-group-lg">
<div class="input-group-prepend">
<span class="input-group-text" id="inputGroup-sizing-lg">Contraseña</span>
</div>
<input type="password" name="contrasena" class="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm">
</div>
      <button type="submit" class="btn btn-primary btn-lg boton">Login</button>
    </form>
    </div>

  </body>
</html>

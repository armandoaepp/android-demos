<?php
    include_once("Clases/usuario.php");
    $loginusu = isset($_POST["txtUsuario"]) ? $_POST["txtUsuario"] : "";
    $password = isset($_POST["txtContrasena"]) ? $_POST["txtContrasena"] : "";  
    $us = new usuario();
    $result = $us->VerificarUsuario($loginusu, $password);
    $rows = array();
    if($result->rowCount()){
        while($row = $result->fetch()){
            $rows[] = $row;
        }
        echo json_encode(array('results'=>$rows));
    }
    else{
        echo json_encode(array('results'=>'-1'));
    }    
?>

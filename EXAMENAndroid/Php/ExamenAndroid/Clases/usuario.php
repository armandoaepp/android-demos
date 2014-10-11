<?php
include_once("conexion.php");
class usuario {
     function VerificarUsuario($usuario, $contrasena){
        $ocado=new conexion();
        $sql = "select idusuario,nombcomp,loginusu,password from usuario where loginusu='$usuario' and password='$contrasena'";
        //echo $sql;
        $ejecutar=$ocado->Ejecutar($sql);
	return $ejecutar;
    }
}

?>

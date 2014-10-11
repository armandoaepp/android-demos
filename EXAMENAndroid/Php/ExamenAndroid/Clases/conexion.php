<?php
class conexion{  
    function Conectar(){
        try {
            $db = new PDO('mysql:host=localhost;dbname=bdExamen','root','');
            return $db;
        }
        catch (PDOException $e) {
            echo $e->getMessage();
            return null;
        }
    }
    
    function Ejecutar($strsql){
        $ejecutar=$this->conectar()->prepare($strsql);
        $ejecutar->execute();
        return $ejecutar;
    }
}
?>
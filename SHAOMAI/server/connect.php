<?php
    $host = "localhost";
    $username = "root";
    $password = "":
    $database = "shaomai";

    $con = mysqli_connect($host, $usernamem $password, $database);
    
    if(mysqli_connect_errno()){
        echo "Database COnnect Failed:".mysqli_connect_erro();
        exit();
    }

    mysqli_query($con,"SET NAMES utf8");

?>
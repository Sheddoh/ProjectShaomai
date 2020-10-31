<?php

    include("connect.php");
    $json_return = array();
    $sql = "SELECT * FROM product ORDER BY product_price_rent DESC";
    $result = mysqli_query($con, $sql);
    while($row = mysqli_fetch_assoc($result)){
        array_push($json_return,array(
            "product_name"=> $row["product_name"],
            "product_price_rent"=> $row["product_price_rent"],
            "product_img"=> $row["product_img"],
            "product_status"=>$row["product_status"]));
    }

    print json_encode($json_return);

    mysqli_close($con);

?>

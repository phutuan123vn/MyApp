<?php
    $server="localhost";
    $username="root";
    $DB="android";
    $conn= new mysqli($server,$username,"",$DB);
    if($conn -> connect_error){
        die ("Connection error: ".$conn -> connect_error);
    }else{
        echo "Connection Success \n";
    }
?>
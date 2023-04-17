<?php
    $server="localhost";
    $username="root";
    $DB="android";
    $conn= new mysqli($server,$username,"",$DB);
    $ID=$_POST['ID'];
    $pass=$_POST['PASSWORD'];
    $query="UPDATE user SET `PASSWORD` = '$pass' WHERE ID = '$ID' " ;
     if($conn ->query($query)===true ){
          echo json_encode("Change Password Success");
     }else{
          echo json_encode("Change Password Fail");
     }
     $conn->close();
?>
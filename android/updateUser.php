<?php
    include 'connect.php';
    $email=$_POST['EMAIL'];
    $email="phutuan123vn@gmail.com";
    $query="UPDATE user SET ROLE = '0' WHERE EMAIL = '$email' ";
    if($conn ->query($query)===true ){
        echo json_encode("Success");
   }else{
        echo json_encode("Fail");
   }
   $conn->close();
?>
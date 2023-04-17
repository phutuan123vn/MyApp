<?php
    include 'connect.php';
    $id=$_POST['ID'];
    $phone=$_POST['PHONE'];
    $address=$_POST['ADDRESS'];
    $pid=$_POST['PID'];
    $query="UPDATE stuinfo SET PHONE='$phone',ADDRESS='$address',PERSON_ID='$pid' WHERE USER_ID='$id' ";
    if($conn ->query($query)===true ){
        echo json_encode("Success");
   }else{
        echo json_encode("Fail");
   }
   $conn->close();
?>
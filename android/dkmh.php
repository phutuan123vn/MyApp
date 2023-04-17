<?php
    include 'connect.php';
    $id=$_POST['ID'];
    $sid=$_POST['SID'];
    $query="INSERT INTO `dkmh`(`USER_ID`, `SUB_ID`) SELECT '$id','$sid' FROM DUAL WHERE NOT EXISTS (SELECT * FROM dkmh WHERE USER_ID= '$id' AND SUB_ID = '$sid')";
    if($conn->query($query)===true){
        echo json_encode("Success");
    }else{
        echo json_encode("Fail");
    }
    $conn->close();
?>
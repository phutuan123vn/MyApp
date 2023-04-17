<?php
     include 'connect.php';
     $sid=$_GET['subid'];
     $id=$_GET['id'];
     $query="UPDATE diemdanh SET ATTEND= '1' WHERE USER_ID='$id' AND SUB_ID = '$sid' ";
     if($conn ->query($query)===true ){
          echo json_encode("Success");
     }else{
          echo json_encode("error occured");
     }
     $conn->close();
?>
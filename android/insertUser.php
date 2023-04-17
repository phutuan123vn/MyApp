<?php 
     include 'connect.php';
     $lname=$_POST['LAST_NAME'];
     $fname=$_POST['FIRST_NAME'];
     $email=$_POST['EMAIL'];
     $pass=$_POST['PASS'];
     $role=$_POST['ROLEID'];
     $query="INSERT INTO user VALUES ('', '$lname', '$fname', '$email', '$pass', '$role')";
     if($conn ->query($query)===true ){
          echo json_encode("Data inserted");
     }else{
          echo json_encode("error occured");
     }
     $conn->close();
?>
<?php
    $conn=mysqli_connect('localhost','root','','android');
    $email=$_POST['EMAIL'];
    // $email="sinhvien";
    $query = "SELECT ID, PASSWORD, ROLE FROM user WHERE EMAIL = '$email' ";
    $results = mysqli_query($conn,$query);
    $val = array();
    if($conn -> connect_error){
        die ("Connection error: ".$conn -> connect_error);
    }else{
        if( mysqli_num_rows($results)>0){
            while($res = mysqli_fetch_assoc($results)){
                $val['response']= "Success";
                $val['data'][] = $res;
                
            }
            echo json_encode($val);
        }else{
            $val['response'] = "Fail";
            echo json_encode($val);
        }
    }
    
    
?>
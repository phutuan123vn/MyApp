<?php
    $conn=mysqli_connect('localhost','root','','android');
    $query = "SELECT `ID`,`LAST_NAME`,`FIRST_NAME`,CONCAT(CAREER,CODE) FROM `user` LEFT JOIN teachinfo ON user.ID=teachinfo.USER_ID WHERE ID>3 AND ROLE ='1'";
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
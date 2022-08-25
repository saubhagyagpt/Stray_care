<?php
    $con=mysqli_connect("localhost","id18679271_straycaredb","qtp)595*ai\H+%e","id18679271_straycare");
      $doc_id=(int)$_POST["doc_id"];
    
 $result=array();
 $result['data']=array();
 $select="SELECT * FROM SEND_INJUREDPET INNER JOIN USER ON USER.US_ID=SEND_INJUREDPET.US_ID WHERE DOC_ID='$doc_id' ";
 $response=mysqli_query($con,$select);
while($row=mysqli_fetch_array($response)){
      
    $index['animaltype']=$row['3'];
     $index['location']=$row['4'];
    $index['name']=$row['7'];
     $index['email']=$row['8'];
    $index['mobile']=$row['9'];
     
     array_push($result['data'],$index);
}
$result["success"]="1";
echo json_encode($result);
mysqli_close($connection);
?>

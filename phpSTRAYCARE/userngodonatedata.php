<?php
    $con=mysqli_connect("localhost","id18679271_straycaredb","qtp)595*ai\H+%e","id18679271_straycare");
 $result=array();
 $result['data']=array();
 $select="SELECT * FROM NGO";
 $response=mysqli_query($con,$select);
while($row=mysqli_fetch_array($response)){
       $index['ngo_id']=$row['0'];
    $index['name']=$row['1'];
     $index['email']=$row['2'];
         $index['mobile']=$row['3'];
     $index['address']=$row['4'];
     array_push($result['data'],$index);
}
$result["success"]="1";
echo json_encode($result);
mysqli_close($connection);
?>
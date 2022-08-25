<?php
    $con=mysqli_connect("localhost","id18679271_straycaredb","qtp)595*ai\H+%e","id18679271_straycare");
  $NAME=$_POST["name"];
    $EMAIL=$_POST["email"];
  $MOBILE=$_POST["mobile"];
  $ADDRESS= $_POST["address"];
$sql="INSERT INTO USER(NAME,EMAIL,MOBILE,ADDRESS) VALUES ('$NAME','$EMAIL','$MOBILE','$ADDRESS')";
$result=mysqli_query($con,$sql);
if($result){
echo "registered succesfully";
} 
else{
echo "some error";
}
?>
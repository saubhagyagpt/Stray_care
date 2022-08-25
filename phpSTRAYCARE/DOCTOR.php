<?php
    $con=mysqli_connect("localhost","id18679271_straycaredb","qtp)595*ai\H+%e","id18679271_straycare");
  $name=$_POST["name"];
    $email=$_POST["email"];
  $mobile=$_POST["mobile"];
  $address= $_POST["address"];
$sql="INSERT INTO DOCTOR(NAME,EMAIL,MOBILE,ADDRESS) VALUES ('$name','$email','$mobile','$address')";
$result=mysqli_query($con,$sql);
if($result){
echo "registered succesfully";
} 
else{
echo "some error";
}
?>
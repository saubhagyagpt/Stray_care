<?php
    $con=mysqli_connect("localhost","id18679271_straycaredb","qtp)595*ai\H+%e","id18679271_straycare");
  $email=$_POST["email"];
    $mobile=$_POST["mobile"];
;
$sql="SELECT * FROM DOCTOR WHERE EMAIL='$email' AND MOBILE='$mobile'  ";
$result=mysqli_query($con,$sql);
if($result->num_rows>0){
echo "logged in succesfully";
} 
else{
echo "user not found";
}
?>
<?php
require_once("database.php");
$vehicle_no=$_POST['vehicle_no'];
$name=$_POST['name'];
$mobile_no=$_POST['mobile_no'];
$email=$_POST['email'];
$password=$_POST['password'];


$sql="insert into users(vehicle_no,name,mobile_no,email_id,password) values('$vehicle_no','$name','$mobile_no','$email','$password')";
$result=mysqli_query($con,$sql);
if($result>0)
{
$response['success']=1;
}
else
{
	$response['success']=0;
}
echo json_encode($response);

?>
<?php
require_once("database.php");
$vehicle_no=$_POST['vehicle_no'];
$password=$_POST['password'];
$sql="select vehicle_no,password from users where vehicle_no='$vehicle_no' and password='$password'";
$result=mysqli_query($con,$sql);
if(mysqli_fetch_array($result))
{
	$response["success"]=1;
}
else
{
	$response["success"]=0;
}
echo json_encode($response);

?>
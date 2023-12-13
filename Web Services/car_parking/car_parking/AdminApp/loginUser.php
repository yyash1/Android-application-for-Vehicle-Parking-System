<?php
require_once("database.php");
$username=$_POST['username'];
$password=$_POST['password'];
$sql="select username,password from admin_login where username='$username' and password='$password'";
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
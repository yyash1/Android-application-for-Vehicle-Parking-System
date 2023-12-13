<?php
require_once("database.php");
$watchmen_id=$_POST['watchmen_id'];
$watchmen_password=$_POST['watchmen_password'];
$sql="select watchmen_id,watchmen_password from watchmen where watchmen_id='$watchmen_id' and watchmen_password='$watchmen_password'";
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
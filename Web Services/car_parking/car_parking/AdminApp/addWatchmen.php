<?php
require_once("database.php");
$watchmen_id=$_POST['watchmen_id'];
$watchmen_name=$_POST['watchmen_name'];
$watchmen_mobile_no=$_POST['watchmen_mobile_no'];
$watchmen_password=$_POST['watchmen_password'];


$sql="insert into watchmen(watchmen_id,watchmen_name,watchmen_mobile_no,watchmen_password) values('$watchmen_id','$watchmen_name','$watchmen_mobile_no','$watchmen_password')";
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
<?php
require_once("database.php");

$id=$_POST['id'];

$sql="delete from parking_booking where id='$id'";
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
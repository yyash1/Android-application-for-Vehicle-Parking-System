<?php
require_once("database.php");
$plot_name=$_POST['plot_name'];
$slot_name=$_POST['slot_name'];
$number_of_slot=$_POST['number_of_slot'];



$sql="insert into car_slot(plot_name,slot_name,number_of_slot) values('$plot_name','$slot_name','$number_of_slot')";
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
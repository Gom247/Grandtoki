<?php 

include "connection.php";

if ($_SERVER["REQUEST_METHOD"] == "POST") {

	$db = new Connection();
	$respone = array("error" => false);
		
	$email = $_POST['email'];
	$password = $_POST['password'];

	$user = $db->LoginUser($email, $password);

	if ($user) {
			
		$respone["error"] = false;
		$respone["message"] = " Login berhasil " .$email;
		echo json_encode($respone);
	} else {
		$respone["error"] = true;
		$respone["message"] = "Login gagal";
		echo json_encode($respone);
	}
	
} else {
	$respone["error"] = true;
	$respone["message"] = "maintaince";
	echo json_encode($respone);
}

?>
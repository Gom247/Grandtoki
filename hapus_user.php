<?php 

include 'connection.php';

if ($_SERVER["REQUEST_METHOD"] == "POST") {

	$db = new Connection();
	$respone = array("error" => false);
	
	$email = $_POST['email'];

	$user = $db->DeleteUser($email);

	if ($user) {
		$respone["error"] = false;
		$respone["message"] = "Delete user berhasil";
		echo json_encode($respone);
	} else {
		$respone["error"] = true;
		$respone["message"] = "Delete user gagal";
		echo json_encode($respone);
	}
} else {
	$respone["error"] = true;
	$respone["message"] = "maintaince";
	echo json_encode($respone);
}
?>
<?php 

include "connection.php";

if ($_SERVER["REQUEST_METHOD"] == "POST") {

	$db = new Connection();
	$respone_user = array("error" => false);

	$email = $_POST['email'];

	$user = $db->LihatUser($email);

	if ($user) {
		$respone_user["error"] = false;
		$respone_user["email"] = $user["email"];
		$respone_user["photo"] = $user["photo"];
		$respone_user["nama"] = $user["nama"];
		$respone_user["alamat"] = $user["alamat"];
		$respone_user["notlp"] = $user["notlp"];
		echo json_encode($respone_user);
	} else {
		$respone_user["error"] = true;
		$respone_user["message"] = "Gagal mendapatkan data";
		echo json_encode($respone_user);
	}
} else {
	$respone_user["error"] = true;
	$respone_user["message"] = "maintaince";
	echo json_encode($respone_user);
}
?>
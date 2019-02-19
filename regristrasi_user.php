<?php 

include "connection.php";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
	
	$db = new Connection();
	$respone = array("error" => false);
		
	$email = $_POST['email'];
	$password = $_POST['password'];
	$photo = $_POST['photo'];
	$nama = $_POST['nama'];
	$alamat = $_POST['alamat'];
	$notlp = $_POST['notlp'];

	if ($db->CekEmail($email)) {
		$respone["error"] = true;
		$respone["message"] = "Email sudah digunakan " .$email;
		echo json_encode($respone);
	} else {
		$user = $db->TambahUser($email, $password, $photo, $nama, $alamat, $notlp);
		if ($user) {
			$respone["error"] = false;
			$respone["message"] = "Registrasi berhasil " .$email;
			echo json_encode($respone);
		} else {
			$respone["error"] = true;
			$respone["message"] = "Registrasi gagal";
			echo json_encode($respone);
		}
	}
} else {
	$respone["error"] = true;
	$respone["message"] = "maintaince";
	echo json_encode($respone);
}

?>
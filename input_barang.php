<?php 

include 'connection.php';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
	
	$db = new Connection();
	$respone = array("error" => false);

	$kode_barang = $_POST['kode_barang'];
	$nama_barang = $_POST['nama_barang'];
	$jenis_barang = $_POST['jenis_barang'];
	$produk_barang = $_POST['produk_barang'];
	$harga_barang = $_POST['harga_barang'];

	if ($db->CekKodeBarang($kode_barang)) {

		$respone["error"] = true;
		$respone["message"] = "Kode barang sudah digunakan " .$kode_barang;
		echo json_encode($respone);

	} else {

		$barang = $db->TambahBarang($kode_barang, $nama_barang, $jenis_barang, $produk_barang, $harga_barang);
		if ($barang) {
			$respone["error"] = false;
			$respone["message"] = "Kode barang berhasil ditambah " .$kode_barang;
			echo json_encode($respone);
		} else {
			$respone["error"] = true;
			$respone["massage"] = "kode barang gagal ditambah";
		}

	}
} else {
	$respone["error"] = true;
	$respone["message"] = "maintaince";
	echo json_encode($respone);
}

?>
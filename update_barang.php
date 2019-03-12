<?php 

include "connection.php";

if ($_SERVER["REQUEST_METHOD"] == "POST") {

	$db = new Connection();
	$respone = array("error" => false);
	
	$kode_barang = $_POST['kode_barang'];
	$nama_barang = $_POST['nama_barang'];
	$jenis_barang = $_POST['jenis_barang'];
	$produk_barang = $_POST['produk_barang'];
	$harga_barang = $_POST['harga_barang'];

	if ($db->UpdateBarang($kode_barang, $nama_barang, $jenis_barang, $produk_barang, $harga_barang)) {

		$respone["error"] = false;
		$respone["message"] = "Kode barang berhasil diupdate " .$kode_barang;
		echo json_encode($respone);
	} else {
		$respone["error"] = true;
		$respone["message"] = "kode barang gagal diupdate";
		echo json_encode($respone);
	}

} else {
	$respone["error"] = true;
	$respone["message"] = "maintaince";
	echo json_encode($respone);
}

?>
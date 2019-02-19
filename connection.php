<?php 

/**
* 
*/
class Connection
{
	private $conn;

	function __construct()
	{
		$this->conn = new PDO('mysql:host=localhost;dbname=pegasus', "root", "");
	}

	public function LoginUser($email, $password) 
	{
		$encript = md5($password);

		$sql = "SELECT * FROM user WHERE email = :email AND password = :password";
		$data = $this->conn->prepare($sql);
		$data->bindParam(":email", $email);
		$data->bindParam(":password", $encript);
		$data->execute();
		$user = $data->fetch();

		return $user;

	}

	public function TambahUser($email, $password, $photo, $nama, $alamat, $notlp)
	{
		$encript = md5($password);

		$sql = "INSERT INTO user (email, password, photo, nama, alamat, notlp) VALUES (:email, :password, :photo, :nama, :alamat, :notlp)";
		$data = $this->conn->prepare($sql);
		$data->bindParam(':email', $email);
		$data->bindParam(':password', $encript);
		$data->bindParam(':photo', $photo);
		$data->bindParam(':nama', $nama);
		$data->bindParam(':alamat', $alamat);
		$data->bindParam(':notlp', $notlp);
		$user = $data->execute();

		return $user;
	}

	public function CekEmail($email)
	{
		$query = "SELECT email FROM user WHERE email = :email";
        $data = $this->conn->prepare($query);
        $data->bindParam(':email', $email);
        $data->execute();
        $data->fetch();
 
        if ($data->rowCount() > 0) {
            // user telah ada 
            $data = null;
            return true;
        } else {
            // user belum ada 
            $data = null;
            return false;
        }
	}

	public function UpdateUser($email, $photo, $nama, $alamat, $notlp)
	{
		$sql = "UPDATE user SET email = :email, photo = :photo, nama = :nama, alamat = :alamat, notlp = :notlp WHERE email = :email";
		$data = $this->conn->prepare($sql);
		$data->bindParam(':email', $email);
		$data->bindParam(':photo', $photo);
		$data->bindParam(':nama', $nama);
		$data->bindParam(':alamat', $alamat);
		$data->bindParam(':notlp', $notlp);
		$user = $data->execute();

		return $user;
	}

	public function LihatUser($email)
	{
		$sql = "SELECT * FROM user WHERE email = :email";
		$data = $this->conn->prepare($sql);
		$data->bindParam(':email', $email);
		$data->execute();
		$user = $data->fetch();

		return $user;
	}

	public function DeleteUser($email)
	{
		$sql = "DELETE FROM user WHERE email = :email ";
		$data = $this->conn->prepare($sql);
		$data->bindParam(':email', $email);
		$user = $data->execute();

		return $user;
	}

	public function LihatMenu()
	{
		$sql = "SELECT * FROM menu";
		$data = $this->conn->prepare($sql);
		$data->execute();
		$user = $data->fetchAll();

		return $user;
	}

	public function TambahBarang($kode_barang, $nama_barang, $jenis_barang, $produk_barang, $harga_barang)
	{
		$sql = "INSERT INTO barang(kode_barang, nama_barang, jenis_barang, produk_barang, harga_barang) VALUES (:kode_barang, nama_barang, jenis_barang, produk_barang, harga_barang)";
		$data = $this->conn->prepare($sql);
		$data->bindParam(':kode_barang', $kode_barang);
		$data->bindParam(':nama_barang', $nama_barang);
		$data->bindParam(':jenis_barang', $jenis_barang);
		$data->bindParam(':produk_barang', $produk_barang);
		$data->bindParam(':harga_barang', $harga_barang);
		$barang = $data->execute();

		return $barang;

	}

	public function CekKodeBarang($kode_barang)
	{
		$sql = "SELECT kode_barang FROM barang WHERE kode_barang = :kode_barang";
		$data = $this->conn->prepare($sql);
		$data->bindParam(':kode_barang', $kode_barang);
		$data->execute();
		$data->fetch();

		if ($data->rowCount() > 0) 
		{
			$data = null;
			return TRUE;
		} else {
			$data = null;
			return FALSE;
		}
	}

	public function UpdateBarang($kode_barang, $nama_barang, $jenis_barang, $produk_barang, $harga_barang)
	{
		$sql = "UPDATE barang SET kode_barang = :kode_barang, nama_barang = :nama_barang, jenis_barang = :jenis_barang, produk_barang = :produk_barang, harga_barang = :harga_barang WHERE kode_barang = :kode_barang";
		$data = $this->conn->prepare($sql);
		$data->bindParam(':kode_barang', $kode_barang);
		$data->bindParam(':nama_barang', $nama_barang);
		$data->bindParam(':jenis_barang', $jenis_barang);
		$data->bindParam(':produk_barang', $produk_barang);
		$data->bindParam(':harga_barang', $harga_barang);
		$barang = $data->execute();

		return $barang;
	}

	public function LihatJenisBarang($jenis_barang)
	{
		$sql = "SELECT * FROM barang WHERE jenis_barang = :jenis_barang";
		$data = $this->conn->prepare($sql);
		$data->bindParam(':jenis_barang', $jenis_barang);
		$data->execute();
		$barang = $data->fetchAll();

		return $barang;

	}

	public function LihatBarang()
	{
		$sql = "SELECT * FROM barang";
		$data = $this->conn->prepare($sql);
		$data->execute();
		$barang = $data->fetchAll();

		return $barang;
	}

	public function DeleteBarang($kode_barang)
	{
		$sql = "DELETE FROM barang WHERE kode_barang = :kode_barang";
		$data = $this->conn->prepare($data);
		$data->bindParam(':kode_barang', $kode_barang);
		$barang = $data->execute();

		return $barang;
	}
}

?>
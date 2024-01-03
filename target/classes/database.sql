CREATE DATABASE QuanLyCuaHang;
use QuanLyCuaHang;
CREATE TABLE TaiKhoan(
	IDNhanVien VARCHAR(100) PRIMARY KEY NOT NULL,
    Username VARCHAR(100),
    Password VARCHAR(100),
    isAdmin BOOLEAN DEFAULT FALSE
);
CREATE TABLE ThongTinNhanVien(
	IDNhanVien VARCHAR(100) NOT NULL,
    HoVaTen NVARCHAR(200),
    GioiTinh BOOLEAN,
    NgayThangNamSinh DATE,
    SDT VARCHAR(12),
    QueQuan VARCHAR(255),
    FOREIGN KEY (IDNhanVien) REFERENCES TaiKhoan(IDNhanVien)
);
INSERT INTO taikhoan() VALUES(0, 'admin', 'admin', true);
INSERT INTO thongtinnhanvien() VALUES(0, 'admin', true, '2024-1-1', '123', 'VN');
CREATE TABLE HangHoa(
	IDHangHoa VARCHAR(20) PRIMARY KEY NOT NULL,
    TenHangHoa VARCHAR(255),
    SoLuong INT,
    DonGia INT
);
CREATE TABLE HoaDon(
	IDHoaDon VARCHAR(20) PRIMARY KEY,
    IDNhanVien VARCHAR(100) NOT NULL,
    ThoiGian DATETIME,
    GiamGia INT,
    TongTien INT,
    FOREIGN KEY (IDNhanVien) REFERENCES TaiKhoan(IDNhanVien)
);
CREATE TABLE ChiTietHoaDon(
	IDHoaDon VARCHAR(20) NOT NULL,
    IDHangHoa VARCHAR(20) NOT NULL,
    SoLuong INT,
    FOREIGN KEY (IDHoaDon) REFERENCES HoaDon(IDHoaDon),
    FOREIGN KEY (IDHangHoa) REFERENCES HangHoa(IDHangHoa)	
);
CREATE TABLE NhapHang(
	IDNhapHang VARCHAR(20) PRIMARY KEY,
    IDNhanVien VARCHAR(100) NOT NULL,
    ThoiGian DATETIME,
    TongTien INT,
    FOREIGN KEY (IDNhanVien) REFERENCES TaiKhoan(IDNhanVien)
);
CREATE TABLE ChiTietNhapHang(
	IDNhapHang VARCHAR(20) NOT NULL,
    IDHangHoa VARCHAR(20) NOT NULL,
    SoLuong INT,
    FOREIGN KEY (IDNhapHang) REFERENCES NhapHang(IDNhapHang),
    FOREIGN KEY (IDHangHoa) REFERENCES HangHoa(IDHangHoa)	
);
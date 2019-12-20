package sample.database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;


public class SQLiteJDBC {

    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header
    private static final String FILE_HEADER = "date, customerid, customername, branch, branchcode, password";

    public static void createNewDatabase() {

        File file = new File("C:/winDairy");
        if (!file.exists())
            file.mkdir();

        File logFile = new File("C:/winDairy/log.csv");
        if(!logFile.exists()) {
            try {
                logFile.createNewFile();
//                FileWriter fileWriter = null;
//
//                fileWriter = new FileWriter(logFile, true);
//
//                //Write the CSV file header
//                fileWriter.append(FILE_HEADER);
//
//                //Add a new line separator after the header
//                fileWriter.append(NEW_LINE_SEPARATOR);

                System.out.println("CSV file was created successfully !!!");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String url = "jdbc:sqlite:C:/winDairy/base.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createNewTables() {

        createItemTable();
        createMemberTable();
        createRateGrpMasterTable();
        createRateMasterTable();
        createUserTable();
    }

    private static void createMemberTable() {

        String url = "jdbc:sqlite:C:/winDairy/base.db";

        String sql = "CREATE TABLE IF NOT EXISTS `member` ( `memb_code` INTEGER UNIQUE, `memb_name` TEXT, `zoon_code` INTEGER, `Cobf_type` INTEGER, `memb_type` INTEGER, `accno` INTEGER, `rategrno` INTEGER DEFAULT 0, `bank_code` INTEGER DEFAULT 0, `BankAcNo` TEXT, `membNam_Eng` TEXT, `AcNo` INTEGER, PRIMARY KEY(`memb_code`) )";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createRateMasterTable() {

        String url = "jdbc:sqlite:C:/winDairy/base.db";

        String sql = "CREATE TABLE IF NOT EXISTS `ratemst` ( `rtgrno` INTEGER, `RtDate` NUMERIC, `cobf` TEXT, `rtno` INTEGER UNIQUE, `fat` REAL, `rate` REAL, `degree` REAL, `snf` REAL )";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createRateGrpMasterTable() {

        String url = "jdbc:sqlite:C:/winDairy/base.db";

        String sql = "CREATE TABLE IF NOT EXISTS `Rt_grmst` ( `RateGrno` INTEGER DEFAULT 0 UNIQUE, `RateGrname` TEXT, `RateTyp` INTEGER DEFAULT 0, `CowRate` REAL DEFAULT 0, `BufRate` REAL DEFAULT 0, PRIMARY KEY(`RateGrno`) )";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createItemTable() {

        String url = "jdbc:sqlite:C:/winDairy/base.db";

        String sql = "CREATE TABLE IF NOT EXISTS `item` ( `itcode` INTEGER UNIQUE, `itname` TEXT, `opqty` INTEGER, `oprate` REAL, `opbal` REAL, `issue` TEXT, `rece` TEXT, `clqty` INTEGER, `rate` REAL, `purchAc` TEXT, `saleAc` TEXT, `saleCr` REAL )";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createUserTable() {

        String url = "jdbc:sqlite:C:/winDairy/base.db";

        String sql = "CREATE TABLE `customer` ( `customerid` INTEGER UNIQUE, `customername` TEXT, `branch` TEXT, `branchcode` INTEGER, `password` TEXT )";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int insert(String id, String name, String branch, String branchCode, String password) throws NoSuchAlgorithmException {

        String url = "jdbc:sqlite:C:/winDairy/base.db";

        String sql = "INSERT INTO customer (customerid, customername, branch, branchcode, password) VALUES(?,?,?,?,?)";


//        String pattern = "dd-MM-yyyy";
//        String dateInString =new SimpleDateFormat(pattern).format(System.currentTimeMillis());

        //ID
        byte[] bytesID = new byte[0];
        try {
            bytesID = id.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        MessageDigest mdID = MessageDigest.getInstance("MD5");
        byte[] encID = mdID.digest(bytesID);

        //Name
//        byte[] bytesName = new byte[0];
//        try {
//            bytesName = name.getBytes("UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

//        MessageDigest mdName = MessageDigest.getInstance("MD5");
//        byte[] encName = mdName.digest(bytesName);

        //Branch
//        byte[] bytesBranch = new byte[0];
//        try {
//            bytesBranch = branch.getBytes("UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

//        MessageDigest mdBranch = MessageDigest.getInstance("MD5");
//        byte[] encBranch = mdBranch.digest(bytesBranch);

        //BranchCode
//        byte[] bytesBranchCode = new byte[0];
//        try {
//            bytesBranchCode = branchCode.getBytes("UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

//        MessageDigest mdBranchCode = MessageDigest.getInstance("MD5");
//        byte[] encBranchCode = mdBranchCode.digest(bytesBranchCode);

        //Password
        byte[] bytesPassword = new byte[0];
        try {
            bytesPassword = password.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        MessageDigest mdPassword = MessageDigest.getInstance("MD5");
        byte[] encPassword = mdPassword.digest(bytesPassword);

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBytes(1, encID);
            pstmt.setString(2, name);
            pstmt.setString(3, branch);
            pstmt.setString(4, branchCode);
            pstmt.setBytes(5, encPassword);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
        return 0;
    }
}

package sample.main;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import sample.database.SQLiteJDBC;
import sample.utils.AlertMaker;
import sample.utils.CSVWriter;
import sample.utils.Util;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField branch;

    @FXML
    private JFXTextField branchCode;

    @FXML
    private JFXTextField password;

    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane mainContainer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        new Thread(() -> {
            SQLiteJDBC.createNewDatabase();
            SQLiteJDBC.createNewTables();
        }).start();
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void createDB(ActionEvent event) {

        String mID = StringUtils.trimToEmpty(id.getText());
        String mName = StringUtils.trimToEmpty(name.getText());
        String mBranchCode = StringUtils.trimToEmpty(branchCode.getText());
        String mBranch = StringUtils.trimToEmpty(branch.getText());
        String mPassword = StringUtils.trimToEmpty(password.getText());

        boolean flag = mName.isEmpty() || mID.isEmpty() || mBranch.isEmpty() || mBranchCode.isEmpty() || mPassword.isEmpty();
        if (flag) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter data in all fields.");
            return;
        }
        SQLiteJDBC db = new SQLiteJDBC();

        int res = -1;
        try {
            res = db.insert(mID, mName, mBranch, mBranchCode, mPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (res == 0) {
            AlertMaker.showSimpleAlert("Success", "Database created Successfully.\n Please check C:/winDairy/ folder.");

            Long startTime = System.currentTimeMillis();
            String date = Util.formatDateTimeString(startTime);
            CSVWriter.writeCsvFile(date, mID, mName, mBranch, mBranchCode, mPassword);
            System.exit(0);
        } else {
            AlertMaker.showErrorMessage("Failed", "Couldn't create the database.\n Please try again later.");
        }
    }
}

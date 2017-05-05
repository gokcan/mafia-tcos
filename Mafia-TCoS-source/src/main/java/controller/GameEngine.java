package controller;

import Model.Crime;
import Model.Player;
import Network.AccessManager;
import Network.ErrorType;
import View.CrimeScene;

import View.ViewController;
import View.WelcomeScene;
import javafx.application.Platform;
import model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by SKYLIFE on 30/04/2017.
 */
public class GameEngine implements AccessManager.AccessClientListener {

    /*
     WORK-IN-PROGRESS...
     */

    private static GameEngine staticGameInstance;
    private AccessManager accessManager;
    private WelcomeScene welcomeScene;
    private CrimeScene crimeScene;
    private static Player playerUnique;
    private ViewController viewController;
    private HashMap<Integer, List<Crime>> mapper;
    private static ArrayList<Crime> crimes;
    public String description;


    public void init() {
        staticGameInstance = this;
        accessManager = new AccessManager();
        accessManager.setCustomListener(this);
        welcomeScene = new WelcomeScene();
        crimeScene = new CrimeScene();
        mapper = new HashMap();
        //Crime crime1 = new Crime("Hey this is test1", "21212", 2);
        //Crime crime2 = new Crime("This is test2", "3232323", 2);
    }

    @Override
    public void onLoginFailure(String response) {

        Platform.runLater(() -> {
             welcomeScene.showNotification(response);
        });
    }

    @Override
    public void onFetchPlayerSuccess(Player player) {
        playerUnique = player;

    }

    @Override
    public void onFetchCrimesSuccess(ArrayList<Crime> crimeArrayList) {

        populateDataForCrimeList(crimeArrayList);
        mapper.put(1, crimes);
        Crime crime = mapper.get(1).get(0);
        description = crime.getDescription();

    }
    @Override
    public void onFetchPlayerFailure(String response) {
        //FAIL TO FETCH PLAYER

    }

    public boolean isSuccessful(Boolean result, Player player) {

        // TODO : Implement a solid formula for calculating the success chance via another method.
        
        if (result) {
            Platform.runLater(() -> {
                crimeScene.showNotification("Congrats you did it!");
            });
            return true;
        }

        return false;
    }

    @Override
    public void onSignupFailure(String response) {

        Platform.runLater(() -> {
            welcomeScene.showNotification(response);
        });
    }

    @Override
    public void onLoginSuccess(String response) {

        viewController = new ViewController();
        Platform.runLater(() -> {
            viewController.changeView();
        });

        accessManager.callGetPlayerData();
        accessManager.callGetCrimes();
    }

    @Override
    public void onSignupSuccess(String response) {

        Platform.runLater(() -> {
            welcomeScene.showNotification(response);
        });

    }

    public AccessManager getAccessManager() {

        return accessManager;
    }

    public static ArrayList<Crime> getCrimes() {
        return crimes;
    }

    public static Player getPlayerUnique() {
        return playerUnique;
    }

    public static GameEngine game() {
        return staticGameInstance;
    }


    private void populateDataForCrimeList(ArrayList<Crime> crimeArrayList) {

         crimes = new ArrayList<>();

         /*
            Deep copy for an arraylist not a shallow one.
          */

        for (int i = 0; i < crimeArrayList.size(); i++) {
            Crime crime = new Crime();
            crime.setCrimeID(crimeArrayList.get(i).getCrimeID());
            crime.setDescription(crimeArrayList.get(i).getDescription());
            crime.setDifficultyLevel(crimeArrayList.get(i).getDifficultyLevel());

            crimes.add(crime);
        }

    }

}

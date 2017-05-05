package controller;

import Model.Crime;
import Model.Player;
import Network.AccessManager;
import Network.ErrorType;
import View.CrimeScene;

import View.ViewController;
import View.WelcomeScene;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Skylifee7 on 30/04/2017.
 */
public class GameEngine implements AccessManager.AccessClientListener {

    private static GameEngine staticGameInstance;
    private AccessManager accessManager;
    private WelcomeScene welcomeScene;
    private CrimeScene crimeScene;
    private static Player playerUnique;
    private ViewController viewController;
    private HashMap<Integer, List<Crime>> mapper;
    private ArrayList<Crime> crimes;
    private static boolean isSuccessful;
    private double successChance;
    private Random random;

    public String description;

    private GameEngine() {
        /*
        Game Engine will obey the Singleton Pattern, but not yet implemented.
         */
    }

    public void init() {
        staticGameInstance = this;
        accessManager = new AccessManager();
        accessManager.setCustomListener(this);
        viewController = new ViewController();
        welcomeScene = new WelcomeScene();
        crimeScene = new CrimeScene();
        mapper = new HashMap();
    }


    private void calcSuccessChance() {

        int min = (2 * playerUnique.getRank() + ((int) playerUnique.getHealth()) / 10);
        int max = ((playerUnique.getTotalExperience()) / (crimes.get(0).getDifficultyLevel() * 30));

        successChance = ThreadLocalRandom.current().nextInt(min, max);
    }

    public void isSuccessful(String information) {

        int index = information.indexOf("%");
        String threshold = information.substring(index + 1, information.length() - 1);

        float t = Float.parseFloat(threshold) / 100f;
        Random r = new Random();
        float chance = r.nextFloat();

        if (playerUnique.getHealth() > 0 && chance <= t) {

            Platform.runLater(() -> {
                crimeScene.showNotification("Congrats you did it! You earned +400 XP, +220 $ and lost 11 Health Points");
            });

            playerUnique.increment(Player.TYPE.EXPERIENCE, 400);
            playerUnique.increment(Player.TYPE.HEALTH, -11);
            playerUnique.increment(Player.TYPE.MONEY, 220);

            if (playerUnique.getTotalExperience() > 3000) {
                playerUnique.increment(Player.TYPE.RANK, 1);
            }
            isSuccessful = true;
        } else {
            Platform.runLater(() -> {
                crimeScene.showNotification("Failed! But you escaped..");
            });

            isSuccessful = false;
        }
        //else if PRISON CASE

        accessManager.callUpdateService(playerUnique);
        accessManager.callGetCrimes();
    }

    @Override
    public void onSignupSuccess(String response) {

        Platform.runLater(() -> {
            welcomeScene.showNotification(response);
        });

    }

    @Override
    public void onSignupFailure(String response) {

        Platform.runLater(() -> {
            welcomeScene.showNotification(response);
        });
    }

    @Override
    public void onLoginSuccess(String response) {

        accessManager.callGetPlayerData();
        accessManager.callGetCrimes();

        Platform.runLater(() -> {
            viewController.changeView();
        });
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
    public void onFetchPlayerFailure(String response) {

        Platform.runLater(() -> {
            crimeScene.showNotification(response);
        });
    }

    @Override
    public void onFetchCrimesSuccess(ArrayList<Crime> crimeArrayList) {

        accessManager.callGetPlayerData();
        int difficulty = playerUnique.getRank();

        populateDataForCrimeList(crimeArrayList);

        int i = 0;
        while (i < crimes.size()) {
            if (crimes.get(i).getDifficultyLevel() != difficulty) {
                crimes.remove(i);
            }
            i++;
        }

        for (int k = 0; k < crimes.size(); k++) {
            calcSuccessChance();
            crimes.get(k).setDescription(crimes.get(k).getDescription() + " (%" + successChance + ")");
        }
        //Crime value = mapper.get(1).get(0);
        // description = value.getDescription();
        // crimeScene.bindCrimes(crimes);
    }

    public AccessManager getAccessManager() {

        return accessManager;
    }

    public ArrayList<Crime> getCrimes() {

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

        for (int i = 0; i < crimeArrayList.size(); i++) {
            Crime crime = new Crime();
            crime.setCrimeID(crimeArrayList.get(i).getCrimeID());
            crime.setDescription(crimeArrayList.get(i).getDescription());
            crime.setDifficultyLevel(crimeArrayList.get(i).getDifficultyLevel());

            crimes.add(crime);
        }

    }

}

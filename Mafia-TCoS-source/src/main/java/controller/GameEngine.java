package controller;

import Model.Crime;
import Model.Player;
import Network.AccessManager;
import View.CrimeScene;

import View.ViewController;
import View.WelcomeScene;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
/*
    Game Engine v.0.2 - WorkInProgress -
 */
public class GameEngine implements AccessManager.AccessClientListener {

    private static GameEngine staticGameInstance;
    private AccessManager accessManager;
    private WelcomeScene welcomeScene;
    private CrimeScene crimeScene;
    private static Player playerUnique;
    private ViewController viewController;
    private HashMap<Enum, Integer> mapper;
    private ArrayList<Crime> crimes;
    private static boolean isSuccessful;
    private double successChance;
    private boolean isPropertyCrime = false;

    public GameEngine() {
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

        System.out.println(chance + " " + t);

        if (playerUnique.getHealth() > 0 && chance <= t) {

            int maxAmount = ((int)playerUnique.getHealth() + playerUnique.getTotalExperience() % 100);
            int minAmount = (int)playerUnique.getHealth() % 100;

            int amount = ThreadLocalRandom.current().nextInt(minAmount, maxAmount);
            playerUnique.increment(Player.PROPERTY_TYPE.EXPERIENCE, amount);
            mapper.put(AMOUNT_TYPE.EXPERIENCE,amount);

            amount = -(ThreadLocalRandom.current().nextInt(2, crimes.get(0).getDifficultyLevel() * 10));
            playerUnique.increment(Player.PROPERTY_TYPE.HEALTH, amount);
            mapper.put(AMOUNT_TYPE.HEALTH,amount);

            amount = ThreadLocalRandom.current().nextInt(minAmount, maxAmount);
            playerUnique.increment(Player.PROPERTY_TYPE.MONEY, amount);
            mapper.put(AMOUNT_TYPE.MONEY,amount);

            Platform.runLater(() -> {
                crimeScene.showNotification("Congrats You did it! You Earned " + mapper.get(AMOUNT_TYPE.EXPERIENCE) +" XP "+
                        ", +" + mapper.get(AMOUNT_TYPE.MONEY) + "$ and " +
                        "Lost " + mapper.get(AMOUNT_TYPE.HEALTH) + " Health Points");
            });

            if (playerUnique.getTotalExperience() > 3000) {
                playerUnique.increment(Player.PROPERTY_TYPE.RANK, 1);
            }

        } else if (playerUnique.getHealth()<=0) {

            Platform.runLater(() -> {
                crimeScene.showNotification("Rest in Peace..");
            });

        }
        else {
            Platform.runLater(() -> {
                crimeScene.showNotification("Failed! But you escaped..");
            });

            playerUnique.increment(Player.PROPERTY_TYPE.EXPERIENCE, 20);
            playerUnique.increment(Player.PROPERTY_TYPE.HEALTH, -2);
        }

        //else if PRISON

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
    public void onFetchCrimesSuccess(ArrayList<Crime> crimeArrayList) {

        accessManager.callGetPlayerData();

        int difficulty = playerUnique.getRank();

        populateDataForCrimeList(crimeArrayList);

        int i = 0;
        while (i < crimes.size()) {
            System.out.println(crimes.get(i).getisProperty());
            if ((crimes.get(i).getDifficultyLevel() != difficulty)){
                crimes.remove(i);
            }
            else i++;
        }

        if (!isPropertyCrime) {
            int j = 0;
            while (j < crimes.size()) {
                if ((Boolean.valueOf(crimes.get(j).getisProperty()))) {
                    crimes.remove(j);
                }
                else j++;
            }
        }

        else {
            int j = 0;
            while (j < crimes.size()) {
                if (!(Boolean.valueOf(crimes.get(j).getisProperty()))) {
                    crimes.remove(j);
                }
                else j++;
            }

        }

        for (int k = 0; k < crimes.size(); k++) {
            System.out.println(crimes.get(k).getDescription());
            calcSuccessChance();
            crimes.get(k).setDescription(crimes.get(k).getDescription() + " (%" + successChance + ")");
        }
    }

    @Override
    public void onFetchPlayerFailure(String response) {
        Platform.runLater(() -> {
            crimeScene.showNotification(response);
        });
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

    public void setIsPropertyCrime(boolean set){
        isPropertyCrime = set;
    }

    private void populateDataForCrimeList(ArrayList<Crime> crimeArrayList) {

        crimes = new ArrayList<>();

        for (int i = 0; i < crimeArrayList.size(); i++) {
            Crime crime = new Crime();
            crime.setCrimeID(crimeArrayList.get(i).getCrimeID());
            crime.setDescription(crimeArrayList.get(i).getDescription());
            crime.setDifficultyLevel(crimeArrayList.get(i).getDifficultyLevel());
            crime.setProperty(crimeArrayList.get(i).getisProperty());

            crimes.add(crime);
        }
    }

    private enum AMOUNT_TYPE {
        HEALTH, EXPERIENCE, MONEY
    }

}

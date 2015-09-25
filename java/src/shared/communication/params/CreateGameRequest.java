package shared.communication.params;

/**
 * Wrapper class used to pass parameters during the IServerProxy.createGame API
 * call
 *
 * { "randomTiles": "boolean", "randomNumbers": "boolean",
 * "randomPorts": "boolean", "name": "string" }
 */
public class CreateGameRequest {
    //boolean randomTiles - whether the tiles should be randomly placed
    //boolean randomNumbers - whether the numbers should be randomly placed
    //boolean randomPorts - whether ports should be randomly placed
    //String name - name of your new game

    boolean randomTiles;
    boolean randomNumbers;
    boolean randomPorts;
    String name;

    public boolean isRandomTiles() {
        return randomTiles;
    }

    public void setRandomTiles(boolean randomTiles) {
        this.randomTiles = randomTiles;
    }

    public boolean isRandomNumbers() {
        return randomNumbers;
    }

    public void setRandomNumbers(boolean randomNumbers) {
        this.randomNumbers = randomNumbers;
    }

    public boolean isRandomPorts() {
        return randomPorts;
    }

    public void setRandomPorts(boolean randomPorts) {
        this.randomPorts = randomPorts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

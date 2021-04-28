package smallGame;

public class GameCore {
	
	GameMap sessionMap;
	
	GameCore() {
		
	}
	
	private GameMap createSessionMap(int sizeX, int sizeY) {
		GameMap temp = new GameMap(sizeX, sizeY);
		for(int i = 0, j = 0; j < sizeY; i++) {
			
		}
		
		return temp;
	}
}

class GameMap {

	int sizeX, sizeY;
	GameField gameFields[][];
	
	GameMap(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		gameFields = new GameField[sizeX][sizeY];
	}
}

class GameField {
	
	int heights;
	GameFieldType type;
	
	GameField(GameFieldType type) {
		this.type = type;
	}
	
	public GameFieldType getGameFieldType() {
		
		return type;
	}
}
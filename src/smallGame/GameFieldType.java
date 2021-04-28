package smallGame;

public enum GameFieldType {
	
	grass("green"),
	rocks("lightGray"),
	river("blue"),
	sea("cyan"),
	forest("darkGreen"),
	desert("orangeOchra"),
	road("brownOchra");
	
	GameFieldType(String color) {
		this.color = color;
	}
	
	private String color;
	
	public String getColor() {return color;}
}

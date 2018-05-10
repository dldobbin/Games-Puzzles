package solver;

public class Block {
	private String description;
	private float[] color;
	private int rotated;
	private int[][][] block;
	private int[][][] primary;
	private int[] offset;

	public Block(String d, int[][][] b, float[] c) {
		description = d;
		block = b;
		primary = b;
		color = c;
		rotated = 0;
	}

	public int[][][] getBlock() {
		return block;
	}
	
	public float[] getColor() {
		return color;
	}
	
	public int[] getOffset() { return offset; }
	public void setOffset(int[] offset) { this.offset = offset; }

	public String toString() {
		return description + " rotated " + rotated + " times";
	}

	public void printBlock() {
		for (int i=0; i<block.length; i++) {
			for (int j=0; j<block[0].length; j++) {
				for (int k=0; k<block[0][0].length; k++) {
					System.out.print(block[i][j][k] + " ");
				}
				System.out.print("\n");
			}
			System.out.print("\n");
		}
	}

	public boolean rotate() {
		if (rotated >= 23)
			return false;

		if (rotated%4 != 3)
			rotateLeft();
		else if (rotated == 3 || rotated == 7 || rotated == 19)
			rotateUp();
		else
			rotateDown();

		rotated++;
		return true;
	}

	public void rotationReset() {
		block = primary;
		rotated = 0;
	}

	public void rotateLeft() {
		int[][][] newBlock = new int[block.length][block[0][0].length][block[0].length];
		for (int i=0; i<newBlock.length; i++) {
			for (int j=0; j<newBlock[0].length; j++) {
				for (int k=0; k<newBlock[0][0].length; k++) {
					newBlock[i][j][k] = block[i][block[0].length-1-k][j];
				}
			}
		}
		block = newBlock;
	}

	public void rotateUp() {
		int[][][] newBlock = new int[block[0].length][block.length][block[0][0].length];
		for (int i=0; i<newBlock.length; i++) {
			for (int j=0; j<newBlock[0].length; j++) {
				for (int k=0; k<newBlock[0][0].length; k++) {
					newBlock[i][j][k] = block[block.length-1-j][i][k];
				}
			}
		}
		block = newBlock;
	}

	public void rotateDown() {
		int[][][] newBlock = new int[block[0].length][block.length][block[0][0].length];
		for (int i=0; i<newBlock.length; i++) {
			for (int j=0; j<newBlock[0].length; j++) {
				for (int k=0; k<newBlock[0][0].length; k++) {
					newBlock[i][j][k] = block[j][block[0].length-1-i][k];
				}
			}
		}
		block = newBlock;
	}
}
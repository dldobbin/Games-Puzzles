package solver;

import java.util.LinkedList;

public class Response {
	private boolean success;
	private LinkedList<Block> result;

	public Response(boolean s) {
		success = s;
		result = new LinkedList<>();
	}

	public void setResult(Block b) {
		result.addFirst(b);
	}

	public LinkedList<Block> getResult() {
		return result;
	}

	public boolean isSuccess() {
		return success;
	}
}
package graphics;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.nio.FloatBuffer;
import java.util.HashMap;

import com.jogamp.opengl.util.PMVMatrix;



public class Controls extends KeyAdapter {
	private Component component;
	private double verticalAngle;
	private double horizontalAngle;
	private float speed;
	private double lastTime;
	
	private HashMap<Integer, Boolean> isPressed = new HashMap<>();
	
	public Controls(Component component) {
		this.component = component;
		component.addKeyListener(this);
		
		this.verticalAngle = 0;
		this.horizontalAngle = 0;
		this.speed = .1f;
		
		this.lastTime = System.currentTimeMillis();
	}
	
	public FloatBuffer getMatrix() {
		double currentTime = System.currentTimeMillis();
		double deltaTime = currentTime - lastTime;
		lastTime = currentTime;
		
		PMVMatrix matrix = new PMVMatrix();
		
		if (isPressed.getOrDefault(KeyEvent.VK_UP, false)) {
			verticalAngle -= speed*deltaTime;
		}
		
		if (isPressed.getOrDefault(KeyEvent.VK_DOWN, false)) {
			verticalAngle += speed*deltaTime;
		}
		
		if (isPressed.getOrDefault(KeyEvent.VK_RIGHT, false)) {
			horizontalAngle += speed*deltaTime;
		}
		
		if (isPressed.getOrDefault(KeyEvent.VK_LEFT, false)) {
			horizontalAngle -= speed*deltaTime;
		}
		
		matrix.gluPerspective(45f, (float)component.getWidth()/(float)component.getHeight(), .1f, 100f);
		matrix.gluLookAt(8, 6, 6, 0, 0, 0, 0, 1, 0);
		matrix.glRotatef((float)horizontalAngle, 0, 1, 0);
		matrix.glRotatef((float)verticalAngle, .5f, 0, .5f);
		
		return matrix.glGetMatrixf();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		isPressed.put(e.getKeyCode(), true);
		super.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		isPressed.put(e.getKeyCode(), false);
		super.keyReleased(e);
	}
	
	
}
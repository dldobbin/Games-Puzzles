package graphics;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import com.jogamp.opengl.GL3;
import com.jogamp.opengl.util.glsl.ShaderState;

public class Cube {
	static final float[] initial_vertex_data = new float[] {
			-2.0f, -2.0f,  -1.0f,
		    -2.0f, -1.0f, -1.0f,
		    -1.0f, -1.0f, -1.0f,
		    -1.0f, -2.0f, -1.0f,
		    -1.0f, -2.0f, -2.0f,
		    -1.0f, -1.0f, -2.0f,
		    -2.0f, -1.0f, -2.0f,
		    -2.0f, -2.0f, -2.0f
	};
	
	static final IntBuffer index_data = IntBuffer.wrap(new int[] {
			0, 2, 1,
			0, 3, 2,
			0, 1, 6,
			0, 6, 7,
			3, 4, 5,
			3, 5, 2,
			0, 4, 3,
			0, 7, 4,
			5, 7, 4,
			5, 6, 7,
			2, 5, 1,
			1, 5, 6
	});
	
	static final int VERTEX_BUFFER = 0;
	static final int COLOR_BUFFER = 1;
	static final int INDEX_BUFFER = 2;
	
	private GL3 gl;
	private ShaderState st;
	private int[] buffers;
	private FloatBuffer vertex_data;
	private FloatBuffer color_data;
	
	public Cube(GL3 gl, ShaderState st, float[] color, int[] offset) {
		this.gl = gl;
		this.st = st;
		
		float[] vd = initial_vertex_data.clone();
		for (int i=0; i<vd.length/3; i++) {
			vd[3*i] += offset[0];
			vd[3*i+1] += offset[1];
			vd[3*i+2] += offset[2];
		}
		vertex_data = FloatBuffer.wrap(vd);
		color_data = FloatBuffer.wrap(colors(color));
		
		this.buffers = new int[3];
		this.gl.glGenBuffers(3, this.buffers, 0);
		this.gl.glBindBuffer(GL3.GL_ARRAY_BUFFER, this.buffers[VERTEX_BUFFER]);
		this.gl.glBufferData(GL3.GL_ARRAY_BUFFER, 4*vertex_data.capacity(), vertex_data, GL3.GL_STATIC_DRAW);
		this.gl.glBindBuffer(GL3.GL_ARRAY_BUFFER, this.buffers[COLOR_BUFFER]);
		this.gl.glBufferData(GL3.GL_ARRAY_BUFFER, 4*color_data.capacity(), color_data, GL3.GL_STATIC_DRAW);
		this.gl.glBindBuffer(GL3.GL_ELEMENT_ARRAY_BUFFER, this.buffers[INDEX_BUFFER]);
		this.gl.glBufferData(GL3.GL_ELEMENT_ARRAY_BUFFER, 4*index_data.capacity(), index_data, GL3.GL_STATIC_DRAW);
	}
	
	public void draw() {
		int vertexPositionID = this.st.getAttribLocation(this.gl, "vertexPosition");
		int vertexColorID = this.st.getAttribLocation(this.gl, "vertexColor");
		gl.glEnableVertexAttribArray(vertexPositionID);
		gl.glEnableVertexAttribArray(vertexColorID);
		
		this.gl.glBindBuffer(GL3.GL_ARRAY_BUFFER, this.buffers[VERTEX_BUFFER]);
		this.gl.glVertexAttribPointer(vertexPositionID, 3, GL3.GL_FLOAT, false, 0, 0);
		this.gl.glBindBuffer(GL3.GL_ARRAY_BUFFER, this.buffers[COLOR_BUFFER]);
		this.gl.glVertexAttribPointer(vertexColorID, 3, GL3.GL_FLOAT, false, 0, 0);
		
		this.gl.glBindBuffer(GL3.GL_ELEMENT_ARRAY_BUFFER, this.buffers[INDEX_BUFFER]);

		gl.glDrawElements(GL3.GL_TRIANGLES, index_data.capacity(), GL3.GL_UNSIGNED_INT, 0);
		
		gl.glDisableVertexAttribArray(vertexPositionID);
		gl.glDisableVertexAttribArray(vertexColorID);
	}
	
	public float[] colors(float[] color) {
		float[] c = new float[vertex_data.capacity()];
		for (int i=0; i<c.length/3; i++) {
			c[3*i] = color[0];
			c[3*i+1] = color[1];
			c[3*i+2] = color[2];
		}
		return c;
	}
	
	public float[] getVertexData() {
		return vertex_data.array();
	}
}

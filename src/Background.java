import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Background {

	private Array<BackgroundLayer> mBackgroundLayers;
	private float mScreenWidth;
	private boolean run;

	public Background(float ScreenWidth) {
		mBackgroundLayers = new Array<BackgroundLayer>();
		mScreenWidth = ScreenWidth;
		run = false;
	}

	// Start, pause and stop for controlling the update. Stop also resets the backgrounds speed to the original values
	public void start() {
		run = true;
	}

	public void pause() {
		run = false;
	}

	public void stop() {
		run = false;
		resetSpeed();
	}

	// Add new layer to the background system. Returns true if successful, false if error
	public boolean addBackgroundLayer(BackgroundLayer mBackgroundLayerToAdd) {
		if (!mBackgroundLayers.contains(mBackgroundLayerToAdd, false)) {
			mBackgroundLayers.add(mBackgroundLayerToAdd);
			return true;
		}
		return false;
	}

	// Update each layer one by one and pass the delta value to the layers' update function
	public void update(float delta) {
		if (run) {
			Iterator<BackgroundLayer> mBackgroundLayerIterator = mBackgroundLayers.iterator();
			while (mBackgroundLayerIterator.hasNext()) {
				BackgroundLayer mBackgroundLayer = (BackgroundLayer) mBackgroundLayerIterator.next();
				mBackgroundLayer.update(delta);
				
			}
		}
	}

	// Redirects render call to all layers
	public void render(SpriteBatch mSpriteBatch) {
		for (BackgroundLayer mBackgroundLayer : mBackgroundLayers) {
			mBackgroundLayer.draw(mSpriteBatch);
		}
	}

	// Resets the speeds back to the original values
	public void resetSpeed() {
		for (BackgroundLayer mBackgroundLayer : mBackgroundLayers) {
			mBackgroundLayer.setSpeed(mBackgroundLayer.getOriginalSpeed());
		}
	}

	// Changes the speeds of all the layers with the given delta value (current speed + delta), if delta < 0, then decreases the speeds
	public void updateSpeed(float deltaSpeed) {
		for (BackgroundLayer mBackgroundLayer : mBackgroundLayers) {
			mBackgroundLayer.setSpeed(mBackgroundLayer.getSpeed() + deltaSpeed);
		}
	}

	// Returns the list of the background layers
	public Array<BackgroundLayer> getBackgroundLayers() {
		return mBackgroundLayers;
	}
	
	public float getScreenWidth() {
		return mScreenWidth;
	}

	// Disposes the background system
	public void dispose() {
		for (BackgroundLayer mBackgroundLayer : mBackgroundLayers) {
			mBackgroundLayer.dispose();
		}
		mBackgroundLayers.clear();
	}

}

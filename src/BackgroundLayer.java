import java.util.Iterator;

import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class BackgroundLayer {

	private TextureRegion mTextureRegion;
	private float y;
	private float originalSpeed;
	private float currentSpeed;
	private int spritesInTheScreen;
	private Array<Sprite> mLayerSprites;
	private Background mBackground;


	public BackgroundLayer(TextureRegion mTextureRegion, float y, float originalSpeed, Background mBackground) {

		// Pass the parameters
		this.mTextureRegion = mTextureRegion;
		this.mBackground = mBackground;
		this.mLayerSprites = new Array<Sprite>();
		this.originalSpeed = originalSpeed;
		this.currentSpeed = originalSpeed;
		this.y = y;

		// Set texture filter and texture wrap
		this.mTextureRegion.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		this.mTextureRegion.getTexture().setWrap(TextureWrap.Repeat, TextureWrap.Repeat);

		// Calculate how many sprites should be viewed to fill the screen, if the width of the texture is smaller the the width of the screen.
		spritesInTheScreen = (int) Math.ceil(mBackground.getScreenWidth() / mTextureRegion.getRegionWidth());
		
		// Create initial sprites
		for (int i = 0; i < spritesInTheScreen; i++) {
			Sprite mSprite = new Sprite(mTextureRegion);
			mSprite.setPosition(i * mTextureRegion.getRegionWidth(), y);
			mLayerSprites.add(mSprite);
		}
	}

	public void update(float delta) {

		// Move sprites
		Iterator<Sprite> iterSprites = mLayerSprites.iterator();
		while (iterSprites.hasNext()) {

			Sprite mItem = iterSprites.next();
			float x = mItem.getX();
			x = x - currentSpeed * delta;
			mItem.setPosition(x, y);

			// If sprite has passed the screen, delete it
			if (x <= -mTextureRegion.getRegionWidth()) {
				iterSprites.remove();
			}
			// Check if we need to create new sprite
			if (mLayerSprites.get(mLayerSprites.size-1).getX() <= mBackground.getScreenWidth() - mTextureRegion.getRegionWidth()) {
				createNewSprite();
			}	
		}
	}

	// Create new sprite
	private void createNewSprite() {
		Sprite newSprite = new Sprite(mTextureRegion);
		newSprite.setPosition(mBackground.getScreenWidth(), y);
		mLayerSprites.add(newSprite);
		System.out.println("hep");
	}

	// Draw all the sprites in current layer
	public void draw(SpriteBatch mSpriteBatch) {
		for (Sprite sprite : mLayerSprites) {
			sprite.draw(mSpriteBatch);
		}
	}

	// Getters and setters
	public float getSpeed() {
		return currentSpeed;
	}

	public void setSpeed(float f) {
		this.currentSpeed = f;
	}

	public float getOriginalSpeed() {
		return this.originalSpeed;
	}
	
	// Dispose
	public void dispose() {
		mLayerSprites.clear();
	}

}
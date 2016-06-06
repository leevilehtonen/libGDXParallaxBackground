# libGDX Parallax Background system

Easy to implement parallax background system for libGDX which can be modified for other engines

## Features:
- Multi layer parallax background
- Velocity controlling
- Easy to add layers
- Can be modified for several engines
- Easy to add new features


## How to use:
### Define the background system:
```
private Background mBackground;
```

### Initialize the background system:
```
mBackground = new Background(mScreenWidth);
```

### Add parallax layers:
```
mBackground.addBackgroundLayer(new BackgroundLayer(mTextureRegion, mYposition, mSpeed, mBackground));
```

### Start the background system:
```
mBackground.start();
```

### Inside the game loop, update and render the background:
```
mBackground.update(mDeltaTime);
mBackground.render(mSpriteBatch);
```

### If you want to pause or stop the background, use:
```
mBackground.pause();
mBackground.stop();
```
Stop also resets the speeds of the layers back to original

## Full example
```
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch mSpriteBatch;
	TextureRegion mTextureRegion;
	Background mBackground;
	
	@Override
	public void create () {
		mSpriteBatch = new SpriteBatch();
		mTextureRegion = new TextureRegion(new Texture("BackgroundLayer01.png"));
		mBackground = new Background(Gdx.graphics.getWidth());
		mBackground.addBackgroundLayer(new BackgroundLayer(mTextureRegion, 200, 100, mBackground));
		mBackground.start();
		
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mSpriteBatch.begin();
		mBackground.update(Gdx.graphics.getDeltaTime());
		mBackground.render(mSpriteBatch);
		mSpriteBatch.end();
	}
}
```

## Showcase
- https://play.google.com/store/apps/details?id=com.hypernirmo.game.android


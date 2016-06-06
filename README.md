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




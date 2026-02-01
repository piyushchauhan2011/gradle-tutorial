# Lesson 17: Game Development with libGDX

## Description
This lesson introduces you to game development using libGDX, a popular cross-platform Java game development framework. Learn how to create games with graphics, input handling, game loops, collision detection, and game state management. libGDX provides a high-level API built on top of OpenGL, making it easier to develop games compared to raw OpenGL programming.

## Key Concepts

### 1. libGDX Overview
- **What is libGDX?**: Cross-platform game development framework for Java
- **Architecture**: Built on OpenGL, supports desktop, Android, iOS, and web
- **Core Components**: Application lifecycle, rendering, input, audio, assets
- **Advantages**: High-level API, cross-platform, active community, well-documented

### 2. Application Lifecycle
- **create()**: Called once when application starts - initialize resources
- **render()**: Called every frame - update and draw game
- **resize()**: Called when window is resized
- **pause()/resume()**: Called when application loses/gains focus
- **dispose()**: Called when application closes - clean up resources

### 3. Rendering
- **ShapeRenderer**: Draw shapes (circles, rectangles, lines) - good for prototyping
- **SpriteBatch**: Draw textures/sprites - used in production games
- **Camera**: Viewport management for 2D and 3D games
- **Coordinate System**: Y-axis points up, origin at bottom-left

### 4. Input Handling
- **Keyboard Input**: `Gdx.input.isKeyPressed()` and `isKeyJustPressed()`
- **Mouse Input**: Position, buttons, scrolling
- **Touch Input**: For mobile platforms
- **Input Processors**: Custom input handling classes

### 5. Game Loop
- **Delta Time**: Time between frames - use for frame-independent movement
- **Fixed Timestep**: Update game logic at fixed intervals
- **Variable Timestep**: Update based on actual frame time
- **Frame Rate**: Target 60 FPS for smooth gameplay

### 6. Game Architecture
- **Game Objects**: Separate classes for game entities (Ball, Paddle, etc.)
- **Game State**: Manage score, lives, level, etc.
- **Collision Detection**: AABB (Axis-Aligned Bounding Box) collision
- **Separation of Concerns**: Keep rendering, logic, and input separate

## Runnable Examples

### Bouncing Ball Game
A simple but complete game demonstrating libGDX fundamentals:

```bash
./gradlew -p lesson17-libgdx run
```

**Game Features:**
- Ball bounces around the screen
- Player-controlled paddle at the bottom
- Collision detection between ball and paddle
- Score tracking
- Lives system
- Game over and reset functionality

**Controls:**
- **Arrow Keys** or **A/D**: Move paddle left/right
- **R**: Reset game

**Game Mechanics:**
- Ball bounces off walls
- Ball bounces off paddle (adds to score)
- Ball falls off bottom (loses a life)
- Game resets when lives reach 0

## Code Structure

### Main Classes

#### `DesktopLauncher.java`
- Entry point for desktop application
- Configures window settings (size, title, vsync)
- Creates and launches libGDX application

#### `BouncingBallGame.java` (Main Game Class)
- Extends `ApplicationAdapter` - implements libGDX lifecycle
- Manages game loop: input → update → render
- Handles collision detection
- Manages game state

**Key Methods:**
- `create()`: Initialize game objects
- `render()`: Main game loop
- `handleInput()`: Process keyboard input
- `update()`: Update game state
- `render()`: Draw game objects

#### `Ball.java`
- Represents the bouncing ball
- Handles physics (position, velocity)
- Bounces off walls
- Resets position when needed

**Key Methods:**
- `update()`: Update position based on velocity
- `bounceY()`: Bounce vertically (off paddle)
- `reset()`: Reset to starting position

#### `Paddle.java`
- Represents player-controlled paddle
- Handles movement
- Keeps paddle within screen bounds

**Key Methods:**
- `moveLeft()` / `moveRight()`: Move paddle
- `keepOnScreen()`: Prevent paddle from going off-screen
- `reset()`: Reset paddle position

#### `GameState.java`
- Manages game state (score, lives)
- Tracks game progress
- Handles game over condition

**Key Methods:**
- `incrementScore()`: Add to score
- `incrementLives()`: Decrease lives
- `reset()`: Reset game state
- `isGameOver()`: Check if game is over

## Exercises

### Beginner

1. **Change Colors**
   - Modify ball and paddle colors
   - Experiment with different color combinations
   - Add color changes on collision

2. **Adjust Speed**
   - Change ball speed
   - Change paddle speed
   - Make speed increase over time

3. **Add More Balls**
   - Create multiple balls
   - Handle collisions for all balls
   - Track score for each ball

4. **Modify Paddle Size**
   - Make paddle bigger/smaller
   - Make paddle size change based on score
   - Add power-ups that affect paddle size

### Intermediate

5. **Add Blocks/Bricks**
   - Create breakable blocks at top of screen
   - Detect collision between ball and blocks
   - Remove blocks when hit
   - Increase score when blocks are destroyed

6. **Improve Graphics**
   - Replace ShapeRenderer with SpriteBatch
   - Load and draw textures/sprites
   - Add background image
   - Add particle effects

7. **Add Sound Effects**
   - Play sound on paddle hit
   - Play sound on block destruction
   - Add background music
   - Use libGDX Audio module

8. **Add UI Elements**
   - Display score text using BitmapFont
   - Show game over screen
   - Add start menu
   - Add pause functionality

### Advanced

9. **Implement Power-ups**
   - Multiple balls power-up
   - Slow motion power-up
   - Larger paddle power-up
   - Extra life power-up

10. **Add Levels**
    - Different block patterns per level
    - Increasing difficulty
    - Level progression system
    - Save/load game state

11. **Implement Physics**
    - More realistic ball physics
    - Angular velocity
    - Friction and damping
    - Use Box2D for advanced physics

12. **Create a Full Game**
    - Complete Breakout/Arkanoid clone
    - Add menus, high scores, settings
    - Implement game saving
    - Add multiple game modes

## Testing

Run the test suite:

```bash
./gradlew -p lesson17-libgdx test
```

The tests cover:
- **Ball physics**: Movement, bouncing, reset
- **Paddle logic**: Movement, boundary checking
- **Game state**: Score, lives, game over
- **Collision detection**: Ball-paddle collision logic

## Key Takeaways

1. **Game Loop**: The heart of every game - input → update → render
2. **Delta Time**: Always use delta time for frame-independent movement
3. **Separation of Concerns**: Keep game objects, state, and rendering separate
4. **libGDX Lifecycle**: Understand create(), render(), dispose() methods
5. **Collision Detection**: AABB is simple but effective for many games
6. **Game State**: Manage score, lives, and game progression
7. **Input Handling**: Respond to player input in the game loop
8. **Resource Management**: Always dispose of resources in dispose()

## libGDX vs Raw OpenGL

| Feature | libGDX | Raw OpenGL |
|---------|--------|------------|
| **Ease of Use** | High-level API | Low-level API |
| **Setup** | Simple | Complex |
| **Cross-platform** | Built-in | Manual setup |
| **2D Graphics** | Easy | Moderate |
| **3D Graphics** | Supported | Full control |
| **Learning Curve** | Gentle | Steep |
| **Performance** | Good | Maximum |
| **Use Case** | Games, prototypes | Graphics apps, engines |

## Building and Running

### Prerequisites
- Java Development Kit (JDK) 11 or later
- Gradle (included as wrapper `./gradlew`)
- OpenGL 3.3+ compatible graphics card/driver

### Build
```bash
./gradlew -p lesson17-libgdx build
```

### Run Game
```bash
./gradlew -p lesson17-libgdx run
```

### Run Tests
```bash
./gradlew -p lesson17-libgdx test
```

## Common Issues and Solutions

### Issue: "Application doesn't start"
- **Solution**: Check that DesktopLauncher is set as main class
- Verify libGDX dependencies are downloaded
- Check console for error messages

### Issue: "Window is black"
- **Solution**: Ensure `render()` method is drawing something
- Check that `Gdx.gl.glClear()` is being called
- Verify ShapeRenderer/SpriteBatch is being used correctly

### Issue: "Input not working"
- **Solution**: Check that input is being polled in `render()`
- Verify key codes are correct (`Input.Keys.LEFT`, etc.)
- Ensure `Gdx.input` is being accessed on main thread

### Issue: "Game runs too fast/slow"
- **Solution**: Use delta time for all movement calculations
- Check frame rate with `Gdx.graphics.getFramesPerSecond()`
- Enable vsync in DesktopLauncher configuration

### Issue: "Memory leaks"
- **Solution**: Always dispose resources in `dispose()` method
- Don't create new objects every frame
- Use object pooling for frequently created objects

## Next Steps

After mastering this lesson, consider:

1. **Advanced Graphics**
   - SpriteBatch and textures
   - Animations and sprite sheets
   - Shaders and post-processing
   - Particle systems

2. **Physics**
   - Box2D integration
   - Collision callbacks
   - Joints and constraints
   - Physics-based movement

3. **Asset Management**
   - AssetManager for loading resources
   - Texture atlases
   - Sound and music loading
   - Font management

4. **Scene Management**
   - Screen system (MenuScreen, GameScreen, etc.)
   - Transitions between screens
   - Game state management
   - Save/load functionality

5. **Mobile Development**
   - Android setup
   - Touch input
   - Screen orientation
   - Performance optimization

6. **Networking**
   - Multiplayer games
   - Client-server architecture
   - Real-time synchronization

## Additional Resources

### libGDX Documentation
- [libGDX Official Website](https://libgdx.com/)
- [libGDX Wiki](https://github.com/libgdx/libgdx/wiki)
- [libGDX API Documentation](https://libgdx.com/api/)

### Tutorials
- [libGDX Official Tutorials](https://github.com/libgdx/libgdx/wiki#tutorials)
- [Game From Scratch libGDX Tutorial](http://www.gamefromscratch.com/page/libGDX-Tutorial-series.aspx)

### Example Games
- [libGDX Demos](https://github.com/libgdx/libgdx/tree/master/demos)
- [Awesome libGDX](https://github.com/rafaskb/awesome-libgdx) - Curated list of resources

### Concepts to Explore
- **Entity Component System (ECS)**: Architecture for complex games
- **Scene2D**: UI framework for libGDX
- **Box2D**: 2D physics engine
- **Ashley**: Entity framework for libGDX
- **TexturePacker**: Create texture atlases
- **Particle Editor**: Create particle effects visually

## Notes

- libGDX uses OpenGL under the hood but provides a much simpler API
- The framework handles cross-platform differences automatically
- ShapeRenderer is great for prototyping but use SpriteBatch for production
- Always use delta time for movement to ensure consistent gameplay across frame rates
- libGDX is actively maintained and has a large community
- The framework is used in many commercial games
- Understanding libGDX concepts helps with other game engines too


# Change Log
The format is based on [Keep a Changelog](http://keepachangelog.com/).

## [0.5] - 2022-02-05
 
### Added
- life count and icons
- level cursor
- endgame booth
- sounds

### Changed
- froze horizontal speed during jumps for consistency with original game 

### Fixed
- 1 pixel offset in level_carpet.png
- regression where fallToDeath was not triggered anymore

## [0.4] - 2022-02-04

### Added
- swordsman enemy
- carpet
- death by timeout


## [0.3] - 2022-02-01

### Added
- level template mechanism
- score and time update
- platform physics
- death by
    - fall
    - enemy collision

### Fixed
- offset in texture flipping when bobby goes backwards


## [0.2] - 2022-01-30

### Added
- bitmap font for score and time
- moving actors for bobby (keyboard-controlled), sausage, creampie
- animated sprites for bobby, sausage, creampie

### Fixed
- textures are now scaled using Nearest instead of Linear, 
i.e. they are pixel-perfect, not fuzzy

## [0.1] - 2022-01-28

### Added
- libgdx project skeleton generated
- git repository initialized
- generic Base* classes re-adapted
- mock screens
- raw multimedia files obtained 

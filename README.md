# jMCCS Tray

This is a sytemtray application using the [jMCCS][jmccs] library to control the brightness (luminance) of your monitors.

The systemtray-icon can be right-clicked, revealing a menu-list with a few predefined brightnesses.
The brightnesses are in the format of `X - Y`, where `X` is the brightness the main-monitor should be set to and `Y` is the brightness every other monitor should be set to.

## Building

Simply clone this repository and execute `mvn clean package` in the project-root.
This creates the file `target/jmccs-tray-X.X-jar-with-dependencies.jar` which is a standalone JAR, that can be executed just by double-clicking.

## License

This library includes implementations for both [Windows][jmccs-win] and [OS X][jmccs-osx], the OS X implementation is licensed under GPLv3, thus this project is licensed under GPLv3, too.


[jmccs]: https://github.com/pitkley/jmccs
[jmccs-win]: https://github.com/pitkley/jmccs-win
[jmccs-osx]: https://github.com/pitkley/jmccs-osx

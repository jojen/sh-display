# E-Ink Smart Home Display
Shows smart home and online data in e-Ink display.

Software components
- Spring Boot
- Google Calendar API
- OpenWeatherAPI

Hardware components
- WaveShare ePaper 7.5 HAT
- Raspberry Pi
- Loxone Smart Home


### Setup
##### Raspberry Pi
Install Raspberry OS lite

sudo apt-get update
sudo apt-get install -y default-jdk git cutycapt xvfb python3

sudo mkdir ~/sh-display
cd ~/sh-display
sudo git clone https://github.com/jojen/sh-display/release
sudo chmod +x bin/update-screen.sh

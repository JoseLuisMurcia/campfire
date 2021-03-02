/*
    1. Get our avatar attributes -> Sockets ?
    2. Preload resources -> DONE
    3. Print our avatar -> DONE ? nope xd
    4. Set button callbacks -> DONE
    5. Save our avatar attributes -> Sockets ?
*/

//AVATAR ATTRIBUTES -----------------------------------------------------------------

//Our avatar attributes
//Let's imagine we get the ids of the avatar (and everything is 0)
var attributes = [0, 0, 0, 0, 0]; //pColor, secColor, eyesColor, hat and accessory


//PRELOAD -----------------------------------------------------------------
var aliceBlue = "#F0F8FF";
var antiqueWhite = "#FAEBD7";
var aquaMarine = "#7FFFD4";
var black = "#000000";
var cadetBlue = "#5F9EA0";
var darkOrange = "#FF8C00";
var darkSalmon = "#E9967A";
var yellow = "#FFFF00";

//Array of avaliable resources
var pColors = [aliceBlue, antiqueWhite, aquaMarine, black, cadetBlue, darkOrange, darkSalmon, yellow];
var sColors = pColors;
var eyesColors = pColors;
var hats = [];
var accessories = [];
var resourcesLength;

//DISPLAY AND BUTTONS -----------------------------------------------------------------
var pColorDisplay;
var secColorDisplay;
var eyesColorDisplay;
var hatDisplay;
var accessoryDisplay;
//BUTTONS
var primaryColorRightBut;
var primaryColorLeftBut;
var secColorRightBut;
var secColorLeftBut;
var eyesColorRightBut;
var eyesColorLeftBut;
var hatRightBut;
var hatLeftBut;
var accessoryRightBut;
var accessoryLeftBut;
var saveBut;


const loadElements = () =>
{
    //DISPLAY
     pColorDisplay = document.getElementById("PColorDisplay");
     secColorDisplay = document.getElementById("SColorDisplay");
     eyesColorDisplay = document.getElementById("EyesDisplay");
     hatDisplay = document.getElementById("HatDisplay");
     accessoryDisplay = document.getElementById("AccessoryDisplay");

    //BUTTONS
     primaryColorRightBut = document.getElementById("PColorR");
     primaryColorLeftBut = document.getElementById("PColorL");

     secColorRightBut = document.getElementById("SColorR");
     secColorLeftBut = document.getElementById("SColorL");

     eyesColorRightBut = document.getElementById("EColorR");
     eyesColorLeftBut = document.getElementById("EColorL");

     hatRightBut = document.getElementById("HatR");
     hatLeftBut = document.getElementById("HatL");

     accessoryRightBut = document.getElementById("AccessoryR");
     accessoryLeftBut = document.getElementById("AccessoryL");

     saveBut = document.getElementById("Save");
}

function printAvatar()
{
    //pColorDisplay.color = pColors[attributes[0]];
    //secColorDisplay.style.color = secColors[attributes[1]];
    //eyesColorDisplay.style.color = eyesColors[attributes[2]];
    //console.log(hatDisplay);
    hatDisplay.src = hats[attributes[3]];
    accessoryDisplay.src = accessories[attributes[4]];
}

const setResourcesLength = () =>
{
    //console.log(pColors, sColors, eyesColors, hats, accessories);
    resourcesLength = [pColors.length, sColors.length,
        eyesColors.length, hats.length, accessories.length];
}

function preloadAvatarResources()
{
    hats = preloadHats();
    accessories = preloadAccessories();
    loadElements();
    setResourcesLength();
    printAvatar();
    setCallbacks();
}

window.onload = () => preloadAvatarResources();

//BUTTON CALLBACKS -----------------------------------------------------------------
function rotateArray(attributeId, toRight)
{
    attributes[attributeId] += (toRight  ? 1 : -1);
    attributes[attributeId] %= resourcesLength[attributeId];
    if(attributes[attributeId] < 0)
    attributes[attributeId] = resourcesLength[attributeId] + attributes[attributeId];
    console.log(resourcesLength[attributeId]);
    printAvatar();
}

function saveAvatar()
{
    //let msg = new Object();
    //msg.event = "SAVE";
    //msg.attributes = attributes;
    //socket.send(JSON.stringify(msg))
}

function setCallbacks()
{
    //Rotate to right callbacks
    primaryColorRightBut.onclick = () => rotateArray(0, true);
    secColorRightBut.onclick = () => rotateArray(1, true);
    eyesColorRightBut.onclick = () => rotateArray(2, true);
    hatRightBut.onclick = () => rotateArray(3, true);
    accessoryRightBut.onclick = () => rotateArray(4, true);

    //Rotate to left callbacks
    primaryColorLeftBut.onclick = () => rotateArray(0, false);
    secColorLeftBut.onclick = () => rotateArray(1, false);
    eyesColorLeftBut.onclick = () => rotateArray(2, false);
    hatLeftBut.onclick = () => rotateArray(3, false);
    accessoryLeftBut.onclick = () => rotateArray(4, false);

    saveBut.onclick = () => saveAvatar();
}

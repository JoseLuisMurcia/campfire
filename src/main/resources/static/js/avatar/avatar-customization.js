/*
    1. Get our avatar attributes
    2. Preload resources
    3. Print our avatar
    4. Set button callbacks -> change attribute & print avatar / save and go home
*/

//AVATAR ATTRIBUTES -----------------------------------------------------------------

//Our avatar attributes
//Let's imagine we get the ids of the avatar (and everything is 0)
var attributes = [0, 0, 0, 0, 0]; //pColor, secColor, eyesColor, hat and accessory

//PRELOAD -----------------------------------------------------------------

//Array of avaliable resources
var pColors = [];
var sColors = [];
var eyesColors = [];
var hats = [];
var accessories = [];

function printAvatar()
{
    //pColorDisplay -> set color
    //secColorDisplay -> set color
    //eyesColorDisplay -> set color
    //hatIdDisplay -> set image
    //accessoryDisplay -> set image
}

function setResourcesLength()
{
    var resourcesLength = [pColors.length, sColors.length,
        eyesColors.length, hats.length, accessories.length];
}

function preloadAvatarResources()
{
    pColors = preloadPrimaryColors();
    sColors = preloadSecColors();
    eyesColors = preloadEyesColors();
    hats = preloadHats();
    accessories = preloadAccessories();
    setResourcesLength();
    printAvatar();
}

preloadAvatarResources();


//DISPLAY AND BUTTONS -----------------------------------------------------------------

//DISPLAY
var pColorDisplay = document.getElementById("");
var secColorDisplay = document.getElementById("");
var eyesColorDisplay = document.getElementById("");
var hatIdDisplay = document.getElementById("");
var accessoryDisplay = document.getElementById("");

//BUTTONS
var initColorRightBut = document.getElementById("");
var initColorLeftBut = document.getElementById("");

var secColorRightBut = document.getElementById("");
var secColorLeftBut = document.getElementById("");

var eyesColorRightBut = document.getElementById("");
var eyestColorLeftBut = document.getElementById("");

var hatRightBut = document.getElementById("");
var hatrLeftBut = document.getElementById("");

var accessoryRightBut = document.getElementById("");
var accessoryLeftBut = document.getElementById("");

//BUTTON CALLBACKS -----------------------------------------------------------------
function rotateArray(attributeId, toRight)
{
    attributes[attributeId] += (toRight  ? 1 : -1) % resourcesLength[attributeId];
    printAvatar();
}

 //Rotate to right callbacks
 initColorRightBut.onclick = () => rotateArray(0, true);
 secColorRightBut.onclick = () => rotateArray(1, true);
 eyesColorRightBut.onclick = () => rotateArray(2, true);
 hatRightBut.onclick = () => rotateArray(3, true);
 accessoryRightBut.onclick = () => rotateArray(4, true);

 //Rotate to left callbacks
 initColorRightBut.onclick = () => rotateArray(0, false);
 secColorRightBut.onclick = () => rotateArray(1, false);
 eyesColorRightBut.onclick = () => rotateArray(2, false);
 hatRightBut.onclick = () => rotateArray(3, false);
 accessoryRightBut.onclick = () => rotateArray(4, false);

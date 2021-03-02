/*
    1. Get our avatar attributes -> Sockets ?
    2. Preload resources -> DONE
    3. Print our avatar -> DONE
    4. Set button callbacks -> DONE
    5. Save our avatar attributes -> Sockets ?
*/

//AVATAR ATTRIBUTES -----------------------------------------------------------------

//Our avatar attributes
//Let's imagine we get the ids of the avatar (and everything is 0)
var attributes = [0, 0, 0, 0, 0]; //pColor, secColor, eyesColor, hat and accessory


//PRELOAD -----------------------------------------------------------------
var aliceBlue = "invert(98%) sepia(67%) saturate(5182%) hue-rotate(175deg) brightness(116%) contrast(108%)";
var antiqueWhite = "invert(98%) sepia(82%) saturate(588%) hue-rotate(301deg) brightness(105%) contrast(96%)";
var aquaMarine = "invert(93%) sepia(10%) saturate(1907%) hue-rotate(88deg) brightness(104%) contrast(101%)";
var black = "invert(0%) sepia(5%) saturate(7500%) hue-rotate(93deg) brightness(101%) contrast(99%)";
var cadetBlue = "invert(69%) sepia(5%) saturate(2802%) hue-rotate(133deg) brightness(83%) contrast(88%)";
var darkOrange = "invert(56%) sepia(92%) saturate(1604%) hue-rotate(358deg) brightness(98%) contrast(109%)";
var yellow = "invert(83%) sepia(78%) saturate(5143%) hue-rotate(357deg) brightness(103%) contrast(102%)";

//Array of avaliable resources
var pColors = [aliceBlue, antiqueWhite, aquaMarine, black, cadetBlue, darkOrange, yellow];
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
    pColorDisplay.style.filter = pColors[attributes[0]];
    secColorDisplay.style.filter = sColors[attributes[1]];
    eyesColorDisplay.style.filter = eyesColors[attributes[2]];
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
    //- SOCKETS -> ATTRIBUTES
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
    let msg = new Object();
    msg.event = "SAVE";
    msg.attributes = attributes;
    socket.send(JSON.stringify(msg))
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

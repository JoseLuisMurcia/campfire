//INITIALIZE SOCKET
var socket = new WebSocket("ws://127.0.0.1:8080/avatar-customization");

function preloadImages() {
    var _images = new Array()
    for (i = 0; i < arguments.length; i++) 
    {
        _images[i] = new Image()
        _images[i].src = arguments[i]
    }
    return _images;
}

function preloadHats()
{
    return ["assets/avatar-resources/hats/HatDefault.png",
        "assets/avatar-resources/hats/Hat01.png",
        "assets/avatar-resources/hats/Hat02.png"];
    //)
}

function preloadAccessories()
{
    //preloadImages(
    return ["assets/avatar-resources/accessories/AccessoryDefault.png",
        "assets/avatar-resources/accessories/Accessory01.png",
        "assets/avatar-resources/accessories/Accessory02.png"];
    //)
}

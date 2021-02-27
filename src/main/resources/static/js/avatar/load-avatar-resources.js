function preloadImages() {
    var _images = new Array()
    for (i = 0; i < preload.arguments.length; i++) 
    {
        _images[i] = new Image()
        _images[i].src = preload.arguments[i]
    }
    return _images;
}

function preloadPrimaryColors()
{
    //Something here, depends on the storage :c
}

function preloadSecColors()
{
    //Something here, depends on the storage :c
}

function preloadEyesColors()
{
    //Something here, depends on the storage :c
}

function preloadHats()
{
    return preloadImages(
        "http://domain.tld/gallery/image-001.jpg",
        "http://domain.tld/gallery/image-002.jpg",
        "http://domain.tld/gallery/image-003.jpg"
    )
}

function preloadAccessories()
{
    return preloadImages(
        "http://domain.tld/gallery/image-001.jpg",
        "http://domain.tld/gallery/image-002.jpg",
        "http://domain.tld/gallery/image-003.jpg"
    )
}

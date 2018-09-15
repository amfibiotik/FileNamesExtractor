#target;
photoshop;
var LIST_PATH = "o:\\!schools\\!!випуск2018\\ш6, 9Г клас\\list.txt";
var MODEL_PATH = "o:\\!schools\\!!випуск2018\\ш6, 9Г клас\\!FB\\";
var FILE_EXTENSION = "psd";
var names = []; //array of names
var b = new File(LIST_PATH);
b.open('r'); // open as read-only

var i = 0;
// names from file to array
while (!b.eof) {
    names[i] = b.readln();
    names[i] = names[i].replace(" ", "\r");
    i++;
}
b.close();

var j = 2; //start number of file

//var i;
for (i = 0; i < names.length; i = i + 2) {
    // format with leading zeros
    if (j < 10)
        var filePath = MODEL_PATH + "0" + j + "." + FILE_EXTENSION;
    else
        var filePath = MODEL_PATH + j + "." + FILE_EXTENSION;


    var fileRef = new File(filePath);
    if (fileRef.exists) {
        app.open(fileRef);
        activeDocument.activeLayer = activeDocument.artLayers.getByName("l_text");
        app.activeDocument.activeLayer.textItem.contents = names[i];
        activeDocument.activeLayer = activeDocument.artLayers.getByName("r_text");
        app.activeDocument.activeLayer.textItem.contents = names[i + 1];
        app.activeDocument.close(SaveOptions.SAVECHANGES);
    } else {
        alert("File not found");
    }
    j++;
}








# FileNamesExtractor

The tool handles a special .txt files, searches in them special names of files and generates(TODO) bat-file to select files matching the found names. Also appended JS-script for Adobe Photoshop to automatic put some txt-info from list to .psd-files

->Example:
James Hetfield
Основне-3009
Портрет 1207
Вертикальні:1447,2096
Внизу:2168,3678,2118

Kirk Hammett
Основне-3009
Портрет 1207
Вертикальні:1447,2096
Внизу:2168,3678,2118

->Output:
James Hetfield
3009.jpg
1207.jpg
1447.jpg
2096.jpg
2168.jpg
3678.jpg
2118.jpg

Kirk Hammett
3009.jpg
1207.jpg
1447.jpg
2096.jpg
2168.jpg
3678.jpg
2118.jpg

-======== ТІЛЬКИ ІМЕНА ========-

James Hetfield
Kirk Hammett

-======== ПОРТРЕТИ ========-

1207.jpg

-======== ОСНОВНІ ========-

3009.jpg

-======== ВЕРТИКАЛЬНІ ========-

1447.jpg
2096.jpg


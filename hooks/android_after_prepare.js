var fs = require('fs');

const srcFile = 'google-services.json';

if (fs.existsSync(srcFile)) {
    try {
        var fileContents = fs.readFileSync(srcFile).toString();
        fs.writeFileSync("platforms/android/app/google-services.json", fileContents);
        console.log('google-services.json file copied successfully.');
    } catch (err) {
        console.log('error while copying google-services.json file: ' + err);
    }
} else {
    console.log('No google-services.json file found.');
}
